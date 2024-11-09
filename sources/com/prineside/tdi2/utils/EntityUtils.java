package com.prineside.tdi2.utils;

import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.Enemy;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/EntityUtils.class */
public class EntityUtils {
    public static void removeNullRefs(Array<Enemy.EnemyReference> array) {
        for (int i = array.size - 1; i >= 0; i--) {
            if (array.items[i].enemy == null) {
                array.removeIndex(i);
            }
        }
    }

    public static boolean removeByValue(Array<Enemy.EnemyReference> array, Enemy enemy) {
        for (int i = 0; i < array.size; i++) {
            if (array.items[i].enemy == enemy) {
                array.removeIndex(i);
                return true;
            }
        }
        return false;
    }
}
