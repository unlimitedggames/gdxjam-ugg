package com.ugg.gdxjam.model;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.utils.Pool;
import com.ugg.gdxjam.controller.EngineController;
import com.ugg.gdxjam.model.enums.BulletDataType;

public abstract class Weapon implements Pool.Poolable, Clonable<Weapon>{


	public GameEntity parent;
	public EngineController engineCtrl;
	public TimeCounter interval = new TimeCounter(1f);
	public BulletDataType bulletDataType;
	public boolean enabled = true;

    public Weapon(){

    }

	public abstract void shoot(float delta);

    protected void cloneCommon(Weapon weapon){
        weapon.interval.limit = this.interval.limit;
        weapon.bulletDataType = bulletDataType;
    }

	@Override
	public void reset() {
		parent = null;
        bulletDataType = null;
		interval.reset();
	}
}
