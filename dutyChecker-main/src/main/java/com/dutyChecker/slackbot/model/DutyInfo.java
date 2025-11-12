package com.dutyChecker.slackbot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "IF_WEB_CONNECTION")
@Getter
@NoArgsConstructor
public class DutyInfo {

    @EmbeddedId
    private DutyInfoId id;

    @Column(name = "WEB_CON_COUNT")
    private String webConCount;

    public DutyInfo(DutyInfoId id, String webConCount) {
        this.id = id;
        this.webConCount = webConCount;
    }
}
