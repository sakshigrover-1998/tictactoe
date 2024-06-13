package com.example.tictactoe.util;

import com.example.tictactoe.model.Symbol;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardCheckerTest {

    @Test
    public void testIsWinningMove_RowWin() {
        // Arrange
        char[][] board = {
                {'X', 'X', 'X'},
                {'O', 'O', ' '},
                {' ', ' ', ' '}
        };
        Symbol currentPlayerSymbol = Symbol.X;
        int lastMoveRow = 0;
        int lastMoveColumn = 2;
        boolean isWinningMove = BoardChecker.isWinningMove(board, currentPlayerSymbol, lastMoveRow, lastMoveColumn);
        assertTrue(isWinningMove);
    }

    @Test
    public void testIsWinningMove_ColumnWin() {
        // Arrange
        char[][] board = {
                {'X', 'O', 'X'},
                {'X', 'O', ' '},
                {' ', 'O', ' '}
        };
        Symbol currentPlayerSymbol = Symbol.O;
        int lastMoveRow = 2;
        int lastMoveColumn = 1;
        boolean isWinningMove = BoardChecker.isWinningMove(board, currentPlayerSymbol, lastMoveRow, lastMoveColumn);
        assertTrue(isWinningMove);
    }

    @Test
    public void testIsWinningMove_DiagonalWin() {
        // Arrange
        char[][] board = {
                {'X', 'O', 'X'},
                {'O', 'X', ' '},
                {' ', ' ', 'X'}
        };
        Symbol currentPlayerSymbol = Symbol.X;
        int lastMoveRow = 2;
        int lastMoveColumn = 2;
        boolean isWinningMove = BoardChecker.isWinningMove(board, currentPlayerSymbol, lastMoveRow, lastMoveColumn);
        assertTrue(isWinningMove);
    }

    @Test
    public void testIsDraw_Draw() {
        char[][] board = {
                {'X', 'O', 'X'},
                {'O', 'X', 'O'},
                {'O', 'X', 'X'}
        };
        boolean isDraw = BoardChecker.isDraw(board);
        assertTrue(isDraw);
    }

    @Test
    public void testIsDraw_NotDraw() {
        char[][] board = {
                {'X', 'O', 'X'},
                {'O', 'X', ' '},
                {'O', 'X', ' '}
        };
        boolean isDraw = BoardChecker.isDraw(board);
        assertFalse(isDraw);
    }
}
