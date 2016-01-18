package com.ugg.gdxjam.view.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.ugg.gdxjam.controller.CameraController;
import com.ugg.gdxjam.controller.InputController;
import com.ugg.gdxjam.model.UpdatableGUI;

/**
 * Created by Jose Cuellar on 13/01/2016.
 */
public class FinishTable extends Table implements UpdatableGUI {

    final CameraController oCameraController;
    final InputController inputCtrl;

    public FinishTable(CameraController oCameraController, InputController inputCtrl, Skin skin){
        this.oCameraController = oCameraController;
        this.inputCtrl = inputCtrl;
        this.setSkin(skin);
        this.setFillParent(true);
        this.setBackground("dimBG");
        final float pad = oCameraController.getViewport().getWorldHeight()*0.1f;
        Label lblTitle = new Label("Thanks for playing!", skin, "bold");
        lblTitle.setColor(Color.YELLOW);
        lblTitle.setAlignment(Align.center);
        this.add(lblTitle).expandX().pad(pad);
        this.row();
        Label lblDesc = new Label("No time for a proper ending, but we hope you enjoyed it! \n" +
                "Thanks to those who helped with the project. " +
                "Music is from Incompetech.com (great royalty free music), sounds created in Bfxr." +
                "Assets are open art from different sources (OpenGameArt mainly) and some created by us.\n" +
                "Finally, our big team conformed by:\n" +
                "Jose Mauricio Cuellar \n" +
                "Max Alexander Baires\n" +
                "We are UnlimitedGGames :D Thank you! You can now close the game :)", skin, "default");
        lblDesc.setAlignment(Align.topLeft, Align.topLeft);
        lblDesc.setWrap(true);
        this.add(lblDesc).expand().fill().padLeft(pad).padRight(pad).padBottom(pad);

    }

    @Override
    public void updateGUI() {
        final float width = oCameraController.getViewport().getWorldWidth();
        final int padding = (int)(this.getWidth()*0.2f);
        this.setPosition(width - this.getWidth() - padding, padding );
    }
}
