package com.ugg.gdxjam.model.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.ugg.gdxjam.controller.PhysicsController;

/**
 * Created by Jose Cuellar on 11/01/2016.
 */
public class Box2dUtils {

    static final float aabbOffset = 2f;
    static PhysicsController physicsCtrl;

    public static void init(PhysicsController oPhysicsCtrl){
        physicsCtrl = oPhysicsCtrl;
    }

    /****************************************************/
    /***************** PARTE QUERYAABB ******************/
    /****************************************************/

    public static Array<Body> queryAABB(float xi, float yi, float xf, float yf){
        return physicsCtrl.queryAABB(xi, yi, xf, yf);
    }

    public static Array<Body> queryAABBInput(float x, float y){
        return queryAABB(x - aabbOffset, y - aabbOffset, x + aabbOffset, y + aabbOffset);
    }
}
