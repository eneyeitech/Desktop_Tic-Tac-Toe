package tictactoe;

import javax.swing.*;
import java.awt.*;

public class TicTacToe extends JFrame {
    private static final Font FONT_ARIAL_BOLD_12 = new Font("Arial", Font.BOLD, 12);
    private static final Font FONT_ARIAL_BOLD_36 = new Font("Arial", Font.BOLD, 36);

    private static final String[][] BUTTON_NAMES = {
            new String[]{"A3", "B3", "C3"},
            new String[]{"A2", "B2","C2"},
            new String[]{"A1", "B1", "C1"}
    };

    private static final String[] ICONS = {"X", "O", " "};

    private final JButton[][] grid = new JButton[3][3];

    private int turn = 0;
    private final JLabel statusLabel = new JLabel("Game is not started");
    private boolean gameOver = false;

    public TicTacToe() {
        initializeGame();
    }

    private void initializeGame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 340);
        setTitle("Tic Tac Toe");
        setLayout(new BorderLayout());
        Panel gamePanel = new Panel();
        gamePanel.setPreferredSize(new Dimension(300, 270));
        Panel statusPanel = new Panel();
        statusPanel.setSize(300, 30);
        statusPanel.setPreferredSize(new Dimension(300, 30));
        getContentPane().add(gamePanel, BorderLayout.NORTH);
        getContentPane().add(statusPanel, BorderLayout.SOUTH);
        gamePanel.setLayout(new GridLayout(3, 3));
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                JButton button = new JButton(" ");
                grid[y][x] = button;
                button.setName("Button" + BUTTON_NAMES[y][x]);
                button.setFont(FONT_ARIAL_BOLD_36);
                button.addActionListener(e -> {
                    if (!gameOver && button.getText().equals(" ")) {
                        button.setText(ICONS[turn]);
                        turn = (turn + 1) % 2;
                        statusLabel.setText("Game in progress");
                        gameOver = checkForGameOver();
                    }
                });
                gamePanel.add(button);
                button.setFocusPainted(false);
            }
        }
        statusPanel.setLayout(new BorderLayout(2, 2));
        statusLabel.setFont(FONT_ARIAL_BOLD_12);
        statusLabel.setName("LabelStatus");
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetGame());
        resetButton.setFont(FONT_ARIAL_BOLD_12);
        resetButton.setName("ButtonReset");
        statusPanel.add(statusLabel, BorderLayout.WEST);
        statusPanel.add(resetButton, BorderLayout.EAST);
        setVisible(true);
    }

    private void resetGame() {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                grid[y][x].setText(" ");
            }
        }
        statusLabel.setText("Game is not started");
        gameOver = false;
        turn = 0;
    }

    // To check for win assign a power of 2 to each cell on the grid (starting with the top-right corner). For example:
    // -------------------      -------------------
    // | 2^0 | 2^1 | 2^2 |      |   1 |   2 |   4 |
    // |-----------------|      |-----------------|
    // | 2^3 | 2^4 | 2^5 |  or  |   8 |  16 |  32 |
    // |-----------------|      |-----------------|
    // | 2^6 | 2^7 | 2^8 |      |  64 | 128 | 256 |
    // -------------------      -------------------
    //
    // Add up all the rows, columns, and diagonals that constitute a win (e.g. a win across the middle row sums to 56).
    // There are 8 winning combinations with values of (7, 56, 448, 73, 146, 292, 273, and 84).
    //
    // Calculate an 'X' or 'O' win by getting the sum of each 'X' and 'O' using the power of 2 value for the cell
    // the 'X' or 'O' is in. For example:
    // -------------------
    // |  X  |  O  |   X |    Sum X: 1 + 4 + 16 + 256 = 277
    // |-----------------|    Sum O: 2 + 8 + 128 = 138
    // |  O  |  X  |     |
    // |-----------------|
    // |     |  O  |  X  |
    // -------------------
    //
    // Do a bit-wise AND (&) with each of the sums to each winning combination.  If the AND result is equal to the
    // winning combination, then a win was found.  In the example above:
    // When a bit-wise AND is done with X's value of 277 and the diagonal value of 273 the result is 273:
    //
    //         000100010101 = 277
    //       & 000100010001 = 273
    //         ------------------
    //         000100010001 = 273
    //
    private boolean checkForGameOver() {
        int occupiedCellCount = 0;
        int[] wins = { 7, 56, 448, 73, 146, 292, 273, 84};
        int sumX = 0;
        int sumO = 0;
        int index = 0;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (!grid[y][x].getText().equals(" ")) {
                    occupiedCellCount++;
                    if (grid[y][x].getText().equals("X")) {
                        sumX += (int)Math.pow(2, index);
                    } else {
                        sumO += (int)Math.pow(2, index);
                    }
                }
                index++;
            }
        }
        for (int win : wins) {
            if ((win & sumX) == win) {
                statusLabel.setText("X wins");
                return true;
            }
            if ((win & sumO) == win) {
                statusLabel.setText("O wins");
                return true;
            }
        }
        if (occupiedCellCount == 9) {
            statusLabel.setText("Draw");
            return true;
        }
        return false;
    }
}