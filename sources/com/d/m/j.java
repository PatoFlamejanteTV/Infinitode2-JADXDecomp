package com.d.m;

import com.d.m.i;

/* loaded from: infinitode-2.jar:com/d/m/j.class */
class j extends ThreadLocal<i.a> {
    @Override // java.lang.ThreadLocal
    protected final /* synthetic */ i.a initialValue() {
        return a();
    }

    private static i.a a() {
        return new i.a((byte) 0);
    }
}
