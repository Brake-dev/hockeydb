package com.hockeydb.hockeydb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hockeydb.hockeydb.data.SeasonDto;
import com.hockeydb.hockeydb.data.SeasonReq;
import com.hockeydb.hockeydb.model.Season;
import com.hockeydb.hockeydb.model.Team;
import com.hockeydb.hockeydb.repository.SeasonRepository;
import com.hockeydb.hockeydb.repository.SeasonDtoRepository;
import com.hockeydb.hockeydb.repository.SkaterGoalsRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(value = "/api", produces = "application/hockeydb.v1+json")
public class SeasonController {

    @Autowired
    private SeasonDtoRepository seasonDtoRepo;

    @Autowired
    private SeasonRepository seasonRepo;

    @Autowired
    private SkaterGoalsRepository skaterGoalsRepo;

    @GetMapping("/seasons")
    public ResponseEntity<List<SeasonDto>> getSeasons() {
        try {
            List<SeasonDto> seasons = new ArrayList<SeasonDto>();

            seasonDtoRepo.fetchSeasons().forEach(seasons::add);

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
    public ResponseEntity<SeasonDto> getSeasonById(@PathVariable UUID id) {
        Optional<SeasonDto> seasonData = seasonDtoRepo.fetchSeasonById(id);

        if (seasonData.isPresent()) {
            return new ResponseEntity<>(seasonData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/seasons/{id}/teams")
    public ResponseEntity<List<Team>> getTeamsForSeason(@PathVariable UUID id) {
        try {
            List<Team> teams = new ArrayList<Team>();

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

    @GetMapping("/seasons/{id}/skaters/goals")
    public ResponseEntity<List<Map<String, Object>>> getSkaterGoalsForSeason(@PathVariable UUID id,
            @RequestParam Integer page, @RequestParam Integer items, @RequestParam String sort,
            @RequestParam String sortBy) {
        try {
            List<Map<String, Object>> skatersWithStats = new ArrayList<Map<String, Object>>();

            Sort sortVal = Sort.by(sort.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
            Pageable pageCount = PageRequest.of(page, items, sortVal);

            skaterGoalsRepo.fetchSkaterStats(id, pageCount).forEach(skatersWithStats::add);

            if (skatersWithStats.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(skatersWithStats, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/seasons/new")
    // TODO: Error handling into separate functions, put back ResponseEntity type
    public ResponseEntity<?> createNewSeason(@RequestBody SeasonReq req) {
        try {
            Season existingSeason = seasonRepo.findByName(req.getName());

            if (existingSeason == null) {
                Season newSeason = new Season(req.getName());
                Season savedSeason = seasonRepo.save(newSeason);
                return new ResponseEntity<>(savedSeason, HttpStatus.CREATED);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Season with given name already exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/seasons/{id}")
    public ResponseEntity<HttpStatus> deleteSeason(@PathVariable UUID id) {
        try {
            Optional<Season> existingSeason = seasonRepo.findById(id);

            if (existingSeason.isPresent()) {
                seasonRepo.delete(existingSeason.get());
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
