package com.dutyChecker.slackbot.model;

public record DutyReportData(
        String webConCount,
        int avgWebConCount,
        String wasTps,
        String offTps,
        String imageUrl
) {}
