package com.a.a.c.k.a;

import com.a.a.a.al;
import com.a.a.a.an;
import com.a.a.c.aa;
import com.a.a.c.c.x;
import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/c/k/a/d.class */
public abstract class d {
    public abstract Object c();

    public abstract com.a.a.c.k<?> d();

    public abstract com.a.a.c.p e();

    public abstract com.a.a.c.o<?> f();

    public abstract com.a.a.c.i.h<?> g();

    public abstract com.a.a.c.i.g h();

    @Deprecated
    public abstract com.a.a.c.k.d p();

    public abstract com.a.a.c.j q();

    public static com.a.a.c.k.e a(com.a.a.c.k.e eVar, Class<?>[] clsArr) {
        if (clsArr.length == 1) {
            return new b(eVar, clsArr[0]);
        }
        return new a(eVar, clsArr);
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/k/a/d$b.class */
    static final class b extends com.a.a.c.k.e implements Serializable {
        private com.a.a.c.k.e j;
        private Class<?> k;

        protected b(com.a.a.c.k.e eVar, Class<?> cls) {
            super(eVar);
            this.j = eVar;
            this.k = cls;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.k.e
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public b a(com.a.a.c.m.r rVar) {
            return new b(this.j.a(rVar), this.k);
        }

        @Override // com.a.a.c.k.e
        public final void a(com.a.a.c.o<Object> oVar) {
            this.j.a(oVar);
        }

        @Override // com.a.a.c.k.e
        public final void b(com.a.a.c.o<Object> oVar) {
            this.j.b(oVar);
        }

        @Override // com.a.a.c.k.e, com.a.a.c.k.p
        public final void a(Object obj, com.a.a.b.h hVar, aa aaVar) {
            Class<?> e = aaVar.e();
            if (e == null || this.k.isAssignableFrom(e)) {
                this.j.a(obj, hVar, aaVar);
            } else {
                this.j.a(hVar);
            }
        }

        @Override // com.a.a.c.k.e
        public final void b(Object obj, com.a.a.b.h hVar, aa aaVar) {
            Class<?> e = aaVar.e();
            if (e == null || this.k.isAssignableFrom(e)) {
                this.j.b(obj, hVar, aaVar);
            } else {
                this.j.a(hVar, aaVar);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/k/a/d$a.class */
    static final class a extends com.a.a.c.k.e implements Serializable {
        private com.a.a.c.k.e j;
        private Class<?>[] k;

        protected a(com.a.a.c.k.e eVar, Class<?>[] clsArr) {
            super(eVar);
            this.j = eVar;
            this.k = clsArr;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.k.e
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public a a(com.a.a.c.m.r rVar) {
            return new a(this.j.a(rVar), this.k);
        }

        @Override // com.a.a.c.k.e
        public final void a(com.a.a.c.o<Object> oVar) {
            this.j.a(oVar);
        }

        @Override // com.a.a.c.k.e
        public final void b(com.a.a.c.o<Object> oVar) {
            this.j.b(oVar);
        }

        @Override // com.a.a.c.k.e, com.a.a.c.k.p
        public final void a(Object obj, com.a.a.b.h hVar, aa aaVar) {
            if (a(aaVar.e())) {
                this.j.a(obj, hVar, aaVar);
            } else {
                this.j.a(hVar);
            }
        }

        @Override // com.a.a.c.k.e
        public final void b(Object obj, com.a.a.b.h hVar, aa aaVar) {
            if (a(aaVar.e())) {
                this.j.b(obj, hVar, aaVar);
            } else {
                this.j.a(hVar, aaVar);
            }
        }

        private final boolean a(Class<?> cls) {
            if (cls == null) {
                return true;
            }
            int length = this.k.length;
            for (int i = 0; i < length; i++) {
                if (this.k[i].isAssignableFrom(cls)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static com.a.a.c.j a() {
        return null;
    }

    public static com.a.a.c.j b() {
        return null;
    }

    public static x i() {
        return null;
    }

    public static al<?> j() {
        return null;
    }

    public static an k() {
        return null;
    }

    public static com.a.a.c.x l() {
        return null;
    }

    public static com.a.a.c.m.k<?, ?> m() {
        return null;
    }

    public static com.a.a.c.k.s n() {
        return null;
    }

    public static Object o() {
        return null;
    }

    public com.a.a.c.k.o a(Object obj) {
        com.a.a.c.k.d p = p();
        if (p == null) {
            return null;
        }
        return m.a(p);
    }
}
