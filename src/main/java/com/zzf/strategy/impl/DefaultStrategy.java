package com.zzf.strategy.impl;

import com.zzf.entity.Bullet;
import com.zzf.entity.Tank;
import com.zzf.strategy.FireStrategy;

/**
 * 默认开火策略
 */
public class DefaultStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        int bx = tank.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        // new Bullet(bx, by, tank.getDirectionEnums(), tank.getTankFrame(), tank.getGroupEnums());
        tank.getGm().gameFactory.createBullet(bx, by, tank.getDirectionEnums(), tank.getGroupEnums(), tank.getGm());
    }
}
