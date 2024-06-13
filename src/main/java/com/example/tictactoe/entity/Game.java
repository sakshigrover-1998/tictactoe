package com.example.tictactoe.entity;

import com.example.tictactoe.model.PlayerType;
import com.example.tictactoe.model.Symbol;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a game entity.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Move> moves = new ArrayList<>();

    private boolean isOver;

    private String winner;

    @Enumerated(EnumType.STRING)
    private PlayerType currentPlayer;

    @Enumerated(EnumType.STRING)
    private Symbol humanSymbol;

    @Enumerated(EnumType.STRING)
    private Symbol computerSymbol;

    private String humanName;

    @ElementCollection
    @CollectionTable(name = "board")
    private char[][] board;
}
