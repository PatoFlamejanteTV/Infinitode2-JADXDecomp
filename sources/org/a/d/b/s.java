package org.a.d.b;

/* loaded from: infinitode-2.jar:org/a/d/b/s.class */
public final class s extends c {
    @Override // org.a.d.b.c
    public final void a(Object obj) {
        if (obj instanceof Integer) {
            ((Integer) obj).intValue();
            throw null;
        }
        if (!(obj instanceof String)) {
            throw new IllegalArgumentException("Value given is not allowed for the Integer type: " + obj);
        }
        Integer.parseInt((String) obj);
        throw null;
    }

    @Override // org.a.d.b.c
    public final String a() {
        throw null;
    }
}
