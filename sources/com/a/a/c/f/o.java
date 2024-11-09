package com.a.a.c.f;

/* loaded from: infinitode-2.jar:com/a/a/c/f/o.class */
public abstract class o extends j {
    protected final aa[] c;

    public abstract int f();

    public abstract Class<?> a(int i);

    public abstract com.a.a.c.j b(int i);

    public abstract Object g();

    public abstract Object a(Object[] objArr);

    public abstract Object a(Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    public o(an anVar, aa aaVar, aa[] aaVarArr) {
        super(anVar, aaVar);
        this.c = aaVarArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final n a(int i, aa aaVar) {
        this.c[i] = aaVar;
        return c(i);
    }

    private aa d(int i) {
        if (this.c != null && i >= 0 && i < this.c.length) {
            return this.c[i];
        }
        return null;
    }

    public final n c(int i) {
        return new n(this, b(i), this.f457a, d(i), i);
    }
}
