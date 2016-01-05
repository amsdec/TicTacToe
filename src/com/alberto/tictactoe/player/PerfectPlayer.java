package com.alberto.tictactoe.player;

import com.alberto.tictactoe.Cell;
import com.alberto.tictactoe.Player;
import com.alberto.tictactoe.ui.Board;
import com.alberto.tictactoe.ui.listeners.MoveListener;

public class PerfectPlayer implements MoveListener {

    private NextPlayEvaluator badStrategy;

    public PerfectPlayer() {
        badStrategy = new MinimaxStrategy();
    }

    @Override
    public void onMoveMade(Board board, Player player) {
        if (isItMyTurn(board, player))
            move(board);
    }

    private void move(Board board) {
        Cell cell = badStrategy.getBestCellToPlay(board.getPlayer2().getSymbol(), board.getGame());
        if (cell != null)
            board.play(cell.getRow(), cell.getColumn());
    }

    private boolean isItMyTurn(Board board, Player player) {
        return player.equals(board.getPlayer2());
    }
}
