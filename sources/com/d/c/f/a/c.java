package com.d.c.f.a;

import com.d.c.d.j;
import com.d.e.ad;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/c/f/a/c.class */
public final class c extends com.d.c.f.e {

    /* renamed from: a, reason: collision with root package name */
    private final List<ad> f1076a;

    public c(com.d.c.a.a aVar, j jVar) {
        super(aVar, jVar.a(), jVar.d(), jVar.d());
        this.f1076a = jVar.m();
    }

    public final List<ad> j() {
        return this.f1076a;
    }

    @Override // com.d.c.f.e, com.d.c.f.g
    public final String[] e() {
        if (this.f1076a == null || this.f1076a.isEmpty()) {
            return com.d.m.c.f1417a;
        }
        return (String[]) this.f1076a.stream().map((v0) -> {
            return v0.toString();
        }).toArray(i -> {
            return new String[i];
        });
    }
}
