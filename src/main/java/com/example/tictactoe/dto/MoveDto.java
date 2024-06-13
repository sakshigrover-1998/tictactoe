package com.example.tictactoe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing a move.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MoveDto {
    private int row;
    private int column;
}
