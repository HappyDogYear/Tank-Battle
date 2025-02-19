package com.zzf.entity;

import java.awt.*;

/**
 * 经典的用接口 还是抽象类
 * 名词用抽象类， 形容词用接口
 *
 * 游戏物体父类
 */
public abstract class GameObject {

    // 每个游戏物体都要有位置
    int x, y;

    // 每个游戏物体如何画出
    public abstract void paint(Graphics g);
}
