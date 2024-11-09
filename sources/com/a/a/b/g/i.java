package com.a.a.b.g;

import com.a.a.b.g.h;

/* loaded from: infinitode-2.jar:com/a/a/b/g/i.class */
public final class i<F extends h> {

    /* renamed from: a, reason: collision with root package name */
    private int f161a;

    private i(int i) {
        this.f161a = i;
    }

    public static <F extends h> i<F> a(F[] fArr) {
        if (fArr.length > 31) {
            throw new IllegalArgumentException(String.format("Can not use type `%s` with JacksonFeatureSet: too many entries (%d > 31)", fArr[0].getClass().getName(), Integer.valueOf(fArr.length)));
        }
        int i = 0;
        for (F f : fArr) {
            if (f.a()) {
                i |= f.b();
            }
        }
        return new i<>(i);
    }

    public final i<F> a(F f) {
        int b2 = this.f161a | f.b();
        return b2 == this.f161a ? this : new i<>(b2);
    }

    public final boolean b(F f) {
        return (f.b() & this.f161a) != 0;
    }
}
