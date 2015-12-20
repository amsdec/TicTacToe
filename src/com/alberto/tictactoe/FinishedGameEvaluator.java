package com.alberto.tictactoe;


public class FinishedGameEvaluator {

    private AbstractGrid grid;
    private String winner;

    public FinishedGameEvaluator(final AbstractGrid grid) {
        this.grid = grid;
    }

    public boolean isFinished() {
        return isThereAWinner() || isTie();
    }

    private boolean isThereAWinner() {
        return isThereAWinnerByFillingARow() ||
                isThereAWinnerByFillingAColumn() ||
                isThereAWinnerByFillingADiagonal();
    }

    private boolean isTie() {
        return grid.isFull();
    }

    private boolean isThereAWinnerByFillingARow() {
        for (int rowNumber = 0; rowNumber < 3; rowNumber++)
            if (isThereAWinnerByByFillingTheRow(rowNumber)) {
                winner = grid.getCellValue(new Cell(rowNumber, 0));
                return true;
            }
        return false;
    }

    private boolean isThereAWinnerByFillingAColumn() {
        for (int columnNumber = 0; columnNumber < 3; columnNumber++)
            if (isThereAWinnerByFillingTheColumn(columnNumber)) {
                winner = grid.getCellValue(new Cell(0, columnNumber));
                return true;
            }
        return false;
    }

    private boolean isThereAWinnerByFillingADiagonal() {
        if ((isSameValueOnCells(new Cell(0, 0), new Cell(1, 1), new Cell(2, 2))) ||
                isSameValueOnCells(new Cell(0, 2), new Cell(1, 1), new Cell(2, 0))) {
            winner = grid.getCellValue(new Cell(1, 1));
            return true;
        }
        return false;
    }

    private boolean isThereAWinnerByByFillingTheRow(final int rowNumber) {
        return isSameValueOnCells(new Cell(rowNumber, 0), new Cell(rowNumber, 1), new Cell(rowNumber, 2));
    }

    private boolean isThereAWinnerByFillingTheColumn(final int columnNumber) {
        return isSameValueOnCells(new Cell(0, columnNumber), new Cell(1, columnNumber), new Cell(2, columnNumber));
    }

    private boolean isSameValueOnCells(Cell cellOne, Cell cellTwo, Cell cellThree) {
        return grid.getCellValue(cellOne) != null && grid.getCellValue(cellTwo) != null && grid.getCellValue(cellThree) != null &&
                grid.getCellValue(cellOne).equals(grid.getCellValue(cellTwo)) && grid.getCellValue(cellTwo).equals(grid.getCellValue(cellThree));
    }

    public String getWinner() {
        return winner;
    }
}
