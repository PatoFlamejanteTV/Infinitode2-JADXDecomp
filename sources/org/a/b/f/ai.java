package org.a.b.f;

import java.util.List;

/* loaded from: infinitode-2.jar:org/a/b/f/ai.class */
public final class ai implements c {

    /* renamed from: a, reason: collision with root package name */
    private final d f4276a;

    /* renamed from: b, reason: collision with root package name */
    private final n f4277b;
    private final List<String> c;

    public ai(d dVar, n nVar, List<String> list) {
        this.f4276a = dVar;
        this.f4277b = nVar;
        this.c = list;
    }

    @Override // org.a.b.f.c
    public final int a(int i) {
        return this.f4277b.a(this.f4276a.a(i), ae.a(i), this.c);
    }

    @Override // org.a.b.f.c
    public final List<Integer> b(int i) {
        return this.f4276a.b(this.f4277b.a(i));
    }
}
