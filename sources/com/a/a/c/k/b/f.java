package com.a.a.c.k.b;

import com.a.a.a.l;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/k/b/f.class */
public final class f extends an<Object> implements com.a.a.c.k.k {

    /* renamed from: a, reason: collision with root package name */
    private boolean f619a;

    public f(boolean z) {
        super(z ? Boolean.TYPE : Boolean.class, (byte) 0);
        this.f619a = z;
    }

    @Override // com.a.a.c.k.k
    public final com.a.a.c.o<?> a(com.a.a.c.aa aaVar, com.a.a.c.c cVar) {
        l.d a2 = a(aaVar, cVar, (Class<?>) a());
        if (a2 != null) {
            l.c c = a2.c();
            if (c.a()) {
                return new a(this.f619a);
            }
            if (c == l.c.STRING) {
                return new as(this.h);
            }
        }
        return this;
    }

    @Override // com.a.a.c.k.b.ao, com.a.a.c.o
    public final void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        hVar.a(Boolean.TRUE.equals(obj));
    }

    @Override // com.a.a.c.k.b.an, com.a.a.c.o
    public final void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar, com.a.a.c.i.i iVar) {
        hVar.a(Boolean.TRUE.equals(obj));
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/k/b/f$a.class */
    static final class a extends an<Object> implements com.a.a.c.k.k {

        /* renamed from: a, reason: collision with root package name */
        private boolean f620a;

        public a(boolean z) {
            super(z ? Boolean.TYPE : Boolean.class, (byte) 0);
            this.f620a = z;
        }

        @Override // com.a.a.c.k.b.ao, com.a.a.c.o
        public final void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            hVar.c(Boolean.FALSE.equals(obj) ? 0 : 1);
        }

        @Override // com.a.a.c.k.b.an, com.a.a.c.o
        public final void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar, com.a.a.c.i.i iVar) {
            hVar.a(Boolean.TRUE.equals(obj));
        }

        @Override // com.a.a.c.k.k
        public final com.a.a.c.o<?> a(com.a.a.c.aa aaVar, com.a.a.c.c cVar) {
            l.d a2 = a(aaVar, cVar, (Class<?>) Boolean.class);
            if (a2 != null && !a2.c().a()) {
                return new f(this.f620a);
            }
            return this;
        }
    }
}
