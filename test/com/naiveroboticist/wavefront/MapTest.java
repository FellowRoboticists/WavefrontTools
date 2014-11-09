package com.naiveroboticist.wavefront;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MapTest {
    
    private Map map;

    @Before
    public void setUp() throws Exception {
        map = new Map(5, 5);
    }

    @After
    public void tearDown() throws Exception {
        map = null;
    }

    @Test
    public void testMap() {
        assertEquals(map.getSizeX(), 5);
        assertEquals(map.getSizeY(), 5);
        
        // All cells should be initialized to NOTHING
        for (int x=0; x<5; x++) {
            for (int y=0; y<5; y++) {
                assertEquals(map.getMap()[x][y], Map.NOTHING);
            }
        }
   }

    @Test
    public void testGetMap() {
        assertNotNull(map.getMap());
        assertEquals(map.getMap().length, 5);
        assertEquals(map.getMap()[0].length, 5);
    }

    @Test
    public void testPlaceValue() {
        map.placeValue(10, 10, 11); // No exception
        map.placeValue(4, 4, 3);
        assertEquals(map.getMap()[4][4], 3);
    }

    @Test
    public void testMinSurroundingNodeDown() {
        map.placeValue(2, 1, 20);
        MinValueDirection mvd = map.minSurroundingNode(1, 1);
        assertEquals(mvd.getNodeValue(), 20);
        assertEquals(mvd.getDirection(), Map.DOWN);
    }

    public void testMinSurroundingNodeUp() {
        map.placeValue(0, 1, 20);
        MinValueDirection mvd = map.minSurroundingNode(1, 1);
        assertEquals(mvd.getNodeValue(), 20);
        assertEquals(mvd.getDirection(), Map.UP);
    }

    public void testMinSurroundingNodeLeft() {
        map.placeValue(1, 0, 20);
        MinValueDirection mvd = map.minSurroundingNode(1, 1);
        assertEquals(mvd.getNodeValue(), 20);
        assertEquals(mvd.getDirection(), Map.LEFT);
    }

    public void testMinSurroundingNodeRight() {
        map.placeValue(1, 2, 20);
        MinValueDirection mvd = map.minSurroundingNode(1, 1);
        assertEquals(mvd.getNodeValue(), 20);
        assertEquals(mvd.getDirection(), Map.RIGHT);
    }

    public void testMinSurroundingNodeNothing() {
        MinValueDirection mvd = map.minSurroundingNode(1, 1);
        assertEquals(mvd.getNodeValue(), Map.RESET_MIN);
        assertEquals(mvd.getDirection(), null);
    }

    @Test
    public void testClear() {
        map.placeValue(2, 2, Map.ROBOT);
        map.placeValue(4, 4, Map.GOAL);
        map.placeValue(1, 1, 18);
        map.placeValue(1, 4, 20);
        
        map.clear();
        
        assertEquals(map.getMap()[2][2], Map.ROBOT);
        assertEquals(map.getMap()[4][4], Map.GOAL);
        assertEquals(map.getMap()[1][1], Map.NOTHING);
        assertEquals(map.getMap()[1][4], Map.NOTHING);
        assertEquals(map.getMap()[0][0], Map.NOTHING);
    }

    @Test
    public void testUnpropagate() {
        map.placeValue(2, 2, Map.ROBOT);
        map.placeValue(4, 4, Map.GOAL);
        map.placeValue(3, 3, Map.WALL);
        map.placeValue(1, 1, 2);
        map.placeValue(1, 4, 3);
        
        map.unpropagate();
        assertEquals(map.getMap()[2][2], Map.ROBOT);
        assertEquals(map.getMap()[4][4], Map.GOAL);
        assertEquals(map.getMap()[3][3], Map.WALL);
        assertEquals(map.getMap()[1][1], Map.NOTHING);
        assertEquals(map.getMap()[1][4], Map.NOTHING);
    }

    @Test
    public void testPropagateWavefrontToRobot() {
        map.placeValue(0, 0, Map.ROBOT);
        map.placeValue(4, 4, Map.GOAL);

        int direction = map.propagateWavefront();

        assertEquals(direction, Map.DOWN);
    }
    
    @Test
    public void testPropagateWavefrontToWall() {
        map.placeValue(0, 0, Map.ROBOT);
        map.placeValue(4, 4, Map.GOAL);
        map.placeValue(4, 2, Map.WALL);
        map.placeValue(3, 2, Map.WALL);
        map.placeValue(2, 2, Map.WALL);
        map.placeValue(2, 3, Map.WALL);
        map.placeValue(2, 4, Map.WALL);

        int direction = map.propagateWavefront();

        assertEquals(direction, 0);
    }
    
    @Test
    public void testProgateWavefrontAroundWall() {
        map.placeValue(0, 2, Map.GOAL);
        map.placeValue(4, 2, Map.ROBOT);

        // Put a short wall between the robot and the goal
        map.placeValue(2, 1, Map.WALL);
        map.placeValue(2, 2, Map.WALL);
        map.placeValue(2, 3, Map.WALL);

        int direction = map.propagateWavefront();

        assertEquals(direction, Map.UP);
    }
    
    @Test
    public void testGridLocationFromCenterRadius() {
        Coordinate coord;
        for (int angle=0; angle<360; angle += 30) {
            coord = map.gridLocationFromCenterRadius(2, 0, angle, 33.0 + 17.0);
            switch (angle) {
            case 0:
                assertEquals(4, coord.getX());
                assertEquals(0, coord.getY());
                break;
            case 30:
                assertEquals(3, coord.getX());
                assertEquals(1, coord.getY());
                break;
            case 60:
                assertEquals(3, coord.getX());
                assertEquals(1, coord.getY());
                break;
            case 90:
                assertEquals(2, coord.getX());
                assertEquals(2, coord.getY());
                break;
            case 120:
                assertEquals(1, coord.getX());
                assertEquals(1, coord.getY());
                break;
            case 150:
                assertEquals(1, coord.getX());
                assertEquals(1, coord.getY());
                break;
            case 180:
                assertEquals(0, coord.getX());
                assertEquals(0, coord.getY());
                break;
            case 210:
                assertEquals(1, coord.getX());
                assertEquals(-1, coord.getY());
                break;
            case 240:
                assertEquals(1, coord.getX());
                assertEquals(-1, coord.getY());
                break;
            case 270:
                assertEquals(2, coord.getX());
                assertEquals(-2, coord.getY());
                break;
            case 300:
                assertEquals(3, coord.getX());
                assertEquals(-1, coord.getY());
                break;
            case 330:
                assertEquals(3, coord.getX());
                assertEquals(-1, coord.getY());
                break;
            }
        }
    }

}
