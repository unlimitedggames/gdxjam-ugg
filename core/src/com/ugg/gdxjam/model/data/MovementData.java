package com.ugg.gdxjam.model.data;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pools;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.components.AngularComponent;
import com.ugg.gdxjam.model.components.VelocityComponent;

public class MovementData implements GameData {

	public float velX, velY;
	public float radio;
	
	@Override
	public void assignComponents(GameEntity gameEntity) {

		VelocityComponent velC = gameEntity.addComponent(VelocityComponent.class);
		velC.x = velX;
		velC.y = velY;
		
		if(radio != 0){
			AngularComponent angC = gameEntity.addComponent(AngularComponent.class);
			angC.radio = radio;
		}
		
	}
}
