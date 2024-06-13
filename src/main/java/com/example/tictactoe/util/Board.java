package com.example.tictactoe.util;

import com.example.tictactoe.entity.Move;
import com.example.tictactoe.model.Symbol;

import java.util.Random;

/**
 * Utility class for managing the game board.
 */
public class Board {

    /**
     * Initializes an empty game board.
     *
     * @return The initialized game board.
     */
    public static char[][] initializeBoard() {
        char[][] board = new char[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.BOARD_SIZE; j++) {
                board[i][j] = Symbol.EMPTY.getCharValue();
            }
        }
        return board;
    }

    /**
     * Finds the best move for the given symbol on the current game board.
     *
     * @param board  The current game board.
     * @param symbol The symbol for which to find the best move.
     * @return The coordinates [row, column] of the best move.
     */

    public static int[] findBestMove(char[][] board, Symbol symbol) {
        int[] winMove = attemptWin(board, symbol);
        if (winMove != null) {
            return winMove;
        }

        int[] blockMove = blockOpponentWin(board, symbol);
        if (blockMove != null) {
            return blockMove;
        }

        return getRandomMove(board, symbol);
    }

    private static int[] blockOpponentWin(char[][] board, Symbol playerSymbol) {
        Symbol opponentSymbol = (playerSymbol == Symbol.X) ? Symbol.O : Symbol.X;
        for (int row = 0; row < Constants.BOARD_SIZE; row++) {
            for (int col = 0; col < Constants.BOARD_SIZE; col++) {
                if (board[row][col] == Symbol.EMPTY.getCharValue()) {
                    board[row][col] = opponentSymbol.getCharValue();
                    if (checkWinner(board, opponentSymbol)) {
                        board[row][col] = Symbol.EMPTY.getCharValue();
                        return new int[]{row, col};
                    }
                    board[row][col] = Symbol.EMPTY.getCharValue();
                }
            }
        }
        return null;
    }

    private static int[] attemptWin(char[][] board, Symbol symbol) {
        for (int row = 0; row < Constants.BOARD_SIZE; row++) {
            for (int col = 0; col < Constants.BOARD_SIZE; col++) {
                if (board[row][col] == Symbol.EMPTY.getCharValue()) {
                    board[row][col] = symbol.getCharValue();
                    if (checkWinner(board, symbol)) {
                        board[row][col] = Symbol.EMPTY.getCharValue();
                        return new int[]{row, col};
                    }
                    board[row][col] = Symbol.EMPTY.getCharValue();
                }
            }
        }
        return null;
    }

    private static int[] getRandomMove(char[][] board, Symbol symbol) {
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(Constants.BOARD_SIZE);
            col = random.nextInt(Constants.BOARD_SIZE);
        } while (board[row][col] != Symbol.EMPTY.getCharValue());
        return new int[]{row, col};
    }

    private static boolean checkWinner(char[][] board, Symbol symbol) {
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            if ((board[i][0] == symbol.getCharValue() && board[i][1] == symbol.getCharValue() && board[i][2] == symbol.getCharValue()) ||
                    (board[0][i] == symbol.getCharValue() && board[1][i] == symbol.getCharValue() && board[2][i] == symbol.getCharValue())) {
                return true;
            }
        }
        return (board[0][0] == symbol.getCharValue() && board[1][1] == symbol.getCharValue() && board[2][2] == symbol.getCharValue()) ||
                (board[0][2] == symbol.getCharValue() && board[1][1] == symbol.getCharValue() && board[2][0] == symbol.getCharValue());
    }
}