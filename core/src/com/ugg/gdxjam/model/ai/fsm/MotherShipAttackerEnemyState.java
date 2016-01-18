package com.ugg.gdxjam.model.ai.fsm;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.ai.steer.behaviors.Pursue;
import com.badlogic.gdx.ai.steer.behaviors.Seek;
import com.badlogic.gdx.ai.steer.behaviors.Wander;
import com.badlogic.gdx.math.Vector2;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Logger;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.SteerLocation;
import com.ugg.gdxjam.model.components.*;
import com.ugg.gdxjam.model.configs.Configs;

import java.util.concurrent.locks.Lock;

/**
 * Created by Jose Cuellar on 10/01/2016.
 */
public enum MotherShipAttackerEnemyState implements State<GameEntity> {

    Attack(){
        @Override
        public void enter(GameEntity gameEntity) {
            SensorComponent sensorComponent = Mappers.sensor.get(gameEntity.entity);
            ParentComponent parentC = Mappers.parent.get(gameEntity.entity);
            LockOnComponent lockOnC = Mappers.lockOn.get(parentC.parentEntity.entity);
            if(lockOnC == null || lockOnC.target == null)
                gameEntity.changeState(WanderEnemyState.Wandering);
            LockOnComponent thisLockOnC = gameEntity.addComponent(LockOnComponent.class);
            thisLockOnC.origin = gameEntity;
            thisLockOnC.target = lockOnC.target;
            thisLockOnC.lockOnTime.limit = -1f;

            sensorComponent.enabled = false;

            PhysicsComponent physicsC = Mappers.physics.get(gameEntity.entity);
            WeaponsComponent weaponC = Mappers.weapon.get(gameEntity.entity);
            SteerableComponent steerableC = Mappers.steerable.get(gameEntity.entity);
            SteeringBehaviorComponent steeringBC = Mappers.steeringBehavior.get(gameEntity.entity);
            InitialPositionComponent initialPositionC = Mappers.initialPosition.get(gameEntity.entity);
            if(initialPositionC == null)
                initialPositionC = gameEntity.addComponent(InitialPositionComponent.class);
            Vector2 entityPos = physicsC.body.getPosition();
            initialPositionC.x = entityPos.x;
            initialPositionC.y = entityPos.y;

            weaponC.enabled = true;
            Pursue pursue = (Pursue) gameEntity.getBehavior(Pursue.class);
            Steerable steerableTarget = Mappers.steerable.get(Mappers.lockOn.get(gameEntity.entity).target.entity);
            if (pursue == null) {
                pursue = new Pursue(steerableC, steerableTarget);
            }
            pursue.setTarget(steerableTarget);

            gameEntity.cacheBehavior(steeringBC.behavior);
            steeringBC.behavior = pursue;
            Configs.enemyPursue.applyConfigs(steeringBC);
        }

        @Override
        public void update(GameEntity gameEntity) {
            LockOnComponent lockOnComponent = Mappers.lockOn.get(gameEntity.entity);
            if(lockOnComponent == null){
                gameEntity.changeState(WanderEnemyState.Wandering);
                return;
            }
            if(lockOnComponent.target.isDead()){
                gameEntity.changeState(WanderEnemyState.Wandering);
            }
        }

        @Override
        public void exit(GameEntity gameEntity) {
            WeaponsComponent weaponC = Mappers.weapon.get(gameEntity.entity);
            weaponC.enabled = false;
            gameEntity.entity.remove(LockOnComponent.class);
        }
    };

    @Override
    public boolean onMessage(GameEntity gameEntity, Telegram telegram) {
        return false;
    }
}
