package tictactoe;

import javax.swing.*;
import java.awt.*;

class Toolbar extends JPanel {
    private final TicTacToe game;
    private final JButton playerButton1 = new JButton();
    private final JButton playerButton2 = new JButton();

    Toolbar(TicTacToe game) {
        this.game = game;
        addButtons();
        this.setLayout(new GridLayout(1, 3));
        this.setVisible(true);
    }

    private void addButtons() {
        this.addPlayerButton(this.playerButton1, "ButtonPlayer1", this.game.getPlayer1());
        this.addStartButton();
        this.addPlayerButton(this.playerButton2, "ButtonPlayer2", this.game.getPlayer2());
    }

    private void addPlayerButton(JButton button, String name, Player player) {
        button.setName(name);
        button.setText(player.getType().getLabel());

        button.addActionListener(actionEvent -> {
            if (this.game.getGameStatus() != GameStatus.NOT_STARTED) {
                return;
            }
            player.toggleType();
            button.setText(player.getType().getLabel());
        });

        add(button);
    }

    private void addStartButton() {
        JButton button = new JButton();
        button.setName("ButtonStartReset");
        button.setText("Start");

        button.addActionListener(actionEvent -> {
            if ("Reset".equals(button.getText())) {
                this.game.reset();
                button.setText("Start");
                this.playerButton1.setEnabled(true);
                this.playerButton2.setEnabled(true);
                return;
            }
            this.game.start();
            button.setText("Reset");
            this.playerButton1.setEnabled(false);
            this.playerButton2.setEnabled(false);
        });

        add(button);
    }
}
