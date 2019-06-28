package com.vport.task;

import com.vport.model.JadbConnectionUniversal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.vidstige.jadb.JadbConnection;
import se.vidstige.jadb.JadbDevice;
import se.vidstige.jadb.JadbException;
import se.vidstige.jadb.RemoteFile;

import java.io.File;
import java.io.IOException;

@Component
public class GrabScreenThread extends Thread {

    @Autowired
    JadbConnectionUniversal jadbConnection;

    @Override
    public void run() {
//        JadbConnection jadbConnection = new JadbConnection();

        try {
            JadbDevice device = (JadbDevice) jadbConnection.getJadbConnection().getDevices().get(1);
            device.executeShell("screencap -p /sdcard/screencap.png");
            device.pull(new RemoteFile("/sdcard/screencap.png"), new File("screencap.png"));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JadbException e) {
            e.printStackTrace();
        }
    }
}
