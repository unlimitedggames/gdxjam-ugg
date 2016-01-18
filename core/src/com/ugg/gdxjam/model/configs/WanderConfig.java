package com.ugg.gdxjam.model.configs;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.behaviors.Wander;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.components.SteeringBehaviorComponent;

/**
 * Created by Jose Cuellar on 12/01/2016.
 */
public class WanderConfig implements BaseConfig<SteeringBehaviorComponent> {

    static final float MAX_LINEAR_SPEED =2.5f;
    static final float MAX_LINEAR_ACCELERATION = 700f;
    static final float MAX_ANGULAR_SPEED = 1;
    static final float MAX_ANGULAR_ACCELERATION =  1;
    static final float ZERO_LINEAR_SPEED_THRESHOLD = 0.001f;

    @Override
    public void applyConfigs(SteeringBehaviorComponent steeringBC) {
        Wander wander = (Wander)steeringBC.behavior;
        Steerable steerable = wander.getOwner();
        steerable.setMaxLinearSpeed(MAX_LINEAR_SPEED);
        steerable.setMaxLinearAcceleration(MAX_LINEAR_ACCELERATION);
        steerable.setMaxAngularSpeed(MAX_ANGULAR_SPEED);
        steerable.setMaxAngularAcceleration(MAX_ANGULAR_ACCELERATION);
        //wander.setTarget(loc);
        wander.setWanderRadius(5f);
        wander.setTimeToTarget(0.1f);
        wander.setDecelerationRadius(3f);
        wander.setFaceEnabled(false);
        wander.setWanderOrientation(250f);

    }
}
