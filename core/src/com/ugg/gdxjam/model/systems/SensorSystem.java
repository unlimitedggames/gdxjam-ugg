package com.ugg.gdxjam.model.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.components.CollisionComponent;
import com.ugg.gdxjam.model.components.KillComponent;
import com.ugg.gdxjam.model.components.RemovedComponent;
import com.ugg.gdxjam.model.components.SensorComponent;


public class SensorSystem extends IteratingSystem {

    public SensorSystem() {
        super(Family.all(SensorComponent.class).exclude(KillComponent.class).exclude(RemovedComponent.class).get());
    }

    public void processEntity(Entity entity, float deltaTime) {
        SensorComponent sensorComponent = Mappers.sensor.get(entity);
        if(sensorComponent != null && sensorComponent.enabled){
            sensorComponent.sensor.detect().processDetection();
        }

    }
}
