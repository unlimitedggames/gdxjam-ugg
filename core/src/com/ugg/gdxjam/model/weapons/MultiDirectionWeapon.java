package com.ugg.gdxjam.model.weapons;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.FloatArray;
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

public class MultiDirectionWeapon extends Weapon{

	public final FloatArray angles;

	public MultiDirectionWeapon(){
		this.angles = new FloatArray();
	}

	public MultiDirectionWeapon(float... angles){
		this.angles = new FloatArray(angles);
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

			for(int i = 0, n = angles.size; i < n; i++){
				float currentAngle = angles.get(i);
				createBullet(position, (degAngle + currentAngle) * MathUtils.degreesToRadians);
			}

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
        MultiDirectionWeapon weapon = Pools.obtain(MultiDirectionWeapon.class);
        cloneCommon(weapon);
		weapon.angles.addAll(this.angles);
        return weapon;
    }

}
