package com.example.tictactoe.exception;

import jakarta.persistence.EntityNotFoundException;

/**
 * Custom exception to represent a game not found error.
 */
public class GameNotFound extends EntityNotFoundException {

    /**
     * Constructs a new GameNotFound exception with the specified detail message.
     *
     * @param message the detail message
     */
    public GameNotFound(String message) {
        super(message);
    }
}
