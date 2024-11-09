package com.a.a.c.m;

import java.lang.reflect.Array;

/* loaded from: infinitode-2.jar:com/a/a/c/m/d.class */
class d {

    /* renamed from: a, reason: collision with root package name */
    private /* synthetic */ Class f715a;

    /* renamed from: b, reason: collision with root package name */
    private /* synthetic */ int f716b;
    private /* synthetic */ Object c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(Class cls, int i, Object obj) {
        this.f715a = cls;
        this.f716b = i;
        this.c = obj;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!i.a(obj, (Class<?>) this.f715a) || Array.getLength(obj) != this.f716b) {
            return false;
        }
        for (int i = 0; i < this.f716b; i++) {
            Object obj2 = Array.get(this.c, i);
            Object obj3 = Array.get(obj, i);
            if (obj2 != obj3 && obj2 != null && !obj2.equals(obj3)) {
                return false;
            }
        }
        return true;
    }
}
