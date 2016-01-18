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
import com.ugg.gdxjam.model.components.ParentComponent;
import com.ugg.gdxjam.model.components.PhysicsComponent;
import com.ugg.gdxjam.model.components.SoundComponent;
import com.ugg.gdxjam.model.components.VelocityComponent;
import com.ugg.gdxjam.model.enums.GameEntityType;
import com.ugg.gdxjam.model.enums.TroopDataType;
import com.ugg.gdxjam.model.utils.SoundManager;
import com.ugg.gdxjam.model.utils.Vector2Utils;

public class SpawnEntityWeapon extends Weapon{

	public final FloatArray angles;
	public GameEntityType typeToSpawn;
	public TroopDataType troopType;
	public float spawnDistance;

	public SpawnEntityWeapon(){
		this.angles = new FloatArray();
	}

	public SpawnEntityWeapon(GameEntityType typeToSpawn, TroopDataType troopType, float spawnDistance, float... angles){
		this.angles = new FloatArray(angles);
		this.typeToSpawn = typeToSpawn;
		this.troopType = troopType;
		this.spawnDistance = spawnDistance;
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
				createEntity(position, (degAngle + currentAngle) * MathUtils.degreesToRadians);
			}

			SoundComponent soundC = Mappers.sound.get(entity);
			if(soundC != null && soundC.spawn != null)
				SoundManager.getInstance().play(soundC.spawn);
		}
	}

	private void createEntity(Vector2 position, float angle){
		final GameEntity troop = typeToSpawn == GameEntityType.Ally?
				engineCtrl.createAlly(this.troopType, this.parent) : engineCtrl.createEnemy(this.troopType, this.parent);

        //Set the position of the troop, given the distance
        troop.setPosition(Vector2Utils.getXf(position.x, this.spawnDistance, angle),
				Vector2Utils.getYf(position.y, this.spawnDistance, angle), angle);

	}

    @Override
    public Weapon cloneObject() {
        SpawnEntityWeapon weapon = Pools.obtain(SpawnEntityWeapon.class);
        cloneCommon(weapon);
		weapon.angles.addAll(this.angles);
		weapon.typeToSpawn = this.typeToSpawn;
		weapon.troopType = this.troopType;
		weapon.spawnDistance = this.spawnDistance;
        return weapon;
    }

}
