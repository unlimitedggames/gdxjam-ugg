package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class AngularComponent implements Component, Pool.Poolable{
	 public float radio=0f;
	 public float angulo = 0f;


	@Override
	public void reset() {
		radio = 0f;
		angulo = 0f;
	}
}
