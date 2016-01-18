package com.ugg.gdxjam.model.enums;

import com.ugg.gdxjam.model.data.ParticleData;
import com.ugg.gdxjam.model.data.RewardData;
import com.ugg.gdxjam.model.rewards.FuelReward;
import com.ugg.gdxjam.model.rewards.LifeReward;
import com.ugg.gdxjam.model.rewards.MotherShipReward;

/**
 * Created by Jose Cuellar on 17/01/2016.
 */
public enum ParticleDataType {

    MainShip(new ParticleData(ParticleType.EnemyExplosion, null, ParticleType.MainBooster)),
    Enemy(new ParticleData(ParticleType.EnemyExplosion, null, null)),
    MotherShip(new ParticleData(ParticleType.MotherShipExplosion, null, null)),
    Meteorite(new ParticleData(ParticleType.MeteoriteExplosion, null, null)),
    AllyBullet(new ParticleData(ParticleType.AllyBulletHit, null, null)),
    EnemyBullet(new ParticleData(ParticleType.EnemyBulletHit, null, null));

    private final ParticleData data;

    private ParticleDataType(
            ParticleData data)
    {
        this.data = data;
    }

    public ParticleData get()
    {
        return data;
    }

}
