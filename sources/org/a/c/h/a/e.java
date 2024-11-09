package org.a.c.h.a;

/* loaded from: infinitode-2.jar:org/a/c/h/a/e.class */
public class e implements c {

    /* renamed from: a, reason: collision with root package name */
    private final org.a.c.b.d f4485a;

    public e() {
        this.f4485a = new org.a.c.b.d();
    }

    public e(org.a.c.b.d dVar) {
        this.f4485a = dVar;
    }

    @Override // org.a.c.h.a.c
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public final org.a.c.b.d f() {
        return this.f4485a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof e) {
            return this.f4485a.equals(((e) obj).f4485a);
        }
        return false;
    }

    public int hashCode() {
        return this.f4485a.hashCode();
    }
}
