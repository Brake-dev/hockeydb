package com.hockeydb.hockeydb.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hockeydb.hockeydb.model.TeamStats;
import com.hockeydb.hockeydb.repository.TeamStatsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TeamStatsController {

    @Autowired
    private TeamStatsRepository teamStatsRepo;

    @GetMapping("/team/stats/{id}")
    public ResponseEntity<TeamStats> getTeamStatsById(@PathVariable UUID id) {
        Optional<TeamStats> teamStatsData = teamStatsRepo.findById(id);

        if (teamStatsData.isPresent()) {
            return new ResponseEntity<>(teamStatsData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
