package com.a.a.c.l;

/* loaded from: infinitode-2.jar:com/a/a/c/l/e.class */
public final class e extends d {
    private e(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr, com.a.a.c.j jVar2, Object obj, Object obj2, boolean z) {
        super(cls, nVar, jVar, jVarArr, jVar2, obj, obj2, z);
    }

    public static e a(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr, com.a.a.c.j jVar2) {
        return new e(cls, nVar, jVar, jVarArr, jVar2, null, null, false);
    }

    @Override // com.a.a.c.l.d, com.a.a.c.j
    public final com.a.a.c.j a(com.a.a.c.j jVar) {
        if (this.e == jVar) {
            return this;
        }
        return new e(this.f522a, this.i, this.g, this.h, jVar, this.f523b, this.c, this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.l.d, com.a.a.c.j
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public e a(Object obj) {
        return new e(this.f522a, this.i, this.g, this.h, this.e, this.f523b, obj, this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.l.d, com.a.a.c.j
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public e b(Object obj) {
        return new e(this.f522a, this.i, this.g, this.h, this.e.a(obj), this.f523b, this.c, this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.l.d, com.a.a.c.j
    /* renamed from: k, reason: merged with bridge method [inline-methods] */
    public e c(Object obj) {
        return new e(this.f522a, this.i, this.g, this.h, this.e, obj, this.c, this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.l.d, com.a.a.c.j
    /* renamed from: l, reason: merged with bridge method [inline-methods] */
    public e d(Object obj) {
        return new e(this.f522a, this.i, this.g, this.h, this.e.c(obj), this.f523b, this.c, this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.l.d, com.a.a.c.j
    /* renamed from: J, reason: merged with bridge method [inline-methods] */
    public e a() {
        if (this.d) {
            return this;
        }
        return new e(this.f522a, this.i, this.g, this.h, this.e.a(), this.f523b, this.c, true);
    }

    @Override // com.a.a.c.l.d, com.a.a.c.j
    public final com.a.a.c.j a(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr) {
        return new e(cls, nVar, jVar, jVarArr, this.e, this.f523b, this.c, this.d);
    }

    @Override // com.a.a.c.l.d, com.a.a.c.j
    public final String toString() {
        return "[collection type; class " + this.f522a.getName() + ", contains " + this.e + "]";
    }
}
