package com.alberto.tictactoe.player;

import com.alberto.tictactoe.Cell;

class CellWeight implements Comparable {
    private Cell cell;
    private Integer weight;

    public Cell getCell() {
        return cell;
    }

    public Integer getWeight() {
        return weight;
    }

    public CellWeight(Cell cell, Integer weight) {
        this.cell = cell;
        this.weight = weight;
    }

    @Override
    public int compareTo(Object otherCellWeight) {
        if (otherCellWeight != null && otherCellWeight instanceof CellWeight)
            return this.getWeight().compareTo(((CellWeight) otherCellWeight).getWeight());
        return 1;
    }
}