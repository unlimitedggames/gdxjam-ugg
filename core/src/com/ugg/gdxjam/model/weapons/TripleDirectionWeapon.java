package com.ugg.gdxjam.model.weapons;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Pools;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.Weapon;
import com.ugg.gdxjam.model.components.PhysicsComponent;
import com.ugg.gdxjam.model.components.SoundComponent;
import com.ugg.gdxjam.model.components.VelocityComponent;
import com.ugg.gdxjam.model.utils.SoundManager;
import com.ugg.gdxjam.model.utils.Vector2Utils;

public class TripleDirectionWeapon extends Weapon{

	public float angle0, angle1, angle2;

	public TripleDirectionWeapon(){

	}

	public TripleDirectionWeapon(float angle0, float angle1, float angle2){
		this.angle0 = angle0;
		this.angle1 = angle1;
		this.angle2 = angle2;
	}

	@Override
	public void shoot(float delta) {
		if(engineCtrl == null){
			throw new GdxRuntimeException("EngineController hasn't been set on weapon: " + this.getClass().getSimpleName());
		}
		if(interval.lapse(delta)) {
			final Entity entity = this.parent.entity;
            final PhysicsComponent pm = Mappers.physics.get(entity);
            final Vector2 position = pm.body.getPosition();
			final float angle = pm.body.getAngle();
			final float degAngle = MathUtils.radiansToDegrees*angle;

			createBullet(position, (degAngle + angle0) * MathUtils.degreesToRadians);
			createBullet(position, (degAngle + angle1) * MathUtils.degreesToRadians);
			createBullet(position, (degAngle + angle2) * MathUtils.degreesToRadians);

			SoundComponent soundC = Mappers.sound.get(entity);
			if(soundC != null && soundC.shoot != null)
				SoundManager.getInstance().play(soundC.shoot);
		}
	}

	private void createBullet(Vector2 position, float angle){
		final GameEntity bullet = engineCtrl.createBullet(this.parent, this.bulletDataType);
		bullet.setPosition(position.x, position.y, angle);
		VelocityComponent velC = Mappers.velocity.get(bullet.entity);
		velC.x = Vector2Utils.getXFromAngle(angle);
		velC.y = Vector2Utils.getYFromAngle(angle);
	}

    @Override
    public Weapon cloneObject() {
        TripleDirectionWeapon weapon = Pools.obtain(TripleDirectionWeapon.class);
        cloneCommon(weapon);
		weapon.angle0 = this.angle0;
		weapon.angle1 = this.angle1;
		weapon.angle2 = this.angle2;
        return weapon;
    }

}
