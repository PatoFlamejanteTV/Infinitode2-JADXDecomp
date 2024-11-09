package com.d.c.f.a;

import com.d.c.d.j;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/c/f/a/f.class */
public final class f extends com.d.c.f.e {

    /* renamed from: a, reason: collision with root package name */
    private final List<j> f1080a;

    public f(com.d.c.a.a aVar, j jVar) {
        super(aVar, jVar.a(), jVar.d(), jVar.d());
        this.f1080a = jVar.l();
    }

    public final List<j> j() {
        return this.f1080a;
    }

    @Override // com.d.c.f.e, com.d.c.f.g
    public final String[] e() {
        if (this.f1080a == null || this.f1080a.isEmpty()) {
            return com.d.m.c.f1417a;
        }
        return (String[]) this.f1080a.stream().map((v0) -> {
            return v0.toString();
        }).toArray(i -> {
            return new String[i];
        });
    }
}
