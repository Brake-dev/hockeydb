package com.hockeydb.hockeydb.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hockeydb.hockeydb.model.Skater;
import com.hockeydb.hockeydb.model.SkaterStats;
import com.hockeydb.hockeydb.repository.SkaterRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class SkaterController {

    @Autowired
    private SkaterRepository skaterRepo;

    @GetMapping("/skater/{id}")
    public ResponseEntity<Skater> getSkaterById(@PathVariable UUID id) {
        Optional<Skater> skaterData = skaterRepo.findById(id);

        if (skaterData.isPresent()) {
            return new ResponseEntity<>(skaterData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/skater/{skaterId}/seasons/{seasonId}/stats")
    public ResponseEntity<SkaterStats> getTeamStatsForSeason(@PathVariable UUID seasonId, @PathVariable UUID skaterId) {
        try {
            Optional<SkaterStats> skaterStatsData = skaterRepo.findById(skaterId).get().getSkaterStats(seasonId);

            if (skaterStatsData.isPresent()) {
                return new ResponseEntity<>(skaterStatsData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
