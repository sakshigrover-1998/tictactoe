package com.example.tictactoe.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameNotFoundTest {

    @Test
    public void testConstructor() {
        // Arrange
        String errorMessage = "Game not found.";

        // Act
        GameNotFound gameNotFound = new GameNotFound(errorMessage);

        // Assert
        assertNotNull(gameNotFound);
        assertEquals(errorMessage, gameNotFound.getMessage());
    }

    @Test
    public void testThrownExceptionMessage() {
        // Arrange
        String errorMessage = "Game not found.";

        // Act and Assert
        assertThrows(GameNotFound.class, () -> {
            throw new GameNotFound(errorMessage);
        }, errorMessage);
    }
}
