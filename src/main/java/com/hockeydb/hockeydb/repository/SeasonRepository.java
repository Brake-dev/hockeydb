package com.hockeydb.hockeydb.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hockeydb.hockeydb.model.Season;

public interface SeasonRepository extends JpaRepository<Season, UUID> {
    Season findByName(String name);
}
