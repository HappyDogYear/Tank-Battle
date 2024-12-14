package com.zzf.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageUtils {

    public static BufferedImage tankL;
    public static BufferedImage tankU;
    public static BufferedImage tankR;
    public static BufferedImage tankD;

    public static BufferedImage bulletL;
    public static BufferedImage bulletU;
    public static BufferedImage bulletR;
    public static BufferedImage bulletD;

    static {
        try {
            tankL = ImageIO.read(ImageUtils.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            tankU = ImageIO.read(ImageUtils.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            tankR = ImageIO.read(ImageUtils.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
            tankD = ImageIO.read(ImageUtils.class.getClassLoader().getResourceAsStream("images/tankD.gif"));


            bulletL = ImageIO.read(ImageUtils.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
            bulletU = ImageIO.read(ImageUtils.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
            bulletR = ImageIO.read(ImageUtils.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
            bulletD = ImageIO.read(ImageUtils.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
