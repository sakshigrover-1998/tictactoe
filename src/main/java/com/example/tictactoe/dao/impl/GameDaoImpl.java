package com.example.tictactoe.dao.impl;

import com.example.tictactoe.dao.GameDao;
import com.example.tictactoe.entity.Game;
import com.example.tictactoe.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GameDaoImpl implements GameDao {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game findById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    @Override
    public Game save(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public void deleteById(Long id) {
        gameRepository.deleteById(id);
    }
}
