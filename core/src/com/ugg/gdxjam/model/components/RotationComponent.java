package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class RotationComponent  implements Component,Pool.Poolable {
	public float rotation = 0.0f;


	@Override
	public void reset() {
		rotation = 0f;
	}
}
