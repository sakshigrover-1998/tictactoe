package com.example.tictactoe.dao;


import com.example.tictactoe.entity.Game;

import java.util.Optional;

public interface GameDao {

    /**
     * Retrieves a game by its ID.
     *
     * @param id the ID of the game to retrieve
     * @return an Optional containing the game if found, otherwise empty
     */
    Game findById(Long id);

    /**
     * Saves a game entity.
     *
     * @param game the game entity to save
     * @return the saved game entity
     */
    Game save(Game game);

    /**
     * Deletes a game by its ID.
     *
     * @param id the ID of the game to delete
     */
    void deleteById(Long id);
}
