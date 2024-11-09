package com.a.a.c.k.a;

import com.a.a.c.aa;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/a/a/c/k/a/k.class */
public abstract class k {

    /* renamed from: a, reason: collision with root package name */
    protected final boolean f563a;

    public abstract com.a.a.c.o<Object> a(Class<?> cls);

    public abstract k b(Class<?> cls, com.a.a.c.o<Object> oVar);

    protected k(boolean z) {
        this.f563a = z;
    }

    protected k(k kVar) {
        this.f563a = kVar.f563a;
    }

    public final d a(Class<?> cls, aa aaVar, com.a.a.c.c cVar) {
        com.a.a.c.o<Object> b2 = aaVar.b(cls, cVar);
        return new d(b2, b(cls, b2));
    }

    public final d a(com.a.a.c.j jVar, aa aaVar, com.a.a.c.c cVar) {
        com.a.a.c.o<Object> b2 = aaVar.b(jVar, cVar);
        return new d(b2, b(jVar.b(), b2));
    }

    public final d b(Class<?> cls, aa aaVar, com.a.a.c.c cVar) {
        com.a.a.c.o<Object> c2 = aaVar.c(cls, cVar);
        return new d(c2, b(cls, c2));
    }

    public final d b(com.a.a.c.j jVar, aa aaVar, com.a.a.c.c cVar) {
        com.a.a.c.o<Object> c2 = aaVar.c(jVar, cVar);
        return new d(c2, b(jVar.b(), c2));
    }

    public final d c(Class<?> cls, aa aaVar, com.a.a.c.c cVar) {
        com.a.a.c.o<Object> d2 = aaVar.d(cls, cVar);
        return new d(d2, b(cls, d2));
    }

    public final d a(Class<?> cls, com.a.a.c.o<Object> oVar) {
        return new d(oVar, b(cls, oVar));
    }

    public final d a(com.a.a.c.j jVar, com.a.a.c.o<Object> oVar) {
        return new d(oVar, b(jVar.b(), oVar));
    }

    public static k a() {
        return b.f565b;
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/k/a/k$d.class */
    public static final class d {

        /* renamed from: a, reason: collision with root package name */
        public final com.a.a.c.o<Object> f567a;

        /* renamed from: b, reason: collision with root package name */
        public final k f568b;

        public d(com.a.a.c.o<Object> oVar, k kVar) {
            this.f567a = oVar;
            this.f568b = kVar;
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/k/a/k$f.class */
    static final class f {

        /* renamed from: a, reason: collision with root package name */
        public final Class<?> f570a;

        /* renamed from: b, reason: collision with root package name */
        public final com.a.a.c.o<Object> f571b;

        public f(Class<?> cls, com.a.a.c.o<Object> oVar) {
            this.f570a = cls;
            this.f571b = oVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/c/k/a/k$b.class */
    public static final class b extends k {

        /* renamed from: b, reason: collision with root package name */
        public static final b f565b = new b(false);

        static {
            new b(true);
        }

        private b(boolean z) {
            super(z);
        }

        @Override // com.a.a.c.k.a.k
        public final com.a.a.c.o<Object> a(Class<?> cls) {
            return null;
        }

        @Override // com.a.a.c.k.a.k
        public final k b(Class<?> cls, com.a.a.c.o<Object> oVar) {
            return new e(this, cls, oVar);
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/k/a/k$e.class */
    static final class e extends k {

        /* renamed from: b, reason: collision with root package name */
        private final Class<?> f569b;
        private final com.a.a.c.o<Object> c;

        public e(k kVar, Class<?> cls, com.a.a.c.o<Object> oVar) {
            super(kVar);
            this.f569b = cls;
            this.c = oVar;
        }

        @Override // com.a.a.c.k.a.k
        public final com.a.a.c.o<Object> a(Class<?> cls) {
            if (cls == this.f569b) {
                return this.c;
            }
            return null;
        }

        @Override // com.a.a.c.k.a.k
        public final k b(Class<?> cls, com.a.a.c.o<Object> oVar) {
            return new a(this, this.f569b, this.c, cls, oVar);
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/k/a/k$a.class */
    static final class a extends k {

        /* renamed from: b, reason: collision with root package name */
        private final Class<?> f564b;
        private final Class<?> c;
        private final com.a.a.c.o<Object> d;
        private final com.a.a.c.o<Object> e;

        public a(k kVar, Class<?> cls, com.a.a.c.o<Object> oVar, Class<?> cls2, com.a.a.c.o<Object> oVar2) {
            super(kVar);
            this.f564b = cls;
            this.d = oVar;
            this.c = cls2;
            this.e = oVar2;
        }

        @Override // com.a.a.c.k.a.k
        public final com.a.a.c.o<Object> a(Class<?> cls) {
            if (cls == this.f564b) {
                return this.d;
            }
            if (cls == this.c) {
                return this.e;
            }
            return null;
        }

        @Override // com.a.a.c.k.a.k
        public final k b(Class<?> cls, com.a.a.c.o<Object> oVar) {
            return new c(this, new f[]{new f(this.f564b, this.d), new f(this.c, this.e), new f(cls, oVar)});
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/k/a/k$c.class */
    static final class c extends k {

        /* renamed from: b, reason: collision with root package name */
        private final f[] f566b;

        public c(k kVar, f[] fVarArr) {
            super(kVar);
            this.f566b = fVarArr;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to find 'out' block for switch in B:15:0x0041. Please report as an issue. */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0089  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x009d  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x00b1  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x00c5  */
        /* JADX WARN: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
        @Override // com.a.a.c.k.a.k
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final com.a.a.c.o<java.lang.Object> a(java.lang.Class<?> r4) {
            /*
                Method dump skipped, instructions count: 204
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.a.a.c.k.a.k.c.a(java.lang.Class):com.a.a.c.o");
        }

        @Override // com.a.a.c.k.a.k
        public final k b(Class<?> cls, com.a.a.c.o<Object> oVar) {
            int length = this.f566b.length;
            if (length != 8) {
                f[] fVarArr = (f[]) Arrays.copyOf(this.f566b, length + 1);
                fVarArr[length] = new f(cls, oVar);
                return new c(this, fVarArr);
            }
            if (this.f563a) {
                return new e(this, cls, oVar);
            }
            return this;
        }
    }
}
