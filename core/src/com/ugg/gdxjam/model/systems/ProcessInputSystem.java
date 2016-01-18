package com.ugg.gdxjam.model.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.Body;
import com.ugg.gdxjam.controller.InputController;
import com.ugg.gdxjam.model.Logger;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.components.*;
import com.ugg.gdxjam.model.enums.VelocityType;


public class ProcessInputSystem extends IteratingSystem {

    final InputController inputCtrl;

    public ProcessInputSystem(InputController inputCtrl) {
        super(Family.all(InputCommandComponent.class).get());
        this.inputCtrl = inputCtrl;
    }

    public void processEntity(Entity entity, float deltaTime) {
        InputCommandComponent inputCommandC = Mappers.inputCommand.get(entity);
        //Logger.getInstance().log("Process command: " + inputCommandC.getClass().getSimpleName());
        inputCtrl.executeCommand(inputCommandC.command);
        entity.remove(InputCommandComponent.class);
    }
}
