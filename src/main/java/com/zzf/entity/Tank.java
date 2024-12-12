package com.zzf.entity;

import com.zzf.enums.DirectionEnums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

/**
 * 坦克类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tank {

    // tank的位置
    private int x;
    private int y;

    //tank的方向
    private DirectionEnums directionEnums;

    //tank的速度
    private static final int SPEED = 10;


    public void paint(Graphics g) {
        g.fillRect(x, y, 50, 50);

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
