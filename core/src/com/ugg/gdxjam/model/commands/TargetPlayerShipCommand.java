package com.ugg.gdxjam.model.commands;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.ugg.gdxjam.model.*;
import com.ugg.gdxjam.model.ai.fsm.MainShipState;
import com.ugg.gdxjam.model.components.FSMComponent;
import com.ugg.gdxjam.model.components.GameEntityTypeComponent;
import com.ugg.gdxjam.model.components.LockOnComponent;
import com.ugg.gdxjam.model.components.SteeringBehaviorComponent;
import com.ugg.gdxjam.model.enums.GameEntityType;
import com.ugg.gdxjam.model.utils.Box2dUtils;

/**
 * Created by Jose Cuellar on 09/01/2016.
 */
public class TargetPlayerShipCommand extends Command {

    @Override
    public boolean execute() {
        final Array<Object> args = cma.getArguments();

        Player player = (Player) args.get(0);
        float x = (float) args.get(3);
        float y = (float) args.get(4);
        GameEntity gameEntity = player.mainEntity;

        if(gameEntity.getState() != MainShipState.Damaged) {

            boolean hasTarget = false;
            Array<Body> aabbFound = Box2dUtils.queryAABBInput(x, y);

            if(aabbFound.size > 0) {
                for(Body b : aabbFound){
                    GameEntity abbEntity = (GameEntity) b.getUserData();
                    GameEntityType entityType = Mappers.gameEntityType.get(abbEntity.entity).type;
                    if(entityType == GameEntityType.Enemy || entityType == GameEntityType.Scenario) {
                        LockOnComponent lockOnComponent = Mappers.lockOn.get(gameEntity.entity);
                        if(lockOnComponent == null)
                            lockOnComponent = gameEntity.addComponent(LockOnComponent.class);
                        lockOnComponent.origin = gameEntity;
                        lockOnComponent.target = abbEntity;
                        gameEntity.changeState(MainShipState.LockOn);
                        hasTarget = true;
                        break;
                    }
                }
            }

            if(!hasTarget){
                SteeringBehaviorComponent steeringBC = Mappers.steeringBehavior.get(gameEntity.entity);
                Steerable steerableC = Mappers.steerable.get(gameEntity.entity);
                Arrive<Vector2> arrive = (Arrive<Vector2>) gameEntity.getBehavior(Arrive.class);
                if (arrive == null) {
                    SteerLocation location = new SteerLocation();
                    arrive = new Arrive<Vector2>(steerableC, location);
                    arrive.setArrivalTolerance(0.5f);
                    arrive.setDecelerationRadius(10f);
                    arrive.setTimeToTarget(0.005f);
                }
                arrive.setOwner(steerableC);
                gameEntity.cacheBehavior(steeringBC.behavior);
                steeringBC.behavior = arrive;
                arrive.getTarget().getPosition().set(x, y);

                gameEntity.changeState(MainShipState.Ready);
            }
        }
        return true;
    }

    @Override
    public void undo() {

    }

    @Override
    public void reset() {
        cma.clear();
    }
}
