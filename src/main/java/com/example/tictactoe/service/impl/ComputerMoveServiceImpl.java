package com.example.tictactoe.service.impl;

import com.example.tictactoe.dto.GameDto;
import com.example.tictactoe.dto.MoveDto;
import com.example.tictactoe.entity.Game;
import com.example.tictactoe.entity.Move;
import com.example.tictactoe.model.Symbol;
import com.example.tictactoe.service.ComputerMoveService;
import com.example.tictactoe.util.Board;
import com.example.tictactoe.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Service implementation for generating computer moves in a Tic Tac Toe game.
 */
@Service
@Slf4j
public class ComputerMoveServiceImpl implements ComputerMoveService {

    /**
     * Generates a computer move based on the current state of the game board.
     *
     * @param game The current state of the game.
     * @return The computer-generated move.
     */
    @Override
    public MoveDto makeComputerMove(GameDto game) {
        char[][] board = game.getBoard();
        Symbol computerSymbol = game.getComputerSymbol();
        int[] bestMove = Board.findBestMove(board, computerSymbol);
        log.debug("best move by computer {}", bestMove);
        Move move = new Move(null, bestMove[0], bestMove[1], new Game());
        MoveDto moveDto = new MoveDto();
        moveDto.setRow(move.getMove_row());
        moveDto.setColumn(move.getColumn());
        return moveDto;
    }
}
