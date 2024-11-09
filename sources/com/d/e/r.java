package com.d.e;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/d/e/r.class */
public class r extends com.d.i.m {

    /* renamed from: a, reason: collision with root package name */
    private /* synthetic */ v f1158a;

    /* renamed from: b, reason: collision with root package name */
    private /* synthetic */ com.d.i.u f1159b;
    private /* synthetic */ int c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public r(v vVar, com.d.i.u uVar, int i) {
        this.f1158a = vVar;
        this.f1159b = uVar;
        this.c = i;
    }

    @Override // com.d.i.m
    public final int a() {
        return this.f1158a.l().a(this.f1158a, this.f1159b, this.c);
    }

    @Override // com.d.i.m
    public final int b() {
        return this.f1158a.l().b(this.f1158a, this.f1159b, this.c);
    }
}
