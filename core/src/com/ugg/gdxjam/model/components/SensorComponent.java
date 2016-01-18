package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.ugg.gdxjam.model.sensors.BaseSensor;

/**
 * Created by Jose Cuellar on 14/01/2016.
 */
public class SensorComponent implements Component, Pool.Poolable {
    public BaseSensor sensor;
    public boolean enabled = true;

    @Override
    public void reset() {
        sensor.reset();
        enabled = true;
    }
}
