package com.hockeydb.hockeydb.repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.hockeydb.hockeydb.model.Skater;

// TODO: use a DTO
public interface SkaterGoalsRepository extends PagingAndSortingRepository<Skater, UUID> {
    @Query(value = "SELECT sk.skater_id, sk.name AS skater_name, sk_st.games_played, sk_st.goals AS goals, sk_st.assists, sk_st.points, sk_st.powerplay_goals, sk_st.shorthanded_goals, t.name AS team_name FROM skater sk JOIN skater_seasons AS sk_s ON sk.skater_id = sk_s.skater_id JOIN skater_stats AS sk_st ON sk.skater_id = sk_st.skater_id AND sk_s.season_id = sk_st.season_id JOIN team_skaters AS t_sk ON sk.skater_id = t_sk.skater_id AND sk_s.season_id = t_sk.season_id JOIN team AS t ON t_sk.team_id = t.team_id WHERE sk_s.season_id = ?1", nativeQuery = true)
    List<Map<String, Object>> fetchSkaterStats(UUID seasonId, Pageable pageable);
}
