package com.vport.Service;

import com.vport.model.Coordinate;
import com.vport.task.ADBExecutor;
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

    public Boolean touch(Coordinate coordinate) throws IOException, JadbException {
        Float x = 1080 * coordinate.getX();
        Float y = 1920 * coordinate.getY();

        System.out.println("X TOUCH: " + x + "\nY TOUCH: " + y);

        ADBExecutor.executeADBCommand("adb shell input tap " + x + " " + y);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return true;
    }
}
