package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.ugg.gdxjam.model.GameEntity;

/**
 * Created by Jose Cuellar on 06/01/2016.
 */
public class ParentComponent implements Component, Pool.Poolable {
    public GameEntity parentEntity = null;

    @Override
    public void reset() {
        parentEntity = null;
    }
}
