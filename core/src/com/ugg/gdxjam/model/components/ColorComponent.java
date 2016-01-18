package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Pool;

public class ColorComponent  implements Component, Pool.Poolable {
	public Color color = new Color();

	@Override
	public void reset() {
		color.set(1f, 1f, 1f, 1f);
	}
}
