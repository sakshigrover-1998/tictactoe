package com.example.tictactoe.repository;

import com.example.tictactoe.entity.Move;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing and managing move entities in the database.
 */
@Repository
public interface MoveRepository extends JpaRepository<Move, Long> {
}
