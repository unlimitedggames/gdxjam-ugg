package com.ugg.gdxjam.model.listeners;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.utils.Array;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.enums.GameEntityType;

public class EntityRayCastCallback implements RayCastCallback {

	public Array<GameEntityType> types = new Array<>();
	public Array<GameEntity> found = new Array<GameEntity>();

	@Override
	public float reportRayFixture(Fixture fixture, Vector2 vector2, Vector2 vector21, float v) {
		final GameEntity gameEntity = (GameEntity) fixture.getBody().getUserData();
		GameEntityType gameEntityType = Mappers.gameEntityType.get(gameEntity.entity).type;

		if(types.contains(gameEntityType, true)){
			found.add(gameEntity);
			return 0;
		}

		return -1f;
	}
}
