package com.alberto.tictactoe.ui.listeners;

import com.alberto.tictactoe.Player;
import com.alberto.tictactoe.ui.Board;

public interface MoveListener {
    void onMoveMade(Board board, Player player);
}
