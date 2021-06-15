package com.carbonit.treasuremap;

public class AbstractMapElement {
    protected Position pos;

    public AbstractMapElement(Position pos) {
        this.pos = pos;
    }


    public int getXFromPos() {
        return pos.getX();
    }

    public int getYFromPos() {
        return pos.getY();
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    /**
     * Display each elements of the map as in the file
     * @return a String representing elements
     */
    public String render() {
        return " - " + getXFromPos() + " - " + getYFromPos();
    }
}
