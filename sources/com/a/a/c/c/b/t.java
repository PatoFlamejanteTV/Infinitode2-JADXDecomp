package com.a.a.c.c.b;

import com.a.a.c.c.b.f;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b/t.class */
public final class t extends f<com.a.a.c.m> {

    /* renamed from: b, reason: collision with root package name */
    private static final t f361b = new t();

    @Override // com.a.a.c.c.b.f, com.a.a.c.c.k
    public final /* bridge */ /* synthetic */ com.a.a.c.k a(com.a.a.c.g gVar, com.a.a.c.c cVar) {
        return super.a(gVar, cVar);
    }

    @Override // com.a.a.c.c.b.f, com.a.a.c.k
    public final /* bridge */ /* synthetic */ boolean c() {
        return super.c();
    }

    @Override // com.a.a.c.c.b.f, com.a.a.c.k
    public final /* bridge */ /* synthetic */ com.a.a.c.l.f b() {
        return super.b();
    }

    @Override // com.a.a.c.c.b.f, com.a.a.c.c.b.ae, com.a.a.c.k
    public final /* bridge */ /* synthetic */ Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
        return super.a(lVar, gVar, eVar);
    }

    @Override // com.a.a.c.k, com.a.a.c.c.s
    public final /* synthetic */ Object a(com.a.a.c.g gVar) {
        return d(gVar);
    }

    protected t() {
        super(com.a.a.c.m.class, null);
    }

    private t(t tVar, boolean z, boolean z2) {
        super(tVar, z, z2);
    }

    @Override // com.a.a.c.c.b.f
    protected final com.a.a.c.k<?> a(boolean z, boolean z2) {
        return new t(this, z, z2);
    }

    public static com.a.a.c.k<? extends com.a.a.c.m> a(Class<?> cls) {
        if (cls == com.a.a.c.j.r.class) {
            return b.g();
        }
        if (cls == com.a.a.c.j.a.class) {
            return a.g();
        }
        return f361b;
    }

    private static com.a.a.c.m d(com.a.a.c.g gVar) {
        gVar.l();
        return com.a.a.c.j.l.a();
    }

    @Override // com.a.a.c.k, com.a.a.c.c.s
    public final Object b(com.a.a.c.g gVar) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public com.a.a.c.m a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        f.a aVar = new f.a();
        com.a.a.c.j.l l = gVar.l();
        switch (lVar.l()) {
            case 1:
                return a(lVar, gVar, l, aVar, l.d());
            case 2:
                return l.d();
            case 3:
                return a(lVar, gVar, l, aVar, l.c());
            case 4:
            default:
                return b(lVar, gVar);
            case 5:
                return a(lVar, gVar, l, aVar);
        }
    }

    @Override // com.a.a.c.c.b.f, com.a.a.c.k
    public final Boolean a(com.a.a.c.f fVar) {
        return this.f335a;
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/t$b.class */
    static final class b extends f<com.a.a.c.j.r> {

        /* renamed from: b, reason: collision with root package name */
        private static b f363b = new b();

        protected b() {
            super(com.a.a.c.j.r.class, Boolean.TRUE);
        }

        public static b g() {
            return f363b;
        }

        private b(b bVar, boolean z, boolean z2) {
            super(bVar, z, z2);
        }

        @Override // com.a.a.c.c.b.f
        protected final com.a.a.c.k<?> a(boolean z, boolean z2) {
            return new b(this, z, z2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.k
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public com.a.a.c.j.r a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            com.a.a.c.j.l l = gVar.l();
            if (lVar.q()) {
                com.a.a.c.j.r d = l.d();
                a(lVar, gVar, l, new f.a(), d);
                return d;
            }
            if (lVar.a(com.a.a.b.o.FIELD_NAME)) {
                return a(lVar, gVar, l, new f.a());
            }
            if (lVar.a(com.a.a.b.o.END_OBJECT)) {
                return l.d();
            }
            return (com.a.a.c.j.r) gVar.a(com.a.a.c.j.r.class, lVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.k
        public com.a.a.c.j.r a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.j.r rVar) {
            if (lVar.q() || lVar.a(com.a.a.b.o.FIELD_NAME)) {
                return (com.a.a.c.j.r) a(lVar, gVar, rVar, new f.a());
            }
            return (com.a.a.c.j.r) gVar.a(com.a.a.c.j.r.class, lVar);
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/t$a.class */
    static final class a extends f<com.a.a.c.j.a> {

        /* renamed from: b, reason: collision with root package name */
        private static a f362b = new a();

        protected a() {
            super(com.a.a.c.j.a.class, Boolean.TRUE);
        }

        public static a g() {
            return f362b;
        }

        private a(a aVar, boolean z, boolean z2) {
            super(aVar, z, z2);
        }

        @Override // com.a.a.c.c.b.f
        protected final com.a.a.c.k<?> a(boolean z, boolean z2) {
            return new a(this, z, z2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.k
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public com.a.a.c.j.a a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            if (lVar.p()) {
                com.a.a.c.j.l l = gVar.l();
                com.a.a.c.j.a c = l.c();
                a(lVar, gVar, l, new f.a(), c);
                return c;
            }
            return (com.a.a.c.j.a) gVar.a(com.a.a.c.j.a.class, lVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.k
        public com.a.a.c.j.a a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.j.a aVar) {
            if (lVar.p()) {
                a(lVar, gVar, gVar.l(), new f.a(), aVar);
                return aVar;
            }
            return (com.a.a.c.j.a) gVar.a(com.a.a.c.j.a.class, lVar);
        }
    }
}
