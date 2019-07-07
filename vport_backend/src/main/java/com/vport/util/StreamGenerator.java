package com.vport.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class StreamGenerator {

    Logger logger = LoggerFactory.getLogger(StreamGenerator.class);

    public static byte[] imageToByteStream() throws IOException {
        BufferedImage bImage = ImageIO.read(new File("screencap.png"));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "png", bos );
        byte [] data = bos.toByteArray();

        return data;
    }
}
