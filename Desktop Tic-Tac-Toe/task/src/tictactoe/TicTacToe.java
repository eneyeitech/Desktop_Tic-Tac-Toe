package tictactoe;

import javax.swing.*;
import java.awt.*;

public class TicTacToe extends JFrame {

    public TicTacToe() {
        setWindowProperties();
        addComponents();
        setVisible(true);
    }

    /**
     * Set the properties for the main game window here.
     */
    private void setWindowProperties() {
        setTitle("Tic Tac Toe");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(360, 460);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    /**
     * Add in the various components into the game window here.
     */
    private void addComponents() {
        GUI gui = GUI.getInstance();

        this.setJMenuBar(gui.getMenuBar());
        this.add(gui.getButtonBar(), BorderLayout.PAGE_START);
        this.add(gui.getField(), BorderLayout.CENTER);
        this.add(gui.getStatusBar(), BorderLayout.PAGE_END);

        Controller.initialize(gui);
    }
}