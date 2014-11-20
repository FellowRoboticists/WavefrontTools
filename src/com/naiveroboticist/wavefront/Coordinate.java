package com.naiveroboticist.wavefront;

public class Coordinate {
    
    private int x;
    private int y;
    
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public int getY() {
        return y;
    }
    
    public boolean onTheGrid(int maxX, int maxY) {
        return x >= 0 && x < maxX && y >= 0 && y < maxY;
    }

}
