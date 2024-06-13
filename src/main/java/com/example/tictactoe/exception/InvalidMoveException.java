package com.example.tictactoe.exception;

/**
 * Custom exception to invalid move exception.
 */
public class InvalidMoveException extends RuntimeException {
    /**
     * Constructs a new InvalidMoveException exception with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidMoveException(String message) {
        super(message);
    }
}
