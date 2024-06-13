package com.example.tictactoe.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InvalidMoveExceptionTest {

    @Test
    public void testConstructor() {
        // Arrange
        String errorMessage = "Invalid move.";

        // Act
        InvalidMoveException exception = new InvalidMoveException(errorMessage);

        // Assert
        assertNotNull(exception);
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    public void testThrownExceptionMessage() {
        // Arrange
        String errorMessage = "Invalid move.";

        // Act and Assert
        assertThrows(InvalidMoveException.class, () -> {
            throw new InvalidMoveException(errorMessage);
        }, errorMessage);
    }
}
