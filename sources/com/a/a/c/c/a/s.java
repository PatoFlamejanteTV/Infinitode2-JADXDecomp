package com.a.a.c.c.a;

import com.a.a.a.al;
import com.a.a.a.an;
import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/c/c/a/s.class */
public final class s implements Serializable {
    private com.a.a.c.j e;

    /* renamed from: a, reason: collision with root package name */
    public final com.a.a.c.w f280a;

    /* renamed from: b, reason: collision with root package name */
    public final al<?> f281b;
    public final an c;
    private com.a.a.c.k<Object> f;
    public final com.a.a.c.c.v d;

    private s(com.a.a.c.j jVar, com.a.a.c.w wVar, al<?> alVar, com.a.a.c.k<?> kVar, com.a.a.c.c.v vVar, an anVar) {
        this.e = jVar;
        this.f280a = wVar;
        this.f281b = alVar;
        this.c = anVar;
        this.f = kVar;
        this.d = vVar;
    }

    public static s a(com.a.a.c.j jVar, com.a.a.c.w wVar, al<?> alVar, com.a.a.c.k<?> kVar, com.a.a.c.c.v vVar, an anVar) {
        return new s(jVar, wVar, alVar, kVar, vVar, anVar);
    }

    public final com.a.a.c.k<Object> a() {
        return this.f;
    }

    public final com.a.a.c.j b() {
        return this.e;
    }

    public final boolean c() {
        return false;
    }

    public final boolean a(String str, com.a.a.b.l lVar) {
        return false;
    }

    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        return this.f.a(lVar, gVar);
    }
}
