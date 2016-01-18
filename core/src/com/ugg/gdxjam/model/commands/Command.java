package com.ugg.gdxjam.model.commands;

import com.badlogic.gdx.utils.Pool;

public abstract class Command implements Pool.Poolable {

	public final CommandArguments cma = new CommandArguments();
	public CommandManager commandManager;
	public boolean undoable = false;

	public abstract boolean execute();
	public abstract void undo();
}
