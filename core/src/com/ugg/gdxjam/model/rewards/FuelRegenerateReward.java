package com.ugg.gdxjam.model.rewards;

import com.badlogic.gdx.utils.Pools;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.components.FuelComponent;

/**
 * Created by Jose Cuellar on 16/01/2016.
 */
public class FuelRegenerateReward implements Reward{

    int amount = 1;

    public FuelRegenerateReward(){}

    public FuelRegenerateReward(int amount){
        this.amount = amount;
    }

    @Override
    public void applyReward(GameEntity gameEntity) {
        FuelComponent fuelC = Mappers.fuel.get(gameEntity.entity);
        fuelC.regenerate += amount;
    }

    @Override
    public void reset() {
        amount = 1;
    }

    @Override
    public Reward cloneObject() {
        FuelRegenerateReward fuelRegenReward = Pools.obtain(FuelRegenerateReward.class);
        fuelRegenReward.amount = this.amount;
        return fuelRegenReward;
    }
}
