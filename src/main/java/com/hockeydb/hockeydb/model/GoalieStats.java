package com.hockeydb.hockeydb.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.AccessLevel;

@Entity
@Getter
public class GoalieStats {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "goalie_stats_id")
    @Getter(AccessLevel.NONE)
    private UUID goalieStatsId;

    @ManyToOne
    @JoinColumn(name = "goalie_id")
    @JsonIgnore
    private Goalie goalie;

    @ManyToOne
    @JoinColumn(name = "season_id")
    @JsonIgnore
    private Season season;

    @Column
    private Integer gamesPlayed;

    @Column
    private Integer gamesStarted;

    @Column
    private Integer wins;

    @Column
    private Integer losses;

    @Column
    private Integer overtimeLosses;

    @Column
    private Integer shotsAgainst;

    @Column
    private Float goalsAgainstAverage;

    @Column
    private Float savePercentage;

    @Column
    private Integer shutouts;

    @Column
    private Integer goals;

    @Column
    private Integer assists;

    @Column
    private Integer penaltyMinutes;

    @Column
    private Integer timeOnIce;

    public UUID getID() {
        return goalieStatsId;
    }
}