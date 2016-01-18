package com.ugg.gdxjam.model.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.SteerLocation;
import com.ugg.gdxjam.model.components.CollisionComponent;
import com.ugg.gdxjam.model.components.LockOnComponent;
import com.ugg.gdxjam.model.components.PhysicsComponent;
import com.ugg.gdxjam.model.components.SteeringBehaviorComponent;
import com.ugg.gdxjam.model.utils.BehaviorUtils;


public class LockOnSystem extends IteratingSystem {

    public LockOnSystem() {
        super(Family.all(LockOnComponent.class).get());
    }

    public void processEntity(Entity entity, float deltaTime) {
        LockOnComponent lockOnC = Mappers.lockOn.get(entity);

        if(lockOnC.lockOnTime.limit > 0 && lockOnC.lockOnTime.lapse(deltaTime)) {
            entity.remove(LockOnComponent.class);
            return;
        }

        if(lockOnC.updateTargetLocation) {
            SteeringBehaviorComponent behaviorC = Mappers.steeringBehavior.get(entity);

            //Get target physicsComponent, so we know the position
        /*if(lockOnC.target == null || lockOnC.target.entity == null) {
            lockOnC.origin.entity.remove(LockOnComponent.class);
            return;
        }*/
            PhysicsComponent physicsComponent = Mappers.physics.get(lockOnC.target.entity);
            Vector2 targetPosition = physicsComponent.body.getPosition();

            //Set the new target position
            SteerLocation targetLocation = BehaviorUtils.getTarget(behaviorC.behavior);
            if (targetLocation != null)
                targetLocation.getPosition().set(targetPosition);
        }



    }


}
