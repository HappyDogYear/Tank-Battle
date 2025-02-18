package com.zzf.factory;

import com.zzf.entity.Bullet;
import com.zzf.entity.Explode;
import com.zzf.entity.Tank;
import com.zzf.enums.DirectionEnums;
import com.zzf.enums.GroupEnums;
import com.zzf.tank.TankFrame;

public class DefaultFactory extends GameFactory {
    @Override
    public BaseTank createTank(int x, int y, DirectionEnums directionEnums, GroupEnums groupEnums, TankFrame tankFrame) {
        return new Tank(x, y, directionEnums, tankFrame, groupEnums);
    }

    @Override
    public BaseBullet createBullet(int x, int y, DirectionEnums directionEnums, GroupEnums groupEnums, TankFrame tankFrame) {
        return new Bullet(x, y, directionEnums, groupEnums, tankFrame);
    }


    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tankFrame) {
        return new Explode(x, y, tankFrame);
    }
}
