package com.example.tictactoe.service;

import com.example.tictactoe.dto.GameDto;
import com.example.tictactoe.dto.MoveDto;

public interface ValidationService {

    boolean isValidMove(MoveDto move, GameDto game);

}
