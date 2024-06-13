package com.example.tictactoe.util;

import com.example.tictactoe.model.Symbol;
/**
 * Utility class for checking the game board for winning moves and draws.
 */
public class BoardChecker {

    /**
     * Checks if the current move results in a win for the player.
     *
     * @param board  The current game board.
     * @param symbol The symbol of the current player.
     * @param row    The row of the latest move.
     * @param column The column of the latest move.
     * @return True if the move results in a win, false otherwise.
     */

    public static boolean isWinningMove(char[][] board, Symbol symbol, int row, int column) {
        return isRowWin(board, symbol, row) || isColumnWin(board, symbol, column) || isDiagonalWin(board, symbol, row, column);
    }

    /**
     * Checks if the game has ended in a draw.
     *
     * @param board The current game board.
     * @return True if the game is a draw, false otherwise.
     */

    public static boolean isDraw(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == Symbol.EMPTY.getCharValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isRowWin(char[][] board, Symbol symbol, int row) {
        for (int j = 0; j < board[row].length; j++) {
            if (board[row][j] != symbol.getCharValue()) {
                return false;
            }
        }
        return true;
    }

    private static boolean isColumnWin(char[][] board, Symbol symbol, int column) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][column] != symbol.getCharValue()) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDiagonalWin(char[][] board, Symbol symbol, int row, int column) {
        if (row == column) {
            for (int i = 0; i < board.length; i++) {
                if (board[i][i] != symbol.getCharValue()) {
                    return false;
                }
            }
            return true;
        } else if (row + column == board.length - 1) {
            for (int i = 0; i < board.length; i++) {
                if (board[i][board.length - 1 - i] != symbol.getCharValue()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
