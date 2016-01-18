package com.ugg.gdxjam.model.listeners;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.utils.Array;

public class QueryAABBCallback implements QueryCallback {

	public Array<Body> found = new Array<Body>();
	
	@Override
	public boolean reportFixture(Fixture fixture) {
		Body b = fixture.getBody();
		if(!found.contains(b, true)){
			found.add(b);
		}
		return true;
	}

}
