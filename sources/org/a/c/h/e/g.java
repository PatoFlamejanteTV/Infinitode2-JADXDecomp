package org.a.c.h.e;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: infinitode-2.jar:org/a/c/h/e/g.class */
public final class g {

    /* renamed from: a, reason: collision with root package name */
    private final Map<i, SoftReference<org.a.b.b>> f4539a = new ConcurrentHashMap();

    public final void a(i iVar, org.a.b.b bVar) {
        this.f4539a.put(iVar, new SoftReference<>(bVar));
    }

    public final org.a.b.b a(i iVar) {
        SoftReference<org.a.b.b> softReference = this.f4539a.get(iVar);
        if (softReference != null) {
            return softReference.get();
        }
        return null;
    }
}
