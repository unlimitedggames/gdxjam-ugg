package com.ugg.gdxjam.controller;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.ugg.gdxjam.model.Player;
import com.ugg.gdxjam.model.commands.Command;
import com.ugg.gdxjam.model.commands.CommandManager;
import com.ugg.gdxjam.model.components.InputCommandComponent;
import com.ugg.gdxjam.model.enums.InputPeripheralType;

/**
 * Created by Jose Cuellar on 08/01/2016.
 */
public class InputController {

    final CommandManager commandManager;

    final IntMap<Player> players;
    final ObjectMap<InputPeripheralType, IntMap<Player>> mappedKeysForPlayer;

    /**************************************/
    /********** STAGE OBJECTS ************/
    /**************************************/

    final Stage playStage;
    final Stage guiStage;
    final InputMultiplexer inputMultiplexer;

    public InputController(Stage playStage, Stage guiStage){
        this.playStage = playStage;
        this.guiStage = guiStage;
        this.inputMultiplexer = new InputMultiplexer(guiStage, playStage);
        this.commandManager = new CommandManager();
        this.players = new IntMap<>();
        this.mappedKeysForPlayer = new ObjectMap<>();
    }

    public InputController addPlayer(Player player){
        this.players.put(player.id, player);
        return this;
    }

    // La cuestion es que al hacer el input, vos cabal solo sabes ciertas cosas
    // una cosa es el pointer, el tipo de peripheral, la key por ejemplo, el boton
    // Esas cosas se van agregar como componente, un InputComponent
    // ok, entonces necesito un sistema que segun el input, el cual se le asigne a la entity
    // los tome y obtenga un comando de la clase especifica mapeada
    // y a ese comando le pase los argumentos
    // de esta forma, tengo un sistema, InputProcessSystem que se encargara de ejecutar los comandos
    // y el Inputcontroller, que solo se encargara de crear el InputComponent y adicionarlo al player indicado.
    public <T extends Command> InputController mapKeyToCommand(int playerId, InputPeripheralType peripheralType, int key, Class<T> commandClass){
        Player targetPlayer = this.players.get(playerId);
        targetPlayer.mapCommand(peripheralType, key, commandClass);
        IntMap<Player> mappedKeys = this.mappedKeysForPlayer.get(peripheralType);
        if(mappedKeys == null){
            mappedKeys = new IntMap<>();
            this.mappedKeysForPlayer.put(peripheralType, mappedKeys);
        }
        mappedKeys.put(key, targetPlayer);
        return this;
    }

    private Player getTargetPlayerForKey(int key, InputPeripheralType peripheralType){
        IntMap<Player> mappedKeys = this.mappedKeysForPlayer.get(peripheralType);
        if(mappedKeys == null)
            return null;
        else
            return mappedKeys.get(key);
    }

    private <T extends Command> Class<T> getCommandClass(int key, InputPeripheralType peripheralType){
        return getCommandClass(key, peripheralType, getTargetPlayerForKey(key, peripheralType));
    }

    private <T extends Command> Class<T> getCommandClass(int key, InputPeripheralType peripheralType, Player targetPlayer){
        Class<T> commandClass = targetPlayer.getCommandClass(peripheralType, key);
        return commandClass;
    }

    public InputController processTap(InputEvent event, float x, float y, int count, int button){
        Player targetPlayer = getTargetPlayerForKey(button, InputPeripheralType.Mouse);
        if(targetPlayer != null) {
            Class commandClass = getCommandClass(button, InputPeripheralType.Mouse, targetPlayer);
            Command command = obtain(commandClass);
            command.cma.addArgument(targetPlayer);
            command.cma.addArgument(event);
            command.cma.addArgument(button);
            command.cma.addArgument(x);
            command.cma.addArgument(y);
            command.cma.addArgument(count);
            InputCommandComponent inputCommandC = targetPlayer.mainEntity.addComponent(InputCommandComponent.class);
            inputCommandC.command = command;
        }
        return this;
    }

    public <T extends Command> InputController addClickCommand(final Actor actor, final Class<T> commandClass, final int playerId, final Object... arguments){
        final Player targetPlayer = players.get(playerId);
        actor.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Command command = obtain(commandClass);
                command.cma.addArgument(targetPlayer);
                addArguments(command, arguments);
                InputCommandComponent inputCommandC = targetPlayer.mainEntity.addComponent(InputCommandComponent.class);
                inputCommandC.command = command;
            }
        });
        return this;
    }

    private void addArguments(Command command, Object... arguments){
        command.cma.getArguments().addAll(arguments);
    }

    public <T extends Command> T obtain(Class<T> commandClass){
        T command = commandManager.obtain(commandClass);
        command.commandManager = commandManager;
        return command;
    }

    public InputController executeCommand(Command command){
        commandManager.runCommand(command);
        return this;
    }

    public void setInputForGUIAndPlay(){
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    public void setInputForGUI(){
        Gdx.input.setInputProcessor(guiStage);
    }

    public void disableInput(){
        Gdx.input.setInputProcessor(null);
    }
}
