package com.ugg.gdxjam.model.ai.fsm;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.ai.steer.behaviors.Face;
import com.badlogic.gdx.math.Vector2;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Logger;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.SteerLocation;
import com.ugg.gdxjam.model.components.*;
import com.ugg.gdxjam.model.configs.Configs;
import com.ugg.gdxjam.model.enums.TelegramMessage;

/**
 * Created by Jose Cuellar on 10/01/2016.
 */
public enum MainShipState implements State<GameEntity> {

    Ready() {
        @Override
        public void enter(GameEntity gameEntity) {
        }

        @Override
        public void update(GameEntity gameEntity) {

        }

        @Override
        public void exit(GameEntity gameEntity) {

        }
    },
    LockOn(){
        @Override
        public void enter(GameEntity gameEntity) {
            WeaponsComponent weaponC = Mappers.weapon.get(gameEntity.entity);
            SteerableComponent steerableC = Mappers.steerable.get(gameEntity.entity);
            SteeringBehaviorComponent steeringBC = Mappers.steeringBehavior.get(gameEntity.entity);
            weaponC.enabled = true;
            Face face = (Face<Vector2>) gameEntity.getBehavior(Face.class);
            if (face == null) {
                SteerLocation loc = new SteerLocation();
                loc.getPosition().set(0, 0);
                face = new Face(steerableC, loc);
                face.setTarget(loc);
                face.setTimeToTarget(0.001f);
                face.setAlignTolerance(0.0001f);
                face.setDecelerationRadius(0f);
            }
            face.setOwner(steerableC);
            gameEntity.cacheBehavior(steeringBC.behavior);
            steeringBC.behavior = face;
           // face.getTarget().getPosition().set(x, y);
        }

        @Override
        public void update(GameEntity gameEntity) {
            LockOnComponent lockOnComponent = Mappers.lockOn.get(gameEntity.entity);
            if(lockOnComponent.target == null || lockOnComponent.target.isDead()){
                gameEntity.changeState(MainShipState.Ready);
            }
        }

        @Override
        public void exit(GameEntity gameEntity) {
            WeaponsComponent weaponC = Mappers.weapon.get(gameEntity.entity);
            weaponC.enabled = false;
            gameEntity.entity.remove(LockOnComponent.class);
        }

        @Override
        public boolean onMessage(GameEntity gameEntity, Telegram telegram) {
            /*TelegramMessage telegramMessage = TelegramMessage.values()[telegram.message];
            switch(telegramMessage){
                case TargetKilled:
                    gameEntity.entity.remove(LockOnComponent.class);
                    return true;
            }*/
            return false;
        }
    },
    Damaged(){
        @Override
        public void enter(GameEntity gameEntity) {
            IntervalComponent intervalToRegenerate = gameEntity.addComponent(IntervalComponent.class);
            PhysicsComponent physicsComponent = Mappers.physics.get(gameEntity.entity);
            physicsComponent.body.setActive(false);
            intervalToRegenerate.interval.limit = Configs.mainShip.timeToRegenerate;
        }

        @Override
        public void update(GameEntity gameEntity) {
            IntervalComponent intervalToRegenerate = Mappers.interval.get(gameEntity.entity);
            if(intervalToRegenerate.tickComplete){
                PhysicsComponent physicsC = Mappers.physics.get(gameEntity.entity);
                LifeComponent lifeC = Mappers.life.get(gameEntity.entity);
                physicsC.body.setActive(true);
                lifeC.amount = (int)(lifeC.max * 0.5f);
                gameEntity.entity.remove(IntervalComponent.class);
                gameEntity.changeState(MainShipState.Ready);
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
