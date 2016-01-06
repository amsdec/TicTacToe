package com.alberto.tictactoe.ui.listeners;

import com.alberto.tictactoe.ui.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectCellListener implements ActionListener {
    private int row;
    private int column;
    private Board board;

    public SelectCellListener(final Board board, final int row, final int column) {
        this.row = row;
        this.column = column;
        this.board = board;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        board.play(row, column);
    }
}