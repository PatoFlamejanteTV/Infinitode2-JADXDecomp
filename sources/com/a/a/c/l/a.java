package com.a.a.c.l;

import java.lang.reflect.Array;

/* loaded from: infinitode-2.jar:com/a/a/c/l/a.class */
public final class a extends m {
    private com.a.a.c.j e;
    private Object f;

    private a(com.a.a.c.j jVar, n nVar, Object obj, Object obj2, Object obj3, boolean z) {
        super(obj.getClass(), nVar, null, null, jVar.hashCode(), obj2, obj3, z);
        this.e = jVar;
        this.f = obj;
    }

    public static a a(com.a.a.c.j jVar, n nVar) {
        return a(jVar, nVar, (Object) null, (Object) null);
    }

    private static a a(com.a.a.c.j jVar, n nVar, Object obj, Object obj2) {
        return new a(jVar, nVar, Array.newInstance(jVar.b(), 0), obj, obj2, false);
    }

    @Override // com.a.a.c.j
    public final com.a.a.c.j a(com.a.a.c.j jVar) {
        return new a(jVar, this.i, Array.newInstance(jVar.b(), 0), this.f523b, this.c, this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.j
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public a a(Object obj) {
        if (obj == this.c) {
            return this;
        }
        return new a(this.e, this.i, this.f, this.f523b, obj, this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.j
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public a b(Object obj) {
        if (obj == this.e.B()) {
            return this;
        }
        return new a(this.e.a(obj), this.i, this.f, this.f523b, this.c, this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.j
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public a c(Object obj) {
        if (obj == this.f523b) {
            return this;
        }
        return new a(this.e, this.i, this.f, obj, this.c, this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.j
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public a d(Object obj) {
        if (obj == this.e.A()) {
            return this;
        }
        return new a(this.e.c(obj), this.i, this.f, this.f523b, this.c, this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.j
    /* renamed from: J, reason: merged with bridge method [inline-methods] */
    public a a() {
        if (this.d) {
            return this;
        }
        return new a(this.e.a(), this.i, this.f, this.f523b, this.c, true);
    }

    @Override // com.a.a.c.j
    public final com.a.a.c.j a(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr) {
        return null;
    }

    @Override // com.a.a.c.j
    public final boolean g() {
        return true;
    }

    @Override // com.a.a.c.j
    public final boolean d() {
        return false;
    }

    @Override // com.a.a.c.j
    public final boolean e() {
        return true;
    }

    @Override // com.a.a.c.j
    public final boolean s() {
        return this.e.s();
    }

    @Override // com.a.a.c.j
    public final boolean n() {
        return true;
    }

    @Override // com.a.a.c.j
    public final com.a.a.c.j u() {
        return this.e;
    }

    @Override // com.a.a.c.j
    public final boolean C() {
        return super.C() || this.e.C();
    }

    @Override // com.a.a.c.j
    public final StringBuilder a(StringBuilder sb) {
        sb.append('[');
        return this.e.a(sb);
    }

    @Override // com.a.a.c.j
    public final StringBuilder b(StringBuilder sb) {
        sb.append('[');
        return this.e.b(sb);
    }

    public final Object[] H() {
        return (Object[]) this.f;
    }

    @Override // com.a.a.c.j
    public final String toString() {
        return "[array type, component type: " + this.e + "]";
    }

    @Override // com.a.a.c.j
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            return this.e.equals(((a) obj).e);
        }
        return false;
    }
}
