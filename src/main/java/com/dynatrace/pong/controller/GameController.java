package com.dynatrace.pong.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dynatrace.pong.dto.GameRequest;
import com.dynatrace.pong.dto.GameResponse;
import com.dynatrace.pong.dto.ScoreUpdateRequest;
import com.dynatrace.pong.service.GameService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/Games")
public class GameController {

    private final GameService GameService;

    public GameController(GameService GameService) {
        this.GameService = GameService;
    }

    @PostMapping
    public ResponseEntity<GameResponse> createGame(@Valid @RequestBody GameRequest request) {
        GameResponse response = GameService.createGame(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponse> getGameById(@PathVariable Long id) {
        return ResponseEntity.ok(GameService.getGameById(id));
    }

    @GetMapping
    public ResponseEntity<List<GameResponse>> getAllGames() {
        return ResponseEntity.ok(GameService.getAllGames());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        GameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameResponse> updateGameScore(@PathVariable Long id, @Valid @RequestBody ScoreUpdateRequest request) {
        GameService.updatePlayer1Score(id, request.getScorePlayer1());
        GameService.updatePlayer2Score(id, request.getScorePlayer2());
        GameResponse response = GameService.getGameById(id);
        return ResponseEntity.ok(response);
    }
}

