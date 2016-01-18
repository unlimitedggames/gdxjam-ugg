package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.ugg.gdxjam.model.enums.RewardDataType;
import com.ugg.gdxjam.model.rewards.Reward;

/**
 * Created by Jose Cuellar on 16/01/2016.
 */
public class RewardTypeComponent implements Component, Pool.Poolable {

    public RewardDataType rewardType;

    @Override
    public void reset() {
        rewardType = null;
    }
}
