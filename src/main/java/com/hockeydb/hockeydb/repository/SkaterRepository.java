package com.hockeydb.hockeydb.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hockeydb.hockeydb.model.Skater;

public interface SkaterRepository extends JpaRepository<Skater, UUID> {
}
