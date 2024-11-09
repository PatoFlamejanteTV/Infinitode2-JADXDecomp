package com.a.a.c.l;

import java.util.Objects;

/* loaded from: infinitode-2.jar:com/a/a/c/l/j.class */
public final class j extends l {
    private com.a.a.c.j e;
    private com.a.a.c.j f;

    private j(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr, com.a.a.c.j jVar2, com.a.a.c.j jVar3, Object obj, Object obj2, boolean z) {
        super(cls, nVar, jVar, jVarArr, Objects.hashCode(jVar2), obj, obj2, z);
        this.e = jVar2;
        this.f = jVar3 == null ? this : jVar3;
    }

    public static j a(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr, com.a.a.c.j jVar2) {
        return new j(cls, nVar, jVar, jVarArr, jVar2, null, null, null, false);
    }

    @Override // com.a.a.c.l.l, com.a.a.c.j
    public final com.a.a.c.j a(com.a.a.c.j jVar) {
        if (this.e == jVar) {
            return this;
        }
        return new j(this.f522a, this.i, this.g, this.h, jVar, this.f, this.f523b, this.c, this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.l.l, com.a.a.c.j
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public j a(Object obj) {
        if (obj == this.c) {
            return this;
        }
        return new j(this.f522a, this.i, this.g, this.h, this.e, this.f, this.f523b, obj, this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.l.l, com.a.a.c.j
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public j b(Object obj) {
        if (obj == this.e.B()) {
            return this;
        }
        return new j(this.f522a, this.i, this.g, this.h, this.e.a(obj), this.f, this.f523b, this.c, this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.l.l, com.a.a.c.j
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public j c(Object obj) {
        if (obj == this.f523b) {
            return this;
        }
        return new j(this.f522a, this.i, this.g, this.h, this.e, this.f, obj, this.c, this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.l.l, com.a.a.c.j
    /* renamed from: k, reason: merged with bridge method [inline-methods] */
    public j d(Object obj) {
        if (obj == this.e.A()) {
            return this;
        }
        return new j(this.f522a, this.i, this.g, this.h, this.e.c(obj), this.f, this.f523b, this.c, this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.l.l, com.a.a.c.j
    /* renamed from: J, reason: merged with bridge method [inline-methods] */
    public j a() {
        if (this.d) {
            return this;
        }
        return new j(this.f522a, this.i, this.g, this.h, this.e.a(), this.f, this.f523b, this.c, true);
    }

    @Override // com.a.a.c.l.l, com.a.a.c.j
    public final com.a.a.c.j a(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr) {
        return new j(cls, this.i, jVar, jVarArr, this.e, this.f, this.f523b, this.c, this.d);
    }

    @Override // com.a.a.c.l.l, com.a.a.c.l.m
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
    public final com.a.a.c.j u() {
        return this.e;
    }

    @Override // com.a.a.c.j, com.a.a.c.c.a.l
    /* renamed from: v */
    public final com.a.a.c.j E() {
        return this.e;
    }

    @Override // com.a.a.c.l.l, com.a.a.c.j
    public final boolean c() {
        return true;
    }

    @Override // com.a.a.c.c.a.l
    public final boolean F() {
        return true;
    }

    @Override // com.a.a.c.l.l, com.a.a.c.j
    public final StringBuilder b(StringBuilder sb) {
        return a(this.f522a, sb, true);
    }

    @Override // com.a.a.c.l.l, com.a.a.c.j
    public final StringBuilder a(StringBuilder sb) {
        a(this.f522a, sb, false);
        sb.append('<');
        StringBuilder a2 = this.e.a(sb);
        a2.append(">;");
        return a2;
    }

    @Override // com.a.a.c.l.l, com.a.a.c.j
    public final String toString() {
        return new StringBuilder(40).append("[reference type, class ").append(I()).append('<').append(this.e).append('>').append(']').toString();
    }

    @Override // com.a.a.c.l.l, com.a.a.c.j
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        j jVar = (j) obj;
        if (jVar.f522a != this.f522a) {
            return false;
        }
        return this.e.equals(jVar.e);
    }
}
