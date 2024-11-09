package com.a.a.c.c.b;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/c/b/ar.class */
public final class ar extends ae<Object> {

    /* renamed from: a, reason: collision with root package name */
    protected static final Object[] f329a = new Object[0];

    /* renamed from: b, reason: collision with root package name */
    private static ar f330b = new ar();
    private boolean c;

    public ar() {
        this(false);
    }

    private ar(boolean z) {
        super((Class<?>) Object.class);
        this.c = z;
    }

    public static ar a(boolean z) {
        if (z) {
            return new ar(true);
        }
        return f330b;
    }

    @Override // com.a.a.c.k
    public final com.a.a.c.l.f b() {
        return com.a.a.c.l.f.Untyped;
    }

    @Override // com.a.a.c.k
    public final Boolean a(com.a.a.c.f fVar) {
        if (this.c) {
            return Boolean.FALSE;
        }
        return null;
    }

    @Override // com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        switch (lVar.l()) {
            case 1:
                return a(lVar, gVar, a.a(gVar.a(com.a.a.b.s.DUPLICATE_PROPERTIES)));
            case 2:
                return a.g();
            case 3:
                return a(lVar, gVar, a.a());
            case 4:
            default:
                return gVar.a(e(gVar), lVar);
            case 5:
                return c(lVar, gVar);
            case 6:
                return lVar.w();
            case 7:
                if (gVar.a(r)) {
                    return u(lVar, gVar);
                }
                return lVar.B();
            case 8:
                if (gVar.a(com.a.a.c.i.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return lVar.L();
                }
                return lVar.B();
            case 9:
                return Boolean.TRUE;
            case 10:
                return Boolean.FALSE;
            case 11:
                return null;
            case 12:
                return lVar.N();
        }
    }

    @Override // com.a.a.c.c.b.ae, com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
        switch (lVar.l()) {
            case 1:
            case 3:
            case 5:
                return eVar.d(lVar, gVar);
            case 2:
            case 4:
            default:
                return a(lVar, gVar, lVar.l());
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0012. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004c  */
    @Override // com.a.a.c.k
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(com.a.a.b.l r6, com.a.a.c.g r7, java.lang.Object r8) {
        /*
            r5 = this;
            r0 = r5
            boolean r0 = r0.c
            if (r0 == 0) goto Le
            r0 = r5
            r1 = r6
            r2 = r7
            java.lang.Object r0 = r0.a(r1, r2)
            return r0
        Le:
            r0 = r6
            int r0 = r0.l()
            switch(r0) {
                case 1: goto L36;
                case 2: goto L34;
                case 3: goto La0;
                case 4: goto L34;
                case 5: goto L45;
                default: goto Ld6;
            }
        L34:
            r0 = r8
            return r0
        L36:
            r0 = r6
            com.a.a.b.o r0 = r0.g()
            r1 = r0
            r9 = r1
            com.a.a.b.o r1 = com.a.a.b.o.END_OBJECT
            if (r0 != r1) goto L45
            r0 = r8
            return r0
        L45:
            r0 = r8
            boolean r0 = r0 instanceof java.util.Map
            if (r0 == 0) goto Ld6
            r0 = r8
            java.util.Map r0 = (java.util.Map) r0
            r9 = r0
            r0 = r6
            java.lang.String r0 = r0.v()
            r10 = r0
        L58:
            r0 = r6
            com.a.a.b.o r0 = r0.g()
            r0 = r9
            r1 = r10
            java.lang.Object r0 = r0.get(r1)
            r1 = r0
            r11 = r1
            if (r0 == 0) goto L79
            r0 = r5
            r1 = r6
            r2 = r7
            r3 = r11
            java.lang.Object r0 = r0.a(r1, r2, r3)
            r12 = r0
            goto L81
        L79:
            r0 = r5
            r1 = r6
            r2 = r7
            java.lang.Object r0 = r0.a(r1, r2)
            r12 = r0
        L81:
            r0 = r12
            r1 = r11
            if (r0 == r1) goto L94
            r0 = r9
            r1 = r10
            r2 = r12
            java.lang.Object r0 = r0.put(r1, r2)
        L94:
            r0 = r6
            java.lang.String r0 = r0.h()
            r1 = r0
            r10 = r1
            if (r0 != 0) goto L58
            r0 = r8
            return r0
        La0:
            r0 = r6
            com.a.a.b.o r0 = r0.g()
            r1 = r0
            r9 = r1
            com.a.a.b.o r1 = com.a.a.b.o.END_ARRAY
            if (r0 != r1) goto Laf
            r0 = r8
            return r0
        Laf:
            r0 = r8
            boolean r0 = r0 instanceof java.util.Collection
            if (r0 == 0) goto Ld6
            r0 = r8
            java.util.Collection r0 = (java.util.Collection) r0
            r9 = r0
        Lbc:
            r0 = r9
            r1 = r5
            r2 = r6
            r3 = r7
            java.lang.Object r1 = r1.a(r2, r3)
            boolean r0 = r0.add(r1)
            r0 = r6
            com.a.a.b.o r0 = r0.g()
            com.a.a.b.o r1 = com.a.a.b.o.END_ARRAY
            if (r0 != r1) goto Lbc
            r0 = r8
            return r0
        Ld6:
            r0 = r5
            r1 = r6
            r2 = r7
            java.lang.Object r0 = r0.a(r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.c.c.b.ar.a(com.a.a.b.l, com.a.a.c.g, java.lang.Object):java.lang.Object");
    }

    private Object c(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        Object a2;
        a a3 = a.a(gVar.a(com.a.a.b.s.DUPLICATE_PROPERTIES));
        String v = lVar.v();
        while (true) {
            String str = v;
            if (str != null) {
                com.a.a.b.o g = lVar.g();
                com.a.a.b.o oVar = g;
                if (g == null) {
                    oVar = com.a.a.b.o.NOT_AVAILABLE;
                }
                switch (oVar.a()) {
                    case 1:
                        a2 = a(lVar, gVar, a3.b());
                        break;
                    case 2:
                        return a3.e();
                    case 3:
                        a2 = a(lVar, gVar, a3.c());
                        break;
                    default:
                        a2 = a(lVar, gVar, oVar.a());
                        break;
                }
                a3.a(str, a2);
                v = lVar.h();
            } else {
                return a3.e();
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:60:0x003c. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x0133. Please report as an issue. */
    private Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, a aVar) {
        Object N;
        Object N2;
        boolean a2 = gVar.a(r);
        boolean a3 = gVar.a(com.a.a.c.i.USE_JAVA_ARRAY_FOR_JSON_ARRAY);
        a aVar2 = aVar;
        while (true) {
            a aVar3 = aVar2;
            if (aVar3.d()) {
                String h = lVar.h();
                while (true) {
                    String str = h;
                    if (str != null) {
                        com.a.a.b.o g = lVar.g();
                        com.a.a.b.o oVar = g;
                        if (g == null) {
                            oVar = com.a.a.b.o.NOT_AVAILABLE;
                        }
                        switch (oVar.a()) {
                            case 1:
                                aVar3 = aVar3.a(str);
                                h = lVar.h();
                            case 2:
                            case 4:
                            case 5:
                            default:
                                return gVar.a(e(gVar), lVar);
                            case 3:
                                aVar2 = aVar3.b(str);
                                break;
                            case 6:
                                N = lVar.w();
                                aVar3.a(str, N);
                                h = lVar.h();
                            case 7:
                                N = a2 ? u(lVar, gVar) : lVar.B();
                                aVar3.a(str, N);
                                h = lVar.h();
                            case 8:
                                N = gVar.a(com.a.a.c.i.USE_BIG_DECIMAL_FOR_FLOATS) ? lVar.L() : lVar.B();
                                aVar3.a(str, N);
                                h = lVar.h();
                            case 9:
                                N = Boolean.TRUE;
                                aVar3.a(str, N);
                                h = lVar.h();
                            case 10:
                                N = Boolean.FALSE;
                                aVar3.a(str, N);
                                h = lVar.h();
                            case 11:
                                N = null;
                                aVar3.a(str, N);
                                h = lVar.h();
                            case 12:
                                N = lVar.N();
                                aVar3.a(str, N);
                                h = lVar.h();
                        }
                    } else {
                        if (aVar3 == aVar) {
                            return aVar3.e();
                        }
                        aVar2 = aVar3.f();
                    }
                }
            } else {
                while (true) {
                    com.a.a.b.o g2 = lVar.g();
                    com.a.a.b.o oVar2 = g2;
                    if (g2 == null) {
                        oVar2 = com.a.a.b.o.NOT_AVAILABLE;
                    }
                    switch (oVar2.a()) {
                        case 1:
                            aVar2 = aVar3.b();
                            break;
                        case 2:
                        case 5:
                        default:
                            return gVar.a(e(gVar), lVar);
                        case 3:
                            aVar2 = aVar3.c();
                            break;
                        case 4:
                            if (aVar3 == aVar) {
                                return aVar3.b(a3);
                            }
                            aVar2 = aVar3.c(a3);
                            break;
                        case 6:
                            N2 = lVar.w();
                            aVar3.a(N2);
                        case 7:
                            N2 = a2 ? u(lVar, gVar) : lVar.B();
                            aVar3.a(N2);
                        case 8:
                            N2 = gVar.a(com.a.a.c.i.USE_BIG_DECIMAL_FOR_FLOATS) ? lVar.L() : lVar.B();
                            aVar3.a(N2);
                        case 9:
                            N2 = Boolean.TRUE;
                            aVar3.a(N2);
                        case 10:
                            N2 = Boolean.FALSE;
                            aVar3.a(N2);
                        case 11:
                            N2 = null;
                            aVar3.a(N2);
                        case 12:
                            N2 = lVar.N();
                            aVar3.a(N2);
                    }
                }
            }
        }
    }

    private Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, int i) {
        switch (i) {
            case 6:
                return lVar.w();
            case 7:
                if (gVar.a(com.a.a.c.i.USE_BIG_INTEGER_FOR_INTS)) {
                    return lVar.I();
                }
                return lVar.B();
            case 8:
                if (gVar.a(com.a.a.c.i.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return lVar.L();
                }
                return lVar.B();
            case 9:
                return Boolean.TRUE;
            case 10:
                return Boolean.FALSE;
            case 11:
                return null;
            case 12:
                return lVar.N();
            default:
                return gVar.a(e(gVar), lVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/ar$a.class */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private final a f331a;

        /* renamed from: b, reason: collision with root package name */
        private a f332b;
        private boolean c;
        private boolean d;
        private String e;
        private Map<String, Object> f;
        private List<Object> g;

        private a(a aVar) {
            this.f331a = aVar;
            this.c = false;
            this.d = false;
        }

        private a(a aVar, boolean z, boolean z2) {
            this.f331a = aVar;
            this.c = true;
            this.d = z2;
        }

        public static a a(boolean z) {
            return new a(null, true, z);
        }

        public static a a() {
            return new a(null);
        }

        private a h() {
            this.c = false;
            return this;
        }

        private a d(boolean z) {
            this.c = true;
            this.d = z;
            return this;
        }

        public final a b() {
            if (this.f332b == null) {
                return new a(this, true, this.d);
            }
            return this.f332b.d(this.d);
        }

        public final a a(String str) {
            this.e = str;
            if (this.f332b == null) {
                return new a(this, true, this.d);
            }
            return this.f332b.d(this.d);
        }

        public final a c() {
            if (this.f332b == null) {
                return new a(this);
            }
            return this.f332b.h();
        }

        public final a b(String str) {
            this.e = str;
            if (this.f332b == null) {
                return new a(this);
            }
            return this.f332b.h();
        }

        public final boolean d() {
            return this.c;
        }

        public final void a(String str, Object obj) {
            if (this.d) {
                b(str, obj);
                return;
            }
            if (this.f == null) {
                this.f = new LinkedHashMap();
            }
            this.f.put(str, obj);
        }

        private a b(Object obj) {
            String str = (String) Objects.requireNonNull(this.e);
            this.e = null;
            if (this.d) {
                b(str, obj);
                return this;
            }
            if (this.f == null) {
                this.f = new LinkedHashMap();
            }
            this.f.put(str, obj);
            return this;
        }

        public final void a(Object obj) {
            if (this.g == null) {
                this.g = new ArrayList();
            }
            this.g.add(obj);
        }

        public final Object e() {
            if (this.f == null) {
                return g();
            }
            return this.f;
        }

        public final a f() {
            Map<String, Object> map;
            if (this.f == null) {
                map = new LinkedHashMap();
            } else {
                map = this.f;
                this.f = null;
            }
            if (this.f331a.d()) {
                return this.f331a.b(map);
            }
            this.f331a.a(map);
            return this.f331a;
        }

        public final Object b(boolean z) {
            if (this.g == null) {
                if (z) {
                    return ar.f329a;
                }
                return i();
            }
            if (z) {
                return this.g.toArray(ar.f329a);
            }
            return this.g;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v21, types: [java.lang.Object[]] */
        /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Object[]] */
        public final a c(boolean z) {
            List<Object> list;
            if (this.g == null) {
                if (z) {
                    list = ar.f329a;
                } else {
                    list = i();
                }
            } else {
                if (z) {
                    list = this.g.toArray(ar.f329a);
                } else {
                    list = this.g;
                }
                this.g = null;
            }
            if (this.f331a.d()) {
                return this.f331a.b(list);
            }
            this.f331a.a(list);
            return this.f331a;
        }

        private void b(String str, Object obj) {
            if (this.f == null) {
                this.f = new LinkedHashMap();
                this.f.put(str, obj);
                return;
            }
            Object put = this.f.put(str, obj);
            if (put != null) {
                if (put instanceof List) {
                    ((List) put).add(obj);
                    this.f.put(str, put);
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(put);
                    arrayList.add(obj);
                    this.f.put(str, arrayList);
                }
            }
        }

        public static Map<String, Object> g() {
            return new LinkedHashMap(2);
        }

        private static List<Object> i() {
            return new ArrayList(2);
        }
    }
}
