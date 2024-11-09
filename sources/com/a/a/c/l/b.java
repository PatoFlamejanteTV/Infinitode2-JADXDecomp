package com.a.a.c.l;

import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/c/l/b.class */
public final class b implements Serializable, Comparable<b> {

    /* renamed from: a, reason: collision with root package name */
    private String f653a;

    /* renamed from: b, reason: collision with root package name */
    private Class<?> f654b;
    private int c;

    public b() {
        this.f654b = null;
        this.f653a = null;
        this.c = 0;
    }

    public b(Class<?> cls) {
        this.f654b = cls;
        this.f653a = cls.getName();
        this.c = this.f653a.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(b bVar) {
        return this.f653a.compareTo(bVar.f653a);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && obj.getClass() == getClass() && ((b) obj).f654b == this.f654b;
    }

    public final int hashCode() {
        return this.c;
    }

    public final String toString() {
        return this.f653a;
    }
}
