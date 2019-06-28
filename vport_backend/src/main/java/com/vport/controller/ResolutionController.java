package com.vport.controller;

import com.vport.model.Coordinate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se.vidstige.jadb.JadbConnection;
import se.vidstige.jadb.JadbDevice;

import java.util.List;

@RestController
public class ResolutionController {

    @GetMapping("/resolution")
    public Coordinate getDisplayAspect() {
        Coordinate retval = new Coordinate(1080f, 1920f);
        // get the resolution of the emulated device  else return 1080p
        JadbConnection jadb = new JadbConnection();
        try {
            List<JadbDevice> devices = jadb.getDevices();
            devices.forEach(d -> System.out.println(d.getSerial()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retval;
    }
}
