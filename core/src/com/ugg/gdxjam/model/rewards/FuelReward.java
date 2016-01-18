package com.ugg.gdxjam.model.rewards;

import com.badlogic.gdx.utils.Pools;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.components.FuelComponent;
import com.ugg.gdxjam.model.components.LifeComponent;

/**
 * Created by Jose Cuellar on 16/01/2016.
 */
public class FuelReward implements Reward{

    int amount = 10;

    public FuelReward(){}

    public FuelReward(int amount){
        this.amount = amount;
    }

    @Override
    public void applyReward(GameEntity gameEntity) {
        FuelComponent fuelC = Mappers.fuel.get(gameEntity.entity);
        fuelC.amount = Math.min(fuelC.amount + amount, fuelC.max);
    }

    @Override
    public void reset() {
        amount = 10;
    }

    @Override
    public Reward cloneObject() {
        FuelReward lifeReward = Pools.obtain(FuelReward.class);
        lifeReward.amount = this.amount;
        return lifeReward;
    }
}
