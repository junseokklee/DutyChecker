package com.dutyChecker.slackbot.scheduler;

import com.dutyChecker.slackbot.service.TeamsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DailyReportScheduler {

    private final TeamsService teamsService;

    @Scheduled(cron = "0 0 9 * * ?")  // 매일 09시에 실행
    public void sendDailyReportMorning() {
        log.info("09시 일일 당직 보고 스케줄러 실행 시작");
        try {
            teamsService.sendDailyReportOff(9);
            log.info("09시 일일 당직 보고 스케줄러 실행 완료");
        } catch (Exception e) {
            log.error("09시 일일 당직 보고 스케줄러 실행 실패: {}", e.getMessage(), e);
        }
    }

    @Scheduled(cron = "0 0 13 * * ?")  // 매일 13시에 실행
    public void sendDailyReportNoon() {
        log.info("13시 일일 당직 보고 스케줄러 실행 시작");
        try {
            teamsService.sendDailyReportOff(13);
            log.info("13시 일일 당직 보고 스케줄러 실행 완료");
        } catch (Exception e) {
            log.error("13시 일일 당직 보고 스케줄러 실행 실패: {}", e.getMessage(), e);
        }
    }

//    //@Scheduled(cron = "0 0 15 * * ?")  // 매일 15시에 실행
//    public void sendDailyReportMiddle() {
//        log.info("15시 일일 당직 보고 스케줄러 실행 시작");
//        try {
//            teamsService.sendDailyReportOff(15);
//            log.info("15시 일일 당직 보고 스케줄러 실행 완료");
//        } catch (Exception e) {
//            log.error("15시 일일 당직 보고 스케줄러 실행 실패: {}", e.getMessage(), e);
//        }
//    }

    @Scheduled(cron = "0 0 18 * * ?")  // 매일 18시에 실행
    public void sendDailyReportOff() {
        log.info("18시 일일 당직 보고 스케줄러 실행 시작");
        try {
            teamsService.sendDailyReportOff(18);
            log.info("18시 일일 당직 보고 스케줄러 실행 완료");
        } catch (Exception e) {
            log.error("18시 일일 당직 보고 스케줄러 실행 실패: {}", e.getMessage(), e);
        }
    }

    @Scheduled(cron = "0 58 21 * * ?")  // 매일 21:58에 실행
    public void sendDailyReportEnd() {
        log.info("22시 일일 당직 보고 스케줄러 실행 시작");
        try {
            teamsService.sendDailyReportOff(22);
            log.info("22시 일일 당직 보고 스케줄러 실행 완료");
        } catch (Exception e) {
            log.error("22시 일일 당직 보고 스케줄러 실행 실패: {}", e.getMessage(), e);
        }
    }
} 