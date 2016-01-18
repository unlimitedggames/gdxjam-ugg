package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.TimeCounter;

/**
 * Created by Jose Cuellar on 06/01/2016.
 */
public class LifeDurationComponent implements Component, Pool.Poolable {
    public TimeCounter interval = new TimeCounter();
    public GameEntity gameEntity = null;


    @Override
    public void reset() {
        interval.reset();
        gameEntity = null;
    }
}
