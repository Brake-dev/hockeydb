package com.hockeydb.hockeydb.controller;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hockeydb.hockeydb.model.Team;
import com.hockeydb.hockeydb.model.TeamGames;
import com.hockeydb.hockeydb.model.TeamGoalies;
import com.hockeydb.hockeydb.model.TeamSkaters;
import com.hockeydb.hockeydb.model.TeamStats;
import com.hockeydb.hockeydb.repository.TeamRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TeamController {

    @Autowired
    private TeamRepository teamRepo;

    @GetMapping("/teams")
    public ResponseEntity<List<Team>> getTeams(@RequestParam(required = false) String conference,
            @RequestParam(required = false) String division) {
        try {
            List<Team> teams = new ArrayList<Team>();

            teamRepo.findAll().forEach(teams::add);

            if (conference != null) {
                teams.removeIf(t -> !t.getConference().equals(conference));
            }

            if (division != null) {
                teams.removeIf(t -> !t.getDivision().equals(division));
            }

            if (teams.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(teams, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/teams/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable() UUID id) {
        Optional<Team> teamData = teamRepo.findById(id);

        if (teamData.isPresent()) {
            return new ResponseEntity<>(teamData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/teams/{teamId}/seasons/{seasonId}/stats")
    public ResponseEntity<TeamStats> getTeamStatsForSeason(@PathVariable UUID seasonId, @PathVariable UUID teamId) {
        try {
            Optional<TeamStats> teamStatsData = teamRepo.findById(teamId).get().getTeamStats(seasonId);

            if (teamStatsData.isPresent()) {
                return new ResponseEntity<>(teamStatsData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/teams/{teamId}/seasons/{seasonId}/home")
    public ResponseEntity<List<TeamGames>> getTeamHomeGamesForSeason(@PathVariable UUID seasonId,
            @PathVariable UUID teamId) {

        try {
            List<TeamGames> teamHomeGamesData = teamRepo.findById(teamId).get().getHomeGames(seasonId);

            if (!teamHomeGamesData.isEmpty()) {
                return new ResponseEntity<>(teamHomeGamesData, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/teams/{teamId}/seasons/{seasonId}/away")
    public ResponseEntity<List<TeamGames>> getTeamAwayGamesForSeason(@PathVariable UUID seasonId,
            @PathVariable UUID teamId) {

        try {
            List<TeamGames> teamAwayGamesData = teamRepo.findById(teamId).get().getAwayGames(seasonId);

            if (!teamAwayGamesData.isEmpty()) {
                return new ResponseEntity<>(teamAwayGamesData, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/teams/{teamId}/seasons/{seasonId}/skaters")
    public ResponseEntity<List<TeamSkaters>> getTeamSkatersForSeason(@PathVariable UUID seasonId,
            @PathVariable UUID teamId) {

        try {
            List<TeamSkaters> teamSkatersData = teamRepo.findById(teamId).get().getSkaters(seasonId);

            if (!teamSkatersData.isEmpty()) {
                return new ResponseEntity<>(teamSkatersData, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/teams/{teamId}/seasons/{seasonId}/goalies")
    public ResponseEntity<List<TeamGoalies>> getTeamGoaliesForSeason(@PathVariable UUID seasonId,
            @PathVariable UUID teamId) {

        try {
            List<TeamGoalies> teamGoaliesData = teamRepo.findById(teamId).get().getGoalies(seasonId);

            if (!teamGoaliesData.isEmpty()) {
                return new ResponseEntity<>(teamGoaliesData, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
