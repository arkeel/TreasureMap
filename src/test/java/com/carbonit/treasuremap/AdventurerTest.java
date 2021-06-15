package com.carbonit.treasuremap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdventurerTest {
    private Adventurer adventurerTest;
    private Adventurer adventurerTestB;

    @BeforeEach
    public void setUp() {
        List<Movement> movements = new ArrayList<>();
        adventurerTest = new Adventurer("Jones", new Position(1, 2), Direction.SOUTH, movements);
        List<Movement> movementsB = Arrays.asList(Movement.FORWARD,Movement.RIGHT, Movement.FORWARD);
        adventurerTestB = new Adventurer("Croft", new Position(2, 2), Direction.NORTH, movementsB);
    }

    @Test
    void Given_Name_When_GetName_Then_Get_Name() {
        assertEquals("Jones", adventurerTest.getName());
    }

    @Test
    void Given_Direction_When_getDirection_Then_Get_Direction() {
        assertEquals(Direction.SOUTH, adventurerTest.getDirection());
    }

    @Test
    void Given_No_Treasure_When_getNbTreasures_Then_Zero_Treasure() {
        assertEquals(0, adventurerTest.getNbTreasures());
    }

    @Test
    void Given_Treasure_Collected_When_getNbTreasures_Then_Get_Nb_Treasure() {
        adventurerTest.collectTreasure();
        assertEquals(1, adventurerTest.getNbTreasures());
    }

    @Test
    void Given_Direction_When_setDirection_Then_Get_Direction() {
        assertEquals(Direction.SOUTH, adventurerTest.getDirection());
        adventurerTest.setDirection(Direction.NORTH);
        assertEquals(Direction.NORTH, adventurerTest.getDirection());
    }

    @Test
    void Given_Treasure_Collected_collectTreasure_Then_Get_Nb_Treasure() {
        adventurerTest.collectTreasure();
        assertEquals(1, adventurerTest.getNbTreasures());
        adventurerTest.collectTreasure();
        assertEquals(2, adventurerTest.getNbTreasures());
    }

    @Test
    void Given_Change_Direction_Right_When_Direction_South_Then_Get_Direction_East() {
        adventurerTest.setDirection(Direction.SOUTH);
        adventurerTest.changeDirection(Movement.RIGHT);
        assertEquals(Direction.EAST, adventurerTest.getDirection());
    }

    @Test
    void Given_Change_Direction_Right_When_Direction_North_Then_Get_Direction_West() {
        adventurerTest.setDirection(Direction.NORTH);
        adventurerTest.changeDirection(Movement.RIGHT);
        assertEquals(Direction.WEST, adventurerTest.getDirection());
    }

    @Test
    void Given_Change_Direction_Right_When_Direction_East_Then_Get_Direction_North() {
        adventurerTest.setDirection(Direction.EAST);
        adventurerTest.changeDirection(Movement.RIGHT);
        assertEquals(Direction.NORTH, adventurerTest.getDirection());
    }

    @Test
    void Given_Change_Direction_Right_When_Direction_West_Then_Get_Direction_South() {
        adventurerTest.setDirection(Direction.WEST);
        adventurerTest.changeDirection(Movement.RIGHT);
        assertEquals(Direction.SOUTH, adventurerTest.getDirection());
    }

    @Test
    void Given_Change_Direction_Left_When_Direction_South_Then_Get_Direction_West() {
        adventurerTest.setDirection(Direction.SOUTH);
        adventurerTest.changeDirection(Movement.LEFT);
        assertEquals(Direction.WEST, adventurerTest.getDirection());
    }

    @Test
    void Given_Change_Direction_Left_When_Direction_North_Then_Get_Direction_East() {
        adventurerTest.setDirection(Direction.NORTH);
        adventurerTest.changeDirection(Movement.LEFT);
        assertEquals(Direction.EAST, adventurerTest.getDirection());
    }

    @Test
    void Given_Change_Direction_Left_When_Direction_East_Then_Get_Direction_South() {
        adventurerTest.setDirection(Direction.EAST);
        adventurerTest.changeDirection(Movement.LEFT);
        assertEquals(Direction.SOUTH, adventurerTest.getDirection());
    }

    @Test
    void Given_Change_Direction_Left_When_Direction_West_Then_Get_Direction_North() {
        adventurerTest.setDirection(Direction.WEST);
        adventurerTest.changeDirection(Movement.LEFT);
        assertEquals(Direction.NORTH, adventurerTest.getDirection());
    }

    @Test
    void Given_Empty_Movement_When_Can_Still_Move_Then_False() {
        assertFalse(adventurerTest.canStillMove());
    }

    @Test
    void Given_Movement_List_When_Can_Still_Move_Then_True() {
        assertTrue(adventurerTestB.canStillMove());
    }

    @Test
    void Given_Adventurer_When_render_Then_render() {
        assertEquals("A - Jones - 1 - 2 - S - 0", adventurerTest.render());
    }
}