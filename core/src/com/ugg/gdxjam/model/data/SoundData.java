package com.ugg.gdxjam.model.data;

import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.components.SoundComponent;
import com.ugg.gdxjam.model.enums.GameSound;

/**
 * Created by Jose Cuellar on 16/01/2016.
 */
public class SoundData implements GameData {

    public GameSound hit;
    public GameSound shoot;
    public GameSound killed;
    public GameSound spawn;
    public GameSound move;

    public SoundData(GameSound hit, GameSound shoot, GameSound killed, GameSound spawn, GameSound move) {
        this.hit = hit;
        this.shoot = shoot;
        this.killed = killed;
        this.spawn = spawn;
        this.move = move;
    }

    @Override
    public void assignComponents(GameEntity gameEntity) {
        SoundComponent soundC = gameEntity.addComponent(SoundComponent.class);
        soundC.hit = hit;
        soundC.shoot = shoot;
        soundC.killed = killed;
        soundC.spawn = spawn;
        soundC.move = move;
    }
}
