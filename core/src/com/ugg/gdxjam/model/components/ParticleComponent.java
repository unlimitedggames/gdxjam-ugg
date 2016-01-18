
package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.enums.ParticleType;
import com.ugg.gdxjam.view.RenderableParticle;

public class ParticleComponent implements Component, Pool.Poolable {
    public RenderableParticle particle;
	public GameEntity gameEntity;

	@Override
	public void reset ()
	{
        particle = null;
		gameEntity = null;
	}

}
