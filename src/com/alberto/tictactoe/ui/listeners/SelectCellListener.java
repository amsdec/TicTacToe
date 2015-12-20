package com.alberto.tictactoe.ui.listeners;

import com.alberto.tictactoe.ui.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectCellListener implements ActionListener {
    private int row;
    private int column;
    private Board board;

    public SelectCellListener(Board board, int row, int column) {
        this.row = row;
        this.column = column;
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.play(row, column);
    }
}