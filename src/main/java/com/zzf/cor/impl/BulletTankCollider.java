package com.zzf.cor.impl;

import com.zzf.cor.Collider;
import com.zzf.entity.Bullet;
import com.zzf.entity.Explode;
import com.zzf.entity.GameObject;
import com.zzf.entity.Tank;

public class BulletTankCollider implements Collider {
    @Override
    public void collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Tank){
            Bullet bullet = (Bullet) o1;
            Tank tank = (Tank) o2;
            collideWith(bullet, tank);
         }else if(o1 instanceof Tank && o2 instanceof Bullet){
            collide(o2, o1);
        }else {
            return;
        }
    }


    private void collideWith(Bullet bullet, Tank tank){
        if(bullet.getGroupEnums() == tank.getGroupEnums()){
            return;
        }

        if (bullet.getRect().intersects(tank.getRect())) {
            tank.die();
            bullet.die();

            int ex = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int ey = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;

            bullet.getGm().add(new Explode(ex, ey, bullet.getGm()));
        }
    }
}
