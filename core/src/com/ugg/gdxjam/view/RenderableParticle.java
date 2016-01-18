package com.ugg.gdxjam.view;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pools;
import com.ugg.gdxjam.model.enums.ParticleType;

/**
 * Created by Jose Cuellar on 05/01/2016.
 */
public class RenderableParticle extends Renderable{

    ParticleEffectPool.PooledEffect pooledEffect;

    public RenderableParticle(){
        super();
    }

    @Override
    public Renderable cloneObject() {
        RenderableParticle newRenderable = Pools.obtain(RenderableParticle.class);
        return newRenderable;
    }

    public void setEffect(ParticleEffectPool.PooledEffect effect){
        this.pooledEffect = effect;
    }

    public void update(float delta){
        pooledEffect.update(delta);
    }

    public boolean isComplete(){
        return this.pooledEffect.isComplete();
    }

    @Override
    public void setPosition(float x, float y){
        pooledEffect.setPosition(x, y);
        super.setPosition(x, y);
    }

    public void tintEmitters(Color color){
        for(int i = 0, n = this.pooledEffect.getEmitters().size; i < n; i++) {
            float[] thisColor = this.pooledEffect.getEmitters().get(i).getTint().getColors();
            thisColor[0] = color.r;
            thisColor[1] = color.g;
            thisColor[2] = color.b;
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
            pooledEffect.draw(batch);
        }
    }

    @Override
    public void reset() {
        if(this.pooledEffect != null)
            pooledEffect.free();
    }
}
