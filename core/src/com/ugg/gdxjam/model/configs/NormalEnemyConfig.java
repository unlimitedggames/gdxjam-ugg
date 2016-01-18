package com.ugg.gdxjam.model.configs;

import com.badlogic.gdx.ai.steer.behaviors.Face;
import com.badlogic.gdx.ai.steer.behaviors.Wander;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.SteerLocation;
import com.ugg.gdxjam.model.ai.fsm.MainShipState;
import com.ugg.gdxjam.model.ai.fsm.WanderEnemyState;
import com.ugg.gdxjam.model.components.*;
import com.ugg.gdxjam.model.enums.BuildingType;
import com.ugg.gdxjam.model.enums.GameEntityType;
import com.ugg.gdxjam.model.sensors.RayCastSensor;

/**
 * Created by Jose Cuellar on 10/01/2016.
 */
public class NormalEnemyConfig implements BaseConfig<GameEntity>{

    @Override
    public void applyConfigs(GameEntity gameEntity) {
        gameEntity.changeState(WanderEnemyState.Wandering);
    }
}
