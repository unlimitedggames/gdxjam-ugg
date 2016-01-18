package com.ugg.gdxjam.model.configs;

import com.badlogic.gdx.math.MathUtils;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.components.PhysicsComponent;
import com.ugg.gdxjam.model.components.ScaleComponent;

/**
 * Created by Jose Cuellar on 17/01/2016.
 */
public class MeteoriteConfig implements BaseConfig<GameEntity> {

    public static float maxImpulse = 0.5f;

    @Override
    public void applyConfigs(GameEntity gameEntity) {
        PhysicsComponent physicsC = Mappers.physics.get(gameEntity.entity);
        physicsC.body.setAngularVelocity(MathUtils.random(-maxImpulse, maxImpulse));
        final float scale = MathUtils.random(0.7f, 1.1f);
        ScaleComponent scaleC = Mappers.scale.get(gameEntity.entity);
        scaleC.x = scale;
        scaleC.y = scale;
    }
}
