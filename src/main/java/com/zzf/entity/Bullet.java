package com.zzf.entity;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zzf.enums.DirectionEnums;
import com.zzf.enums.GroupEnums;
import com.zzf.tank.TankFrame;
import com.zzf.utils.ImageUtils;
import lombok.Getter;

import java.awt.*;

/**
 * 子弹
 */
@Getter
public class Bullet {

    // 子弹位置
    private int x;
    private int y;

    // 子弹方向
    private DirectionEnums directionEnums;
    private GroupEnums groupEnums;

    // 子弹宽度和高度
    public static final int WIDTH = ImageUtils.bulletD.getWidth();
    public static final int HEIGHT = ImageUtils.bulletD.getHeight();

    // 子弹速度
    private static final int SPEED = 5;

    //子弹存活状态
    private boolean living = true;

    TankFrame tankFrame = null;

    Rectangle rect = new Rectangle();

    public Bullet(int x, int y, DirectionEnums directionEnums, TankFrame tankFrame, GroupEnums groupEnums) {
        this.x = x;
        this.y = y;
        this.directionEnums = directionEnums;
        this.tankFrame = tankFrame;
        this.groupEnums = groupEnums;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public void paint(Graphics g) {
        if(!living){
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

        rect.x = x;
        rect.y = y;

        if( x <0 || y <0 || x >= TankFrame.GAME_WIDTH || y >= TankFrame.GAME_HEIGHT ){
            living = false;
        }
    }

    public void collideWith(Tank tank) {

        if(this.groupEnums == tank.getGroupEnums()){
            return;
        }

        // 多次new 会产生很多垃圾碎片，垃圾回收器执行频率也高
        // Rectangle bulletRect = new Rectangle(x, y, WIDTH, HEIGHT);
        // Rectangle tankRect = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);

        if (this.rect.intersects(tank.rect)) {
            tank.die();
            this.die();

            int ex = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int ey = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;

            tankFrame.explodes.add(new Explode(ex, ey, tankFrame));
        }
    }

    private void die() {
        this.living = Boolean.FALSE;
    }
}
