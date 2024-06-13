package com.example.tictactoe.service;

import com.example.tictactoe.dao.GameDao;
import com.example.tictactoe.dto.GameDto;
import com.example.tictactoe.dto.MoveDto;
import com.example.tictactoe.entity.Game;
import com.example.tictactoe.exception.GameNotFound;
import com.example.tictactoe.exception.InvalidMoveException;
import com.example.tictactoe.model.PlayerType;
import com.example.tictactoe.model.Symbol;
import com.example.tictactoe.service.impl.GameServiceImpl;
import com.example.tictactoe.util.Board;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static com.example.tictactoe.TestDataGenerator.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameServiceImplTest {


    private GameServiceImpl gameService;

    @Mock
    private GameDao gameDao;

    @Mock
    private Game game;

    private GameDto gameDto;

    private MoveDto moveDto;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ValidationService validationService;

    @Mock
    private ComputerMoveService computerMoveService;

    @BeforeEach
    public void setup() {
        gameService = new GameServiceImpl(gameDao, validationService, computerMoveService, modelMapper);
        game = createGame();

    }

    @Test
    public void testStartGame_HumanStarts() {
        when(gameDao.save(any(Game.class))).thenReturn(game);
        when(modelMapper.map(any(Game.class), eq(GameDto.class))).thenReturn(createGameDto());

        GameDto result = gameService.startGame("Sakshi");

        assertNotNull(result);
        assertEquals(PlayerType.HUMAN, result.getCurrentPlayer());
        verify(gameDao, times(1)).save(any(Game.class));
    }

    @Test
    public void testMakeMove_InvalidMove() {
        long gameId = 1L;
        MoveDto move = new MoveDto(0, 0);
        Game gameInProgress = game;

        when(gameDao.findById(gameId)).thenReturn(gameInProgress);
        when(validationService.isValidMove(move, modelMapper.map(gameInProgress, GameDto.class))).thenReturn(false);

        InvalidMoveException exception = assertThrows(InvalidMoveException.class,
                () -> gameService.makeMove(move, gameId));
        assertEquals("Invalid move: " + move, exception.getMessage());

        verify(gameDao, times(1)).findById(gameId);
        verify(validationService, times(1)).isValidMove(move, modelMapper.map(gameInProgress, GameDto.class));
        verify(gameDao, never()).save(any(Game.class));
        verify(computerMoveService, never()).makeComputerMove(any());
    }






    @Test
    public void testGetFullGameDetails_GameNotFound() {
        when(gameDao.findById(1L)).thenReturn(null);
        assertThrows(EntityNotFoundException.class, () -> gameService.getFullGameDetails(1L));
    }

    @Test
    public void testGetFullGameDetails_GameFound() {
        Game game = new Game();
        game.setId(1L);
        when(gameDao.findById(1L)).thenReturn(game);
        when(modelMapper.map(any(Game.class), eq(GameDto.class))).thenReturn(new GameDto());
        GameDto gameDto = gameService.getFullGameDetails(1L);
        assertNotNull(gameDto);
    }

    @Test
    public void testMakeMove_GameNotFound() {
        when(gameDao.findById(1L)).thenReturn(null);
        MoveDto moveDto = new MoveDto();
        moveDto.setRow(0);
        moveDto.setColumn(0);
        assertThrows(GameNotFound.class, () -> gameService.makeMove(moveDto, 1L));
    }
}
