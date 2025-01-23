package com.hockeydb.hockeydb.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hockeydb.hockeydb.model.Season;
import com.hockeydb.hockeydb.model.Team;
import com.hockeydb.hockeydb.repository.SeasonRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class SeasonController {

    @Autowired
    private SeasonRepository seasonRepo;

    @GetMapping("/seasons")
    public ResponseEntity<Set<Season>> getSeasons() {
        try {
            Set<Season> seasons = new HashSet<Season>();

            seasonRepo.findAll().forEach(seasons::add);

            if (seasons.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(seasons, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/seasons/{id}")
    public ResponseEntity<Season> getSeasonById(@PathVariable UUID id) {
        Optional<Season> seasonData = seasonRepo.findById(id);

        if (seasonData.isPresent()) {
            return new ResponseEntity<>(seasonData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/seasons/{id}/teams")
    public ResponseEntity<Set<Team>> getTeamsForSeason(@PathVariable UUID id) {
        try {
            Set<Team> teams = new HashSet<Team>();

            seasonRepo.findById(id).get().getTeams().forEach(teams::add);

            if (teams.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(teams, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
