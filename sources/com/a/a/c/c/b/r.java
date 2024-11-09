package com.a.a.c.c.b;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b/r.class */
public final class r {

    /* renamed from: a, reason: collision with root package name */
    private static final HashSet<String> f360a = new HashSet<>();

    static {
        Class[] clsArr = {UUID.class, AtomicBoolean.class, AtomicInteger.class, AtomicLong.class, StackTraceElement.class, ByteBuffer.class, Void.class};
        for (int i = 0; i < 7; i++) {
            f360a.add(clsArr[i].getName());
        }
        Class<?>[] g = q.g();
        for (int i2 = 0; i2 < 14; i2++) {
            f360a.add(g[i2].getName());
        }
    }

    public static com.a.a.c.k<?> a(com.a.a.c.g gVar, Class<?> cls, String str) {
        if (f360a.contains(str)) {
            q<?> a2 = q.a(cls);
            if (a2 != null) {
                return a2;
            }
            if (cls == UUID.class) {
                return new ap();
            }
            if (cls == StackTraceElement.class) {
                return ac.d(gVar);
            }
            if (cls == AtomicBoolean.class) {
                return new b();
            }
            if (cls == AtomicInteger.class) {
                return new c();
            }
            if (cls == AtomicLong.class) {
                return new d();
            }
            if (cls == ByteBuffer.class) {
                return new g();
            }
            if (cls == Void.class) {
                return w.f370a;
            }
            return null;
        }
        return null;
    }
}
