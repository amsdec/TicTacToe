package com.alberto.tictactoe.player;

import com.alberto.tictactoe.AbstractGrid;
import com.alberto.tictactoe.Cell;
import com.alberto.tictactoe.FinishedGameEvaluator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinimaxStrategy implements NextPlayEvaluator {
    private static final String CPU_PLAYER_SYMBOL = "O";
    private static final String HUMAN_PLAYER_SYMBOL = "X";
    private static final int SCORE_FOR_DRAW = 0;
    private static final int SCORE_FOR_CPU_AS_WINNER = 1;
    private static final int SCORE_FOR_CPU_AS_LOSER = -1;


    @Override
    public Cell getBestCellToPlay(final String symbol, final AbstractGrid grid) {
        return getBestCellToPlayForSymbolOnEmptyCells(symbol, getEmptyCellWeights(symbol, grid));
    }

    private Cell getBestCellToPlayForSymbolOnEmptyCells(final String symbol, final List<CellWeight> cellWeights) {
        if (symbol.equals(CPU_PLAYER_SYMBOL))
            return Collections.max(cellWeights).getCell();
        return Collections.min(cellWeights).getCell();
    }

    private List<CellWeight> getEmptyCellWeights(final String symbol, final AbstractGrid grid) {
        List<Cell> emptyCells = this.getEmptyCells(grid);
        return getWeightsForEmptyCells(symbol, grid, emptyCells);
    }

    private List<CellWeight> getWeightsForEmptyCells(final String symbol, final AbstractGrid grid, final List<Cell> emptyCells) {
        List<CellWeight> cellWeights = new ArrayList<CellWeight>();
        for (Cell emptyCell : emptyCells)
            cellWeights.add(new CellWeight(emptyCell, getScore(grid, symbol, emptyCell), symbol));
        return cellWeights;
    }

    private int getScore(final AbstractGrid grid, final String symbol, final Cell cell) {
        return getScore(symbol, getNextGrid(grid, cell, symbol), getScore(getNextGrid(grid, cell, symbol)));
    }

    private int getScore(final String symbol, final AbstractGrid nextGrid, final Integer score) {
        if (isFinishedGame(score))
            return score;
        return getScoreForNonFinishedGame(symbol, nextGrid);
    }

    private boolean isFinishedGame(final Integer score) {
        return score != null;
    }

    private int getScoreForNonFinishedGame(final String symbol, final AbstractGrid nextGrid) {
        return getConvenientScoreForPlayerInTurn(getEmptyCellWeights(getNextTurnSymbol(symbol), nextGrid));
    }

    private int getConvenientScoreForPlayerInTurn(final List<CellWeight> cellWeights) {
        if (areThereAvailableCells(cellWeights))
            return getConvenientScoreForWinner(cellWeights);
        return SCORE_FOR_DRAW;
    }

    private int getConvenientScoreForWinner(final List<CellWeight> cellWeights) {
        if (isCPUWinner(cellWeights))
            return Collections.max(cellWeights).getWeight();
        return Collections.min(cellWeights).getWeight();
    }

    private boolean isCPUWinner(final List<CellWeight> cellWeights) {
        return cellWeights.get(0).getSymbol().equals(CPU_PLAYER_SYMBOL);
    }

    private boolean areThereAvailableCells(List<CellWeight> cellWeights) {
        return cellWeights.size() > 0;
    }

    private String getNextTurnSymbol(String symbol) {
        return symbol.equals(HUMAN_PLAYER_SYMBOL) ? CPU_PLAYER_SYMBOL : HUMAN_PLAYER_SYMBOL;
    }

    private AbstractGrid getNextGrid(final AbstractGrid grid, final Cell cell, final String symbol) {
        AbstractGrid nextGrid = new AbstractGrid();
        cloneGrid(grid, nextGrid);
        nextGrid.setSymbol(symbol, cell);
        return nextGrid;
    }

    private void cloneGrid(final AbstractGrid grid, final AbstractGrid nextGrid) {
        for (int row = 0; row < 3; row++)
            for (int column = 0; column < 3; column++)
                if (grid.getCellValue(new Cell(row, column)) != null)
                    nextGrid.setSymbol(grid.getCellValue(new Cell(row, column)), new Cell(row, column));
    }

    public Integer getScore(final AbstractGrid grid) {
        FinishedGameEvaluator evaluator = new FinishedGameEvaluator(grid);
        if (evaluator.isFinished())
            return getScoreForFinishedGame(evaluator);
        return null;
    }

    private int getScoreForFinishedGame(final FinishedGameEvaluator evaluator) {
        if (evaluator.getWinner() == null)
            return SCORE_FOR_DRAW;
        return getScoreWhenThereIsAWinner(evaluator);
    }

    private int getScoreWhenThereIsAWinner(final FinishedGameEvaluator evaluator) {
        if (isCPUWinner(evaluator))
            return SCORE_FOR_CPU_AS_WINNER;
        return SCORE_FOR_CPU_AS_LOSER;
    }

    private boolean isCPUWinner(final FinishedGameEvaluator evaluator) {
        return evaluator.getWinner().equals(CPU_PLAYER_SYMBOL);
    }

    public List<Cell> getEmptyCells(final AbstractGrid grid) {
        List<Cell> emptyCells = new ArrayList<Cell>();
        addCellsToList(grid, emptyCells);
        return emptyCells;
    }

    private void addCellsToList(final AbstractGrid grid, final List<Cell> emptyCells) {
        for (int row = 0; row < 3; row++)
            for (int column = 0; column < 3; column++)
                addCellIfEmpty(grid, emptyCells, row, column);
    }

    private void addCellIfEmpty(final AbstractGrid grid, final List<Cell> emptyCells, final int row, final int column) {
        if (grid.isCellFree(new Cell(row, column)))
            emptyCells.add(new Cell(row, column));
    }

}