package com.ugg.gdxjam.model.data;


import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pools;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.components.ColorComponent;
import com.ugg.gdxjam.model.components.OriginComponent;
import com.ugg.gdxjam.model.components.PositionComponent;
import com.ugg.gdxjam.model.components.RenderableComponent;
import com.ugg.gdxjam.model.components.RotationComponent;
import com.ugg.gdxjam.model.components.ScaleComponent;
import com.ugg.gdxjam.model.components.ShowActorComponent;
import com.ugg.gdxjam.model.components.SizeComponent;
import com.ugg.gdxjam.view.Renderable;
import com.ugg.gdxjam.view.RenderableRegion;

public class RenderData implements GameData{

	public Renderable renderable;
	public float x, y;
	public int z;
	public float width, height;
	public float scaleX = 1f, scaleY = 1f;
	public float originX = 0f, originY = 0f;
	public float rotation = 0f;
	public float offsetX = 0f, offsetY = 0f;
	public Color color;
	

	public RenderData(Renderable renderable, float x, float y, int z, float width,
			float height, boolean centerOrigin) {
		this(renderable, x, y, z, width,
				height, 1f, 1f, centerOrigin?  width*0.5f : 0f,
						centerOrigin? height*0.5f : 0f, 0f, -width*0.5f, -height*0.5f, new Color(1f, 1f, 1f, 1f));
	}

	public RenderData(Renderable renderable, float x, float y, int z, float width,
					  float height, boolean centerOrigin, Color color) {
		this(renderable, x, y, z, width,
				height, 1f, 1f, centerOrigin?  width*0.5f : 0f,
				centerOrigin? height*0.5f : 0f, 0f, -width*0.5f, -height*0.5f, color);
	}

	public RenderData(Renderable renderable, float x, float y, int z, float width,
			float height, float originX, float originY, float rotation) {
		this(renderable, x, y, z, width,
				height, 1f, 1f, originX,
				originY, rotation, -width*0.5f, -height*0.5f, new Color(1f, 1f, 1f, 1f));
	}
	
	public RenderData(Renderable renderable, float x, float y, int z, float width,
			float height, float scaleX, float scaleY, float originX,
			float originY, float rotation, float offX, float offY, Color color) {
		this.renderable = renderable;
        this.renderable.setZIndex(z);
		this.x = x;
		this.y = y;
		this.z = z;
		this.width = width;
		this.height = height;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.originX = originX;
		this.originY = originY;
		this.rotation = rotation;
		this.color = color;
		this.offsetX = offX;
		this.offsetY = offY;
	}



	@Override
	public void assignComponents(GameEntity gameEntity) {

		RenderableComponent rcn = gameEntity.addComponent(RenderableComponent.class);
		PositionComponent pcn = gameEntity.addComponent(PositionComponent.class);
		SizeComponent scn = gameEntity.addComponent(SizeComponent.class);
		ScaleComponent scaleC = gameEntity.addComponent(ScaleComponent.class);
		RotationComponent rotationC = gameEntity.addComponent(RotationComponent.class);
		ColorComponent colorC = gameEntity.addComponent(ColorComponent.class);
		OriginComponent originC = gameEntity.addComponent(OriginComponent.class);
		
		rcn.renderable = renderable.cloneObject();
        rcn.renderable.setZIndex(this.z);
		pcn.x = x;
		pcn.y = y;
		pcn.z = z;
		pcn.offsetX = this.offsetX;
		pcn.offsetY = this.offsetY;
		scn.width = width;
		scn.height = height;
		scaleC.x = scaleX;
		scaleC.y = scaleY;
		originC.x = originX;
		originC.y = originY;
		rotationC.rotation = rotation;
		colorC.color.set(this.color);
		
	}
	
	
}
