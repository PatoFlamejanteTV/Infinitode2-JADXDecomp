package com.d.c.e;

import com.d.c.c.f;
import com.d.i.v;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/c/e/d.class */
public final class d {

    /* renamed from: a, reason: collision with root package name */
    private final int f1070a;

    /* renamed from: b, reason: collision with root package name */
    private final List<v> f1071b = new ArrayList();
    private final List<f> c = new ArrayList();

    public d(int i) {
        this.f1070a = i;
    }

    public final List<v> a() {
        return Collections.unmodifiableList(this.f1071b);
    }

    public final void a(List<v> list) {
        this.f1071b.addAll(list);
    }

    public final void a(f fVar) {
        this.c.add(fVar);
    }

    public final List<f> b() {
        return this.c;
    }

    public final int c() {
        return this.f1070a;
    }
}
