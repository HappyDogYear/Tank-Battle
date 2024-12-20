package com.zzf.entity;

import com.zzf.enums.DirectionEnums;
import com.zzf.enums.GroupEnums;
import com.zzf.strategy.DefaultStrategy;
import com.zzf.strategy.FireStrategy;
import com.zzf.strategy.FourStrategy;
import com.zzf.tank.TankFrame;
import com.zzf.utils.ImageUtils;
import lombok.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 坦克类
 */
@Setter
@Getter
public class Tank {

    // tank的位置  xy是tank的左上角
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

    Rectangle rect = new Rectangle();

    FireStrategy def;

    public static final int WIDTH = ImageUtils.tankD.getWidth();
    public static final int HEIGHT = ImageUtils.tankD.getHeight();

    public Tank(int x, int y, DirectionEnums directionEnums, TankFrame tankFrame, GroupEnums groupEnums) {
        this.x = x;
        this.y = y;
        this.directionEnums = directionEnums;
        this.tankFrame = tankFrame;
        this.groupEnums = groupEnums;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        //开火策略 我方tank单发，敌方tank四个方向都发射
        def = GroupEnums.isGood(this.groupEnums) ? new DefaultStrategy() : new FourStrategy();
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

        BufferedImage image = null;
        switch (directionEnums) {
            case LEFT:
                image = GroupEnums.isGood(this.groupEnums) ? ImageUtils.tankL : ImageUtils.badTankL;
                break;
            case UP:
                image = GroupEnums.isGood(this.groupEnums) ? ImageUtils.tankU : ImageUtils.badTankU;
                break;
            case RIGHT:
                image = GroupEnums.isGood(this.groupEnums) ? ImageUtils.tankR : ImageUtils.badTankR;
                break;
            case DOWN:
                image = GroupEnums.isGood(this.groupEnums) ? ImageUtils.tankD : ImageUtils.badTankD;
                break;
        }
        g.drawImage(image, x, y, null);

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
        if (!GroupEnums.isGood(this.groupEnums) && random.nextInt(100) > 95) {
            this.fire();
        }

        if (!GroupEnums.isGood(this.groupEnums) && random.nextInt(100) > 95) {
            randomDirection();
        }

        // 边界检测
        boundsCheck();

        rect.x = x;
        rect.y = y;
    }

    private void boundsCheck() {
        if (this.x < 2) {
            x = 2;
        }
        if (this.y < 28) {
            y = 28;
        }
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2) {
            x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        }
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) {
            y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
        }

    }

    public void fire() {
        // 这样的话， 子弹都是从tank中心发出
        // int bx = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        // int by = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        // tankFrame.bullets.add(new Bullet(bx, by, directionEnums, this.tankFrame, this.groupEnums));

        def.fire(this);
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
