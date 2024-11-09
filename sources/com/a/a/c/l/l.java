package com.a.a.c.l;

/* loaded from: infinitode-2.jar:com/a/a/c/l/l.class */
public class l extends m {
    /* JADX INFO: Access modifiers changed from: protected */
    public l(Class<?> cls) {
        this(cls, n.a(), null, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public l(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr) {
        this(cls, nVar, jVar, jVarArr, null, null, false);
    }

    private l(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr, Object obj, Object obj2, boolean z) {
        super(cls, nVar, jVar, jVarArr, 0, obj, obj2, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public l(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr, int i, Object obj, Object obj2, boolean z) {
        super(cls, nVar, jVar, jVarArr, i, obj, obj2, z);
    }

    public static l e(Class<?> cls) {
        return new l(cls, null, null, null, null, null, false);
    }

    @Override // com.a.a.c.j
    public com.a.a.c.j a(com.a.a.c.j jVar) {
        throw new IllegalArgumentException("Simple types have no content types; cannot call withContentType()");
    }

    @Override // com.a.a.c.j
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public l a(Object obj) {
        if (this.c == obj) {
            return this;
        }
        return new l(this.f522a, this.i, this.g, this.h, this.f523b, obj, this.d);
    }

    @Override // com.a.a.c.j
    public com.a.a.c.j b(Object obj) {
        throw new IllegalArgumentException("Simple types have no content types; cannot call withContenTypeHandler()");
    }

    @Override // com.a.a.c.j
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public l c(Object obj) {
        if (obj == this.f523b) {
            return this;
        }
        return new l(this.f522a, this.i, this.g, this.h, obj, this.c, this.d);
    }

    @Override // com.a.a.c.j
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public l d(Object obj) {
        throw new IllegalArgumentException("Simple types have no content types; cannot call withContenValueHandler()");
    }

    @Override // com.a.a.c.j
    /* renamed from: H, reason: merged with bridge method [inline-methods] */
    public l a() {
        return this.d ? this : new l(this.f522a, this.i, this.g, this.h, this.f523b, this.c, true);
    }

    @Override // com.a.a.c.j
    public com.a.a.c.j a(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr) {
        return null;
    }

    @Override // com.a.a.c.l.m
    protected String I() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f522a.getName());
        int c = this.i.c();
        if (c > 0 && c(c)) {
            sb.append('<');
            for (int i = 0; i < c; i++) {
                com.a.a.c.j a2 = a(i);
                if (i > 0) {
                    sb.append(',');
                }
                sb.append(a2.G());
            }
            sb.append('>');
        }
        return sb.toString();
    }

    @Override // com.a.a.c.j
    public final boolean n() {
        return false;
    }

    @Override // com.a.a.c.j
    public boolean c() {
        return false;
    }

    @Override // com.a.a.c.j
    public StringBuilder b(StringBuilder sb) {
        return a(this.f522a, sb, true);
    }

    @Override // com.a.a.c.j
    public StringBuilder a(StringBuilder sb) {
        a(this.f522a, sb, false);
        int c = this.i.c();
        if (c > 0) {
            sb.append('<');
            for (int i = 0; i < c; i++) {
                sb = a(i).a(sb);
            }
            sb.append('>');
        }
        sb.append(';');
        return sb;
    }

    @Override // com.a.a.c.j
    public String toString() {
        StringBuilder sb = new StringBuilder(40);
        sb.append("[simple type, class ").append(I()).append(']');
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
        l lVar = (l) obj;
        if (lVar.f522a != this.f522a) {
            return false;
        }
        return this.i.equals(lVar.i);
    }
}
