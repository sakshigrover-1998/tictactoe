package com.example.tictactoe.exception;

public class GameEndedException extends RuntimeException{

    /**
     * Constructs a new GameEnded exception with the specified detail message.
     *
     * @param message the detail message
     */
    public GameEndedException(String message) {
        super(message);
    }

}
