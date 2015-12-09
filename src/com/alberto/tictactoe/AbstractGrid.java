package com.alberto.tictactoe;

public class AbstractGrid {
    private static final int TOTAL_CELLS = 9;
    private String[][] grid = new String[3][3];
    private int filledCells;

    public void setSymbol(final String symbol, final int row, final int column) {
        grid[row][column] = symbol;
        filledCells++;
    }

    public boolean isCellFree(final int row, final int column) {
        return getCellValue(row, column) == null;
    }

    public boolean isCellValueEqualsTo(final String symbol, final int row, final int column) {
        return getCellValue(row, column) != null && getCellValue(row, column).equalsIgnoreCase(symbol);
    }

    private String getCellValue(final int row, final int column) {
        return grid[row][column];
    }

    public boolean isFull() {
        return filledCells == TOTAL_CELLS;
    }
}
