package com.ugg.gdxjam.model.sensors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ugg.gdxjam.controller.PhysicsController;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.components.LockOnComponent;
import com.ugg.gdxjam.model.components.PhysicsComponent;
import com.ugg.gdxjam.model.enums.GameEntityType;
import com.ugg.gdxjam.model.utils.Vector2Utils;

/**
 * Created by Jose Cuellar on 14/01/2016.
 */
public class RayCastSensor implements BaseSensor {

    public final Array<GameEntityType> typesToDetect;
    public GameEntity owner;
    public float radius = 1f;
    public float lockOnInterval = -1f;
    private PhysicsController physicsCtrl;
    private float previousXF, previousYF;
    private final Array<GameEntity> gameEntitiesFound;

    public RayCastSensor(float radius, float lockOnInterval, GameEntityType... typesToDetect){
        this(radius, lockOnInterval, null, typesToDetect);
    }

    public RayCastSensor(float radius, float lockOnInterval, GameEntity owner, GameEntityType... typesToDetect) {
        this.typesToDetect = new Array<>(typesToDetect);
        this.gameEntitiesFound = new Array<>();
        this.lockOnInterval = lockOnInterval;
        this.radius = radius;
        this.owner = owner;
    }

    @Override
    public BaseSensor detect() {
        PhysicsComponent physicsC = Mappers.physics.get(owner.entity);
        Vector2 position = physicsC.body.getPosition();
        final float angle = physicsC.body.getAngle();
        final float xf = Vector2Utils.getXf(position.x, radius, angle);// radius * MathUtils.cos(angle) + position.x;
        final float yf = Vector2Utils.getYf(position.y, radius, angle);//radius * MathUtils.sin(angle) + position.y;
        if(previousXF != xf || previousYF != yf) {
            gameEntitiesFound.addAll(physicsCtrl.raycastEntities(typesToDetect, position.x, position.y, xf, yf));
            previousXF = xf;
            previousYF = yf;
        }
        return this;
    }

    @Override
    public BaseSensor processDetection() {
        if(gameEntitiesFound.size > 0){
            LockOnComponent lockOnC = owner.addComponent(LockOnComponent.class);
            lockOnC.origin = owner;
            lockOnC.target =  gameEntitiesFound.get(0);
            lockOnC.lockOnTime.limit = this.lockOnInterval;
            gameEntitiesFound.clear();
        }
        return this;
    }

    @Override
    public BaseSensor setController(PhysicsController physicsController) {
        this.physicsCtrl = physicsController;
        return this;
    }

    @Override
    public BaseSensor setOwner(GameEntity owner) {
        this.owner = owner;
        return this;
    }

    @Override
    public BaseSensor debug(ShapeRenderer renderer) {
        PhysicsComponent physicsC = Mappers.physics.get(owner.entity);
        Vector2 position = physicsC.body.getPosition();
        renderer.line(position.x, position.y, previousXF, previousYF, Color.WHITE, Color.WHITE);
        return this;
    }


    @Override
    public void reset() {
        radius = 1f;
        previousXF = previousYF = 0;
        gameEntitiesFound.clear();
        typesToDetect.clear();
    }

    @Override
    public BaseSensor cloneObject() {
        return new RayCastSensor(this.radius, this.lockOnInterval, typesToDetect.toArray());
    }
}
