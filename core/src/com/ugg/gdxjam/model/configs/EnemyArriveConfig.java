package com.ugg.gdxjam.model.configs;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.ai.steer.behaviors.Wander;
import com.ugg.gdxjam.model.components.SteeringBehaviorComponent;

/**
 * Created by Jose Cuellar on 14/01/2016.
 */
public class EnemyArriveConfig implements BaseConfig<SteeringBehaviorComponent> {

    static final float MAX_LINEAR_SPEED = 15f;
    static final float MAX_LINEAR_ACCELERATION = 12000;
    static final float MAX_ANGULAR_SPEED = 300;
    static final float MAX_ANGULAR_ACCELERATION =  300;

    @Override
    public void applyConfigs(SteeringBehaviorComponent steeringBC) {
        Arrive arrive = (Arrive)steeringBC.behavior;
        Steerable steerable = arrive.getOwner();
        steerable.setMaxLinearSpeed(MAX_LINEAR_SPEED);
        steerable.setMaxLinearAcceleration(MAX_LINEAR_ACCELERATION);
        steerable.setMaxAngularSpeed(MAX_ANGULAR_SPEED);
        steerable.setMaxAngularAcceleration(MAX_ANGULAR_ACCELERATION);

        arrive.setArrivalTolerance(0.5f);
        arrive.setDecelerationRadius(10f);
        arrive.setTimeToTarget(0.005f);
    }
}
