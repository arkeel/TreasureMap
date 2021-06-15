package com.carbonit.treasuremap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MountainTest {
    private Mountain mountainTest;

    @BeforeEach
    public void setUp() {
        mountainTest = new Mountain(new Position(1, 3));
    }

    @Test
    void Given_Mountain_When_render_Then_render() {
        assertEquals("M - 1 - 3", mountainTest.render());
    }
}