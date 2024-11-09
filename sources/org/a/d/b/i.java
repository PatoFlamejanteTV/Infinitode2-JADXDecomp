package org.a.d.b;

/* loaded from: infinitode-2.jar:org/a/d/b/i.class */
public final class i extends c {
    @Override // org.a.d.b.c
    public final void a(Object obj) {
        if (obj instanceof Boolean) {
            ((Boolean) obj).booleanValue();
            throw null;
        }
        if (obj instanceof String) {
            String upperCase = obj.toString().trim().toUpperCase();
            if ("TRUE".equals(upperCase)) {
                throw null;
            }
            if (!"FALSE".equals(upperCase)) {
                throw new IllegalArgumentException("Not a valid boolean value : '" + obj + "'");
            }
            throw null;
        }
        throw new IllegalArgumentException("Value given is not allowed for the Boolean type.");
    }

    @Override // org.a.d.b.c
    public final String a() {
        throw null;
    }
}
