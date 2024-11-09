package com.a.a.b.g;

import java.lang.ref.SoftReference;

/* loaded from: infinitode-2.jar:com/a/a/b/g/b.class */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    private static final p f152a;

    /* renamed from: b, reason: collision with root package name */
    private static ThreadLocal<SoftReference<a>> f153b;

    static {
        boolean z = false;
        try {
            z = "true".equals(System.getProperty("com.fasterxml.jackson.core.util.BufferRecyclers.trackReusableBuffers"));
        } catch (SecurityException unused) {
        }
        f152a = z ? p.a() : null;
        f153b = new ThreadLocal<>();
    }

    public static a a() {
        SoftReference<a> softReference;
        SoftReference<a> softReference2 = f153b.get();
        a aVar = softReference2 == null ? null : softReference2.get();
        a aVar2 = aVar;
        if (aVar == null) {
            aVar2 = new a();
            if (f152a != null) {
                softReference = f152a.a(aVar2);
            } else {
                softReference = new SoftReference<>(aVar2);
            }
            f153b.set(softReference);
        }
        return aVar2;
    }
}
