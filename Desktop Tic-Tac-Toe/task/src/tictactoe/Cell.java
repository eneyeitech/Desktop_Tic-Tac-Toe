package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cell extends JButton implements ActionListener {
    Cell(String text) {
        setName(text);
        setCellText(" ");
        setEnabled(false);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        setBackground(Color.LIGHT_GRAY);
        setFocusPainted(false);
        addActionListener(this);
    }

    void setCellText(String text) {
        setText(text);
    }

    String getCellText() {
        return getText();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Controller.playCell(this);
    }
}
