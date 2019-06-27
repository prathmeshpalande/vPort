package com.vport.controller;

import com.vport.Service.TouchCoordinatesService;
import com.vport.model.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.vidstige.jadb.JadbException;

import java.io.IOException;

@RestController
public class TouchCoordinatesController {

    @Autowired
    TouchCoordinatesService touchCoordinatesService;

    @PostMapping("/touch")
    public String touchCoordinates(@RequestBody Coordinate coordinate) throws IOException, JadbException {
        return touchCoordinatesService.touch(coordinate) ? "Success" : "Failure";
    }
}
