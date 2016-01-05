package com.alberto.tictactoe;

import org.junit.Assert;
import org.junit.Test;

public class StrategyTwoToWin {

    @Test
    public void getBestPlayWhenTheFirstMoveWasAnyCorner() {
        AbstractGrid grid = new AbstractGrid();
        grid.setSymbol("X", new Cell(0, 0));
        Assert.assertEquals(new Cell(1, 1), getBestMove(grid, new Cell(0,0)));
        grid = new AbstractGrid();
        grid.setSymbol("X", new Cell(0, 2));
        Assert.assertEquals(new Cell(1, 1), getBestMove(grid, new Cell(0, 2)));
        grid = new AbstractGrid();
        grid.setSymbol("X", new Cell(2, 0));
        Assert.assertEquals(new Cell(1, 1), getBestMove(grid, new Cell(2, 0)));
        grid = new AbstractGrid();
        grid.setSymbol("X", new Cell(2, 2));
        Assert.assertEquals(new Cell(1, 1), getBestMove(grid, new Cell(2, 2)));
    }

    @Test
    public void getBestPlayWhenTheFirstMoveWasAnyCenterSide() {
        AbstractGrid grid = new AbstractGrid();
        grid.setSymbol("X", new Cell(0, 1));
        Assert.assertEquals(new Cell(0, 0), getBestMove(grid, new Cell(0, 1)));
        grid = new AbstractGrid();
        grid.setSymbol("X", new Cell(1, 0));
        Assert.assertEquals(new Cell(0, 0), getBestMove(grid, new Cell(1, 0)));
        grid = new AbstractGrid();
        grid.setSymbol("X", new Cell(1, 2));
        Assert.assertEquals(new Cell(2, 2), getBestMove(grid, new Cell(1, 2)));
        grid = new AbstractGrid();
        grid.setSymbol("X", new Cell(2, 1));
        Assert.assertEquals(new Cell(2, 2), getBestMove(grid, new Cell(2, 1)));
    }

    @Test
    public void getBestPlayWhenTheSecondMOveWasInACenterSideAfterCorner() {
        AbstractGrid grid = new AbstractGrid();
        grid.setSymbol("X", new Cell(0, 0));
        grid.setSymbol("O", new Cell(1, 1));
        grid.setSymbol("X", new Cell(0, 1));
        Assert.assertEquals(new Cell(0, 2), getBestMove(grid, new Cell(0, 1)));

    }

    @Test
    public void getBestPlayWhenTheFirstMoveWasTheMiddle() {
        AbstractGrid grid = new AbstractGrid();
        grid.setSymbol("X", new Cell(1, 1));
        Assert.assertEquals(new Cell(0, 0), getBestMove(grid, new Cell(1, 1)));
    }

    private Cell getBestMove(AbstractGrid grid, Cell opponentLastMove) {
        if (isCorner(opponentLastMove))
            return new Cell(1, 1);
        if(isCenterSide(opponentLastMove))
            return getNextCornerOfCenterSide(grid, opponentLastMove);
        return new Cell(0, 0);
    }

    private boolean isCorner(Cell opponentLastMove) {
        return opponentLastMove.equals(new Cell(0, 0)) ||
                opponentLastMove.equals(new Cell(0, 2)) ||
                opponentLastMove.equals(new Cell(2, 0)) ||
                opponentLastMove.equals(new Cell(2, 2));
    }

    private boolean isCenterSide(Cell opponentLastMove) {
        return opponentLastMove.equals(new Cell(0, 1)) ||
                opponentLastMove.equals(new Cell(1, 0)) ||
                opponentLastMove.equals(new Cell(1, 2)) ||
                opponentLastMove.equals(new Cell(2, 1));
    }

    private Cell getNextCornerOfCenterSide(AbstractGrid grid, Cell opponentLastMove) {
        if(opponentLastMove.equals(new Cell(0, 1))) {
            if(grid.isCellFree(new Cell(0, 0))){
                return new Cell(0, 0);
            }
            else {
                return new Cell(0, 2);
            }
        }
        if (opponentLastMove.equals(new Cell(1, 0)))
            return new Cell(0, 0);
        else
           return new Cell(2, 2);
    }




}
