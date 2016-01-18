package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.commands.Command;
import com.ugg.gdxjam.model.commands.TargetPlayerShipCommand;
import com.ugg.gdxjam.model.enums.InputPeripheralType;

/**
 * Created by Jose Cuellar on 09/01/2016.
 */
public class InputCommandComponent implements Component, Pool.Poolable {
    public Command command;

    @Override
    public void reset() {
        command = null;
    }
}
