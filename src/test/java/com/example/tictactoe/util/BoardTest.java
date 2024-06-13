package com.example.tictactoe.util;

import com.example.tictactoe.model.Symbol;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    void testInitializeBoard() {
        char[][] board = Board.initializeBoard();
        assertNotNull(board);
        assertEquals(Constants.BOARD_SIZE, board.length);
        assertEquals(Constants.BOARD_SIZE, board[0].length);
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.BOARD_SIZE; j++) {
                assertEquals(Symbol.EMPTY.getCharValue(), board[i][j]);
            }
        }
    }

    @Test
    void testFindBestMove() {
        char[][] board = {
                {'X', 'O', 'X'},
                {'O', 'X', 'O'},
                {' ', 'X', ' '}
        };
        int[] bestMove = Board.findBestMove(board, Symbol.X);
        assertNotNull(bestMove);
        assertEquals(2, bestMove[0]);
        assertEquals(0, bestMove[1]);
    }

    @Test
    public void testFindBestMove_BlockOpponentWin() {
        // Arrange
        char[][] board = {
                {'X', 'O', ' '},
                {'X', 'O', ' '},
                {' ', ' ', ' '}
        };
        Symbol playerSymbol = Symbol.X;

        // Act
        int[] bestMove = Board.findBestMove(board, playerSymbol);

        // Assert
        assertNotNull(bestMove);
        assertEquals(2, bestMove[0]);
        assertEquals(0, bestMove[1]);
    }

    @Test
    public void testFindBestMove_RandomMove() {
        // Arrange
        char[][] board = {
                {'X', 'O', 'X'},
                {'O', ' ', 'O'},
                {'X', ' ', 'X'}
        };
        Symbol playerSymbol = Symbol.O;

        // Act
        int[] bestMove = Board.findBestMove(board, playerSymbol);

        // Assert
        assertNotNull(bestMove);
        assertTrue(bestMove[0] >= 0 && bestMove[0] < Constants.BOARD_SIZE);
        assertTrue(bestMove[1] >= 0 && bestMove[1] < Constants.BOARD_SIZE);
        assertEquals(Symbol.EMPTY.getCharValue(), board[bestMove[0]][bestMove[1]]);
    }
}

