package com.zzf.factory;

import com.zzf.entity.Bullet;
import com.zzf.entity.Explode;
import com.zzf.entity.Tank;
import com.zzf.enums.DirectionEnums;
import com.zzf.enums.GroupEnums;
import com.zzf.model.GameModel;
import com.zzf.tank.TankFrame;

public class DefaultFactory extends GameFactory{
    @Override
    public BaseTank createTank(int x, int y, DirectionEnums directionEnums, GroupEnums groupEnums, GameModel gm) {
        return new Tank(x, y, directionEnums, gm, groupEnums);
    }

    @Override
    public BaseBullet createBullet(int x, int y, DirectionEnums directionEnums, GroupEnums groupEnums, GameModel gm) {
        return  new Bullet(x, y, directionEnums, groupEnums, gm);
    }


    @Override
    public BaseExplode createExplode(int x, int y, GameModel gm) {
        return new Explode(x, y, gm);
    }
}
