package com.ugg.gdxjam.model.data;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pools;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.Weapon;
import com.ugg.gdxjam.model.components.DamageComponent;
import com.ugg.gdxjam.model.components.IntervalComponent;
import com.ugg.gdxjam.model.components.WeaponsComponent;
import com.ugg.gdxjam.model.enums.BulletDataType;

/**
 * Created by Jose Cuellar on 05/01/2016.
 */
public class WeaponData implements GameData{
    Weapon weapon;
    BulletDataType bulletType;
    public float attackInterval = 1f;
    public int damage = 1;
    public float multiplier = 1f;

    public WeaponData(Weapon weapon, BulletDataType bulletType, float attackInterval, int damage,
                      float multiplier) {
        this.weapon = weapon;
        this.bulletType = bulletType;
        this.attackInterval = attackInterval;
        this.damage = damage;
        this.multiplier = multiplier;
    }

    @Override
    public void assignComponents(GameEntity gameEntity) {
        DamageComponent dmgC = gameEntity.addComponent(DamageComponent.class);
        dmgC.damage = damage;
        dmgC.multiplier = multiplier;

        WeaponsComponent weaponC = Mappers.weapon.get(gameEntity.entity);
        if(weaponC == null)
            weaponC = gameEntity.addComponent(WeaponsComponent.class);
        Weapon weapon = this.weapon.cloneObject();
        weaponC.weapons.add(weapon);
        weapon.engineCtrl = gameEntity.engineCtrl;
        weapon.parent = gameEntity;
        weapon.bulletDataType = bulletType;
        weapon.interval.limit = attackInterval;

    }
}
