package com.zzf.entity;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zzf.enums.DirectionEnums;
import com.zzf.enums.GroupEnums;
import com.zzf.tank.TankFrame;
import com.zzf.utils.ImageUtils;
import lombok.*;

import java.awt.*;
import java.util.Random;

/**
 * 坦克类
 */
@Setter
@Getter
public class Tank {

    // tank的位置
    private int x;
    private int y;

    // tank的方向
    private DirectionEnums directionEnums;
    //
    private GroupEnums groupEnums;

    // tank的速度
    private static final int SPEED = 3;
    // tank是否停止
    private boolean moving = true;
    // tank是否存活
    private boolean living = true;

    private TankFrame tankFrame;

    private Random random = new Random();

    public static final int WIDTH = ImageUtils.tankD.getWidth();
    public static final int HEIGHT = ImageUtils.tankD.getHeight();

    public Tank(int x, int y, DirectionEnums directionEnums, TankFrame tankFrame, GroupEnums groupEnums) {
        this.x = x;
        this.y = y;
        this.directionEnums = directionEnums;
        this.tankFrame = tankFrame;
        this.groupEnums = groupEnums;
    }


    public void paint(Graphics g) {
        // 绘制
        // Color color = g.getColor();
        // g.setColor(Color.YELLOW);
        // g.fillRect(x, y, 50, 50);
        // //todo 从测试来看，可以不要
        // g.setColor(color);

        if (!living) {
            tankFrame.tanks.remove(this);
        }

        switch (directionEnums) {
            // todo 疑问，此处为什么不能  DirectionEnums.LEFT
            case LEFT:
                g.drawImage(ImageUtils.tankL, x, y, null);
                break;
            case UP:
                g.drawImage(ImageUtils.tankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ImageUtils.tankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ImageUtils.tankD, x, y, null);
                break;
            default:
                break;
        }

        // 移动
        move();
    }


    private void move() {
        if (!moving) {
            return;
        }

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

        if(x < 0 || y < 0 || x >= TankFrame.GAME_WIDTH || y >= TankFrame.GAME_HEIGHT) {
            tankFrame.tanks.remove(this);
            return;
        }

        // 目标是让敌方坦克开火  但是现在没有开火 ？
        // 问题解决， 因为 moving 默认是false move方法不生效
        // tank要分清敌我，就得有个属性来标记敌我 , 同样子弹也要有
        if(this.groupEnums == GroupEnums.BAD && random.nextInt(100) > 95){
            this.fire();
        }

        randomDirection();
    }

    public void fire() {
        // 这样的话， 子弹都是从tank中心发出
        int bx = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

        tankFrame.bullets.add(new Bullet(bx, by, directionEnums, this.tankFrame, this.groupEnums));
    }

    public void die() {
        this.living = Boolean.FALSE;
    }

    /**
     * 随机方向
     */
    private void randomDirection() {
        if(this.groupEnums == GroupEnums.BAD && random.nextInt(1000) > 950){
            this.directionEnums = DirectionEnums.values()[random.nextInt(4)];
        }
    }
}
