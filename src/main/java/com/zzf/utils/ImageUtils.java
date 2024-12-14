package com.zzf.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageUtils {

    public static BufferedImage tankL;
    public static BufferedImage tankU;
    public static BufferedImage tankR;
    public static BufferedImage tankD;

    static {
        try {
            tankL = ImageIO.read(ImageUtils.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            tankU = ImageIO.read(ImageUtils.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            tankR = ImageIO.read(ImageUtils.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
            tankD = ImageIO.read(ImageUtils.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
