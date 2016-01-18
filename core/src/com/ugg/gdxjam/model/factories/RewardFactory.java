package com.ugg.gdxjam.model.factories;

import com.ugg.gdxjam.controller.EngineController;
import com.ugg.gdxjam.controller.PhysicsController;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.enums.*;

/**
 * Created by Jose Cuellar on 05/01/2016.
 */
public class RewardFactory extends GameEntityFactory<RewardDataType>{

    public RewardFactory(EngineController oEngineCtrl, PhysicsController oPhysicsCtrl) {
        super(oEngineCtrl, oPhysicsCtrl);
    }

    @Override
    public GameEntity create(RewardDataType dataType, GameEntityType entityType, GameEntity parent) {
        GameEntity gameEntity = createGameEntity(entityType);
        gameEntity.addGameData(dataType.get());
        if(dataType.get().bodyDataType != null)
            oPhysicsCtrl.assignFilter(gameEntity, FilterCategory.REWARD,
                    0,
                    MaskBitsType.Reward.get());
        return gameEntity;
    }


}
