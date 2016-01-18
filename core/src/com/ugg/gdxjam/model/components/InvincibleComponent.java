package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.ugg.gdxjam.model.TimeCounter;

/**
 * Created by Jose Cuellar on 06/01/2016.
 */
public class InvincibleComponent implements Component, Pool.Poolable {
    public TimeCounter time = new TimeCounter();


    @Override
    public void reset() {
        time.reset();
    }
}
