package com.alberto.tictactoe;

import org.junit.Before;
import org.junit.Test;

public class TicTacToeGameTest {

    private TicTacToeGame game;

    @Before
    public void setup() {
        game = new TicTacToeGame();
    }

    @Test
    public void placeSymbol() {
        game.placeSymbol("X");
    }
}