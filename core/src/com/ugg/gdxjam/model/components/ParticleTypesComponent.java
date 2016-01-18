
package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.ugg.gdxjam.model.enums.ParticleType;
import com.ugg.gdxjam.model.enums.RenderDataType;
import com.ugg.gdxjam.view.RenderableParticle;

public class ParticleTypesComponent implements Component, Pool.Poolable {
    public IntMap<ParticleType> particleTypes = new IntMap<>();

	@Override
	public void reset ()
	{
        particleTypes.clear();
	}

}
