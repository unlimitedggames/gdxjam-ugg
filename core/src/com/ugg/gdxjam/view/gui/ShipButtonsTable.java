package com.ugg.gdxjam.view.gui;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.ugg.gdxjam.controller.CameraController;
import com.ugg.gdxjam.controller.InputController;
import com.ugg.gdxjam.model.Player;
import com.ugg.gdxjam.model.UpdatableGUI;
import com.ugg.gdxjam.model.commands.CenterCameraOnPlayerCommand;

import java.awt.*;

/**
 * Created by Jose Cuellar on 13/01/2016.
 */
public class ShipButtonsTable extends Table implements UpdatableGUI {

    final CameraController oCameraGUI;
    final InputController inputCtrl;
    final ImageButton centerButton;

    public ShipButtonsTable(CameraController oCameraGUI, CameraController oCameraPlay, InputController inputCtrl, Skin skin){
        this.oCameraGUI = oCameraGUI;
        this.inputCtrl = inputCtrl;
        centerButton = new ImageButton(skin, "default");
        this.add(centerButton);
        this.pack();
    }

    public ImageButton getCenterButton(){
        return this.centerButton;
    }

    @Override
    public void updateGUI() {
        final float width = oCameraGUI.getViewport().getWorldWidth();
        final int padding = (int)(this.getWidth()*0.2f);
        this.setPosition(width - this.getWidth() - padding, padding );
    }
}
