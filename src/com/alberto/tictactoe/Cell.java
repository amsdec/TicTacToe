package com.alberto.tictactoe;

public class Cell {
    private final int row;
    private final int column;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Cell && (getRow() == ((Cell) obj).getRow() && getColumn() == ((Cell) obj).getColumn());
    }
}
