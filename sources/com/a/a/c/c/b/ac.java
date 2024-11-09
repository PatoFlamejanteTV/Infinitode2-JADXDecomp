package com.a.a.c.c.b;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b/ac.class */
public final class ac extends ai<StackTraceElement> {

    /* renamed from: a, reason: collision with root package name */
    private com.a.a.c.k<?> f305a;

    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/ac$a.class */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public String f306a = "";

        /* renamed from: b, reason: collision with root package name */
        public String f307b = "";
        public String c = "";
        public int d = -1;
    }

    @Deprecated
    public ac() {
        this(null);
    }

    private ac(com.a.a.c.k<?> kVar) {
        super((Class<?>) StackTraceElement.class);
        this.f305a = kVar;
    }

    public static com.a.a.c.k<?> d(com.a.a.c.g gVar) {
        if (gVar == null) {
            return new ac();
        }
        return new ac(gVar.a(gVar.b(a.class)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public StackTraceElement a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        a aVar;
        com.a.a.b.o k = lVar.k();
        if (k == com.a.a.b.o.START_OBJECT || k == com.a.a.b.o.FIELD_NAME) {
            if (this.f305a == null) {
                aVar = (a) gVar.b(lVar, a.class);
            } else {
                aVar = (a) this.f305a.a(lVar, gVar);
            }
            return a(gVar, aVar);
        }
        if (k == com.a.a.b.o.START_ARRAY && gVar.a(com.a.a.c.i.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            lVar.g();
            StackTraceElement a2 = a(lVar, gVar);
            if (lVar.g() != com.a.a.b.o.END_ARRAY) {
                j(gVar);
            }
            return a2;
        }
        return (StackTraceElement) gVar.a(this.s, lVar);
    }

    private StackTraceElement a(com.a.a.c.g gVar, a aVar) {
        return a(aVar.f306a, aVar.c, aVar.f307b, aVar.d);
    }

    private static StackTraceElement a(String str, String str2, String str3, int i) {
        return new StackTraceElement(str, str2, str3, i);
    }
}
