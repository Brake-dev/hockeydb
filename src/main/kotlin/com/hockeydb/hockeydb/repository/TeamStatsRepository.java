package com.hockeydb.hockeydb.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hockeydb.hockeydb.model.TeamStats;

public interface TeamStatsRepository extends JpaRepository<TeamStats, UUID> {
}