package com.a.a.c.i.a;

import com.a.a.a.af;

/* loaded from: infinitode-2.jar:com/a/a/c/i/a/h.class */
public class h extends b {

    /* renamed from: a, reason: collision with root package name */
    protected final String f502a;

    public h(com.a.a.c.i.g gVar, com.a.a.c.c cVar, String str) {
        super(gVar, cVar);
        this.f502a = str;
    }

    @Override // com.a.a.c.i.a.b, com.a.a.c.i.i
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public h a(com.a.a.c.c cVar) {
        return this.c == cVar ? this : new h(this.f515b, cVar, this.f502a);
    }

    @Override // com.a.a.c.i.a.u, com.a.a.c.i.i
    public final String b() {
        return this.f502a;
    }

    @Override // com.a.a.c.i.a.b, com.a.a.c.i.i
    public af.a a() {
        return af.a.PROPERTY;
    }
}
