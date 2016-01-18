package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.ugg.gdxjam.model.Weapon;
import com.ugg.gdxjam.model.enums.BulletDataType;

public class WeaponsComponent implements Component, Pool.Poolable {
	public Array<Weapon> weapons = new Array<>();
    public boolean enabled = false;

	@Override
	public void reset() {
		Pools.freeAll(weapons);
        weapons.clear();
	}
}
