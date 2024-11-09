package com.a.a.c.l;

import net.bytebuddy.description.type.TypeDescription;

/* loaded from: infinitode-2.jar:com/a/a/c/l/k.class */
public final class k extends m {
    private com.a.a.c.j e;

    public k(Class<?> cls, n nVar) {
        super(cls, nVar, null, null, 0, null, null, false);
    }

    public final void e(com.a.a.c.j jVar) {
        if (this.e != null) {
            throw new IllegalStateException("Trying to re-set self reference; old value = " + this.e + ", new = " + jVar);
        }
        this.e = jVar;
    }

    @Override // com.a.a.c.l.m, com.a.a.c.j
    public final com.a.a.c.j y() {
        if (this.e != null) {
            return this.e.y();
        }
        return super.y();
    }

    public final com.a.a.c.j H() {
        return this.e;
    }

    @Override // com.a.a.c.l.m, com.a.a.c.j
    public final n x() {
        if (this.e != null) {
            return this.e.x();
        }
        return super.x();
    }

    @Override // com.a.a.c.j
    public final StringBuilder a(StringBuilder sb) {
        if (this.e != null) {
            return this.e.b(sb);
        }
        return sb.append(TypeDescription.Generic.OfWildcardType.SYMBOL);
    }

    @Override // com.a.a.c.j
    public final StringBuilder b(StringBuilder sb) {
        if (this.e != null) {
            return this.e.b(sb);
        }
        return sb;
    }

    @Override // com.a.a.c.j
    public final com.a.a.c.j a(com.a.a.c.j jVar) {
        return this;
    }

    @Override // com.a.a.c.j
    public final com.a.a.c.j a(Object obj) {
        return this;
    }

    @Override // com.a.a.c.j
    public final com.a.a.c.j b(Object obj) {
        return this;
    }

    @Override // com.a.a.c.j
    public final com.a.a.c.j c(Object obj) {
        return this;
    }

    @Override // com.a.a.c.j
    public final com.a.a.c.j d(Object obj) {
        return this;
    }

    @Override // com.a.a.c.j
    public final com.a.a.c.j a() {
        return this;
    }

    @Override // com.a.a.c.j
    public final com.a.a.c.j a(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr) {
        return null;
    }

    @Override // com.a.a.c.j
    public final boolean n() {
        return false;
    }

    @Override // com.a.a.c.j
    public final String toString() {
        StringBuilder append = new StringBuilder(40).append("[recursive type; ");
        if (this.e == null) {
            append.append("UNRESOLVED");
        } else {
            append.append(this.e.b().getName());
        }
        return append.toString();
    }

    @Override // com.a.a.c.j
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            return false;
        }
        return false;
    }
}
