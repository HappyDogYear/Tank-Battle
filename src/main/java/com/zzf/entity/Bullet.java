package com.zzf.entity;

import com.zzf.enums.DirectionEnums;

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

    public Bullet(int x, int y, DirectionEnums directionEnums) {
        this.x = x;
        this.y = y;
        this.directionEnums = directionEnums;
    }

    public void paint(Graphics g) {
        // 绘制子弹

        Color color = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(color);

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
    }
}
