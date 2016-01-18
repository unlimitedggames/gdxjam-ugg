package com.ugg.gdxjam.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Created by Jose Cuellar on 18/01/2016.
 */
public class DisabledBlendingGroup extends Group {

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.disableBlending();
        super.draw(batch, parentAlpha);
        batch.enableBlending();
    }
}
