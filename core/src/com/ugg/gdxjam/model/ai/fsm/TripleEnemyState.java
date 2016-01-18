package com.ugg.gdxjam.model.ai.fsm;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.behaviors.Pursue;
import com.badlogic.gdx.ai.steer.behaviors.Wander;
import com.badlogic.gdx.math.Vector2;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Logger;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.SteerLocation;
import com.ugg.gdxjam.model.components.*;
import com.ugg.gdxjam.model.configs.Configs;

/**
 * Created by Jose Cuellar on 10/01/2016.
 */
public enum TripleEnemyState implements State<GameEntity> {

    Watch() {
        @Override
        public void enter(GameEntity gameEntity) {
            WeaponsComponent weaponC = Mappers.weapon.get(gameEntity.entity);
            weaponC.enabled = false;

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
                gameEntity.changeState(TripleEnemyState.Attack);

        }

        @Override
        public void exit(GameEntity gameEntity) {

        }
    },
    Attack(){
        @Override
        public void enter(GameEntity gameEntity) {
            WeaponsComponent weaponC = Mappers.weapon.get(gameEntity.entity);
            weaponC.enabled = true;

        }

        @Override
        public void update(GameEntity gameEntity) {
            LockOnComponent lockOnComponent = Mappers.lockOn.get(gameEntity.entity);
            if(lockOnComponent == null){
                gameEntity.changeState(TripleEnemyState.Watch);
                return;
            }
            if(lockOnComponent.target.isDead()){
                gameEntity.changeState(TripleEnemyState.Watch);
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
