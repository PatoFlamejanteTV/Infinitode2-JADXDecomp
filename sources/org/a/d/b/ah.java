package org.a.d.b;

/* loaded from: infinitode-2.jar:org/a/d/b/ah.class */
public final class ah extends c {
    @Override // org.a.d.b.c
    public final void a(Object obj) {
        if (obj instanceof Float) {
            ((Float) obj).floatValue();
            throw null;
        }
        if (!(obj instanceof String)) {
            throw new IllegalArgumentException("Value given is not allowed for the Real type: " + obj);
        }
        Float.valueOf((String) obj).floatValue();
        throw null;
    }

    @Override // org.a.d.b.c
    public final String a() {
        throw null;
    }
}
