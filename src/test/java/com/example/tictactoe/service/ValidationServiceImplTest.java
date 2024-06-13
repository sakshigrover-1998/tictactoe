package com.example.tictactoe.service;


import com.example.tictactoe.dto.GameDto;
import com.example.tictactoe.dto.MoveDto;
import com.example.tictactoe.model.Symbol;
import com.example.tictactoe.service.impl.ValidationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
@ExtendWith(MockitoExtension.class)
public class ValidationServiceImplTest {

    private ValidationServiceImpl validationService;

    @BeforeEach
    public void setUp() {
        validationService = new ValidationServiceImpl();
    }

    @Test
    public void isValidMove_ValidMove_ShouldReturnTrue() {
        // Arrange
        char[][] board = {
                {Symbol.EMPTY.getCharValue(), Symbol.X.getCharValue(), Symbol.O.getCharValue()},
                {Symbol.X.getCharValue(), Symbol.EMPTY.getCharValue(), Symbol.O.getCharValue()},
                {Symbol.X.getCharValue(), Symbol.O.getCharValue(), Symbol.EMPTY.getCharValue()}
        };
        GameDto game = new GameDto();
        game.setBoard(board);
        MoveDto move = new MoveDto(0, 0);

        // Act
        boolean result = validationService.isValidMove(move, game);

        // Assert
        assertTrue(result);
    }

    @Test
    public void isValidMove_InvalidMove_ShouldReturnFalse() {
        // Arrange
        char[][] board = {
                {Symbol.X.getCharValue(), Symbol.X.getCharValue(), Symbol.O.getCharValue()},
                {Symbol.X.getCharValue(), Symbol.O.getCharValue(), Symbol.O.getCharValue()},
                {Symbol.X.getCharValue(), Symbol.O.getCharValue(), Symbol.EMPTY.getCharValue()}
        };
        GameDto game = new GameDto();
        game.setBoard(board);
        MoveDto move = new MoveDto(1, 1); // Position already occupied

        // Act
        boolean result = validationService.isValidMove(move, game);

        // Assert
        assertFalse(result);
    }

    @Test
    public void isValidMove_OutOfBoundsMove_ShouldReturnFalse() {
        // Arrange
        char[][] board = {
                {Symbol.EMPTY.getCharValue(), Symbol.X.getCharValue(), Symbol.O.getCharValue()},
                {Symbol.X.getCharValue(), Symbol.EMPTY.getCharValue(), Symbol.O.getCharValue()},
                {Symbol.X.getCharValue(), Symbol.O.getCharValue(), Symbol.EMPTY.getCharValue()}
        };
        GameDto game = new GameDto();
        game.setBoard(board);
        MoveDto move = new MoveDto(-1, 0); // Out of bounds

        // Act
        boolean result = validationService.isValidMove(move, game);

        // Assert
        assertFalse(result);
    }

    @Test
    public void isValidMove_OutOfBoundsMove_ShouldReturnFalse_Column() {
        // Arrange
        char[][] board = {
                {Symbol.EMPTY.getCharValue(), Symbol.X.getCharValue(), Symbol.O.getCharValue()},
                {Symbol.X.getCharValue(), Symbol.EMPTY.getCharValue(), Symbol.O.getCharValue()},
                {Symbol.X.getCharValue(), Symbol.O.getCharValue(), Symbol.EMPTY.getCharValue()}
        };
        GameDto game = new GameDto();
        game.setBoard(board);
        MoveDto move = new MoveDto(0, 3); // Out of bounds

        // Act
        boolean result = validationService.isValidMove(move, game);

        // Assert
        assertFalse(result);
    }

    @Test
    public void isValidMove_EmptyMove_ShouldReturnTrue() {
        // Arrange
        char[][] board = {
                {Symbol.EMPTY.getCharValue(), Symbol.X.getCharValue(), Symbol.O.getCharValue()},
                {Symbol.X.getCharValue(), Symbol.EMPTY.getCharValue(), Symbol.O.getCharValue()},
                {Symbol.X.getCharValue(), Symbol.O.getCharValue(), Symbol.EMPTY.getCharValue()}
        };
        GameDto game = new GameDto();
        game.setBoard(board);
        MoveDto move = new MoveDto(2, 2); // Empty spot

        // Act
        boolean result = validationService.isValidMove(move, game);

        // Assert
        assertTrue(result);
    }
}
