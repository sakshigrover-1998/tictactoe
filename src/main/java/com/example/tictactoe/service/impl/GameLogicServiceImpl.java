package com.example.tictactoe.service.impl;

import com.example.tictactoe.dto.GameDto;
import com.example.tictactoe.model.Symbol;
import com.example.tictactoe.service.*;
import com.example.tictactoe.util.BoardChecker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service implementation for checking the status of a Tic Tac Toe game.
 */
@Service
public class GameLogicServiceImpl implements GameLogicService {

    private final GameService gameService;

    /**
     * Constructs a new instance of GameLogicServiceImpl.
     *
     * @param gameService The GameService to fetch additional game data.
     */
    @Autowired
    public GameLogicServiceImpl(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Checks the status of the Tic Tac Toe game based on the current board state and the last move made.
     *
     * @param board              The current state of the game board.
     * @param currentPlayerSymbol The symbol of the player who made the last move.
     * @param lastMoveRow        The row of the last move made.
     * @param lastMoveColumn     The column of the last move made.
     * @return A string indicating the status of the game.
     */
    @Override
    public String checkGameStatus(char[][] board, Symbol currentPlayerSymbol, int lastMoveRow, int lastMoveColumn) {
        if (BoardChecker.isWinningMove(board, currentPlayerSymbol, lastMoveRow, lastMoveColumn)) {
            return "Player " + currentPlayerSymbol + " wins!";
        } else if (BoardChecker.isDraw(board)) {
            return "The game is a draw!";
        } else {
            return "Game is still ongoing.";
        }
    }
}

