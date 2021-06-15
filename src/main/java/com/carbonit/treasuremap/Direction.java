package com.carbonit.treasuremap;

public enum Direction {
    NORTH('N'),
    EAST('E'),
    WEST('O'),
    SOUTH('S');

    public final char direction;

    private Direction(char direction) {
        this.direction = direction;
    }

    public char getDirection() {
        return this.direction;
    }
}
