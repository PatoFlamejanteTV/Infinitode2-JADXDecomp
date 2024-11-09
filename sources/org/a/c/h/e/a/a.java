package org.a.c.h.e.a;

import java.util.Map;

/* loaded from: infinitode-2.jar:org/a/c/h/e/a/a.class */
public final class a extends c {
    public a(Map<Integer, String> map) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            a(entry.getKey().intValue(), entry.getValue());
        }
    }

    @Override // org.a.c.h.a.c
    public final org.a.c.b.b f() {
        throw new UnsupportedOperationException("Built-in encodings cannot be serialized");
    }

    @Override // org.a.c.h.e.a.c
    public final String a() {
        return "built-in (TTF)";
    }
}
