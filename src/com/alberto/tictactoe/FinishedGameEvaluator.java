package com.alberto.tictactoe;


public class FinishedGameEvaluator {

    private AbstractGrid grid;
    private String winner;
    public FinishedGameEvaluator(final AbstractGrid grid) {
        this.grid = grid;
    }
    public boolean isFinished(final String lastPlacedSymbol) {
        return doesLastPLacedSymbolWinsByFillingARow(lastPlacedSymbol) ||
                doesLastPLacedSymbolWinsByFillingAColumn(lastPlacedSymbol) ||
                doesLastPlacedSymbolWinsByFillingADiagonal(lastPlacedSymbol) ||
                isTie();
    }

    private boolean isTie() {
        return grid.isFull();
    }

    private boolean doesLastPLacedSymbolWinsByFillingARow(final String lastPlacedSymbol) {
        for (int rowNumber = 0; rowNumber < 3; rowNumber++)
            if (doesLastPlacedSymbolWinsByFillingTheRow(rowNumber, lastPlacedSymbol)) {
                winner = lastPlacedSymbol;
                return true;
            }
        return false;
    }

    private boolean doesLastPLacedSymbolWinsByFillingAColumn(final String lastPlacedSymbol) {
        for (int columnNumber = 0; columnNumber < 3; columnNumber++)
            if (doesLastPlacedSymbolWinsByFillingTheColumn(columnNumber, lastPlacedSymbol)) {
                winner = lastPlacedSymbol;
                return true;
            }
        return false;
    }

    private boolean doesLastPlacedSymbolWinsByFillingADiagonal(final String lastPlacedSymbol) {
        if ((grid.isCellValueEqualsTo(lastPlacedSymbol, 0, 0) &&
                grid.isCellValueEqualsTo(lastPlacedSymbol, 1, 1) &&
                grid.isCellValueEqualsTo(lastPlacedSymbol, 2, 2)) ||
                (grid.isCellValueEqualsTo(lastPlacedSymbol, 0, 2) &&
                        grid.isCellValueEqualsTo(lastPlacedSymbol, 1, 1) &&
                        grid.isCellValueEqualsTo(lastPlacedSymbol, 2, 0))) {
            winner = lastPlacedSymbol;
            return true;
        }
        return false;
    }

    private boolean doesLastPlacedSymbolWinsByFillingTheRow(final int rowNumber, final String lastPlacedSymbol) {
        return (grid.isCellValueEqualsTo(lastPlacedSymbol, rowNumber, 0) &&
                grid.isCellValueEqualsTo(lastPlacedSymbol, rowNumber, 1) &&
                grid.isCellValueEqualsTo(lastPlacedSymbol, rowNumber, 2));
    }

    private boolean doesLastPlacedSymbolWinsByFillingTheColumn(final int columnNumber, final String lastPlacedSymbol) {
        return (grid.isCellValueEqualsTo(lastPlacedSymbol, 0, columnNumber) &&
                grid.isCellValueEqualsTo(lastPlacedSymbol, 1, columnNumber) &&
                grid.isCellValueEqualsTo(lastPlacedSymbol, 2, columnNumber));
    }

    public String getWinner() {
        return winner;
    }
}
