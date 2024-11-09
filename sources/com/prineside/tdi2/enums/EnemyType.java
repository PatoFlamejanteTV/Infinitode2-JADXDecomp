package com.prineside.tdi2.enums;

import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enums/EnemyType.class */
public enum EnemyType {
    REGULAR,
    FAST,
    STRONG,
    HELI,
    JET,
    ARMORED,
    HEALER,
    TOXIC,
    ICY,
    FIGHTER,
    LIGHT,
    GENERIC,
    BOSS,
    SNAKE_BOSS_HEAD,
    SNAKE_BOSS_BODY,
    SNAKE_BOSS_TAIL,
    BROOT_BOSS,
    CONSTRUCTOR_BOSS,
    MOBCHAIN_BOSS_HEAD,
    MOBCHAIN_BOSS_BODY,
    MOBCHAIN_BOSS_CREEP,
    METAPHOR_BOSS,
    METAPHOR_BOSS_CREEP;

    public static final EnemyType[] values = values();
    public static final EnemyType[] mainEnemyTypes = {REGULAR, FAST, STRONG, HELI, JET, ARMORED, HEALER, TOXIC, ICY, FIGHTER, LIGHT, BOSS};

    public static EnemyType getMainEnemyType(EnemyType enemyType) {
        if (isBoss(enemyType)) {
            return BOSS;
        }
        return enemyType;
    }

    public static boolean isBoss(EnemyType enemyType) {
        return enemyType.ordinal() >= BOSS.ordinal();
    }
}
