package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerButton extends JButton implements ActionListener {
    PlayerButton(String name) {
        super("Human");
        setName(name);
        setPreferredSize(new Dimension(110, 30));
        setBackground(Color.LIGHT_GRAY);
        addActionListener(this);
    }

    void setButtonText(String text) {
        setText(text);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        setButtonText("Human".equals(getText()) ? "Robot" : "Human");
    }
}
