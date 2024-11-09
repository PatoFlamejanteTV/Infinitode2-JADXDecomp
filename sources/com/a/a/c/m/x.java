package com.a.a.c.m;

/* loaded from: infinitode-2.jar:com/a/a/c/m/x.class */
public abstract class x<T> {

    /* renamed from: a, reason: collision with root package name */
    private T f748a;

    /* renamed from: b, reason: collision with root package name */
    private a<T> f749b;
    private a<T> c;
    private int d;

    protected abstract T a(int i);

    public final int a() {
        return this.d;
    }

    public final T b() {
        c();
        return this.f748a == null ? a(12) : this.f748a;
    }

    public final T a(T t, int i) {
        int i2;
        a<T> aVar = new a<>(t, i);
        if (this.f749b == null) {
            this.c = aVar;
            this.f749b = aVar;
        } else {
            this.c.a(aVar);
            this.c = aVar;
        }
        this.d += i;
        if (i < 16384) {
            i2 = i + i;
        } else {
            i2 = i + (i >> 2);
        }
        return a(i2);
    }

    public final T b(T t, int i) {
        int i2 = i + this.d;
        T a2 = a(i2);
        int i3 = 0;
        a<T> aVar = this.f749b;
        while (true) {
            a<T> aVar2 = aVar;
            if (aVar2 == null) {
                break;
            }
            i3 = aVar2.a(a2, i3);
            aVar = aVar2.b();
        }
        System.arraycopy(t, 0, a2, i3, i);
        int i4 = i3 + i;
        if (i4 != i2) {
            throw new IllegalStateException("Should have gotten " + i2 + " entries, got " + i4);
        }
        return a2;
    }

    private void c() {
        if (this.c != null) {
            this.f748a = this.c.a();
        }
        this.c = null;
        this.f749b = null;
        this.d = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/c/m/x$a.class */
    public static final class a<T> {

        /* renamed from: a, reason: collision with root package name */
        private T f750a;

        /* renamed from: b, reason: collision with root package name */
        private int f751b;
        private a<T> c;

        public a(T t, int i) {
            this.f750a = t;
            this.f751b = i;
        }

        public final T a() {
            return this.f750a;
        }

        public final int a(T t, int i) {
            System.arraycopy(this.f750a, 0, t, i, this.f751b);
            return i + this.f751b;
        }

        public final a<T> b() {
            return this.c;
        }

        public final void a(a<T> aVar) {
            if (this.c != null) {
                throw new IllegalStateException();
            }
            this.c = aVar;
        }
    }
}
