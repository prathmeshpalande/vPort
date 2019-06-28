package com.vport.Service;

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

    @Autowired
    JadbConnection jadbConnection;
    public byte[] grabScreen() throws IOException, JadbException {
        JadbDevice device = jadbConnection.getDevices().get(0);

        device.executeShell("screencap -p /sdcard/screencap.png");
        device.pull(new RemoteFile("/sdcard/screencap.png"), new File("screencap.png"));

        BufferedImage bImage = ImageIO.read(new File("screencap.png"));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "png", bos );
        byte [] data = bos.toByteArray();


        return data;

    }
}
