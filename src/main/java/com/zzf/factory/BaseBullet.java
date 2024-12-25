package com.zzf.factory;

import com.zzf.entity.Tank;

import java.awt.*;

public abstract class BaseBullet {

    public abstract void paint(Graphics g);

    public abstract void collideWith(Tank t);
}
