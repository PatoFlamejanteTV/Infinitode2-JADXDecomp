package com.a.a.c.m;

import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/c/m/m.class */
public final class m implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private final Class<Enum<?>> f733a;

    /* renamed from: b, reason: collision with root package name */
    private final com.a.a.b.r[] f734b;

    private m(Class<Enum<?>> cls, com.a.a.b.r[] rVarArr) {
        this.f733a = cls;
        cls.getEnumConstants();
        this.f734b = rVarArr;
    }

    public static m a(com.a.a.c.b.q<?> qVar, Class<Enum<?>> cls) {
        Class<? extends Enum<?>> l = i.l(cls);
        Enum<?>[] enumArr = (Enum[]) l.getEnumConstants();
        if (enumArr != null) {
            String[] a2 = qVar.j().a(l, enumArr, new String[enumArr.length]);
            com.a.a.b.r[] rVarArr = new com.a.a.b.r[enumArr.length];
            int length = enumArr.length;
            for (int i = 0; i < length; i++) {
                Enum<?> r0 = enumArr[i];
                String str = a2[i];
                String str2 = str;
                if (str == null) {
                    str2 = r0.name();
                }
                rVarArr[r0.ordinal()] = com.a.a.c.b.q.a(str2);
            }
            return a(cls, rVarArr);
        }
        throw new IllegalArgumentException("Cannot determine enum constants for Class " + cls.getName());
    }

    private static m a(Class<Enum<?>> cls, com.a.a.b.r[] rVarArr) {
        return new m(cls, rVarArr);
    }

    public final com.a.a.b.r a(Enum<?> r4) {
        return this.f734b[r4.ordinal()];
    }

    public final Class<Enum<?>> a() {
        return this.f733a;
    }
}
