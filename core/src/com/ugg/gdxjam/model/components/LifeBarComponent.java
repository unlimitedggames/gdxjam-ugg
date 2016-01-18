package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.ugg.gdxjam.view.RenderableLifeBar;

/**
 * Created by Jose Cuellar on 15/01/2016.
 */
public class LifeBarComponent implements Component, Pool.Poolable {
    public RenderableLifeBar lifeBar;

    @Override
    public void reset() {
        Pools.free(lifeBar);
        lifeBar = null;
    }
}
