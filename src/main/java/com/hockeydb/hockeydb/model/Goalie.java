package com.hockeydb.hockeydb.model;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.AccessLevel;

@Entity
@Getter
public class Goalie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "goalie_id")
    @Getter(AccessLevel.NONE)
    private UUID goalieId;

    @Column(columnDefinition = "bpchar")
    private String name;

    @Column
    private Integer number;

    @Column(columnDefinition = "bpchar")
    private String height;

    @Column(columnDefinition = "bpchar")
    private String weight;

    @Column
    private Date born;

    @Column(columnDefinition = "bpchar")
    private String birthplace;

    @Column(columnDefinition = "bpchar")
    private String catches;

    @Column(columnDefinition = "bpchar")
    private String draft;

    @OneToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, mappedBy = "goalie")
    @Getter(AccessLevel.NONE)
    private List<GoalieStats> goalieStats;

    public UUID getID() {
        return goalieId;
    }

    public Optional<GoalieStats> getGoalieStats(UUID seasonId) {
        return goalieStats.stream().filter(
                stats -> goalieId.equals(stats.getGoalie().getID()) && seasonId.equals(stats.getSeason().getID()))
                .findAny();
    }
}
