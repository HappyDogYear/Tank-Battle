package com.zzf.strategy;

import com.zzf.entity.Bullet;
import com.zzf.entity.Tank;
import com.zzf.enums.DirectionEnums;

public class FourStrategy implements FireStrategy{
    @Override
    public void fire(Tank tank) {
        int bx = tank.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        for (DirectionEnums value : DirectionEnums.values()) {
            new Bullet(bx, by, value, tank.getTankFrame(), tank.getGroupEnums());
        }
    }
}
