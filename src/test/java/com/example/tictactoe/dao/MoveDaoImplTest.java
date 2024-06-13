package com.example.tictactoe.dao;

import com.example.tictactoe.dao.impl.MoveDaoImpl;
import com.example.tictactoe.entity.Move;
import com.example.tictactoe.repository.MoveRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MoveDaoImplTest {

    @Mock
    private MoveRepository moveRepository;

    @InjectMocks
    private MoveDaoImpl moveDao;

    public MoveDaoImplTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById_MoveFound() {
        // Arrange
        Long moveId = 1L;
        Move move = new Move();
        when(moveRepository.findById(moveId)).thenReturn(Optional.of(move));

        // Act
        Move result = moveDao.findById(moveId);

        // Assert
        assertNotNull(result);
        assertEquals(move, result);
    }

    @Test
    public void testFindById_MoveNotFound() {
        // Arrange
        Long moveId = 1L;
        when(moveRepository.findById(moveId)).thenReturn(Optional.empty());

        // Act
        Move result = moveDao.findById(moveId);

        // Assert
        assertNull(result);
    }

    @Test
    public void testSave() {
        // Arrange
        Move move = new Move();
        when(moveRepository.save(move)).thenReturn(move);

        // Act
        Move result = moveDao.save(move);

        // Assert
        assertNotNull(result);
        assertEquals(move, result);
    }

    @Test
    public void testDeleteById() {
        // Arrange
        Long moveId = 1L;

        // Act
        moveDao.deleteById(moveId);

        // Assert
        verify(moveRepository, times(1)).deleteById(moveId);
    }
}
