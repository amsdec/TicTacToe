package com.alberto.tictactoe;

import com.alberto.tictactoe.player.NextPlayEvaluator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class NextPlayEvaluatorTest {


    private AbstractGrid grid;
    private NextPlayEvaluator nextPlayEvaluator;

    @Before
    public void setUp() throws Exception {
        grid = new AbstractGrid();
        nextPlayEvaluator = new NextPlayEvaluator();
    }

    @Test
    public void getPossibleCellsToPlayOnFilledGridMustReturnAnEmptyListOfCells() {
        fillGrid();
        assertListSize(0, nextPlayEvaluator.getPossibleCellsToPlayFromGrid(grid));
    }


    @Test
    public void getPossibleCellsToPlayOnEmptyGridMustReturnAListOfAllCells() {
        List<Cell> possibleCellsToPlayFromGrid = nextPlayEvaluator.getPossibleCellsToPlayFromGrid(grid);
        assertListSize(9, possibleCellsToPlayFromGrid);
        Assert.assertEquals(new Cell(0, 0), possibleCellsToPlayFromGrid.get(0));
        Assert.assertEquals(new Cell(0, 1), possibleCellsToPlayFromGrid.get(1));
        Assert.assertEquals(new Cell(0, 2), possibleCellsToPlayFromGrid.get(2));
        Assert.assertEquals(new Cell(1, 0), possibleCellsToPlayFromGrid.get(3));
        Assert.assertEquals(new Cell(1, 1), possibleCellsToPlayFromGrid.get(4));
        Assert.assertEquals(new Cell(1, 2), possibleCellsToPlayFromGrid.get(5));
        Assert.assertEquals(new Cell(2, 0), possibleCellsToPlayFromGrid.get(6));
        Assert.assertEquals(new Cell(2, 1), possibleCellsToPlayFromGrid.get(7));
        Assert.assertEquals(new Cell(2, 2), possibleCellsToPlayFromGrid.get(8));
    }

    @Test
    public void getGridStateAfterMove() {
        AbstractGrid nextGrid = nextPlayEvaluator.getNextGridState(grid, new Cell(1, 1), "X");
        Assert.assertEquals("X", nextGrid.getCellValue(new Cell(1, 1)));
        Assert.assertTrue(nextGrid.isCellFree(new Cell(0, 0)));
        Assert.assertTrue(nextGrid.isCellFree(new Cell(0, 1)));
        Assert.assertTrue(nextGrid.isCellFree(new Cell(0, 2)));
        Assert.assertTrue(nextGrid.isCellFree(new Cell(1, 0)));
        Assert.assertFalse(nextGrid.isCellFree(new Cell(1, 1)));
        Assert.assertTrue(nextGrid.isCellFree(new Cell(1, 2)));
        Assert.assertTrue(nextGrid.isCellFree(new Cell(2, 0)));
        Assert.assertTrue(nextGrid.isCellFree(new Cell(2, 1)));
        Assert.assertTrue(nextGrid.isCellFree(new Cell(2, 2)));
    }

    @Test
    public void getScoreForXOnCell22MustBe0() {
        grid.setSymbol("X", new Cell(0, 0));
        grid.setSymbol("X", new Cell(0, 1));
        grid.setSymbol("O", new Cell(0, 2));
        grid.setSymbol("O", new Cell(1, 0));
        grid.setSymbol("O", new Cell(1, 1));
        grid.setSymbol("X", new Cell(1, 2));
        grid.setSymbol("X", new Cell(2, 0));
        grid.setSymbol("O", new Cell(2, 1));
        Assert.assertEquals(0, nextPlayEvaluator.getScoreForSymbolOnCell("X", new Cell(2, 2), grid));
    }

    @Test
    public void getScoreForXOnCell22MustBe1() {
        grid.setSymbol("O", new Cell(0, 0));
        grid.setSymbol("X", new Cell(0, 1));
        grid.setSymbol("O", new Cell(0, 2));
        grid.setSymbol("X", new Cell(1, 0));
        grid.setSymbol("O", new Cell(1, 1));
        grid.setSymbol("O", new Cell(1, 2));
        grid.setSymbol("X", new Cell(2, 0));
        grid.setSymbol("X", new Cell(2, 1));
        Assert.assertEquals(1, nextPlayEvaluator.getScoreForSymbolOnCell("X", new Cell(2, 2), grid));
    }

    @Test
    public void getScoreForXOnCell22MustBe2() {
        grid.setSymbol("X", new Cell(0, 0));
        grid.setSymbol("O", new Cell(0, 1));
        grid.setSymbol("O", new Cell(0, 2));
        grid.setSymbol("X", new Cell(1, 0));
        grid.setSymbol("X", new Cell(1, 1));
        grid.setSymbol("O", new Cell(2, 0));
        Assert.assertEquals(2, nextPlayEvaluator.getScoreForSymbolOnCell("X", new Cell(2, 1), grid));
    }

    @Test
    public void getScoreForXOnCell21MustBe1() {
        grid.setSymbol("X", new Cell(0, 0));
        grid.setSymbol("O", new Cell(0, 1));
        grid.setSymbol("O", new Cell(0, 2));
        grid.setSymbol("X", new Cell(1, 0));
        grid.setSymbol("X", new Cell(1, 1));
        grid.setSymbol("O", new Cell(1, 2));
        Assert.assertEquals(0, nextPlayEvaluator.getScoreForSymbolOnCell("X", new Cell(2, 1), grid));
    }

    @Test
    public void getBestCellToPlay() {
        grid.setSymbol("X", new Cell(0, 0));
        grid.setSymbol("O", new Cell(0, 1));
        grid.setSymbol("O", new Cell(0, 2));
        grid.setSymbol("X", new Cell(1, 0));
        grid.setSymbol("X", new Cell(1, 1));
        grid.setSymbol("O", new Cell(1, 2));
        Assert.assertEquals(new Cell(2, 0), nextPlayEvaluator.getBestCellToPlay("X", grid));
    }

    @Test
    public void getBestCellToPlayOnEmptyGrid() {
        Assert.assertEquals(new Cell(1, 1), nextPlayEvaluator.getBestCellToPlay("X", grid));
    }

    @Test
    public void getBestCellToPlayOnSecondPlayerFirstTurn() {
        grid.setSymbol("X", new Cell(1, 1));
        Assert.assertEquals(new Cell(0, 0), nextPlayEvaluator.getBestCellToPlay("O", grid));
    }

    private void assertListSize(int expectedSize, List<Cell> possibleCellsToPlayFromGrid) {
        Assert.assertEquals(expectedSize, possibleCellsToPlayFromGrid.size());
    }

    private void fillGrid() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                grid.setSymbol("X", new Cell(row, column));
            }
        }
    }
}
