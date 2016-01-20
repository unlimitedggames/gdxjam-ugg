package com.ugg.gdxjam.view.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.ugg.gdxjam.controller.CameraController;
import com.ugg.gdxjam.controller.GUIController;
import com.ugg.gdxjam.controller.InputController;
import com.ugg.gdxjam.model.UpdatableGUI;

/**
 * Created by Jose Cuellar on 13/01/2016.
 */
public class StartTable extends Table implements UpdatableGUI {

    final CameraController oCameraController;
    final InputController inputCtrl;

    public StartTable(final GUIController guiCtrl, CameraController oCameraController, InputController inputCtrl, Skin skin){
        this.oCameraController = oCameraController;
        this.inputCtrl = inputCtrl;
        this.setSkin(skin);
        this.setFillParent(true);
        this.setBackground("dimBG");
        final float pad = oCameraController.getViewport().getWorldHeight()*0.1f;
        Image img = new Image(skin, "logo");
        img.setScaling(Scaling.fit);
        this.add(img).height(oCameraController.getViewport().getWorldHeight()*0.4f);
        this.row();
        Label lblTitle = new Label("Controls:", skin, "bold");
        lblTitle.setColor(Color.YELLOW);
        lblTitle.setAlignment(Align.center);
        this.add(lblTitle).expandX().pad(pad);
        this.row();
        Label lblDesc = new Label("Touch the screen to guide the ship, touch a target to attack. \n" +
                "If life runs out, don't panic! It will come back alive, with fewer life. \n" +
                "Fuel is consumed while you move, it regenerates when you stop.\n" +
                "Your mission: collect the 4 power-ups from the almighty Motherships, but don't forget to explore, it will make your journey easier!", skin, "default");
        lblDesc.setAlignment(Align.topLeft, Align.topLeft);
        lblDesc.setWrap(true);
        this.add(lblDesc).expand().fill().padLeft(pad).padRight(pad).padBottom(pad);
        this.row();
        Label lblTouch = new Label("Touch here to start.", skin, "bold");
        lblTouch.setAlignment(Align.center);
        this.add(lblTouch).expandX().fillX();
        ClickListener lblClickListener = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                guiCtrl.startGame();
            }
        };
        lblTouch.addListener(lblClickListener);
        lblDesc.addListener(lblClickListener);


    }

    @Override
    public void updateGUI() {
        final float width = oCameraController.getViewport().getWorldWidth();
        final int padding = (int)(this.getWidth()*0.2f);
        this.setPosition(width - this.getWidth() - padding, padding );
    }
}
