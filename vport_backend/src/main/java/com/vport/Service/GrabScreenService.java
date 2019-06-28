package com.vport.Service;

import com.vport.task.ADBExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.vidstige.jadb.JadbConnection;
import se.vidstige.jadb.JadbDevice;
import se.vidstige.jadb.JadbException;
import se.vidstige.jadb.RemoteFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Service
public class GrabScreenService {


//    @Autowired
//    JadbConnectionUniversal jadbConnection;

//    @Autowired
//    JadbConnection jadbConnection;

    public byte[] grabScreen() throws IOException, JadbException {
//        JadbConnection jadbConnection = new JadbConnection();
//       // JadbDevice device = (JadbDevice) jadbConnection.getJadbConnection().getDevices().get(1);
//        JadbDevice device = (JadbDevice) jadbConnection.getDevices().get(1);
//
//
//        device.executeShell("screencap -p /sdcard/screencap.png");
//        device.pull(new RemoteFile("/sdcard/screencap.png"), new File("screencap.png"));

        ADBExecutor.executeADBCommand("adb shell screencap -p /sdcard/screencap.png");
        ADBExecutor.executeADBCommand("adb pull /sdcard/screencap.png");

//        GrabScreenThread grabScreenThread = new GrabScreenThread();
//        grabScreenThread.start();
//
//        try {
//            grabScreenThread.join();
//        } catch(InterruptedException e) {
//            e.printStackTrace();
//        }

//        byte[] data = null;
        byte[] data = imageToByteStream();
//        try {
//            data = imageToByteStream();
//        }
//        catch(Exception e) {
//            data = new byte[1];
//        }

        return data;

    }

    public byte[] imageToByteStream() throws IOException {
        BufferedImage bImage = ImageIO.read(new File("screencap.png"));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "png", bos );
        byte [] data = bos.toByteArray();

        return data;
    }
}
