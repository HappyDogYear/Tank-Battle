package com.zzf.tank;

import java.awt.*;
import java.awt.event.*;

public class TankFrame extends Frame {

    int x = 200;
    int y = 200;

    public TankFrame() {
        // 大小  长和宽
        setSize(800, 600);
        // 能否改变大小
        setResizable(false);

        setVisible(true);
        // 标题
        setTitle("tank war");

        // 添加 键盘监听事件
        this.addKeyListener(new MyKeyListener());

        // 关闭窗口
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * 会自动调用  在窗口需要重新绘制的时候
     *
     * @param g 一支画笔
     */
    @Override
    public void paint(Graphics g) {

        // 版本2 填充一个矩形
        // g.fillRect(200, 200, 50, 50);

        // 版本3 想让矩形动起来 win + d 算一次重新绘制， 可以看到效果
        g.fillRect(x, y, 50, 50);
        // x += 30;
        // y += 30;


    }

    /**
     * 内部类
     */
    class MyKeyListener extends KeyAdapter {

        // 用于判断方向， 实现同时按左上， 斜着走的功能
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        // 键盘按下调用
        @Override
        public void keyPressed(KeyEvent e) {
            // x += 200;
            // 会默认调用 paint 方法
            // repaint();

            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;

                case KeyEvent.VK_UP:
                    bU = true;
                    break;

                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;

                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;

                default:
                    break;
            }

            if (bL) {
                x -= 10;
            }
            if (bU) {
                y -= 10;
            }
            if (bR) {
                x += 10;
            }
            if (bD) {
                y += 10;
            }

        }

        // 键盘抬起调用
        @Override
        public void keyReleased(KeyEvent e) {

            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;

                case KeyEvent.VK_UP:
                    bU = false;
                    break;

                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;

                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;

                default:
                    break;
            }
        }
    }
}
