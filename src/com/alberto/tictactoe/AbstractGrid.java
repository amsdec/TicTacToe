package com.alberto.tictactoe;

public class AbstractGrid {
    public static final String EMPTY_CELL = " ";
    private static final int TOTAL_CELLS = 9;
    private String[][] grid = new String[3][3];
    private int filledCells;

    public boolean setSymbol(final String symbol, final Cell cell) {
        return isCellFree(cell) && setSymbolOnCell(symbol, cell);
    }

    private boolean setSymbolOnCell(final String symbol, final Cell cell) {
        grid[cell.getRow()][cell.getColumn()] = symbol;
        filledCells++;
        return true;
    }

    public boolean isCellFree(final Cell cell) {
        return getCellValue(cell) == null;
    }

    public String getCellValue(final Cell cell) {
        return grid[cell.getRow()][cell.getColumn()];
    }

    public boolean isFull() {
        return filledCells >= TOTAL_CELLS;
    }

    @Override
    public String toString() {
        StringBuilder stringForm = new StringBuilder();
        buildPrintableGrid(stringForm);
        return stringForm.toString();
    }

    private void buildPrintableGrid(final StringBuilder stringForm) {
        for (int row = 0; row < 3; row++) {
            addPrintableRow(stringForm, row);
            addRowSeparator(stringForm, row);
        }
    }

    private void addRowSeparator(final StringBuilder stringForm, final int row) {
        stringForm.append("\n");
        addDelimiter(stringForm, row, "--+---+---");
        stringForm.append("\n");
    }

    private void addPrintableRow(final StringBuilder stringForm, final int row) {
        for (int column = 0; column < 3; column++) {
            stringForm.append(getPrintableCell(row, column));
            addDelimiter(stringForm, column, " | ");
        }
    }

    private void addDelimiter(final StringBuilder stringForm, final int index, final String delimiter) {
        if (index < 2)
            stringForm.append(delimiter);
    }

    private String getPrintableCell(final int row, final int column) {
        return getCellValue(new Cell(row, column)) != null ? getCellValue(new Cell(row, column)) : EMPTY_CELL;
    }

}
