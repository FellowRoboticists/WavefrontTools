package com.naiveroboticist.wavefront;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MinValueDirectionTest {
    
    private MinValueDirection mvd;

    @Before
    public void setUp() throws Exception {
        mvd = new MinValueDirection(1, 1);
    }

    @After
    public void tearDown() throws Exception {
        mvd = null;
    }

    @Test
    public void testDirectionSet() {
        assertFalse(mvd.directionSet());
        
        mvd.setDirection(8);
        assertTrue(mvd.directionSet());
    }

    @Test
    public void testSetNodeValue() {
        mvd.setNodeValue(22);
        assertEquals(mvd.getNodeValue(), 22);
    }

    @Test
    public void testGetNodeValue() {
        assertEquals(mvd.getNodeValue(), 1);
    }

    @Test
    public void testSetDirection() {
        mvd.setDirection(13);
        assertEquals(mvd.getDirection(), 13);
    }

    @Test
    public void testGetDirection() {
        assertEquals(mvd.getDirection(), 1);
    }

}
