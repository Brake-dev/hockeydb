package com.hockeydb.hockeydb.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "season_id")
    @Getter(AccessLevel.NONE)
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
    private List<Team> teams;

    public Season(String name) {
        this.name = name;
    }

    public UUID getID() {
        return seasonId;
    }
}
