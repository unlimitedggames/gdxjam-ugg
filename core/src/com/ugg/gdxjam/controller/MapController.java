package com.ugg.gdxjam.controller;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.AtlasTmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.components.OriginComponent;
import com.ugg.gdxjam.model.components.PositionComponent;
import com.ugg.gdxjam.model.components.SizeComponent;
import com.ugg.gdxjam.model.enums.GameEntityType;
import com.ugg.gdxjam.model.enums.ScenarioDataType;
import com.ugg.gdxjam.model.enums.TroopDataType;

/**
 * Created by Jose Cuellar on 13/01/2016.
 */
public class MapController {

    private TmxMapLoader maploader;
    private AtlasTmxMapLoader atlasTmxMapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer; // won't use it this time

    private EngineController oEngineCtrl;
    private CameraController oCameraPlay;

    public MapController(EngineController engineCtrl, CameraController cameraPlay){
        this.oEngineCtrl = engineCtrl;
        this.maploader = new TmxMapLoader();
        this.atlasTmxMapLoader = new AtlasTmxMapLoader();
    }

    public MapController loadMap(String tmxFile, Batch batch, boolean atlas) {
        if(atlas)
            this.map = atlasTmxMapLoader.load(tmxFile);
        else
            this.map = maploader.load(tmxFile);
        renderer = new OrthogonalTiledMapRenderer(map, batch);
        loadObjects();
        return this;
    }

    protected  MapController loadObjects(){
        for(MapLayer layer : map.getLayers()){
            final String name = layer.getName();
            final GameEntityType currenType = getTypeByLayer(name);
            for(MapObject object : layer.getObjects()) {
                GameEntity gameEntity = generateEntityByObject(object, currenType);
            }
        }
        return this;
    }

    protected  GameEntity generateEntityByObject(MapObject mapObject, GameEntityType type){
        GameEntity gameEntity = null;
        switch (type){
            case Enemy:
                gameEntity = oEngineCtrl.createEnemy(TroopDataType.values()[Integer.parseInt((String) mapObject.getProperties().get("type"))]);
                break;
            case Scenario:
                gameEntity = oEngineCtrl.createScenario(ScenarioDataType.values()[Integer.parseInt((String) mapObject.getProperties().get("type"))]);
                break;
        }
        final Object rotationObj = mapObject.getProperties().get("rotation");
        final float rotation = rotationObj == null? 0 : (float)rotationObj;
        final Object mantenerTam = mapObject.getProperties().get("mantenerTam");
        final float height = (float)mapObject.getProperties().get("height");
        OriginComponent origin = Mappers.origin.get(gameEntity.entity);
        SizeComponent size = Mappers.size.get(gameEntity.entity);
        if(mantenerTam != null){
            size.width = (float)mapObject.getProperties().get("width");
            size.height = height;
            origin.x = size.width * 0.5f;
            origin.y = size.height * 0.5f;
            PositionComponent position = Mappers.position.get(gameEntity.entity);
            position.offsetX = -origin.x;
            position.offsetY = -origin.y;
        }
        gameEntity.setPosition((float)mapObject.getProperties().get("x"),
                    (float)mapObject.getProperties().get("y") + height,
                    rotation);
        return gameEntity;
    }

    protected GameEntityType getTypeByLayer(String name){
        switch(name){
            case "enemy":
                return GameEntityType.Enemy;
            case "scenario":
                return GameEntityType.Scenario;
        }
        return null;
    }

    public void render(){
        this.renderer.setView((OrthographicCamera) oCameraPlay.getViewport().getCamera());
        this.render();
    }

}
