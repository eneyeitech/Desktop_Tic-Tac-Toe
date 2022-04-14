package tictactoe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GUI {
    private static GUI gui;

    private final JMenuBar menuBar;
    private final ArrayList<PlayerButton> players;
    private final ResetButton startButton;
    private final JPanel buttonBar;
    private final ArrayList<Cell> cells;
    private final JPanel field;
    private final JLabel statusBar;

    private GUI() {
        menuBar = createMenuBar();
        players = definePlayerButtons();
        startButton = new ResetButton();
        buttonBar = createButtonBar();
        cells = defineCells();
        field = createField();
        statusBar = createStatusBar();
    }

    public static GUI getInstance() {
        if (null == gui) {
            gui = new GUI();
        }
        return gui;
    }

    JMenuBar getMenuBar() {
        return menuBar;
    }

    JPanel getButtonBar() {
        return buttonBar;
    }

    JPanel getField() {
        return field;
    }

    JLabel getStatusBar() {
        return statusBar;
    }

    private JMenuBar createMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.setMargin(new Insets(10, 10, 10, 10));

        jMenuBar.add(createGameMenu());
        return jMenuBar;
    }

    private JMenu createGameMenu() {
        JMenu jMenu = new JMenu();
        jMenu.setText("Game");
        jMenu.setName("MenuGame");
        jMenu.setMnemonic(KeyEvent.VK_G);

        for (var item : MenuItemSpec.values()) {
            jMenu.add(new MenuItem(item));
        }

        jMenu.insertSeparator(jMenu.getMenuComponentCount() - 1);

        return jMenu;
    }

    private ArrayList<PlayerButton> definePlayerButtons() {
        ArrayList<PlayerButton> buttons = new ArrayList<>();
        buttons.add(new PlayerButton("ButtonPlayer1"));
        buttons.add(new PlayerButton("ButtonPlayer2"));
        return buttons;
    }

    /**
     * Create the button bar holding the player toggle buttons and the game start/reset button.
     * @return JPanel with the three buttons
     */
    private JPanel createButtonBar() {
        JPanel buttonBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonBar.setPreferredSize(new Dimension(360, 50));
        buttonBar.setBackground(Color.GRAY);

        buttonBar.add(players.get(0));
        buttonBar.add(startButton);
        buttonBar.add(players.get(1));

        return buttonBar;
    }

    /**
     * Instantiates the JButtons with their name and associated listener method.
     * Adds each one to the ArrayList<Cell> field.
     */
    private ArrayList<Cell> defineCells() {
        ArrayList<Cell> cells = new ArrayList<>();
        String[] rowNames = {"3", "2", "1"};
        String[] colNames = {"A", "B", "C"};
        for (var row : rowNames) {
            for (var col : colNames) {
                cells.add(new Cell("Button" + col + row));
            }
        }
        return cells;
    }

    /**
     * Create the JPanel which holds the 3 x 3 grid of cells of the tic-tac-toe board.
     * @return JPanel with nine buttons
     */
    private JPanel createField() {
        JPanel jPanel = new JPanel(new GridLayout(3, 3));
        jPanel.setPreferredSize(new Dimension(360, 360));

        for (Cell cell : cells) {
            jPanel.add(cell);
        }

        return jPanel;
    }

    /**
     * Create a JLabel to display messages about the game state.
     * @return JLabel
     */
    private JLabel createStatusBar() {
        JLabel statusPanel = new JLabel();
        statusPanel.setPreferredSize(new Dimension(360, 50));
        statusPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        statusPanel.setFont(new Font(Font.SERIF, Font.PLAIN, 14));
        statusPanel.setName("LabelStatus");
        statusPanel.setText("Game is not started");
        statusPanel.setBackground(Color.GRAY);
        statusPanel.setOpaque(true);
        return statusPanel;
    }

    /**
     * Sets the status bar text to the supplied string
     * @param msg text to be displayed
     */
    void setStatus(final String msg) {
        statusBar.setText(msg);
    }

    /**
     * Gets the current text of the specified player button
     * @param player index to the player to check (0 or 1)
     * @return text of the button
     */
    String getButtonText(final int player) {
        return players.get(player).getText();
    }

    /**
     * Set the player buttons to the specified states
     * @param setTo Two element array of states to set buttons to
     */
    void setPlayerButtons(final String[] setTo) {
        for (PlayerButton player : players) {
            if (!player.getText().equals(setTo[players.indexOf(player)])) {
                player.doClick();
            }
        }
    }

    /**
     * Set the player buttons to the specified enabled state
     * @param enabled state to set (true or false)
     */
    void enableButtons(final boolean enabled) {
        for (PlayerButton player : players) {
            player.setEnabled(enabled);
        }
    }

    /**
     * Set the cells to the specified enabled state
     *
     * @param enabled
     *         state to set (true or false)
     */
    void enableCells(final boolean enabled) {
        for (Cell cell : cells) {
            cell.setEnabled(enabled);
        }
    }

    /**
     * Set the cells back to their initial state
     */
    void resetCells() {
        for (Cell cell : cells) {
            cell.setCellText(" ");
            cell.setEnabled(false);
            cell.setBackground(Color.LIGHT_GRAY);
        }
    }

    /**
     * Check to see if we three in a row for the specified indices
     * @param indices of cells to check
     * @return true if we have three in a row that's not a space
     */
    boolean isThreeInARow(final int[] indices) {
        return cells.get(indices[0]).getCellText().equals(cells.get(indices[1]).getCellText()) &&
                cells.get(indices[1]).getCellText().equals(cells.get(indices[2]).getCellText()) &&
                !cells.get(indices[0]).getCellText().equals(" ");
    }

    /**
     * Returns a specified cell
     * @param cell to be returned (0 - 8)
     */
    Cell getCell(final int cell) {
        return cells.get(cell);
    }

    /**
     * Handles the cases where a menu item was chosen.
     *
     * If the menu item was chosen mid-game, we need to click the start button to reset the board, then click again
     * to start the game. Otherwise, we just click it once to start.
     */
    void menuItemClicked() {
        if ("Reset".equals(startButton.getText())) {
            startButton.doClick();
        }
        startButton.doClick();
    }

    /**
     * Sets everything back to an initial state ready to start a game.
     *
     * Resets the cells, enables the player select buttons, and sets the status message to not begun.
     */
    void resetBoard() {
        resetCells();
        enableButtons(true);
        setStatus("Game is not started");
    }
}
