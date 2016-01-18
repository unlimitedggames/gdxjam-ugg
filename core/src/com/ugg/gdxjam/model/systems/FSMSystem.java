package com.ugg.gdxjam.model.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.ashley.systems.IteratingSystem;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.components.FSMComponent;

public class FSMSystem extends IteratingSystem {

	public FSMSystem() {
        super(Family.all(FSMComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Mappers.fsm.get(entity).update();
    }
}
