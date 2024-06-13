package com.example.tictactoe;

import com.example.tictactoe.dto.GameDto;
import com.example.tictactoe.dto.MoveDto;
import com.example.tictactoe.entity.Game;
import com.example.tictactoe.entity.Move;
import com.example.tictactoe.model.PlayerType;
import com.example.tictactoe.model.Symbol;
import com.example.tictactoe.util.Board;

import java.util.ArrayList;
import java.util.List;

public class TestDataGenerator {

    public static Game createGame(){
        Game game = new Game();
        game.setId(1L);
        game.setOver(Boolean.FALSE);
        game.setWinner(null);
        game.setCurrentPlayer(PlayerType.HUMAN);
        game.setHumanSymbol(Symbol.X);
        game.setComputerSymbol(Symbol.O);
        game.setHumanName("Sakshi");
        game.setBoard(Board.initializeBoard());

        return game;
    }

    public static GameDto createGameDto() {

        GameDto game = new GameDto();
        game.setId(1L);
        game.setOver(Boolean.FALSE);
        game.setWinner(null);
        game.setCurrentPlayer(PlayerType.HUMAN);
        game.setHumanSymbol(Symbol.X);
        game.setComputerSymbol(Symbol.O);
        game.setHumanName("Sakshi");
        game.setBoard(Board.initializeBoard());

        return game;

    }

    public static MoveDto createMoveDto() {

        MoveDto moveDto = new MoveDto();
        moveDto.setRow(1);
        moveDto.setColumn(1);

        return moveDto;

    }
}
