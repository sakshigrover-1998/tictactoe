package com.example.tictactoe.dao.impl;

import com.example.tictactoe.dao.PlayerDao;
import com.example.tictactoe.entity.Player;
import com.example.tictactoe.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PlayerDaoImpl implements PlayerDao {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Player findById(Long id) {
        return playerRepository.findById(id).orElse(null);
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public void deleteById(Long id) {
        playerRepository.deleteById(id);
    }
}
