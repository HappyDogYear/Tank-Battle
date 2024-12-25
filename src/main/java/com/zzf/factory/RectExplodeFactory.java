package com.zzf.factory;

import com.zzf.entity.*;
import com.zzf.enums.DirectionEnums;
import com.zzf.enums.GroupEnums;
import com.zzf.tank.TankFrame;

public class RectExplodeFactory extends GameFactory {
    @Override
    public BaseTank createTank(int x, int y, DirectionEnums directionEnums, GroupEnums groupEnums, TankFrame tankFrame) {
        return null;
    }

    @Override
    public BaseBullet createBullet(int x, int y, DirectionEnums directionEnums, GroupEnums groupEnums, TankFrame tankFrame) {
        return new RectBullet(x, y, directionEnums, groupEnums, tankFrame);
    }


    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tankFrame) {
        return new RectExplode(x, y, tankFrame);
    }
}
