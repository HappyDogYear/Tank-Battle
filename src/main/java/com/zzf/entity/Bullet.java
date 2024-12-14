package com.zzf.entity;

import com.zzf.enums.DirectionEnums;
import com.zzf.tank.TankFrame;
import com.zzf.utils.ImageUtils;

import java.awt.*;

/**
 * 子弹
 */
public class Bullet {

    // 子弹位置
    private int x;
    private int y;

    // 子弹方向
    private DirectionEnums directionEnums;

    // 子弹宽度和高度
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;

    // 子弹速度
    private static final int SPEED = 5;

    //子弹存活状态
    private boolean live = true;

    TankFrame tankFrame = null;

    public Bullet(int x, int y, DirectionEnums directionEnums, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.directionEnums = directionEnums;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
        if(!live){
            tankFrame.bullets.remove(this);
        }

        // // 绘制子弹
        // Color color = g.getColor();
        // g.setColor(Color.RED);
        // g.fillOval(x, y, WIDTH, HEIGHT);
        // g.setColor(color);

        switch (directionEnums){
            // todo 疑问，此处为什么不能  DirectionEnums.LEFT
            case LEFT:
                g.drawImage(ImageUtils.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ImageUtils.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ImageUtils.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ImageUtils.bulletD, x, y, null);
                break;
            default:
                break;
        }

        // 移动
        move();
    }

    private void move() {
        switch (directionEnums) {
            case LEFT:
                x -= SPEED;
                break;

            case UP:
                y -= SPEED;
                break;

            case RIGHT:
                x += SPEED;
                break;

            case DOWN:
                y += SPEED;
                break;

            default:
                break;
        }

        if( x <0 || y <0 || x >= TankFrame.GAME_WIDTH || y >= TankFrame.GAME_HEIGHT ){
            live = false;
        }
    }
}
