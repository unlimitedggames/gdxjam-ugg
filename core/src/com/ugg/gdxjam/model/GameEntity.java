package com.ugg.gdxjam.model;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.ugg.gdxjam.controller.EngineController;
import com.ugg.gdxjam.model.ai.fsm.MainShipState;
import com.ugg.gdxjam.model.components.*;
import com.ugg.gdxjam.model.data.GameData;
import com.ugg.gdxjam.model.enums.EntityEventType;
import com.ugg.gdxjam.model.enums.GameEntityType;
import com.ugg.gdxjam.model.enums.ParticleType;
import com.ugg.gdxjam.model.utils.MusicManager;
import com.ugg.gdxjam.model.utils.SoundManager;

public class GameEntity implements Poolable{
	public Entity entity;
    public EngineController engineCtrl;
    private final ObjectMap<Class<?>, SteeringBehavior> cachedBehaviors;

    private FSMComponent fsmC;
	
	public GameEntity() {
        cachedBehaviors = new ObjectMap<>();
		/*this.gameData = new Array<GameData>();*/
	}
	
	public GameEntity addGameData(GameData data){
		//this.gameData.add(data);
		data.assignComponents(this);
		return this;
	}

    public GameEntity setPosition(float x, float y){
        return setPosition(x, y, 0);
    }

	public GameEntity setPosition(float x, float y, float angle){
		final PhysicsComponent phC = Mappers.physics.get(entity);
		if(phC != null){
			phC.body.setTransform(x, y, angle);
		}else {
			final PositionComponent psC = Mappers.position.get(entity);
			psC.x = x;
			psC.y = y;
		}
		return this;
	}

	public GameEntity addCollision(GameEntity target){
		CollisionComponent collisionC = Mappers.collision.get(entity);
		if(collisionC == null){
			collisionC = engineCtrl.createComponent(CollisionComponent.class);
            this.entity.add(collisionC);
		}
        collisionC.origin = this;
		collisionC.targets.add(target);
        //Logger.getInstance().log("Adding collision: " + this + ", target: " + target);
        return this;
	}

	public GameEntity removeCollision(GameEntity target){
		CollisionComponent collisionC = Mappers.collision.get(entity);
		if(collisionC == null){
			return this;
		}
		collisionC.targets.removeValue(target, true);
		if(collisionC.targets.size == 0){
			this.entity.remove(CollisionComponent.class);
		}
		return this;
	}

    public GameEntity handleCollision(){
        final GameEntityType thisType = Mappers.gameEntityType.get(this.entity).type;
        DamageComponent dmgC = Mappers.damage.get(this.entity);
        LifeComponent thisLifeC = Mappers.life.get(entity);
        CollisionComponent collisionC = Mappers.collision.get(this.entity);
        if(dmgC == null || thisLifeC == null || collisionC == null) return this;
        LifeDecrementComponent ldC = Mappers.lifeDecrement.get(entity);
        IntervalComponent thisInterval = Mappers.interval.get(entity);
        final Array<GameEntity> targets = collisionC.targets;
        boolean hit = false;
        for(int i = targets.size - 1; i > -1; i-- ){
			final GameEntity target = targets.get(i);
            final LifeComponent lifeC = Mappers.life.get(target.entity);
            final RewardComponent rewardC = Mappers.reward.get(target.entity);
            if(rewardC != null){
                rewardC.reward.applyReward(this);
                target.kill();
                continue;
            }else if(dmgC.damage == 0){
                continue;
            }

            if(lifeC == null) continue;
            final GameEntityType targetType = Mappers.gameEntityType.get(target.entity).type;
            if((thisType == GameEntityType.Ally && targetType == GameEntityType.AllyBullet)
					|| (thisType == GameEntityType.AllyBullet && targetType == GameEntityType.Ally)
                	|| (thisType == GameEntityType.Enemy && targetType == GameEntityType.EnemyBullet)
					|| (thisType == GameEntityType.EnemyBullet && targetType == GameEntityType.Enemy)){

			}else if((thisType == GameEntityType.EnemyBullet && targetType == GameEntityType.EnemyBullet)
                || (thisType == GameEntityType.AllyBullet && targetType == GameEntityType.AllyBullet)) {

			}else{
                boolean attack = true;
                if(thisInterval != null){
                    if(thisInterval.tickComplete) thisInterval.tickComplete = false;
                    else attack = false;
                }
                if(attack) {
                    lifeC.amount -= dmgC.damage * dmgC.multiplier;
                    //Logger.getInstance().log(thisType + " damage to " + targetType + ", " + targetType + " life: " + lifeC.amount);
                    if (lifeC.amount <= 0) {
                        targets.removeIndex(i);
                        PlayerComponent playerComponent = Mappers.player.get(target.entity);
                        if(playerComponent == null) {
                            target.kill();
                        }else {
                            FSMComponent fsmComponent = Mappers.fsm.get(target.entity);
                            fsmComponent.changeState(MainShipState.Damaged);
                            target.entity.remove(CollisionComponent.class);

                            SoundComponent soundC = Mappers.sound.get(target.entity);
                            if(soundC != null && soundC.killed != null)
                                SoundManager.getInstance().play(soundC.killed);
                        }
                    }
                    hit = true;
                    if(hit && lifeC.amount > 0){
                        SoundComponent soundC = Mappers.sound.get(target.entity);
                        if(soundC != null && soundC.hit != null)
                            SoundManager.getInstance().play(soundC.hit);
                    }
                }
            }
        }
        if(hit && ldC != null){
            thisLifeC.amount -= ldC.amount;
            if(thisLifeC.amount <= 0){
                kill();
            }
        }

        return this;
    }

    public GameEntity kill(){
        KillComponent killC = addComponent(KillComponent.class);
        killC.gameEntity = this;

        //Play killed sound
        SoundComponent soundC = Mappers.sound.get(this.entity);
        if(soundC != null && soundC.killed != null)
            SoundManager.getInstance().play(soundC.killed);

        //Change music, if exists
        ChangeMusicComponent changeMusicC = Mappers.changeMusic.get(entity);
        if(changeMusicC != null) {
            MusicManager.getInstance().setCurrentMusic(changeMusicC.music);
            MusicManager.getInstance().play();
        }

        PhysicsComponent physicsC = Mappers.physics.get(this.entity);

        ParticleTypesComponent particleTypesC = Mappers.particleType.get(entity);
        if(particleTypesC != null && particleTypesC.particleTypes.get(EntityEventType.Kill.ordinal()) != null){
            ParticleType particleType = particleTypesC.particleTypes.get(EntityEventType.Kill.ordinal());
            GameEntity gameEntity = engineCtrl.createParticle(particleType);
            Vector2 position = physicsC.body.getPosition();
            gameEntity.setPosition(position.x, position.y);
        }

        //Drop reward, if exists
        RewardTypeComponent rewardTypeC = Mappers.rewardType.get(this.entity);
        if(rewardTypeC != null) {
            GameEntity gameEntity = engineCtrl.createReward(rewardTypeC.rewardType);
            Vector2 position = physicsC.body.getPosition();
            gameEntity.setPosition(position.x, position.y);
        }
        return this;
    }

    public <T extends State> GameEntity changeState(T state){
        if(fsmC == null) fsmC = Mappers.fsm.get(this.entity);
        if(fsmC != null && fsmC.stateMachine.getCurrentState() != state){
            fsmC.stateMachine.changeState(state);
        }
        return this;
    }

    public State getState(){
        if(fsmC == null) fsmC = Mappers.fsm.get(this.entity);
        return fsmC == null? null : fsmC.stateMachine.getCurrentState();
    }


    public boolean isDead(){
        if(entity == null) return true;
        LifeComponent lifeC = Mappers.life.get(entity);
        int amount = lifeC == null? 0 : lifeC.amount;
        return amount <= 0 || Mappers.kill.has(entity) || Mappers.removed.has(entity);
    }

    public <T extends Component> T addComponent (Class<T> componentType) {
        T component = engineCtrl.createComponent(componentType);
        this.entity.add(component);
        return component;
    }

    public <T extends SteeringBehavior> SteeringBehavior getBehavior(Class<T> behaviorClass){
        return cachedBehaviors.get(behaviorClass);
    }

    public GameEntity cacheBehavior(SteeringBehavior behavior){
        if(behavior == null) return this;
        this.cachedBehaviors.put(behavior.getClass(), behavior);
        return this;
    }

	@Override
	public void reset() {
		for(Component c : entity.getComponents()){
            entity.remove(c.getClass());
		}
        this.fsmC = null;
        this.entity = null;
        // cachedBehaviors.clear(); // Not needed to clear behaviors, as it might be nice if the next gameEntity already has a certain behavior cached
		//gameData.clear();
	}
}
