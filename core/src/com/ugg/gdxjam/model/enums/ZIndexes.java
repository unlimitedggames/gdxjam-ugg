package com.ugg.gdxjam.model.enums;

/**
 * Created by Jose Cuellar on 06/01/2016.
 */
public enum ZIndexes {

    LifeBar(9),
    ParticleUp(8),
    Ally(6),
    Enemy(7),
    EnemyBullet(5),
    AllyBullet(4),
    ParticleDown(3),
    Scenario(1),
    Reward(2);


    private final int data;

    private ZIndexes(
            int data)
    {
        this.data = data;
    }

    public int get()
    {
        return data;
    }

}
