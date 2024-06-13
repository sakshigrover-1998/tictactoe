package com.example.tictactoe.service;

import com.example.tictactoe.dto.GameDto;
import com.example.tictactoe.dto.MoveDto;

public interface GameService {

    GameDto startGame(String playerName);
    GameDto makeMove(MoveDto move, Long gameId);
    GameDto getFullGameDetails(Long gameId);

}
