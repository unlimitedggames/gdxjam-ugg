package com.ugg.gdxjam.controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.ugg.gdxjam.model.Player;
import com.ugg.gdxjam.model.UpdatableGUI;
import com.ugg.gdxjam.model.commands.CenterCameraOnPlayerCommand;
import com.ugg.gdxjam.model.enums.GameMusic;
import com.ugg.gdxjam.model.utils.LoaderUtils;
import com.ugg.gdxjam.model.utils.MusicManager;
import com.ugg.gdxjam.view.gui.BarsTable;
import com.ugg.gdxjam.view.gui.FinishTable;
import com.ugg.gdxjam.view.gui.ShipButtonsTable;
import com.ugg.gdxjam.view.gui.StartTable;

/**
 * Created by Jose Cuellar on 13/01/2016.
 */
public class GUIController {

    final CameraController oCameraGUI;
    final CameraController oCameraPlay;
    final LoaderUtils loader;
    final Array<UpdatableGUI> updatables;
    final InputController inputCtrl;
    final Skin skin;
    final Stage guiStage;
    final StartTable startTable;

    public GUIController(CameraController oCameraGUI, CameraController oCameraPlay, InputController inputCtrl, Stage guiStage, LoaderUtils loaderUtils){
        this.oCameraGUI = oCameraGUI;
        this.oCameraPlay = oCameraPlay;
        this.loader = loaderUtils;
        this.inputCtrl = inputCtrl;
        this.guiStage = guiStage;
        this.updatables = new Array<>();
        Skin skin = loaderUtils.getSkin();
        this.skin = skin;
        ShipButtonsTable btnTable = new ShipButtonsTable(oCameraGUI, oCameraPlay, inputCtrl, skin);
        BarsTable barsTable = new BarsTable(oCameraGUI, inputCtrl, skin);
        updatables.add(btnTable);
        updatables.add(barsTable);
         startTable = new StartTable(this, oCameraGUI, inputCtrl, skin);


    }

    public GUIController updateLife(int amount, int max){
        BarsTable bars = (BarsTable)updatables.get(1);
        bars.updateLife(amount, max);
        return this;
    }

    public GUIController updateFuel(int amount, int max){
        BarsTable bars = (BarsTable)updatables.get(1);
        bars.updateFuel(amount, max);
        return this;
    }

    public void updateGUI(){
        for(UpdatableGUI updatable : this.updatables){
            updatable.updateGUI();
        }
    }

    public void addCenterButtonListener(Player player){
        ShipButtonsTable buttonTable = (ShipButtonsTable)updatables.get(0);
        inputCtrl.addClickCommand(buttonTable.getCenterButton(), CenterCameraOnPlayerCommand.class, player.id, oCameraPlay);
    }

    public void showStartGame(){
        guiStage.addActor(startTable);
    }

    public void startGame(){
        guiStage.addActor((ShipButtonsTable)updatables.get(0));
        guiStage.addActor((BarsTable)updatables.get(1));
        startTable.remove();
        inputCtrl.setInputForGUIAndPlay();

    }

    public void showEndGame(){
        inputCtrl.setInputForGUI();
        for(UpdatableGUI updatableGUI : this.updatables){
            Actor actor = (Actor)updatableGUI;
            actor.setVisible(false);
        }
        FinishTable finishTable = new FinishTable(oCameraGUI, inputCtrl, skin);
        finishTable.getColor().a = 0f;
        finishTable.addAction(Actions.fadeIn(1f));
        this.guiStage.addActor(finishTable);
        MusicManager.getInstance().loadAndPlay(GameMusic.Finish);
    }

}
