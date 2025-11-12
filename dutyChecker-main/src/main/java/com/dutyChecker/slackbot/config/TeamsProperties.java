package com.dutyChecker.slackbot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component
@ConfigurationProperties(prefix = "teams.webhook")
@Data
public class TeamsProperties {
    private URL url;
}
