package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.ugg.gdxjam.model.GameEntity;

/**
 * Created by Jose Cuellar on 05/01/2016.
 */
public class CollisionComponent implements Pool.Poolable, Component {
    public GameEntity origin = null;
    public Array<GameEntity> targets = new Array<GameEntity>();

    @Override
    public void reset() {
        origin = null;
        targets.clear();
    }
}
