package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class MineralComponent implements Component, Pool.Poolable {
	public int amount = 0;

	@Override
	public void reset() {
		amount = 0;
	}
}
