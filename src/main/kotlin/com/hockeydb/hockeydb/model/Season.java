package com.hockeydb.hockeydb.model;

import java.util.Set;
import java.util.UUID;
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
    private Set<Team> teams;

    public UUID getID() {
        return seasonId;
    }

    public String getName() {
        return name;
    }

    public Set<Team> getTeams() {
        return teams;
    }
}
