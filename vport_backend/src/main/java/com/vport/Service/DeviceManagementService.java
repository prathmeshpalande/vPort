package com.vport.Service;

import com.vport.model.Coordinate;
import com.vport.model.CreateDeviceRequest;
import com.vport.model.GeneralResponseObject;
import com.vport.util.ADBExecutor;
import com.vport.util.StreamGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import se.vidstige.jadb.JadbException;

import java.io.IOException;
import java.util.List;

@Service
public class DeviceManagementService {

    Logger logger = LoggerFactory.getLogger(DeviceManagementService.class);

    public GeneralResponseObject createDevice(CreateDeviceRequest createDeviceRequest) {

        //Code to create device using AVD Manager CLI
        //....
        //....

        //GeneralResponseObject
        GeneralResponseObject responseObject = GeneralResponseObject.getDefaultObject(1);

        return responseObject;
    }

    public GeneralResponseObject grabScreen() throws IOException {
        ADBExecutor.executeADBCommand("adb shell screencap -p /sdcard/screencap.png");
        ADBExecutor.executeADBCommand("adb pull /sdcard/screencap.png");

        byte[] data = StreamGenerator.imageToByteStream();

        GeneralResponseObject responseObject = GeneralResponseObject.getDefaultObject(1);
        responseObject.setResponseData(data);

        return responseObject;
    }

    public GeneralResponseObject swipe(List<Coordinate> coordinateList) throws IOException, JadbException {
        Float x1 = coordinateList.get(0).getX() * 1080;
        Float y1 = coordinateList.get(0).getY() * 1920;
        Float x2 = coordinateList.get(1).getX() * 1080;
        Float y2 = coordinateList.get(1).getY() * 1920;

        System.out.println("x1: " + x1 + "\ny1: " + y1 + "\nx2: " + x2 + "\ny2: " + y2);
        ADBExecutor.executeADBCommand("adb shell input touchscreen swipe " + x1 + " " + y1 + " " + x2 + " " + y2);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return GeneralResponseObject.getDefaultObject(1);
    }

    public GeneralResponseObject touch(Coordinate coordinate) throws IOException, JadbException {
        Float x = 1080 * coordinate.getX();
        Float y = 1920 * coordinate.getY();

        System.out.println("X TOUCH: " + x + "\nY TOUCH: " + y);

        ADBExecutor.executeADBCommand("adb shell input tap " + x + " " + y);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return GeneralResponseObject.getDefaultObject(1);
    }
}
