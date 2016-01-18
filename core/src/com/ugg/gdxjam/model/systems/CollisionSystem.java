package com.ugg.gdxjam.model.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.Body;
import com.ugg.gdxjam.model.Logger;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.components.CollisionComponent;
import com.ugg.gdxjam.model.components.PhysicsComponent;
import com.ugg.gdxjam.model.components.PositionComponent;
import com.ugg.gdxjam.model.components.VelocityComponent;


public class CollisionSystem extends IteratingSystem {

    public CollisionSystem() {
        super(Family.all(CollisionComponent.class).get());
    }

    public void processEntity(Entity entity, float deltaTime) {
        CollisionComponent collisionC = Mappers.collision.get(entity);
        if(collisionC != null && collisionC.origin != null)
            collisionC.origin.handleCollision();
    }
}
