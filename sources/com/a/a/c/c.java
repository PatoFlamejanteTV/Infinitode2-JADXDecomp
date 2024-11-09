package com.a.a.c;

import com.a.a.a.l;
import com.a.a.a.s;
import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/c/c.class */
public interface c extends com.a.a.c.m.v {

    /* renamed from: a, reason: collision with root package name */
    public static final l.d f245a = new l.d();

    @Override // com.a.a.c.m.v
    String a();

    w b();

    j c();

    v d();

    com.a.a.c.f.j e();

    l.d a(com.a.a.c.b.q<?> qVar, Class<?> cls);

    s.b b(com.a.a.c.b.q<?> qVar, Class<?> cls);

    static {
        s.b.a();
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c$b.class */
    public static class b implements c, Serializable {
        private w c;
        private j d;
        private w e;
        private v f;

        /* renamed from: b, reason: collision with root package name */
        protected final com.a.a.c.f.j f294b;

        public b(w wVar, j jVar, w wVar2, com.a.a.c.f.j jVar2, v vVar) {
            this.c = wVar;
            this.d = jVar;
            this.e = wVar2;
            this.f = vVar;
            this.f294b = jVar2;
        }

        @Override // com.a.a.c.c
        public final l.d a(com.a.a.c.b.q<?> qVar, Class<?> cls) {
            l.d f = qVar.f(cls);
            com.a.a.c.a j = qVar.j();
            if (j == null || this.f294b == null) {
                return f;
            }
            l.d h = j.h((com.a.a.c.f.b) this.f294b);
            if (h == null) {
                return f;
            }
            return f.a(h);
        }

        @Override // com.a.a.c.c
        public final s.b b(com.a.a.c.b.q<?> qVar, Class<?> cls) {
            s.b a2 = qVar.a(cls, this.d.b());
            com.a.a.c.a j = qVar.j();
            if (j == null || this.f294b == null) {
                return a2;
            }
            s.b t = j.t(this.f294b);
            if (t == null) {
                return a2;
            }
            return a2.a(t);
        }

        @Override // com.a.a.c.c, com.a.a.c.m.v
        public final String a() {
            return this.c.b();
        }

        @Override // com.a.a.c.c
        public final w b() {
            return this.c;
        }

        @Override // com.a.a.c.c
        public final j c() {
            return this.d;
        }

        public final w f() {
            return this.e;
        }

        @Override // com.a.a.c.c
        public final v d() {
            return this.f;
        }

        @Override // com.a.a.c.c
        public final com.a.a.c.f.j e() {
            return this.f294b;
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c$a.class */
    public static class a implements c {
        @Override // com.a.a.c.c, com.a.a.c.m.v
        public final String a() {
            return "";
        }

        @Override // com.a.a.c.c
        public final w b() {
            return w.f771b;
        }

        @Override // com.a.a.c.c
        public final j c() {
            return com.a.a.c.l.o.b();
        }

        @Override // com.a.a.c.c
        public final v d() {
            return v.c;
        }

        @Override // com.a.a.c.c
        public final com.a.a.c.f.j e() {
            return null;
        }

        @Override // com.a.a.c.c
        public final l.d a(com.a.a.c.b.q<?> qVar, Class<?> cls) {
            return l.d.a();
        }

        @Override // com.a.a.c.c
        public final s.b b(com.a.a.c.b.q<?> qVar, Class<?> cls) {
            return null;
        }
    }
}
