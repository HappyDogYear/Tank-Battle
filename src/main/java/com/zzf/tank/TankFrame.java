package com.zzf.tank;

import com.zzf.entity.*;
import com.zzf.enums.DirectionEnums;
import com.zzf.enums.GroupEnums;
import com.zzf.model.GameModel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TankFrame extends Frame {

    GameModel gm = new GameModel();

    public static final int GAME_WIDTH = 900;
    public static final int GAME_HEIGHT = 800;

    // /**
    //  * 不加 pulic 无法访问属性
    //  * 与案例不同的是， 我的两个类在不同的包下， 默认访问是在同一个包的类可以访问
    //  */
    // public Bullet bullet = new Bullet(100, 100, DirectionEnums.DOWN);

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
        gm.paint(g);
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

            confirmDirAndFire(e, true, false);

            // 设置tank的方向
            setTankDirection();

        }


        // 键盘抬起调用
        @Override
        public void keyReleased(KeyEvent e) {
            confirmDirAndFire(e, false, true);
            // 设置tank的方向 我觉得加不加不影响后面的操作，到后面再验证
            setTankDirection();
        }

        /**
         * 设置tank的方向
         */
        private void setTankDirection() {
            Tank mainTank = gm.getMainTank();
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

        /**
         * 确认方向和开火
         */
        private void confirmDirAndFire(KeyEvent e, Boolean dirBool, Boolean fireBool){
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    bL = dirBool;
                    break;

                case KeyEvent.VK_UP:
                    bU = dirBool;
                    break;

                case KeyEvent.VK_RIGHT:
                    bR = dirBool;
                    break;

                case KeyEvent.VK_DOWN:
                    bD = dirBool;
                    break;

                case KeyEvent.VK_CONTROL:
                    if(fireBool){
                        gm.getMainTank().fire();
                    }
                    break;

                default:
                    break;
            }
        }
    }
}
