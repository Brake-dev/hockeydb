package com.hockeydb.hockeydb.model;

import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Goalie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "goalie_id")
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
}
