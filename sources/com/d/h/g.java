package com.d.h;

import org.a.b.f.ao;
import org.a.b.f.ap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/d/h/g.class */
public class g implements ao.a {

    /* renamed from: a, reason: collision with root package name */
    private /* synthetic */ String f1229a;

    /* renamed from: b, reason: collision with root package name */
    private /* synthetic */ Integer f1230b;
    private /* synthetic */ com.d.c.a.c c;
    private /* synthetic */ boolean d;
    private /* synthetic */ f e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(f fVar, String str, Integer num, com.d.c.a.c cVar, boolean z) {
        this.e = fVar;
        this.f1229a = str;
        this.f1230b = num;
        this.c = cVar;
        this.d = z;
    }

    @Override // org.a.b.f.ao.a
    public final void a(ap apVar) {
        this.e.a(apVar, this.f1229a, this.f1230b, this.c, this.d);
    }
}
