package com.ugg.gdxjam.model.factories;

import com.badlogic.gdx.utils.Pools;
import com.ugg.gdxjam.controller.EngineController;
import com.ugg.gdxjam.controller.PhysicsController;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.TimeCounter;
import com.ugg.gdxjam.model.components.GameEntityTypeComponent;
import com.ugg.gdxjam.model.components.ParentComponent;
import com.ugg.gdxjam.model.data.BulletData;
import com.ugg.gdxjam.model.enums.*;

/**
 * Created by Jose Cuellar on 05/01/2016.
 */
public class BulletFactory extends GameEntityFactory<BulletDataType>{

    public BulletFactory(EngineController oEngineCtrl, PhysicsController oPhysicsCtrl) {
        super(oEngineCtrl, oPhysicsCtrl);
    }

    @Override
    public GameEntity create(BulletDataType dataType, GameEntityType entityType, GameEntity parent) {
        BulletData data = dataType.get();
        GameEntity bullet = createGameEntity(RenderDataType.get(data.renderableType), BodyDataType.get(data.bodyDataType), entityType);
        bullet.addGameData(data);
        oPhysicsCtrl.assignFilter(bullet, FilterCategory.BULLET, 0, entityType == GameEntityType.AllyBullet ? MaskBitsType.AllyBullet.get() : MaskBitsType.EnemyBullet.get());

        if(parent != null){
            ParentComponent parentC = bullet.addComponent(ParentComponent.class);
            parentC.parentEntity = parent;
        }

        return bullet;
    }
}
