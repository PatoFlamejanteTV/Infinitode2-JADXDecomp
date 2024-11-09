package com.d.i.a;

import com.d.i.a.c;
import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/i/a/a.class */
public final class a extends c {

    /* renamed from: a, reason: collision with root package name */
    private final List<c.a> f1283a;

    /* renamed from: b, reason: collision with root package name */
    private final int f1284b = 0;

    public a(int i, int i2) {
        this.f1283a = new ArrayList(i2 + 1);
        for (int i3 = 0; i3 < i2 + 1; i3++) {
            this.f1283a.add(new c.a(null));
        }
    }

    @Override // com.d.i.a.c
    public final c.a a(int i) {
        return this.f1283a.get(i - this.f1284b);
    }

    @Override // com.d.i.a.c
    public final int a() {
        return this.f1284b;
    }

    @Override // com.d.i.a.c
    public final int b() {
        return (this.f1284b + this.f1283a.size()) - 1;
    }
}
