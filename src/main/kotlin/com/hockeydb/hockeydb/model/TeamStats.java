package com.hockeydb.hockeydb.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class TeamStats {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "team_stats_id")
    private UUID teamStatsId;

    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonIgnore
    private Team team;

    @ManyToOne
    @JoinColumn(name = "season_id")
    @JsonIgnore
    private Season season;

    @Column
    private Integer games;

    @Column
    private Integer wins;

    @Column
    private Integer losses;

    @Column
    private Integer overtimeWins;

    @Column
    private Integer overtimeLosses;

    @Column
    private Integer points;

    @Column
    private Integer goalsFor;

    @Column
    private Integer goalsAgainst;

    @Column(columnDefinition = "bpchar")
    private String homeRecord;

    @Column(columnDefinition = "bpchar")
    private String awayRecord;

    @Column(columnDefinition = "bpchar")
    private String shootoutRecord;

    @Column(columnDefinition = "bpchar", name = "last_10")
    private String last10;

    @Column(columnDefinition = "bpchar")
    private String streak;
}
