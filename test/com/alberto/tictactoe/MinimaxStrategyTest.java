package com.alberto.tictactoe;

import com.alberto.tictactoe.player.MinimaxStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MinimaxStrategyTest {

    private MinimaxStrategy minimaxStrategy;
    private AbstractGrid grid;

    @Before
    public void setup() {
        grid = new AbstractGrid();
        minimaxStrategy = new MinimaxStrategy();
    }

    @Test
    public void getScoreForDraw() {
        grid.setSymbol("O", new Cell(0, 0));
        grid.setSymbol("X", new Cell(0, 1));
        grid.setSymbol("O", new Cell(0, 2));
        grid.setSymbol("O", new Cell(1, 0));
        grid.setSymbol("X", new Cell(1, 1));
        grid.setSymbol("O", new Cell(1, 2));
        grid.setSymbol("X", new Cell(2, 0));
        grid.setSymbol("X", new Cell(2, 2));
        grid.setSymbol("O", new Cell(2, 1));
        Assert.assertEquals(Integer.valueOf(0), minimaxStrategy.getScore(grid));
    }

    @Test
    public void getScoreForWin() {
        grid.setSymbol("O", new Cell(0, 0));
        grid.setSymbol("X", new Cell(0, 1));
        grid.setSymbol("X", new Cell(0, 2));
        grid.setSymbol("O", new Cell(1, 0));
        grid.setSymbol("X", new Cell(1, 1));
        grid.setSymbol("O", new Cell(2, 0));
        Assert.assertEquals(Integer.valueOf(1), minimaxStrategy.getScore(grid));
    }

    @Test
    public void getScoreForLose() {
        grid.setSymbol("X", new Cell(0, 0));
        grid.setSymbol("O", new Cell(0, 1));
        grid.setSymbol("O", new Cell(0, 2));
        grid.setSymbol("X", new Cell(1, 0));
        grid.setSymbol("O", new Cell(1, 1));
        grid.setSymbol("X", new Cell(2, 0));
        Assert.assertEquals(Integer.valueOf(-1), minimaxStrategy.getScore(grid));
    }

    @Test
    public void getEmptyCells() {
        grid.setSymbol("O", new Cell(0, 0));
        grid.setSymbol("X", new Cell(0, 1));
        grid.setSymbol("O", new Cell(0, 2));
        grid.setSymbol("O", new Cell(1, 0));
        grid.setSymbol("X", new Cell(1, 1));
        grid.setSymbol("O", new Cell(1, 2));
        grid.setSymbol("X", new Cell(2, 0));
        grid.setSymbol("X", new Cell(2, 2));
        Assert.assertEquals(1, minimaxStrategy.getEmptyCells(grid).size());
        Assert.assertEquals(new Cell(2, 1), minimaxStrategy.getEmptyCells(grid).get(0));
    }

    @Test
    public void getScoreOfGridState() {
        grid.setSymbol("X", new Cell(0, 0));
        grid.setSymbol("O", new Cell(0, 1));
        grid.setSymbol("O", new Cell(0, 2));
        grid.setSymbol("O", new Cell(1, 0));
        grid.setSymbol("O", new Cell(2, 0));
        grid.setSymbol("X", new Cell(2, 1));
        grid.setSymbol("X", new Cell(2, 2));
        Assert.assertEquals(new Cell(1, 1), minimaxStrategy.getBestCellToPlay("X", grid));
        /*
         X | O | O
        ---+---+---
         O |   |
        ---+---+---
         O | X | X
         */
    }

    @Test
    public void getScoreOfGridState2() {
        grid.setSymbol("X", new Cell(0, 0));
        grid.setSymbol("O", new Cell(0, 2));
        grid.setSymbol("O", new Cell(1, 0));
        grid.setSymbol("O", new Cell(1, 2));
        grid.setSymbol("O", new Cell(2, 0));
        grid.setSymbol("X", new Cell(2, 1));
        grid.setSymbol("X", new Cell(2, 2));
        Assert.assertEquals(new Cell(1, 1), minimaxStrategy.getBestCellToPlay("X", grid));
        /*
         X |   | O
        ---+---+---
         O |   | O
        ---+---+---
         O | X | X
         */
    }

    @Test
    public void getScoreOfGridState3() {
        grid.setSymbol("X", new Cell(0, 0));
        grid.setSymbol("O", new Cell(0, 2));
        grid.setSymbol("O", new Cell(1, 0));
        grid.setSymbol("O", new Cell(2, 0));
        grid.setSymbol("X", new Cell(2, 1));
        grid.setSymbol("X", new Cell(2, 2));
        Assert.assertEquals(new Cell(1, 1), minimaxStrategy.getBestCellToPlay("O", grid));
        /*
         X |   | O
        ---+---+---
         O |   |
        ---+---+---
         O | X | X
         */
    }

    @Test
    public void getScoreOfGridState4() {
        grid.setSymbol("X", new Cell(0, 0));
        grid.setSymbol("O", new Cell(0, 1));
        grid.setSymbol("X", new Cell(0, 2));
        grid.setSymbol("X", new Cell(1, 0));
        grid.setSymbol("O", new Cell(1, 1));
        grid.setSymbol("X", new Cell(1, 2));
        grid.setSymbol("O", new Cell(2, 0));
        Assert.assertEquals(new Cell(2, 1), minimaxStrategy.getBestCellToPlay("O", grid));
        /*
         X | O | X
        ---+---+---
         X | O | X
        ---+---+---
         O |   |
         */
    }

    @Test
    public void getScoreOfGridState5() {
        grid.setSymbol("O", new Cell(0, 0));
        grid.setSymbol("X", new Cell(0, 1));
        grid.setSymbol("X", new Cell(1, 1));
        Assert.assertEquals(new Cell(2, 1), minimaxStrategy.getBestCellToPlay("O", grid));
        /*
         O | X |
        ---+---+---
           | X |
        ---+---+---
           |   |
         */
    }
}
