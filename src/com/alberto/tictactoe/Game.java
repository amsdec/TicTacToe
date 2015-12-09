package com.alberto.tictactoe;

public class Game {
    private TicTacToeGame ticTacToeGame;
    private Player player1;
    private Player player2;

    public Game(final TicTacToeGame ticTacToeGame){
        this.ticTacToeGame = ticTacToeGame;
    }

    public void addPlayer(final Player player) {
        if(player1 == null) {
            player1 = player;
            player1.setGame(ticTacToeGame);
        }
        else {
            if (player1.equals(player)){
                throw  new PlayerNotAllowedException("There is a " + player.getSymbol()+ " player yet");
            }
            player2 = player;
            player2.setGame(ticTacToeGame);
        }
    }

    public boolean isFinished() {
        return this.ticTacToeGame.isFinished();
    }

    public Player getWinner() {
        if(this.ticTacToeGame.getWinner() == null)
            return null;
        return PlayerFactory.getPlayer(this.ticTacToeGame.getWinner());
    }
}
