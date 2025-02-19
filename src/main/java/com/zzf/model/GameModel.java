package com.zzf.model;

import com.zzf.entity.Tank;
import com.zzf.enums.DirectionEnums;
import com.zzf.enums.GroupEnums;
import com.zzf.factory.*;
import com.zzf.utils.ConfigUtils;

import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

//门面
public class GameModel {

    Tank mainTank = new Tank(200, 400, DirectionEnums.DOWN, this, GroupEnums.GOOD);

    public List<BaseBullet> bullets = new CopyOnWriteArrayList<>();
    public List<Tank> tanks = new CopyOnWriteArrayList<>();
    public List<BaseExplode> explodes = new CopyOnWriteArrayList<>();
    public GameFactory gameFactory = new DefaultFactory();


    public GameModel(){

        int enemyTankNums = Integer.parseInt((String) ConfigUtils.getKey("enemyTankNums"));
        //初始化敌方tank
        for (int i = 0; i < enemyTankNums; i++) {
            tanks.add(new Tank(50+i*80, 200, DirectionEnums.DOWN, this, GroupEnums.BAD));
        }
    }


    public void paint(Graphics g) {
        // 版本2 填充一个矩形
        // g.fillRect(200, 200, 50, 50);

        // 版本3 想让矩形动起来 win + d 算一次重新绘制， 可以看到效果
        // g.fillRect(x, y, 50, 50);
        // x += 30;
        // y += 30;

        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量：" + bullets.size(), 10, 60);
        g.drawString("敌人的数量：" + tanks.size(), 10, 80);
        g.drawString("爆炸的数量：" + explodes.size(), 10, 100);
        g.setColor(color);

        //画出tank
        if(mainTank.isLiving()){
            mainTank.paint(g);
        }

        tanks.forEach(x -> {
            x.paint(g);
        });

        //画子弹
        // bullet.paint(g);

        // 用普通 ArrayList
        // 做子弹消失的操作时候， 会产生著名的并发修改异常
        // 可以使用 CopyOnWriteArrayList 解决  或者直接用普通的fori循环
        bullets.forEach(x -> {
            x.paint(g);
        });

        explodes.forEach(x -> {
            x.paint(g);
        });

        //此时，测试发现一个问题，主坦克在敌方tank的位置上打不出子弹
        //因为敌方tank挂掉之后，容器没有移除
        bullets.forEach(b -> {
            tanks.forEach(t -> {
                b.collideWith(t);
            });

            // if(b.getGroupEnums() == GroupEnums.BAD) {
            //     b.collideWith(mainTank);
            // }
        });

        // for (int i = 0; i < bullets.size(); i++) {
        //     bullets.get(i).paint(g);
        // }
    }

    public Tank getMainTank(){
        return mainTank;
    }
}
