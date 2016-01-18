package com.ugg.gdxjam.model.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.SteerLocation;
import com.ugg.gdxjam.model.components.FuelComponent;
import com.ugg.gdxjam.model.components.LockOnComponent;
import com.ugg.gdxjam.model.components.PhysicsComponent;
import com.ugg.gdxjam.model.components.SteeringBehaviorComponent;
import com.ugg.gdxjam.model.utils.BehaviorUtils;


public class FuelSystem extends IteratingSystem {

    public FuelSystem() {
        super(Family.all(FuelComponent.class).get());
    }

    public void processEntity(Entity entity, float deltaTime) {
        FuelComponent fuelC = Mappers.fuel.get(entity);
        PhysicsComponent physicsC = Mappers.physics.get(entity);

        if (fuelC.amount < fuelC.max && physicsC.body.getLinearVelocity().isZero() || !physicsC.body.isActive()) {
            fuelC.amount = Math.min(fuelC.amount + fuelC.regenerate, fuelC.max);
        }else if(fuelC.amount > 0 && !physicsC.body.getLinearVelocity().isZero()){
            fuelC.amount = Math.max(0, fuelC.amount - fuelC.decrement);
        }
    }

}
