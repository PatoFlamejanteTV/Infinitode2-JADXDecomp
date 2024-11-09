package com.prineside.tdi2.configs;

import com.badlogic.gdx.math.RandomXS128;
import com.prineside.tdi2.utils.StringFormatter;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/configs/InventoryItemCountLimits.class */
public final class InventoryItemCountLimits {
    public static final int UNIQUE_SOURCE_TILES = 500;
    public static final int UNIQUE_SPAWN_TILES = 500;
    public static final int UNIQUE_TARGET_TILES = 500;
    public static final int UNIQUE_MUSIC_TILES = 50;

    private InventoryItemCountLimits() {
    }

    public static void main(String[] strArr) {
        int i = 0;
        for (int i2 = 0; i2 < 50; i2++) {
            RandomXS128 randomXS128 = new RandomXS128();
            for (int i3 = 0; i3 < 100; i3++) {
                for (int i4 = 0; i4 < 60; i4++) {
                    if (i4 > 3) {
                        for (int i5 = 0; i5 < 60; i5++) {
                            if (randomXS128.nextDouble() < 1.2E-4d) {
                                i++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("after 100 in-game hours avg: 1 bird every " + ((Object) StringFormatter.compactNumber(100.0f / (i / 50), true)) + "h");
    }
}
