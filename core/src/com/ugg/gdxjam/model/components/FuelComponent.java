package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class FuelComponent implements Component, Pool.Poolable {
	public int amount=0;
	public int max = 0;
	public int regenerate = 1;
    public int decrement = 1;
	@Override
	public void reset() {
		amount = 0;
		max = 0;
		regenerate = 1;
        decrement = 1;
	}
}
