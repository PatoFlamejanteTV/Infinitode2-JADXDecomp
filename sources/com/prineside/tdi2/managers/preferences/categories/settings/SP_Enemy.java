package com.prineside.tdi2.managers.preferences.categories.settings;

import com.badlogic.gdx.utils.ObjectSet;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.managers.preferences.PrefMap;
import com.prineside.tdi2.managers.preferences.PrefSubcategory;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/categories/settings/SP_Enemy.class */
public final class SP_Enemy implements PrefSubcategory {
    public final ObjectSet<EnemyType> enemiesMetWith = new ObjectSet<>();

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final void load(PrefMap prefMap) {
        this.enemiesMetWith.clear();
        String str = prefMap.get("enemyTypesMetWithCrossProgress", null);
        if (str != null) {
            try {
                for (String str2 : str.split(",")) {
                    if (str2.length() != 0) {
                        this.enemiesMetWith.add(EnemyType.valueOf(str2));
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final synchronized void save(PrefMap prefMap) {
        if (this.enemiesMetWith.size != 0) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            ObjectSet.ObjectSetIterator<EnemyType> it = this.enemiesMetWith.iterator();
            while (it.hasNext()) {
                sb.append(it.next().name());
                i++;
                if (i != this.enemiesMetWith.size) {
                    sb.append(",");
                }
            }
            prefMap.set("enemyTypesMetWithCrossProgress", sb.toString());
        }
    }
}
