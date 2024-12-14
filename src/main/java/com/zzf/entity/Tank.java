package com.zzf.entity;

import com.zzf.enums.DirectionEnums;
import com.zzf.tank.TankFrame;
import com.zzf.utils.ImageUtils;
import lombok.*;

import java.awt.*;

/**
 * 坦克类
 */
@Setter
public class Tank {

    // tank的位置
    private int x;
    private int y;

    //tank的方向
    private DirectionEnums directionEnums;

    //tank的速度
    private static final int SPEED = 3;
    //tank是否停止
    private boolean moving = false;

    private TankFrame tankFrame;

    public Tank(int x, int y, DirectionEnums directionEnums, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.directionEnums = directionEnums;
        this.tankFrame = tankFrame;
    }


    public void paint(Graphics g) {
        //绘制
        // Color color = g.getColor();
        // g.setColor(Color.YELLOW);
        // g.fillRect(x, y, 50, 50);
        // //todo 从测试来看，可以不要
        // g.setColor(color);

        switch (directionEnums){
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

        //移动
        move();
    }


    private void move(){
        if(!moving){
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
    }

    public void fire() {
        tankFrame.bullets.add(new Bullet(this.x, this.y, directionEnums, this.tankFrame));
    }
}
