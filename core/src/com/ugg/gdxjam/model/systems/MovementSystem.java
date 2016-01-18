package com.ugg.gdxjam.model.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.ugg.gdxjam.model.Logger;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.components.*;
import com.ugg.gdxjam.model.enums.VelocityType;


public class MovementSystem extends IteratingSystem {
    private ComponentMapper<PositionComponent> pm;
    private ComponentMapper<VelocityComponent> vm;

    public MovementSystem() {
        super(Family.all(PositionComponent.class, VelocityComponent.class)
                .exclude(SteerableComponent.class, SteeringBehaviorComponent.class).get());
        pm = Mappers.position;
        vm = Mappers.velocity;
    }

    public void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = pm.get(entity);
        VelocityComponent velocity = vm.get(entity);

        PhysicsComponent phyC = Mappers.physics.get(entity);
    	if(phyC != null){
	    	Body body = phyC.body;
            final float velX = velocity.x * velocity.multiplier;
            final float velY = velocity.y * velocity.multiplier;
            //Logger.getInstance().log("Multiplied: " + velocity.multiplier + ", Type: " + velocity.type + ", VelX: " + velX + ", VelY; " + velY);
            if(velocity.type == VelocityType.Linear)
	    	    body.setLinearVelocity(velX, velY);
            else if(velocity.type == VelocityType.Impulse)
                body.applyLinearImpulse(velX, velY, 0, 0, true);
    	}else{
	        position.x += velocity.x * deltaTime;
	        position.y += velocity.y * deltaTime;
    	}
    }
}
