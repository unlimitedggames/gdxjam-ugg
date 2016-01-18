package com.ugg.gdxjam.model.enums;

import com.ugg.gdxjam.model.data.ScenarioData;
import com.ugg.gdxjam.model.data.SoundData;

/**
 * Created by Jose Cuellar on 11/01/2016.
 */
public enum SoundDataType {

    PlayerShip(new SoundData(GameSound.HitBig, GameSound.Shoot, GameSound.BigExplosion, null, null )),
    EnemyShip(new SoundData(GameSound.Hit, GameSound.Shoot, GameSound.Explosion, null, null )),
    Reward(new SoundData(null, null, GameSound.PowerUp1, null, null )),
    SpecialReward(new SoundData(null, null, GameSound.PowerUp2, null, null )),
    Meteorite(new SoundData(GameSound.Hit, null, GameSound.Explosion, null, null ));


    private final SoundData data;

    private SoundDataType(
            SoundData data)
    {
        this.data = data;
    }

    public SoundData get()
    {
        return data;
    }
}
