package com.a.a.c.m;

import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/c/m/af.class */
public class af implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private static af f710a = new af();

    public boolean a(Class<?> cls) {
        return false;
    }

    public static af a(Class<?>[] clsArr) {
        if (clsArr == null) {
            return f710a;
        }
        switch (clsArr.length) {
            case 0:
                return f710a;
            case 1:
                return new b(clsArr[0]);
            default:
                return new a(clsArr);
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/m/af$b.class */
    static final class b extends af {

        /* renamed from: a, reason: collision with root package name */
        private final Class<?> f712a;

        public b(Class<?> cls) {
            this.f712a = cls;
        }

        @Override // com.a.a.c.m.af
        public final boolean a(Class<?> cls) {
            return cls == this.f712a || this.f712a.isAssignableFrom(cls);
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/m/af$a.class */
    static final class a extends af implements Serializable {

        /* renamed from: a, reason: collision with root package name */
        private final Class<?>[] f711a;

        public a(Class<?>[] clsArr) {
            this.f711a = clsArr;
        }

        @Override // com.a.a.c.m.af
        public final boolean a(Class<?> cls) {
            int length = this.f711a.length;
            for (int i = 0; i < length; i++) {
                Class<?> cls2 = this.f711a[i];
                if (cls == cls2 || cls2.isAssignableFrom(cls)) {
                    return true;
                }
            }
            return false;
        }
    }
}
