package com.ugg.gdxjam.model.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.ugg.gdxjam.GDXJam;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.ZComparator;
import com.ugg.gdxjam.model.components.*;
import com.ugg.gdxjam.view.Renderable;

public class RenderableUpdateSystem extends IteratingSystem {
    private ComponentMapper<RenderableComponent> rm;
    private ComponentMapper<PositionComponent> pm;
    private ComponentMapper<SizeComponent> sm;

    public RenderableUpdateSystem() {
        super(Family.all(RenderableComponent.class, PositionComponent.class, SizeComponent.class, ScaleComponent.class, OriginComponent.class, ColorComponent.class, RotationComponent.class).get());
    }

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		RenderableComponent rc = Mappers.renderable.get(entity);
    	PositionComponent pc = Mappers.position.get(entity);
    	SizeComponent szC = Mappers.size.get(entity);
    	ScaleComponent scC = Mappers.scale.get(entity);
    	OriginComponent orC = Mappers.origin.get(entity);
    	RotationComponent rotC = Mappers.rotation.get(entity);
    	ColorComponent colC = Mappers.color.get(entity);
		LifeBarComponent lifeBarC = Mappers.lifeBar.get(entity);
    	
    	//Update physics position
    	PhysicsComponent phyC = Mappers.physics.get(entity);
    	if(phyC != null){
			if(phyC.body == null){
				int i = 0;
			}
	    	Vector2 bodyPosition = phyC.body.getPosition();
	    	pc.x = bodyPosition.x + pc.offsetX;
	    	pc.y = bodyPosition.y + pc.offsetY;

			final float angle = phyC.body.getAngle();
			rotC.rotation = MathUtils.radiansToDegrees*angle;
    	}
    	
    	//Con los componentes, actualizamos los valores del renderable
    	Renderable r = rc.renderable;
    	//position
    	r.setPosition(pc.x, pc.y);
    	//r.setZIndex(pc.z);
    	//size
    	r.setSize(szC.width, szC.height);
    	//scale
    	r.setScale(scC.x, scC.y);
    	//origin
    	r.setOrigin(orC.x, orC.y);
    	//rotation
    	r.setRotation(rotC.rotation);
    	//color
    	r.setColor(colC.color);
    	
    	r.act(deltaTime);

		if(lifeBarC != null){
			LifeComponent lifeC = Mappers.life.get(entity);
			if(lifeC != null){
				lifeBarC.lifeBar.setPosition(pc.x + Math.abs(pc.offsetX), pc.y + Math.abs(pc.offsetY));
				lifeBarC.lifeBar.setLife(lifeC.amount, lifeC.max);
			}
		}else{
			PlayerComponent playerC = Mappers.player.get(entity);
			if(playerC != null){
				LifeComponent lifeC = Mappers.life.get(entity);
				FuelComponent fuelC = Mappers.fuel.get(entity);
				playerC.player.updateBars(lifeC.amount, lifeC.max, fuelC.amount, fuelC.max);
			}
		}


	}
    

   
}
