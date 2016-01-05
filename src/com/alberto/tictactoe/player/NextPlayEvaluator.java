package com.alberto.tictactoe.player;

import com.alberto.tictactoe.AbstractGrid;
import com.alberto.tictactoe.Cell;

import java.util.List;

public interface NextPlayEvaluator {
    Cell getBestCellToPlay(String symbol, AbstractGrid grid);
}
