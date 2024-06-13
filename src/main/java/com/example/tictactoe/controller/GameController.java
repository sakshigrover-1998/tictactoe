package com.example.tictactoe.controller;


import com.example.tictactoe.dao.GameDao;
import com.example.tictactoe.dto.GameDto;
import com.example.tictactoe.dto.GameResultDto;
import com.example.tictactoe.dto.MoveDto;
import com.example.tictactoe.dto.MoveRequest;
import com.example.tictactoe.entity.Game;
import com.example.tictactoe.exception.GameNotFound;
import com.example.tictactoe.service.GameService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller class to handle Tic Tac Toe game operations.
 */
@RestController
@Slf4j
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameDao gameDao;

    /**
     * Endpoint to start a new game.
     *
     * @param playerName Name of the player starting the game
     * @return ResponseEntity with GameDto containing the game details
     */
    @PostMapping("v1/startGame")
    public ResponseEntity<GameDto> startGame(@RequestBody String playerName) {
        log.info("Starting game");
        return ResponseEntity.ok(gameService.startGame(playerName));
    }

    /**
     * Endpoint to make a move in the game.
     *
     * @param moveRequest MoveRequest object containing move details and game ID
     * @return ResponseEntity with GameDto containing the updated game details
     */
    @PostMapping("v1/makeMove")
    public ResponseEntity<GameDto> makeMove(@RequestBody MoveRequest moveRequest) {
        MoveDto moveDto = moveRequest.getMoveDto();
        Long gameId = moveRequest.getGameId();
        log.info("making move for game {}", gameId);
        return ResponseEntity.ok(gameService.makeMove(moveDto, gameId));
    }

    /**
     * Endpoint to get the result of a game.
     *
     * @param gameId ID of the game
     * @return ResponseEntity with GameResultDto containing the game result
     * @throws GameNotFound if the game with the specified ID is not found
     */
    @GetMapping("v1/gameResult/{gameId}")
    public ResponseEntity<GameResultDto> getGameResult(@PathVariable Long gameId) {
        Game game = gameDao.findById(gameId);
        if (game == null) {
            throw new GameNotFound("Game not found with id: " + gameId);
        }
        GameResultDto gameResultDto = new GameResultDto();
        gameResultDto.setWinner(game.getWinner());
        gameResultDto.setGameOver(game.isOver());
        return ResponseEntity.ok(gameResultDto);
    }

    /**
     * Endpoint to get the result of a game.
     *
     * @param gameId ID of the game
     * @return ResponseEntity with GameResultDto containing the game result
     * @throws GameNotFound if the game with the specified ID is not found
     */
    @GetMapping("v1/board/{gameId}")
    public ResponseEntity<char[][]> displayBoard(@PathVariable Long gameId) {
        try {
            GameDto gameDto = gameService.getFullGameDetails(gameId);
            char[][] board = gameDto.getBoard();
            return ResponseEntity.ok(board);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
