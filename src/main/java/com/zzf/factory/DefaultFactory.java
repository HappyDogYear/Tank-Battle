package com.zzf.factory;

import com.zzf.entity.Explode;
import com.zzf.enums.DirectionEnums;
import com.zzf.enums.GroupEnums;
import com.zzf.tank.TankFrame;

public class DefaultFactory extends GameFactory{
    @Override
    public BaseTank createTank(int x, int y, DirectionEnums directionEnums, GroupEnums groupEnums, TankFrame tankFrame) {
        return null;
    }

    @Override
    public BaseBullet createBullet(int x, int y, DirectionEnums directionEnums, GroupEnums groupEnums, TankFrame tankFrame) {
        return null;
    }


    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tankFrame) {
        return new Explode(x, y, tankFrame);
    }
}
