package com.zzf.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    public TankFrame(){
        //大小  长和宽
        setSize(800,600);
        //能否改变大小
        setResizable(false);

        setVisible(true);
        //标题
        setTitle("tank war");
        //关闭窗口
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * 会自动调用  在窗口需要重新绘制的时候
     * @param g 一支画笔
     */
    @Override
    public void paint(Graphics g) {

        //填充一个矩形
        g.fillRect(200, 200, 50, 50);

    }
}
