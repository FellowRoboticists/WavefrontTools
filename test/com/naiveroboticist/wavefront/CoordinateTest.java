package com.naiveroboticist.wavefront;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CoordinateTest {
    
    private static final int MAXX = 50;
    private static final int MAXY = 50;
    
    private Coordinate mCut;

    @Before
    public void setUp() throws Exception {
        mCut = new Coordinate(11, 12);
    }

    @After
    public void tearDown() throws Exception {
        mCut = null;
    }

    @Test
    public void testSetX() {
        mCut.setX(21);
        assertEquals(21, mCut.getX());
    }

    @Test
    public void testGetX() {
        assertEquals(11, mCut.getX());
    }

    @Test
    public void testSetY() {
        mCut.setY(32);
        assertEquals(32, mCut.getY());
    }

    @Test
    public void testGetY() {
        assertEquals(12, mCut.getY());
    }
    
    @Test
    public void testOnTheGridValid() {
        assertTrue(mCut.onTheGrid(MAXX, MAXY));
    }

    @Test
    public void testOnTheGridNegativeX() {
        mCut.setX(-1);
        assertFalse(mCut.onTheGrid(MAXX, MAXY));
    }

    @Test
    public void testOnTheGridNegativeY() {
        mCut.setY(-1);
        assertFalse(mCut.onTheGrid(MAXX, MAXY));
    }

    @Test
    public void testOnTheGridExcessiveX() {
        mCut.setX(51);
        assertFalse(mCut.onTheGrid(MAXX, MAXY));
    }

    @Test
    public void testOnTheGridExcessiveY() {
        mCut.setY(51);
        assertFalse(mCut.onTheGrid(MAXX, MAXY));
    }

}
