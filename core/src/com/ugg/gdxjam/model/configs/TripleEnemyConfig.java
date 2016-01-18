package com.ugg.gdxjam.model.configs;

import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.ai.fsm.TripleEnemyState;
import com.ugg.gdxjam.model.ai.fsm.WatcherEnemyState;
import com.ugg.gdxjam.model.components.WeaponsComponent;

/**
 * Created by Jose Cuellar on 10/01/2016.
 */
public class TripleEnemyConfig implements BaseConfig<GameEntity>{

    @Override
    public void applyConfigs(GameEntity gameEntity) {
        /*WeaponsComponent weaponsComponent = Mappers.weapon.get(gameEntity.entity);
        weaponsComponent.enabled = true;*/
        gameEntity.changeState(TripleEnemyState.Watch);
    }
}
