package com.ugg.gdxjam.model.configs;

import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.ai.fsm.WanderEnemyState;
import com.ugg.gdxjam.model.ai.fsm.WatcherEnemyState;

/**
 * Created by Jose Cuellar on 10/01/2016.
 */
public class WatcherEnemyConfig implements BaseConfig<GameEntity>{

    @Override
    public void applyConfigs(GameEntity gameEntity) {
        gameEntity.changeState(WatcherEnemyState.Watch);
    }
}
