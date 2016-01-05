package com.alberto.tictactoe;

import com.alberto.tictactoe.player.MinimaxStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MinimaxStrategyTest {

    private final MinimaxStrategy minimaxStrategy = new MinimaxStrategy();

    @Before
    public void setup() {
        this.minimaxStrategy.grid = new AbstractGrid();
    }

    @Test
    public void getScoreForDraw() {
        minimaxStrategy.grid.setSymbol("O", new Cell(0, 0));
        minimaxStrategy.grid.setSymbol("X", new Cell(0, 1));
        minimaxStrategy.grid.setSymbol("O", new Cell(0, 2));
        minimaxStrategy.grid.setSymbol("O", new Cell(1, 0));
        minimaxStrategy.grid.setSymbol("X", new Cell(1, 1));
        minimaxStrategy.grid.setSymbol("O", new Cell(1, 2));
        minimaxStrategy.grid.setSymbol("X", new Cell(2, 0));
        minimaxStrategy.grid.setSymbol("X", new Cell(2, 2));
        minimaxStrategy.grid.setSymbol("O", new Cell(2, 1));
        Assert.assertEquals(Integer.valueOf(0), minimaxStrategy.getScore(minimaxStrategy.grid));
    }

    @Test
    public void getScoreForWin() {
        minimaxStrategy.grid.setSymbol("O", new Cell(0, 0));
        minimaxStrategy.grid.setSymbol("X", new Cell(0, 1));
        minimaxStrategy.grid.setSymbol("X", new Cell(0, 2));
        minimaxStrategy.grid.setSymbol("O", new Cell(1, 0));
        minimaxStrategy.grid.setSymbol("X", new Cell(1, 1));
        minimaxStrategy.grid.setSymbol("O", new Cell(2, 0));
        Assert.assertEquals(Integer.valueOf(1), minimaxStrategy.getScore(minimaxStrategy.grid));
    }

    @Test
    public void getScoreForLose() {
        minimaxStrategy.grid.setSymbol("X", new Cell(0, 0));
        minimaxStrategy.grid.setSymbol("O", new Cell(0, 1));
        minimaxStrategy.grid.setSymbol("O", new Cell(0, 2));
        minimaxStrategy.grid.setSymbol("X", new Cell(1, 0));
        minimaxStrategy.grid.setSymbol("O", new Cell(1, 1));
        minimaxStrategy.grid.setSymbol("X", new Cell(2, 0));
        Assert.assertEquals(Integer.valueOf(-1), minimaxStrategy.getScore(minimaxStrategy.grid));
    }

    @Test
    public void getEmptyCells() {
        minimaxStrategy.grid.setSymbol("O", new Cell(0, 0));
        minimaxStrategy.grid.setSymbol("X", new Cell(0, 1));
        minimaxStrategy.grid.setSymbol("O", new Cell(0, 2));
        minimaxStrategy.grid.setSymbol("O", new Cell(1, 0));
        minimaxStrategy.grid.setSymbol("X", new Cell(1, 1));
        minimaxStrategy.grid.setSymbol("O", new Cell(1, 2));
        minimaxStrategy.grid.setSymbol("X", new Cell(2, 0));
        minimaxStrategy.grid.setSymbol("X", new Cell(2, 2));
        Assert.assertEquals(1, minimaxStrategy.getEmptyCells(minimaxStrategy.grid).size());
        Assert.assertEquals(new Cell(2, 1), minimaxStrategy.getEmptyCells(minimaxStrategy.grid).get(0));
    }

    @Test
    public void getScoreOfGridState() {
        minimaxStrategy.grid.setSymbol("X", new Cell(0, 0));
        minimaxStrategy.grid.setSymbol("O", new Cell(0, 1));
        minimaxStrategy.grid.setSymbol("O", new Cell(0, 2));
        minimaxStrategy.grid.setSymbol("O", new Cell(1, 0));
        minimaxStrategy.grid.setSymbol("O", new Cell(2, 0));
        minimaxStrategy.grid.setSymbol("X", new Cell(2, 1));
        minimaxStrategy.grid.setSymbol("X", new Cell(2, 2));
        Assert.assertEquals(new Cell(1, 1), minimaxStrategy.getBestCellToPlay("X", minimaxStrategy.grid));
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
        minimaxStrategy.grid.setSymbol("X", new Cell(0, 0));
        minimaxStrategy.grid.setSymbol("O", new Cell(0, 2));
        minimaxStrategy.grid.setSymbol("O", new Cell(1, 0));
        minimaxStrategy.grid.setSymbol("O", new Cell(1, 2));
        minimaxStrategy.grid.setSymbol("O", new Cell(2, 0));
        minimaxStrategy.grid.setSymbol("X", new Cell(2, 1));
        minimaxStrategy.grid.setSymbol("X", new Cell(2, 2));
        Assert.assertEquals(new Cell(1, 1), minimaxStrategy.getBestCellToPlay("X", minimaxStrategy.grid));
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
        minimaxStrategy.grid.setSymbol("X", new Cell(0, 0));
        minimaxStrategy.grid.setSymbol("O", new Cell(0, 2));
        minimaxStrategy.grid.setSymbol("O", new Cell(1, 0));
        minimaxStrategy.grid.setSymbol("O", new Cell(2, 0));
        minimaxStrategy.grid.setSymbol("X", new Cell(2, 1));
        minimaxStrategy.grid.setSymbol("X", new Cell(2, 2));
        Assert.assertEquals(new Cell(1, 1), minimaxStrategy.getBestCellToPlay("O", minimaxStrategy.grid));
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
        minimaxStrategy.grid.setSymbol("X", new Cell(0, 0));
        minimaxStrategy.grid.setSymbol("O", new Cell(0, 1));
        minimaxStrategy.grid.setSymbol("X", new Cell(0, 2));
        minimaxStrategy.grid.setSymbol("X", new Cell(1, 0));
        minimaxStrategy.grid.setSymbol("O", new Cell(1, 1));
        minimaxStrategy.grid.setSymbol("X", new Cell(1, 2));
        minimaxStrategy.grid.setSymbol("O", new Cell(2, 0));
        Assert.assertEquals(new Cell(2, 1), minimaxStrategy.getBestCellToPlay("O", minimaxStrategy.grid));
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
        minimaxStrategy.grid.setSymbol("O", new Cell(0, 0));
        minimaxStrategy.grid.setSymbol("X", new Cell(0, 1));
        minimaxStrategy.grid.setSymbol("X", new Cell(1, 1));
        Assert.assertEquals(new Cell(2, 1), minimaxStrategy.getBestCellToPlay("O", minimaxStrategy.grid));
        /*
         O | X |
        ---+---+---
           | X |
        ---+---+---
           |   |
         */
    }
}
