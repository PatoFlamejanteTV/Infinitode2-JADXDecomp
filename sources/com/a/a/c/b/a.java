package com.a.a.c.b;

import com.a.a.c.f.a;
import com.a.a.c.x;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: infinitode-2.jar:com/a/a/c/b/a.class */
public final class a implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private static final TimeZone f208a = TimeZone.getTimeZone("UTC");

    /* renamed from: b, reason: collision with root package name */
    private com.a.a.c.l.o f209b;
    private com.a.a.c.f.t c;
    private com.a.a.c.a d;
    private x e;
    private a.AbstractC0008a f;
    private com.a.a.c.i.h<?> g;
    private com.a.a.c.i.c h;
    private DateFormat i;
    private com.a.a.c.k.a.d j;
    private Locale k;
    private TimeZone l;
    private com.a.a.b.a m;

    public a(com.a.a.c.f.t tVar, com.a.a.c.a aVar, x xVar, com.a.a.c.l.o oVar, com.a.a.c.i.h<?> hVar, DateFormat dateFormat, com.a.a.c.k.a.d dVar, Locale locale, TimeZone timeZone, com.a.a.b.a aVar2, com.a.a.c.i.c cVar, a.AbstractC0008a abstractC0008a) {
        this.c = tVar;
        this.d = aVar;
        this.e = xVar;
        this.f209b = oVar;
        this.g = hVar;
        this.i = dateFormat;
        this.j = dVar;
        this.k = locale;
        this.l = timeZone;
        this.m = aVar2;
        this.h = cVar;
        this.f = abstractC0008a;
    }

    public final a a(com.a.a.c.f.t tVar) {
        if (this.c == tVar) {
            return this;
        }
        return new a(tVar, this.d, this.e, this.f209b, this.g, this.i, this.j, this.k, this.l, this.m, this.h, this.f);
    }

    public final com.a.a.c.f.t a() {
        return this.c;
    }

    public final com.a.a.c.a b() {
        return this.d;
    }

    public final x c() {
        return this.e;
    }

    public final a.AbstractC0008a d() {
        return this.f;
    }

    public final com.a.a.c.l.o e() {
        return this.f209b;
    }

    public final com.a.a.c.i.h<?> f() {
        return this.g;
    }

    public final com.a.a.c.i.c g() {
        return this.h;
    }

    public final DateFormat h() {
        return this.i;
    }

    public final com.a.a.c.k.a.d i() {
        return this.j;
    }

    public final Locale j() {
        return this.k;
    }

    public final TimeZone k() {
        TimeZone timeZone = this.l;
        return timeZone == null ? f208a : timeZone;
    }

    public final com.a.a.b.a l() {
        return this.m;
    }
}
