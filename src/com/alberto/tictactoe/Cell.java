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
    public boolean equals(Object otherCell) {
        return canCompare(otherCell) && isSamePositionOnGrid((Cell) otherCell);
    }

    private boolean isSamePositionOnGrid(Cell otherCell) {
        return isaRowValueEquals(otherCell) && isColumnValueEquals(otherCell);
    }

    private boolean isColumnValueEquals(Cell otherCell) {
        return getColumn() == otherCell.getColumn();
    }

    private boolean isaRowValueEquals(Cell otherCell) {
        return getRow() == otherCell.getRow();
    }

    private boolean canCompare(Object otherCell) {
        return otherCell != null && otherCell instanceof Cell;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }
}
