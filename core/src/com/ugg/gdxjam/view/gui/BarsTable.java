package com.ugg.gdxjam.view.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.ugg.gdxjam.controller.CameraController;
import com.ugg.gdxjam.controller.InputController;
import com.ugg.gdxjam.model.UpdatableGUI;

/**
 * Created by Jose Cuellar on 13/01/2016.
 */
public class BarsTable extends Table implements UpdatableGUI {

    final CameraController oCameraController;
    final InputController inputCtrl;
    final ProgressBar pgbLife;
    final ProgressBar pgbFuel;

    float lastLife = -1f;
    float lastFuel = -1f;

    public BarsTable(CameraController oCameraController, InputController inputCtrl, Skin skin){
        this.oCameraController = oCameraController;
        this.inputCtrl = inputCtrl;
        Label lblLife = new Label("Life ", skin);
        lblLife.setColor(Color.GREEN);
        this.pgbLife = new ProgressBar(0, 100, 1, false, skin, "default");
        this.pgbLife.setColor(Color.GREEN);
        this.pgbLife.setAnimateDuration(0f);
        Label lblFuel = new Label("Fuel ", skin);
        lblFuel.setColor(Color.PURPLE);
        this.pgbFuel = new ProgressBar(0, 100, 1, false, skin, "default");
        this.pgbFuel.setColor(Color.PURPLE);
        this.pgbFuel.setAnimateDuration(0f);
        final float width = oCameraController.getViewport().getWorldWidth();
        this.add(lblLife);
        this.add(pgbLife).width((int)(width * 0.25f));
        this.row();
        this.add(lblFuel);
        this.add(pgbFuel).width((int)(width * 0.25f));
        this.pack();
    }

    public BarsTable updateLife(float amount, float max){
        updateBar(amount, max, pgbLife, lastLife);
        lastLife = amount;
        if(lastLife < max * 0.25f)
            pgbLife.setColor(Color.RED);
        else if(lastLife < max * 0.5f)
            pgbLife.setColor(Color.YELLOW);
        else
            pgbLife.setColor(Color.GREEN);
        return this;
    }

    public BarsTable updateFuel(int amount, int max){
        updateBar(amount, max, pgbFuel, lastFuel);
        lastFuel = amount;
        return this;
    }

    private void updateBar(float amount, float max, ProgressBar pgb, float lastAmount){
        if(lastAmount != amount) {
            if(amount >= max){
                pgb.setValue(pgbLife.getMaxValue());
            }else if(amount == 0){
                pgb.setValue(0);
            }else {
                float realValue = amount / max;
                int value = (int) (pgb.getMaxValue() * realValue);
                pgb.setValue(value);
            }
        }
    }

    @Override
    public void updateGUI() {
        final float width = oCameraController.getViewport().getWorldWidth();
        final float height = oCameraController.getViewport().getWorldHeight();
        final int padding = (int)(this.getHeight()*0.1f);
        this.setPosition(width - this.getWidth() - padding, height - this.getHeight() - padding );
    }
}
