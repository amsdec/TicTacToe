package com.alberto.tictactoe;


import org.junit.Assert;
import org.junit.Test;
public class PlayerFactoryTest {
@Test
    public void createXPlayer() {
    Assert.assertTrue(PlayerFactory.getPlayer("X") instanceof XPlayer);
}

    @Test
    public void getSameInstanceOfXPlayer() {
        Player player = PlayerFactory.getPlayer("X");
        Assert.assertEquals(player, PlayerFactory.getPlayer("X"));
    }
    @Test
    public void createOPlayer() {
        Assert.assertTrue(PlayerFactory.getPlayer("O") instanceof OPLayer);
    }

    @Test (expected = PlayerNotAllowedException.class)
    public void createInvalidPlayer() {
        Assert.assertTrue(PlayerFactory.getPlayer("Z") instanceof OPLayer);
    }
    @Test
    public void getSameInstanceOfOPlayer() {
        Player player = PlayerFactory.getPlayer("O");
        Assert.assertEquals(player, PlayerFactory.getPlayer("O"));
    }
}
