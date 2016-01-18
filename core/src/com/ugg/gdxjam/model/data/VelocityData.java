package com.ugg.gdxjam.model.data;

import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.components.VelocityComponent;
import com.ugg.gdxjam.model.enums.VelocityType;

/**
 * Created by Jose Cuellar on 07/01/2016.
 */
public class VelocityData implements GameData {
    public float x = 0.0f;
    public float y = 0.0f;
    public float multiplier = 1f;
    public VelocityType type = VelocityType.Linear;

    public VelocityData(float x, float y, float multiplier, VelocityType type) {
        this.x = x;
        this.y = y;
        this.multiplier = multiplier;
        this.type = type;
    }

    @Override
    public void assignComponents(GameEntity gameEntity) {
        VelocityComponent velC = gameEntity.addComponent(VelocityComponent.class);
        velC.x = x;
        velC.y = y;
        velC.multiplier = multiplier;
        velC.type = type;
    }
}
