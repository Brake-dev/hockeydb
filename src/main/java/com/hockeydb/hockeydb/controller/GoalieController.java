package com.hockeydb.hockeydb.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hockeydb.hockeydb.model.Goalie;
import com.hockeydb.hockeydb.model.GoalieStats;
import com.hockeydb.hockeydb.repository.GoalieRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class GoalieController {
    @Autowired
    private GoalieRepository goalieRepo;

    @GetMapping("/goalie/{id}")
    public ResponseEntity<Goalie> getGoalieById(@PathVariable UUID id) {
        Optional<Goalie> goalieData = goalieRepo.findById(id);

        if (goalieData.isPresent()) {
            return new ResponseEntity<>(goalieData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/goalie/{goalieId}/seasons/{seasonId}/stats")
    public ResponseEntity<GoalieStats> getTeamStatsForSeason(@PathVariable UUID seasonId, @PathVariable UUID goalieId) {
        try {
            Optional<GoalieStats> goalieStatsData = goalieRepo.findById(goalieId).get().getGoalieStats(seasonId);

            if (goalieStatsData.isPresent()) {
                return new ResponseEntity<>(goalieStatsData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
