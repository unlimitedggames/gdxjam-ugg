package com.ugg.gdxjam.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ugg.gdxjam.model.Logger;

public class CameraController {

	private Viewport viewport;
	private float maxZoom = 3f;
	private float minZoom = 0.6f;
	private final Rectangle boundary;
	
	public CameraController(Viewport viewport){
		this.viewport = viewport;
		this.boundary = new Rectangle();
		viewport.apply(true);
	}

	public void updateViewport(int width, int height, boolean centerCamera, boolean updateLastPosition){
        Vector3 pos = this.viewport.getCamera().position;
		viewport.update(width, height, centerCamera);
        if(updateLastPosition)
            setPosition(pos.x, pos.y);
	}

	public Viewport getViewport(){ return this.viewport; }

    public void setBoundaries(Rectangle boundaries){
        this.boundary.set(boundaries);
    }

	public void setBoundaries(float startX, float startY, float width, float height){
		boundary.set(startX, startY, width, height);
	}

    public void zoomCamera(float amount){
        final OrthographicCamera camera = (OrthographicCamera)viewport.getCamera();
		camera.zoom = MathUtils.clamp(camera.zoom + amount, minZoom, maxZoom);
		camera.update();
    }

	public float getCameraZoom(){
		return ((OrthographicCamera)viewport.getCamera()).zoom;
	}

	public void updateCamera(){
		viewport.getCamera().update();
	}

	public void translate(float x, float y){
		final Camera camera = viewport.getCamera();
		camera.translate(x, y, 0);
        keepInBoundaries();
		updateCamera();
	}

    public void setPosition(float x, float y){
        final Camera camera = viewport.getCamera();
        camera.position.x = x;
        camera.position.y = y;
        //Logger.getInstance().log("Camera pos x: " + x + ", y: " + y);
        keepInBoundaries();
    }

    private void keepInBoundaries(){
        final Camera camera = viewport.getCamera();
        final Vector3 position = camera.position;
        if(position.x < boundary.x){
            position.x = boundary.x;
        }
        if(position.x > boundary.x + boundary.width){
            position.x = boundary.x + boundary.width;
        }
        if(position.y < boundary.y){
            position.y = boundary.y;
        }
        if(position.y > boundary.y + boundary.height) {
            position.y = boundary.y + boundary.height;
        }
    }


}
