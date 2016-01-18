package com.ugg.gdxjam.model.ai.fsm;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.behaviors.*;
import com.badlogic.gdx.math.Vector2;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Logger;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.SteerLocation;
import com.ugg.gdxjam.model.components.*;
import com.ugg.gdxjam.model.configs.Configs;
import com.ugg.gdxjam.model.utils.BehaviorUtils;

/**
 * Created by Jose Cuellar on 10/01/2016.
 */
public enum WanderEnemyState implements State<GameEntity> {

    Wandering() {
        @Override
        public void enter(GameEntity gameEntity) {
            SensorComponent sensorComponent = Mappers.sensor.get(gameEntity.entity);
            WeaponsComponent weaponC = Mappers.weapon.get(gameEntity.entity);
            weaponC.enabled = false;
            sensorComponent.enabled = true;

            SteerableComponent steerableC = Mappers.steerable.get(gameEntity.entity);
            SteeringBehaviorComponent steeringBC = Mappers.steeringBehavior.get(gameEntity.entity);
            Wander wander = (Wander) gameEntity.getBehavior(Wander.class);
            if(wander == null) {
                wander = new Wander(steerableC);
            }
            wander.setOwner(steerableC);
            gameEntity.cacheBehavior(steeringBC.behavior);
            steeringBC.behavior = wander;
            Configs.wander.applyConfigs(steeringBC);

        }

        @Override
        public void update(GameEntity gameEntity) {
            if(Mappers.lockOn.has(gameEntity.entity))
                gameEntity.changeState(WanderEnemyState.Attack);

        }

        @Override
        public void exit(GameEntity gameEntity) {

        }
    },
    Attack(){
        @Override
        public void enter(GameEntity gameEntity) {
            SensorComponent sensorComponent = Mappers.sensor.get(gameEntity.entity);
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
            sensorComponent.enabled = false;

            Seek seek = (Seek) gameEntity.getBehavior(Seek.class);
            if (seek == null) {
                SteerLocation loc = new SteerLocation();
                loc.getPosition().set(0, 0);
                seek = new Seek(steerableC, loc);
                seek.setTarget(loc);
            }
            seek.setOwner(steerableC);
            /*Pursue pursue = (Pursue) gameEntity.getBehavior(Pursue.class);
            Steerable steerableTarget = Mappers.steerable.get(Mappers.lockOn.get(gameEntity.entity).target.entity);
            if (pursue == null) {
                SteerLocation loc = new SteerLocation();
                loc.getPosition().set(0, 0);
                pursue = new Pursue(steerableC, steerableTarget);
            }
            pursue.setTarget(steerableTarget);*/

            gameEntity.cacheBehavior(steeringBC.behavior);
            steeringBC.behavior = seek;
            //Configs.enemyPursue.applyConfigs(steeringBC);
            Configs.enemySeek.applyConfigs(steeringBC);
        }

        @Override
        public void update(GameEntity gameEntity) {
            LockOnComponent lockOnComponent = Mappers.lockOn.get(gameEntity.entity);
            if(lockOnComponent == null){
                gameEntity.changeState(WanderEnemyState.Return);
                return;
            }
            if(lockOnComponent.target.isDead()){
                gameEntity.changeState(WanderEnemyState.Return);
            }
        }

        @Override
        public void exit(GameEntity gameEntity) {
            WeaponsComponent weaponC = Mappers.weapon.get(gameEntity.entity);
            weaponC.enabled = false;
            gameEntity.entity.remove(LockOnComponent.class);
        }
    },
    Return(){
        @Override
        public void enter(GameEntity gameEntity) {
            SensorComponent sensorComponent = Mappers.sensor.get(gameEntity.entity);
            SteerableComponent steerableC = Mappers.steerable.get(gameEntity.entity);
            SteeringBehaviorComponent steeringBC = Mappers.steeringBehavior.get(gameEntity.entity);
            InitialPositionComponent initialPositionC = Mappers.initialPosition.get(gameEntity.entity);

            sensorComponent.enabled = true;

            Arrive arrive = (Arrive) gameEntity.getBehavior(Arrive.class);
            if (arrive == null) {
                arrive = BehaviorUtils.createArrive(0,0,steerableC);
            }
            arrive.setOwner(steerableC);
            SteerLocation targetPosition = BehaviorUtils.getTarget(arrive);
            targetPosition.getPosition().set(initialPositionC.x, initialPositionC.y);
            gameEntity.cacheBehavior(steeringBC.behavior);
            steeringBC.behavior = arrive;
            Configs.enemyArrive.applyConfigs(steeringBC);

            gameEntity.entity.remove(InitialPositionComponent.class);
        }

        @Override
        public void update(GameEntity gameEntity) {
            LockOnComponent lockOnC = Mappers.lockOn.get(gameEntity.entity);
            if(lockOnC != null) {
                gameEntity.changeState(WanderEnemyState.Attack);
                return;
            }

            PhysicsComponent physicsC = Mappers.physics.get(gameEntity.entity);
            Vector2 velocity = physicsC.body.getLinearVelocity();
            if(velocity.isZero()){
                gameEntity.changeState(WanderEnemyState.Wandering);
            }
        }

        @Override
        public void exit(GameEntity gameEntity) {

        }
    };

    @Override
    public boolean onMessage(GameEntity gameEntity, Telegram telegram) {
        return false;
    }
}
