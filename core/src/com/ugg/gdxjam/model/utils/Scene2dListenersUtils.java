package com.ugg.gdxjam.model.utils;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.ugg.gdxjam.controller.CameraController;
import com.ugg.gdxjam.controller.EngineController;
import com.ugg.gdxjam.controller.InputController;
import com.ugg.gdxjam.model.Logger;

/**
 * Created by Jose Cuellar on 01/01/2016.
 */
public class Scene2dListenersUtils {

    public static void setupPlayStageCameraListener(final Stage stage, final CameraController cameraCtrl, final InputController inputCtrl){
        stage.addListener(new ActorGestureListener(2.75f, 0.4f, 1.1f, 0.15f) {

            float lastDistance = 0, originalDistance = 0;


            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                super.tap(event, x, y, count, button);
                inputCtrl.processTap(event, x, y, count, button);
            }

            public boolean longPress(Actor actor, float x, float y) {
                //Logger.getInstance().log("long press " + x + ", " + y);
                return true;
            }

            public void fling(InputEvent event, float velocityX, float velocityY, int pointer, int button) {
                //Logger.getInstance().log("fling " + velocityX + ", " + velocityY);
            }

            public void zoom(InputEvent event, float initialDistance, float distance) {
                boolean negative = false;
                float amount = 0, threshold = 0;

                if (initialDistance != originalDistance) {
                    threshold = Math.abs(initialDistance - distance);
                    negative = initialDistance > distance;
                    lastDistance = distance;
                    originalDistance = initialDistance;
                } else {
                    threshold = Math.abs(lastDistance - distance);
                    negative = lastDistance > distance;
                    lastDistance = distance;
                }
                //Logger.getInstance().log("Threshold: " + threshold);
                amount = negative ? threshold*0.0285f:-threshold*0.0285f;
                cameraCtrl.zoomCamera(amount);
                //Logger.getInstance().log("zoom " + initialDistance + ", " + distance + ", amount: " + amount);
            }

            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
                //Logger.getInstance().log("Pan x: " + x + ", y: " + y + ", deltaX: " + deltaX + ", deltaY: " + deltaY);
                cameraCtrl.translate(deltaX*0.70f, deltaY * 0.76f);
            }
        });

        stage.addListener(new InputListener(){

            @Override
            public boolean scrolled(InputEvent event, float x, float y,
                                    int amount) {
                float zoom = 0;
                //Amount to zoom
                zoom = amount > 0 ? 0.1f : amount < 0 ? -0.1f : 0;
                cameraCtrl.zoomCamera(zoom);
                return true;
            }
        });
    }
}
