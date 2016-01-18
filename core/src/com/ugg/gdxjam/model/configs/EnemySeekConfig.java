package com.ugg.gdxjam.model.configs;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.ai.steer.behaviors.Seek;
import com.ugg.gdxjam.model.components.SteeringBehaviorComponent;

/**
 * Created by Jose Cuellar on 14/01/2016.
 */
public class EnemySeekConfig implements BaseConfig<SteeringBehaviorComponent> {

    static final float MAX_LINEAR_SPEED = 2.5f;
    static final float MAX_LINEAR_ACCELERATION = 11000;
    static final float MAX_ANGULAR_SPEED = 1000;
    static final float MAX_ANGULAR_ACCELERATION =  1500;

    @Override
    public void applyConfigs(SteeringBehaviorComponent steeringBC) {
        Seek seek = (Seek)steeringBC.behavior;
        Steerable steerable = seek.getOwner();
        steerable.setMaxLinearSpeed(MAX_LINEAR_SPEED);
        steerable.setMaxLinearAcceleration(MAX_LINEAR_ACCELERATION);
        steerable.setMaxAngularSpeed(MAX_ANGULAR_SPEED);
        steerable.setMaxAngularAcceleration(MAX_ANGULAR_ACCELERATION);

    }
}
