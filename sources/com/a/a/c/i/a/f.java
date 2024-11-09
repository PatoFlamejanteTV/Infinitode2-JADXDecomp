package com.a.a.c.i.a;

import com.a.a.a.af;

/* loaded from: infinitode-2.jar:com/a/a/c/i/a/f.class */
public final class f extends u {

    /* renamed from: a, reason: collision with root package name */
    private String f501a;

    public f(com.a.a.c.i.g gVar, com.a.a.c.c cVar, String str) {
        super(gVar, cVar);
        this.f501a = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.i.i
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public f a(com.a.a.c.c cVar) {
        return this.c == cVar ? this : new f(this.f515b, cVar, this.f501a);
    }

    @Override // com.a.a.c.i.a.u, com.a.a.c.i.i
    public final String b() {
        return this.f501a;
    }

    @Override // com.a.a.c.i.i
    public final af.a a() {
        return af.a.EXTERNAL_PROPERTY;
    }
}
