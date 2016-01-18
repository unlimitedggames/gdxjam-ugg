package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class PositionComponent  implements Component, Pool.Poolable {
	public float x = 0.0f;
    public float y = 0.0f;
	public int z = 0;
	public float offsetX = 0f;
	public float offsetY = 0f;


	@Override
	public void reset() {
		x = y = offsetX = offsetY = 0f;
		z = 0;
	}
}
