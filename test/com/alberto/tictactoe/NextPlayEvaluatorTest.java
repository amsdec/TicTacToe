package com.alberto.tictactoe;

import org.junit.Assert;
import org.junit.Test;

public class NextPlayEvaluatorTest {
    @Test
    public void evaluateForEmptyGrid() {
        AbstractGrid grid = new AbstractGrid();
        NextPlayEvaluator nextPlayEvaluator = new NextPlayEvaluator(grid);
        Cell cell = nextPlayEvaluator.getNextCellToPlay();
        Assert.assertNotNull(cell);
    }

    @Test
    public void evaluateForOneFilledCell() {
        AbstractGrid grid = new AbstractGrid();
        NextPlayEvaluator nextPlayEvaluator = new NextPlayEvaluator(grid);
        FinishedGameEvaluator finishedGameEvaluator = new FinishedGameEvaluator(grid);
        grid.setSymbol("X", new Cell(0, 0));
        Assert.assertFalse(finishedGameEvaluator.isFinished("X"));
        Cell cell = nextPlayEvaluator.getNextCellToPlay();
        Assert.assertNotNull(cell);
        Assert.assertEquals(1, cell.getRow());
        Assert.assertEquals(1, cell.getColumn());
        grid.setSymbol("O", cell);
        Assert.assertFalse(finishedGameEvaluator.isFinished("O"));
        grid.setSymbol("X", new Cell(0, 1));
        Assert.assertFalse(finishedGameEvaluator.isFinished("X"));
        cell = nextPlayEvaluator.getNextCellToPlay();
        Assert.assertNotNull(cell);
        Assert.assertEquals(0, cell.getRow());
        Assert.assertEquals(2, cell.getColumn());
        grid.setSymbol("O", cell);
        Assert.assertFalse(finishedGameEvaluator.isFinished("O"));
        grid.setSymbol("X", new Cell(2, 0));
        Assert.assertFalse(finishedGameEvaluator.isFinished("X"));
        cell = nextPlayEvaluator.getNextCellToPlay();
        Assert.assertNotNull(cell);
        Assert.assertEquals(1, cell.getRow());
        Assert.assertEquals(0, cell.getColumn());
        grid.setSymbol("O", cell);
        Assert.assertFalse(finishedGameEvaluator.isFinished("O"));
        grid.setSymbol("X", new Cell(1, 2));
        Assert.assertFalse(finishedGameEvaluator.isFinished("X"));
        cell = nextPlayEvaluator.getNextCellToPlay();
        Assert.assertNotNull(cell);
        Assert.assertEquals(1, cell.getRow());
        Assert.assertEquals(0, cell.getColumn());
        grid.setSymbol("O", cell);
        Assert.assertFalse(finishedGameEvaluator.isFinished("O"));

    }

    private class NextPlayEvaluator {
        private AbstractGrid grid;

        private Cell lastUsedCell;
        public NextPlayEvaluator(AbstractGrid grid) {
            this.grid = grid;
        }

        public Cell getNextCellToPlay() {
            if (this.grid.isEmpty())
                return getRandomPlay();
            else
                return getNextBestPlay();
        }

        private Cell getRandomPlay() {
            int row;
            int column;
            do {
                row = Double.valueOf(Math.random() % 3).intValue();
                column = Double.valueOf(Math.random() % 3).intValue();
            } while (!grid.isCellFree(new Cell(row, column)));
            return new Cell(row, column);
        }

        private Cell getNextBestPlay() {
            if (this.grid.isCellFree(new com.alberto.tictactoe.Cell(1, 1))) {
                return new Cell(1, 1);
            } else {
                Cell winnerCell = getWinnerPlay();
                if (winnerCell != null) {
                    lastUsedCell = winnerCell;
                    return winnerCell;
                } else {
                    Cell blockerCell = getBlockerPlay();
                    if (blockerCell != null) {
                        lastUsedCell = blockerCell;
                        return blockerCell;
                    }

                }
            }
            return getRandomPlay();
        }

        private Cell getWinnerPlay() {
            return getMustPlayCell("O");
        }

        private Cell getBlockerPlay() {
            return getMustPlayCell("X");
        }

        private Cell getMustPlayCell(String symbol) {
            if (grid.isCellValueEqualsTo(symbol, new Cell(0, 0)) && grid.isCellValueEqualsTo(symbol, new Cell(0, 1)) && grid.isCellFree(new Cell(0, 2))) {
                return new Cell(0, 2);
            }
            if (grid.isCellValueEqualsTo(symbol, new Cell(0, 0)) && grid.isCellValueEqualsTo(symbol, new Cell(0, 2)) && grid.isCellFree(new Cell(0, 1))) {
                return new Cell(0, 1);
            }
            if (grid.isCellValueEqualsTo(symbol, new Cell(0, 1)) && grid.isCellValueEqualsTo(symbol, new Cell(0, 2)) && grid.isCellFree(new Cell(0, 0))) {
                return new Cell(0, 0);
            }

            if (grid.isCellValueEqualsTo(symbol, new Cell(1, 0)) && grid.isCellValueEqualsTo(symbol, new Cell(1, 1)) && grid.isCellFree(new Cell(1, 2))) {
                return new Cell(1, 2);
            }
            if (grid.isCellValueEqualsTo(symbol, new Cell(1, 0)) && grid.isCellValueEqualsTo(symbol, new Cell(1, 2)) && grid.isCellFree(new Cell(1, 1))) {
                return new Cell(1, 1);
            }
            if (grid.isCellValueEqualsTo(symbol, new Cell(1, 1)) && grid.isCellValueEqualsTo(symbol, new Cell(1, 2)) && grid.isCellFree(new Cell(1, 0))) {
                return new Cell(1, 0);
            }

            if (grid.isCellValueEqualsTo(symbol, new Cell(2, 0)) && grid.isCellValueEqualsTo(symbol, new Cell(2, 1)) && grid.isCellFree(new Cell(2, 2))) {
                return new Cell(2, 2);
            }
            if (grid.isCellValueEqualsTo(symbol, new Cell(2, 0)) && grid.isCellValueEqualsTo(symbol, new Cell(2, 2)) && grid.isCellFree(new Cell(2, 1))) {
                return new Cell(2, 1);
            }
            if (grid.isCellValueEqualsTo(symbol, new Cell(2, 1)) && grid.isCellValueEqualsTo(symbol, new Cell(2, 2)) && grid.isCellFree(new Cell(2, 0))) {
                return new Cell(2, 0);
            }

            if (grid.isCellValueEqualsTo(symbol, new Cell(0, 0)) && grid.isCellValueEqualsTo(symbol, new Cell(1, 0)) && grid.isCellFree(new Cell(2, 0))) {
                return new Cell(2, 0);
            }
            if (grid.isCellValueEqualsTo(symbol, new Cell(0, 0)) && grid.isCellValueEqualsTo(symbol, new Cell(2, 0)) && grid.isCellFree(new Cell(1, 0))) {
                return new Cell(1, 0);
            }
            if (grid.isCellValueEqualsTo(symbol, new Cell(1, 0)) && grid.isCellValueEqualsTo(symbol, new Cell(2, 0)) && grid.isCellFree(new Cell(0, 0))) {
                return new Cell(0, 0);
            }

            if (grid.isCellValueEqualsTo(symbol, new Cell(0, 1)) && grid.isCellValueEqualsTo(symbol, new Cell(1, 1)) && grid.isCellFree(new Cell(2, 1))) {
                return new Cell(2, 1);
            }
            if (grid.isCellValueEqualsTo(symbol, new Cell(0, 1)) && grid.isCellValueEqualsTo(symbol, new Cell(2, 1)) && grid.isCellFree(new Cell(1, 1))) {
                return new Cell(1, 1);
            }
            if (grid.isCellValueEqualsTo(symbol, new Cell(1, 1)) && grid.isCellValueEqualsTo(symbol, new Cell(2, 1)) && grid.isCellFree(new Cell(0, 1))) {
                return new Cell(0, 1);
            }

            if (grid.isCellValueEqualsTo(symbol, new Cell(0, 2)) && grid.isCellValueEqualsTo(symbol, new Cell(1, 2)) && grid.isCellFree(new Cell(2, 2))) {
                return new Cell(2, 2);
            }
            if (grid.isCellValueEqualsTo(symbol, new Cell(0, 2)) && grid.isCellValueEqualsTo(symbol, new Cell(2, 2)) && grid.isCellFree(new Cell(1, 2))) {
                return new Cell(1, 2);
            }
            if (grid.isCellValueEqualsTo(symbol, new Cell(1, 2)) && grid.isCellValueEqualsTo(symbol, new Cell(2, 2)) && grid.isCellFree(new Cell(0, 2))) {
                return new Cell(0, 2);
            }

            if (grid.isCellValueEqualsTo(symbol, new Cell(0, 0)) && grid.isCellValueEqualsTo(symbol, new Cell(1, 1)) && grid.isCellFree(new Cell(2, 2))) {
                return new Cell(2, 2);
            }
            if (grid.isCellValueEqualsTo(symbol, new Cell(0, 0)) && grid.isCellValueEqualsTo(symbol, new Cell(2, 2)) && grid.isCellFree(new Cell(1, 1))) {
                return new Cell(1, 1);
            }
            if (grid.isCellValueEqualsTo(symbol, new Cell(1, 1)) && grid.isCellValueEqualsTo(symbol, new Cell(2, 2)) && grid.isCellFree(new Cell(0, 0))) {
                return new Cell(0, 0);
            }

            if (grid.isCellValueEqualsTo(symbol, new Cell(0, 2)) && grid.isCellValueEqualsTo(symbol, new Cell(1, 1)) && grid.isCellFree(new Cell(2, 0))) {
                return new Cell(2, 0);
            }
            if (grid.isCellValueEqualsTo(symbol, new Cell(0, 2)) && grid.isCellValueEqualsTo(symbol, new Cell(2, 0)) && grid.isCellFree(new Cell(1, 1))) {
                return new Cell(1, 1);
            }
            if (grid.isCellValueEqualsTo(symbol, new Cell(1, 1)) && grid.isCellValueEqualsTo(symbol, new Cell(2, 0)) && grid.isCellFree(new Cell(0, 2))) {
                return new Cell(0, 2);
            }
            return null;
        }
    }
}
