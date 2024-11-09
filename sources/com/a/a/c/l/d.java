package com.a.a.c.l;

/* loaded from: infinitode-2.jar:com/a/a/c/l/d.class */
public class d extends m {
    protected final com.a.a.c.j e;

    /* JADX INFO: Access modifiers changed from: protected */
    public d(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr, com.a.a.c.j jVar2, Object obj, Object obj2, boolean z) {
        super(cls, nVar, jVar, jVarArr, jVar2.hashCode(), obj, obj2, z);
        this.e = jVar2;
    }

    @Override // com.a.a.c.j
    public com.a.a.c.j a(com.a.a.c.j jVar) {
        if (this.e == jVar) {
            return this;
        }
        return new d(this.f522a, this.i, this.g, this.h, jVar, this.f523b, this.c, this.d);
    }

    @Override // com.a.a.c.j
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public d a(Object obj) {
        return new d(this.f522a, this.i, this.g, this.h, this.e, this.f523b, obj, this.d);
    }

    @Override // com.a.a.c.j
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public d b(Object obj) {
        return new d(this.f522a, this.i, this.g, this.h, this.e.a(obj), this.f523b, this.c, this.d);
    }

    @Override // com.a.a.c.j
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public d c(Object obj) {
        return new d(this.f522a, this.i, this.g, this.h, this.e, obj, this.c, this.d);
    }

    @Override // com.a.a.c.j
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public d d(Object obj) {
        return new d(this.f522a, this.i, this.g, this.h, this.e.c(obj), this.f523b, this.c, this.d);
    }

    @Override // com.a.a.c.j
    public final com.a.a.c.j b(com.a.a.c.j jVar) {
        com.a.a.c.j b2;
        com.a.a.c.j b3 = super.b(jVar);
        com.a.a.c.j u = jVar.u();
        if (u != null && (b2 = this.e.b(u)) != this.e) {
            b3 = b3.a(b2);
        }
        return b3;
    }

    @Override // com.a.a.c.j
    /* renamed from: H, reason: merged with bridge method [inline-methods] */
    public d a() {
        if (this.d) {
            return this;
        }
        return new d(this.f522a, this.i, this.g, this.h, this.e.a(), this.f523b, this.c, true);
    }

    @Override // com.a.a.c.j
    public com.a.a.c.j a(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr) {
        return new d(cls, nVar, jVar, jVarArr, this.e, this.f523b, this.c, this.d);
    }

    @Override // com.a.a.c.j
    public final boolean n() {
        return true;
    }

    @Override // com.a.a.c.j
    public final boolean o() {
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
    public final StringBuilder b(StringBuilder sb) {
        return a(this.f522a, sb, true);
    }

    @Override // com.a.a.c.j
    public final StringBuilder a(StringBuilder sb) {
        a(this.f522a, sb, false);
        sb.append('<');
        this.e.a(sb);
        sb.append(">;");
        return sb;
    }

    @Override // com.a.a.c.l.m
    protected final String I() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f522a.getName());
        if (this.e != null && c(1)) {
            sb.append('<');
            sb.append(this.e.G());
            sb.append('>');
        }
        return sb.toString();
    }

    @Override // com.a.a.c.j
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        d dVar = (d) obj;
        return this.f522a == dVar.f522a && this.e.equals(dVar.e);
    }

    @Override // com.a.a.c.j
    public String toString() {
        return "[collection-like type; class " + this.f522a.getName() + ", contains " + this.e + "]";
    }
}
