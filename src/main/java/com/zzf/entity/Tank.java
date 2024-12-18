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
                if(this.groupEnums == GroupEnums.BAD){
                    g.drawImage(ImageUtils.badTankL, x, y, null);
                }else {
                    g.drawImage(ImageUtils.tankL, x, y, null);
                }
                break;
            case UP:
                if(this.groupEnums == GroupEnums.BAD){
                    g.drawImage(ImageUtils.badTankU, x, y, null);
                }else {
                    g.drawImage(ImageUtils.tankU, x, y, null);
                }
                break;
            case RIGHT:
                if(this.groupEnums == GroupEnums.BAD){
                    g.drawImage(ImageUtils.badTankR, x, y, null);
                }else {
                    g.drawImage(ImageUtils.tankR, x, y, null);
                }
                break;
            case DOWN:
                if(this.groupEnums == GroupEnums.BAD){
                    g.drawImage(ImageUtils.badTankD, x, y, null);
                }else {
                    g.drawImage(ImageUtils.tankD, x, y, null);
                }

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

        // 目标是让敌方坦克开火  但是现在没有开火 ？
        // 问题解决， 因为 moving 默认是false move方法不生效
        // tank要分清敌我，就得有个属性来标记敌我 , 同样子弹也要有
        if (this.groupEnums == GroupEnums.BAD && random.nextInt(100) > 95) {
            this.fire();
        }

        if (this.groupEnums == GroupEnums.BAD && random.nextInt(100) > 95) {
            randomDirection();
        }

        //边界检测
        boundsCheck();
    }

    private void boundsCheck() {
        if(this.x < 0){
            x = 2;
        }
        if(this.y < 30){
            y = 30;
        }
        if(this.x > TankFrame.GAME_WIDTH - Tank.WIDTH){
            x = TankFrame.GAME_WIDTH - Tank.WIDTH;
        }
        if(this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT){
            y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
        }

    }

    public void fire() {
        // 这样的话， 子弹都是从tank中心发出
        int bx = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        tankFrame.bullets.add(new Bullet(bx, by, directionEnums, this.tankFrame, this.groupEnums));
    }

    public void die() {
        this.living = Boolean.FALSE;
    }

    /**
     * 随机方向
     */
    private void randomDirection() {
        this.directionEnums = DirectionEnums.values()[random.nextInt(4)];
    }
}
