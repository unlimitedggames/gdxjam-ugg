package com.ugg.gdxjam.model.enums;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;

/**
 * Created by Jose Cuellar on 17/01/2016.
 */
public enum ParticleType {

    MainBooster("mainship-booster.p")
    ,EnemyExplosion("enemy-explosion.p")
    ,MotherShipExplosion("mothership-explosion.p")
    ,MeteoriteExplosion("meteorite-explosion.p")
    ,EnemyBulletHit("enemy-bullet-hit.p")
    ,AllyBulletHit("ally-bullet-hit.p");

    public String file;

    private ParticleType (String file) {
        this.file = file;
    }


}
