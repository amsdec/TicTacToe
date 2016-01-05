package com.alberto.tictactoe.player;

import com.alberto.tictactoe.Cell;

public class CellWeight implements Comparable {
    private Cell cell;
    private Integer weight;

    private String symbol;

    public CellWeight(Cell cell, Integer weight) {
        this.cell = cell;
        this.weight = weight;
    }

    public CellWeight(Cell cell, Integer weight, String symbol) {
        this.cell = cell;
        this.weight = weight;
        this.symbol = symbol;
    }

    public Cell getCell() {
        return cell;
    }

    public Integer getWeight() {
        return weight;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public int compareTo(Object otherCellWeight) {
        if (canCompareWIth(otherCellWeight))
            return this.getWeight().compareTo(((CellWeight) otherCellWeight).getWeight());
        return 1;
    }

    private boolean canCompareWIth(Object otherCellWeight) {
        return otherCellWeight != null && otherCellWeight instanceof CellWeight;
    }

    @Override
    public String toString() {
        return symbol + " en " + cell + " = " + weight;
    }
}