package com.cqx.brooklyn.fetch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/12/8
 */
@Slf4j
@Service
public class EurekaServiceFetch {

    @Value("${custom.eureka.url:http://localhost:8761}")
    private String location;

    private ObjectMapper objectMapper = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    public EurekaRoot fetch() {
        WebClient client = WebClient.create(location);
        Mono<String> result = client.get()
                .uri("/eureka/apps").accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
        String json = result.blockOptional().get();
        EurekaRoot eurekaRoot = parse(json);
        return eurekaRoot;
    }

    private EurekaRoot parse(String json) {
        EurekaRoot root;
        try {
            JsonNode jsonNode = objectMapper.readTree(json).get("applications");
            root = objectMapper.treeToValue(jsonNode, EurekaRoot.class);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("json解析异常");
        }
        return root;
    }

    public static void main(String[] args) {
        EurekaServiceFetch fetch = new EurekaServiceFetch();
        fetch.fetch();
    }
}
