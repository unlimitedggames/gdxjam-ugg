package com.ugg.gdxjam.model.rewards;

import com.badlogic.gdx.utils.Pools;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.components.LifeComponent;

/**
 * Created by Jose Cuellar on 16/01/2016.
 */
public class LifeReward implements Reward{

    int amount = 3;

    public LifeReward(){}
    
    public LifeReward(int amount){
        this.amount = amount;
    }

    @Override
    public void applyReward(GameEntity gameEntity) {
        LifeComponent lifeC = Mappers.life.get(gameEntity.entity);
        lifeC.amount = Math.min(lifeC.amount + amount, lifeC.max);
    }

    @Override
    public void reset() {
        amount = 3;
    }

    @Override
    public Reward cloneObject() {
        LifeReward lifeReward = Pools.obtain(LifeReward.class);
        lifeReward.amount = this.amount;
        return lifeReward;
    }
}
