package com.vport.controller;

import com.vport.Service.SwipeService;
import com.vport.model.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.vidstige.jadb.JadbException;

import java.io.IOException;
import java.util.List;

@RestController
public class SwipeController {

    @Autowired
    SwipeService swipeService;

    @PostMapping("/swipe")
    public void getCoordinates(@RequestBody List<Coordinate> coordinateList) throws IOException, JadbException {
        swipeService.processCoordinates(coordinateList);
    }
}
