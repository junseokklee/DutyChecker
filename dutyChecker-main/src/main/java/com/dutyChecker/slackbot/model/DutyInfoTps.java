package com.dutyChecker.slackbot.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbTPSXView")
@Getter
@NoArgsConstructor
public class DutyInfoTps {

    @EmbeddedId
    private DutyInfoId id;

    @Column(name = "WAS_TPS")
    private String wasTps;

    @Column(name = "OFF_TPS")
    private String offTps;

    @Column(name = "IMAGE_STR")
    private String image;

    public DutyInfoTps(DutyInfoId id, String wasTps, String offTps, String image) {
        this.id = id;
        this.wasTps = wasTps;
        this.offTps = offTps;
        this.image = image;
    }
}
