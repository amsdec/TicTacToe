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

    private Game game = new Game(new TicTacToeGame());
    private Player player1 = PlayerFactory.getPlayer("X");
    private Player player2 = PlayerFactory.getPlayer("O");
    private JLabel messages = new JLabel("Player 1 turn");
    private JButton[][] cells = new JButton[3][3];
    private int play = 1;
    private JPanel buttonPane = new JPanel(new GridLayout(3,3));
    private JScrollPane listScrollPane = new JScrollPane(messages);

    public Board() {
        super(new BorderLayout());
        initBoard();
        initGame();
    }

    private void initBoard() {
        createGrid();
        add(buttonPane, BorderLayout.PAGE_START);
        add(listScrollPane, BorderLayout.PAGE_END);
    }

    private void initGame() {
        game.addPlayer(player1);
        game.addPlayer(player2);
    }

    private void createGrid() {
        for(int row = 0; row < 3; row++){
            for(int column = 0; column < 3; column++){
                addCellToBoard(row, column);
            }
        }
    }

    private void addCellToBoard(int row, int column) {
        cells[row][column] = new JButton();
        buttonPane.add(cells[row][column]);
        cells[row][column].setActionCommand(row + "-" + column);
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
            if(player.playOn(row, column)){
                updateBoard(e, player);
            } else {
                messages.setText("You can't play on this cell!!");
            }
        }

        private void updateBoard(ActionEvent e, Player player) {
            markCell(e, player);
            play++;
            messages.setText("Player " + ((getPlayerTurn()) + 1)+ " turn");
            if (game.isFinished())
                finalizeGame(player);
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
            for(int row = 0; row < 3; row++){
                for(int column = 0; column < 3; column++){
                    cells[row][column].setEnabled(false);
                }
            }
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

    public static void main(String [] args) {
        JFrame frame = new JFrame("Tic tac toe");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(100, 100);
        Board board = new Board();
        board.setSize(100, 100);
        frame.setContentPane(board);
        frame.pack();
        frame.setVisible(true);
    }

}
