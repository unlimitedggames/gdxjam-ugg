package com.ugg.gdxjam.model.rewards;

import com.badlogic.gdx.utils.Pools;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.components.FuelComponent;
import com.ugg.gdxjam.model.components.LifeComponent;
import com.ugg.gdxjam.model.components.PlayerComponent;

/**
 * Created by Jose Cuellar on 16/01/2016.
 */
public class MotherShipReward implements Reward{


    public MotherShipReward(){}

    @Override
    public void applyReward(GameEntity gameEntity) {
        FuelComponent fuelC = Mappers.fuel.get(gameEntity.entity);
        fuelC.max = fuelC.max + 40;
        fuelC.amount = fuelC.max;
        LifeComponent lifeC = Mappers.life.get(gameEntity.entity);
        lifeC.max = lifeC.max + 10;
        lifeC.amount = lifeC.max;

        PlayerComponent playerC = Mappers.player.get(gameEntity.entity);
        playerC.player.currentEvents++;

        playerC.player.checkEvent();
    }

    @Override
    public void reset() {

    }

    @Override
    public Reward cloneObject() {
        MotherShipReward mShipReward = Pools.obtain(MotherShipReward.class);
        return mShipReward;
    }
}
