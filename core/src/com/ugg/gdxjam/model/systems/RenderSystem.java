package com.ugg.gdxjam.model.systems;

import java.util.Comparator;

import bloom.Bloom;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.ugg.gdxjam.model.Logger;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.TimeCounter;
import com.ugg.gdxjam.model.ZComparator;
import com.ugg.gdxjam.model.components.PositionComponent;
import com.ugg.gdxjam.model.components.RenderableComponent;
import com.ugg.gdxjam.model.components.SizeComponent;
import com.ugg.gdxjam.model.utils.LoaderUtils;
import com.ugg.gdxjam.model.utils.ResolutionChooser;
import com.ugg.gdxjam.view.DisabledBlendingGroup;
import com.ugg.gdxjam.view.Renderable;

public class RenderSystem extends EntitySystem {
   
    final Stage playStage;
    final Stage guiStage;
    final TimeCounter orderZTime;
    final ZComparator zComparator;
    Bloom bloom;
    DisabledBlendingGroup group;
    
    public RenderSystem(DisabledBlendingGroup group, Stage playStage, Stage guiStage, LoaderUtils loader) {
        super();
        this.group = group;
        this.playStage = playStage;
        this.guiStage = guiStage;
        this.orderZTime = new TimeCounter(1/60f);
        this.zComparator = new ZComparator();
        final int w = Gdx.app.getGraphics().getWidth();
        final int h = Gdx.app.getGraphics().getHeight();
        float threshold = 0.65f;
        int sizex = (int)(w*0.45f);
        int sizey = (int)(h*0.45f);

        /*float scaleFactor = ResolutionChooser.getScaleFactor(loader.resolutions);
        if (scaleFactor == 6f) {
            threshold = 0.95f;
            sizex = w / 18;
            sizey = h / 18;
        } else if (scaleFactor == 4f) {
            threshold = 0.95f;
            sizex = w / 14;
            sizey = h / 14;
        } else if (scaleFactor == 2.5f) {
            threshold = 0.95f;
            sizex = w / 10;
            sizey = h / 10;
        }*/

        this.bloom = new Bloom(sizex, sizey,
                false, false, true);
        //this.bloom.setBloomIntesity(2f);
        //this.bloom.setTreshold(threshold);
        this.playStage.getRoot().getChildren().sort((Comparator)this.zComparator);


    }

    @Override
    public void update(float deltaTime) {
    	super.update(deltaTime);

        //Already set in bloom
        /*Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);*/

        bloom.setClearColor(0f, 0f, 0f, 1f);
        bloom.capture();

        //group.draw(this.playStage.getBatch(), 1f);
        if(orderZTime.lapse(deltaTime))
            this.playStage.getRoot().getChildren().sort((Comparator)this.zComparator);
    	this.playStage.draw();

        bloom.render();

        this.guiStage.act(deltaTime);
        this.guiStage.draw();

    }
    
    public Stage getPlayStage() {
		return playStage;
	}
    

   
}
