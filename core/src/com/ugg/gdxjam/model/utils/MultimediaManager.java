package com.ugg.gdxjam.model.utils;

/*import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;*/
import com.badlogic.gdx.Gdx;

public class MultimediaManager {

  /* final TweenCallback tweenRestoreVolume = new TweenCallback() {

      @Override
      public void onEvent(int arg0, BaseTween arg1) {
         MultimediaManager mgr = (MultimediaManager)arg1.getUserData();
         if(mgr instanceof MusicManager){
            ((MusicManager)mgr).stop();
         }
         mgr.setVolume(mgr.prevVolume);
      }
   };*/

   /**
    * The volume to be set on the music.
    */
   protected float volume = 1f;
   protected float prevVolume = volume;

   /**
    * Sets the sound volume which must be inside the range [0,1].
    */
   public void setVolume(float volume) {

      // check and set the new volume
      if (volume < 0 || volume > 1f) {
         throw new IllegalArgumentException(
                "The volume must be inside the range: [0,1]");
      }
      this.volume = volume;
   }

   public float getVolume() {
      return this.volume;
   }

   public void fadeVolume(float time){
      this.prevVolume = this.volume;
      /*Tween.to(this, MultimediaObjAccesor.VOLUMEN, time)
             .target(0f)
             .setCallback(this.tweenRestoreVolume)
             .setUserData(this)
             .ease(TweenEquations.easeNone)
             .start(PCMExample.tweenManager);*/

   }


}
