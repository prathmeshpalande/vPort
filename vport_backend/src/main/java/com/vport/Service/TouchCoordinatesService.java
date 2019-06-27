package com.vport.Service;

import com.vport.model.Coordinate;
import org.springframework.stereotype.Service;
import se.vidstige.jadb.JadbConnection;
import se.vidstige.jadb.JadbDevice;
import se.vidstige.jadb.JadbException;

import java.io.IOException;
import java.util.List;

@Service
public class TouchCoordinatesService {

    public void touch(Coordinate coordinate) throws IOException, JadbException {
        JadbConnection jadbConnection = new JadbConnection();
        List<JadbDevice> devices = jadbConnection.getDevices();
        int x = (int) (1080 * coordinate.getX());
        int y = (int) (1920 * coordinate.getY());

        System.out.println("X TOUCH: " + x + "\nY TOUCH: " + y);
        devices.get(1).executeShell("input tap " + x + " " + y);
    }
}
