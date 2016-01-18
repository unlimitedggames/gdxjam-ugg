package com.ugg.gdxjam.view;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool;
import com.ugg.gdxjam.model.Clonable;

public abstract class Renderable extends Actor implements Pool.Poolable, Clonable<Renderable>{

	Entity entity;
	public int zInd = 0;


	public Renderable(){

	}

	@Override
	public void setZIndex(int index) {
		super.setZIndex(index);
		this.zInd = index;
	}

	public Renderable(Entity entity){
		this.entity = entity;
	}

	public void setEntity(Entity entity){
		this.entity = entity;
	}

}
