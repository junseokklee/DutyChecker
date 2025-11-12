package com.dutyChecker.slackbot.service;

import com.dutyChecker.slackbot.config.TeamsMessageBuilder;
import com.dutyChecker.slackbot.config.TeamsMessageSender;
import com.dutyChecker.slackbot.model.DutyReportData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TeamsService {

    private final DutyInfoService dutyInfoService;
    private final TeamsMessageBuilder messageBuilder;
    private final TeamsMessageSender messageSender;

    public void sendDailyReport(String reportTitle) {
        DutyReportData reportData = dutyInfoService.collectDutyReportData();
        String messageText = messageBuilder.buildReportMessage(reportTitle, reportData);
        Map<String, Object> adaptiveCard = messageBuilder.buildAdaptiveCard(messageText, reportData.imageUrl());
        messageSender.send(adaptiveCard);
    }

    public void sendMinuteReport() {
        sendDailyReport("[테스트] 1분 당직 보고");
    }

    public void sendDailyReportOff(int hour) {
        sendDailyReport(  getFormattedDate() + " " + hour + ":00 당직점검");
    }

    // 요일을 한글로 변환하는 함수
    private static String getKoreanDayOfWeek(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY: return "월";
            case TUESDAY: return "화";
            case WEDNESDAY: return "수";
            case THURSDAY: return "목";
            case FRIDAY: return "금";
            case SATURDAY: return "토";
            case SUNDAY: return "일";
            default: return "";
        }
    }

    // 날짜를 원하는 형식으로 반환하는 함수
    public static String getFormattedDate() {
        LocalDate today = LocalDate.now();
        String datePart = today.format(DateTimeFormatter.ofPattern("yy/MM/dd"));
        String dayPart = getKoreanDayOfWeek(today.getDayOfWeek());
        return "[" + datePart + "(" + dayPart + ")]";
    }
}
