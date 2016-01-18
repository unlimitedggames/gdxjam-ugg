package com.ugg.gdxjam.model.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.ugg.gdxjam.model.Logger;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.components.BuildingComponent;
import com.ugg.gdxjam.model.components.CollisionComponent;
import com.ugg.gdxjam.model.components.FuelComponent;
import com.ugg.gdxjam.model.components.LifeComponent;


public class BuildingSystem extends IteratingSystem {

    public BuildingSystem() {
        super(Family.all(BuildingComponent.class).get());
    }

    public void processEntity(Entity entity, float deltaTime) {
        BuildingComponent buildingC = Mappers.building.get(entity);
        LifeComponent lifeC = Mappers.life.get(entity);
    }
}
