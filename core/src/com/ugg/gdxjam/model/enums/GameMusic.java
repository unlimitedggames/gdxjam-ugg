package com.ugg.gdxjam.model.enums;

public enum GameMusic {
    Main( "data/music/Jellyfish in Space.ogg" )
    ,Boss( "data/music/Future Gladiator.ogg" )
    ,Finish( "data/music/Presenterator.ogg" );

    private final String fileName;

    private GameMusic(
           String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return fileName;
    }
}
