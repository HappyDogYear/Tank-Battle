package com.zzf.entity;

import com.zzf.tank.TankFrame;
import com.zzf.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.*;

/**
 * 爆炸类
 */
public class Explode {

    public static int WIDTH = ImageUtils.explodes[0].getWidth();
    public static int HEIGHT = ImageUtils.explodes[0].getHeight();

    //爆炸的位置
    private int x;
    private int y;

    //是否存活
    private boolean living = true;

    TankFrame tankFrame = null;

    private int step = 0;


    public Explode(int x, int y, TankFrame tankFrame){
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;

        new Audio("audio/explode.wav").play();
    }

    public void paint(Graphics g){

        g.drawImage(ImageUtils.explodes[step++], x, y, null);

        if(step >= ImageUtils.explodes.length){
            step = 0;
        }
    }
}
