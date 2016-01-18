package com.ugg.gdxjam.model.configs;

import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.ai.fsm.MotherShipEnemyState;
import com.ugg.gdxjam.model.components.ChangeMusicComponent;
import com.ugg.gdxjam.model.enums.GameMusic;

/**
 * Created by Jose Cuellar on 10/01/2016.
 */
public class MotherShipConfig implements BaseConfig<GameEntity>{

    public static final float distanceToLostLockOn = 160f;
    public static final float shootingInterval = 15f;

    @Override
    public void applyConfigs(GameEntity gameEntity) {
        ChangeMusicComponent changeMusicC = gameEntity.addComponent(ChangeMusicComponent.class);
        changeMusicC.music = GameMusic.Main;
        gameEntity.changeState(MotherShipEnemyState.Watch);
    }
}
