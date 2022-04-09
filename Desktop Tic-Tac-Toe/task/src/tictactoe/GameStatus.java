package tictactoe;

public enum GameStatus {
    NOT_STARTED("Game is not started"),
    IN_PROGRESS("Game in progress"),
    X_WINS("X wins"),
    O_WINS("O wins"),
    DRAW("Draw");

    private final String label;

    GameStatus(String label) {
        this.label = label;
    }

    String getLabel() {
        return this.label;
    }
}
