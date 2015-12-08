package com.alberto.tictactoe;

import org.junit.Assert;
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
        game.placeSymbol("X", 1, 1);
        Assert.assertFalse(game.isFinished());
    }

    @Test
    public void cantPlaceSymbolOnSamePosition() {
        Assert.assertTrue(game.placeSymbol("X", 1, 1));
        Assert.assertFalse(game.placeSymbol("O", 1, 1));

    }
}