package com.ugg.gdxjam.model.configs;

import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.ai.fsm.MotherShipAttackerEnemyState;
import com.ugg.gdxjam.model.ai.fsm.WanderEnemyState;

/**
 * Created by Jose Cuellar on 10/01/2016.
 */
public class MotherShipAttackerConfig implements BaseConfig<GameEntity>{

    @Override
    public void applyConfigs(GameEntity gameEntity) {
        gameEntity.changeState(MotherShipAttackerEnemyState.Attack);
    }
}
