package com.ugg.gdxjam.model;

import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;

public interface Clonable<T> {
	
	public T cloneObject();
	
	//public void cloneTo(T object);
	
}
