package com.hockeydb.hockeydb.model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "season_id")
    private UUID seasonId;

    @Column(columnDefinition = "bpchar")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "team_seasons", joinColumns = { @JoinColumn(name = "season_id") }, inverseJoinColumns = {
            @JoinColumn(name = "team_id")
    })
    @JsonIgnore
    private List<Team> teams;

    @OneToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, mappedBy = "season")
    private List<TeamStats> teamStats;

    public UUID getID() {
        return seasonId;
    }

    public String getName() {
        return name;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public Optional<TeamStats> getTeamStats(UUID teamId) {
        return teamStats.stream().filter(
                stats -> teamId.equals(stats.getTeam().getID()) && seasonId.equals(stats.getSeason().getID()))
                .findAny();
    }
}
