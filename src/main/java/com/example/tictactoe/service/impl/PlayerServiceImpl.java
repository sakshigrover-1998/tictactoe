package com.example.tictactoe.service.impl;

import com.example.tictactoe.dao.PlayerDao;
import com.example.tictactoe.dto.PlayerDto;
import com.example.tictactoe.entity.Player;
import com.example.tictactoe.service.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing players.
 */

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerDao playerDAO;

    @Autowired
    private ModelMapper modelMapper;


    /**
     * Creates a new player with the provided name.
     *
     * @param name The name of the player to create.
     * @return The DTO representing the newly created player.
     */

    @Override
    public PlayerDto createPlayer(String name) {
        Player player = new Player();
        player.setName(name);
        Player savedPlayer = playerDAO.save(player);
        return modelMapper.map(savedPlayer, PlayerDto.class);
    }
}
