package com.example.tictactoe.controller;


import com.example.tictactoe.dao.GameDao;
import com.example.tictactoe.dto.GameDto;
import com.example.tictactoe.dto.GameResultDto;
import com.example.tictactoe.dto.MoveDto;
import com.example.tictactoe.dto.MoveRequest;
import com.example.tictactoe.entity.Game;
import com.example.tictactoe.exception.GameNotFound;
import com.example.tictactoe.service.GameService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class GameControllerTest {

    @Mock
    private GameService gameService;

    @Mock
    private GameDao gameDao;

    @InjectMocks
    private GameController gameController;

    public GameControllerTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testStartGame() {
        // Arrange
        String playerName = "Player1";
        GameDto gameDto = new GameDto();
        when(gameService.startGame(playerName)).thenReturn(gameDto);

        // Act
        ResponseEntity<GameDto> response = gameController.startGame(playerName);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(gameDto, response.getBody());
    }

    @Test
    public void testMakeMove() {
        // Arrange
        MoveDto moveDto = new MoveDto();
        Long gameId = 1L;
        GameDto gameDto = new GameDto();
        when(gameService.makeMove(moveDto, gameId)).thenReturn(gameDto);

        // Act
        ResponseEntity<GameDto> response = gameController.makeMove(new MoveRequest(moveDto, gameId));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(gameDto, response.getBody());
    }

    @Test
    public void testGetGameResult_GameFound() {
        // Arrange
        Long gameId = 1L;
        Game game = new Game();
        game.setWinner("Player1");
        game.setOver(true);
        when(gameDao.findById(gameId)).thenReturn(game);

        // Act
        ResponseEntity<GameResultDto> response = gameController.getGameResult(gameId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        GameResultDto gameResultDto = response.getBody();
        assertNotNull(gameResultDto);
        assertEquals("Player1", gameResultDto.getWinner());
        assertTrue(gameResultDto.isGameOver());
    }

    @Test
    public void testGetGameResult_GameNotFound() {
        // Arrange
        Long gameId = 1L;
        when(gameDao.findById(gameId)).thenReturn(null);

        // Act and Assert
        assertThrows(GameNotFound.class, () -> {
            gameController.getGameResult(gameId);
        }, "Game not found with id: " + gameId);
    }
}
