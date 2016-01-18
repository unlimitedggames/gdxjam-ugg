package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.ugg.gdxjam.model.enums.VelocityType;

public class VelocityComponent  implements Component, Pool.Poolable{
	public float x = 0.0f;
    public float y = 0.0f;
    public float multiplier = 1f;
    public VelocityType type = VelocityType.Linear;

    @Override
    public void reset() {
        x = y = 0f;
        multiplier = 1f;
        type = VelocityType.Linear;
    }
}
