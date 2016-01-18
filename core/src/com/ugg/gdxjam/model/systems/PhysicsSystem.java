package com.ugg.gdxjam.model.systems;

import com.badlogic.ashley.core.EntitySystem;
import com.ugg.gdxjam.controller.PhysicsController;

public class PhysicsSystem extends EntitySystem{

	PhysicsController physicsCtrl;
	private float accumB2D = 0f;

	public static final float MinFPS = 30f;
	public static final float MAX_UPDATE_ITERATIONS = 1f + 60 / MinFPS;
	public static final float fixedTimeStep = 1f / 60;
	public final static float MaxTimePerFrame = fixedTimeStep * MAX_UPDATE_ITERATIONS;

	public PhysicsSystem(PhysicsController physicsCtrl) {
		this.physicsCtrl = physicsCtrl;
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);

		accumB2D += deltaTime;

		if (accumB2D > MaxTimePerFrame)
			accumB2D = MaxTimePerFrame;

		while (accumB2D >= fixedTimeStep) {

			physicsCtrl.step();

			accumB2D -= fixedTimeStep;
		}

	}

}
