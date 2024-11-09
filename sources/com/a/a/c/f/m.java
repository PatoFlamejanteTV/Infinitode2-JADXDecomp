package com.a.a.c.f;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/f/m.class */
public final class m implements Iterable<k> {

    /* renamed from: a, reason: collision with root package name */
    private Map<z, k> f461a;

    public m() {
    }

    public m(Map<z, k> map) {
        this.f461a = map;
    }

    public final k a(String str, Class<?>[] clsArr) {
        if (this.f461a == null) {
            return null;
        }
        return this.f461a.get(new z(str, clsArr));
    }

    @Override // java.lang.Iterable
    public final Iterator<k> iterator() {
        if (this.f461a == null) {
            return Collections.emptyIterator();
        }
        return this.f461a.values().iterator();
    }
}
