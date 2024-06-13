package com.example.tictactoe.dao;

import com.example.tictactoe.dao.impl.PlayerDaoImpl;
import com.example.tictactoe.entity.Player;
import com.example.tictactoe.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlayerDaoImplTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerDaoImpl playerDao;

    public PlayerDaoImplTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById_PlayerFound() {
        // Arrange
        Long playerId = 1L;
        Player player = new Player();
        when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));

        // Act
        Player result = playerDao.findById(playerId);

        // Assert
        assertNotNull(result);
        assertEquals(player, result);
    }

    @Test
    public void testFindById_PlayerNotFound() {
        // Arrange
        Long playerId = 1L;
        when(playerRepository.findById(playerId)).thenReturn(Optional.empty());

        // Act
        Player result = playerDao.findById(playerId);

        // Assert
        assertNull(result);
    }

    @Test
    public void testSave() {
        // Arrange
        Player player = new Player();
        when(playerRepository.save(player)).thenReturn(player);

        // Act
        Player result = playerDao.save(player);

        // Assert
        assertNotNull(result);
        assertEquals(player, result);
    }

    @Test
    public void testDeleteById() {
        // Arrange
        Long playerId = 1L;

        // Act
        playerDao.deleteById(playerId);

        // Assert
        verify(playerRepository, times(1)).deleteById(playerId);
    }
}
