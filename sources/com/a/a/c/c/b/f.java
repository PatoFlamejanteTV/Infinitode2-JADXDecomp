package com.a.a.c.c.b;

import com.a.a.b.l;
import com.a.a.c.m;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b/f.class */
abstract class f<T extends com.a.a.c.m> extends ae<T> implements com.a.a.c.c.k {

    /* renamed from: a, reason: collision with root package name */
    protected final Boolean f335a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f336b;
    private boolean c;

    protected abstract com.a.a.c.k<?> a(boolean z, boolean z2);

    public f(Class<T> cls, Boolean bool) {
        super((Class<?>) cls);
        this.f335a = bool;
        this.f336b = true;
        this.c = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public f(f<?> fVar, boolean z, boolean z2) {
        super(fVar);
        this.f335a = fVar.f335a;
        this.f336b = z;
        this.c = z2;
    }

    @Override // com.a.a.c.c.b.ae, com.a.a.c.k
    public Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
        return eVar.d(lVar, gVar);
    }

    @Override // com.a.a.c.k
    public com.a.a.c.l.f b() {
        return com.a.a.c.l.f.Untyped;
    }

    @Override // com.a.a.c.k
    public boolean c() {
        return true;
    }

    @Override // com.a.a.c.k
    public Boolean a(com.a.a.c.f fVar) {
        return this.f335a;
    }

    @Override // com.a.a.c.c.k
    public com.a.a.c.k<?> a(com.a.a.c.g gVar, com.a.a.c.c cVar) {
        com.a.a.c.f a2 = gVar.a();
        Boolean g = a2.g(com.a.a.c.j.a.class);
        Boolean g2 = a2.g(com.a.a.c.j.r.class);
        Boolean g3 = a2.g(com.a.a.c.m.class);
        boolean a3 = a(g, g3);
        boolean a4 = a(g2, g3);
        if (a3 != this.f336b || a4 != this.c) {
            return a(a3, a4);
        }
        return this;
    }

    private static boolean a(Boolean bool, Boolean bool2) {
        if (bool != null) {
            return bool.booleanValue();
        }
        if (bool2 != null) {
            return bool2.booleanValue();
        }
        return true;
    }

    private static void a(com.a.a.c.g gVar, com.a.a.c.j.l lVar, String str, com.a.a.c.j.r rVar, com.a.a.c.m mVar, com.a.a.c.m mVar2) {
        if (gVar.a(com.a.a.c.i.FAIL_ON_READING_DUP_TREE_KEY)) {
            gVar.a(com.a.a.c.m.class, "Duplicate field '%s' for `ObjectNode`: not allowed when `DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY` enabled", str);
        }
        if (gVar.a(com.a.a.b.s.DUPLICATE_PROPERTIES)) {
            if (mVar.b()) {
                ((com.a.a.c.j.a) mVar).a(mVar2);
                rVar.b(str, mVar);
            } else {
                com.a.a.c.j.a c = lVar.c();
                c.a(mVar);
                c.a(mVar2);
                rVar.b(str, c);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.c.j.r a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.j.l lVar2, a aVar) {
        com.a.a.c.m b2;
        com.a.a.c.j.r d = lVar2.d();
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
                        b2 = a(lVar, gVar, lVar2, aVar, lVar2.d());
                        break;
                    case 3:
                        b2 = a(lVar, gVar, lVar2, aVar, lVar2.c());
                        break;
                    default:
                        b2 = b(lVar, gVar);
                        break;
                }
                com.a.a.c.m b3 = d.b(str, b2);
                if (b3 != null) {
                    a(gVar, lVar2, str, d, b3, b2);
                }
                v = lVar.h();
            } else {
                return d;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public final com.a.a.c.m a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.j.r rVar, a aVar) {
        String v;
        com.a.a.c.m c;
        if (lVar.q()) {
            v = lVar.h();
        } else {
            if (!lVar.a(com.a.a.b.o.FIELD_NAME)) {
                return (com.a.a.c.m) a(lVar, gVar);
            }
            v = lVar.v();
        }
        com.a.a.c.j.l l = gVar.l();
        while (v != null) {
            com.a.a.b.o g = lVar.g();
            com.a.a.c.m a2 = rVar.a(v);
            if (a2 != null) {
                if (a2 instanceof com.a.a.c.j.r) {
                    if (g == com.a.a.b.o.START_OBJECT && this.c) {
                        com.a.a.c.m a3 = a(lVar, gVar, (com.a.a.c.j.r) a2, aVar);
                        if (a3 != a2) {
                            rVar.a(v, a3);
                        }
                    }
                } else if ((a2 instanceof com.a.a.c.j.a) && g == com.a.a.b.o.START_ARRAY && this.f336b) {
                    a(lVar, gVar, l, aVar, (com.a.a.c.j.a) a2);
                }
                v = lVar.h();
            }
            if (g == null) {
                g = com.a.a.b.o.NOT_AVAILABLE;
            }
            switch (g.a()) {
                case 1:
                    c = a(lVar, gVar, l, aVar, l.d());
                    break;
                case 2:
                case 4:
                case 5:
                case 8:
                default:
                    c = c(lVar, gVar);
                    break;
                case 3:
                    c = a(lVar, gVar, l, aVar, l.c());
                    break;
                case 6:
                    c = com.a.a.c.j.l.a(lVar.w());
                    break;
                case 7:
                    c = a(lVar, gVar, l);
                    break;
                case 9:
                    c = com.a.a.c.j.l.a(true);
                    break;
                case 10:
                    c = com.a.a.c.j.l.a(false);
                    break;
                case 11:
                    if (!gVar.a(com.a.a.c.b.o.READ_NULL_PROPERTIES)) {
                        break;
                    } else {
                        c = com.a.a.c.j.l.a();
                        break;
                    }
            }
            rVar.a(v, c);
            v = lVar.h();
        }
        return rVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Failed to find 'out' block for switch in B:11:0x003c. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:52:0x016d. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0146 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.a.a.c.j.f<?> a(com.a.a.b.l r8, com.a.a.c.g r9, com.a.a.c.j.l r10, com.a.a.c.c.b.f.a r11, com.a.a.c.j.f<?> r12) {
        /*
            Method dump skipped, instructions count: 590
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.c.c.b.f.a(com.a.a.b.l, com.a.a.c.g, com.a.a.c.j.l, com.a.a.c.c.b.f$a, com.a.a.c.j.f):com.a.a.c.j.f");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.c.m b(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        com.a.a.c.j.l l = gVar.l();
        switch (lVar.l()) {
            case 2:
                return l.d();
            case 3:
            case 4:
            case 5:
            default:
                return (com.a.a.c.m) gVar.a(a(), lVar);
            case 6:
                return com.a.a.c.j.l.a(lVar.w());
            case 7:
                return a(lVar, gVar, l);
            case 8:
                return b(lVar, gVar, l);
            case 9:
                return com.a.a.c.j.l.a(true);
            case 10:
                return com.a.a.c.j.l.a(false);
            case 11:
                return com.a.a.c.j.l.a();
            case 12:
                return e(lVar, gVar);
        }
    }

    private com.a.a.c.m c(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        switch (lVar.l()) {
            case 2:
                return gVar.l().d();
            case 8:
                return b(lVar, gVar, gVar.l());
            case 12:
                return e(lVar, gVar);
            default:
                return (com.a.a.c.m) gVar.a(a(), lVar);
        }
    }

    private static com.a.a.c.m a(com.a.a.b.l lVar, int i, com.a.a.c.j.l lVar2) {
        if (i != 0) {
            if (com.a.a.c.i.USE_BIG_INTEGER_FOR_INTS.a(i)) {
                return lVar2.a(lVar.I());
            }
            return com.a.a.c.j.l.a(lVar.H());
        }
        l.b D = lVar.D();
        if (D == l.b.INT) {
            return com.a.a.c.j.l.a(lVar.G());
        }
        if (D == l.b.LONG) {
            return com.a.a.c.j.l.a(lVar.H());
        }
        return lVar2.a(lVar.I());
    }

    private static com.a.a.c.m a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.j.l lVar2) {
        l.b D;
        int i = gVar.i();
        if ((i & r) != 0) {
            if (!com.a.a.c.i.USE_BIG_INTEGER_FOR_INTS.a(i)) {
                if (com.a.a.c.i.USE_LONG_FOR_INTS.a(i)) {
                    D = l.b.LONG;
                } else {
                    D = lVar.D();
                }
            } else {
                D = l.b.BIG_INTEGER;
            }
        } else {
            D = lVar.D();
        }
        if (D == l.b.INT) {
            return com.a.a.c.j.l.a(lVar.G());
        }
        if (D == l.b.LONG) {
            return com.a.a.c.j.l.a(lVar.H());
        }
        return lVar2.a(lVar.I());
    }

    private static com.a.a.c.m b(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.j.l lVar2) {
        l.b D = lVar.D();
        if (D == l.b.BIG_DECIMAL) {
            return lVar2.a(lVar.L());
        }
        if (gVar.a(com.a.a.c.i.USE_BIG_DECIMAL_FOR_FLOATS)) {
            if (lVar.s()) {
                return com.a.a.c.j.l.a(lVar.K());
            }
            return lVar2.a(lVar.L());
        }
        if (D == l.b.FLOAT) {
            return com.a.a.c.j.l.a(lVar.J());
        }
        return com.a.a.c.j.l.a(lVar.K());
    }

    private static com.a.a.c.m e(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        gVar.l();
        Object N = lVar.N();
        if (N == null) {
            return com.a.a.c.j.l.a();
        }
        if (N.getClass() == byte[].class) {
            return com.a.a.c.j.l.a((byte[]) N);
        }
        if (N instanceof com.a.a.c.m.y) {
            return com.a.a.c.j.l.a((com.a.a.c.m.y) N);
        }
        if (N instanceof com.a.a.c.m) {
            return (com.a.a.c.m) N;
        }
        return com.a.a.c.j.l.a(N);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/f$a.class */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private com.a.a.c.j.f[] f337a;

        /* renamed from: b, reason: collision with root package name */
        private int f338b;
        private int c;

        public final void a(com.a.a.c.j.f fVar) {
            if (this.f338b < this.c) {
                com.a.a.c.j.f[] fVarArr = this.f337a;
                int i = this.f338b;
                this.f338b = i + 1;
                fVarArr[i] = fVar;
                return;
            }
            if (this.f337a == null) {
                this.c = 10;
                this.f337a = new com.a.a.c.j.f[this.c];
            } else {
                this.c += Math.min(4000, Math.max(20, this.c >> 1));
                this.f337a = (com.a.a.c.j.f[]) Arrays.copyOf(this.f337a, this.c);
            }
            com.a.a.c.j.f[] fVarArr2 = this.f337a;
            int i2 = this.f338b;
            this.f338b = i2 + 1;
            fVarArr2[i2] = fVar;
        }

        public final com.a.a.c.j.f a() {
            if (this.f338b == 0) {
                return null;
            }
            com.a.a.c.j.f[] fVarArr = this.f337a;
            int i = this.f338b - 1;
            this.f338b = i;
            return fVarArr[i];
        }
    }
}
