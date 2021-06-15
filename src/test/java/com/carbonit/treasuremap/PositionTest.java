package com.carbonit.treasuremap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
    private Position position;

    @BeforeEach
    public void setUp() {
        position = new Position(3, 4);
    }

    @Test
    void Given_Pos_When_getX_Then_Get_X_From_Pos() {
        assertEquals(3, position.getX());
    }

    @Test
    void Given_Pos_When_getY_Then_Get_Y_From_Pos() {
        assertEquals(4, position.getY());
    }

    @Test
    void Given_Pos_When_SetX_Then_Get_x_From_Pos() {
        position.setX(6);
        assertEquals(6, position.getX());
    }

    @Test
    void Given_Pos_When_SetY_Then_Get_Y_From_Pos() {
        position.setY(0);
        assertEquals(0, position.getX());
    }
}