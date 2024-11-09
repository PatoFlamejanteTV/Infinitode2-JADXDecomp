package com.a.a.c.f;

import com.a.a.c.f.af;

/* loaded from: infinitode-2.jar:com/a/a/c/f/aj.class */
class aj implements af.c<ad> {

    /* renamed from: a, reason: collision with root package name */
    private /* synthetic */ af f438a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public aj(af afVar) {
        this.f438a = afVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.f.af.c
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public ad a(j jVar) {
        ad a2 = this.f438a.f431b.a((b) jVar);
        ad adVar = a2;
        if (a2 != null) {
            adVar = this.f438a.f431b.a(jVar, adVar);
        }
        return adVar;
    }
}
