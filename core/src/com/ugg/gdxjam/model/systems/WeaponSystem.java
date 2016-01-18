package com.ugg.gdxjam.model.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.Weapon;
import com.ugg.gdxjam.model.components.PositionComponent;
import com.ugg.gdxjam.model.components.VelocityComponent;
import com.ugg.gdxjam.model.components.WeaponsComponent;


public class WeaponSystem extends IteratingSystem {
    private ComponentMapper<WeaponsComponent> wm;

    public WeaponSystem() {
        super(Family.all(WeaponsComponent.class).get());
        wm = Mappers.weapon;
    }

    public void processEntity(Entity entity, float deltaTime) {
    	WeaponsComponent wc = wm.get(entity);
        if(wc.enabled){
            for(Weapon weapon : wc.weapons){
                if(weapon.enabled)
                    weapon.shoot(deltaTime);
            }
        }
    }
}
