package com.alberto.tictactoe.ui;

import com.alberto.tictactoe.ui.listeners.OneVsCPUActionListener;
import com.alberto.tictactoe.ui.listeners.OneVsOneActionListener;

import javax.swing.*;
import java.awt.*;

public class GameSelection extends JFrame {

    public GameSelection() {
        super("TiC TaC Toe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        setContentPane(panel);
        panel.add(createActionPanel(), BorderLayout.PAGE_END);
    }

    private JPanel createActionPanel() {
        JPanel actionsPanel = new JPanel();
        actionsPanel.add(create1vs1Button());
        actionsPanel.add(createVsCpuButton());
        return actionsPanel;
    }

    private JButton create1vs1Button() {
        JButton game = new JButton("1 vs 1");
        game.addActionListener(new OneVsOneActionListener());
        return game;
    }

    private JButton createVsCpuButton() {
        JButton game = new JButton("1 vs CPU");
        game.addActionListener(new OneVsCPUActionListener());
        return game;
    }
}
