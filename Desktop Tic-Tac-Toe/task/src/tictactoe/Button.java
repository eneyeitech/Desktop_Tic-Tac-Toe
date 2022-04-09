package tictactoe;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    private boolean isClicked = false;

    Button(String label) {
        this.setName("Button" + label);
        this.setText(" ");
        this.setFocusPainted(false);
        this.setFont(new Font("Roboto Thin", Font.PLAIN, 100));
        this.setEnabled(false);
    }

    boolean isClicked() {
        return this.isClicked;
    }

    void setClicked(boolean clicked) {
        this.isClicked = clicked;
    }
}
