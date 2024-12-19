package com.zzf.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageUtils {

    public static BufferedImage tankL;
    public static BufferedImage tankU;
    public static BufferedImage tankR;
    public static BufferedImage tankD;

    public static BufferedImage badTankL;
    public static BufferedImage badTankU;
    public static BufferedImage badTankR;
    public static BufferedImage badTankD;

    public static BufferedImage bulletL;
    public static BufferedImage bulletU;
    public static BufferedImage bulletR;
    public static BufferedImage bulletD;

    public static BufferedImage[] explodes = new BufferedImage[16];
    static {
        try {
            tankU = ImageIO.read(ImageUtils.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            tankL = rotateImage(tankU, -90);
            tankR = rotateImage(tankU, 90);
            tankD = rotateImage(tankU, 180);

            badTankU = ImageIO.read(ImageUtils.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankL = rotateImage(badTankU, -90);
            badTankR = rotateImage(badTankU, 90);
            badTankD = rotateImage(badTankU, 180);

            bulletU = ImageIO.read(ImageUtils.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = rotateImage(bulletU, -90);
            bulletR = rotateImage(bulletU, 90);
            bulletD = rotateImage(bulletU, 180);

            for (int i = 0; i < 16; i++) {
                explodes[i] = ImageIO.read(ImageUtils.class.getClassLoader().getResourceAsStream("images/e" + (i+1) + ".gif"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 图片旋转
     * @param bufferedimage
     * @param degree
     * @return
     */
    public static BufferedImage rotateImage(BufferedImage bufferedimage, int degree) {
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w, h, type))
                .createGraphics()).setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        return img;
    }
}
