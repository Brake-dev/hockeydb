package com.hockeydb.hockeydb.model;

import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Skater {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "skater_id")
    private UUID skaterId;

    @Column(columnDefinition = "bpchar")
    private String name;

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
}