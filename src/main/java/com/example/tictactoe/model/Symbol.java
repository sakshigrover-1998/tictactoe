package com.example.tictactoe.model;

import lombok.Getter;

@Getter
public enum Symbol {
    X('X'),
    O('O'),
    EMPTY(' ');

    private final char charValue;

    Symbol(char charValue) {
        this.charValue = charValue;
    }

}

