package com.ugg.gdxjam.model.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public final class Vector2Utils {

	private Vector2Utils() {
	}

	public static float vectorToAngle(Vector2 vector) {
		return vector.angleRad();
	}

	public static Vector2 angleToVector(Vector2 outVector, float angle) {
		outVector.x = (float) MathUtils.cos(angle);
		outVector.y = (float) MathUtils.sin(angle);
		return outVector;
	}

	public static float getXFromAngle(float radiansAngle){
		return MathUtils.cos(radiansAngle);
	}

	public static float getYFromAngle(float radiansAngle){
		return MathUtils.sin(radiansAngle);
	}

	public static float getXf(float iniX, float distance, float radiansAngle){
		return  distance * MathUtils.cos(radiansAngle) + iniX;
	}

	public static float getYf(float iniY, float distance, float radiansAngle){
		return  distance * MathUtils.sin(radiansAngle) + iniY;
	}

}
