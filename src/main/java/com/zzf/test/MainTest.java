package com.zzf.test;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainTest {

    public static void main(String[] args) {
        System.out.println("tank battle start");

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
}
