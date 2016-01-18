
package com.ugg.gdxjam.model.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.components.*;
import com.ugg.gdxjam.model.enums.ZIndexes;
import com.ugg.gdxjam.view.RenderableParticle;

public class ParticleSystem extends IteratingSystem {

	@SuppressWarnings("unchecked")
	public ParticleSystem() {
		super(Family.all(ParticleComponent.class).get());
	}

	@Override
	protected void processEntity (Entity entity, float deltaTime) {
		ParticleComponent particleC = Mappers.particle.get(entity);
        ParentComponent parentC = Mappers.parent.get(entity);

		if(particleC != null){
			particleC.particle.update(deltaTime);

			if (particleC.particle.isComplete()) {
				particleC.gameEntity.kill();
                return;
			}

            if(parentC != null && parentC.parentEntity != null){
                PhysicsComponent physicsC = Mappers.physics.get(parentC.parentEntity.entity);
                Vector2 position = physicsC.body.getPosition();
                particleC.gameEntity.setPosition(position.x, position.y);
                if(particleC.particle.getZIndex() != ZIndexes.ParticleDown.get())
                    particleC.particle.setZIndex(ZIndexes.ParticleDown.get());
            }
		}
	}



}
