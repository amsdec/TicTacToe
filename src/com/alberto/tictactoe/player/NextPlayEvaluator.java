package com.alberto.tictactoe.player;

import com.alberto.tictactoe.AbstractGrid;
import com.alberto.tictactoe.Cell;
import com.alberto.tictactoe.FinishedGameEvaluator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NextPlayEvaluator {

    public NextPlayEvaluator() {
    }

    public List<Cell> getPossibleCellsToPlayFromGrid(AbstractGrid grid) {
        List<Cell> emptyCells = new ArrayList<Cell>();
        for (int row = 0; row < 3; row++)
            for (int column = 0; column < 3; column++)
                if (grid.isCellFree(new Cell(row, column)))
                    emptyCells.add(new Cell(row, column));
        return emptyCells;
    }

    public AbstractGrid getNextGridState(AbstractGrid grid, Cell cell, String symbol) {
        AbstractGrid nextGrid = new AbstractGrid();
        fillNextGridFromActualAndNewMove(grid, cell, symbol, nextGrid);
        return nextGrid;
    }

    private void fillNextGridFromActualAndNewMove(AbstractGrid grid, Cell cell, String symbol, AbstractGrid nextGrid) {
        for (int row = 0; row < 3; row++)
            for (int column = 0; column < 3; column++)
                nextGrid.setSymbol(grid.getCellValue(new Cell(row, column)), new Cell(row, column));
        nextGrid.setSymbol(symbol, cell);
    }

    public int getScoreForSymbolOnCell(String symbol, Cell cell, AbstractGrid grid) {
        return getScoreForSymbolOnCell(symbol, symbol, cell, grid, 0);
    }

    public int getScoreForSymbolOnCell(String originalSymbol, String symbol, Cell cell, AbstractGrid grid, int score) {
        AbstractGrid nextGridState = getNextGridState(grid, cell, symbol);
        FinishedGameEvaluator finishedGameEvaluator = new FinishedGameEvaluator(nextGridState);
        return getScore(originalSymbol, symbol, score, nextGridState, finishedGameEvaluator);
    }

    private int getScore(String originalSymbol, String symbol, int score, AbstractGrid nextGridState, FinishedGameEvaluator finishedGameEvaluator) {
        if (finishedGameEvaluator.isFinished(symbol))
            return getScoreForFinishedGame(originalSymbol, score, finishedGameEvaluator);
        else
            return getScoreForEmptyCells(originalSymbol, symbol, score, nextGridState);
    }

    private int getScoreForFinishedGame(String originalSymbol, int score, FinishedGameEvaluator finishedGameEvaluator) {
        if (finishedGameEvaluator.getWinner() == null)
            return 0;
        else return getScoreWhenAPlayerWins(originalSymbol, score, finishedGameEvaluator);
    }

    private int getScoreWhenAPlayerWins(String originalSymbol, int score, FinishedGameEvaluator finishedGameEvaluator) {
        if (finishedGameEvaluator.getWinner().equals(originalSymbol))
            return score + 1;
        else
            return score - 1;
    }

    private int getScoreForEmptyCells(String originalSymbol, String symbol, int score, AbstractGrid nextGridState) {
        for (Cell emptyCell : getPossibleCellsToPlayFromGrid(nextGridState))
            score = getScoreForSymbolOnCell(originalSymbol, symbol.equals("X") ? "O" : "X", emptyCell, nextGridState, score);
        return score;
    }

    public Cell getBestCellToPlay(String symbol, AbstractGrid grid) {
        List<Cell> emptyCells = getPossibleCellsToPlayFromGrid(grid);
        List cellWeights = getCellWeights(symbol, grid, emptyCells);
        if (cellWeights.isEmpty())
            return null;
        return Collections.max(getCellWeights(symbol, grid, emptyCells)).getCell();
    }

    private List<CellWeight> getCellWeights(String symbol, AbstractGrid grid, List<Cell> emptyCells) {
        List<CellWeight> cellWeights = new ArrayList<CellWeight>();
        for (Cell emptyCell : emptyCells)
            cellWeights.add(new CellWeight(emptyCell, getScoreForSymbolOnCell(symbol, emptyCell, grid)));
        return cellWeights;
    }
}