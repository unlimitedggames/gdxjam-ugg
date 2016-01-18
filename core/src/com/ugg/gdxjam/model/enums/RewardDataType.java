package com.ugg.gdxjam.model.enums;

import com.ugg.gdxjam.model.data.RewardData;
import com.ugg.gdxjam.model.rewards.*;

/**
 * Created by Jose Cuellar on 11/01/2016.
 */
public enum RewardDataType {

    SmallLife(new RewardData(RenderDataType.SmallLifePowerUp, BodyDataType.NormalPlayerBody, new LifeReward(3), SoundDataType.Reward)),
    MediumLife(new RewardData(RenderDataType.MediumLifePowerUp, BodyDataType.NormalPlayerBody, new LifeReward(6), SoundDataType.Reward)),
    LargeLife(new RewardData(RenderDataType.LargeLifePowerUp, BodyDataType.NormalPlayerBody, new LifeReward(10), SoundDataType.Reward)),
    SmallFuel(new RewardData(RenderDataType.SmallFuelPowerUp, BodyDataType.NormalPlayerBody, new FuelReward(50), SoundDataType.Reward)),
    MediumFuel(new RewardData(RenderDataType.MediumFuelPowerUp, BodyDataType.NormalPlayerBody, new FuelReward(150), SoundDataType.Reward)),
    MotherShipReward(new RewardData(RenderDataType.MotherShipPowerUp, BodyDataType.NormalPlayerBody, new MotherShipReward(), SoundDataType.SpecialReward)),
    FuelRegenerate(new RewardData(RenderDataType.FuelRegenerate, BodyDataType.NormalPlayerBody, new FuelRegenerateReward(1), SoundDataType.SpecialReward)),
    RapidFire(new RewardData(RenderDataType.RapidFirePowerUp, BodyDataType.NormalPlayerBody, new RapidFireReward(0.02f), SoundDataType.SpecialReward));

    private final RewardData data;

    private RewardDataType(
            RewardData data)
    {
        this.data = data;
    }

    public RewardData get()
    {
        return data;
    }
}
