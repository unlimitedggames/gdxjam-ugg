package com.ugg.gdxjam.model.utils;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.ai.steer.behaviors.Face;
import com.badlogic.gdx.ai.steer.behaviors.Seek;
import com.badlogic.gdx.math.Vector2;
import com.ugg.gdxjam.model.SteerLocation;

/**
 * Created by Jose Cuellar on 10/01/2016.
 */
public final class BehaviorUtils {

    public static SteerLocation getTarget(SteeringBehavior behavior){
        SteerLocation targetL = null;
        if(behavior instanceof Face) targetL = (SteerLocation)((Face) behavior).getTarget();
        else if(behavior instanceof Arrive) targetL = (SteerLocation)((Arrive) behavior).getTarget();
        else if(behavior instanceof Seek) targetL = (SteerLocation)((Seek) behavior).getTarget();
        return targetL;
    }

    public static Arrive createArrive(float x, float y, Steerable steerable){
        SteerLocation loc = new SteerLocation();
        loc.getPosition().set(x, y);
        Arrive arrive = new Arrive(steerable, loc);
            arrive.setTarget(loc);

        arrive.setOwner(steerable);
        return arrive;
    }
}
