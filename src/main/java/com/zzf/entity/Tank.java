package com.zzf.entity;

import com.zzf.enums.DirectionEnums;
import lombok.*;

import java.awt.*;

/**
 * 坦克类
 */
@Setter
@RequiredArgsConstructor
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

    public Tank(int x, int y, DirectionEnums directionEnums) {
        this.x = x;
        this.y = y;
        this.directionEnums = directionEnums;
    }


    public void paint(Graphics g) {
        //绘制
        Color color = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 50, 50);
        //todo 从测试来看，可以不要
        g.setColor(color);

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
}
