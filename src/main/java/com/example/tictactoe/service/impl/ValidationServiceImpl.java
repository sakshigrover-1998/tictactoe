package com.example.tictactoe.service.impl;

import com.example.tictactoe.dto.GameDto;
import com.example.tictactoe.dto.MoveDto;
import com.example.tictactoe.model.Symbol;
import com.example.tictactoe.service.ValidationService;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {

    /**
     * Validates if the specified move is valid for the given game.
     *
     * @param move The move to validate.
     * @param game The game in which the move is being made.
     * @return {@code true} if the move is valid, {@code false} otherwise.
     */

    @Override
    public boolean isValidMove(MoveDto move, GameDto game) {
        char[][] board = game.getBoard();
        int row = move.getRow();
        int column = move.getColumn();

        if (row < 0 || row >= board.length || column < 0 || column >= board[0].length) {
            return false;
        }
        return board[row][column] == Symbol.EMPTY.getCharValue();
    }
}
