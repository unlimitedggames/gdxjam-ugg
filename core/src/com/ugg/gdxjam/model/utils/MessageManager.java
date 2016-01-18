package com.ugg.gdxjam.model.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.ugg.gdxjam.view.RenderableText;

public class MessageManager {

	public class TipoMensaje{
		public static final int TOAST_IZQ = 0;
		public static final int CONVERSACION = 1;
	}
	
	private final float OFFSET_X_MSG;
	private final float OFFSET_Y_MSG;
	private final float DELAY_TOASTS;
	private final int NUM_MAX_TOAST_MSG;
	
	private final Array<RenderableText> messageQueue;
	private final Stage guiStage;
	
	private final Vector2 tmpVector;
	
	public MessageManager(float OFFSETX_MSG, float OFFSETY_MSG, int NUM_MAX_TOAST_MSG, float DELAY_TOASTS, Stage guiStage){
		this.OFFSET_X_MSG = OFFSETX_MSG;
		this.OFFSET_Y_MSG = OFFSETY_MSG;
		this.DELAY_TOASTS = DELAY_TOASTS;
		this.NUM_MAX_TOAST_MSG = NUM_MAX_TOAST_MSG;
		this.messageQueue = new Array<RenderableText>();
		this.tmpVector = new Vector2();
		this.guiStage = guiStage;
	}
	
	public void mostrarToastIzq(String texto, Color color){
/*
		if(this.messageQueue.size == NUM_MAX_TOAST_MSG){
			TextActor tAc = messageQueue.get(0);
			PCMExample.tweenManager.killTarget(tAc);
			removerToastDeCola(tAc);
			Timeline.createParallel()
			.push(
					Tween.to(tAc, ActorObjAccesor.OPACITY, 0.45f)
					.target(0f)
					.ease(TweenEquations.easeNone)
			)
			.push(
					Tween.to(tAc, ActorObjAccesor.POSITION_X, 0.35f)
					.target(this.OFFSET_X_MSG - tAc.getWidth())
					.ease(TweenEquations.easeInExpo)
			)
			.setCallback(PCMExample.loaderHelper.removerTextoDespuesDeTween)
			.setUserData(tAc)
			.beginParallel()
			.start(PCMExample.tweenManager);
		}
		
		TextActor txtAct = TextFactoryHelper.generarTexto(TipoTexto.TEXTO_TOAST);
		txtAct.setText(texto);
		final float yPos = this.OFFSET_Y_MSG - (txtAct.getHeight() * 0.5f) - (this.messageQueue.size * txtAct.getHeight()*1.15f);
		txtAct.setPosition(this.OFFSET_X_MSG - txtAct.getWidth(), yPos);
		txtAct.msgEnCola = true;
		txtAct.setColor(1f, 1f, 1f, 0f);
		
		Timeline.createSequence()
		.push(Timeline.createParallel()
				.push(
						Tween.to(txtAct, ActorObjAccesor.OPACITY, 0.5f)
						.target(1f)
						.ease(TweenEquations.easeOutExpo)
						)
				.push(
						Tween.to(txtAct, ActorObjAccesor.OPACITY, DELAY_TOASTS * 0.25f)
						.target(0.90f)
						.ease(TweenEquations.easeNone)
						.delay(0.5f)
						)
				.push(
						Tween.to(txtAct, ActorObjAccesor.TONALIDAD, 0.4f)
						.target(color.r, color.g, color.b)
						.ease(TweenEquations.easeOutExpo)
						)
				.push(
						Tween.to(txtAct, ActorObjAccesor.POSITION_X, 0.4f)
						.target(this.OFFSET_X_MSG + (txtAct.getWidth() * 0.5f))
						.ease(TweenEquations.easeOutExpo)
						)
						.beginParallel().end())
		.push(Timeline.createParallel()
				.push(
						Tween.to(txtAct, ActorObjAccesor.OPACITY, 0.4f)
						.target(0f)
						.ease(TweenEquations.easeNone)
				)
				.push(
						Tween.to(txtAct, ActorObjAccesor.POSITION_X, 0.3f)
						.target(this.OFFSET_X_MSG - txtAct.getWidth())
						.ease(TweenEquations.easeInExpo)
				)
				.delay(DELAY_TOASTS)
				.beginParallel().end())
			.setCallback(PCMExample.loaderHelper.removerTextoDespuesDeTween)
			.setUserData(txtAct)
			.beginSequence()
			.start(PCMExample.tweenManager);
		
		this.messageQueue.add(txtAct);
		this.guiStage.addActor(txtAct);

		//Ejecutamos sonido
		SoundManager.getInstance().play(SoundManager.PCMSound.WHOOSH);*/
	}
	
	public void removerToastDeCola(RenderableText txtAct){
		txtAct.msgEnCola = false;
		if(messageQueue.size > 1){
			final int size = messageQueue.size;
			RenderableText tAct = null;
			for (int i = 1; i < size; i++){
				tAct = messageQueue.get(i);
				/*Tween.to(tAct, ActorObjAccesor.POSITION_Y, 0.3f)
					.target(messageQueue.get(i - 1).getY())
					.ease(TweenEquations.easeOutExpo)
					.start(PCMExample.tweenManager);*/
			}
		}
		this.messageQueue.removeValue(txtAct, true);
		
	}
	
	public void reset(){
		//TextFactoryHelper.vaciarTextActorPool();
		this.messageQueue.clear();
	}
	
	public void addTextActor(RenderableText txtAct){
		this.guiStage.addActor(txtAct);
	}
	
	public void addTextActor(RenderableText txtAct, float posX, float posY){
		//Gdx.app.log(PCMExample.LOG, "Posx: " + posX + ", Posy: " + posY);
		this.tmpVector.set(posX, posY);
		this.guiStage.screenToStageCoordinates(this.tmpVector);
		txtAct.setPosition(tmpVector.x, tmpVector.y);
		this.guiStage.addActor(txtAct);
	}
	
}
