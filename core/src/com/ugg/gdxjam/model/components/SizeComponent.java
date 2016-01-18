package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class SizeComponent  implements Component, Pool.Poolable {
	public float width = 0.0f;
    public float height = 0.0f;

    @Override
    public void reset() {
        width = height = 0f;
    }
}
