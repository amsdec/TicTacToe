package com.alberto.tictactoe.player;

import com.alberto.tictactoe.Cell;

class CellWeight implements Comparable {
    private Cell cell;
    private Integer weight;

    public CellWeight(Cell cell, Integer weight) {
        this.cell = cell;
        this.weight = weight;
    }

    public Cell getCell() {
        return cell;
    }

    public Integer getWeight() {
        return weight;
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
}