package com.zzf.factory;

import com.zzf.entity.*;
import com.zzf.enums.DirectionEnums;
import com.zzf.enums.GroupEnums;
import com.zzf.model.GameModel;
import com.zzf.tank.TankFrame;

public class RectExplodeFactory extends GameFactory {
    @Override
    public BaseTank createTank(int x, int y, DirectionEnums directionEnums, GroupEnums groupEnums, GameModel gm) {
        return null;
    }

    @Override
    public BaseBullet createBullet(int x, int y, DirectionEnums directionEnums, GroupEnums groupEnums, GameModel gm) {
        return new RectBullet(x, y, directionEnums, groupEnums, gm);
    }


    @Override
    public BaseExplode createExplode(int x, int y, GameModel gm) {
        return new RectExplode(x, y, gm);
    }
}
