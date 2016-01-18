package com.ugg.gdxjam.model.ai.fsm;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.ai.steer.behaviors.Pursue;
import com.badlogic.gdx.ai.steer.behaviors.Wander;
import com.badlogic.gdx.math.Vector2;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Logger;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.SteerLocation;
import com.ugg.gdxjam.model.components.*;
import com.ugg.gdxjam.model.configs.Configs;
import com.ugg.gdxjam.model.enums.GameMusic;
import com.ugg.gdxjam.model.utils.BehaviorUtils;
import com.ugg.gdxjam.model.utils.MusicManager;
import com.ugg.gdxjam.model.utils.Vector2Utils;

import java.util.concurrent.locks.Lock;

/**
 * Created by Jose Cuellar on 10/01/2016.
 */
public enum MotherShipEnemyState implements State<GameEntity> {

    Watch() {
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
            Configs.watcher.applyConfigs(steeringBC);
        }

        @Override
        public void update(GameEntity gameEntity) {
            if(Mappers.lockOn.has(gameEntity.entity))
                gameEntity.changeState(MotherShipEnemyState.Shoot);

        }

        @Override
        public void exit(GameEntity gameEntity) {

        }
    },
    Shoot(){
        @Override
        public void enter(GameEntity gameEntity) {
            MusicManager.getInstance().loadAndPlay(GameMusic.Boss);

            SensorComponent sensorC = Mappers.sensor.get(gameEntity.entity);
            PhysicsComponent physicsC = Mappers.physics.get(gameEntity.entity);
            WeaponsComponent weaponC = Mappers.weapon.get(gameEntity.entity);
            SteerableComponent steerableC = Mappers.steerable.get(gameEntity.entity);
            SteeringBehaviorComponent steeringBC = Mappers.steeringBehavior.get(gameEntity.entity);
            InitialPositionComponent initialPositionC = Mappers.initialPosition.get(gameEntity.entity);
            IntervalComponent intervalC = gameEntity.addComponent(IntervalComponent.class);

            //LockOn, no need for the sensor
            sensorC.enabled = false;

            //Limit the shooting, has to be checked on update
            intervalC.interval.limit = Configs.mothership.shootingInterval;

            //Set the initial position, so it can return later
            if(initialPositionC == null)
                initialPositionC = gameEntity.addComponent(InitialPositionComponent.class);
            Vector2 entityPos = physicsC.body.getPosition();
            initialPositionC.x = entityPos.x;
            initialPositionC.y = entityPos.y;

            weaponC.enabled = true;
            weaponC.weapons.get(0).enabled = true;
            weaponC.weapons.get(1).enabled = false;

            steerableC = Mappers.steerable.get(gameEntity.entity);
            steeringBC = Mappers.steeringBehavior.get(gameEntity.entity);
            Wander wander = (Wander) gameEntity.getBehavior(Wander.class);
            if(wander == null) {
                wander = new Wander(steerableC);
            }
            wander.setOwner(steerableC);
            gameEntity.cacheBehavior(steeringBC.behavior);
            steeringBC.behavior = wander;
            Configs.watcher.applyConfigs(steeringBC);
        }

        @Override
        public void update(GameEntity gameEntity) {
            super.update(gameEntity);
            IntervalComponent intervalC = Mappers.interval.get(gameEntity.entity);
            if(intervalC != null && intervalC.tickComplete)
                gameEntity.changeState(MotherShipEnemyState.ArriveLeft);
        }

        @Override
        public void exit(GameEntity gameEntity) {
            WeaponsComponent weaponC = Mappers.weapon.get(gameEntity.entity);
            if(weaponC != null && weaponC.weapons.size > 0){
                weaponC.weapons.get(0).enabled = false;
                if(weaponC.weapons.size > 1)
                    weaponC.weapons.get(1).enabled = true;
            }
            LockOnComponent lockOnC = Mappers.lockOn.get(gameEntity.entity);
            if(lockOnC != null) {
                lockOnC.updateTargetLocation = false;
                gameEntity.entity.remove(IntervalComponent.class);
            }

        }
    },
    ArriveLeft(){
        @Override
        public void enter(GameEntity gameEntity) {
            SteerableComponent steerableC = Mappers.steerable.get(gameEntity.entity);
            SteeringBehaviorComponent steeringBC = Mappers.steeringBehavior.get(gameEntity.entity);
            PhysicsComponent physicsC = Mappers.physics.get(gameEntity.entity);
            Vector2 position = physicsC.body.getPosition();

            Arrive arrive = (Arrive) gameEntity.getBehavior(Arrive.class);
            if (arrive == null) {
                arrive = BehaviorUtils.createArrive(0,0, steerableC);
            }
            arrive.setOwner(steerableC);
            SteerLocation targetPosition = BehaviorUtils.getTarget(arrive);
            targetPosition.getPosition().set(position.x - 30, position.y + 30);
            gameEntity.cacheBehavior(steeringBC.behavior);
            steeringBC.behavior = arrive;
            Configs.enemyArrive.applyConfigs(steeringBC);
        }

        @Override
        public void update(GameEntity gameEntity) {
            super.update(gameEntity);
            PhysicsComponent physicsC = Mappers.physics.get(gameEntity.entity);
            Vector2 velocity = physicsC.body.getLinearVelocity();
            if(velocity.isZero()){
                gameEntity.changeState(MotherShipEnemyState.ArriveRight);
            }
        }

        @Override
        public void exit(GameEntity gameEntity) {

        }
    },
    ArriveRight(){
        @Override
        public void enter(GameEntity gameEntity) {
            SteerableComponent steerableC = Mappers.steerable.get(gameEntity.entity);
            SteeringBehaviorComponent steeringBC = Mappers.steeringBehavior.get(gameEntity.entity);
            PhysicsComponent physicsC = Mappers.physics.get(gameEntity.entity);
            Vector2 position = physicsC.body.getPosition();

            Arrive arrive = (Arrive) gameEntity.getBehavior(Arrive.class);
            if (arrive == null) {
                arrive = BehaviorUtils.createArrive(0, 0, steerableC);
            }
            arrive.setOwner(steerableC);
            SteerLocation targetPosition = BehaviorUtils.getTarget(arrive);
            targetPosition.getPosition().set(position.x + 60, position.y);
            gameEntity.cacheBehavior(steeringBC.behavior);
            steeringBC.behavior = arrive;
            Configs.enemyArrive.applyConfigs(steeringBC);
        }

        @Override
        public void update(GameEntity gameEntity) {
            super.update(gameEntity);
            PhysicsComponent physicsC = Mappers.physics.get(gameEntity.entity);
            InitialPositionComponent initialPositionC = Mappers.initialPosition.get(gameEntity.entity);
            Vector2 velocity = physicsC.body.getLinearVelocity();
            Vector2 position = physicsC.body.getPosition();
            if(initialPositionC != null && velocity.isZero() && position.x > initialPositionC.x){
                gameEntity.changeState(MotherShipEnemyState.ArriveDown);
            }
        }

        @Override
        public void exit(GameEntity gameEntity) {

        }
    },
    ArriveDown(){
        @Override
        public void enter(GameEntity gameEntity) {
            SteerableComponent steerableC = Mappers.steerable.get(gameEntity.entity);
            SteeringBehaviorComponent steeringBC = Mappers.steeringBehavior.get(gameEntity.entity);
            PhysicsComponent physicsC = Mappers.physics.get(gameEntity.entity);
            Vector2 position = physicsC.body.getPosition();

            Arrive arrive = (Arrive) gameEntity.getBehavior(Arrive.class);
            if (arrive == null) {
                arrive = BehaviorUtils.createArrive(0,0, steerableC);
            }
            arrive.setOwner(steerableC);
            SteerLocation targetPosition = BehaviorUtils.getTarget(arrive);
            targetPosition.getPosition().set(position.x - 20f, position.y - 40f);
            gameEntity.cacheBehavior(steeringBC.behavior);
            steeringBC.behavior = arrive;
            Configs.enemyArrive.applyConfigs(steeringBC);
        }

        @Override
        public void update(GameEntity gameEntity) {
            super.update(gameEntity);
            PhysicsComponent physicsC = Mappers.physics.get(gameEntity.entity);
            InitialPositionComponent initialPositionC = Mappers.initialPosition.get(gameEntity.entity);
            Vector2 velocity = physicsC.body.getLinearVelocity();
            Vector2 position = physicsC.body.getPosition();
            if(velocity.isZero() && position.y < initialPositionC.y){
                gameEntity.changeState(MotherShipEnemyState.ArriveUp);
            }
        }

        @Override
        public void exit(GameEntity gameEntity) {

        }
    },
    ArriveUp(){
        @Override
        public void enter(GameEntity gameEntity) {
            SteerableComponent steerableC = Mappers.steerable.get(gameEntity.entity);
            SteeringBehaviorComponent steeringBC = Mappers.steeringBehavior.get(gameEntity.entity);
            PhysicsComponent physicsC = Mappers.physics.get(gameEntity.entity);
            InitialPositionComponent initialPositionC = Mappers.initialPosition.get(gameEntity.entity);

            Arrive arrive = (Arrive) gameEntity.getBehavior(Arrive.class);
            if (arrive == null) {
                arrive = BehaviorUtils.createArrive(0,0, steerableC);
            }
            arrive.setOwner(steerableC);
            SteerLocation targetPosition = BehaviorUtils.getTarget(arrive);
            targetPosition.getPosition().set(initialPositionC.x, initialPositionC.y);
            gameEntity.cacheBehavior(steeringBC.behavior);
            steeringBC.behavior = arrive;
            Configs.enemyArrive.applyConfigs(steeringBC);
        }

        @Override
        public void update(GameEntity gameEntity) {
            super.update(gameEntity);
            PhysicsComponent physicsC = Mappers.physics.get(gameEntity.entity);
            InitialPositionComponent initialPositionC = Mappers.initialPosition.get(gameEntity.entity);
            Vector2 velocity = physicsC.body.getLinearVelocity();
            Vector2 position = physicsC.body.getPosition();
            if(velocity.isZero() && position.y >= initialPositionC.y -  0.5f){
                gameEntity.changeState(MotherShipEnemyState.Shoot);
            }
        }

        @Override
        public void exit(GameEntity gameEntity) {

        }
    },
    Return(){
        @Override
        public void enter(GameEntity gameEntity) {
            MusicManager.getInstance().loadAndPlay(GameMusic.Main);
            gameEntity.entity.remove(IntervalComponent.class);
            gameEntity.entity.remove(LockOnComponent.class);

            WeaponsComponent weaponC = Mappers.weapon.get(gameEntity.entity);
            SensorComponent sensorComponent = Mappers.sensor.get(gameEntity.entity);
            SteerableComponent steerableC = Mappers.steerable.get(gameEntity.entity);
            SteeringBehaviorComponent steeringBC = Mappers.steeringBehavior.get(gameEntity.entity);
            InitialPositionComponent initialPositionC = Mappers.initialPosition.get(gameEntity.entity);

            weaponC.enabled = false;
            sensorComponent.enabled = false;

            Arrive arrive = (Arrive) gameEntity.getBehavior(Arrive.class);
            if (arrive == null) {
                arrive = BehaviorUtils.createArrive(0,0, steerableC);
            }
            arrive.setOwner(steerableC);
            SteerLocation targetPosition = BehaviorUtils.getTarget(arrive);
            targetPosition.getPosition().set(initialPositionC.x, initialPositionC.y);
            gameEntity.cacheBehavior(steeringBC.behavior);
            steeringBC.behavior = arrive;
            Configs.enemyArrive.applyConfigs(steeringBC);

            //gameEntity.entity.remove(InitialPositionComponent.class);
        }

        @Override
        public void update(GameEntity gameEntity) {
            PhysicsComponent physicsC = Mappers.physics.get(gameEntity.entity);
            Vector2 velocity = physicsC.body.getLinearVelocity();
            if(velocity.isZero()){
                gameEntity.changeState(MotherShipEnemyState.Watch);
            }
        }

        @Override
        public void exit(GameEntity gameEntity) {

        }
    }
    ;

    @Override
    public void update(GameEntity gameEntity) {
        LockOnComponent lockOnComponent = Mappers.lockOn.get(gameEntity.entity);
        if(lockOnComponent == null){
            gameEntity.changeState(MotherShipEnemyState.Return);
            return;
        }
        if(lockOnComponent.target == null || lockOnComponent.target.isDead()){
            gameEntity.changeState(MotherShipEnemyState.Return);
            return;
        }
        GameEntity targetEntity = lockOnComponent.target;
        Vector2 targetPos = Mappers.physics.get(targetEntity.entity).body.getPosition();
        Vector2 originPos = Mappers.physics.get(gameEntity.entity).body.getPosition();
        if(Vector2.dst(originPos.x, originPos.y, targetPos.x, targetPos.y) > Configs.mothership.distanceToLostLockOn){
            gameEntity.changeState(MotherShipEnemyState.Return);
            return;
        }
    }

    @Override
    public boolean onMessage(GameEntity gameEntity, Telegram telegram) {
        return false;
    }
}
