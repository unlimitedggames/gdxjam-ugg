
package com.ugg.gdxjam.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.ugg.gdxjam.model.GameEntity;

public class FSMComponent implements Component,Telegraph {

	public DefaultStateMachine stateMachine = new DefaultStateMachine();

	public void update () {
		stateMachine.update();
	}

	public void changeState (State<GameEntity> state) {
		stateMachine.changeState(state);
	}

	@Override
	public boolean handleMessage (Telegram msg) {
		return stateMachine.handleMessage(msg);
	}
}
