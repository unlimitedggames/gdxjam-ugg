package com.ugg.gdxjam.model.commands;

import com.badlogic.gdx.utils.Array;

public class CommandArguments {
	
	private Array<Object> args;

	CommandArguments() {
		args = new Array<Object>();
	}

	public Array<Object> getArguments() {
		return args;
	}

	public void addArgument(Object arg0) {
		args.add(arg0);
	}

	public void clear(){
		this.args.clear();
	}

}
