package tictactoe;

class Player {
    private PlayerType type;
    private final String symbol;

    Player(PlayerType type, String symbol) {
        this.type = type;
        this.symbol = symbol;
    }

    PlayerType getType() {
        return this.type;
    }

    String getSymbol() {
        return this.symbol;
    }

    void toggleType() {
        this.type = this.type == PlayerType.HUMAN ? PlayerType.ROBOT : PlayerType.HUMAN;
    }
}


enum PlayerType {
    HUMAN("Human"),
    ROBOT("Robot");

    private final String label;

    PlayerType(String label) {
        this.label = label;
    }

    String getLabel() {
        return this.label;
    }
}
