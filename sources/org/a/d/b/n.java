package org.a.d.b;

import java.io.IOException;
import java.util.Calendar;

/* loaded from: infinitode-2.jar:org/a/d/b/n.class */
public final class n extends c {
    private static boolean b(Object obj) {
        if (obj instanceof Calendar) {
            return true;
        }
        if (obj instanceof String) {
            try {
                org.a.d.a.a((String) obj);
                return true;
            } catch (IOException unused) {
                return false;
            }
        }
        return false;
    }

    @Override // org.a.d.b.c
    public final void a(Object obj) {
        if (!b(obj)) {
            if (obj == null) {
                throw new IllegalArgumentException("Value null is not allowed for the Date type");
            }
            throw new IllegalArgumentException("Value given is not allowed for the Date type: " + obj.getClass() + ", value: " + obj);
        }
        if (obj instanceof String) {
            throw null;
        }
        throw null;
    }

    @Override // org.a.d.b.c
    public final String a() {
        throw null;
    }
}
