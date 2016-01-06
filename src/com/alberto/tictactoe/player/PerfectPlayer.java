package com.alberto.tictactoe.player;

import com.alberto.tictactoe.Cell;
import com.alberto.tictactoe.Player;
import com.alberto.tictactoe.ui.Board;
import com.alberto.tictactoe.ui.listeners.MoveListener;

public class PerfectPlayer implements MoveListener {

    private NextPlayEvaluator nextPlayStrategy;

    public PerfectPlayer() {
        nextPlayStrategy = new MinimaxStrategy();
    }

    @Override
    public void onMoveMade(final Board board, final Player player) {
        if (isItMyTurn(board, player))
            move(board);
    }

    private void move(final Board board) {
        Cell cell = nextPlayStrategy.getBestCellToPlay(board.getPlayer2().getSymbol(), board.getGame());
        if (cell != null)
            board.play(cell.getRow(), cell.getColumn());
    }

    private boolean isItMyTurn(final Board board, final Player player) {
        return player.equals(board.getPlayer2());
    }
}
