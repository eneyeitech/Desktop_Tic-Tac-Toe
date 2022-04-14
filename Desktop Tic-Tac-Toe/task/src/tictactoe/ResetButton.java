package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetButton extends JButton implements ActionListener {
    ResetButton() {
        super("Start");
        setName("ButtonStartReset");
        setPreferredSize(new Dimension(80, 30));
        setBackground(Color.LIGHT_GRAY);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if ("Reset".equals(getText())) {
            setText("Start");
            Controller.startButtonClicked("Reset");
        } else {
            setText("Reset");
            Controller.startButtonClicked("Start");
        }
    }
}
