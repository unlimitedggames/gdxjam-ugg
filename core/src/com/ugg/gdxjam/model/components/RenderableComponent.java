package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.ugg.gdxjam.view.Renderable;

public class RenderableComponent implements Component, Pool.Poolable {
	public Renderable renderable = null;

	@Override
	public void reset() {
		Pools.free(renderable);
		renderable = null;
	}
}
