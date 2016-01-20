package com.ugg.gdxjam.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pools;
import com.ugg.gdxjam.controller.EngineController;
import com.ugg.gdxjam.controller.PhysicsController;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Logger;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.components.*;


public class KillSystem extends EntitySystem {

    private Family family;
    private ImmutableArray<Entity> entities;

    final EngineController oEngineCtrl;
    final PhysicsController oPhysicsCtrl;

    public KillSystem(EngineController oEngineCtrl, PhysicsController oPhysicsCtrl) {
        this.family = Family.all(KillComponent.class).get();
        this.oEngineCtrl = oEngineCtrl;
        this.oPhysicsCtrl = oPhysicsCtrl;
    }

    @Override
    public void addedToEngine (Engine engine) {
        entities = engine.getEntitiesFor(family);
    }

    @Override
    public void removedFromEngine (Engine engine) {
        entities = null;
    }

    @Override
    public void update (float deltaTime) {
        for (int i = entities.size() - 1; i >  -1; i--) {
            processEntity(entities.get(i), deltaTime);
        }
    }

    public void processEntity(Entity entity, float deltaTime) {
        final PhysicsComponent phyC = Mappers.physics.get(entity);
        final KillComponent killC = Mappers.kill.get(entity);
        final WeaponsComponent weaponC = Mappers.weapon.get(entity);
        final FSMComponent fsmComponent = Mappers.fsm.get(entity);

        if(fsmComponent != null)
            entity.remove(FSMComponent.class);
        
        if(phyC != null){
	    	final Body body = phyC.body;
            phyC.body.setActive(false);
    }
        if(weaponC != null){
            entity.remove(WeaponsComponent.class);
        }


        if(killC != null){

            //Get the GameEntity
            GameEntity gameEntity = killC.gameEntity;

            //Add a removed component, to get the entity later on listener
            RemovedComponent removedC = gameEntity.addComponent(RemovedComponent.class);
            removedC.gameEntity = gameEntity;

            //Not want to kill it again :)
            entity.remove(KillComponent.class);

            //Remove entity from engine
            oEngineCtrl.removeEntity(gameEntity);

        }
    }
}
