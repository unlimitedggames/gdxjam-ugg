package com.ugg.gdxjam.model.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.ugg.gdxjam.controller.CameraController;
import com.ugg.gdxjam.controller.PhysicsController;
import com.ugg.gdxjam.model.Logger;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.components.*;
import com.ugg.gdxjam.model.utils.LoaderUtils;
import com.ugg.gdxjam.model.utils.TextDebugUtils;
import sun.rmi.runtime.Log;


public class DebugSystem extends EntitySystem  {

	private Family family;
	private ImmutableArray<Entity> entities;
	final PhysicsController physicsCtrl;
	final ShapeRenderer shapeRenderer;
    final CameraController cameraCtrl;

	final Stage guiStage;
	final Stage playStage;
	final LoaderUtils loader;
	BitmapFont font;

    private boolean drawB2dDebug = false;

    public DebugSystem(Stage guiStage, Stage playStage, LoaderUtils loader, PhysicsController physicsCtrl, CameraController cameraPlayCtrl) {
		this.family = Family.one(SensorComponent.class).get();
		this.physicsCtrl = physicsCtrl;
        this.cameraCtrl = cameraPlayCtrl;
		this.shapeRenderer = new ShapeRenderer();
        this.shapeRenderer.setAutoShapeType(true);
		this.guiStage = guiStage;
		this.playStage = playStage;
		this.loader = loader;
        font = this.loader.getSkin().get("default-font", BitmapFont.class);
    }

	@Override
	public void addedToEngine (Engine engine) {
		entities = engine.getEntitiesFor(family);
	}

	@Override
	public void removedFromEngine (Engine engine) {
		entities = null;
	}

    @Override
	public void update(float deltaTime) {
		super.update(deltaTime);
        if(drawB2dDebug) {
            physicsCtrl.debug();
            this.shapeRenderer.setProjectionMatrix(cameraCtrl.getViewport().getCamera().combined);
            this.shapeRenderer.begin();
            for (int i = entities.size() - 1; i > -1; i--) {
                processEntity(entities.get(i), deltaTime);
            }
            this.shapeRenderer.end();
        }
		TextDebugUtils.drawFPS((SpriteBatch)guiStage.getBatch(), (SpriteBatch)playStage.getBatch(), font, Gdx.graphics.getFramesPerSecond());
	}

	public void processEntity(Entity entity, float deltaTime) {
		SensorComponent sensor = Mappers.sensor.get(entity);
		if(sensor != null && sensor.enabled)
			sensor.sensor.debug(shapeRenderer);
	}
    
}
