package com.zzf.cor.impl;

import com.zzf.cor.Collider;
import com.zzf.entity.GameObject;
import com.zzf.entity.Tank;

public class TankTankCollider implements Collider {
    @Override
    public void collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Tank){
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            if(t1.getRect().intersects(t2.getRect())){
                t1.stop();
                t2.stop();
            }
        }
    }
}
