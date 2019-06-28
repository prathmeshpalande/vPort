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

    public byte[] grabScreen() throws IOException, JadbException {
        ADBExecutor.executeADBCommand("adb shell screencap -p /sdcard/screencap.png");
        ADBExecutor.executeADBCommand("adb pull /sdcard/screencap.png");

        byte[] data = imageToByteStream();

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
