package com.hockeydb.hockeydb.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hockeydb.hockeydb.model.Game;

public interface GameRepository extends JpaRepository<Game, UUID> {
}