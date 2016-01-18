package com.ugg.gdxjam.model.factories;

import java.util.EnumMap;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pools;
import com.ugg.gdxjam.controller.EngineController;
import com.ugg.gdxjam.controller.PhysicsController;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Logger;
import com.ugg.gdxjam.model.components.*;
import com.ugg.gdxjam.model.data.BodyData;
import com.ugg.gdxjam.model.data.MovementData;
import com.ugg.gdxjam.model.data.RenderData;
import com.ugg.gdxjam.model.enums.GameEntityType;

public abstract class GameEntityFactory<T> {

	final EngineController oEngineCtrl;
	final PhysicsController oPhysicsCtrl;

	public GameEntityFactory(EngineController oEngineCtrl, PhysicsController oPhysicsCtrl){
		this.oEngineCtrl = oEngineCtrl;
		this.oPhysicsCtrl = oPhysicsCtrl;
	}

	public GameEntity newGameEntity(){
		GameEntity oEntity = Pools.obtain(GameEntity.class);
		oEntity.engineCtrl = oEngineCtrl;
		oEntity.entity = oEngineCtrl.newEntity();
		oEngineCtrl.addEntity(oEntity.entity);
		return oEntity;
	}

	public GameEntity createGameEntity(GameEntityType entityType){;
		GameEntity gameEntity = newGameEntity();
		GameEntityTypeComponent gameETC = gameEntity.addComponent(GameEntityTypeComponent.class);
		gameETC.type = entityType;
		return gameEntity;
	}

	public GameEntity createGameEntity(RenderData data, BodyData bodyData, GameEntityType entityType){;
		GameEntity gameEntity = newGameEntity();
        gameEntity.addGameData(data);
        gameEntity.addGameData(bodyData);
		GameEntityTypeComponent gameETC = gameEntity.addComponent(GameEntityTypeComponent.class);
		gameETC.type = entityType;
		return gameEntity;
	}

	public abstract GameEntity create(T dataType, GameEntityType entityType, GameEntity parent);
	
	
}
