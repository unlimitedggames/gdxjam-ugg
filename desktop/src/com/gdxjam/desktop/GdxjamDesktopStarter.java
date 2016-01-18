package com.gdxjam.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ugg.gdxjam.GDXJam;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class GdxjamDesktopStarter{
	
	public static void main(String[] args) throws LWJGLException {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();

        /*cfg.width = LwjglApplicationConfiguration.getDesktopDisplayMode().width;
        cfg.height = LwjglApplicationConfiguration.getDesktopDisplayMode().height;*/
		cfg.width = 960;
		cfg.height = 720;
        cfg.resizable = false;
        //cfg.fullscreen = true;
		cfg.vSyncEnabled = true;
		
		new LwjglApplication(new GDXJam(), cfg);
	}
	
	
}
