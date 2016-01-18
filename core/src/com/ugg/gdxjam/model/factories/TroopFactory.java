package com.ugg.gdxjam.model.factories;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.ai.steer.behaviors.Face;
import com.badlogic.gdx.ai.steer.behaviors.ReachOrientation;
import com.badlogic.gdx.ai.steer.behaviors.Wander;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pools;
import com.ugg.gdxjam.controller.EngineController;
import com.ugg.gdxjam.controller.PhysicsController;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.SteerLocation;
import com.ugg.gdxjam.model.components.*;
import com.ugg.gdxjam.model.data.TroopData;
import com.ugg.gdxjam.model.enums.*;
import com.ugg.gdxjam.model.sensors.RayCastSensor;
import sun.management.Sensor;

import java.util.logging.Filter;

/**
 * Created by Jose Cuellar on 05/01/2016.
 */
public class TroopFactory extends GameEntityFactory<TroopDataType>{

    public TroopFactory(EngineController oEngineCtrl, PhysicsController oPhysicsCtrl) {
        super(oEngineCtrl, oPhysicsCtrl);
    }

    @Override
    public GameEntity create(TroopDataType dataType, GameEntityType entityType, GameEntity parent) {
        TroopData data = TroopDataType.get(dataType);
        GameEntity troop = createGameEntity(RenderDataType.get(data.renderableType), BodyDataType.get(data.bodyDataType), entityType);
        troop.addGameData(data);
        oPhysicsCtrl.assignFilter(troop, entityType == GameEntityType.Ally ? FilterCategory.ALLY : FilterCategory.ENEMY,
                0,
                entityType == GameEntityType.Ally ? MaskBitsType.Ally.get() : MaskBitsType.Enemy.get());

        SteerableComponent steerableC = troop.addComponent(SteerableComponent.class);
        steerableC.body = Mappers.physics.get(troop.entity).body;
        steerableC.boundingRadius = 10;

        SteeringBehaviorComponent steeringBC = troop.addComponent(SteeringBehaviorComponent.class);

        if(data.sensorType != null) {
            SensorComponent sensorComponent = troop.addComponent(SensorComponent.class);
            sensorComponent.sensor = data.sensorType.get().cloneObject();
            sensorComponent.sensor.setController(oPhysicsCtrl).setOwner(troop);
        }

        if(parent != null){
            ParentComponent parentC = troop.addComponent(ParentComponent.class);
            parentC.parentEntity = parent;
        }

        if(data.config != null)
            data.config.applyConfigs(troop);

        return troop;
    }


}
