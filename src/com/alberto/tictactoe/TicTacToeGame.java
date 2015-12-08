package com.alberto.tictactoe;

public class TicTacToeGame {

    AbstractGrid grid = new AbstractGrid();
    String lastPlacedSymbol = null;

    public boolean placeSymbol(final String symbol, final int row, final int column) {
        if (!canPlaceSymbol(symbol, row, column))
            return false;
        placeSymbolOnCell(symbol, row, column);
        return true;
    }

    private void placeSymbolOnCell(String symbol, int row, int column) {
        grid.setSymbol(symbol, row, column);
        lastPlacedSymbol = symbol;
    }

    private boolean canPlaceSymbol(String symbol, int row, int column) {
        return isSymbolDifferentThanThePrevious(symbol) &&
                grid.isCellFree(row, column);
    }

    private boolean isSymbolDifferentThanThePrevious(final String symbol) {
        return !symbol.equalsIgnoreCase(lastPlacedSymbol);
    }


    public boolean isFinished() {
        return doesLastPLacedSymbolWinsByFillingARow() ||
                doesLastPLacedSymbolWinsByFillingAColumn() ||
                doesLastPlacedSymbolWinsByFillingADiagonal() ||
                isTie();
    }

    private boolean isTie() {
        return grid.isFull();
    }

    private boolean doesLastPLacedSymbolWinsByFillingARow() {
        for (int rowNumber = 0; rowNumber < 3; rowNumber++)
            if (doesLastPlacedSymbolWinsByFillingTheRow(rowNumber))
                return true;
        return false;
    }

    private boolean doesLastPLacedSymbolWinsByFillingAColumn() {
        for (int columnNumber = 0; columnNumber < 3; columnNumber++)
            if (doesLastPlacedSymbolWinsByFillingTheColumn(columnNumber))
                return true;
        return false;
    }

    private boolean doesLastPlacedSymbolWinsByFillingADiagonal() {
        return ((grid.isCellValueEqualsTo(lastPlacedSymbol, 0, 0) &&
                grid.isCellValueEqualsTo(lastPlacedSymbol, 1, 1) &&
                grid.isCellValueEqualsTo(lastPlacedSymbol, 2, 2)) ||
                (grid.isCellValueEqualsTo(lastPlacedSymbol, 0, 2) &&
                        grid.isCellValueEqualsTo(lastPlacedSymbol, 1, 1) &&
                        grid.isCellValueEqualsTo(lastPlacedSymbol, 2, 0)));
    }

    private boolean doesLastPlacedSymbolWinsByFillingTheRow(final int rowNumber) {
        return (grid.isCellValueEqualsTo(lastPlacedSymbol, rowNumber, 0) &&
                grid.isCellValueEqualsTo(lastPlacedSymbol, rowNumber, 1) &&
                grid.isCellValueEqualsTo(lastPlacedSymbol, rowNumber, 2));
    }

    private boolean doesLastPlacedSymbolWinsByFillingTheColumn(final int columnNumber) {
        return (grid.isCellValueEqualsTo(lastPlacedSymbol, 0, columnNumber) &&
                grid.isCellValueEqualsTo(lastPlacedSymbol, 1, columnNumber) &&
                grid.isCellValueEqualsTo(lastPlacedSymbol, 2, columnNumber));
    }

    public String getWinner() {
        if (!isTie())
            return lastPlacedSymbol;
        return null;
    }
}
