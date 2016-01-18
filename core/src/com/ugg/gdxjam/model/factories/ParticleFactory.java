package com.ugg.gdxjam.model.factories;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import com.ugg.gdxjam.controller.EngineController;
import com.ugg.gdxjam.controller.PhysicsController;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.components.ParentComponent;
import com.ugg.gdxjam.model.components.ParticleComponent;
import com.ugg.gdxjam.model.components.RenderableComponent;
import com.ugg.gdxjam.model.enums.*;
import com.ugg.gdxjam.model.utils.LoaderUtils;
import com.ugg.gdxjam.view.RenderableParticle;

/**
 * Created by Jose Cuellar on 05/01/2016.
 */
public class ParticleFactory extends GameEntityFactory<ParticleType>{

    final LoaderUtils loader;

    public ParticleFactory(EngineController oEngineCtrl, PhysicsController oPhysicsCtrl, LoaderUtils loaderUtils) {
        super(oEngineCtrl, oPhysicsCtrl);
        this.loader = loaderUtils;
    }

    @Override
    public GameEntity create(ParticleType dataType, GameEntityType entityType, GameEntity parent) {
        GameEntity gameEntity = createGameEntity(entityType);
        gameEntity.addGameData(RenderDataType.get(RenderDataType.Particle));
        RenderableComponent renderableC = Mappers.renderable.get(gameEntity.entity);
        RenderableParticle renderableParticle = ((RenderableParticle)renderableC.renderable);
        renderableParticle.setEffect(loader.obtainEffect(dataType));
        ParticleComponent particleC = gameEntity.addComponent(ParticleComponent.class);
        particleC.particle = renderableParticle;
        particleC.gameEntity = gameEntity;
        if(parent != null){
            ParentComponent parentC = gameEntity.addComponent(ParentComponent.class);
            parentC.parentEntity = parent;
        }

        return gameEntity;
    }


}
