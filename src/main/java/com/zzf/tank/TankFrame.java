package com.zzf.tank;

import java.awt.*;
import java.awt.event.*;

public class TankFrame extends Frame {

    int x = 200;
    int y = 200;

    public TankFrame(){
        //大小  长和宽
        setSize(800,600);
        //能否改变大小
        setResizable(false);

        setVisible(true);
        //标题
        setTitle("tank war");

        //添加 键盘监听事件
        this.addKeyListener(new MyKeyListener());

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

        // 版本2 填充一个矩形
        //g.fillRect(200, 200, 50, 50);

        //版本3 想让矩形动起来 win + d 算一次重新绘制， 可以看到效果
        g.fillRect(x, y, 50, 50);
        x += 30;
        y += 30;




    }

    /**
     * 内部类
     */
    class MyKeyListener extends KeyAdapter {

        //键盘按下调用
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("key pressed");
        }

        //键盘抬起调用
        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("key released");
        }
    }
}
