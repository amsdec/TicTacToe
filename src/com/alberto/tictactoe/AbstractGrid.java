package com.alberto.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class AbstractGrid {
    public static final String EMPTY_CELL = " ";
    private static final int TOTAL_CELLS = 9;
    private String[][] grid = new String[3][3];
    private int filledCells;

    public boolean setSymbol(final String symbol, Cell cell) {
        if (!isCellFree(cell))
            return false;
        grid[cell.getRow()][cell.getColumn()] = symbol;
        filledCells++;
        return true;
    }

    public boolean isCellFree(Cell cell) {
        return getCellValue(cell) == null;
    }

    public String getCellValue(Cell cell) {
        return grid[cell.getRow()][cell.getColumn()];
    }

    public boolean isFull() {
        return filledCells >= TOTAL_CELLS;
    }

    public boolean isEmpty() {
        return filledCells == 0;
    }

    @Override
    public String toString() {
        StringBuilder stringForm = new StringBuilder();
        buildPrintableGrid(stringForm);
        return stringForm.toString();
    }

    private void buildPrintableGrid(StringBuilder stringForm) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                stringForm.append(getPrintableCell(row, column));
                addDelimiter(stringForm, column, " | ");
            }
            stringForm.append("\n");
            addDelimiter(stringForm, row, "--+---+---");
            stringForm.append("\n");
        }
    }

    private void addDelimiter(StringBuilder stringForm, int index, String delimiter) {
        if (index < 2)
            stringForm.append(delimiter);
    }

    private String getPrintableCell(int row, int column) {
        return getCellValue(new Cell(row, column)) != null ? getCellValue(new Cell(row, column)) : EMPTY_CELL;
    }

    public String[][] getGrid() {
        return this.grid;
    }

}
