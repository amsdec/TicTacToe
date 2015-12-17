package com.alberto.tictactoe.ui;

import com.alberto.tictactoe.player.PerfectPlayer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OneVsCPUActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        PerfectPlayer perfectPlayer = new PerfectPlayer();

        ((JFrame) ((JButton) e.getSource()).getRootPane().getParent()).dispose();
        JFrame frame = new JFrame("Tic tac toe");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Board board = new Board();
        board.addMoveListener(perfectPlayer);
        frame.setContentPane(board);
        frame.pack();
        frame.setVisible(true);

    }

}
