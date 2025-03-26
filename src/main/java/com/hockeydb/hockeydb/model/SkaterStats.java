package com.hockeydb.hockeydb.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.AccessLevel;

@Entity
@Getter
public class SkaterStats {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "skater_stats_id")
    @Getter(AccessLevel.NONE)
    private UUID skaterStatsId;

    @ManyToOne
    @JoinColumn(name = "skater_id")
    @JsonIgnore
    private Skater skater;

    @ManyToOne
    @JoinColumn(name = "season_id")
    @JsonIgnore
    private Season season;

    @Column
    private Integer gamesPlayed;

    @Column
    private Integer goals;

    @Column
    private Integer assists;

    @Column
    private Integer points;

    @Column
    private Integer plusMinus;

    @Column
    private Integer penaltyMinutes;

    @Column
    private Integer powerplayGoals;

    @Column
    private Integer powerplayPoints;

    @Column
    private Integer shorthandedGoals;

    @Column
    private Integer shorthandedPoints;

    @Column(name = "time_on_ice_per_gp")
    private Integer timeOnIcePerGP;

    @Column
    private Integer gameWinningGoals;

    @Column
    private Integer overtimeGoals;

    @Column
    private Integer shotsOnGoal;

    @Column
    private Float faceOffPercentage;

    public UUID getID() {
        return skaterStatsId;
    }
}