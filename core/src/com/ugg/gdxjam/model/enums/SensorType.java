package com.ugg.gdxjam.model.enums;

import com.ugg.gdxjam.model.configs.Configs;
import com.ugg.gdxjam.model.data.ScenarioData;
import com.ugg.gdxjam.model.sensors.BaseSensor;
import com.ugg.gdxjam.model.sensors.RayCastSensor;

/**
 * Created by Jose Cuellar on 14/01/2016.
 */
public enum SensorType {

    NormalEnemySensor(new RayCastSensor(25f, 10f, GameEntityType.Ally)),
    WatcherEnemySensor(new RayCastSensor(30f, 8.5f, GameEntityType.Ally)),
    TripleEnemySensor(new RayCastSensor(38f, 11f, GameEntityType.Ally)),
    MotherShipSensor(new RayCastSensor(Configs.mothership.distanceToLostLockOn, -1f, GameEntityType.Ally));

    private final BaseSensor data;

    private SensorType(
            BaseSensor data)
    {
        this.data = data;
    }

    public BaseSensor get()
    {
        return data;
    }

}
