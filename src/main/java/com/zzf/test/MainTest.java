package com.zzf.test;

import com.zzf.tank.TankFrame;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainTest {

    public static void main(String[] args) throws InterruptedException {

        // 版本1
//        test1();

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

        while (true){
            Thread.sleep(50);
            frame.repaint();
        }
    }


}
