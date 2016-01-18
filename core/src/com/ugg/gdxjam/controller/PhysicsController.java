package com.ugg.gdxjam.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.PhysicsContactListener;
import com.ugg.gdxjam.model.components.PhysicsComponent;
import com.ugg.gdxjam.model.enums.BodyDataType;
import com.ugg.gdxjam.model.enums.GameEntityType;
import com.ugg.gdxjam.model.listeners.EntityRayCastCallback;
import com.ugg.gdxjam.model.listeners.QueryAABBCallback;
import com.ugg.gdxjam.model.utils.Box2dUtils;

/**
 * Created by Jose Cuellar on 03/01/2016.
 */
public class PhysicsController implements Disposable {

    public final float TIME_STEP;
    public final int VELOCITY_ITERATIONS;
    public final int POSITION_ITERATIONS;

    private World world;
    private Box2DDebugRenderer renderer;
    private CameraController cameraCtrl;

    private final QueryAABBCallback queryAABBCallback;
    private final EntityRayCastCallback rayCastCallback;
    private final Vector2 rayCastVector1 = new Vector2();
    private final Vector2 rayCastVector2 = new Vector2();

    public PhysicsController(CameraController cameraCtrl, float timeStep, int velIterations, int posIterations) {
        this.cameraCtrl = cameraCtrl;
        queryAABBCallback = new QueryAABBCallback();
        rayCastCallback = new EntityRayCastCallback();
        TIME_STEP = timeStep;
        VELOCITY_ITERATIONS = velIterations;
        POSITION_ITERATIONS = posIterations;
        world = new World(new Vector2(0, 0), true);
        renderer = new Box2DDebugRenderer();
        world.setContactListener(new PhysicsContactListener());
        BodyDataType.init(this);
        Box2dUtils.init(this);

    }

    public void step() {
        world.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
    }

    public Body createBody(BodyDef def) {
        return world.createBody(def);
    }

    public void assignFilter(GameEntity gameEntity, short category, int group, int mask){
        final PhysicsComponent physicsC = Mappers.physics.get(gameEntity.entity);
        if(physicsC != null){
            final Body body = physicsC.body;
            for(Fixture f : body.getFixtureList()){
                final Filter filter = f.getFilterData();
                filter.categoryBits = category;
                filter.groupIndex = (short)group;
                filter.maskBits = (short)mask;
                f.setFilterData(filter);
            }
        }
    }

    public void destroyBody(Body body) {
        world.destroyBody(body);
    }

    public World getWorld() {
        return world;
    }

    public void debug() {
        if (renderer == null) {
            renderer = new Box2DDebugRenderer();
        }
        renderer.render(world, cameraCtrl.getViewport().getCamera().combined);
    }

    @Override
    public void dispose() {
        world.dispose();
        renderer.dispose();
    }

    public Array<Body> queryAABB(float xi, float yi, float xf, float yf){
        queryAABBCallback.found.clear();
        if(world != null){
            world.QueryAABB(queryAABBCallback, xi, yi, xf, yf);
        }
        return queryAABBCallback.found;
    }

    public Array<GameEntity> raycastEntities(Array<GameEntityType> typesToFind, float xi, float yi, float xf, float yf){
        rayCastCallback.found.clear();
        rayCastCallback.types.clear();
        rayCastCallback.types.addAll(typesToFind);
        rayCastVector1.set(xi, yi);
        rayCastVector2.set(xf, yf);
        world.rayCast(rayCastCallback, rayCastVector1, rayCastVector2);
        return rayCastCallback.found;

    }

}
