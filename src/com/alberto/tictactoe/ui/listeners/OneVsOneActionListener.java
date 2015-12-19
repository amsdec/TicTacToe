package com.alberto.tictactoe.ui.listeners;

import com.alberto.tictactoe.ui.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OneVsOneActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        closeMenu(e);
        showBoard();
    }

    private void showBoard() {
        Board board = new Board();
        showBoard(board);
    }

    private void showBoard(Board board) {
        JFrame frame = new JFrame("Tic tac toe");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(board);
        frame.pack();
        frame.setVisible(true);
    }

    private void closeMenu(ActionEvent e) {
        ((JFrame) ((JButton) e.getSource()).getRootPane().getParent()).dispose();
    }
}