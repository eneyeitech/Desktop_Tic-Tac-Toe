package tictactoe;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JPanel {
    private final Board board;
    private GameStatus status = GameStatus.NOT_STARTED;
    private final JLabel statusLabel = new JLabel(this.status.getLabel());

    StatusBar(Board board) {
        this.board = board;
        this.addStatusLabel();
        this.setLayout(new FlowLayout());
        this.setVisible(true);
    }

    void setStatus(GameStatus status) {
        this.status = status;
        this.statusLabel.setText(status.getLabel());
    }

    GameStatus getStatus() {
        return this.status;
    }

    void addStatusLabel() {
        this.statusLabel.setHorizontalAlignment(JLabel.CENTER);
        this.statusLabel.setName("LabelStatus");
        add(this.statusLabel);
    }
}
