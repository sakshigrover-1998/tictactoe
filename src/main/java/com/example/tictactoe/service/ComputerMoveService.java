package com.example.tictactoe.service;

import com.example.tictactoe.dto.GameDto;
import com.example.tictactoe.dto.MoveDto;

public interface ComputerMoveService {

    MoveDto makeComputerMove(GameDto game);
}
