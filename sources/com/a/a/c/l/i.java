package com.a.a.c.l;

/* loaded from: infinitode-2.jar:com/a/a/c/l/i.class */
public final class i extends m {
    private int e;
    private com.a.a.c.j f;

    public i(int i) {
        super(Object.class, n.a(), o.b(), null, 1, null, null, false);
        this.e = i;
    }

    public final com.a.a.c.j H() {
        return this.f;
    }

    public final void e(com.a.a.c.j jVar) {
        this.f = jVar;
    }

    @Override // com.a.a.c.l.m
    protected final String I() {
        return toString();
    }

    @Override // com.a.a.c.j
    public final StringBuilder a(StringBuilder sb) {
        return b(sb);
    }

    @Override // com.a.a.c.j
    public final StringBuilder b(StringBuilder sb) {
        sb.append('$').append(this.e + 1);
        return sb;
    }

    @Override // com.a.a.c.j
    public final com.a.a.c.j a(Object obj) {
        return (com.a.a.c.j) J();
    }

    @Override // com.a.a.c.j
    public final com.a.a.c.j b(Object obj) {
        return (com.a.a.c.j) J();
    }

    @Override // com.a.a.c.j
    public final com.a.a.c.j c(Object obj) {
        return (com.a.a.c.j) J();
    }

    @Override // com.a.a.c.j
    public final com.a.a.c.j d(Object obj) {
        return (com.a.a.c.j) J();
    }

    @Override // com.a.a.c.j
    public final com.a.a.c.j a(com.a.a.c.j jVar) {
        return (com.a.a.c.j) J();
    }

    @Override // com.a.a.c.j
    public final com.a.a.c.j a() {
        return (com.a.a.c.j) J();
    }

    @Override // com.a.a.c.j
    public final com.a.a.c.j a(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr) {
        return (com.a.a.c.j) J();
    }

    @Override // com.a.a.c.j
    public final boolean n() {
        return false;
    }

    @Override // com.a.a.c.j
    public final String toString() {
        return b(new StringBuilder()).toString();
    }

    @Override // com.a.a.c.j
    public final boolean equals(Object obj) {
        return obj == this;
    }

    private <T> T J() {
        throw new UnsupportedOperationException("Operation should not be attempted on " + getClass().getName());
    }
}
