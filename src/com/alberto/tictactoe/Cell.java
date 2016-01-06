package com.alberto.tictactoe;

public class Cell {
    private final int row;
    private final int column;

    public Cell(final int row, final int column) {
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
    public boolean equals(final Object otherCell) {
        return canCompare(otherCell) && isSamePositionOnGrid((Cell) otherCell);
    }

    private boolean isSamePositionOnGrid(final Cell otherCell) {
        return isaRowValueEquals(otherCell) && isColumnValueEquals(otherCell);
    }

    private boolean isColumnValueEquals(final Cell otherCell) {
        return getColumn() == otherCell.getColumn();
    }

    private boolean isaRowValueEquals(final Cell otherCell) {
        return getRow() == otherCell.getRow();
    }

    private boolean canCompare(final Object otherCell) {
        return otherCell != null && otherCell instanceof Cell;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }
}
