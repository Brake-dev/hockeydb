package com.hockeydb.hockeydb.model;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "game_id")
    private UUID gameId;

    @Column
    private UUID seasonId;

    @Column
    private UUID homeTeam;

    @Column
    private UUID awayTeam;

    @Column
    private Boolean overtime;

    @Column
    private Boolean shootout;

    @Column
    private Integer homeTeamGoals;

    @Column
    private Integer homeTeamShotsOnGoal;

    @Column
    private Float homeTeamFaceOffPercent;

    @Column
    private Float homeTeamPowerplayPercent;

    @Column
    private Integer homeTeamPenaltyMinutes;

    @Column
    private Integer homeTeamHits;

    @Column
    private Integer homeTeamBlockedShots;

    @Column
    private Integer homeTeamGiveaways;

    @Column
    private Integer homeTeamTakeaways;

    @Column
    private Integer awayTeamGoals;

    @Column
    private Integer awayTeamShotsOnGoal;

    @Column
    private Float awayTeamFaceOffPercent;

    @Column
    private Float awayTeamPowerplayPercent;

    @Column
    private Integer awayTeamPenaltyMinutes;

    @Column
    private Integer awayTeamHits;

    @Column
    private Integer awayTeamBlockedShots;

    @Column
    private Integer awayTeamGiveaways;

    @Column
    private Integer awayTeamTakeaways;
}
