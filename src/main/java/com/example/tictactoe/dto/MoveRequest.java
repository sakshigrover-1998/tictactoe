package com.example.tictactoe.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing a move request.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MoveRequest {
    private MoveDto moveDto;
    private Long gameId;
}
