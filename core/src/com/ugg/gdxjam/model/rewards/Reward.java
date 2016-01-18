package com.ugg.gdxjam.model.rewards;

import com.badlogic.gdx.utils.Pool;
import com.ugg.gdxjam.model.Clonable;
import com.ugg.gdxjam.model.GameEntity;

/**
 * Created by Jose Cuellar on 16/01/2016.
 */
public interface Reward extends Pool.Poolable, Clonable<Reward>{

    public void applyReward(GameEntity gameEntity);

}
