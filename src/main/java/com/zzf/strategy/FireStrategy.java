package com.zzf.strategy;


import com.zzf.entity.Tank;

/**
 * 开火 策略
 */
public interface FireStrategy {

    void fire(Tank tank);
}
