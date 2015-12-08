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

    @Test
    public void cantPlaceSameSymbolOnConsecutiveTimes() {
        Assert.assertTrue(game.placeSymbol("X", 1, 1));
        Assert.assertFalse(game.placeSymbol("X", 1, 0));
    }

    @Test
    public void xSymbolWinsByFillingTheSecondRow() {
        Assert.assertTrue(game.placeSymbol("X", 1, 1));
        Assert.assertTrue(game.placeSymbol("O", 0, 0));
        Assert.assertTrue(game.placeSymbol("X", 1, 0));
        Assert.assertTrue(game.placeSymbol("O", 0, 1));
        Assert.assertTrue(game.placeSymbol("X", 1, 2));
        Assert.assertTrue(game.isFinished());
        Assert.assertEquals("X", game.getWinner());
    }

    @Test
    public void xSymbolWinsByFillingTheFirstRow() {
        Assert.assertTrue(game.placeSymbol("X", 0, 1));
        Assert.assertTrue(game.placeSymbol("O", 1, 0));
        Assert.assertTrue(game.placeSymbol("X", 0, 0));
        Assert.assertTrue(game.placeSymbol("O", 1, 1));
        Assert.assertTrue(game.placeSymbol("X", 0, 2));
        Assert.assertTrue(game.isFinished());
        Assert.assertEquals("X", game.getWinner());
    }

    @Test
    public void oSymbolWinsByFillingTheSecondRow() {
        Assert.assertTrue(game.placeSymbol("O", 1, 1));
        Assert.assertTrue(game.placeSymbol("X", 0, 0));
        Assert.assertTrue(game.placeSymbol("O", 1, 0));
        Assert.assertTrue(game.placeSymbol("X", 0, 1));
        Assert.assertTrue(game.placeSymbol("O", 1, 2));
        Assert.assertTrue(game.isFinished());
        Assert.assertEquals("O", game.getWinner());
    }

    @Test
    public void xSymbolWinsByFillingTheSecondColumn() {
        Assert.assertTrue(game.placeSymbol("X", 1, 1));
        Assert.assertTrue(game.placeSymbol("O", 0, 0));
        Assert.assertTrue(game.placeSymbol("X", 0, 1));
        Assert.assertTrue(game.placeSymbol("O", 0, 2));
        Assert.assertTrue(game.placeSymbol("X", 2, 1));
        Assert.assertTrue(game.isFinished());
        Assert.assertEquals("X", game.getWinner());
    }

    @Test
    public void xSymbolWinsByFillingLeftToRightDiagonal() {
        Assert.assertTrue(game.placeSymbol("X", 0, 0));
        Assert.assertTrue(game.placeSymbol("O", 0, 1));
        Assert.assertTrue(game.placeSymbol("X", 1, 1));
        Assert.assertTrue(game.placeSymbol("O", 0, 2));
        Assert.assertTrue(game.placeSymbol("X", 2, 2));
        Assert.assertTrue(game.isFinished());
        Assert.assertEquals("X", game.getWinner());
    }

    @Test
    public void xSymbolWinsByFillingRightToLeftDiagonal() {
        Assert.assertTrue(game.placeSymbol("X", 0, 2));
        Assert.assertTrue(game.placeSymbol("O", 0, 1));
        Assert.assertTrue(game.placeSymbol("X", 1, 1));
        Assert.assertTrue(game.placeSymbol("O", 0, 0));
        Assert.assertTrue(game.placeSymbol("X", 2, 0));
        Assert.assertTrue(game.isFinished());
        Assert.assertEquals("X", game.getWinner());
    }

    @Test
    public void drawGame() {
        Assert.assertTrue(game.placeSymbol("X", 0, 1));
        Assert.assertTrue(game.placeSymbol("O", 0, 0));
        Assert.assertTrue(game.placeSymbol("X", 1, 1));
        Assert.assertTrue(game.placeSymbol("O", 2, 1));
        Assert.assertTrue(game.placeSymbol("X", 1, 2));
        Assert.assertTrue(game.placeSymbol("O", 1, 0));
        Assert.assertTrue(game.placeSymbol("X", 2, 2));
        Assert.assertTrue(game.placeSymbol("O", 0, 2));
        Assert.assertTrue(game.placeSymbol("X", 2, 0));
        Assert.assertTrue(game.isFinished());
        Assert.assertNull(game.getWinner());
    }
}