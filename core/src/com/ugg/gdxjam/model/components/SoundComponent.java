package com.ugg.gdxjam.model.components;


import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.ugg.gdxjam.model.enums.GameSound;

/**
 * Created by Jose Cuellar on 16/01/2016.
 */
public class SoundComponent implements Component, Pool.Poolable {

    public GameSound hit;
    public GameSound shoot;
    public GameSound killed;
    public GameSound spawn;
    public GameSound move;

    @Override
    public void reset() {
        hit = null;
        shoot = null;
        killed = null;
        spawn = null;
        move = null;
    }
}
