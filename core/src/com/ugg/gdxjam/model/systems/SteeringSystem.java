package com.ugg.gdxjam.model.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.ugg.gdxjam.model.Logger;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.SteerLocation;
import com.ugg.gdxjam.model.components.FuelComponent;
import com.ugg.gdxjam.model.components.SteerableComponent;
import com.ugg.gdxjam.model.components.SteeringBehaviorComponent;
import com.ugg.gdxjam.model.utils.BehaviorUtils;
import com.ugg.gdxjam.model.utils.Vector2Utils;

public class SteeringSystem extends IteratingSystem {
	
	private static final SteeringAcceleration<Vector2> steeringOutput = new SteeringAcceleration<Vector2>(new Vector2());

	public SteeringSystem() {
        super(Family.all(SteeringBehaviorComponent.class).one(SteerableComponent.class).get());
	}
	
	@Override
	protected void processEntity(Entity entity, float deltaTime) {

		GdxAI.getTimepiece().update(deltaTime);

		SteeringBehavior<Vector2> behavior = Mappers.steeringBehavior.get(entity).behavior;
		SteerableComponent steerable = Mappers.steerable.get(entity);
		
		if(behavior == null) return;
		if(steerable.body == null) return;

		Body body = steerable.body;

        //Lets update the ship movement, if fuel is empty
		FuelComponent fuelC = Mappers.fuel.get(entity);
		if(fuelC != null){
			if(fuelC.amount <= 0){
				Vector2 velocity = body.getLinearVelocity();
				float velX = velocity.x * 0.95f;
				float velY = velocity.y * 0.95f;
				if(Math.abs(velX) <= 0.1f)
					velX = 0f;
				if(Math.abs(velY) <= 0.1f)
					velY = 0f;
				body.setLinearVelocity(velX, velY);
                if(velX == 0 && velY == 0){
                    SteerLocation loc = BehaviorUtils.getTarget(behavior);
                    loc.getPosition().set(body.getPosition());
                }
				return;
			}
		}

		behavior.calculateSteering(steeringOutput);
		boolean anyAccelerations = false;


		if (!steeringOutput.linear.isZero()) {
			final Vector2 force = steeringOutput.linear.scl(deltaTime);
			body.applyForceToCenter(force, true);
			anyAccelerations = true;
		}else{
			body.setLinearVelocity(0f, 0f);
		}

		// Update orientation and angular velocity
		if (steerable.isIndependentFacing()) {
			if (steeringOutput.angular != 0) {
				body.applyTorque(steeringOutput.angular * deltaTime, true);
				anyAccelerations = true;
			}
		}else {
			// If we haven't got any velocity, then we can't update angle by the velocity
			final Vector2 linVel = body.getLinearVelocity();
			if (!linVel.isZero(steerable.getZeroLinearSpeedThreshold())) {
				float newOrientation = steerable.vectorToAngle(linVel);
                body.setAngularVelocity((newOrientation - steerable.getAngularVelocity()) * deltaTime); // this is superfluous if independentFacing is always true
                body.setTransform(body.getPosition(), newOrientation);
				//body.setTransform(body.getPosition(), newOrientation);
			}else{
                //we have to check if angular != 0, for ReachOrientation and Face behaviors
                if (steeringOutput.angular != 0) {
                    final float steeringAngular = steeringOutput.angular * deltaTime;
                    //Logger.getInstance().log("Angular: " + steeringAngular);
                    body.setAngularVelocity(steeringAngular);
                    anyAccelerations = true;
                }else{
                    body.setAngularVelocity(0);
                }
            }
		}

		if (anyAccelerations) {
			// Cap the linear speed
			Vector2 velocity = body.getLinearVelocity();
			final float currentSpeedSquare = velocity.len2();
			final float maxLinearSpeed = steerable.getMaxLinearSpeed();
			if (currentSpeedSquare > maxLinearSpeed * maxLinearSpeed) {
				body.setLinearVelocity(velocity.clamp(0, maxLinearSpeed * maxLinearSpeed));// .scl(maxLinearSpeed / (float)Math.sqrt(currentSpeedSquare)));
			}

			// Cap the angular speed
			final float maxAngVelocity = steerable.getMaxAngularSpeed();
			if (body.getAngularVelocity() > maxAngVelocity) {
				body.setAngularVelocity(maxAngVelocity);
			}
		}
	}

}
