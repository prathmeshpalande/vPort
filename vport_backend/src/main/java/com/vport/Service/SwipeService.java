package com.vport.Service;

import com.vport.model.Coordinate;
import com.vport.model.JadbConnectionUniversal;
import com.vport.task.ADBExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.vidstige.jadb.JadbConnection;
import se.vidstige.jadb.JadbDevice;
import se.vidstige.jadb.JadbException;

import java.io.IOException;
import java.util.List;

@Service
public class SwipeService {

//    @Autowired
//    JadbConnectionUniversal jadbConnection;

    public void processCoordinates(List<Coordinate> coordinateList) throws IOException, JadbException {
//        JadbConnection jadbConnection = new JadbConnection();
////        JadbDevice device = jadbConnection.getJadbConnection().getDevices().get(0);
//        JadbDevice device = jadbConnection.getDevices().get(0);

        Float x1 = coordinateList.get(0).getX() * 1080;
        Float y1 = coordinateList.get(0).getY() * 1920;
        Float x2 = coordinateList.get(1).getX() * 1080;
        Float y2 = coordinateList.get(1).getY() * 1920;
//        device.executeShell( "touchscreen swipe " + x1 + " " + y1 + " " + x2 + " " + y2);

        System.out.println("x1: " + x1 + "\ny1: " + y1 + "\nx2: " + x2 + "\ny2: " + y2);
        ADBExecutor.executeADBCommand("adb shell input touchscreen swipe " + x1 + " " + y1 + " " + x2 + " " + y2);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        ADBExecutor.executeADBCommand("powershell.exe adb pull /sdcard/screencap.png");
        return;
    }
}
