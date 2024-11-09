package com.d.b;

import com.d.i.a.r;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/d/b/d.class */
class d extends LinkedHashMap<String, r> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public d(c cVar, int i, float f, boolean z) {
        super(16, 0.75f, true);
    }

    @Override // java.util.LinkedHashMap
    protected final boolean removeEldestEntry(Map.Entry<String, r> entry) {
        return size() > 16;
    }
}
