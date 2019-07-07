package com.vport.model;

public class CreateDeviceRequest {
    
    private String name;
    private Coordinate resolution;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinate getResolution() {
        return resolution;
    }

    public void setResolution(Coordinate resolution) {
        this.resolution = resolution;
    }
}
