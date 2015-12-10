package com.alberto.tictactoe;

public class Player {
    private String symbol;
    private TicTacToeGame game;

    public void setGame(final TicTacToeGame game) {
        this.game = game;
    }

    public Player(final String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public boolean playOn(final int row, final int column) {
        return this.game.placeSymbol(symbol, row, column);
    }

}
