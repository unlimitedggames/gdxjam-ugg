package com.ugg.gdxjam.model.commands;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class CommandManager {

	final Array<Command> executedCommands;
	final Array<Command> undoedCommands;

	public CommandManager() {
		executedCommands = new Array<Command>();
		undoedCommands = new Array<Command>();
	}

	public void runCommand(Command command) {
		boolean executed = command.execute();
		if(executed){
			if(command.undoable) {
				executedCommands.add(command);
				if (undoedCommands.size > 0) {
					Pools.freeAll(undoedCommands);
					undoedCommands.clear();
				}
			}
		}
		if(!executed || !command.undoable)
			Pools.free(command);
	}
	
	private void runCommandRedo(Command command){
		command.execute();
		executedCommands.add(command);
	}
	
	public void undo(){
		if(executedCommands.size > 0){
			Command cmd = executedCommands.pop();
			if(cmd != null){
				cmd.undo();
				undoedCommands.add(cmd);
			}
		}
	}
	
	public void redo(){
		if(undoedCommands.size > 0){
			Command cmd = undoedCommands.pop();
			if(cmd != null){
				runCommandRedo(cmd);
			}
		}
	}
	
	public <T extends Command> T obtain(Class<T> commandClass){
		return Pools.obtain(commandClass);
	}
}