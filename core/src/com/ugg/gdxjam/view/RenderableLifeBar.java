package com.ugg.gdxjam.view;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pools;

/**
 * Created by Jose Cuellar on 05/01/2016.
 */
public class RenderableLifeBar extends Renderable{

    TextureRegion region;
    public Color barBackground = new Color(0f, 0f, 0f, 0.45f);
    public float offsetX, offsetY;
    public float borderThickness = 0.3f;
    private float barWidth = 0f;
    private float batTotalWidthDiv = -1f;

    public RenderableLifeBar(){
        super();
    }

    public RenderableLifeBar(TextureRegion region){
        super();
        this.region = region;
        this.setColor(0f, 1f, 0f, 0.70f);
    }

    public RenderableLifeBar(Entity entity){
        super(entity);
    }

    @Override
    public Renderable cloneObject() {
        RenderableLifeBar newRenderable = Pools.obtain(RenderableLifeBar.class);
        newRenderable.setEntity(this.entity);
        newRenderable.setRegion(this.region);
        newRenderable.borderThickness = this.borderThickness;
        newRenderable.offsetY = this.offsetY;
        newRenderable.offsetX = this.offsetX;
        newRenderable.barBackground.set(this.barBackground);
        newRenderable.setColor(this.getColor());
        return newRenderable;
    }

    public void setRegion(TextureRegion region){
        this.region = region;
    }

    public void setLife(float current, float max){
        if(current == max || current <= 0){
            this.setVisible(false);
        }else{
            this.setVisible(true);
            if(batTotalWidthDiv == -1f)
                batTotalWidthDiv = 1f/max;
            this.barWidth = this.getWidth() * current * batTotalWidthDiv;
        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        final OrthographicCamera camera = (OrthographicCamera) this.getStage().getCamera();
        final float x = this.getX();
        final float y = this.getY();
        final float halfWidth = this.getWidth();
        final float halfHeight = this.getHeight();

        if(camera.frustum.boundsInFrustum(x, y, 0, halfWidth, halfHeight, 0)) {
            batch.setColor(this.barBackground);
            batch.draw(region, x + offsetX, y + offsetY, this.getOriginX(), this.getOriginY(), halfWidth, halfHeight, this.getScaleX(), this.getScaleY(), this.getRotation());
            batch.setColor(this.getColor());
            batch.draw(region, x + offsetX + borderThickness, y + offsetY + borderThickness, this.getOriginX(), this.getOriginY(), barWidth - (borderThickness*2f), halfHeight - (borderThickness*2f), this.getScaleX(), this.getScaleY(), this.getRotation());
        }
    }

    @Override
    public void reset() {
        this.region = null;
        this.entity = null;
        this.offsetX = this.offsetY = 0f;
        this.borderThickness = 0.3f;
        this.barWidth = 0f;
        this.batTotalWidthDiv = -1f;
    }
}
