package org.a.b.d;

import java.util.Map;

/* loaded from: infinitode-2.jar:org/a/b/d/a.class */
public final class a extends b {
    public a(Map<Integer, String> map) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            b(entry.getKey().intValue(), entry.getValue());
        }
    }
}
