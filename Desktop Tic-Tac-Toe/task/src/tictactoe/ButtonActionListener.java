package tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonActionListener implements ActionListener {
    private final Board board;
    private final Button button;

    ButtonActionListener(Board board, Button button) {
        this.board = board;
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (
                this.button.isClicked()
                        || this.board.game.isGameFinished()
                        || this.board.getActivePlayer().getType() == PlayerType.ROBOT
        ) {
            return;
        }

        this.button.setText(this.board.getActivePlayer().getSymbol());
        this.button.setClicked(true);
        this.board.game.setGameStatus(GameStatus.IN_PROGRESS);
        this.board.changePlayer();
    }
}
