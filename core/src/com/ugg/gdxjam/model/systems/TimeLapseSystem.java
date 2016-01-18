package com.ugg.gdxjam.model.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Logger;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.components.*;


public class TimeLapseSystem extends IteratingSystem {

    public TimeLapseSystem() {
        super(Family.one(IntervalComponent.class, InvincibleComponent.class, LifeDurationComponent.class)
                .exclude(KillComponent.class).exclude(RemovedComponent.class).get());
    }

    public void processEntity(Entity entity, float deltaTime) {
        InvincibleComponent invincibleC = Mappers.invicible.get(entity);
        LifeDurationComponent lifeDurationC = Mappers.lifeDuration.get(entity);
        IntervalComponent intervalC = Mappers.interval.get(entity);

        if(intervalC != null){
            if(!intervalC.tickComplete) {
                intervalC.tickComplete = intervalC.interval.lapse(deltaTime);
            }
        }

        if(invincibleC != null){
            if(invincibleC.time.lapse(deltaTime)){
                entity.remove(InvincibleComponent.class);
            }
        }

        if(lifeDurationC != null){
            if(lifeDurationC.interval.lapse(deltaTime)){
                GameEntity gameEntity = lifeDurationC.gameEntity;
                gameEntity.kill();
                entity.remove(LifeDurationComponent.class);
            }
        }

    }
}
