package com.ugg.gdxjam.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Pool.Poolable;

public class RenderableText extends Actor implements Poolable{

  private final Table tbText;
  private final Label lblText;
  public int pObjId = -1;
 // public int tipoTexto = TipoTexto.TEXTO_TITULO;
  public boolean msgEnCola = false;
  
  public RenderableText(Skin uiskin){
	  tbText = new Table();
      lblText = new Label("", uiskin.get("default", LabelStyle.class));
      setearParamsBasicos();
  }

  private void setearParamsBasicos(){
      tbText.add(lblText).expand().fill();
      tbText.pack();
      tbText.setTransform(false);
  }
  
  public void setTransform(boolean transform){
	  tbText.setTransform(transform);
  }
  
  public void setTextStyle(String style, Skin uiskin){
	  lblText.setStyle(uiskin.get(style, LabelStyle.class));
  }
  
  public void setTableStyle(String style, Skin uiskin){
	  tbText.setSkin(uiskin);
	  tbText.setBackground(style);
	  
  }

   public void setText(String text){
      lblText.setText(text);
	   GlyphLayout layout = lblText.getGlyphLayout();
	  setSize((int) layout.width, (int) (layout.height * 1.25f));
      this.setOrigin((int)(tbText.getWidth() * 0.5f), (int)(tbText.getHeight() * 0.5f));
   }

  @Override
  public void setOrigin(float originX, float originY) {
	  super.setOrigin((int)originX, (int)originY);
  }

   @Override
  public void setSize(float width, float height) {
	  super.setSize((int)width, (int)height);
	  tbText.setSize((int)width, (int)height);
  }
   
  @Override
	public void setWidth(float width) {
		super.setWidth(width);
		 tbText.setSize((int)width, (int)this.getHeight());
	}
  
  @Override
 	public void setHeight(float height) {
 		super.setHeight(height);
 		 tbText.setSize((int)this.getWidth(), (int)height);
 	}


   @Override
	public void setRotation(float degrees) {
		// TODO Auto-generated method stub
		super.setRotation(degrees);
		tbText.setRotation(degrees);
	}

  @Override
	public void setScale(float scaleX, float scaleY) {
		super.setScale(scaleX, scaleY);
		tbText.setScale(scaleX, scaleY);
	}
  
  @Override
	public void setScaleX(float scaleX) {
		// TODO Auto-generated method stub
		super.setScaleX(scaleX);
		tbText.setScaleX(scaleX);
	}
  
  @Override
	public void setScaleY(float scaleY) {
		super.setScaleY(scaleY);
		tbText.setScaleY(scaleY);
	}
  
  @Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		tbText.setPosition((int)(x - this.getOriginX()), (int)(y - this.getOriginY()));
	}
  
  @Override
	public void setX(float x) {
		super.setX(x);
		tbText.setX((int)(x - this.getOriginX()));
	}
  
  @Override
	public void setY(float y) {
		super.setY(y);
		tbText.setY((int)(y - this.getOriginY()));
	}



  @Override
	public void moveBy(float x, float y) {
		super.moveBy(x, y);
	  tbText.setPosition((int)(x - this.getOriginX()), (int)(y - this.getOriginY()));
	}
  
  @Override
  public void reset(){
	  this.setVisible(false);
	  this.setRotation(0);
	  this.setScale(1f, 1f);
	  this.getColor().set(1f, 1f, 1f, 1f);
	  this.setTransform(false);
	  this.msgEnCola = false;
	  this.clearActions();
	  tbText.setSkin(null);
	  TextureRegion r = null;
	  tbText.setBackground((Drawable)r);
  }
  
  @Override
	public void setColor(float r, float g, float b, float a) {
		// TODO Auto-generated method stub
		super.setColor(r, g, b, a);
		lblText.setColor(this.getColor());
	}
  
  @Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub
		super.setColor(color);
		lblText.setColor(color);
	}
  
	public void setTableColor(Color color) {
		tbText.setColor(color);
	}
  
	public Label getLabel(){
		return this.lblText;
	}
	
	public Table getTable(){
		return this.tbText;
	}
	
  @Override
  public void draw(Batch batch, float parentAlpha) {
  
	  final Color tbColor = tbText.getColor();
	  tbText.setColor(tbColor.r, tbColor.g, tbColor.b, this.getColor().a);
	  lblText.setColor(this.getColor());
	  
	  if(lblText.getColor().a > 0.05f){
		  tbText.draw(batch, parentAlpha);
	  }
  }

}
