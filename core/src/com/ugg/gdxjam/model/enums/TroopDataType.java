package com.ugg.gdxjam.model.enums;

import com.badlogic.gdx.utils.ObjectMap;
import com.ugg.gdxjam.controller.PhysicsController;
import com.ugg.gdxjam.model.configs.Configs;
import com.ugg.gdxjam.model.data.BodyData;
import com.ugg.gdxjam.model.data.TroopData;

public enum TroopDataType {

    MineEnemy,
    MotherShipAttackerEnemy,
    EnemyCommonShip,
    EnemyWatcher,
    EnemyTriple,
    MotherShipEnemy,
    MainShip;

    static ObjectMap<TroopDataType, TroopData> troopsData;

    public static void init() {
        troopsData = new ObjectMap<>();
        troopsData.put(MineEnemy, new TroopData(3, RenderDataType.EnemyMine, BodyDataType.EnemyMine, AIType.None, Configs.enemyMine, null, SoundDataType.EnemyShip, null, ParticleDataType.Enemy, null));
        troopsData.put(MotherShipAttackerEnemy, new TroopData(4, RenderDataType.EnemyAttacker, BodyDataType.NormalShipBody, AIType.Fsm, Configs.mothershipAttacker, SensorType.NormalEnemySensor, SoundDataType.EnemyShip, RewardDataType.SmallLife, ParticleDataType.Enemy, WeaponDataType.LinearEnemy));
        troopsData.put(EnemyCommonShip, new TroopData(7, RenderDataType.EnemyShip, BodyDataType.NormalShipBody, AIType.Fsm, Configs.normalEnemy, SensorType.NormalEnemySensor, SoundDataType.EnemyShip, RewardDataType.SmallFuel, ParticleDataType.Enemy, WeaponDataType.LinearEnemy));
        troopsData.put(EnemyWatcher, new TroopData(14, RenderDataType.EnemyWatcher, BodyDataType.NormalShipBody, AIType.Fsm, Configs.watcherEnemy, SensorType.WatcherEnemySensor, SoundDataType.EnemyShip, RewardDataType.LargeLife, ParticleDataType.Enemy, WeaponDataType.ImpulseEnemy));
        troopsData.put(EnemyTriple, new TroopData(11, RenderDataType.EnemyTriple, BodyDataType.NormalShipBody, AIType.Fsm, Configs.tripleEnemy, SensorType.TripleEnemySensor, SoundDataType.EnemyShip, RewardDataType.MediumLife, ParticleDataType.Enemy, WeaponDataType.TripleEnemy));
        troopsData.put(MotherShipEnemy, new TroopData(130, RenderDataType.MotherShip, BodyDataType.MotherShip, AIType.Fsm, Configs.mothership, SensorType.MotherShipSensor, SoundDataType.EnemyShip, RewardDataType.MotherShipReward, ParticleDataType.MotherShip, WeaponDataType.MultiDirectionMotherShip, WeaponDataType.SpawnMinesEnemy, WeaponDataType.SpawnAttackerEnemy));
        troopsData.put(MainShip, new TroopData(Configs.mainShip.shipLife, RenderDataType.MainShip, BodyDataType.NormalPlayerBody, AIType.Fsm, Configs.mainShip, null, SoundDataType.PlayerShip, null, ParticleDataType.MainShip, WeaponDataType.LinearMain));

    }

    public static TroopData get(TroopDataType type) {
        return troopsData.get(type);
    }


}
