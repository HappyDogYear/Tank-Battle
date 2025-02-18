package com.zzf.factory;

import com.zzf.enums.GroupEnums;

import java.awt.*;

public abstract class BaseTank {

    public GroupEnums groupEnums = GroupEnums.BAD;

    public Rectangle rect = new Rectangle();

    public abstract void paint(Graphics g);


    public GroupEnums getGroup(){
        return groupEnums;
    }

    public abstract void die();

    public abstract int getX();

    public abstract int getY();

}
