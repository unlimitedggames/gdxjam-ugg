package com.ugg.gdxjam.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.*;

public class Logger {

	private static Logger _instance = null;
	private static Application _app = null;
	private static String _appName = "GDXJam-UGG";
	
	protected Logger(){
		
	}
	
	public static Logger getInstance(){
		if(_instance == null || _app != Gdx.app){
			_instance = new Logger();
			_app = Gdx.app;
		}
		return _instance;
	}
	
	public void log(String message){
		Gdx.app.log(_appName, message);
	}
	
	public void error(String message){
		Gdx.app.error(_appName, message);
	}
	
}
