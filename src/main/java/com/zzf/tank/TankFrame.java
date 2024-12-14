package com.zzf.tank;

import com.zzf.entity.Bullet;
import com.zzf.entity.Tank;
import com.zzf.enums.DirectionEnums;

import java.awt.*;
import java.awt.event.*;

public class TankFrame extends Frame {

    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 600;

    Tank mainTank = new Tank(200, 200, DirectionEnums.DOWN);
    Bullet bullet = new Bullet(100, 100, DirectionEnums.DOWN);

    Image offScreenImage = null;

    public TankFrame() {
        // 大小  长和宽
        setSize(GAME_WIDTH, GAME_HEIGHT);
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
        // g.fillRect(x, y, 50, 50);
        // x += 30;
        // y += 30;


        //画出tank
        mainTank.paint(g);

        //画子弹
        bullet.paint(g);
    }

    /**
     * 双缓冲 解决闪烁问题
     * @param g the specified Graphics window
     */
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = createImage(GAME_WIDTH, GAME_HEIGHT);
        }

        Graphics gOffScreen = offScreenImage.getGraphics();
        Color color = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(color);
        paint(gOffScreen);

        g.drawImage(offScreenImage, 0, 0, null);
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

            // 设置tank的方向
            setTankDirection();

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

            // 设置tank的方向 我觉得加不加不影响后面的操作，到后面再验证
            setTankDirection();
        }

        /**
         * 设置tank的方向
         */
        private void setTankDirection() {
            mainTank.setMoving(Boolean.TRUE);

            if (bL) {
                mainTank.setDirectionEnums(DirectionEnums.LEFT);
            }
            if (bU) {
                mainTank.setDirectionEnums(DirectionEnums.UP);
            }
            if (bR) {
                mainTank.setDirectionEnums(DirectionEnums.RIGHT);
            }
            if (bD) {
                mainTank.setDirectionEnums(DirectionEnums.DOWN);
            }

            if(!bL && !bU && !bR && !bD) {
                mainTank.setMoving(Boolean.FALSE);
            }
        }
    }
}
