package com.hockeydb.hockeydb.model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "team_id")
    private UUID teamId;

    @Column
    private String name;

    @Column
    private String division;

    @Column(columnDefinition = "bpchar")
    private String conference;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, mappedBy = "teams")
    private List<Season> seasons;

    @OneToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, mappedBy = "team")
    private List<TeamStats> teamStats;

    public UUID getID() {
        return teamId;
    }

    public String getName() {
        return name;
    }

    public String getDivision() {
        return division;
    }

    public String getConference() {
        return conference;
    }

    public Optional<TeamStats> getTeamStats(UUID seasonId) {
        return teamStats.stream().filter(
                stats -> teamId.equals(stats.getTeam().getID()) && seasonId.equals(stats.getSeason().getID()))
                .findAny();
    }
}
