package com.alberto.tictactoe.ui;

import com.alberto.tictactoe.Game;
import com.alberto.tictactoe.Player;
import com.alberto.tictactoe.PlayerFactory;
import com.alberto.tictactoe.TicTacToeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel {

    private Game game;
    private Player player1;
    private Player player2;
    private JLabel messages;
    private JButton[][] cells = new JButton[3][3];
    private int play = 1;

    public Board() {
        super(new BorderLayout());
        initBoard();
        initGame();
    }

    private void initBoard() {
        add(createGrid(), BorderLayout.PAGE_START);
        add(createMessagesPanel(), BorderLayout.CENTER);
        add(createActionPanel(), BorderLayout.PAGE_END);
    }

    private JScrollPane createMessagesPanel() {
        messages = new JLabel("We are ready!!!");
        return new JScrollPane(messages);
    }

    private JPanel createActionPanel() {
        JPanel actionsPanel = new JPanel();
        actionsPanel.add(createNewGameButton());
        return actionsPanel;
    }

    private JButton createNewGameButton() {
        JButton newGame = new JButton("New game");
        newGame.addActionListener(new newGameListener());
        return newGame;
    }

    private void initGame() {
        game = new Game(new TicTacToeGame());
        initPlayers();
        joinPlayersToGame();
    }

    private void joinPlayersToGame() {
        game.addPlayer(player1);
        game.addPlayer(player2);
    }

    private void initPlayers() {
        player1 = PlayerFactory.getPlayer("X");
        player2 = PlayerFactory.getPlayer("O");
    }

    private JPanel createGrid() {
        JPanel grid = new JPanel(new GridLayout(3,3));
        addCellsToGrid(grid);
        return grid;
    }

    private void addCellsToGrid(JPanel grid) {
        for(int row = 0; row < 3; row++)
            for(int column = 0; column < 3; column++)
                addCellToBoard(row, column, grid);
    }

    private void addCellToBoard(int row, int column, JPanel grid) {
        cells[row][column] = new JButton();
        grid.add(cells[row][column]);
        cells[row][column].addActionListener(new SelectCellListener(row, column));
    }

    class SelectCellListener implements ActionListener {
        private int row;
        private int column;

        public SelectCellListener(int row, int column) {
            this.row = row;
            this.column =column;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Player player = getPlayer(getPlayerTurn());
            play(e, player);
        }

        private void play(ActionEvent e, Player player) {
            if(player.playOn(row, column))
                updateBoard(e, player);
            else
                messages.setText("You can't play on this cell!!");
        }

        private void updateBoard(ActionEvent e, Player player) {
            markCell(e, player);
            if (game.isFinished())
                finalizeGame(player);
            else
                setNextTurn();
        }

        private void setNextTurn() {
            play++;
            Player player = getPlayer(getPlayerTurn());
            messages.setText("Player " + player.getSymbol() + " turn");
        }

        private void markCell(ActionEvent e, Player player) {
            ((JButton)e.getSource()).setEnabled(false);
            cells[row][column].setText(player.getSymbol());
        }

        private void finalizeGame(Player player) {
            messages.setText("Player " + player.getSymbol() + " wins!!!");
            disableCells();
        }

        private void disableCells() {
            for(int row = 0; row < 3; row++)
                for(int column = 0; column < 3; column++)
                    cells[row][column].setEnabled(false);
        }

        private int getPlayerTurn() {
            return play %2;
        }

        private Player getPlayer(int playerTurn) {
            if(playerTurn == 1)
                return player1;
            return player2;
        }
    }

    class newGameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            restarBoard();
            initGame();
        }

        private void restarBoard() {
            restartGrid();
            play = 1;
            messages.setText("We are ready!!!");
        }

        private void restartGrid() {
            for(int row = 0; row < 3; row++)
                for(int column = 0; column < 3; column++)
                    restartCell(cells[row][column]);
        }

        private void restartCell(JButton jButton) {
            jButton.setText("");
            jButton.setEnabled(true);
        }
    }
    public static void main(String [] args) {
        JFrame frame = new JFrame("Tic tac toe");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Board board = new Board();
        frame.setContentPane(board);
        frame.pack();
        frame.setVisible(true);
    }

}
