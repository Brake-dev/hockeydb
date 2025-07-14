package com.hockeydb.hockeydb.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hockeydb.hockeydb.model.Game;
import com.hockeydb.hockeydb.repository.GameRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class GameController {

    @Autowired
    private GameRepository gameRepo;

    @GetMapping("/game/{id}")
    public ResponseEntity<Game> getSkaterById(@PathVariable UUID id) {
        Optional<Game> gameData = gameRepo.findById(id);

        if (gameData.isPresent()) {
            return new ResponseEntity<>(gameData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
