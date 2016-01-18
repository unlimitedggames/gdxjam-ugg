package com.ugg.gdxjam.model.data;

import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.components.LifeBarComponent;
import com.ugg.gdxjam.model.components.LifeComponent;
import com.ugg.gdxjam.model.components.RewardComponent;
import com.ugg.gdxjam.model.enums.BodyDataType;
import com.ugg.gdxjam.model.enums.RenderDataType;
import com.ugg.gdxjam.model.enums.SoundDataType;
import com.ugg.gdxjam.model.rewards.Reward;
import com.ugg.gdxjam.view.RenderableLifeBar;

/**
 * Created by Jose Cuellar on 11/01/2016.
 */
public class RewardData implements GameData {

    public RenderDataType renderableType;
    public BodyDataType bodyDataType;
    public Reward reward;
    public SoundDataType soundDataType;

    public RewardData(RenderDataType renderableType, BodyDataType bodyDataType, Reward reward, SoundDataType soundDataType) {
        this.renderableType = renderableType;
        this.bodyDataType = bodyDataType;
        this.reward = reward;
        this.soundDataType = soundDataType;
    }

    @Override
    public void assignComponents(GameEntity gameEntity) {

        gameEntity.addGameData(RenderDataType.get(renderableType));
        if(this.bodyDataType != null)
            gameEntity.addGameData(BodyDataType.get(this.bodyDataType));

        RewardComponent rewardC = gameEntity.addComponent(RewardComponent.class);
        rewardC.reward = reward.cloneObject();

        if(soundDataType != null)
            gameEntity.addGameData(soundDataType.get());

    }
}
