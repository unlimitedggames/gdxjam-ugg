package com.ugg.gdxjam.model;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.ugg.gdxjam.controller.EngineController;
import com.ugg.gdxjam.controller.GUIController;
import com.ugg.gdxjam.model.commands.Command;
import com.ugg.gdxjam.model.components.PlayerComponent;
import com.ugg.gdxjam.model.enums.InputPeripheralType;

public class Player {

	public int id = 1;
    public GameEntity mainEntity;
    final Array<GameEntity> oEntities;
	final ObjectMap<InputPeripheralType, IntMap<Class>> mappedCommands;
	final GUIController guiCtrl;
	public final int eventsToEnd = 4;
	public int currentEvents = 0;

	public Player(int id, GameEntity mainEntity, GUIController guiCtrl){
		this.id = id;
        this.mainEntity = mainEntity;
		this.guiCtrl = guiCtrl;
		this.mappedCommands = new ObjectMap<>();
        PlayerComponent playerC = this.mainEntity.addComponent(PlayerComponent.class);
        playerC.player = this;
		oEntities = new Array<GameEntity>();
	}

	public <T extends Command> Player mapCommand(InputPeripheralType peripheralType, int key, Class<T> commandClass){
		IntMap<Class> mappedByPeripheral = mappedCommands.get(peripheralType);
		if(mappedByPeripheral == null) {
			mappedByPeripheral = new IntMap<>();
            mappedCommands.put(peripheralType, mappedByPeripheral);
		}
		mappedByPeripheral.put(key, commandClass);
		return this;
	}

	public <T extends Command> Class<T> getCommandClass(InputPeripheralType peripheralType, int key){
		IntMap<Class> mappedByPeripheral = mappedCommands.get(peripheralType);
		if(mappedByPeripheral != null){
			Class<T> command = mappedByPeripheral.get(key);
			if(command != null) {
				return command;
			}else{
				Logger.getInstance().error("No command found for key [" + key + "] and peripheral [" + peripheralType + "]");
			}
		}else{
			Logger.getInstance().log("No commands found for peripheral [" + peripheralType + "]");
		}
		return null;
	}

	public Player updateBars(int lifeAmount, int lifeMax, int fuelAmount, int fuelMax ){
		guiCtrl.updateFuel(fuelAmount, fuelMax).updateLife(lifeAmount, lifeMax);
		return this;
	}

    public void checkEvent(){
        if(currentEvents >= eventsToEnd){
            mainEntity.engineCtrl.killAllEnemies();
            guiCtrl.showEndGame();
        }
    }
}
