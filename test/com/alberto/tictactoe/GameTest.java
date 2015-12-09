package com.alberto.tictactoe;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameTest {
    private Game game;
    @Before
    public void setup(){
        game = new Game(new TicTacToeGame());
    }

    @Test
    public void addPLayer() {
        game.addPlayer(PlayerFactory.getPlayer("X"));
    }

    @Test (expected = PlayerNotAllowedException.class)
    public void addTwoXPlayers() {
        game.addPlayer(PlayerFactory.getPlayer("X"));
        game.addPlayer(PlayerFactory.getPlayer("X"));
    }

    @Test
    public void addTwoPlayersWithDifferentSymbol() {
        game.addPlayer(PlayerFactory.getPlayer("X"));
        game.addPlayer(PlayerFactory.getPlayer("O"));
    }

    @Test (expected = PlayerNotAllowedException.class)
    public void addTwoOPlayers() {
        game.addPlayer(PlayerFactory.getPlayer("O"));
        game.addPlayer(PlayerFactory.getPlayer("O"));
    }

    @Test
    public void allowPlay() {
        Player xPlayer = PlayerFactory.getPlayer("X");
        Player oPlayer = PlayerFactory.getPlayer("O");
        game.addPlayer(xPlayer);
        game.addPlayer(oPlayer);
        Assert.assertTrue(xPlayer.playOn(0, 0));

    }

    @Test
    public void completeGame() {
        Player xPlayer = PlayerFactory.getPlayer("X");
        Player oPlayer = PlayerFactory.getPlayer("O");
        game.addPlayer(xPlayer);
        game.addPlayer(oPlayer);
        Assert.assertTrue(xPlayer.playOn(1, 1));
        Assert.assertFalse(game.isFinished());
        Assert.assertNull(game.getWinner());
        Assert.assertTrue(oPlayer.playOn(1, 0));
        Assert.assertFalse(game.isFinished());
        Assert.assertNull(game.getWinner());
        Assert.assertTrue(xPlayer.playOn(0, 1));
        Assert.assertFalse(game.isFinished());
        Assert.assertNull(game.getWinner());
        Assert.assertTrue(oPlayer.playOn(2, 1));
        Assert.assertFalse(game.isFinished());
        Assert.assertNull(game.getWinner());
        Assert.assertTrue(xPlayer.playOn(0, 2));
        Assert.assertFalse(game.isFinished());
        Assert.assertNull(game.getWinner());
        Assert.assertTrue(oPlayer.playOn(2, 0));
        Assert.assertFalse(game.isFinished());
        Assert.assertNull(game.getWinner());
        Assert.assertTrue(xPlayer.playOn(0, 0));
        Assert.assertTrue(game.isFinished());
        Assert.assertEquals(xPlayer, game.getWinner());






    }
}
