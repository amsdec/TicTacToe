package com.alberto.tictactoe.player;

import com.alberto.tictactoe.AbstractGrid;
import com.alberto.tictactoe.Cell;
import com.alberto.tictactoe.FinishedGameEvaluator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinimaxStrategy implements NextPlayEvaluator{
    public AbstractGrid grid;

    public MinimaxStrategy() {
    }

    @Override
    public Cell getBestCellToPlay(String symbol, AbstractGrid grid) {
        System.out.println("Se va a buscar el mejor tiro para el jugador " + symbol + " en el estado de juego ");
        System.out.println(grid);
        List<Cell> emptyCells = this.getEmptyCells(grid);
        List<CellWeight> cellWeights = new ArrayList<CellWeight>();
        for (Cell emptyCell : emptyCells) {
            System.out.println("Una posible tirada es en " + emptyCell);
            cellWeights.add(new CellWeight(emptyCell, getScore(grid, symbol, emptyCell), symbol));
        }
        System.out.println("De entre " + cellWeights + " se va a escoger el que convenga para " + symbol);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        if (symbol.equals("O")) {
            return Collections.max(cellWeights).getCell();
        } else {
            return Collections.min(cellWeights).getCell();
        }
    }

    public int getScore(AbstractGrid grid, String symbol, Cell cell) {
        System.out.println("Se va a obtener el score de " + symbol + " tirando en " + cell + " en el estado de juego");
        //System.out.println(grid);
        AbstractGrid nextGrid = getNextGrid(grid, cell, symbol);
        //System.out.println("Haciendo el movimiento el grid quedaría así");
        System.out.println(nextGrid);
        Integer score = getScore(nextGrid);
        System.out.println("Y el score sería " + score);
        if (score != null) {
            return score;
        } else {
            List<Cell> emptyCells = this.getEmptyCells(nextGrid);
            List<CellWeight> cellWeights = new ArrayList<CellWeight>();
            for (Cell emptyCell : emptyCells) {
                cellWeights.add(new CellWeight(emptyCell, getScore(nextGrid, symbol.equals("X") ? "O" : "X", emptyCell), symbol.equals("X") ? "O" : "X"));
            }
            System.out.println("En el estado");
            System.out.println(nextGrid);
            System.out.println("De entre " + cellWeights + " se va a escoger el que convenga para " + (symbol.equals("X") ? "O" : "X"));
            System.out.println("----------------------------------------------------------");
            if (cellWeights.size() > 0) {
                if (cellWeights.get(0).getSymbol().equals("O")) {
                    System.out.println("Y es " + Collections.max(cellWeights).getCell());
                    return Collections.max(cellWeights).getWeight();
                } else {
                    System.out.println("Y es " + Collections.min(cellWeights).getCell());
                    return Collections.min(cellWeights).getWeight();
                }
            } else {
                return 0;
            }
        }
    }

    public AbstractGrid getNextGrid(AbstractGrid grid, Cell cell, String symbol) {
        AbstractGrid nextGrid = new AbstractGrid();
        for (int row = 0; row < 3; row++)
            for (int column = 0; column < 3; column++)
                if(grid.getCellValue(new Cell(row, column))!= null)
                nextGrid.setSymbol(grid.getCellValue(new Cell(row, column)), new Cell(row, column));
        nextGrid.setSymbol(symbol, cell);
        return nextGrid;
    }

    public Integer getScore(AbstractGrid grid) {
        FinishedGameEvaluator evaluator = new FinishedGameEvaluator(grid);
        if (evaluator.isFinished())
            return getScoreForFinishedGame(evaluator);
        return null;
    }

    public int getScoreForFinishedGame(FinishedGameEvaluator evaluator) {
        if (evaluator.getWinner() == null)
            return 0;
        else
            return getScoreWhenThereIsAWinner(evaluator);
    }

    public int getScoreWhenThereIsAWinner(FinishedGameEvaluator evaluator) {
        if (evaluator.getWinner().equals("O"))
            return 1;
        else
            return -1;
    }

    public List<Cell> getEmptyCells(AbstractGrid grid) {
        List<Cell> emptyCells = new ArrayList<Cell>();
        addCellsToList(grid, emptyCells);
        return emptyCells;
    }

    public void addCellsToList(AbstractGrid grid, List<Cell> emptyCells) {
        for (int row = 0; row < 3; row++)
            for (int column = 0; column < 3; column++)
                addCellIfEmpty(grid, emptyCells, row, column);
    }

    public void addCellIfEmpty(AbstractGrid grid, List<Cell> emptyCells, int row, int column) {
        if (grid.isCellFree(new Cell(row, column)))
            emptyCells.add(new Cell(row, column));
    }

}