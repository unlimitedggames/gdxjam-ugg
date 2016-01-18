package com.ugg.gdxjam;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ugg.gdxjam.controller.*;
import com.ugg.gdxjam.model.utils.LoaderUtils;
import com.ugg.gdxjam.model.utils.MusicManager;
import com.ugg.gdxjam.model.utils.Scene2dListenersUtils;
import com.ugg.gdxjam.model.utils.SoundManager;
import com.ugg.gdxjam.view.MainScreen;
import com.ugg.gdxjam.view.gui.FinishTable;


public class GDXJam extends Game {
	
	//Rendering
	public static final float MAX_FPS = 60f;
	public static final float TIME_STEP = 1/MAX_FPS;

	//World boundaries
	public static final Rectangle worldBoundaries = new Rectangle(0, 0, 2000, 2000);

	//Para activar/desactivar debug
	public static boolean DEBUG = false;
	
	public static final int TARGET_WIDTH = 80;
	public static final int TARGET_HEIGHT = 60;
	public static final float TARGET_RATIO = TARGET_WIDTH/TARGET_HEIGHT;
	public static int OBJECT_ID;
	
	MainScreen mainScreen;
	
	public GDXJam(){
		
	}
	
	@Override
	public void create() {
		//Init statics
		OBJECT_ID = 0;
		LoaderUtils loaderUtils = new LoaderUtils();
		loaderUtils.init();

        //Init cameras and controllers
		ScreenViewport viewportGUI = new ScreenViewport(new OrthographicCamera());
		CameraController oCameraGUI = new CameraController(viewportGUI);
		oCameraGUI.getViewport().apply(true);
		ScalingViewport viewportPlay = new ScalingViewport(Scaling.fillY, TARGET_WIDTH, TARGET_HEIGHT);
		CameraController oCameraPlay = new CameraController(viewportPlay);
		oCameraPlay.setBoundaries(worldBoundaries);
		
		SpriteBatch batch = new SpriteBatch();
		Stage playStage = new Stage(viewportPlay, batch);
		Stage guiStage = new Stage(viewportGUI, batch);

		InputController oInputCtrl = new InputController(playStage, guiStage);
		PhysicsController physicsCtrl = new PhysicsController(oCameraPlay, TIME_STEP, 1, 1);
		EngineController oEngineCtrl = new EngineController(playStage, guiStage, physicsCtrl, oInputCtrl, oCameraPlay, loaderUtils);
		GUIController oGuiController = new GUIController(oCameraGUI, oCameraPlay, oInputCtrl, guiStage, loaderUtils);
		MapController oMapController = new MapController(oEngineCtrl, oCameraPlay);
		oMapController.loadMap("data/gameMap.tmx", batch, true);

        //Set camera listener, for camera control
		Scene2dListenersUtils.setupPlayStageCameraListener(playStage, oCameraPlay, oInputCtrl);

        //Set screen
        mainScreen = new MainScreen(oEngineCtrl, oInputCtrl, oCameraGUI, oCameraPlay, oGuiController);
		this.setScreen(mainScreen);
	}
	
}
