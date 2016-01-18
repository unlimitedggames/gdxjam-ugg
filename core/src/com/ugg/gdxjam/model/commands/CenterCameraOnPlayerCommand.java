package com.ugg.gdxjam.model.commands;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ugg.gdxjam.controller.CameraController;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.Player;
import com.ugg.gdxjam.model.components.PhysicsComponent;
import com.ugg.gdxjam.model.components.SizeComponent;
import com.ugg.gdxjam.model.configs.Configs;

/**
 * Created by Jose Cuellar on 13/01/2016.
 */
public class CenterCameraOnPlayerCommand extends Command {
    @Override
    public boolean execute() {
        final Array<Object> args = cma.getArguments();

        Player player = (Player) args.get(0);
        CameraController oCameraPlay = (CameraController) args.get(1);
        GameEntity gameEntity = player.mainEntity;
        PhysicsComponent physicsC = Mappers.physics.get(gameEntity.entity);
        final Vector2 position = physicsC.body.getPosition();
        oCameraPlay.setPosition(position.x,
                position.y);

        return true;
    }

    @Override
    public void undo() {

    }

    @Override
    public void reset() {
        cma.clear();
    }
}
