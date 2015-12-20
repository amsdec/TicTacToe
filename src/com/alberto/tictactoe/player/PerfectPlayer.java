package com.alberto.tictactoe.player;

import com.alberto.tictactoe.Cell;
import com.alberto.tictactoe.Player;
import com.alberto.tictactoe.ui.Board;
import com.alberto.tictactoe.ui.listeners.MoveListener;

public class PerfectPlayer implements MoveListener {

    private NextPlayEvaluator nextPlayEvaluator;

    public PerfectPlayer() {
        nextPlayEvaluator = new NextPlayEvaluator();
    }

    @Override
    public void onMoveMade(Board board, Player player) {
        if (isMyTurn(board, player))
            move(board);
    }

    private void move(Board board) {
        Cell cell = nextPlayEvaluator.getBestCellToPlay(board.getPlayer2().getSymbol(), board.getGame());
        if (cell != null)
            board.play(cell.getRow(), cell.getColumn());
    }

    private boolean isMyTurn(Board board, Player player) {
        return player.equals(board.getPlayer2());
    }
}
