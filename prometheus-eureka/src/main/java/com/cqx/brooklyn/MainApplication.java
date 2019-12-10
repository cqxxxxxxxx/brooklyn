package com.cqx.brooklyn;

import com.cqx.brooklyn.fetch.Application;
import com.cqx.brooklyn.fetch.EurekaRoot;
import com.cqx.brooklyn.fetch.EurekaServiceFetch;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@EnableScheduling
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Autowired
    EurekaServiceFetch serviceFetch;

    @Value("${custom.file.path}")
    private String filePath;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Scheduled(fixedDelay = 6000, initialDelay = 0)
    public void writePrometheusConfig() throws IOException {
        EurekaRoot eurekaRoot = serviceFetch.fetch();
        List<StaticConfig> staticConfigs = eurekaRoot.getApplication()
                .stream()
                .map(x -> x.getInstance().stream())
                .map(x -> {
                    StaticConfig staticConfig = new StaticConfig();
                    List<Application.Instance> instances = x.collect(Collectors.toList());
                    Optional<Application.Instance> instanceOptional = instances.stream().findFirst();
                    if (instanceOptional.isPresent()) {
                        Map<String, String> labels = new HashMap<>(4);
                        Application.Instance instance = instanceOptional.get();
                        labels.put("app", instance.getApp());
                        labels.put("host", instance.getHostName());
                        labels.put("instanceId", instance.getInstanceId());
                        staticConfig.setLabels(labels);
                    }
                    List<String> targets = instances.stream().map(y -> y.getIpAddr() + ":" + y.getPort().get("$")).collect(Collectors.toList());
                    staticConfig.setTargets(targets);
                    return staticConfig;
                })
                .collect(Collectors.toList());
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(staticConfigs);
        Path path = Paths.get(filePath);
        Files.write(path, json.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    }
}
