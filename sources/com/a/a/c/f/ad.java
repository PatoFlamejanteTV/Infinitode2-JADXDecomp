package com.a.a.c.f;

/* loaded from: infinitode-2.jar:com/a/a/c/f/ad.class */
public final class ad {

    /* renamed from: a, reason: collision with root package name */
    private com.a.a.c.w f427a;

    /* renamed from: b, reason: collision with root package name */
    private Class<? extends com.a.a.a.al<?>> f428b;
    private Class<? extends com.a.a.a.an> c;
    private Class<?> d;
    private boolean e;
    private static final ad f = new ad(com.a.a.c.w.f771b, Object.class, null, false, null);

    public ad(com.a.a.c.w wVar, Class<?> cls, Class<? extends com.a.a.a.al<?>> cls2, Class<? extends com.a.a.a.an> cls3) {
        this(wVar, cls, cls2, false, cls3);
    }

    /* JADX WARN: Incorrect type for immutable var: ssa=java.lang.Class<? extends com.a.a.a.an>, code=java.lang.Class, for r8v0, types: [java.lang.Class<? extends com.a.a.a.an>] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private ad(com.a.a.c.w r4, java.lang.Class<?> r5, java.lang.Class<? extends com.a.a.a.al<?>> r6, boolean r7, java.lang.Class r8) {
        /*
            r3 = this;
            r0 = r3
            r0.<init>()
            r0 = r3
            r1 = r4
            r0.f427a = r1
            r0 = r3
            r1 = r5
            r0.d = r1
            r0 = r3
            r1 = r6
            r0.f428b = r1
            r0 = r3
            r1 = r7
            r0.e = r1
            r0 = r8
            if (r0 != 0) goto L22
            java.lang.Class<com.a.a.a.aq> r0 = com.a.a.a.aq.class
            r8 = r0
        L22:
            r0 = r3
            r1 = r8
            r0.c = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.c.f.ad.<init>(com.a.a.c.w, java.lang.Class, java.lang.Class, boolean, java.lang.Class):void");
    }

    public static ad a() {
        return f;
    }

    public final ad a(boolean z) {
        if (this.e == z) {
            return this;
        }
        return new ad(this.f427a, this.d, this.f428b, z, this.c);
    }

    public final com.a.a.c.w b() {
        return this.f427a;
    }

    public final Class<?> c() {
        return this.d;
    }

    public final Class<? extends com.a.a.a.al<?>> d() {
        return this.f428b;
    }

    public final Class<? extends com.a.a.a.an> e() {
        return this.c;
    }

    public final boolean f() {
        return this.e;
    }

    public final String toString() {
        return "ObjectIdInfo: propName=" + this.f427a + ", scope=" + com.a.a.c.m.i.g(this.d) + ", generatorType=" + com.a.a.c.m.i.g(this.f428b) + ", alwaysAsId=" + this.e;
    }
}
