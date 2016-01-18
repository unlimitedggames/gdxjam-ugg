package com.ugg.gdxjam.view;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pools;

/**
 * Created by Jose Cuellar on 05/01/2016.
 */
public class RenderableRegion extends Renderable{

    TextureRegion region;

    public RenderableRegion(){
        super();
    }

    public RenderableRegion(TextureRegion region){
        super();
        this.region = region;
    }

    public RenderableRegion(Entity entity){
        super(entity);
    }

    @Override
    public Renderable cloneObject() {
        RenderableRegion newRenderable = Pools.obtain(RenderableRegion.class);
        newRenderable.setEntity(this.entity);
        newRenderable.setRegion(this.region);
        newRenderable.setColor(this.getColor());
        return newRenderable;
    }

    public void setRegion(TextureRegion region){
        this.region = region;
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
            batch.setColor(this.getColor());
            batch.draw(region, x, y, this.getOriginX(), this.getOriginY(), halfWidth, halfHeight, this.getScaleX(), this.getScaleY(), this.getRotation());
        }
    }

    @Override
    public void reset() {
        this.region = null;
        this.entity = null;
    }
}
