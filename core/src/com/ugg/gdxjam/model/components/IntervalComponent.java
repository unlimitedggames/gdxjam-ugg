package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.ugg.gdxjam.model.TimeCounter;

/**
 * Created by Jose Cuellar on 06/01/2016.
 */
public class IntervalComponent implements Component, Pool.Poolable {
    public TimeCounter interval = new TimeCounter();
    public boolean tickComplete = false;


    @Override
    public void reset() {
        interval.reset();
        tickComplete = false;
    }
}
