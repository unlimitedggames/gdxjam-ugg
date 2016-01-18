package com.ugg.gdxjam.model.data;

import com.badlogic.gdx.utils.IntMap;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.components.ParticleTypesComponent;
import com.ugg.gdxjam.model.enums.EntityEventType;
import com.ugg.gdxjam.model.enums.ParticleType;

/**
 * Created by Jose Cuellar on 17/01/2016.
 */
public class ParticleData implements GameData {

    IntMap<ParticleType> particleTypes = new IntMap<>();

    public ParticleData(ParticleType kill, ParticleType hit, ParticleType move){
        if(kill != null) particleTypes.put(EntityEventType.Kill.ordinal(), kill);
        if(hit != null) particleTypes.put(EntityEventType.Hit.ordinal(), hit);
        if(move != null) particleTypes.put(EntityEventType.Move.ordinal(), move);
    }

    @Override
    public void assignComponents(GameEntity gameEntity) {
        ParticleTypesComponent particleC = gameEntity.addComponent(ParticleTypesComponent.class);
        for(IntMap.Entry<ParticleType> entry : particleTypes){
            particleC.particleTypes.put(entry.key, entry.value);
        }

    }
}
