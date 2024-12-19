package com.zzf.test;

import com.zzf.entity.Audio;
import com.zzf.entity.Tank;
import com.zzf.enums.DirectionEnums;
import com.zzf.enums.GroupEnums;
import com.zzf.tank.TankFrame;
import com.zzf.utils.ConfigUtils;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainTest {

    public static void main(String[] args) throws InterruptedException {

        // 版本1
       // test1();

        //版本2
        test2();
    }

    public static void test1(){
        Frame frame = new Frame();
        //大小  长和宽
        frame.setSize(800,600);
        //能否改变大小
        frame.setResizable(false);

        frame.setVisible(true);
        //标题
        frame.setTitle("tank war");
        //关闭窗口
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }


    public static void test2() throws InterruptedException {
        TankFrame frame = new TankFrame();

        int enemyTankNums = Integer.parseInt((String) ConfigUtils.getKey("enemyTankNums"));
        //初始化敌方tank
        for (int i = 0; i < enemyTankNums; i++) {
            frame.tanks.add(new Tank(50+i*80, 200, DirectionEnums.DOWN, frame, GroupEnums.BAD));
        }

        //加入游戏背景音效
        new Thread(() -> new Audio("audio/war1.wav").loop()).start();

        while (true){
            Thread.sleep(50);
            frame.repaint();
        }
    }


}
