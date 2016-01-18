package com.ugg.gdxjam.model.utils;

import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;

/**
 * Created by Jose Cuellar on 27/08/2015.
 */
public class ResolutionChooser {

   public static float getScaleFactor(ResolutionFileResolver.Resolution[] resolutions){
      ResolutionFileResolver.Resolution r = ResolutionFileResolver.choose(resolutions);
      float currentScaleFactor = 1f;
      if(r.folder.equals("1")){
         currentScaleFactor = 6f;
      }else if(r.folder.equals("0.66")){
         currentScaleFactor = 4f;
      }else if(r.folder.equals("0.416")){
         currentScaleFactor = 2.5f;
      }
      return currentScaleFactor;
   }
}
