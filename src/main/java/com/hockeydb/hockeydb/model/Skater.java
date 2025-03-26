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
public class Skater {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "skater_id")
    @Getter(AccessLevel.NONE)
    private UUID skaterId;

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
    private String shoots;

    @Column(columnDefinition = "bpchar")
    private String draft;

    @Column(columnDefinition = "bpchar")
    private String position;

    @OneToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, mappedBy = "skater")
    @Getter(AccessLevel.NONE)
    private List<SkaterStats> skaterStats;

    public UUID getID() {
        return skaterId;
    }

    public Optional<SkaterStats> getSkaterStats(UUID seasonId) {
        return skaterStats.stream().filter(
                stats -> skaterId.equals(stats.getSkater().getID()) && seasonId.equals(stats.getSeason().getID()))
                .findAny();
    }
}