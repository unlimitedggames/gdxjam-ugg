package com.ugg.gdxjam.model.enums;
import com.ugg.gdxjam.model.data.BulletData;
import com.ugg.gdxjam.model.data.WeaponData;
import com.ugg.gdxjam.model.weapons.MultiDirectionWeapon;
import com.ugg.gdxjam.model.weapons.SpawnEntityWeapon;
import com.ugg.gdxjam.model.weapons.TripleDirectionWeapon;
import com.ugg.gdxjam.model.weapons.UnidirectionalWeapon;

public enum WeaponDataType {

	LinearMain(new WeaponData(new UnidirectionalWeapon(), BulletDataType.Normal, 0.25f, 0, 1f)),
	LinearEnemy(new WeaponData(new UnidirectionalWeapon(), BulletDataType.NormalEnemy, 0.6f, 0, 1f)),
	ImpulseEnemy(new WeaponData(new UnidirectionalWeapon(), BulletDataType.ImpulseEnemy, 0.8f, 0, 1f)),
	TripleEnemy(new WeaponData(new TripleDirectionWeapon(0f, 122f, 240f), BulletDataType.SlowBulletEnemy, 0.3f, 0, 1f)),
    MultiDirectionMotherShip(new WeaponData(new MultiDirectionWeapon(0f, 72f, 144f, 216f, 288f), BulletDataType.SlowImpulseBulletEnemy, 0.8f, 0, 1f)),
	SpawnMinesEnemy(new WeaponData(new SpawnEntityWeapon(GameEntityType.Enemy, TroopDataType.MineEnemy, 20, 0, 90, 180, 270), null, 5f, 0, 1f)),
    SpawnAttackerEnemy(new WeaponData(new SpawnEntityWeapon(GameEntityType.Enemy, TroopDataType.MotherShipAttackerEnemy, 20, 0), null, 15f, 0, 1f));

	private final WeaponData data;

	private WeaponDataType(
			WeaponData data)
	{
		this.data = data;
	}

	public WeaponData get()
	{
		return data;
	}
	
	
}
