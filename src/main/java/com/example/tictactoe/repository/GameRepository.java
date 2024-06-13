package com.example.tictactoe.repository;

import com.example.tictactoe.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing and managing Game entities in the database.
 */

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}