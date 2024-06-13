package com.example.tictactoe.dto;

import com.example.tictactoe.model.PlayerType;
import com.example.tictactoe.model.Symbol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Data Transfer Object (DTO) representing a game.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameDto {
    private Long id;
    private char[][] board;
    private boolean isOver;
    private String winner;
    private PlayerType currentPlayer;
    private Symbol humanSymbol;
    private Symbol computerSymbol;
    private String humanName;
}
