package com.d.f;

import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/f/c.class */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    private List f1176a = new ArrayList();

    public final List a() {
        return this.f1176a;
    }

    public final void a(int i) {
        while (this.f1176a.size() < i) {
            this.f1176a.add(null);
        }
    }

    public final void b(int i) {
        this.f1176a.add(i + 1, ((f) this.f1176a.get(i)) == null ? null : f.f1185a);
    }
}
