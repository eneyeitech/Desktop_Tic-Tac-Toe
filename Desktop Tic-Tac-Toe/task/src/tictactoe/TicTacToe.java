package tictactoe;

import javax.swing.*;
import java.awt.*;

public class TicTacToe extends JFrame {
    private static final String PLAYER_X = "X";
    private static final String PLAYER_O = "O";

    private GameStatus gameStatus = GameStatus.NOT_STARTED;

    private final Board board;
    private final StatusBar statusBar;

    private Player player1 = new Player(PlayerType.HUMAN, PLAYER_X);
    private Player player2 = new Player(PlayerType.HUMAN, PLAYER_O);

    public TicTacToe() {
        super("Tic Tac Toe");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 500);
        setLocationRelativeTo(null);

        Toolbar toolbar = new Toolbar(this);
        this.board = new Board(this);
        this.statusBar = new StatusBar(this.board);
        add(toolbar, BorderLayout.NORTH);
        add(this.board, BorderLayout.CENTER);
        add(this.statusBar, BorderLayout.SOUTH);

        setVisible(true);
        setLayout(new BorderLayout());
    }

    Player getPlayer1() {
        return this.player1;
    }

    Player getPlayer2() {
        return this.player2;
    }

    GameStatus getGameStatus() {
        return this.gameStatus;
    }

    void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
        this.statusBar.setStatus(gameStatus);
    }

    boolean isGameFinished() {
        return this.gameStatus != GameStatus.NOT_STARTED && this.gameStatus != GameStatus.IN_PROGRESS;
    }

    void reset() {
        this.board.reset();
        this.setGameStatus(GameStatus.NOT_STARTED);
    }

    void start() {
        this.board.start();
        this.setGameStatus(GameStatus.IN_PROGRESS);
    }
}