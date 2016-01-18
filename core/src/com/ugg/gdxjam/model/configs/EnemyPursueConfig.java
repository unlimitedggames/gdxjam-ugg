package com.ugg.gdxjam.model.configs;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.behaviors.Pursue;
import com.badlogic.gdx.ai.steer.behaviors.Seek;
import com.ugg.gdxjam.model.components.SteeringBehaviorComponent;

/**
 * Created by Jose Cuellar on 14/01/2016.
 */
public class EnemyPursueConfig implements BaseConfig<SteeringBehaviorComponent> {

    static final float MAX_LINEAR_SPEED = 1.5f;
    static final float MAX_LINEAR_ACCELERATION = 3000f;
    static final float MAX_ANGULAR_SPEED = 300f;
    static final float MAX_ANGULAR_ACCELERATION =  1200f;

    @Override
    public void applyConfigs(SteeringBehaviorComponent steeringBC) {
        Pursue pursue = (Pursue)steeringBC.behavior;
        Steerable steerable = pursue.getOwner();
        steerable.setMaxLinearSpeed(MAX_LINEAR_SPEED);
        steerable.setMaxLinearAcceleration(MAX_LINEAR_ACCELERATION);
        steerable.setMaxAngularSpeed(MAX_ANGULAR_SPEED);
        steerable.setMaxAngularAcceleration(MAX_ANGULAR_ACCELERATION);
        pursue.setMaxPredictionTime(0.85f);


    }
}
