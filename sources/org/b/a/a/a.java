package org.b.a.a;

/* loaded from: infinitode-2.jar:org/b/a/a/a.class */
public final class a implements org.b.a.c {

    /* renamed from: a, reason: collision with root package name */
    private final org.b.a.d f4691a;

    /* renamed from: b, reason: collision with root package name */
    private final int f4692b;
    private final int c;

    public a(org.b.a.d dVar, int i, int i2) {
        this.f4691a = dVar;
        this.f4692b = i;
        this.c = i2;
    }

    @Override // org.b.a.c
    public final org.b.a.d getType() {
        return this.f4691a;
    }

    @Override // org.b.a.c
    public final int getBeginIndex() {
        return this.f4692b;
    }

    @Override // org.b.a.c
    public final int getEndIndex() {
        return this.c;
    }

    public final String toString() {
        return "Link{type=" + getType() + ", beginIndex=" + this.f4692b + ", endIndex=" + this.c + "}";
    }
}
