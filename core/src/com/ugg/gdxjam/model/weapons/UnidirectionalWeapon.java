package com.ugg.gdxjam.model.weapons;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Pools;
import com.ugg.gdxjam.controller.EngineController;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Logger;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.Weapon;
import com.ugg.gdxjam.model.components.PhysicsComponent;
import com.ugg.gdxjam.model.components.PositionComponent;
import com.ugg.gdxjam.model.components.SoundComponent;
import com.ugg.gdxjam.model.components.VelocityComponent;
import com.ugg.gdxjam.model.enums.BodyDataType;
import com.ugg.gdxjam.model.enums.RenderDataType;
import com.ugg.gdxjam.model.utils.SoundManager;

public class UnidirectionalWeapon extends Weapon{

	@Override
	public void shoot(float delta) {
		if(engineCtrl == null){
			throw new GdxRuntimeException("EngineController hasn't been set on weapon: " + this.getClass().getSimpleName());
		}
		if(interval.lapse(delta)) {
			final Entity entity = this.parent.entity;
            final PhysicsComponent pm = Mappers.physics.get(entity);
            final Vector2 position = pm.body.getPosition();
			final GameEntity bullet = engineCtrl.createBullet(this.parent, this.bulletDataType);
            final float angle = pm.body.getAngle();
			bullet.setPosition(position.x, position.y, angle);

			VelocityComponent velC = Mappers.velocity.get(bullet.entity);
			velC.x = MathUtils.cos(angle);
			velC.y = MathUtils.sin(angle);

			SoundComponent soundC = Mappers.sound.get(entity);
			if(soundC != null && soundC.shoot != null)
				SoundManager.getInstance().play(soundC.shoot);
		}
	}

    @Override
    public Weapon cloneObject() {
        UnidirectionalWeapon weapon = Pools.obtain(UnidirectionalWeapon.class);
        cloneCommon(weapon);
        return weapon;
    }

}
