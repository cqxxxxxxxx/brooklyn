package com.cqx.brooklyn.prometheusdingtalk;

import com.cqx.brooklyn.prometheusdingtalk.parser.PropertyPlaceholderHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@RequestMapping("/dingtalk")
@SpringBootApplication
public class PrometheusDingtalkApplication {

    @Value("${dt.url}")
    private String dingTalkUrl;

    public static void main(String[] args) {
        SpringApplication.run(PrometheusDingtalkApplication.class, args);
    }

    private static WebClient webClient;

    @PostConstruct
    public void init() {
        webClient = WebClient.builder().baseUrl(dingTalkUrl).build();
    }

    private static ObjectMapper objectMapper = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    private String markdownTemplate = "### ${/commonLabels/alertname}\b\b\n>\b服务:[${/commonLabels/service}]\b\b\n实例:[${/commonLabels/instance}]\b\b\n接口:\b\b\n";

    @PostMapping("/post-alert")
    public void alertGate(@RequestBody String json) {
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            PropertyPlaceholderHelper propertyPlaceholderHelper = new PropertyPlaceholderHelper("${", "}");
            String s = propertyPlaceholderHelper.replacePlaceholders(markdownTemplate, placeholderName -> jsonNode.at(placeholderName).textValue());
            DingTalkModel dingTalkModel = new DingTalkModel();
            DingTalkModel.Markdown markdown = new DingTalkModel.Markdown();
            markdown.setTitle(jsonNode.at("/groupLabels/alertname").textValue());
            ArrayNode alerts = (ArrayNode) jsonNode.get("alerts");
            for (JsonNode alert : alerts) {
                JsonNode at = alert.at("/annotations/summary");
                String summary = at.textValue();
                s += summary + "\b\b\n";
            }
            markdown.setText(s);
            dingTalkModel.setMarkdown(markdown);
            dingTalkModel.setMsgtype("markdown");
            String block = webClient.post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(dingTalkModel)
                    .retrieve()
                    .bodyToMono(String.class).block();
            log.debug(block);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
    }

}
