package com.example.rubyf.chaos;

public class Point {
    public int x;
    public int y;

    Point(){
        x = 0;
        y = 0;
    }

    Point(int startx, int starty){
        x = startx;
        y = starty;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
