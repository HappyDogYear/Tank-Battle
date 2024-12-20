package com.zzf.strategy;

import com.zzf.entity.Bullet;
import com.zzf.entity.Tank;

/**
 * 默认开火策略
 */
public class DefaultStrategy implements FireStrategy{
    @Override
    public void fire(Tank tank) {
        int bx = tank.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        new Bullet(bx, by, tank.getDirectionEnums(), tank.getTankFrame(), tank.getGroupEnums());
    }
}
