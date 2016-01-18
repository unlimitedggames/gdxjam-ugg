package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.ugg.gdxjam.model.GameEntity;

/**
 * Created by Jose Cuellar on 06/01/2016.
 */
public class RemovedComponent implements Component, Pool.Poolable {
    public GameEntity gameEntity = null;

    @Override
    public void reset() {
        gameEntity = null;
    }
}
