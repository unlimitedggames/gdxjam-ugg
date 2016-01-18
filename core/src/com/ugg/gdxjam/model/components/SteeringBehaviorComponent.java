
package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

public class SteeringBehaviorComponent implements Component,Poolable {

	public SteeringBehavior<Vector2> behavior;

	@Override
	public void reset () {
		behavior = null;
	}

}
