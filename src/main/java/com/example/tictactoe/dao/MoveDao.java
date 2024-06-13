package com.example.tictactoe.dao;

import com.example.tictactoe.entity.Move;

import java.util.Optional;

/**
 * Data Access Object interface for Move entities.
 */
public interface MoveDao {

    /**
     * Retrieves a Move entity by its unique identifier.
     *
     * @param id The unique identifier of the Move.
     * @return An Optional containing the Move entity if found, or empty if not found.
     */
    Move findById(Long id);

    /**
     * Saves or updates the given Move entity.
     *
     * @param move The Move entity to save or update.
     * @return The saved or updated Move entity.
     */
    Move save(Move move);

    /**
     * Deletes a Move entity by its unique identifier.
     *
     * @param id The unique identifier of the Move to delete.
     */
    void deleteById(Long id);
}
