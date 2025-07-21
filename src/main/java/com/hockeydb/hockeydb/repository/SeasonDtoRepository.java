package com.hockeydb.hockeydb.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hockeydb.hockeydb.dto.SeasonDto;
import com.hockeydb.hockeydb.model.Season;

public interface SeasonDtoRepository extends JpaRepository<Season, UUID> {
    @Query(value = "SELECT new com.hockeydb.hockeydb.dto.SeasonDto(s.seasonId, s.name) FROM Season s")
    List<SeasonDto> fetchSeasons();

    @Query(value = "SELECT new com.hockeydb.hockeydb.dto.SeasonDto(s.seasonId, s.name) FROM Season s WHERE s.seasonId = :id")
    Optional<SeasonDto> fetchSeasonById(UUID id);
}
