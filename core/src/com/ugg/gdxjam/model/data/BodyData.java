package com.ugg.gdxjam.model.data;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.ugg.gdxjam.controller.PhysicsController;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.components.PhysicsComponent;

public class BodyData implements GameData{
	final BodyDef def;
	PhysicsController physicsCtrl;
	Array<FixtureDef> fixtureDefinitions;

	public BodyData(BodyDef bodyDef, PhysicsController physicsCtrl, FixtureDef... fixDefs){
		this.physicsCtrl = physicsCtrl;
		this.fixtureDefinitions = new Array<FixtureDef>(fixDefs);
		this.def = bodyDef;
	}
	
	@Override
	public void assignComponents(GameEntity gameEntity) {
		Entity entity = gameEntity.entity;
		PhysicsComponent pC = new PhysicsComponent();
		pC.body = physicsCtrl.createBody(def);
		for(FixtureDef fixDef : fixtureDefinitions){
			pC.body.createFixture(fixDef);
		}
		pC.body.setUserData(gameEntity);
		entity.add(pC);
	}
}
