package com.prineside.tdi2.enums;

import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enums/TowerStatType.class */
public enum TowerStatType {
    RANGE,
    MIN_RANGE,
    DAMAGE,
    ATTACK_SPEED,
    ROTATION_SPEED,
    PROJECTILE_SPEED,
    AIM_SPEED,
    CHARGING_SPEED,
    FREEZE_PERCENT,
    FREEZE_SPEED,
    STUN_CHANCE,
    CHAIN_LIGHTNING_DAMAGE,
    RESOURCE_CONSUMPTION,
    DURATION,
    PRICE,
    STARTING_LEVEL,
    STARTING_POWER,
    MAX_EXP_LEVEL,
    MAX_UPGRADE_LEVEL,
    EXPERIENCE_MULTIPLIER,
    EXPERIENCE_GENERATION,
    UPGRADE_PRICE,
    U_PIERCING,
    U_DAMAGE_MULTIPLY,
    U_CRIT_CHANCE,
    U_CRIT_MULTIPLIER,
    U_EXPLOSION_RANGE,
    U_POISON_DURATION_BONUS,
    U_CHAIN_LIGHTNING_BONUS_LENGTH,
    U_POISON_DURATION,
    U_PROJECTILE_COUNT,
    U_STUN_DURATION,
    U_QUAKE_CHARGE_SPEED,
    U_BURN_CHANCE,
    U_BURN_DAMAGE,
    U_ACCELERATION,
    U_SHOOT_ANGLE,
    U_CHAIN_LIGHTNING_LENGTH,
    U_LRM_AIM_SPEED,
    U_BURNING_TIME,
    U_BATTERIES_CAPACITY,
    U_BONUS_COINS,
    U_BONUS_EXPERIENCE,
    U_SHARED_DAMAGE,
    U_DIRECT_FIRE_DAMAGE,
    U_MAGAZINE_SIZE;

    public static final TowerStatType[] values = values();
    public static final TowerStatType[] defaultValues;
    public static final TowerStatType[] uniqueValues;

    /* JADX WARN: Multi-variable type inference failed */
    static {
        Array array = new Array();
        Array array2 = new Array();
        for (TowerStatType towerStatType : values) {
            if (towerStatType.name().startsWith("U_")) {
                array2.add(towerStatType);
            } else {
                array.add(towerStatType);
            }
        }
        defaultValues = new TowerStatType[array.size];
        uniqueValues = new TowerStatType[array2.size];
        int i = 0;
        Array.ArrayIterator it = array.iterator();
        while (it.hasNext()) {
            defaultValues[i] = (TowerStatType) it.next();
            i++;
        }
        int i2 = 0;
        Array.ArrayIterator it2 = array2.iterator();
        while (it2.hasNext()) {
            uniqueValues[i2] = (TowerStatType) it2.next();
            i2++;
        }
    }
}
