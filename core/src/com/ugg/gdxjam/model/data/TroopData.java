package com.ugg.gdxjam.model.data;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pools;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.components.FSMComponent;
import com.ugg.gdxjam.model.components.LifeBarComponent;
import com.ugg.gdxjam.model.components.LifeComponent;
import com.ugg.gdxjam.model.components.RewardTypeComponent;
import com.ugg.gdxjam.model.configs.BaseConfig;
import com.ugg.gdxjam.model.enums.*;
import com.ugg.gdxjam.view.Renderable;
import com.ugg.gdxjam.view.RenderableLifeBar;

/**
 * Created by Jose Cuellar on 05/01/2016.
 */
public class TroopData implements GameData{
    public RenderDataType renderableType;
    public BodyDataType bodyDataType;
    public SoundDataType soundDataType;
    public Array<WeaponDataType> weaponsDataType = new Array<>();
    public AIType aiType;
    public int life = 1;
    public BaseConfig config;
    public SensorType sensorType;
    public RewardDataType rewardDataType;
    public ParticleDataType particleDataType;

    public TroopData(int life, RenderDataType renderableType, BodyDataType bodyDataType, AIType aiType, BaseConfig config, SensorType sensorType, SoundDataType soundDataType, RewardDataType rewardDataType, ParticleDataType particleDataType, WeaponDataType... weaponsDataType) {
        this.life = life;
        this.renderableType = renderableType;
        this.bodyDataType = bodyDataType;
        this.aiType = aiType;
        if(weaponsDataType != null)
            this.weaponsDataType.addAll(weaponsDataType);
        this.config = config;
        this.sensorType = sensorType;
        this.soundDataType = soundDataType;
        this.rewardDataType = rewardDataType;
        this.particleDataType = particleDataType;
    }

    @Override
    public void assignComponents(GameEntity gameEntity) {
        LifeComponent lifeC = gameEntity.addComponent(LifeComponent.class);
        lifeC.amount = life;
        lifeC.max = life;
        RenderData renderData = RenderDataType.get(this.renderableType);
        LifeBarComponent lifeBarC = gameEntity.addComponent(LifeBarComponent.class);
        RenderData lifeBarData = RenderDataType.get(RenderDataType.LifeBar);
        lifeBarC.lifeBar = (RenderableLifeBar)lifeBarData.renderable.cloneObject();
        lifeBarC.lifeBar.setSize(lifeBarData.width, lifeBarData.height);
        lifeBarC.lifeBar.offsetX = lifeBarData.offsetX;
        lifeBarC.lifeBar.offsetY = Math.abs(renderData.offsetY) + lifeBarData.offsetY;

        if(soundDataType != null)
            gameEntity.addGameData(soundDataType.get());

        if(weaponsDataType != null) {
            for (WeaponDataType weaponType : this.weaponsDataType) {
                gameEntity.addGameData(weaponType.get());
            }
        }

        if(particleDataType != null){
            gameEntity.addGameData(particleDataType.get());
        }

        if(aiType == AIType.Fsm || aiType == AIType.FsmAndBTree){
            FSMComponent fsmC = gameEntity.addComponent(FSMComponent.class);
            fsmC.stateMachine.setOwner(gameEntity);
        }

        if(rewardDataType != null){
            RewardTypeComponent rewardTypeC = gameEntity.addComponent(RewardTypeComponent.class);
            rewardTypeC.rewardType = rewardDataType;
        }

    }
}
