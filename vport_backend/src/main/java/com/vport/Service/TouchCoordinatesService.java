package com.vport.Service;

import com.vport.model.Coordinate;
import com.vport.model.JadbConnectionUniversal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.vidstige.jadb.JadbConnection;
import se.vidstige.jadb.JadbDevice;
import se.vidstige.jadb.JadbException;
import se.vidstige.jadb.RemoteFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class TouchCoordinatesService {

    @Autowired
    JadbConnectionUniversal jadbConnection;

    public Boolean touch(Coordinate coordinate) throws IOException, JadbException {
//        JadbConnection jadbConnection = new JadbConnection();
        List<JadbDevice> devices = jadbConnection.getJadbConnection().getDevices();
        Float x = 1080 * coordinate.getX();
        Float y = 1920 * coordinate.getY();

        System.out.println("X TOUCH: " + x + "\nY TOUCH: " + y);
        devices.get(1).executeShell("input tap " + x + " " + y);

//        devices.get(1).executeShell("screencap -p /sdcard/screencap.png");
//        devices.get(1).pull(new RemoteFile("/sdcard/screencap.png"), new File("screencap.png"));

        return true;
    }
}
