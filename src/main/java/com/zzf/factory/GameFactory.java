package com.zzf.factory;

import com.zzf.enums.DirectionEnums;
import com.zzf.enums.GroupEnums;
import com.zzf.model.GameModel;
import com.zzf.tank.TankFrame;

public abstract class GameFactory {


    public abstract BaseTank createTank(int x, int y, DirectionEnums directionEnums, GroupEnums groupEnums, GameModel gm);
    public abstract BaseBullet createBullet(int x, int y, DirectionEnums directionEnums, GroupEnums groupEnums, GameModel gm);
    public abstract BaseExplode createExplode(int x, int y, GameModel gm);
}
