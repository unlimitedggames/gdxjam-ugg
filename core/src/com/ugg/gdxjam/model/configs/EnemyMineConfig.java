package com.ugg.gdxjam.model.configs;

import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.ai.fsm.TripleEnemyState;
import com.ugg.gdxjam.model.components.DamageComponent;
import com.ugg.gdxjam.model.components.LifeComponent;
import com.ugg.gdxjam.model.components.LifeDecrementComponent;

/**
 * Created by Jose Cuellar on 10/01/2016.
 */
public class EnemyMineConfig implements BaseConfig<GameEntity>{

    @Override
    public void applyConfigs(GameEntity gameEntity) {
        LifeDecrementComponent lifeDecC = gameEntity.addComponent(LifeDecrementComponent.class);
        DamageComponent damageC = gameEntity.addComponent(DamageComponent.class);
        LifeComponent lifeC = Mappers.life.get(gameEntity.entity);
        lifeDecC.amount = lifeC.amount;
        damageC.damage = lifeC.amount;
        damageC.multiplier = 1f;
    }
}
