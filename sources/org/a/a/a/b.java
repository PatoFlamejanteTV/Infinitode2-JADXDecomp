package org.a.a.a;

/* loaded from: infinitode-2.jar:org/a/a/a/b.class */
public final class b extends RuntimeException {

    /* renamed from: a, reason: collision with root package name */
    private Throwable f4181a;

    public b() {
        this.f4181a = null;
    }

    public b(Throwable th) {
        this(th.toString(), th);
    }

    public b(String str, Throwable th) {
        super(new StringBuffer().append(str).append(" (Caused by ").append(th).append(")").toString());
        this.f4181a = null;
        this.f4181a = th;
    }

    @Override // java.lang.Throwable
    public final Throwable getCause() {
        return this.f4181a;
    }
}
