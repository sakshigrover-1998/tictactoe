package com.example.tictactoe.dao;

import com.example.tictactoe.dao.impl.GameDaoImpl;
import com.example.tictactoe.entity.Game;
import com.example.tictactoe.repository.GameRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameDaoImplTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameDaoImpl gameDao;

    public GameDaoImplTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById_GameFound() {
        // Arrange
        Long gameId = 1L;
        Game game = new Game();
        when(gameRepository.findById(gameId)).thenReturn(Optional.of(game));

        // Act
        Game result = gameDao.findById(gameId);

        // Assert
        assertNotNull(result);
        assertEquals(game, result);
    }

    @Test
    public void testFindById_GameNotFound() {
        // Arrange
        Long gameId = 1L;
        when(gameRepository.findById(gameId)).thenReturn(Optional.empty());

        // Act
        Game result = gameDao.findById(gameId);

        // Assert
        assertNull(result);
    }

    @Test
    public void testSave() {
        // Arrange
        Game game = new Game();
        when(gameRepository.save(game)).thenReturn(game);

        // Act
        Game result = gameDao.save(game);

        // Assert
        assertNotNull(result);
        assertEquals(game, result);
    }

    @Test
    public void testDeleteById() {
        // Arrange
        Long gameId = 1L;

        // Act
        gameDao.deleteById(gameId);

        // Assert
        verify(gameRepository, times(1)).deleteById(gameId);
    }
}
