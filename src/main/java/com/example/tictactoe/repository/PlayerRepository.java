package com.example.tictactoe.repository;

import com.example.tictactoe.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for accessing and managing player entities in the database.
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}