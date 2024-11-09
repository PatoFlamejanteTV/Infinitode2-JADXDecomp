package com.a.a.c.l;

/* loaded from: infinitode-2.jar:com/a/a/c/l/h.class */
public final class h extends g {
    private h(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr, com.a.a.c.j jVar2, com.a.a.c.j jVar3, Object obj, Object obj2, boolean z) {
        super(cls, nVar, jVar, jVarArr, jVar2, jVar3, obj, obj2, z);
    }

    public static h a(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr, com.a.a.c.j jVar2, com.a.a.c.j jVar3) {
        return new h(cls, nVar, jVar, jVarArr, jVar2, jVar3, null, null, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.l.g, com.a.a.c.j
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public h a(Object obj) {
        return new h(this.f522a, this.i, this.g, this.h, this.e, this.f, this.f523b, obj, this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.l.g, com.a.a.c.j
    /* renamed from: k, reason: merged with bridge method [inline-methods] */
    public h b(Object obj) {
        return new h(this.f522a, this.i, this.g, this.h, this.e, this.f.a(obj), this.f523b, this.c, this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.l.g, com.a.a.c.j
    /* renamed from: l, reason: merged with bridge method [inline-methods] */
    public h c(Object obj) {
        return new h(this.f522a, this.i, this.g, this.h, this.e, this.f, obj, this.c, this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.l.g, com.a.a.c.j
    /* renamed from: m, reason: merged with bridge method [inline-methods] */
    public h d(Object obj) {
        return new h(this.f522a, this.i, this.g, this.h, this.e, this.f.c(obj), this.f523b, this.c, this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.l.g, com.a.a.c.j
    /* renamed from: J, reason: merged with bridge method [inline-methods] */
    public h a() {
        if (this.d) {
            return this;
        }
        return new h(this.f522a, this.i, this.g, this.h, this.e.a(), this.f.a(), this.f523b, this.c, true);
    }

    @Override // com.a.a.c.l.g, com.a.a.c.j
    public final com.a.a.c.j a(com.a.a.c.j jVar) {
        if (this.f == jVar) {
            return this;
        }
        return new h(this.f522a, this.i, this.g, this.h, this.e, jVar, this.f523b, this.c, this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.l.g
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public h e(com.a.a.c.j jVar) {
        if (jVar == this.e) {
            return this;
        }
        return new h(this.f522a, this.i, this.g, this.h, jVar, this.f, this.f523b, this.c, this.d);
    }

    @Override // com.a.a.c.l.g, com.a.a.c.j
    public final com.a.a.c.j a(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr) {
        return new h(cls, nVar, jVar, jVarArr, this.e, this.f, this.f523b, this.c, this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.l.g
    /* renamed from: n, reason: merged with bridge method [inline-methods] */
    public h i(Object obj) {
        return new h(this.f522a, this.i, this.g, this.h, this.e.c(obj), this.f, this.f523b, this.c, this.d);
    }

    @Override // com.a.a.c.l.g, com.a.a.c.j
    public final String toString() {
        return "[map type; class " + this.f522a.getName() + ", " + this.e + " -> " + this.f + "]";
    }
}
