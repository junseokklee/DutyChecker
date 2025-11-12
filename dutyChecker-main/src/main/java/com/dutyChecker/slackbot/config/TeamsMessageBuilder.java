package com.dutyChecker.slackbot.config;

import com.dutyChecker.slackbot.model.DutyReportData;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class TeamsMessageBuilder {

    public String buildReportMessage(String title, DutyReportData data) {
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return String.format("""
                %s
                \n -예매 WAS TPS: %s
                \n -통합발권 APP TPS: %s
                \n -평균 WEB 커넥션: %d
                """, title, data.wasTps(), data.offTps(), data.avgWebConCount());
    }

//이미지포함로직
    public Map<String, Object> buildAdaptiveCard(String contentText, String imageUrl) {
        Map<String, Object> textBlock = Map.of(
            "type", "TextBlock",
            "text", contentText,
            "wrap", true
        );
        Map<String, Object> imageBlock = Map.of(
        "type", "Image",
                "url", imageUrl,
                "size", "Auto"
        );
        List<Map<String, Object>> body = List.of(textBlock, imageBlock);
        Map<String, Object> content = Map.of(
        "$schema", "http://adaptivecards.io/schemas/adaptive-card.json",
                "type", "AdaptiveCard",
                "version", "1.4",
                "body", body
        );
        Map<String, Object> attachment = Map.of(
        "contentType", "application/vnd.microsoft.card.adaptive",
                "content", content
        );
        return Map.of(
                "type", "message",
                "attachments", List.of(attachment)
        );
    }


//backup
//    public Map<String, Object> buildAdaptiveCard(String contentText) {
//        // 기존 createAdaptiveCardMessage 로직 그대로
//        Map<String, Object> textBlock = new LinkedHashMap<>();
//        textBlock.put("type", "TextBlock");
//        textBlock.put("text", contentText);
//        textBlock.put("wrap", true);
//
//        List<Map<String, Object>> body = new ArrayList<>();
//        body.add(textBlock);
//
//        Map<String, Object> content = new LinkedHashMap<>();
//        content.put("$schema", "http://adaptivecards.io/schemas/adaptive-card.json");
//        content.put("type", "AdaptiveCard");
//        content.put("version", "1.4");
//        content.put("body", body);
//
//        Map<String, Object> attachment = new LinkedHashMap<>();
//        attachment.put("contentType", "application/vnd.microsoft.card.adaptive");
//        attachment.put("content", content);
//
//        List<Map<String, Object>> attachments = new ArrayList<>();
//        attachments.add(attachment);
//
//        Map<String, Object> message = new LinkedHashMap<>();
//        message.put("type", "message"); // 최상위에 "type": "message" 추가
//        message.put("attachments", attachments);
//
//        return message;
//    }
}