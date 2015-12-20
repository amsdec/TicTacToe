package com.alberto.tictactoe.ui;

import com.alberto.tictactoe.*;
import com.alberto.tictactoe.ui.listeners.MoveListener;
import com.alberto.tictactoe.ui.listeners.NewGameButtonListener;
import com.alberto.tictactoe.ui.listeners.SelectCellListener;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    private Game game;
    private Player player1;
    private Player player2;
    private JLabel messages;
    private JButton[][] cells = new JButton[3][3];
    private int play = 1;
    private MoveListener moveListener;

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
        newGame.addActionListener(new NewGameButtonListener(this));
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
        JPanel grid = new JPanel(new GridLayout(3, 3));
        addCellsToGrid(grid);
        return grid;
    }

    private void addCellsToGrid(JPanel grid) {
        for (int row = 0; row < 3; row++)
            for (int column = 0; column < 3; column++)
                addCellToBoard(row, column, grid);
    }

    private void addCellToBoard(int row, int column, JPanel grid) {
        cells[row][column] = new JButton();
        grid.add(cells[row][column]);
        cells[row][column].addActionListener(new SelectCellListener(this, row, column));
    }

    public void play(int row, int column) {
        Player player = getPlayer();
        if (player.playOn(row, column))
            updateBoard(player, row, column);
        else
            messages.setText("You can't play on this cell!!");
    }

    private void updateBoard(Player player, int row, int column) {
        markCell(player, row, column);
        if (game.isFinished())
            finalizeGame(player);
        else
            setNextTurn();
    }

    private void setNextTurn() {
        play++;
        Player player = getPlayer();
        messages.setText("Player " + player.getSymbol() + " turn");
        if (moveListener != null)
            this.moveListener.onMoveMade(this, player);
    }

    private void markCell(Player player, int row, int column) {
        cells[row][column].setEnabled(false);
        cells[row][column].setText(player.getSymbol());
    }

    private void finalizeGame(Player player) {
        if (game.getWinner() != null)
            messages.setText("Player " + game.getWinner().getSymbol() + " wins!!!");
        else
            messages.setText("It's a draw");
        disableCells();
    }

    private void disableCells() {
        for (int row = 0; row < 3; row++)
            for (int column = 0; column < 3; column++)
                cells[row][column].setEnabled(false);
    }

    private int getPlayerTurn() {
        return play % 2;
    }

    private Player getPlayer() {
        int playerTurn = getPlayerTurn();
        if (playerTurn == 1)
            return player1;
        return player2;
    }

    public void addMoveListener(MoveListener moveListener) {
        this.moveListener = moveListener;
    }

    public AbstractGrid getGame() {
        return game.getGrid();
    }

    public Player getPlayer2() {
        return player2;
    }

    public void restartBoard() {
        restartGrid();
        play = 1;
        messages.setText("We are ready!!!");
        initGame();
    }

    private void restartGrid() {
        for (int row = 0; row < 3; row++)
            for (int column = 0; column < 3; column++)
                restartCell(cells[row][column]);
    }

    private void restartCell(JButton jButton) {
        jButton.setText("");
        jButton.setEnabled(true);
    }


}
