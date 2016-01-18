package com.ugg.gdxjam.model.utils;

import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;

public class GUIConfig {

	public static float MINPADDING = 5*6;
	public static float WINDOW_PADDING_Y = 7*6;
	public static float WINDOW_PADDING_X = 7*6;
	public static final float ACTION_DELAY = 0.4f;
	
	public static int MIN_BUTTON_HEIGHT = 32 * 6;
	public static int MIN_BTN_WIDTH = 88 * 6;
	public static int SLIDER_WIDTH = 100 * 6;
	
	public static float PIXELS_PER_METER_X = 30*6;
	
	public static float HIDE_DELAY = 0.5f;
	public static float SHOW_DELAY = 0.7f;
	
	public static int MIN_BTN_SELECT_WIDTH = 78*6;

	public static float RES_MULTIPLIER = 1f;
	
	public static void initializeValues(Resolution[] resolutions){
		Resolution r = ResolutionFileResolver.choose(resolutions);
		
		float multiplier = 1;
		
		if(r.folder.equals("1")){
			multiplier = 0.80f;
			RES_MULTIPLIER = 6f;
		}else if(r.folder.equals("0.66")){
			multiplier = 0.60f;
			RES_MULTIPLIER = 4f;
		}else if(r.folder.equals("0.416")){
			multiplier = 0.400f;
			RES_MULTIPLIER = 2.5f;
		}else{
			multiplier = 0.16f;
			RES_MULTIPLIER = 1f;
		}

		MINPADDING = (int)(MINPADDING*multiplier);
		WINDOW_PADDING_Y = (int)(WINDOW_PADDING_Y*multiplier);
		WINDOW_PADDING_X = (int)(WINDOW_PADDING_X*multiplier);
		
		MIN_BUTTON_HEIGHT = (int)(MIN_BUTTON_HEIGHT * multiplier);
		MIN_BTN_WIDTH = (int)(MIN_BTN_WIDTH * multiplier);
		SLIDER_WIDTH = (int)(SLIDER_WIDTH * multiplier);
		
		PIXELS_PER_METER_X = (int)(PIXELS_PER_METER_X*multiplier);
		
		MIN_BTN_SELECT_WIDTH = (int)(MIN_BTN_SELECT_WIDTH*multiplier);
	}
	
}
