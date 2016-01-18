package com.ugg.gdxjam.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.ugg.gdxjam.controller.CameraController;
import com.ugg.gdxjam.controller.EngineController;
import com.ugg.gdxjam.controller.GUIController;
import com.ugg.gdxjam.controller.InputController;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Logger;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.Player;
import com.ugg.gdxjam.model.commands.TargetPlayerShipCommand;
import com.ugg.gdxjam.model.components.LockOnComponent;
import com.ugg.gdxjam.model.configs.Configs;
import com.ugg.gdxjam.model.enums.*;
import com.ugg.gdxjam.model.utils.MusicManager;

public class MainScreen implements Screen {

	/**************************************/
	/************ CONTROLLERS *************/
	/**************************************/

	final EngineController oEngineCtrl;
	final CameraController oCameraGUI;
	final CameraController oCameraPlay;
	final InputController oInputCtrl;
	final GUIController oGuiController;

	/**************************************/
	/********* OVERLAP2D SCENE ************/
	/**************************************/

	//private SceneLoader sl;


    public MainScreen(EngineController oEngineCtrl, InputController oInputCtrl, CameraController oCameraGUI,
			CameraController oCameraPlay, GUIController guiController){
		this.oEngineCtrl = oEngineCtrl;
		this.oInputCtrl = oInputCtrl;
		this.oCameraGUI = oCameraGUI;
		this.oCameraPlay = oCameraPlay;
		this.oGuiController = guiController;

		this.oCameraPlay.translate(Configs.mainShip.shipInitialPosition.x -
                this.oCameraPlay.getViewport().getWorldWidth()*0.5f,
                Configs.mainShip.shipInitialPosition.y - this.oCameraPlay.getViewport().getWorldHeight()*0.5f);

        Player player1 = new Player(1, oEngineCtrl.createAlly(TroopDataType.MainShip), guiController);
        oInputCtrl.addPlayer(player1)
                .mapKeyToCommand(1, InputPeripheralType.Mouse, Input.Buttons.LEFT, TargetPlayerShipCommand.class);
        guiController.addCenterButtonListener(player1);


		/**************************************************************/

        oInputCtrl.setInputForGUI();
        guiController.showStartGame();

		/**************************************************************/

		MusicManager.getInstance().loadAndPlay(GameMusic.Main);

		/*GameEntity gameEntity = oEngineCtrl.createParticle(ParticleType.MainBooster);
		gameEntity.setPosition(2500f, 2500f);*/
	}
	


	/**************************************************************/
	/********************* METODOS DE SCREEN *********************/
	/**************************************************************/
	
	@Override
	public void show() {
		
		
	}
	
	@Override
	public void render(float delta) {

		oEngineCtrl.update(delta);

	}
	
	
	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
        Logger.getInstance().log("Pause");
		oEngineCtrl.toggleSystems(false);
        MusicManager.getInstance().pause();
	}

	@Override
	public void resume() {
        Logger.getInstance().log("Resume");
		oEngineCtrl.toggleSystems(true);
        MusicManager.getInstance().resume();
	}

	@Override
	public void resize(int width, int height) {
        Logger.getInstance().log("Resize");
        oCameraGUI.updateViewport(width, height, true, false);
        oCameraPlay.updateViewport(width, height, false, false);
        oGuiController.updateGUI();
	}


	@Override
	public void dispose() {
		oEngineCtrl.dispose();
	}

}
