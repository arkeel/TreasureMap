package com.carbonit.treasuremap;

public enum Movement {
    FORWARD('A'),
    RIGHT('D'),
    LEFT('G');

    public final char move;

    private Movement(char move) {
        this.move = move;
    }

    public char getMove() {
        return move;
    }
}
