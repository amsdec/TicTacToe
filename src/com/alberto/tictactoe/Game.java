package com.alberto.tictactoe;

public class Game {
    private TicTacToeGame ticTacToeGame;
    private Player player1;
    private Player player2;

    public Game(final TicTacToeGame ticTacToeGame) {
        this.ticTacToeGame = ticTacToeGame;
    }

    public void addPlayer(final Player player) {
        if (player1 == null)
            joinPlayerOne(player);
        else {
            throwExceptionIfThePlayerIsAlreadyJoined(player);
            joinPlayerTwo(player);
        }
    }

    private void throwExceptionIfThePlayerIsAlreadyJoined(Player player) {
        if (player1.equals(player)) {
            throw new PlayerNotAllowedException("There is a " + player.getSymbol() + " player yet");
        }
    }

    private void joinPlayerTwo(Player player) {
        player2 = player;
        player2.setGame(ticTacToeGame);
    }

    private void joinPlayerOne(Player player) {
        player1 = player;
        player1.setGame(ticTacToeGame);
    }

    public boolean isFinished() {
        return this.ticTacToeGame.isFinished();
    }

    public Player getWinner() {
        if (isDraw())
            return null;
        return PlayerFactory.getPlayer(this.ticTacToeGame.getWinner());
    }

    private boolean isDraw() {
        return this.ticTacToeGame.getWinner() == null;
    }

    public AbstractGrid getGrid() {
        return this.ticTacToeGame.getGrid();
    }
}
