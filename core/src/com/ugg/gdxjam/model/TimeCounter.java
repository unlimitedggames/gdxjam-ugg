package com.ugg.gdxjam.model;

/**
 * Created by Jose Cuellar on 23/05/2015.
 */
public class TimeCounter {

   public float limit = 0f;

   private float acum = 0f;

   public TimeCounter() { }

   public TimeCounter(float limit){ this.limit = limit; }

   public boolean lapse(float delta){
      boolean value = false;
      acum += delta;
      if(acum >= limit){
         acum -= limit;
         value = true;
      }
      return value;
   }

   public void resetCounter(){
      acum = 0f;
   }

   public void reset(){
      limit = 0f;
      acum = 0f;
   }
}
