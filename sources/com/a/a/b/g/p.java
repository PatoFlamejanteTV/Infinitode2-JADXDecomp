package com.a.a.b.g;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: infinitode-2.jar:com/a/a/b/g/p.class */
final class p {

    /* renamed from: a, reason: collision with root package name */
    private final Map<SoftReference<com.a.a.b.g.a>, Boolean> f166a;

    /* renamed from: b, reason: collision with root package name */
    private final ReferenceQueue<com.a.a.b.g.a> f167b;

    /* loaded from: infinitode-2.jar:com/a/a/b/g/p$a.class */
    static final class a {

        /* renamed from: a, reason: collision with root package name */
        static final p f168a = new p();
    }

    p() {
        new Object();
        this.f166a = new ConcurrentHashMap();
        this.f167b = new ReferenceQueue<>();
    }

    public static p a() {
        return a.f168a;
    }

    public final SoftReference<com.a.a.b.g.a> a(com.a.a.b.g.a aVar) {
        SoftReference<com.a.a.b.g.a> softReference = new SoftReference<>(aVar, this.f167b);
        this.f166a.put(softReference, Boolean.TRUE);
        b();
        return softReference;
    }

    private void b() {
        while (true) {
            SoftReference softReference = (SoftReference) this.f167b.poll();
            if (softReference != null) {
                this.f166a.remove(softReference);
            } else {
                return;
            }
        }
    }
}
