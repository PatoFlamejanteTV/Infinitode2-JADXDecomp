package com.prineside.tdi2.utils.syncchecker;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.StringBuilder;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/DeepClassComparisonConfig.class */
public class DeepClassComparisonConfig {

    /* renamed from: a, reason: collision with root package name */
    final Array<String> f3975a = new Array<>(true, 1, String.class);
    public final StringBuilder sb = new StringBuilder();
    public int depth = 8;
    public final IdentityHashMap<Object, Set<Object>> comparesMap = new IdentityHashMap<>();
    public boolean debug;
    public Enum<?>[] keyEnum;

    public StringBuilder appendPrefix() {
        for (int i = 0; i < this.f3975a.size; i++) {
            this.sb.append(this.f3975a.items[i]);
        }
        return this.sb;
    }

    public DeepClassComparisonConfig addPrefix(String... strArr) {
        this.f3975a.addAll(strArr);
        return this;
    }

    public void popPrefix(int i) {
        this.f3975a.removeRange(this.f3975a.size - i, this.f3975a.size - 1);
    }

    public void free() {
        Iterator<Map.Entry<Object, Set<Object>>> it = this.comparesMap.entrySet().iterator();
        while (it.hasNext()) {
            Set<Object> value = it.next().getValue();
            if (value != null) {
                SyncChecker.f3977a.free(value);
            }
        }
        this.comparesMap.clear();
    }
}
