package com.vport.task;

import com.vport.model.JadbConnectionUniversal;
import com.vport.util.StreamGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(GrabScreenThread.class);

    @Autowired
    JadbConnectionUniversal jadbConnection;

    @Override
    public void run() {

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
