package com.d.i;

import com.d.e.n;

/* loaded from: infinitode-2.jar:com/d/i/d.class */
class d implements n.c {

    /* renamed from: a, reason: collision with root package name */
    private /* synthetic */ com.d.c.f.d f1344a;

    /* renamed from: b, reason: collision with root package name */
    private /* synthetic */ boolean f1345b;
    private /* synthetic */ com.d.e.y c;
    private /* synthetic */ c d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(c cVar, com.d.c.f.d dVar, boolean z, com.d.e.y yVar) {
        this.d = cVar;
        this.f1344a = dVar;
        this.f1345b = z;
        this.c = yVar;
    }

    @Override // com.d.e.n.c
    public final void a(f fVar) {
        c.a(this.c.b(), fVar.c(this.f1344a, this.f1345b).b());
    }
}
