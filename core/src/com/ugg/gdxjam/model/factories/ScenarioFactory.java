package com.ugg.gdxjam.model.factories;

import com.ugg.gdxjam.controller.EngineController;
import com.ugg.gdxjam.controller.PhysicsController;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.components.SteerableComponent;
import com.ugg.gdxjam.model.components.SteeringBehaviorComponent;
import com.ugg.gdxjam.model.data.TroopData;
import com.ugg.gdxjam.model.enums.*;

/**
 * Created by Jose Cuellar on 05/01/2016.
 */
public class ScenarioFactory extends GameEntityFactory<ScenarioDataType>{

    public ScenarioFactory(EngineController oEngineCtrl, PhysicsController oPhysicsCtrl) {
        super(oEngineCtrl, oPhysicsCtrl);
    }

    @Override
    public GameEntity create(ScenarioDataType dataType, GameEntityType entityType, GameEntity parent) {
        GameEntity gameEntity = createGameEntity(entityType);
        gameEntity.addGameData(dataType.get());
        if(dataType.get().bodyDataType != null)
            oPhysicsCtrl.assignFilter(gameEntity, FilterCategory.SCENARIO,
                    0,
                    MaskBitsType.Scenario.get());
        return gameEntity;
    }


}
