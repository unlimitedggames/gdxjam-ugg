package com.ugg.gdxjam.model.sensors;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Pool;
import com.ugg.gdxjam.controller.PhysicsController;
import com.ugg.gdxjam.model.Clonable;
import com.ugg.gdxjam.model.GameEntity;

/**
 * Created by Jose Cuellar on 14/01/2016.
 */
public interface BaseSensor extends Pool.Poolable, Clonable<BaseSensor> {

    public BaseSensor detect();
    public BaseSensor processDetection();
    public BaseSensor setController(PhysicsController physicsController);
    public BaseSensor setOwner(GameEntity owner);
    public BaseSensor debug(ShapeRenderer renderer);
}
