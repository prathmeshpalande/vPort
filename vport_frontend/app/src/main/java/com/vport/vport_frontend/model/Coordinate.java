package com.vport.vport_frontend.model;

public class Coordinate {

    private Integer width;
    private Integer height;

    public Coordinate() {

    }

    public Coordinate(Integer width, Integer height) {
        this.width = width;
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
