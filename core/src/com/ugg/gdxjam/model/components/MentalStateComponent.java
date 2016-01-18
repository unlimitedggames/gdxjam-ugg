package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.ugg.gdxjam.model.MentalState;

public class MentalStateComponent implements Component, Pool.Poolable {
	public MentalState mState = new MentalState();


	@Override
	public void reset() {
		mState.reset();
	}
}
