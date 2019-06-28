package com.vport.controller;

import com.vport.Service.GrabScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se.vidstige.jadb.JadbException;

import java.io.IOException;

@RestController
public class GrabScreenController {

    @Autowired
private GrabScreenService grabScreenService;

    @GetMapping("/grab_screen")
    public byte[] getScreen() throws IOException, JadbException {

        return grabScreenService.grabScreen();
    }

}
