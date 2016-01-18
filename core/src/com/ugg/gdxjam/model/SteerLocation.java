package com.ugg.gdxjam.model;

import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import com.ugg.gdxjam.model.utils.Vector2Utils;

/**
 * {@code SteerLocation} represents an object having a 2D position and an
 * orientation.
 * 
 * @author davebaol
 */
public class SteerLocation implements Location<Vector2> {

	private Vector2 position;
	private float orientation;

	public SteerLocation() {
		this(new Vector2(), 0);
	}

	public SteerLocation(Vector2 position) {
		this(position, 0);
	}

	public SteerLocation(Vector2 position, float orientation) {
		this.position = position;
		this.orientation = orientation;
	}

	@Override
	public Vector2 getPosition() {
		return position;
	}

	@Override
	public float getOrientation() {
		return orientation;
	}

	@Override
	public void setOrientation(float orientation) {
		this.orientation = orientation;
	}

	@Override
	public SteerLocation newLocation() {
		return new SteerLocation();
	}

	@Override
	public float vectorToAngle(Vector2 vector) {
		return Vector2Utils.vectorToAngle(vector);
	}

	@Override
	public Vector2 angleToVector(Vector2 outVector, float angle) {
		return Vector2Utils.angleToVector(outVector, angle);
	}
}
