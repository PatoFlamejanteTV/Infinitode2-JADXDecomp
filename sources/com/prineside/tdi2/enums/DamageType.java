package com.prineside.tdi2.enums;

import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enums/DamageType.class */
public enum DamageType {
    BULLET,
    FIRE,
    POISON,
    EXPLOSION,
    ELECTRICITY,
    LASER;

    public static final DamageType[] values = values();

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/enums/DamageType$Efficiency.class */
    public static final class Efficiency {
        public static final int CRITICAL_BIT = 1;
        public static final int EFFECTIVE_BIT = 2;
        public static final int OVER_TIME_BIT = 4;
        public static final int ESPECIALLY_EFFECTIVE_BIT = 8;
        public static final int WEAK_BIT = 16;
        public static final int NORMAL = 0;
        public static final int CRITICAL = 1;
        public static final int OVER_TIME = 4;
        public static final int ESPECIALLY_EFFECTIVE = 8;

        public static boolean isNormal(int i) {
            return i == 0;
        }

        public static boolean isCritical(int i) {
            return (i & 1) == 1;
        }

        public static boolean isEffective(int i) {
            return (i & 2) == 2;
        }

        public static boolean isWeak(int i) {
            return (i & 16) == 16;
        }

        public static boolean isOverTime(int i) {
            return (i & 4) == 4;
        }

        public static boolean isEspeciallyEffective(int i) {
            return (i & 8) == 8;
        }
    }
}
