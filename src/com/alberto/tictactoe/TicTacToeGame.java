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

    private void placeSymbolOnCell(final String symbol, final int row, final int column) {
        grid.setSymbol(symbol, new Cell(row, column));
        lastPlacedSymbol = symbol;
        setWinnerIfFinished();

    }

    private void setWinnerIfFinished() {
        if (isFinished()) {
            isFinished = isFinished();
            winner = getWinner();
        }
    }

    private boolean canPlaceSymbol(final String symbol, final int row, final int column) {
        return isSymbolDifferentThanThePrevious(symbol) &&
                grid.isCellFree(new Cell(row, column));
    }

    private boolean isSymbolDifferentThanThePrevious(final String symbol) {
        return !symbol.equalsIgnoreCase(lastPlacedSymbol);
    }

    public boolean isFinished() {
        return evaluator.isFinished();
    }

    public String getWinner() {
        return this.evaluator.getWinner();
    }

    public AbstractGrid getGrid() {
        return grid;
    }
}
