package com.example.tictactoe.service;

import com.example.tictactoe.dto.GameDto;
import com.example.tictactoe.dto.MoveDto;
import com.example.tictactoe.model.Symbol;
import com.example.tictactoe.service.impl.ComputerMoveServiceImpl;
import com.example.tictactoe.util.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ComputerMoveServiceImplTest {

    @Test
    public void testMakeComputerMove() {
        ComputerMoveServiceImpl computerMoveService = new ComputerMoveServiceImpl();
        char[][] board = {
                {'X', 'O', 'X'},
                {'O', ' ', 'O'},
                {'X', ' ', 'X'}
        };
        Symbol computerSymbol = Symbol.O;
        GameDto gameDto = new GameDto();
        gameDto.setBoard(board);
        gameDto.setComputerSymbol(computerSymbol);
        MoveDto moveDto = computerMoveService.makeComputerMove(gameDto);
        assertNotNull(moveDto);
        assertTrue(moveDto.getRow() >= 0 && moveDto.getRow() < Constants.BOARD_SIZE);
        assertTrue(moveDto.getColumn() >= 0 && moveDto.getColumn() < Constants.BOARD_SIZE);
        assertEquals(Symbol.EMPTY.getCharValue(), board[moveDto.getRow()][moveDto.getColumn()]);
    }
}
