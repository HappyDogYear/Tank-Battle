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

        // 绘制爆炸效果的时候会出现卡顿的情况
        // new Audio("audio/explode.wav").play();

        // 使用多线程，解决卡顿情况
        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g){

        g.drawImage(ImageUtils.explodes[step++], x, y, null);

        if(step >= ImageUtils.explodes.length){
            tankFrame.explodes.remove(this);
        }
    }
}
