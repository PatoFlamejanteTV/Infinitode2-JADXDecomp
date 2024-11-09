package com.d.c.e;

import com.d.c.f.f;
import com.d.h.w;

/* loaded from: infinitode-2.jar:com/d/c/e/a.class */
public final class a implements e {

    /* renamed from: a, reason: collision with root package name */
    private int f1064a;

    /* renamed from: b, reason: collision with root package name */
    private d f1065b;
    private com.d.c.f.c c;

    public a(int i) {
        this.f1064a = i;
    }

    @Override // com.d.c.e.e
    public final void a(d dVar) {
        if (this.f1065b != null) {
            throw new w.a("Ruleset can only be set once");
        }
        this.f1065b = dVar;
    }

    @Override // com.d.c.e.e
    public final int a() {
        return this.f1064a;
    }

    public final com.d.c.f.c b() {
        if (this.c == null) {
            this.c = new f().a(com.d.c.c.a.a(this.f1065b.a()));
        }
        return this.c;
    }

    private boolean a(String str) {
        return this.f1065b.a().stream().anyMatch(vVar -> {
            return str.equals(vVar.c());
        });
    }

    public final boolean c() {
        return a("font-family");
    }

    public final boolean d() {
        return a("font-weight");
    }

    public final boolean e() {
        return a("font-style");
    }
}
