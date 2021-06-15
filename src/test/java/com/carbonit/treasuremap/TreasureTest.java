package com.carbonit.treasuremap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreasureTest {
    private Treasure treasureTest;

    @BeforeEach
    public void setUp() {
        treasureTest = new Treasure(new Position(1, 3), 5);

    }

    @Test
    void Given_Treasure_When_getNbTreasure_Then_Nb_Treasure() {
        assertEquals(5, treasureTest.getNbTreasure());
    }

    @Test
    void Given_Empty_Treasure_When_isTreasureFree_Then_true() {
        assertTrue(treasureTest.isTreasureFree());
    }

    @Test
    void Given_Treasure_Taken_When_isTreasureFree_Then_false() {
        treasureTest.setTreasureStatus(false);
        assertFalse(treasureTest.isTreasureFree());
    }

    @Test
    void Given_Empty_Treasure_When_setTreasureStatus_Then_isTreasureFree_False() {
        treasureTest.setTreasureStatus(false);
        assertFalse(treasureTest.isTreasureFree());
    }

    @Test
    void Given_Treasure_Taken_When_setTreasureStatus_Then_isTreasureFree_true() {
        treasureTest.setTreasureStatus(true);
        assertTrue(treasureTest.isTreasureFree());
    }

    @Test
    void Given_Treasure_When_collectTreasure_Then_Get_Nb_Treasure() {
        assertEquals(5, treasureTest.getNbTreasure());
        treasureTest.collectTreasure();
        assertEquals(6, treasureTest.getNbTreasure());
    }

    @Test
    void Given_Treasure_When_render_Then_render() {
        assertEquals("T - 1 - 3 - 5", treasureTest.render());
    }
}