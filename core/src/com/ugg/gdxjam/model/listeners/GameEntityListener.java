package com.ugg.gdxjam.model.listeners;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pools;
import com.ugg.gdxjam.controller.EngineController;
import com.ugg.gdxjam.controller.PhysicsController;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Logger;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.components.PhysicsComponent;
import com.ugg.gdxjam.model.components.RemovedComponent;
import com.ugg.gdxjam.model.components.RenderableComponent;
import com.ugg.gdxjam.model.enums.GameEntityType;

public class GameEntityListener implements EntityListener{

    final EngineController oEngineCtrl;

    public GameEntityListener(EngineController oEngineCtrl){
        this.oEngineCtrl = oEngineCtrl;
    }

	@Override
	public void entityAdded(Entity entity) {
        oEngineCtrl.addShowComponent(entity);
		//Logger.getInstance().log("Test: entity added.");
	}

	@Override
	public void entityRemoved(Entity entity) {
        final RemovedComponent removedC = Mappers.removed.get(entity);
        if(removedC != null) {
            /*GameEntityType type = Mappers.gameEntityType.get(entity).type;
            Logger.getInstance().log("Entity removed: " + entity + ", type: " + type);
            if(type == GameEntityType.Ally || type == GameEntityType.Enemy){
                int y = 0;
            }*/
            oEngineCtrl.removeGameEntity(removedC.gameEntity);
        }

	}

}
