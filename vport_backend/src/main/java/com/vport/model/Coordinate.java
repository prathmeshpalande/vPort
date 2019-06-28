package com.vport.model;

import org.springframework.stereotype.Component;

@Component
public class Coordinate {
    private Float x;
    private Float y;

    public Coordinate() {
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Coordinate(Float x, Float y) {
        this.x = x;
        this.y = y;
    }
}
