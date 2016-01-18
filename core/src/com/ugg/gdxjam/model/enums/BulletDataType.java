package com.ugg.gdxjam.model.enums;
import com.ugg.gdxjam.model.data.BulletData;

public enum BulletDataType {

	Normal (new BulletData(RenderDataType.NormalBullet, BodyDataType.NormalBulletBody, 0f, 1, 1f, 1, 1, 1.4f, VelocityDataType.BulletNormal, ParticleDataType.AllyBullet)),
	NormalEnemy (new BulletData(RenderDataType.RedBullet, BodyDataType.NormalBulletBody, 0f, 1, 1f, 1, 1, 1.8f, VelocityDataType.BulletNormal, ParticleDataType.EnemyBullet)),
	ImpulseEnemy (new BulletData(RenderDataType.WatcherRedBullet, BodyDataType.WatcherBulletBody, 0f, 1, 1f, 2, 1, 3.8f, VelocityDataType.BulletImpulse, ParticleDataType.EnemyBullet)),
	SlowBulletEnemy (new BulletData(RenderDataType.RedBullet, BodyDataType.NormalBulletBody, 0f, 1, 1f, 1, 1, 8f, VelocityDataType.SlowBullet, ParticleDataType.EnemyBullet)),
	SlowImpulseBulletEnemy (new BulletData(RenderDataType.WatcherRedBullet, BodyDataType.WatcherBulletBody, 0f, 1, 1f, 1, 1, 8f, VelocityDataType.SlowBulletImpulse, ParticleDataType.EnemyBullet));;


	private final BulletData data;

	private BulletDataType(
			BulletData data)
	{
		this.data = data;
	}

	public BulletData get()
	{
		return data;
	}
	
	
}
