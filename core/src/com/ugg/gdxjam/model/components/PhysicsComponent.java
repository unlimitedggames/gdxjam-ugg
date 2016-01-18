
package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool.Poolable;

public class PhysicsComponent implements Component, Poolable {

	public Body body;

	@Override
	public void reset () {
		body = null;
	}

}
