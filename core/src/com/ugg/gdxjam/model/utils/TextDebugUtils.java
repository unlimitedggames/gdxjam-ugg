package com.ugg.gdxjam.model.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class TextDebugUtils {

	static final StringBuffer sb = new StringBuffer();
	
	public static void drawFPS(SpriteBatch guiBatch, SpriteBatch stageBatch, BitmapFont fnt, int fps) {
		guiBatch.begin();
		sb.delete(0, sb.length());
		sb.append("FPS: ");
		sb.append(fps);
		sb.append(", RCalls: ");
		sb.append(stageBatch.totalRenderCalls);
		fnt.setColor(1f, 1f, 1f, 1f);
		fnt.draw(guiBatch, sb, 0, Gdx.graphics.getHeight());
		guiBatch.end();
		stageBatch.totalRenderCalls = 0;
	}
}
