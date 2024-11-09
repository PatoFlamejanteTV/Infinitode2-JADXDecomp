package com.a.a.c.l;

/* loaded from: infinitode-2.jar:com/a/a/c/l/g.class */
public class g extends m {
    protected final com.a.a.c.j e;
    protected final com.a.a.c.j f;

    /* JADX INFO: Access modifiers changed from: protected */
    public g(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr, com.a.a.c.j jVar2, com.a.a.c.j jVar3, Object obj, Object obj2, boolean z) {
        super(cls, nVar, jVar, jVarArr, jVar2.hashCode() ^ jVar3.hashCode(), obj, obj2, z);
        this.e = jVar2;
        this.f = jVar3;
    }

    public g e(com.a.a.c.j jVar) {
        if (jVar == this.e) {
            return this;
        }
        return new g(this.f522a, this.i, this.g, this.h, jVar, this.f, this.f523b, this.c, this.d);
    }

    @Override // com.a.a.c.j
    public com.a.a.c.j a(com.a.a.c.j jVar) {
        if (this.f == jVar) {
            return this;
        }
        return new g(this.f522a, this.i, this.g, this.h, this.e, jVar, this.f523b, this.c, this.d);
    }

    @Override // com.a.a.c.j
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public g a(Object obj) {
        return new g(this.f522a, this.i, this.g, this.h, this.e, this.f, this.f523b, obj, this.d);
    }

    @Override // com.a.a.c.j
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public g b(Object obj) {
        return new g(this.f522a, this.i, this.g, this.h, this.e, this.f.a(obj), this.f523b, this.c, this.d);
    }

    @Override // com.a.a.c.j
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public g c(Object obj) {
        return new g(this.f522a, this.i, this.g, this.h, this.e, this.f, obj, this.c, this.d);
    }

    @Override // com.a.a.c.j
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public g d(Object obj) {
        return new g(this.f522a, this.i, this.g, this.h, this.e, this.f.c(obj), this.f523b, this.c, this.d);
    }

    @Override // com.a.a.c.j
    public final com.a.a.c.j b(com.a.a.c.j jVar) {
        com.a.a.c.j b2;
        com.a.a.c.j b3;
        com.a.a.c.j b4 = super.b(jVar);
        com.a.a.c.j t = jVar.t();
        if ((b4 instanceof g) && t != null && (b3 = this.e.b(t)) != this.e) {
            b4 = ((g) b4).e(b3);
        }
        com.a.a.c.j u = jVar.u();
        if (u != null && (b2 = this.f.b(u)) != this.f) {
            b4 = b4.a(b2);
        }
        return b4;
    }

    @Override // com.a.a.c.j
    /* renamed from: H, reason: merged with bridge method [inline-methods] */
    public g a() {
        if (this.d) {
            return this;
        }
        return new g(this.f522a, this.i, this.g, this.h, this.e, this.f.a(), this.f523b, this.c, true);
    }

    @Override // com.a.a.c.j
    public com.a.a.c.j a(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr) {
        return new g(cls, nVar, jVar, jVarArr, this.e, this.f, this.f523b, this.c, this.d);
    }

    @Override // com.a.a.c.l.m
    protected final String I() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f522a.getName());
        if (this.e != null && c(2)) {
            sb.append('<');
            sb.append(this.e.G());
            sb.append(',');
            sb.append(this.f.G());
            sb.append('>');
        }
        return sb.toString();
    }

    @Override // com.a.a.c.j
    public final boolean n() {
        return true;
    }

    @Override // com.a.a.c.j
    public final boolean p() {
        return true;
    }

    @Override // com.a.a.c.j
    public final com.a.a.c.j t() {
        return this.e;
    }

    @Override // com.a.a.c.j
    public final com.a.a.c.j u() {
        return this.f;
    }

    @Override // com.a.a.c.j
    public final boolean C() {
        return super.C() || this.f.C() || this.e.C();
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
        this.f.a(sb);
        sb.append(">;");
        return sb;
    }

    public g i(Object obj) {
        return new g(this.f522a, this.i, this.g, this.h, this.e.c(obj), this.f, this.f523b, this.c, this.d);
    }

    @Override // com.a.a.c.j
    public String toString() {
        return String.format("[map-like type; class %s, %s -> %s]", this.f522a.getName(), this.e, this.f);
    }

    @Override // com.a.a.c.j
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        g gVar = (g) obj;
        return this.f522a == gVar.f522a && this.e.equals(gVar.e) && this.f.equals(gVar.f);
    }
}
