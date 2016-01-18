package com.ugg.gdxjam.model.data;

import com.badlogic.gdx.utils.Pools;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.TimeCounter;
import com.ugg.gdxjam.model.components.*;
import com.ugg.gdxjam.model.enums.BodyDataType;
import com.ugg.gdxjam.model.enums.ParticleDataType;
import com.ugg.gdxjam.model.enums.RenderDataType;
import com.ugg.gdxjam.model.enums.VelocityDataType;
import com.ugg.gdxjam.view.Renderable;

/**
 * Created by Jose Cuellar on 05/01/2016.
 */
public class BulletData implements GameData {
    public RenderDataType renderableType;
    public BodyDataType bodyDataType;
    public float hitInterval = 0f;
    public int damage = 1;
    public float multiplier = 1f;
    public int life = 1;
    public int lifeDecrement = 1;
    public float lifeDuration = -1f;
    public VelocityDataType velType = null;
    public ParticleDataType particleDataType;

    public BulletData(RenderDataType renderableType, BodyDataType bodyDataType, float hitInterval, int damage,
                      float multiplier, int life, VelocityDataType velType, ParticleDataType particleDataType) {
        this( renderableType, bodyDataType, hitInterval, damage,
        multiplier, life, 1, -1f, velType, particleDataType);
    }

    public BulletData(RenderDataType renderableType, BodyDataType bodyDataType, float hitInterval, int damage,
            float multiplier, int life, int lifeDecrement, float lifeDuration, VelocityDataType velType, ParticleDataType particleDataType) {
        this.renderableType = renderableType;
        this.bodyDataType = bodyDataType;
        this.hitInterval = hitInterval;
        this.damage = damage;
        this.multiplier = multiplier;
        this.life = life;
        this.lifeDecrement = lifeDecrement;
        this.lifeDuration = lifeDuration;
        this.velType = velType;
        this.particleDataType = particleDataType;
    }


    @Override
    public void assignComponents(GameEntity gameEntity) {
        if(hitInterval > 0) {
            IntervalComponent intC = gameEntity.addComponent(IntervalComponent.class);
            intC.interval.limit = hitInterval;
        }

        DamageComponent dmgC = gameEntity.addComponent(DamageComponent.class);
        dmgC.damage = damage;
        dmgC.multiplier = multiplier;

        LifeComponent lifeC = gameEntity.addComponent(LifeComponent.class);
        lifeC.amount = life;
        lifeC.max = life;

        if(lifeDuration >= 0) {
            LifeDurationComponent lifeDurationC = gameEntity.addComponent(LifeDurationComponent.class);
            lifeDurationC.interval.limit = lifeDuration;
            lifeDurationC.gameEntity = gameEntity;
        }

        if(lifeDecrement > 0){
            LifeDecrementComponent lifeDecrementC = gameEntity.addComponent(LifeDecrementComponent.class);
            lifeDecrementC.amount = lifeDecrement;
        }

        gameEntity.addGameData(velType.get());

        if(particleDataType != null){
            gameEntity.addGameData(particleDataType.get());
        }
    }
}
