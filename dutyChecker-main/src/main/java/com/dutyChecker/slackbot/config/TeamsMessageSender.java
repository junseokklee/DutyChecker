package com.dutyChecker.slackbot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TeamsMessageSender {

    private final TeamsProperties teamsProperties;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public void send(Map<String, Object> message) {
        try {
            String jsonPayload = objectMapper.writeValueAsString(message);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(jsonPayload, headers);

            URL sendingUrl = teamsProperties.getUrl();
            restTemplate.postForEntity(sendingUrl.toURI(), request, String.class);
        } catch (Exception e) {
            throw new RuntimeException("Teams 메시지 전송 실패", e);
        }
    }
}
