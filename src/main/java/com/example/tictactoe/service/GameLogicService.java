package com.example.tictactoe.service;

import com.example.tictactoe.dto.GameDto;
import com.example.tictactoe.model.Symbol;

public interface GameLogicService {

    public String checkGameStatus(char[][] board, Symbol currentPlayerSymbol,
                                  int lastMoveRow, int lastMoveColumn);
}
