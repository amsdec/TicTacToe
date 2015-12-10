package com.alberto.tictactoe;

public class AbstractGrid {
    private static final int TOTAL_CELLS = 9;
    private String[][] grid = new String[3][3];
    private int filledCells;

    public void setSymbol(final String symbol, Cell cell) {
        grid[cell.getRow()][cell.getColumn()] = symbol;
        filledCells++;
        printGrid();
    }

    public boolean isCellFree(Cell cell) {
        return getCellValue(cell) == null;
    }

    public boolean isCellValueEqualsTo(final String symbol, Cell cell) {
        return getCellValue(cell) != null && getCellValue(cell).equalsIgnoreCase(symbol);
    }

    private String getCellValue(Cell cell) {
        return grid[cell.getRow()][cell.getColumn()];
    }

    public boolean isFull() {
        return filledCells == TOTAL_CELLS;
    }

    public boolean isEmpty() {
        return filledCells == 0;
    }

    private void printGrid() {

        System.out.println("------------------------");
        System.out.println((grid[0][0] != null ? grid[0][0] : " ") + "|" + (grid[0][1] != null ? grid[0][1] : " ") + "|" + (grid[0][2] != null ? grid[0][2] : " "));
        System.out.println("-+-+-");
        System.out.println((grid[1][0] != null ? grid[1][0] : " ") + "|" + (grid[1][1] != null ? grid[1][1] : " ") + "|" + (grid[1][2] != null ? grid[1][2] : " "));
        System.out.println("-+-+-");
        System.out.println((grid[2][0] != null ? grid[2][0] : " ") + "|" + (grid[2][1] != null ? grid[2][1] : " ") + "|" + (grid[2][2] != null ? grid[2][2] : " "));
        System.out.println("------------------------");
    }

}
