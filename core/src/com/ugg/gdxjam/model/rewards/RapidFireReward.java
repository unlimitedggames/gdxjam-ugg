package com.ugg.gdxjam.model.rewards;

import com.badlogic.gdx.utils.Pools;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.components.FuelComponent;
import com.ugg.gdxjam.model.components.WeaponsComponent;

/**
 * Created by Jose Cuellar on 16/01/2016.
 */
public class RapidFireReward implements Reward{

    float amount = 0.02f;

    public RapidFireReward(){}

    public RapidFireReward(float amount){
        this.amount = amount;
    }

    @Override
    public void applyReward(GameEntity gameEntity) {
        WeaponsComponent weaponC = Mappers.weapon.get(gameEntity.entity);
        weaponC.weapons.get(0).interval.limit -= amount;
    }

    @Override
    public void reset() {
        amount = 0.02f;
    }

    @Override
    public Reward cloneObject() {
        RapidFireReward rapidFireReward = Pools.obtain(RapidFireReward.class);
        rapidFireReward.amount = this.amount;
        return rapidFireReward;
    }
}
