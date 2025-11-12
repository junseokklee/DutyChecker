package com.dutyChecker.slackbot.scheduler;

import com.dutyChecker.slackbot.service.TeamsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MinuteReportScheduler {

    private final TeamsService teamsService;

    //@Scheduled(cron = "0 */1 * * * ?")  // 1분마다 실행
    public void sendMinuteReport() {
        log.info("1분 간격 보고 스케줄러 실행 시작");
        try {
            teamsService.sendDailyReportOff(15);
            log.info("1분 간격 보고 스케줄러 실행 완료");
        } catch (Exception e) {
            log.error("1분 간격 보고 스케줄러 실행 실패: {}", e.getMessage(), e);
        }
    }
}