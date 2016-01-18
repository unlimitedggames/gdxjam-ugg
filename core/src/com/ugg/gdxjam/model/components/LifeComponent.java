package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class LifeComponent implements Component, Pool.Poolable {
	public int amount = 0;
	public int max = 0;

	@Override
	public void reset() {
		max = 0;
		amount = 0;
	}

}
