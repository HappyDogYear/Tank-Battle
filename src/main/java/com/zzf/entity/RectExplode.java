package com.zzf.entity;

import com.zzf.factory.BaseExplode;
import com.zzf.tank.TankFrame;
import com.zzf.utils.ImageUtils;

import java.awt.*;

/**
 * 爆炸类
 */
public class RectExplode extends BaseExplode {

    public static int WIDTH = ImageUtils.explodes[0].getWidth();
    public static int HEIGHT = ImageUtils.explodes[0].getHeight();

    //爆炸的位置
    private int x;
    private int y;

    //是否存活
    private boolean living = true;

    TankFrame tankFrame = null;

    private int step = 0;


    public RectExplode(int x, int y, TankFrame tankFrame){
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;

        // 绘制爆炸效果的时候会出现卡顿的情况
        // new Audio("audio/explode.wav").play();

        // 使用多线程，解决卡顿情况
        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    @Override
    public void paint(Graphics g){

        Color color = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x, y, 10, 10);
        // step++;

        // if(step >= 10){
            tankFrame.explodes.remove(this);
        // }

        g.setColor(color);
    }
}
