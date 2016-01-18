package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.ugg.gdxjam.model.Defense;
import com.ugg.gdxjam.model.enums.GameEntityType;

public class GameEntityTypeComponent implements Component, Pool.Poolable {
	public GameEntityType type;

	@Override
	public void reset() {
		type = null;
	}
}
