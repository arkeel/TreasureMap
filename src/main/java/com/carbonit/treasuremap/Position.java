package com.carbonit.treasuremap;

public class Position {
    private int x;
    private int y;

    /**
     * Initialize x and y axis on map
     *
     * @param x axis on map
     * @param y axis on map
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the x position on map
     * @return x axis
     */
    public int getX() {
        return x;
    }

    /**
     * Get the y position on map
     * @return y axis
     */
    public int getY() {
        return y;
    }

    /**
     * Set the x axis on map
     * @param x position given
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set the y axis on map
     * @param y position given
     */
    public void setY(int y) {
        this.y = y;
    }
}
