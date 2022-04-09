package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;

public class Board extends JPanel {
    final TicTacToe game;

    private Player activePlayer;

    private final Button[] buttons = new Button[] {
            new Button("A3"), new Button("B3"), new Button("C3"),
            new Button("A2"), new Button("B2"), new Button("C2"),
            new Button("A1"), new Button("B1"), new Button("C1")
    };

    Board(TicTacToe game) {
        this.game = game;
        this.activePlayer = this.game.getPlayer1();
        this.addButtons();
        this.setLayout(new GridLayout(3, 3));
        this.setVisible(true);
    }

    Player getActivePlayer() {
        return this.activePlayer;
    }

    void changePlayer() {
        if (this.checkWinner() || !this.checkEmptyButton()) {
            return;
        }

        this.activePlayer = this.activePlayer == this.game.getPlayer1()
                ? this.game.getPlayer2()
                : this.game.getPlayer1();

        this.move();
    }

    private boolean checkEmptyButton() {
        for (Button button : this.buttons) {
            if (" ".equals(button.getText())) {
                return true;
            }
        }

        this.game.setGameStatus(GameStatus.DRAW);
        this.disableButtons();

        return false;
    }

    private boolean checkWinner() {
        String[][] versions = new String[][] {
                {"A3", "B3", "C3"},
                {"A2", "B2", "C2"},
                {"A1", "B1", "C1"},
                {"A3", "A2", "A1"},
                {"B3", "B2", "B1"},
                {"C3", "C2", "C1"},
                {"A3", "B2", "C1"},
                {"A1", "B2", "C3"},
        };

        for (String[] version : versions) {
            if (this.checkEquality(version)) {
                this.game.setGameStatus("X".equals(this.activePlayer.getSymbol())
                        ? GameStatus.X_WINS
                        : GameStatus.O_WINS);
                this.disableButtons();
                return true;
            }
        }

        return false;
    }

    private boolean checkEquality(String[] buttonLabels) {
        Button firstButton = this.getButtonByName("Button" + buttonLabels[0]);

        if (firstButton == null || " ".equals(firstButton.getText())) {
            return false;
        }

        for (int i = 1; i < buttonLabels.length; i++) {
            Button button = this.getButtonByName("Button" + buttonLabels[i]);

            if (button == null || " ".equals(button.getText())) {
                return false;
            }

            if (!firstButton.getText().equals(button.getText())) {
                return false;
            }
        }

        return true;
    }

    private Button getButtonByName(String name) {
        for (Button button : this.buttons) {
            if (button.getName().equals(name)) {
                return button;
            }
        }

        return null;
    }

    void reset() {
        Arrays.stream(this.buttons).forEach(b -> {
            b.setText(" ");
            b.setClicked(false);
            b.setEnabled(false);
        });
        this.game.setGameStatus(GameStatus.NOT_STARTED);
        this.activePlayer = this.game.getPlayer1();
    }

    void start() {
        Arrays.stream(this.buttons).forEach(b -> b.setEnabled(true));
        move();
    }

    void disableButtons() {
        Arrays.stream(this.buttons).forEach(b -> b.setEnabled(false));
    }

    private void addButtons() {
        Arrays.stream(this.buttons).forEach(b -> {
            b.addActionListener(new ButtonActionListener(this, b));
            add(b);
        });
    }

    private void move() {
        if (this.activePlayer.getType() == PlayerType.HUMAN) {
            return;
        }

        Button[] unclickedButtons = Arrays.stream(this.buttons).filter(b -> !b.isClicked()).toArray(Button[]::new);

        Optional<Button> optionalButton = Arrays.stream(unclickedButtons)
                .skip(new Random().nextInt(unclickedButtons.length))
                .findFirst();

        if (optionalButton.isEmpty()) {
            return;
        }

        Timer timer = new Timer();

        Board board = this;

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                optionalButton.get().setClicked(true);
                optionalButton.get().setText(board.activePlayer.getSymbol());

                board.changePlayer();
            }
        };
        timer.schedule(task, 500);
    }
}