package com.example.tictactoe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Data Transfer Object (DTO) representing a game  result.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameResultDto {
    private String winner;
    private boolean gameOver;
}