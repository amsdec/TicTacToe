package com.alberto.tictactoe;

public class PlayerNotAllowedException extends RuntimeException{
    public PlayerNotAllowedException(final String message) {
        super(message);
    }
}
