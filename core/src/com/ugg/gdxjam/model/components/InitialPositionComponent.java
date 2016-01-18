package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Jose Cuellar on 14/01/2016.
 */
public class InitialPositionComponent implements Component, Pool.Poolable {
    public float x = 0.0f;
    public float y = 0.0f;

    @Override
    public void reset() {
        x = y = 0f;
    }
}
