package com.prineside.tdi2.managers.preferences.categories.progress;

import com.badlogic.gdx.utils.ObjectSet;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.managers.preferences.PrefMap;
import com.prineside.tdi2.managers.preferences.PrefSubcategory;
import com.prineside.tdi2.utils.logging.TLog;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/categories/progress/PP_Enemy.class */
public final class PP_Enemy implements PrefSubcategory {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2527a = TLog.forClass(PP_Enemy.class);

    /* renamed from: b, reason: collision with root package name */
    private final ObjectSet<EnemyType> f2528b = new ObjectSet<>();

    public final boolean isEnemyMetWith(EnemyType enemyType) {
        return this.f2528b.contains(enemyType);
    }

    public final synchronized void setEnemyMetWith(EnemyType enemyType, boolean z) {
        if (z) {
            this.f2528b.add(enemyType);
        } else {
            this.f2528b.remove(enemyType);
        }
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final synchronized void load(PrefMap prefMap) {
        this.f2528b.clear();
        String str = prefMap.get("enemyTypesMetWith", null);
        if (str != null) {
            try {
                for (String str2 : str.split(",")) {
                    if (str2.length() != 0) {
                        this.f2528b.add(EnemyType.valueOf(str2));
                    }
                }
            } catch (Exception e) {
                f2527a.e("Failed loading info about enemy types met with: " + str, e);
            }
        }
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final synchronized void save(PrefMap prefMap) {
        if (this.f2528b.size != 0) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            ObjectSet.ObjectSetIterator<EnemyType> it = this.f2528b.iterator();
            while (it.hasNext()) {
                sb.append(it.next().name());
                i++;
                if (i != this.f2528b.size) {
                    sb.append(",");
                }
            }
            prefMap.set("enemyTypesMetWith", sb.toString());
        }
    }
}
