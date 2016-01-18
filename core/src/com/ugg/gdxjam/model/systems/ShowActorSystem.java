package com.ugg.gdxjam.model.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Pools;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.components.*;
import com.ugg.gdxjam.view.RenderableLifeBar;


public class ShowActorSystem extends IteratingSystem {
    private ComponentMapper<ShowActorComponent> sm;
    private ComponentMapper<RenderableComponent> rm;

    public ShowActorSystem() {
        super(Family.all(ShowActorComponent.class, RenderableComponent.class).get());
        sm = Mappers.showActor;
        rm = Mappers.renderable;
    }

    public void processEntity(Entity entity, float deltaTime) {
        ShowActorComponent show = sm.get(entity);
        RenderableComponent renderable = rm.get(entity);
        LifeBarComponent lifeBarC = Mappers.lifeBar.get(entity);
        show.stage.addActor(renderable.renderable);
        if(lifeBarC != null){
            show.stage.addActor(lifeBarC.lifeBar);
        }
        entity.remove(ShowActorComponent.class);
    }
}
