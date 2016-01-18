package com.ugg.gdxjam.model.enums;

import com.ugg.gdxjam.model.configs.Configs;
import com.ugg.gdxjam.model.data.ScenarioData;
import com.ugg.gdxjam.model.data.TroopData;

/**
 * Created by Jose Cuellar on 11/01/2016.
 */
public enum ScenarioDataType {

    Luna(new ScenarioData(RenderDataType.Luna)),    //0
    Planeta1(new ScenarioData(RenderDataType.Planeta1)),//1
    Planeta2(new ScenarioData(RenderDataType.Planeta2)),//2
    Planeta3(new ScenarioData(RenderDataType.Planeta3)),//3
    Planeta4(new ScenarioData(RenderDataType.Planeta5)),//4
    Planeta5(new ScenarioData(RenderDataType.Planeta6)),//5
    Planeta6(new ScenarioData(RenderDataType.Planeta7)),//6
    Planeta7(new ScenarioData(RenderDataType.Planeta8)),//7

    Meteorito1(new ScenarioData(5, RenderDataType.Meteorito1, BodyDataType.Meteorite, SoundDataType.Meteorite, Configs.meteorite, RewardDataType.MediumFuel, ParticleDataType.Meteorite)),//8
    Meteorito2(new ScenarioData(5, RenderDataType.Meteorito2, BodyDataType.Meteorite, SoundDataType.Meteorite, Configs.meteorite, null, null)),//9
    Meteorito3(new ScenarioData(5, RenderDataType.Meteorito3, BodyDataType.Meteorite, SoundDataType.Meteorite, Configs.meteorite, RewardDataType.SmallLife, ParticleDataType.Meteorite)),//10

    Heart(new ScenarioData(RenderDataType.Heart)),//11
    Meteorito4(new ScenarioData(RenderDataType.Meteorito4)),//12
    Planeta8(new ScenarioData(RenderDataType.Planeta9)),//13
    Planeta9(new ScenarioData(RenderDataType.Planeta10)),//14

    Ship1(new ScenarioData(RenderDataType.Ship1)),//15
    Ship2(new ScenarioData(RenderDataType.Ship2)),//16
    Ship3(new ScenarioData(RenderDataType.Ship3)),//17
    Ship4(new ScenarioData(RenderDataType.Ship4)),//18
    Ship5(new ScenarioData(15, RenderDataType.Ship5, BodyDataType.Meteorite, SoundDataType.Meteorite, null, RewardDataType.FuelRegenerate, ParticleDataType.Meteorite)),//19
    Ship6(new ScenarioData(RenderDataType.Ship6)),//20
    Ship7(new ScenarioData(15, RenderDataType.Ship7, BodyDataType.Meteorite, SoundDataType.Meteorite, null, RewardDataType.RapidFire, ParticleDataType.Meteorite)),//21
    Ship8(new ScenarioData(RenderDataType.Ship8)),//22
    Ship9(new ScenarioData(RenderDataType.Ship9)),//23
    Ship10(new ScenarioData(RenderDataType.Ship10)),//24
    Ship11(new ScenarioData(RenderDataType.Ship11)),//25
    Cloud(new ScenarioData(RenderDataType.SpaceCloud)),//26
    Cloud1(new ScenarioData(RenderDataType.SpaceCloud)),//27
    Cloud2(new ScenarioData(RenderDataType.SpaceCloud));//28

    private final ScenarioData data;

    private ScenarioDataType(
            ScenarioData data)
    {
        this.data = data;
    }

    public ScenarioData get()
    {
        return data;
    }
}
