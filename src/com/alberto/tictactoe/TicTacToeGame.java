package com.alberto.tictactoe;

public class TicTacToeGame {

    private AbstractGrid grid = new AbstractGrid();
    private FinishedGameEvaluator evaluator = new FinishedGameEvaluator(grid);
    private String lastPlacedSymbol = null;
    private String winner;
    private boolean isFinished;

    public boolean placeSymbol(final String symbol, final int row, final int column) {
        if (!canPlaceSymbol(symbol, row, column) || isFinished)
            return false;
        placeSymbolOnCell(symbol, row, column);
        return true;
    }

    private void placeSymbolOnCell(String symbol, int row, int column) {
        grid.setSymbol(symbol, row, column);
        lastPlacedSymbol = symbol;
        if (isFinished()) {
            isFinished = isFinished();
            winner = getWinner();
        }

    }

    private boolean canPlaceSymbol(String symbol, int row, int column) {
        return isSymbolDifferentThanThePrevious(symbol) &&
                grid.isCellFree(row, column);
    }

    private boolean isSymbolDifferentThanThePrevious(final String symbol) {
        return !symbol.equalsIgnoreCase(lastPlacedSymbol);
    }

    public boolean isFinished() {
        return evaluator.isFinished(lastPlacedSymbol);
    }

    public String getWinner() {
        return this.evaluator.getWinner();
    }
}
