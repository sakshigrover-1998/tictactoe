package com.example.tictactoe.dao;

import com.example.tictactoe.entity.Player;

import java.util.Optional;

/**
 * Data Access Object interface for Player entities.
 */
public interface PlayerDao {

    /**
     * Retrieves a Player entity by its unique identifier.
     *
     * @param id The unique identifier of the Player.
     * @return An Optional containing the Player entity if found, or empty if not found.
     */
    Player findById(Long id);

    /**
     * Saves or updates the given Player entity.
     *
     * @param player The Player entity to save or update.
     * @return The saved or updated Player entity.
     */
    Player save(Player player);

    /**
     * Deletes a Player entity by its unique identifier.
     *
     * @param id The unique identifier of the Player to delete.
     */
    void deleteById(Long id);
}