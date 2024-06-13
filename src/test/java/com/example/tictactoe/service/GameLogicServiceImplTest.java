package com.example.tictactoe.service;

import com.example.tictactoe.model.Symbol;
import com.example.tictactoe.service.impl.GameLogicServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
public class GameLogicServiceImplTest {

    @Test
    public void testCheckGameStatus_WinningMove() {
        char[][] board = {
                {'X', 'O', 'O'},
                {'X', 'X', 'O'},
                {'X', 'O', 'X'}
        };
        Symbol currentPlayerSymbol = Symbol.X;
        int lastMoveRow = 0;
        int lastMoveColumn = 0;
        String result = new GameLogicServiceImpl(null).checkGameStatus(board, currentPlayerSymbol, lastMoveRow, lastMoveColumn);
        assertEquals("Player X wins!", result);
    }

    @Test
    public void testCheckGameStatus_Draw() {
        char[][] board = {
                {'X', 'O', 'X'},
                {'X', 'X', 'O'},
                {'O', 'X', 'O'}
        };
        Symbol currentPlayerSymbol = Symbol.X;
        int lastMoveRow = 2;
        int lastMoveColumn = 0;
        String result = new GameLogicServiceImpl(null).checkGameStatus(board, currentPlayerSymbol, lastMoveRow, lastMoveColumn);
        assertEquals("The game is a draw!", result);
    }

    @Test
    public void testCheckGameStatus_OngoingGame() {
        char[][] board = {
                {'X', 'O', 'X'},
                {'O', ' ', ' '},
                {' ', ' ', ' '}
        };
        Symbol currentPlayerSymbol = Symbol.O;
        int lastMoveRow = 1;
        int lastMoveColumn = 0;
        String result = new GameLogicServiceImpl(null).checkGameStatus(board, currentPlayerSymbol, lastMoveRow, lastMoveColumn);
        assertEquals("Game is still ongoing.", result);
    }
}

