package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.ugg.gdxjam.model.enums.BuildingType;

/**
 * Created by Jose Cuellar on 11/01/2016.
 */
public class BuildingComponent implements Component, Pool.Poolable {
    public BuildingType type;

    @Override
    public void reset() {
        type = null;
    }
}
