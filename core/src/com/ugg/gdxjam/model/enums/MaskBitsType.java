package com.ugg.gdxjam.model.enums;

import com.ugg.gdxjam.model.data.RenderData;

import java.util.logging.Filter;

/**
 * Created by Jose Cuellar on 06/01/2016.
 */
public enum MaskBitsType {

    Ally(FilterCategory.ENEMY | FilterCategory.REWARD | FilterCategory.BULLET),
    Enemy(FilterCategory.ALLY | FilterCategory.BULLET),
    EnemyBullet(FilterCategory.BULLET | FilterCategory.ALLY | FilterCategory.SCENARIO),
    AllyBullet(FilterCategory.BULLET | FilterCategory.ENEMY | FilterCategory.SCENARIO),
    Scenario(FilterCategory.BULLET),
    Reward(FilterCategory.ALLY);


    private final int data;

    private MaskBitsType(
            int data)
    {
        this.data = data;
    }

    public int get()
    {
        return data;
    }

}
