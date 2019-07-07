package com.vport.controller;

import com.vport.Service.DeviceManagementService;
import com.vport.model.Coordinate;
import com.vport.model.CreateDeviceRequest;
import com.vport.model.GeneralResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.vidstige.jadb.JadbException;

import java.io.IOException;
import java.util.List;

@RestController
public class DeviceManagementController {

    Logger logger = LoggerFactory.getLogger(DeviceManagementController.class);

    @Autowired
    DeviceManagementService deviceManagementService;

    @PostMapping("create_device")
    public GeneralResponseObject createDevice(@RequestBody CreateDeviceRequest createDeviceRequest) {

        GeneralResponseObject responseObject = deviceManagementService.createDevice(createDeviceRequest);

        return responseObject;

    }

    @GetMapping("/grab_screen")
    public GeneralResponseObject grabScreen() throws IOException {
        GeneralResponseObject responseObject = deviceManagementService.grabScreen();
        return responseObject;
    }

    @PostMapping("/swipe")
    public GeneralResponseObject swipe(@RequestBody List<Coordinate> coordinateList) throws IOException, JadbException {
        GeneralResponseObject responseObject = deviceManagementService.swipe(coordinateList);
        return responseObject;
    }

    @PostMapping("/touch")
    public GeneralResponseObject touch(@RequestBody Coordinate coordinate) throws IOException, JadbException {
        GeneralResponseObject responseObject = deviceManagementService.touch(coordinate);
        return responseObject;
    }

}
