package com.dynatrace.pong.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dynatrace.pong.dto.GameRequest;
import com.dynatrace.pong.dto.GameResponse;
import com.dynatrace.pong.exception.GameNotFoundException;
import com.dynatrace.pong.exception.PlayerNotFoundException;
import com.dynatrace.pong.model.Game;
import com.dynatrace.pong.model.Player;
import com.dynatrace.pong.repository.GameRepository;
import com.dynatrace.pong.repository.PlayerRepository;

@Service
@Transactional
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public GameService(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    public GameResponse createGame(GameRequest request) {
        Player player1 = playerRepository.findByFirstName(request.getPlayer1Name())
                .orElseThrow(() -> new PlayerNotFoundException("Player not found: " + request.getPlayer1Name()));
        Player player2 = playerRepository.findByFirstName(request.getPlayer2Name())
                .orElseThrow(() -> new PlayerNotFoundException("Player not found: " + request.getPlayer2Name()));

        Game game = new Game(player1, player2);
        game.setScorePlayer1(request.getScorePlayer1());
        game.setScorePlayer2(request.getScorePlayer2());
        
        Game saved = gameRepository.save(game);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public GameResponse getGameById(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));
        return toResponse(game);
    }

    @Transactional(readOnly = true)
    public int getPlayer1Score(Long gameId) {
        gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException(gameId));
        return gameRepository.getPlayer1Score(gameId);
    }

    @Transactional(readOnly = true)
    public int getPlayer2Score(Long gameId) {
        gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException(gameId));
        return gameRepository.getPlayer2Score(gameId);
    }

    public void updatePlayer1Score(Long gameId, int newScore) {
        gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException(gameId));
        gameRepository.updatePlayer1Score(gameId, newScore);
    }

    public void updatePlayer2Score(Long gameId, int newScore) {
        gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException(gameId));
        gameRepository.updatePlayer2Score(gameId, newScore);
    }

    @Transactional(readOnly = true)
    public List<GameResponse> getAllGames() {
        return gameRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public void deleteGame(Long id) {
        if (!gameRepository.existsById(id)) {
            throw new GameNotFoundException(id);
        }
        gameRepository.deleteById(id);
    }

    private GameResponse toResponse(Game game) {
        return new GameResponse(
                game.getId(),
                game.getPlayer1(),
                game.getPlayer2(),
                game.getScorePlayer1(),
                game.getScorePlayer2()
        );
    }
}

