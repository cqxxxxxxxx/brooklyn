package com.cqx.brooklyn.prometheusdingtalk;

import com.cqx.brooklyn.prometheusdingtalk.parser.PropertyPlaceholderHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSubstuti {

    public static void main(String[] args) throws JsonProcessingException {
        String json = "{\n" +
                "  \"receiver\": \"web\\\\.hook\",\n" +
                "  \"status\": \"firing\",\n" +
                "  \"alerts\": [\n" +
                "    {\n" +
                "      \"status\": \"firing\",\n" +
                "      \"labels\": {\n" +
                "        \"alertname\": \"JvmInstanceDown\",\n" +
                "        \"app\": \"OAUTH2-SERVER\",\n" +
                "        \"host\": \"192.168.0.187\",\n" +
                "        \"instance\": \"139.9.168.137:8000\",\n" +
                "        \"instanceId\": \"ceafdf22bcfb:oauth2-server:8000\",\n" +
                "        \"job\": \"eureka-sd\",\n" +
                "        \"severity\": \"page\"\n" +
                "      },\n" +
                "      \"annotations\": {\n" +
                "        \"description\": \"139.9.168.137:8000 of job eureka-sd has been down for more than 30 seconds.\",\n" +
                "        \"summary\": \"实例 139.9.168.137:8000 下线\"\n" +
                "      },\n" +
                "      \"startsAt\": \"2019-12-11T20:59:15.628586008+08:00\",\n" +
                "      \"endsAt\": \"0001-01-01T00:00:00Z\",\n" +
                "      \"generatorURL\": \"http://cqxdeMacBook-Pro.local:9090/graph?g0.expr=up+%3D%3D+0\\u0026g0.tab=1\",\n" +
                "      \"fingerprint\": \"d02f43a110d5e537\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"status\": \"firing\",\n" +
                "      \"labels\": {\n" +
                "        \"alertname\": \"JvmInstanceDown\",\n" +
                "        \"app\": \"SERVICE-SQLQUERY\",\n" +
                "        \"host\": \"192.168.0.187\",\n" +
                "        \"instance\": \"139.9.168.137:8012\",\n" +
                "        \"instanceId\": \"192.168.0.187:service-sqlquery:8012\",\n" +
                "        \"job\": \"eureka-sd\",\n" +
                "        \"severity\": \"page\"\n" +
                "      },\n" +
                "      \"annotations\": {\n" +
                "        \"description\": \"139.9.168.137:8012 of job eureka-sd has been down for more than 30 seconds.\",\n" +
                "        \"summary\": \"实例 139.9.168.137:8012 下线\"\n" +
                "      },\n" +
                "      \"startsAt\": \"2019-12-11T20:59:15.628586008+08:00\",\n" +
                "      \"endsAt\": \"0001-01-01T00:00:00Z\",\n" +
                "      \"generatorURL\": \"http://cqxdeMacBook-Pro.local:9090/graph?g0.expr=up+%3D%3D+0\\u0026g0.tab=1\",\n" +
                "      \"fingerprint\": \"a16f5e0994776e96\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"groupLabels\": {\n" +
                "    \"alertname\": \"JvmInstanceDown\"\n" +
                "  },\n" +
                "  \"commonLabels\": {\n" +
                "    \"alertname\": \"JvmInstanceDown\",\n" +
                "    \"host\": \"192.168.0.187\",\n" +
                "    \"job\": \"eureka-sd\",\n" +
                "    \"severity\": \"page\"\n" +
                "  },\n" +
                "  \"commonAnnotations\": {},\n" +
                "  \"externalURL\": \"http://cqxdeMacBook-Pro.local:9093\",\n" +
                "  \"version\": \"4\",\n" +
                "  \"groupKey\": \"{}:{alertname=\\\"JvmInstanceDown\\\"}\"\n" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        String markdown = "###${/receiver}  \n ${/version}";
        PropertyPlaceholderHelper propertyPlaceholderHelper = new PropertyPlaceholderHelper("${", "}");
        String s = propertyPlaceholderHelper.replacePlaceholders(markdown, placeholderName -> {
            return jsonNode.at(placeholderName).textValue();
        });
        System.out.println(s);
    }
}
