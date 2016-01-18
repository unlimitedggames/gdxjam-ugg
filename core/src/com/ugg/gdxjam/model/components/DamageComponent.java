package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class DamageComponent implements Component, Pool.Poolable{
	 public int damage=1;
	 public float multiplier=1f;

	@Override
	public void reset() {
		damage = 1;
		multiplier = 1f;
	}
}
