package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.ugg.gdxjam.model.enums.GameMusic;

/**
 * Created by Jose Cuellar on 17/01/2016.
 */
public class ChangeMusicComponent implements Component, Pool.Poolable{
    public GameMusic music;

    @Override
    public void reset() {
        music = null;
    }
}
