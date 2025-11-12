package com.dutyChecker.slackbot.model;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class DutyInfoId implements Serializable {

    private String logDate;
    private String logTime;
    private String ip;

    public DutyInfoId(String logDate, String logTime, String ip) {
        this.logDate = logDate;
        this.logTime = logTime;
        this.ip = ip;
    }
}
