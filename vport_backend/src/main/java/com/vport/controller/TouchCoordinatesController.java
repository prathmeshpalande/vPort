package com.vport.controller;

import com.vport.Service.TouchCoordinatesService;
import com.vport.model.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import se.vidstige.jadb.JadbException;

import java.io.IOException;

@RestController
public class TouchCoordinatesController {

    @Autowired
    TouchCoordinatesService touchCoordinatesService;

    @PostMapping("/touch")
    public void touchCoordinates(Coordinate coordinate) throws IOException, JadbException {
        touchCoordinatesService.touch(coordinate);
    }
}
