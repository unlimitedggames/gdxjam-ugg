package com.ugg.gdxjam.model.enums;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectMap;
import com.ugg.gdxjam.model.data.GameData;
import com.ugg.gdxjam.model.data.RenderData;
import com.ugg.gdxjam.model.data.TroopData;
import com.ugg.gdxjam.model.utils.LoaderUtils;
import com.ugg.gdxjam.view.RenderableLifeBar;
import com.ugg.gdxjam.view.RenderableParticle;
import com.ugg.gdxjam.view.RenderableRegion;

public enum RenderDataType {

    MainShip,
    NormalBullet,
    EnemyShip,
    RedBullet,
    WatcherRedBullet,
    EnemyWatcher,
    EnemyTriple,
    MotherShip,
    EnemyMine,
    EnemyAttacker,
    LifeBar,

    ////////// SCENARIO
    Luna,
    Planeta1,
    Planeta2,
    Planeta3,
    Planeta5,
    Planeta6,
    Planeta7,
    Planeta8,
    Planeta9,
    Planeta10,

    Meteorito1,
    Meteorito2,
    Meteorito3,
    Meteorito4,

    Ship1,
    Ship2,
    Ship3,
    Ship4,
    Ship5,
    Ship6,
    Ship7,
    Ship8,
    Ship9,
    Ship10,
    Ship11,

    Heart,
    SpaceCloud,
    SpaceCloud1,
    SpaceCloud2,

    ////////// REWARDS
    SmallLifePowerUp,
    MediumLifePowerUp,
    LargeLifePowerUp,
    SmallFuelPowerUp,
    MediumFuelPowerUp,
    MotherShipPowerUp,
    FuelRegenerate,
    RapidFirePowerUp,

    //Particle
    Particle;

    static ObjectMap<RenderDataType, RenderData> renderDatas;

    public static void init(LoaderUtils loader) {
        renderDatas = new ObjectMap<>();
        renderDatas.put(MainShip,new RenderData(new RenderableRegion(loader.getRegion("player-ship")), 0, 0, ZIndexes.Ally.get(), 5, 4, true));
        renderDatas.put(NormalBullet,new RenderData(new RenderableRegion(loader.getRegion("bullet-normal")), 0, 0, ZIndexes.AllyBullet.get(), 1, 1, true));
        renderDatas.put(EnemyShip,new RenderData(new RenderableRegion(loader.getRegion("enemy-ship")), 0, 0, ZIndexes.Enemy.get(), 4, 5, true));
        renderDatas.put(RedBullet,new RenderData(new RenderableRegion(loader.getRegion("bullet-red")), 0, 0, ZIndexes.EnemyBullet.get(), 2f, 1.4f, true));
        renderDatas.put(WatcherRedBullet,new RenderData(new RenderableRegion(loader.getRegion("bullet-red")), 0, 0, ZIndexes.EnemyBullet.get(), 4f, 2.7f, true));
        renderDatas.put(EnemyWatcher,new RenderData(new RenderableRegion(loader.getRegion("enemy-bomb")), 0, 0, ZIndexes.Enemy.get(), 3.6f, 4.8f, true));
        renderDatas.put(EnemyTriple,new RenderData(new RenderableRegion(loader.getRegion("enemy-comm")), 0, 0, ZIndexes.Enemy.get(), 4.8f, 4.9f, true));
        renderDatas.put(MotherShip,new RenderData(new RenderableRegion(loader.getRegion("mothership")), 0, 0, ZIndexes.Enemy.get(), 40f, 40f, true));
        renderDatas.put(EnemyMine,new RenderData(new RenderableRegion(loader.getRegion("enemy-mine")), 0, 0, ZIndexes.Enemy.get(), 3.5f, 3.5f, true));
        renderDatas.put(EnemyAttacker,new RenderData(new RenderableRegion(loader.getRegion("enemy-attacker")), 0, 0, ZIndexes.Enemy.get(), 5, 4.97f, true));
        renderDatas.put(LifeBar,new RenderData(new RenderableLifeBar(loader.getRegion("white-square")), 0, 0, ZIndexes.LifeBar.get(), 6f, 1.6f, true));

                ////////// SCENARIO
        renderDatas.put(Luna,new RenderData(new RenderableRegion(loader.getRegion("luna")), 0, 0, ZIndexes.Scenario.get(), 4f, 4f, true));
        renderDatas.put(Planeta1,new RenderData(new RenderableRegion(loader.getRegion("planeta1")), 0, 0, ZIndexes.Scenario.get(), 8f, 8f, true));
        renderDatas.put(Planeta2,new RenderData(new RenderableRegion(loader.getRegion("planeta2")), 0, 0, ZIndexes.Scenario.get(), 8f, 8f, true));
        renderDatas.put(Planeta3,new RenderData(new RenderableRegion(loader.getRegion("planeta3")), 0, 0, ZIndexes.Scenario.get(), 8f, 8f, true));
        renderDatas.put(Planeta5,new RenderData(new RenderableRegion(loader.getRegion("planeta5")), 0, 0, ZIndexes.Scenario.get(), 15f, 15f, true));
        renderDatas.put(Planeta6,new RenderData(new RenderableRegion(loader.getRegion("planeta6")), 0, 0, ZIndexes.Scenario.get(), 8f, 8f, true));
        renderDatas.put(Planeta7,new RenderData(new RenderableRegion(loader.getRegion("planeta7")), 0, 0, ZIndexes.Scenario.get(), 8f, 8f, true));
        renderDatas.put(Planeta8,new RenderData(new RenderableRegion(loader.getRegion("planeta8")), 0, 0, ZIndexes.Scenario.get(), 8f, 8f, true));
        renderDatas.put(Planeta9,new RenderData(new RenderableRegion(loader.getRegion("planeta10")), 0, 0, ZIndexes.Scenario.get(), 8f, 8f, true));
        renderDatas.put(Planeta10,new RenderData(new RenderableRegion(loader.getRegion("planeta11")), 0, 0, ZIndexes.Scenario.get(), 8f, 8f, true));

        renderDatas.put(Meteorito1,new RenderData(new RenderableRegion(loader.getRegion("meteorito1")), 0, 0, ZIndexes.Scenario.get(), 8f, 8f, true));
        renderDatas.put(Meteorito2,new RenderData(new RenderableRegion(loader.getRegion("meteorito2")), 0, 0, ZIndexes.Scenario.get(), 8f, 8f, true));
        renderDatas.put(Meteorito3,new RenderData(new RenderableRegion(loader.getRegion("meteorito3")), 0, 0, ZIndexes.Scenario.get(), 8f, 8f, true));
        renderDatas.put(Meteorito4,new RenderData(new RenderableRegion(loader.getRegion("meteorito4")), 0, 0, ZIndexes.Scenario.get(), 8f, 8f, true));

        renderDatas.put(Ship1,new RenderData(new RenderableRegion(loader.getRegion("ship1")), 0, 0, ZIndexes.Scenario.get(), 11f, 11f, true));
        renderDatas.put(Ship2,new RenderData(new RenderableRegion(loader.getRegion("ship2")), 0, 0, ZIndexes.Scenario.get(), 20f, 20f, true));
        renderDatas.put(Ship3,new RenderData(new RenderableRegion(loader.getRegion("ship3")), 0, 0, ZIndexes.Scenario.get(), 20f, 20f, true));
        renderDatas.put(Ship4,new RenderData(new RenderableRegion(loader.getRegion("ship4")), 0, 0, ZIndexes.Scenario.get(), 20f, 20f, true));
        renderDatas.put(Ship5,new RenderData(new RenderableRegion(loader.getRegion("ship5")), 0, 0, ZIndexes.Scenario.get(), 15f, 18f, true));
        renderDatas.put(Ship6,new RenderData(new RenderableRegion(loader.getRegion("ship6")), 0, 0, ZIndexes.Scenario.get(), 20f, 20f, true));
        renderDatas.put(Ship7,new RenderData(new RenderableRegion(loader.getRegion("ship7")), 0, 0, ZIndexes.Scenario.get(), 15f, 18f, true));
        renderDatas.put(Ship8,new RenderData(new RenderableRegion(loader.getRegion("ship8")), 0, 0, ZIndexes.Scenario.get(), 20f, 20f, true));
        renderDatas.put(Ship9,new RenderData(new RenderableRegion(loader.getRegion("ship9")), 0, 0, ZIndexes.Scenario.get(), 20f, 20f, true));
        renderDatas.put(Ship10,new RenderData(new RenderableRegion(loader.getRegion("ship10")), 0, 0, ZIndexes.Scenario.get(), 20f, 20f, true));
        renderDatas.put(Ship11,new RenderData(new RenderableRegion(loader.getRegion("ship11")), 0, 0, ZIndexes.Scenario.get(), 20f, 20f, true));

        renderDatas.put(Heart,new RenderData(new RenderableRegion(loader.getRegion("heart")), 0, 0, ZIndexes.Scenario.get(), 20f, 20f, true));
        renderDatas.put(SpaceCloud,new RenderData(new RenderableRegion(loader.getRegion("space-cloud")), 0, 0, ZIndexes.Scenario.get(), 80f, 40f, true, new Color(0.8f, 0.8f, 0.9f, 0.65f)));
        renderDatas.put(SpaceCloud1,new RenderData(new RenderableRegion(loader.getRegion("space-cloud")), 0, 0, ZIndexes.Scenario.get(), 80f, 40f, true, new Color(1f, 1f, 0.9f, 0.55f)));
        renderDatas.put(SpaceCloud2,new RenderData(new RenderableRegion(loader.getRegion("space-cloud")), 0, 0, ZIndexes.Scenario.get(), 80f, 40f, true, new Color(0.8f, 0.9f, 0.7f, 0.45f)));

        ////////// REWARDS
        renderDatas.put(SmallLifePowerUp,new RenderData(new RenderableRegion(loader.getRegion("powerup")), 0, 0, ZIndexes.Reward.get(), 2.5f, 2.5f, true, new Color(0f, 0.6f, 0f, 1f)));
        renderDatas.put(MediumLifePowerUp,new RenderData(new RenderableRegion(loader.getRegion("powerup")), 0, 0, ZIndexes.Reward.get(), 3.2f, 3.2f, true, new Color(0f, 0.8f, 0f, 1f)));
        renderDatas.put(LargeLifePowerUp,new RenderData(new RenderableRegion(loader.getRegion("powerup")), 0, 0, ZIndexes.Reward.get(), 4f, 4f, true, new Color(0f, 1f, 0f, 1f)));
        renderDatas.put(SmallFuelPowerUp,new RenderData(new RenderableRegion(loader.getRegion("powerup")), 0, 0, ZIndexes.Reward.get(), 2.5f, 2.5f, true, new Color(1f, 0f, 1f, 1f)));
        renderDatas.put(MediumFuelPowerUp,new RenderData(new RenderableRegion(loader.getRegion("powerup")), 0, 0, ZIndexes.Reward.get(), 3.2f, 3.2f, true, new Color(1f, 0f, 1f, 1f)));
        renderDatas.put(MotherShipPowerUp,new RenderData(new RenderableRegion(loader.getRegion("powerup")), 0, 0, ZIndexes.Reward.get(), 4.5f, 4.5f, true, new Color(0f, 0f, 1f, 1f)));
        renderDatas.put(FuelRegenerate,new RenderData(new RenderableRegion(loader.getRegion("powerup")), 0, 0, ZIndexes.Reward.get(), 4f, 4f, true, Color.PINK));
        renderDatas.put(RapidFirePowerUp,new RenderData(new RenderableRegion(loader.getRegion("powerup")), 0, 0, ZIndexes.Reward.get(), 4f, 4f, true, new Color(1f, 1f, 1f, 1f)));

        //Particle
        renderDatas.put(Particle,new RenderData(new RenderableParticle(), 0, 0, ZIndexes.ParticleDown.get(), 2.5f, 2.5f, true, new Color(0f, 0f, 1f, 1f)));
    }

    public static RenderData get(RenderDataType type) {
        return renderDatas.get(type);
    }


}
