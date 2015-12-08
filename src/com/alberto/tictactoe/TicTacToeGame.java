package com.alberto.tictactoe;

public class TicTacToeGame {

    String[][] grid = new String[3][3];
    public boolean placeSymbol(final String symbol, final int row, final int column) {
        if (grid[row][column] != null) {
            return false;
        }
        grid[row][column] = symbol;
        return true;
    }

    public boolean isFinished() {
        return false;
    }
}
