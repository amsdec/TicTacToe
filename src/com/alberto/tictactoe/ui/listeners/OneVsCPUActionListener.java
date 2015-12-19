package com.alberto.tictactoe.ui.listeners;

import com.alberto.tictactoe.player.PerfectPlayer;
import com.alberto.tictactoe.ui.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OneVsCPUActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        closeMenu(e);
        initAndShowBoard();

    }

    private void initAndShowBoard() {
        showBoard(initBoard());
    }

    private void showBoard(Board board) {
        JFrame frame = new JFrame("Tic tac toe");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(board);
        frame.pack();
        frame.setVisible(true);
    }

    private Board initBoard() {
        PerfectPlayer perfectPlayer = new PerfectPlayer();
        Board board = new Board();
        board.addMoveListener(perfectPlayer);
        return board;
    }

    private void closeMenu(ActionEvent e) {
        ((JFrame) ((JButton) e.getSource()).getRootPane().getParent()).dispose();
    }

}
