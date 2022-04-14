package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.text.MessageFormat;
import java.util.Random;

public class Controller {
    private static final MessageFormat TURN_MSG = new MessageFormat("The turn of {0} Player ({1})");
    private static final MessageFormat WIN_MSG  = new MessageFormat("The {0} Player ({1}) wins");
    private static       int           move     = 0;
    private static       boolean       gameOver = false;
    private static       GUI           gui;

    /**
     * Add a private constructor so that we can't mistakenly create a Controller object via new.
     */
    private Controller() {

    }

    static void initialize(final GUI gui) {
        Controller.gui = gui;
    }

    static void playCell(final Cell cell) {
        cell.setCellText(move % 2 == 0 ? "X" : "O");
        cell.setEnabled(false);
        move++;
        gui.setStatus(getTurnMessage());

        checkState();
        if (!gameOver) {
            if (move == 9) {
                gui.setStatus("Draw");
                endGame();
            } else {
                play();
            }
        }
    }

    /**
     * Checks to see if we have a winner.
     * <p>
     * Loops through each row, column and diagonal calling isThreeInARow to check for a winner. If we do have a winner,
     * update the status message and change the winning line background colors to highlight it.
     */
    private static void checkState() {
        final int[][] INDICES = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8},
                {2, 4, 6}};

        for (int[] index : INDICES) {
            if (gui.isThreeInARow(index)) {
                endGame();
                var player = (move - 1) % 2;
                if ("X".equals(gui.getCell(index[0]).getCellText())) {
                    gui.setStatus(WIN_MSG.format(new Object[]{gui.getButtonText(player), "X"}));
                } else {
                    gui.setStatus(WIN_MSG.format(new Object[]{gui.getButtonText(player), "O"}));
                }
                for (int i : index) {
                    gui.getCell(i).setBackground(Color.GRAY);
                }
            }
        }
    }

    private static void endGame() {
        gameOver = true;
        gui.enableCells(false);
    }

    private static String getTurnMessage() {
        int      player = move % 2;
        Object[] args   = {gui.getButtonText(player), player == 0 ? "X" : "O"};
        return TURN_MSG.format(args);
    }

    private static void play() {
        if ("Robot".equals(gui.getButtonText(move % 2))) {
            robotPlayer();
        }
    }

    /**
     * Handle playing for the robot.
     * <p>
     * Does nothing more than pick a random cell, check to see if we can play there, and makes the play if we can.
     * Nothing complex as the test cases for Hyperskill don't actually check if the robot can play or not. Also, this
     * can be a starting point for implementing different difficulty levels later.
     */
    private static void robotPlayer() {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                try {
                    Thread.sleep(750);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Random space = new Random();
                int    play;
                do {
                    play = space.nextInt(9);
                } while (!gui.getCell(play).isEnabled());

                gui.getCell(play).doClick();
                return null;
            }
        };

        worker.execute();
    }

    /**
     * Configures the game for the chosen menu item.
     *
     * @param menuItem
     *         the enum of the chosen menu item
     */
    static void handleMenuItem(MenuItemSpec menuItem) {
        if (menuItem.equals(MenuItemSpec.EXIT)) {
            System.exit(0);
        }

        gui.enableButtons(true);
        gui.setPlayerButtons(menuItem.getButtonText());
        gui.menuItemClicked();
    }

    /**
     * Call the correct action when the start/reset button is clicked on.
     *
     * @param state
     *         of the button when pressed
     */
    static void startButtonClicked(String state) {
        if ("Start".equals(state)) {
            startGame();
        }

        if ("Reset".equals(state)) {
            gui.resetBoard();
        }
    }

    private static void startGame() {
        gameOver = false;
        move = 0;
        gui.enableCells(true);
        gui.enableButtons(false);
        gui.setStatus(getTurnMessage());
        play();
    }

}
