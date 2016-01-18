package com.ugg.gdxjam.model.data;

import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.components.FSMComponent;
import com.ugg.gdxjam.model.components.LifeBarComponent;
import com.ugg.gdxjam.model.components.LifeComponent;
import com.ugg.gdxjam.model.components.RewardTypeComponent;
import com.ugg.gdxjam.model.configs.BaseConfig;
import com.ugg.gdxjam.model.enums.*;
import com.ugg.gdxjam.view.RenderableLifeBar;

/**
 * Created by Jose Cuellar on 11/01/2016.
 */
public class ScenarioData implements GameData {

    public RenderDataType renderableType;
    public BodyDataType bodyDataType;
    public int life = -1;
    public SoundDataType soundDataType;
    public BaseConfig config;
    public RewardDataType rewardDataType;
    public ParticleDataType particleDataType;
    public ScenarioData(RenderDataType renderableType) {
        this(-1, renderableType, null, null, null, null);
    }

    public ScenarioData(int life, RenderDataType renderableType, BodyDataType bodyDataType, SoundDataType soundType, RewardDataType rewardDataType, ParticleDataType particleDataType) {
        this(life, renderableType, bodyDataType, soundType, null, rewardDataType, particleDataType);
    }

    public ScenarioData(int life, RenderDataType renderableType, BodyDataType bodyDataType, SoundDataType soundType, BaseConfig config, RewardDataType rewardDataType, ParticleDataType particleDataType) {
        this.life = life;
        this.renderableType = renderableType;
        this.bodyDataType = bodyDataType;
        this.soundDataType = soundType;
        this.config = config;
        this.rewardDataType = rewardDataType;
        this.particleDataType = particleDataType;
    }

    @Override
    public void assignComponents(GameEntity gameEntity) {
        if(life > 0) {
            LifeComponent lifeC = gameEntity.addComponent(LifeComponent.class);
            lifeC.amount = life;
            lifeC.max = life;
            RenderData renderData =  RenderDataType.get(this.renderableType);
            LifeBarComponent lifeBarC = gameEntity.addComponent(LifeBarComponent.class);
            RenderData lifeBarData = RenderDataType.get(RenderDataType.LifeBar);
            lifeBarC.lifeBar = (RenderableLifeBar)lifeBarData.renderable.cloneObject();
            lifeBarC.lifeBar.setSize(lifeBarData.width, lifeBarData.height);
            lifeBarC.lifeBar.offsetX = lifeBarData.offsetX;
            lifeBarC.lifeBar.offsetY = Math.abs(renderData.offsetY) + lifeBarData.offsetY;
        }

        gameEntity.addGameData(RenderDataType.get(renderableType));
        if(this.bodyDataType != null) {
            gameEntity.addGameData(BodyDataType.get(this.bodyDataType));
        }

        if(this.soundDataType != null){
            gameEntity.addGameData(soundDataType.get());
        }

        if(config != null) {
            config.applyConfigs(gameEntity);
        }

        if(particleDataType != null){
            gameEntity.addGameData(particleDataType.get());
        }

        if(rewardDataType != null){
            RewardTypeComponent rewardTypeC = gameEntity.addComponent(RewardTypeComponent.class);
            rewardTypeC.rewardType = rewardDataType;
        }

    }
}
