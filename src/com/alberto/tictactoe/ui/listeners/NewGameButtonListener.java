package com.alberto.tictactoe.ui.listeners;

import com.alberto.tictactoe.ui.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameButtonListener implements ActionListener {

    private Board board;

    public NewGameButtonListener(final Board board) {
        this.board = board;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        board.restartBoard();
    }
}