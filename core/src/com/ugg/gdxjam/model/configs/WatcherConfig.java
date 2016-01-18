package com.ugg.gdxjam.model.configs;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.behaviors.Wander;
import com.ugg.gdxjam.model.components.SteeringBehaviorComponent;

/**
 * Created by Jose Cuellar on 12/01/2016.
 */
public class WatcherConfig implements BaseConfig<SteeringBehaviorComponent> {

    static final float MAX_LINEAR_SPEED =0.4f;
    static final float MAX_LINEAR_ACCELERATION = 110f;
    static final float MAX_ANGULAR_SPEED = 1;
    static final float MAX_ANGULAR_ACCELERATION =  1;

    @Override
    public void applyConfigs(SteeringBehaviorComponent steeringBC) {
        Wander wander = (Wander)steeringBC.behavior;
        Steerable steerable = wander.getOwner();
        steerable.setMaxLinearSpeed(MAX_LINEAR_SPEED);
        steerable.setMaxLinearAcceleration(MAX_LINEAR_ACCELERATION);
        steerable.setMaxAngularSpeed(MAX_ANGULAR_SPEED);
        steerable.setMaxAngularAcceleration(MAX_ANGULAR_ACCELERATION);

        wander.setWanderRadius(0.1f);
        wander.setTimeToTarget(0.1f);
        wander.setDecelerationRadius(1f);
        wander.setFaceEnabled(false);
        wander.setWanderOrientation(250f);

    }
}
