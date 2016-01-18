package com.ugg.gdxjam.model.configs;

import com.badlogic.gdx.ai.steer.behaviors.Face;
import com.badlogic.gdx.math.Vector2;
import com.ugg.gdxjam.GDXJam;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.SteerLocation;
import com.ugg.gdxjam.model.ai.fsm.MainShipState;
import com.ugg.gdxjam.model.components.*;
import com.ugg.gdxjam.model.enums.EntityEventType;
import com.ugg.gdxjam.model.enums.ParticleType;
import com.ugg.gdxjam.model.enums.ZIndexes;
import com.ugg.gdxjam.view.Renderable;

/**
 * Created by Jose Cuellar on 10/01/2016.
 */
public class MainShipConfig implements BaseConfig<GameEntity>{

    public final int shipLife = 25;
    public final float timeToRegenerate = 5f;
    public final int maxFuel = 600;
    public final Vector2 shipInitialPosition = new Vector2(GDXJam.worldBoundaries.width/2, GDXJam.worldBoundaries.height/2);

    @Override
    public void applyConfigs(GameEntity gameEntity) {
        gameEntity.entity.remove(LifeBarComponent.class);

        FSMComponent fsmComponent = Mappers.fsm.get(gameEntity.entity);
        fsmComponent.changeState(MainShipState.Ready);

        FuelComponent fuelC = gameEntity.addComponent(FuelComponent.class);
        fuelC.amount = maxFuel;
        fuelC.max = maxFuel;

        gameEntity.setPosition(shipInitialPosition.x, shipInitialPosition.y);

        SteerLocation loc = new SteerLocation();
        loc.getPosition().set(shipInitialPosition.x, shipInitialPosition.y);

        SteerableComponent steerableC = Mappers.steerable.get(gameEntity.entity);
        SteeringBehaviorComponent steeringBC = Mappers.steeringBehavior.get(gameEntity.entity);
        Face reachOrientation = new Face(steerableC, loc);
        reachOrientation.setTarget(loc);
        reachOrientation.setTimeToTarget(0.001f);
        reachOrientation.setAlignTolerance(0.1f);
        reachOrientation.setDecelerationRadius(0f);
        steeringBC.behavior = reachOrientation;

        ParticleTypesComponent particleTypesC = Mappers.particleType.get(gameEntity.entity);
        if(particleTypesC != null && particleTypesC.particleTypes.get(EntityEventType.Move.ordinal()) != null){
            ParticleType particleType = particleTypesC.particleTypes.get(EntityEventType.Move.ordinal());
            GameEntity particleEntity = gameEntity.engineCtrl.createParticle(particleType, gameEntity);
            PhysicsComponent physicsC = Mappers.physics.get(gameEntity.entity);
            Vector2 position = physicsC.body.getPosition();
            particleEntity.setPosition(position.x, position.y);
        }
    }
}
