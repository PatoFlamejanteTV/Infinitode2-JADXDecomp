package com.a.a.c.k.a;

/* loaded from: infinitode-2.jar:com/a/a/c/k/a/l.class */
public final class l {

    /* renamed from: a, reason: collision with root package name */
    private final a[] f572a;

    /* renamed from: b, reason: collision with root package name */
    private final int f573b;
    private final int c;

    private l(com.a.a.c.m.o<com.a.a.c.m.n, com.a.a.c.o<Object>> oVar) {
        this.f573b = a(oVar.a());
        this.c = this.f573b - 1;
        a[] aVarArr = new a[this.f573b];
        oVar.a((nVar, oVar2) -> {
            int hashCode = nVar.hashCode() & this.c;
            aVarArr[hashCode] = new a(aVarArr[hashCode], nVar, oVar2);
        });
        this.f572a = aVarArr;
    }

    private static final int a(int i) {
        int i2 = 8;
        while (true) {
            int i3 = i2;
            if (i3 < (i <= 64 ? i + i : i + (i >> 2))) {
                i2 = i3 + i3;
            } else {
                return i3;
            }
        }
    }

    public static l a(com.a.a.c.m.o<com.a.a.c.m.n, com.a.a.c.o<Object>> oVar) {
        return new l(oVar);
    }

    public final com.a.a.c.o<Object> a(com.a.a.c.j jVar) {
        a aVar = this.f572a[com.a.a.c.m.n.b(jVar) & this.c];
        a aVar2 = aVar;
        if (aVar == null) {
            return null;
        }
        if (aVar2.a(jVar)) {
            return aVar2.f574a;
        }
        do {
            a aVar3 = aVar2.f575b;
            aVar2 = aVar3;
            if (aVar3 == null) {
                return null;
            }
        } while (!aVar2.a(jVar));
        return aVar2.f574a;
    }

    public final com.a.a.c.o<Object> a(Class<?> cls) {
        a aVar = this.f572a[com.a.a.c.m.n.b(cls) & this.c];
        a aVar2 = aVar;
        if (aVar == null) {
            return null;
        }
        if (aVar2.a(cls)) {
            return aVar2.f574a;
        }
        do {
            a aVar3 = aVar2.f575b;
            aVar2 = aVar3;
            if (aVar3 == null) {
                return null;
            }
        } while (!aVar2.a(cls));
        return aVar2.f574a;
    }

    public final com.a.a.c.o<Object> b(com.a.a.c.j jVar) {
        a aVar = this.f572a[com.a.a.c.m.n.a(jVar) & this.c];
        a aVar2 = aVar;
        if (aVar == null) {
            return null;
        }
        if (aVar2.b(jVar)) {
            return aVar2.f574a;
        }
        do {
            a aVar3 = aVar2.f575b;
            aVar2 = aVar3;
            if (aVar3 == null) {
                return null;
            }
        } while (!aVar2.b(jVar));
        return aVar2.f574a;
    }

    public final com.a.a.c.o<Object> b(Class<?> cls) {
        a aVar = this.f572a[com.a.a.c.m.n.a(cls) & this.c];
        a aVar2 = aVar;
        if (aVar == null) {
            return null;
        }
        if (aVar2.b(cls)) {
            return aVar2.f574a;
        }
        do {
            a aVar3 = aVar2.f575b;
            aVar2 = aVar3;
            if (aVar3 == null) {
                return null;
            }
        } while (!aVar2.b(cls));
        return aVar2.f574a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/c/k/a/l$a.class */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public final com.a.a.c.o<Object> f574a;

        /* renamed from: b, reason: collision with root package name */
        public final a f575b;
        private Class<?> c;
        private com.a.a.c.j d;
        private boolean e;

        public a(a aVar, com.a.a.c.m.n nVar, com.a.a.c.o<Object> oVar) {
            this.f575b = aVar;
            this.f574a = oVar;
            this.e = nVar.a();
            this.c = nVar.b();
            this.d = nVar.c();
        }

        public final boolean a(Class<?> cls) {
            return this.c == cls && this.e;
        }

        public final boolean b(Class<?> cls) {
            return this.c == cls && !this.e;
        }

        public final boolean a(com.a.a.c.j jVar) {
            return this.e && jVar.equals(this.d);
        }

        public final boolean b(com.a.a.c.j jVar) {
            return !this.e && jVar.equals(this.d);
        }
    }
}
