package com.alberto.tictactoe.player;

import com.alberto.tictactoe.Cell;
import com.alberto.tictactoe.Player;
import com.alberto.tictactoe.ui.Board;
import com.alberto.tictactoe.ui.listeners.MoveListener;

public class PerfectPlayer implements MoveListener {
    NextPlayEvaluator nextPlayEvaluator = new NextPlayEvaluator();

    public PerfectPlayer() {
    }

    public void playOn(Cell cell) {
    }

    @Override
    public void moveMade(Board board, Player player) {
        if (!player.equals(board.getPlayer2())) {
            Cell cell = nextPlayEvaluator.getBestCellToPlay("O", board.getGame());
            System.out.println("Debo de mover en " + cell);
            if (cell != null) {
                board.play(board.getPlayer2(), cell.getRow(), cell.getColumn());
            }
        }
    }
}
