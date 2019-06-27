package com.vport.Service;

import com.vport.model.Coordinate;
import org.springframework.stereotype.Service;
import se.vidstige.jadb.JadbConnection;
import se.vidstige.jadb.JadbDevice;
import se.vidstige.jadb.JadbException;

import java.io.IOException;
import java.util.List;

@Service
public class SwipeService {

    public void processCoordinates(List<Coordinate> coordinateList) throws IOException, JadbException {
        JadbConnection jadbConnection = new JadbConnection();
        JadbDevice device = jadbConnection.getDevices().get(0);

        int x1 = (int)coordinateList.get(0).getX() * 1080;
        int y1 = (int)coordinateList.get(0).getY() * 1920;
        int x2 = (int)coordinateList.get(1).getX() * 1080;
        int y2 = (int)coordinateList.get(1).getY() * 1920;
        device.executeShell( "touchscreen swipe " + x1 + " " + y1 + " " + x2 + " " + y2);
        return;
    }
}
