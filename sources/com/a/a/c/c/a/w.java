package com.a.a.c.c.a;

import com.a.a.a.al;
import com.a.a.a.am;

/* loaded from: infinitode-2.jar:com/a/a/c/c/a/w.class */
public final class w extends am.c {
    public w(Class<?> cls) {
        super(cls);
    }

    @Override // com.a.a.a.al
    public final Object b(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.a.a.a.al
    public final al<Object> a(Class<?> cls) {
        return cls == this.f49a ? this : new w(cls);
    }

    @Override // com.a.a.a.al
    public final al<Object> b() {
        return this;
    }

    @Override // com.a.a.a.al
    public final al.a a(Object obj) {
        if (obj == null) {
            return null;
        }
        return new al.a(getClass(), this.f49a, obj);
    }
}
