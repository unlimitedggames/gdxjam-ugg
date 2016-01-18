package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.TimeCounter;

/**
 * Created by Jose Cuellar on 10/01/2016.
 */
public class LockOnComponent implements Component, Pool.Poolable {
    public GameEntity origin;
    public GameEntity target;
    public TimeCounter lockOnTime = new TimeCounter(-1f);
    public boolean updateTargetLocation = true;


    @Override
    public void reset() {
        origin = null;
        target = null;
        lockOnTime.limit = -1;
        updateTargetLocation = true;
    }
}
