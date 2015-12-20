package com.alberto.tictactoe;

public class AbstractGrid {
    private static final int TOTAL_CELLS = 9;
    public static final String EMPTY_CELL = " ";
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
        return filledCells == TOTAL_CELLS;
    }

    public boolean isEmpty() {
        return filledCells == 0;
    }

    @Override
    public String toString() {
        StringBuilder stringForm = new StringBuilder();
        stringForm.append("------------------------");
        stringForm.append("\n");
        buildPrintableGrid(stringForm);
        stringForm.append("------------------------");
        return stringForm.toString();
    }

    private void buildPrintableGrid(StringBuilder stringForm) {
        for(int row = 0; row < 3; row++) {
            for(int column = 0; column < 3; column++) {
                stringForm.append(getPrintableCell(row, column));
                addDelimiter(stringForm, column, "|");
            }
            addDelimiter(stringForm, row, "-+-+-");
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
