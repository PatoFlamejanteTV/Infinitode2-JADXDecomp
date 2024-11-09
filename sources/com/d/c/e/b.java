package com.d.c.e;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: infinitode-2.jar:com/d/c/e/b.class */
public final class b implements e {

    /* renamed from: a, reason: collision with root package name */
    private final List<String> f1066a = new ArrayList(1);

    /* renamed from: b, reason: collision with root package name */
    private final List<d> f1067b = new ArrayList();
    private final int c;

    public b(int i) {
        this.c = i;
    }

    public final void a(String str) {
        this.f1066a.add(str);
    }

    public final boolean b(String str) {
        if (str.equalsIgnoreCase("all") || this.f1066a.contains("all")) {
            return true;
        }
        return this.f1066a.contains(str.toLowerCase(Locale.US));
    }

    @Override // com.d.c.e.e
    public final void a(d dVar) {
        this.f1067b.add(dVar);
    }

    public final List<d> b() {
        return this.f1067b;
    }

    @Override // com.d.c.e.e
    public final int a() {
        return this.c;
    }
}
