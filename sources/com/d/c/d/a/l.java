package com.d.c.d.a;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/c/d/a/l.class */
public final class l {

    /* renamed from: a, reason: collision with root package name */
    public static final BitSet f1001a = b(new com.d.c.a.c[]{com.d.c.a.c.ap, com.d.c.a.c.P, com.d.c.a.c.y, com.d.c.a.c.u, com.d.c.a.c.aQ, com.d.c.a.c.z, com.d.c.a.c.O, com.d.c.a.c.aI, com.d.c.a.c.U, com.d.c.a.c.av});

    /* renamed from: b, reason: collision with root package name */
    public static final BitSet f1002b = b(new com.d.c.a.c[]{com.d.c.a.c.bh, com.d.c.a.c.bx, com.d.c.a.c.bg});
    public static final BitSet c = b(new com.d.c.a.c[]{com.d.c.a.c.ak, com.d.c.a.c.aK, com.d.c.a.c.e});
    public static final BitSet d = b(new com.d.c.a.c[]{com.d.c.a.c.aq, com.d.c.a.c.aP});
    public static final BitSet e = b(new com.d.c.a.c[]{com.d.c.a.c.bR, com.d.c.a.c.bQ});
    public static final BitSet f = b(new com.d.c.a.c[]{com.d.c.a.c.aR, com.d.c.a.c.o, com.d.c.a.c.bS, com.d.c.a.c.bU, com.d.c.a.c.bV, com.d.c.a.c.bT});
    public static final BitSet g = b(new com.d.c.a.c[]{com.d.c.a.c.aq, com.d.c.a.c.W, com.d.c.a.c.at});
    public static final BitSet h = b(new com.d.c.a.c[]{com.d.c.a.c.aq, com.d.c.a.c.i, com.d.c.a.c.j, com.d.c.a.c.ab});
    public static final BitSet i = b(new com.d.c.a.c[]{com.d.c.a.c.e, com.d.c.a.c.aA, com.d.c.a.c.Z});
    public static final BitSet j = b(new com.d.c.a.c[]{com.d.c.a.c.V, com.d.c.a.c.aw});
    public static final BitSet k = b(new com.d.c.a.c[]{com.d.c.a.c.x, com.d.c.a.c.o, com.d.c.a.c.aR, com.d.c.a.c.v, com.d.c.a.c.w, com.d.c.a.c.ai, com.d.c.a.c.bn, com.d.c.a.c.ag, com.d.c.a.c.ah, com.d.c.a.c.bm, com.d.c.a.c.d, com.d.c.a.c.N, com.d.c.a.c.af, com.d.c.a.c.bl, com.d.c.a.c.ap});
    public static final BitSet l = b(new com.d.c.a.c[]{com.d.c.a.c.aF, com.d.c.a.c.aG, com.d.c.a.c.aH, com.d.c.a.c.ao});
    public static final BitSet m = b(new com.d.c.a.c[]{com.d.c.a.c.aM, com.d.c.a.c.B});
    public static final BitSet n = b(new com.d.c.a.c[]{com.d.c.a.c.aa, com.d.c.a.c.aJ, com.d.c.a.c.bi, com.d.c.a.c.l, com.d.c.a.c.n});
    public static final BitSet o = b(new com.d.c.a.c[]{com.d.c.a.c.bP, com.d.c.a.c.bN, com.d.c.a.c.bF, com.d.c.a.c.bx, com.d.c.a.c.bv, com.d.c.a.c.bM, com.d.c.a.c.bO});
    public static final BitSet p = b(new com.d.c.a.c[]{com.d.c.a.c.bG, com.d.c.a.c.bw});
    public static final com.d.c.d.a.m q = new bm(0);
    public static final com.d.c.d.a.m r = new bk(0);
    public static final com.d.c.d.a.m s = new bl(0);
    public static final com.d.c.d.a.m t = new cl(0);
    public static final com.d.c.d.a.m u = new bt(0);
    public static final com.d.c.d.a.m v = new cl(0);

    /* JADX INFO: Access modifiers changed from: private */
    public static BitSet b(com.d.c.a.c[] cVarArr) {
        BitSet bitSet = new BitSet(com.d.c.a.c.a());
        for (com.d.c.a.c cVar : cVarArr) {
            bitSet.set(cVar.f968a);
        }
        return bitSet;
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$cz.class */
    static abstract class cz extends com.d.c.d.a.a {
        protected abstract BitSet a();

        private cz() {
        }

        /* synthetic */ cz(byte b2) {
            this();
        }

        @Override // com.d.c.d.a.m
        public List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            a(aVar, 1, list.size());
            com.d.c.d.d dVar = (com.d.c.d.d) list.get(0);
            a(dVar, z2);
            if (dVar.e() != 0) {
                a(aVar, dVar);
                a(aVar, a(), b(dVar));
            }
            return Collections.singletonList(new com.d.i.v(aVar, dVar, z, i));
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$bm.class */
    static class bm extends com.d.c.d.a.a {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1018a = l.b(new com.d.c.a.c[]{com.d.c.a.c.bj});

        private bm() {
        }

        /* synthetic */ bm(byte b2) {
            this();
        }

        @Override // com.d.c.d.a.m
        public List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            a(aVar, 1, list.size());
            com.d.c.d.d dVar = (com.d.c.d.d) list.get(0);
            a(dVar, z2);
            if (dVar.e() != 0) {
                c(aVar, dVar);
                if (dVar.a() == 21) {
                    com.d.c.d.h a2 = com.d.c.d.a.f.a(dVar.c());
                    if (a2 != null) {
                        return Collections.singletonList(new com.d.i.v(aVar, new com.d.c.d.j(a2), z, i));
                    }
                    a(aVar, f1018a, b(dVar));
                }
            }
            return Collections.singletonList(new com.d.i.v(aVar, dVar, z, i));
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$bk.class */
    static class bk extends cz {
        private bk() {
            super((byte) 0);
        }

        /* synthetic */ bk(byte b2) {
            this();
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return l.f1001a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$af.class */
    public static class af extends cz {
        public af() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return l.c;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$bl.class */
    static class bl extends com.d.c.d.a.a {
        private bl() {
        }

        /* synthetic */ bl(byte b2) {
            this();
        }

        @Override // com.d.c.d.a.m
        public List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            a(aVar, 1, list.size());
            com.d.c.d.j jVar = (com.d.c.d.j) list.get(0);
            a(jVar, z2);
            if (jVar.e() != 0) {
                f(aVar, jVar);
                if (jVar.a() == 21) {
                    com.d.c.a.c b2 = b(jVar);
                    a(aVar, l.f1002b, b2);
                    return Collections.singletonList(new com.d.i.v(aVar, com.d.c.d.a.f.b(b2.toString()), z, i));
                }
                if (jVar.f() < 0.0f) {
                    throw new com.d.c.d.b(aVar + " may not be negative", -1);
                }
            }
            return Collections.singletonList(new com.d.i.v(aVar, jVar, z, i));
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$bj.class */
    static class bj extends com.d.c.d.a.a {
        private bj() {
        }

        /* synthetic */ bj(byte b2) {
            this();
        }

        @Override // com.d.c.d.a.m
        public List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            a(aVar, 1, 2, list.size());
            com.d.c.d.j jVar = (com.d.c.d.j) list.get(0);
            com.d.c.d.j jVar2 = null;
            if (list.size() == 2) {
                jVar2 = (com.d.c.d.j) list.get(1);
            }
            a(jVar, z2);
            if (jVar2 != null) {
                a((com.d.c.d.d) jVar2, false);
            }
            i(aVar, jVar);
            if (jVar2 == null) {
                return l.b(aVar, jVar, jVar, i, z);
            }
            i(aVar, jVar2);
            return l.b(aVar, jVar, jVar2, i, z);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$bw.class */
    static abstract class bw extends com.d.c.d.a.a {
        protected abstract BitSet a();

        private bw() {
        }

        /* synthetic */ bw(byte b2) {
            this();
        }

        @Override // com.d.c.d.a.m
        public List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            a(aVar, 1, list.size());
            com.d.c.d.j jVar = (com.d.c.d.j) list.get(0);
            a(jVar, z2);
            if (jVar.e() != 0) {
                f(aVar, jVar);
                if (jVar.a() == 21) {
                    a(aVar, a(), b(jVar));
                }
            }
            return Collections.singletonList(new com.d.i.v(aVar, jVar, z, i));
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$bu.class */
    static abstract class bu extends com.d.c.d.a.a {
        protected abstract BitSet a();

        private bu() {
        }

        /* synthetic */ bu(byte b2) {
            this();
        }

        @Override // com.d.c.d.a.m
        public List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            a(aVar, 1, list.size());
            com.d.c.d.j jVar = (com.d.c.d.j) list.get(0);
            a(jVar, z2);
            if (jVar.e() != 0) {
                h(aVar, jVar);
                if (jVar.a() == 21) {
                    a(aVar, a(), b(jVar));
                } else if (!b() && jVar.f() < 0.0f) {
                    throw new com.d.c.d.b(aVar + " may not be negative", -1);
                }
            }
            return Collections.singletonList(new com.d.i.v(aVar, jVar, z, i));
        }

        protected boolean b() {
            return true;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$bs.class */
    static class bs extends com.d.c.d.a.a {
        private bs() {
        }

        /* synthetic */ bs(byte b2) {
            this();
        }

        @Override // com.d.c.d.a.m
        public List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            a(aVar, 1, list.size());
            com.d.c.d.j jVar = (com.d.c.d.j) list.get(0);
            a(jVar, z2);
            if (jVar.e() != 0) {
                i(aVar, jVar);
                if (!a() && jVar.f() < 0.0f) {
                    throw new com.d.c.d.b(aVar + " may not be negative", -1);
                }
            }
            return Collections.singletonList(new com.d.i.v(aVar, jVar, z, i));
        }

        protected boolean a() {
            return true;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$cl.class */
    static class cl extends bs {
        private cl() {
            super((byte) 0);
        }

        /* synthetic */ cl(byte b2) {
            this();
        }

        @Override // com.d.c.d.a.l.bs
        protected final boolean a() {
            return false;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$aa.class */
    static class aa extends com.d.c.d.a.a {
        private aa() {
        }

        /* synthetic */ aa(byte b2) {
            this();
        }

        @Override // com.d.c.d.a.m
        public List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            a(aVar, 1, list.size());
            com.d.c.d.j jVar = (com.d.c.d.j) list.get(0);
            a(jVar, z2);
            if (jVar.e() != 0) {
                k(aVar, jVar);
                if (jVar.f() < 1.0f) {
                    throw new com.d.c.d.b("colspan/rowspan must be greater than zero", -1);
                }
            }
            return Collections.singletonList(new com.d.i.v(aVar, jVar, z, i));
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$cw.class */
    static class cw extends com.d.c.d.a.a {
        private cw() {
        }

        /* synthetic */ cw(byte b2) {
            this();
        }

        @Override // com.d.c.d.a.m
        public List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            a(aVar, 1, list.size());
            com.d.c.d.j jVar = (com.d.c.d.j) list.get(0);
            a(jVar, z2);
            if (jVar.e() != 0) {
                e(aVar, jVar);
                if (!a() && jVar.f() < 0.0f) {
                    throw new com.d.c.d.b(aVar + " may not be negative", -1);
                }
            }
            return Collections.singletonList(new com.d.i.v(aVar, jVar, z, i));
        }

        protected boolean a() {
            return true;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$br.class */
    static class br extends com.d.c.d.a.a {
        private br() {
        }

        /* synthetic */ br(byte b2) {
            this();
        }

        @Override // com.d.c.d.a.m
        public List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            a(aVar, 1, list.size());
            com.d.c.d.j jVar = (com.d.c.d.j) list.get(0);
            a(jVar, z2);
            if (jVar.e() != 0) {
                j(aVar, jVar);
            }
            return Collections.singletonList(new com.d.i.v(aVar, jVar, z, i));
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$bt.class */
    static class bt extends bu {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1021a = l.b(new com.d.c.a.c[]{com.d.c.a.c.e});

        private bt() {
            super((byte) 0);
        }

        /* synthetic */ bt(byte b2) {
            this();
        }

        @Override // com.d.c.d.a.l.bu
        protected final BitSet a() {
            return f1021a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$bx.class */
    static class bx extends bw {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1023a = l.b(new com.d.c.a.c[]{com.d.c.a.c.aq});

        private bx() {
            super((byte) 0);
        }

        /* synthetic */ bx(byte b2) {
            this();
        }

        @Override // com.d.c.d.a.l.bw
        protected final BitSet a() {
            return f1023a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$bv.class */
    static class bv extends bu {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1022a = l.b(new com.d.c.a.c[]{com.d.c.a.c.ap});

        private bv() {
            super((byte) 0);
        }

        /* synthetic */ bv(byte b2) {
            this();
        }

        @Override // com.d.c.d.a.l.bu
        protected final BitSet a() {
            return f1022a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$bn.class */
    static class bn extends com.d.c.d.a.a {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1019a = l.b(new com.d.c.a.c[]{com.d.c.a.c.ap});

        private bn() {
        }

        /* synthetic */ bn(byte b2) {
            this();
        }

        @Override // com.d.c.d.a.m
        public List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            a(aVar, 1, list.size());
            com.d.c.d.d dVar = (com.d.c.d.d) list.get(0);
            a(dVar, z2);
            if (dVar.e() != 0) {
                b(aVar, dVar);
                if (dVar.a() == 21) {
                    a(aVar, f1019a, b(dVar));
                }
            }
            return Collections.singletonList(new com.d.i.v(aVar, dVar, z, i));
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$a.class */
    public static class a extends cz {
        public a() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return l.m;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$b.class */
    public static class b extends bm {
        public b() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bm, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$c.class */
    public static class c extends bn {
        public c() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bn, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$f.class */
    public static class f extends com.d.c.d.a.a {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1042a = l.b(new com.d.c.a.c[]{com.d.c.a.c.e, com.d.c.a.c.r, com.d.c.a.c.s});

        @Override // com.d.c.d.a.m
        public final List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            a(aVar, 1, 2, list.size());
            com.d.c.d.d dVar = (com.d.c.d.d) list.get(0);
            com.d.c.d.d dVar2 = null;
            if (list.size() == 2) {
                dVar2 = (com.d.c.d.d) list.get(1);
            }
            a(dVar, z2);
            if (list.size() == 1 && dVar.e() == 0) {
                return Collections.singletonList(new com.d.i.v(aVar, dVar, z, i));
            }
            if (dVar2 != null) {
                a(dVar2, false);
            }
            h(aVar, dVar);
            if (dVar2 == null) {
                if (dVar.a() != 21) {
                    return l.b(com.d.c.a.a.h, dVar, new com.d.c.d.j(com.d.c.a.c.e), i, z);
                }
                com.d.c.a.c b2 = b(dVar);
                a(aVar, f1042a, b2);
                if (b2 != com.d.c.a.c.r && b2 != com.d.c.a.c.s) {
                    return l.b(com.d.c.a.a.h, dVar, dVar, i, z);
                }
                return Collections.singletonList(new com.d.i.v(aVar, dVar, z, i));
            }
            h(aVar, dVar2);
            if (dVar.a() == 21) {
                if (b(dVar) != com.d.c.a.c.e) {
                    throw new com.d.c.d.b("The only ident value allowed here is 'auto'", -1);
                }
            } else if (((com.d.c.d.j) dVar).f() < 0.0f) {
                throw new com.d.c.d.b(aVar + " values cannot be negative", -1);
            }
            if (dVar2.a() == 21) {
                if (b(dVar2) != com.d.c.a.c.e) {
                    throw new com.d.c.d.b("The only ident value allowed here is 'auto'", -1);
                }
            } else if (((com.d.c.d.j) dVar2).f() < 0.0f) {
                throw new com.d.c.d.b(aVar + " values cannot be negative", -1);
            }
            return l.b(com.d.c.a.a.h, dVar, dVar2, i, z);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$d.class */
    public static class d extends com.d.c.d.a.a {
        @Override // com.d.c.d.a.m
        public final List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            a(aVar, 1, 2, list.size());
            com.d.c.d.d dVar = (com.d.c.d.d) list.get(0);
            com.d.c.d.d dVar2 = null;
            if (list.size() == 2) {
                dVar2 = (com.d.c.d.d) list.get(1);
            }
            a(dVar, z2);
            if (list.size() == 1 && dVar.e() == 0) {
                return Collections.singletonList(new com.d.i.v(aVar, dVar, z, i));
            }
            if (dVar2 != null) {
                a(dVar2, false);
            }
            h(aVar, dVar);
            if (dVar2 == null) {
                if (a(dVar) || dVar.a() == 2) {
                    ArrayList arrayList = new ArrayList(2);
                    arrayList.add(dVar);
                    arrayList.add(new com.d.c.d.j((short) 2, 50.0f, "50%"));
                    return Collections.singletonList(new com.d.i.v(com.d.c.a.a.g, new com.d.c.d.j(arrayList), z, i));
                }
            } else {
                h(aVar, dVar2);
            }
            com.d.c.a.c cVar = null;
            if (dVar.a() == 21) {
                cVar = b(dVar);
                a(aVar, a(), cVar);
            }
            com.d.c.a.c cVar2 = null;
            if (dVar2 == null) {
                cVar2 = com.d.c.a.c.n;
            } else if (dVar2.a() == 21) {
                cVar2 = b(dVar2);
                a(aVar, a(), cVar2);
            }
            if (cVar == null && cVar2 == null) {
                return Collections.singletonList(new com.d.i.v(com.d.c.a.a.g, new com.d.c.d.j((List<com.d.c.d.j>) list), z, i));
            }
            if (cVar != null && cVar2 != null) {
                if (cVar == com.d.c.a.c.bi || cVar == com.d.c.a.c.l || cVar2 == com.d.c.a.c.aa || cVar2 == com.d.c.a.c.aJ) {
                    com.d.c.a.c cVar3 = cVar;
                    cVar = cVar2;
                    cVar2 = cVar3;
                }
                a(aVar, cVar, cVar2);
                return a(a(cVar), a(cVar2), z, i);
            }
            a(aVar, cVar, cVar2);
            ArrayList arrayList2 = new ArrayList(2);
            if (cVar == null) {
                arrayList2.add(dVar);
                arrayList2.add(b(cVar2));
            } else {
                arrayList2.add(b(cVar));
                arrayList2.add(dVar2);
            }
            return Collections.singletonList(new com.d.i.v(com.d.c.a.a.g, new com.d.c.d.j(arrayList2), z, i));
        }

        private static void a(com.d.c.a.a aVar, com.d.c.a.c cVar, com.d.c.a.c cVar2) {
            if (cVar == com.d.c.a.c.bi || cVar == com.d.c.a.c.l || cVar2 == com.d.c.a.c.aa || cVar2 == com.d.c.a.c.aJ) {
                throw new com.d.c.d.b("Invalid combination of keywords in " + aVar, -1);
            }
        }

        private static float a(com.d.c.a.c cVar) {
            float f = 0.0f;
            if (cVar == com.d.c.a.c.n) {
                f = 50.0f;
            } else if (cVar == com.d.c.a.c.l || cVar == com.d.c.a.c.aJ) {
                f = 100.0f;
            }
            return f;
        }

        private com.d.c.d.j b(com.d.c.a.c cVar) {
            float a2 = a(cVar);
            return new com.d.c.d.j((short) 2, a2, a2 + "%");
        }

        private static List a(float f, float f2, boolean z, int i) {
            com.d.c.d.j jVar = new com.d.c.d.j((short) 2, f, f + "%");
            com.d.c.d.j jVar2 = new com.d.c.d.j((short) 2, f2, f2 + "%");
            ArrayList arrayList = new ArrayList(2);
            arrayList.add(jVar);
            arrayList.add(jVar2);
            return Collections.singletonList(new com.d.i.v(com.d.c.a.a.g, new com.d.c.d.j(arrayList), z, i));
        }

        private static BitSet a() {
            return l.n;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$e.class */
    public static class e extends cz {
        public e() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return l.l;
        }
    }

    /* renamed from: com.d.c.d.a.l$l, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$l.class */
    public static class C0021l extends cz {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1043a = l.b(new com.d.c.a.c[]{com.d.c.a.c.q, com.d.c.a.c.aN});

        public C0021l() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return f1043a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$r.class */
    public static class r extends bm {
        public r() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bm, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$g.class */
    public static class g extends bm {
        public g() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bm, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$m.class */
    public static class m extends bm {
        public m() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bm, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$u.class */
    public static class u extends bk {
        public u() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$p.class */
    public static class p extends bk {
        public p() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$j.class */
    public static class j extends bk {
        public j() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$n.class */
    public static class n extends bk {
        public n() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$v.class */
    public static class v extends bl {
        public v() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bl, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$q.class */
    public static class q extends bl {
        public q() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bl, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$k.class */
    public static class k extends bl {
        public k() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bl, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$o.class */
    public static class o extends bl {
        public o() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bl, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$s.class */
    public static class s extends bj {
        public s() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bj, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$t.class */
    public static class t extends bj {
        public t() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bj, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$i.class */
    public static class i extends bj {
        public i() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bj, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$h.class */
    public static class h extends bj {
        public h() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bj, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$w.class */
    public static class w extends bt {
        public w() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bu, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$y.class */
    public static class y extends cz {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1045a = l.b(new com.d.c.a.c[]{com.d.c.a.c.bi, com.d.c.a.c.l});

        public y() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return f1045a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$z.class */
    public static class z extends cz {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1046a = l.b(new com.d.c.a.c[]{com.d.c.a.c.ap, com.d.c.a.c.aa, com.d.c.a.c.aJ, com.d.c.a.c.k});

        public z() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return f1046a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$ab.class */
    public static class ab extends bm {
        public ab() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bm, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$ae.class */
    public static class ae extends cz {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1005a = l.b(new com.d.c.a.c[]{com.d.c.a.c.e, com.d.c.a.c.bq, com.d.c.a.c.br, com.d.c.a.c.az, com.d.c.a.c.by, com.d.c.a.c.bt, com.d.c.a.c.bA, com.d.c.a.c.bB, com.d.c.a.c.bz, com.d.c.a.c.bE, com.d.c.a.c.bI, com.d.c.a.c.bD, com.d.c.a.c.bK, com.d.c.a.c.bJ, com.d.c.a.c.bL, com.d.c.a.c.bu, com.d.c.a.c.bC});

        public ae() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return f1005a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$ag.class */
    public static class ag extends cz {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1006a = l.b(new com.d.c.a.c[]{com.d.c.a.c.R, com.d.c.a.c.h, com.d.c.a.c.ae, com.d.c.a.c.S, com.d.c.a.c.aV, com.d.c.a.c.T, com.d.c.a.c.bd, com.d.c.a.c.bb, com.d.c.a.c.ba, com.d.c.a.c.bc, com.d.c.a.c.aZ, com.d.c.a.c.aY, com.d.c.a.c.aX, com.d.c.a.c.aW, com.d.c.a.c.ap});

        public ag() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return f1006a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$ah.class */
    public static class ah extends cz {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1007a = l.b(new com.d.c.a.c[]{com.d.c.a.c.aO, com.d.c.a.c.Q});

        public ah() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return f1007a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$bd.class */
    public static class bd extends cz {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1016a = l.b(new com.d.c.a.c[]{com.d.c.a.c.aa, com.d.c.a.c.aJ, com.d.c.a.c.ap});

        public bd() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return f1016a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$be.class */
    public static class be extends com.d.c.d.a.a {
        @Override // com.d.c.d.a.m
        public final List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            if (list.size() == 1) {
                com.d.c.d.d dVar = (com.d.c.d.d) list.get(0);
                a(dVar, z2);
                if (dVar.e() == 0) {
                    return Collections.singletonList(new com.d.i.v(aVar, dVar, z, i));
                }
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList(list.size());
            Iterator it = list.iterator();
            while (it.hasNext()) {
                com.d.c.d.j jVar = (com.d.c.d.j) it.next();
                com.d.c.d.k j = jVar.j();
                if (j != null && j != com.d.c.d.k.l) {
                    throw new com.d.c.d.b("Invalid font-family definition", -1);
                }
                if (j != null && arrayList.size() > 0) {
                    arrayList2.add(a((List) arrayList, ' '));
                    arrayList.clear();
                }
                a((com.d.c.d.d) jVar, false);
                short a2 = jVar.a();
                if (a2 == 19) {
                    if (arrayList.size() > 0) {
                        arrayList2.add(a((List) arrayList, ' '));
                        arrayList.clear();
                    }
                    arrayList2.add(jVar.c());
                } else if (a2 == 21) {
                    arrayList.add(jVar.c());
                } else {
                    throw new com.d.c.d.b("Invalid font-family definition", -1);
                }
            }
            if (arrayList.size() > 0) {
                arrayList2.add(a((List) arrayList, ' '));
            }
            String a3 = a((List) arrayList2, ',');
            com.d.c.d.j jVar2 = new com.d.c.d.j((short) 19, a3, a3);
            jVar2.a((String[]) arrayList2.toArray(new String[arrayList2.size()]));
            return Collections.singletonList(new com.d.i.v(aVar, jVar2, z, i));
        }

        private static String a(List list, char c) {
            StringBuilder sb = new StringBuilder(64);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                sb.append((String) it.next());
                if (it.hasNext()) {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$bf.class */
    public static class bf extends com.d.c.d.a.a {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1017a;

        static {
            BitSet bitSet = new BitSet(com.d.c.a.c.a());
            f1017a = bitSet;
            bitSet.or(l.o);
            f1017a.or(l.p);
        }

        @Override // com.d.c.d.a.m
        public final List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            a(aVar, 1, list.size());
            com.d.c.d.j jVar = (com.d.c.d.j) list.get(0);
            a(jVar, z2);
            if (jVar.e() != 0) {
                h(aVar, jVar);
                if (jVar.a() == 21) {
                    a(aVar, f1017a, b(jVar));
                } else if (jVar.f() < 0.0f) {
                    throw new com.d.c.d.b("font-size may not be negative", -1);
                }
            }
            return Collections.singletonList(new com.d.i.v(aVar, jVar, z, i));
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$bg.class */
    public static class bg extends cz {
        public bg() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return l.g;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$bh.class */
    public static class bh extends cz {
        public bh() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return l.d;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$bi.class */
    public static class bi extends com.d.c.d.a.a {
        @Override // com.d.c.d.a.m
        public final List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            a(aVar, 1, list.size());
            com.d.c.d.j jVar = (com.d.c.d.j) list.get(0);
            a(jVar, z2);
            if (jVar.e() != 0) {
                g(aVar, jVar);
                short a2 = jVar.a();
                if (a2 != 21) {
                    if (a2 == 1) {
                        com.d.c.a.c a3 = com.d.c.d.a.f.a(jVar.f());
                        if (a3 == null) {
                            throw new com.d.c.d.b(jVar + " is not a valid font weight", -1);
                        }
                        com.d.c.d.j jVar2 = new com.d.c.d.j((short) 21, a3.toString(), a3.toString());
                        jVar2.a(a3);
                        return Collections.singletonList(new com.d.i.v(aVar, jVar2, z, i));
                    }
                } else {
                    a(aVar, jVar);
                    a(aVar, a(), b(jVar));
                }
            }
            return Collections.singletonList(new com.d.i.v(aVar, jVar, z, i));
        }

        private static BitSet a() {
            return l.h;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$ai.class */
    public static class ai extends br {
        public ai() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.br, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$aj.class */
    public static class aj extends br {
        public aj() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.br, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$an.class */
    public static class an extends cz {
        public an() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return l.e;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$ak.class */
    public static class ak extends cz {
        public ak() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return l.f;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$av.class */
    public static class av extends bt {
        public av() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bu, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.bu
        protected final boolean b() {
            return false;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$ay.class */
    public static class ay extends bt {
        public ay() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bu, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.bu
        protected final boolean b() {
            return false;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$ax.class */
    public static class ax extends cz {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1013a = l.b(new com.d.c.a.c[]{com.d.c.a.c.bH, com.d.c.a.c.e});

        public ax() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return f1013a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$aw.class */
    public static class aw extends cz {
        public aw() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return l.i;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$as.class */
    public static class as extends cz {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1012a = l.b(new com.d.c.a.c[]{com.d.c.a.c.e, com.d.c.a.c.bs});

        public as() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return f1012a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$at.class */
    public static class at extends com.d.c.d.a.a {
        @Override // com.d.c.d.a.m
        public final List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            a(aVar, 1, list.size());
            com.d.c.d.d dVar = (com.d.c.d.d) list.get(0);
            a(dVar, z2);
            if (dVar.e() != 0) {
                m(aVar, dVar);
                if (dVar.a() == 21) {
                    return Collections.singletonList(new com.d.i.v(aVar, new com.d.c.d.j((short) 19, dVar.c(), dVar.d()), z, i));
                }
            }
            return Collections.singletonList(new com.d.i.v(aVar, dVar, z, i));
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$az.class */
    public static class az extends aa {
        public az() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.aa, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$ba.class */
    public static class ba extends aa {
        public ba() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.aa, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$bb.class */
    public static class bb extends cz {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1014a = l.b(new com.d.c.a.c[]{com.d.c.a.c.ay, com.d.c.a.c.e});

        public bb() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return f1014a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$bc.class */
    public static class bc extends cz {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1015a = l.b(new com.d.c.a.c[]{com.d.c.a.c.ac, com.d.c.a.c.h});

        public bc() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return f1015a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$am.class */
    public static class am extends bt {
        public am() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bu, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.bu
        protected final boolean b() {
            return false;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$bo.class */
    public static class bo extends bt {
        public bo() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bu, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.bu
        protected final boolean b() {
            return false;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$al.class */
    public static class al extends cz {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1008a = l.b(new com.d.c.a.c[]{com.d.c.a.c.A, com.d.c.a.c.aS});

        public al() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return f1008a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$ao.class */
    public static class ao extends cz {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1009a = l.b(new com.d.c.a.c[]{com.d.c.a.c.e, com.d.c.a.c.Y});

        public ao() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return f1009a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$aq.class */
    public static class aq extends cz {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1010a = l.b(new com.d.c.a.c[]{com.d.c.a.c.ap, com.d.c.a.c.t});

        public aq() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return f1010a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$bq.class */
    public static class bq extends bt {
        public bq() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bu, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$by.class */
    public static class by extends bx {
        public by() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bw, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$bz.class */
    public static class bz extends com.d.c.d.a.a {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1024a = l.b(new com.d.c.a.c[]{com.d.c.a.c.aq});

        @Override // com.d.c.d.a.m
        public final List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            a(aVar, 1, list.size());
            com.d.c.d.j jVar = (com.d.c.d.j) list.get(0);
            a(jVar, z2);
            if (jVar.e() != 0) {
                n(aVar, jVar);
                if (jVar.a() == 21) {
                    a(aVar, f1024a, b(jVar));
                } else if (jVar.f() < 0.0d) {
                    throw new com.d.c.d.b("line-height may not be negative", -1);
                }
            }
            return Collections.singletonList(new com.d.i.v(aVar, jVar, z, i));
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$ca.class */
    public static class ca extends bn {
        public ca() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bn, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$cb.class */
    public static class cb extends cz {
        public cb() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return l.j;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$cc.class */
    public static class cc extends cz {
        public cc() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return l.k;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$cg.class */
    public static class cg extends bt {
        public cg() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bu, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$cf.class */
    public static class cf extends bt {
        public cf() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bu, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$cd.class */
    public static class cd extends bt {
        public cd() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bu, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$ce.class */
    public static class ce extends bt {
        public ce() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bu, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$ch.class */
    public static class ch extends bv {
        public ch() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bu, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.bu
        protected final boolean b() {
            return false;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$ci.class */
    public static class ci extends bv {
        public ci() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bu, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.bu
        protected final boolean b() {
            return false;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$cj.class */
    public static class cj extends cl {
        public cj() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bs, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$ck.class */
    public static class ck extends cl {
        public ck() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bs, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$au.class */
    public static class au extends cl {
        public au() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bs, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$cm.class */
    public static class cm extends cw {
        public cm() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cw, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cw
        protected final boolean a() {
            return false;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$cn.class */
    public static class cn extends cz {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1025a = l.b(new com.d.c.a.c[]{com.d.c.a.c.bp, com.d.c.a.c.P});

        public cn() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return f1025a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$cr.class */
    public static class cr extends cl {
        public cr() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bs, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$cq.class */
    public static class cq extends cl {
        public cq() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bs, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$co.class */
    public static class co extends cl {
        public co() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bs, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$cp.class */
    public static class cp extends cl {
        public cp() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bs, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$cu.class */
    public static class cu extends cz {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1027a = l.b(new com.d.c.a.c[]{com.d.c.a.c.e, com.d.c.a.c.c, com.d.c.a.c.f, com.d.c.a.c.aa, com.d.c.a.c.aJ});

        public cu() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return f1027a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$cs.class */
    public static class cs extends com.d.c.d.a.a {
        @Override // com.d.c.d.a.m
        public final List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            a(aVar, 1, list.size());
            com.d.c.d.d dVar = (com.d.c.d.d) list.get(0);
            com.d.c.d.d dVar2 = dVar;
            a(dVar, z2);
            if (dVar2.e() != 0) {
                a(aVar, dVar2);
                if (!dVar2.c().equals("auto")) {
                    dVar2 = new com.d.c.d.j((short) 19, dVar2.c(), dVar2.d());
                }
            }
            return Collections.singletonList(new com.d.i.v(aVar, dVar2, z, i));
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$ct.class */
    public static class ct extends cz {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1026a = l.b(new com.d.c.a.c[]{com.d.c.a.c.e, com.d.c.a.c.c, com.d.c.a.c.f, com.d.c.a.c.aa, com.d.c.a.c.aJ});

        public ct() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return f1026a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$cv.class */
    public static class cv extends cz {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1028a = l.b(new com.d.c.a.c[]{com.d.c.a.c.f, com.d.c.a.c.e});

        public cv() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return f1028a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$cx.class */
    public static class cx extends com.d.c.d.a.a {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1029a = l.b(new com.d.c.a.c[]{com.d.c.a.c.aS, com.d.c.a.c.aE, com.d.c.a.c.f969b, com.d.c.a.c.B});

        @Override // com.d.c.d.a.m
        public final List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            a(aVar, 1, list.size());
            com.d.c.d.j jVar = (com.d.c.d.j) list.get(0);
            a(jVar, z2);
            if (jVar.e() != 0) {
                if (jVar.a() != 21) {
                    if (jVar.i() == 7) {
                        com.d.i.e n = jVar.n();
                        if (n.a().equals("running")) {
                            List<com.d.c.d.j> b2 = n.b();
                            if (b2.size() == 1) {
                                if (b2.get(0).a() != 21) {
                                    throw new com.d.c.d.b("The running function takes an identifier as a parameter", -1);
                                }
                            } else {
                                throw new com.d.c.d.b("The running function takes one parameter", -1);
                            }
                        } else {
                            throw new com.d.c.d.b("Only the running function is supported here", -1);
                        }
                    } else {
                        throw new com.d.c.d.b("Value for " + aVar + " must be an identifier or function", -1);
                    }
                } else {
                    a(aVar, jVar);
                    a(aVar, a(), b(jVar));
                }
            }
            return Collections.singletonList(new com.d.i.v(aVar, jVar, z, i));
        }

        private static BitSet a() {
            return f1029a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$cy.class */
    public static class cy extends bt {
        public cy() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bu, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$da.class */
    public static class da extends bn {
        public da() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bn, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$db.class */
    public static class db extends cw {
        public db() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cw, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cw
        protected final boolean a() {
            return false;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$dh.class */
    public static class dh extends bt {
        public dh() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bu, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$dc.class */
    public static class dc extends cz {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1030a = l.b(new com.d.c.a.c[]{com.d.c.a.c.e, com.d.c.a.c.B});

        public dc() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return f1030a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$dd.class */
    public static class dd extends cz {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1031a = l.b(new com.d.c.a.c[]{com.d.c.a.c.aa, com.d.c.a.c.aJ, com.d.c.a.c.n, com.d.c.a.c.X, com.d.c.a.c.bH});

        public dd() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return f1031a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$de.class */
    public static class de extends com.d.c.d.a.a {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1032a = l.b(new com.d.c.a.c[]{com.d.c.a.c.bk, com.d.c.a.c.ax, com.d.c.a.c.ad});

        private static BitSet a() {
            return f1032a;
        }

        @Override // com.d.c.d.a.m
        public final List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            if (list.size() == 1) {
                com.d.c.d.d dVar = (com.d.c.d.d) list.get(0);
                boolean z3 = false;
                if (dVar.e() == 0) {
                    z3 = true;
                } else {
                    a(com.d.c.a.a.ao, dVar);
                    if (b(dVar) == com.d.c.a.c.ap) {
                        z3 = true;
                    }
                }
                if (z3) {
                    return Collections.singletonList(new com.d.i.v(aVar, dVar, z, i));
                }
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                com.d.c.d.j jVar = (com.d.c.d.j) it.next();
                a((com.d.c.d.d) jVar, false);
                a(aVar, jVar);
                com.d.c.a.c b2 = b(jVar);
                if (b2 == com.d.c.a.c.ap) {
                    throw new com.d.c.d.b("Value none may not be used in this position", -1);
                }
                a(aVar, a(), b2);
            }
            return Collections.singletonList(new com.d.i.v(aVar, new com.d.c.d.j((List<com.d.c.d.j>) list), z, i));
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$df.class */
    public static class df extends bs {
        public df() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bs, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$dg.class */
    public static class dg extends cz {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1033a = l.b(new com.d.c.a.c[]{com.d.c.a.c.m, com.d.c.a.c.bo, com.d.c.a.c.aj, com.d.c.a.c.ap});

        public dg() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return f1033a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$dm.class */
    public static class dm extends bu {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1037a = l.b(new com.d.c.a.c[]{com.d.c.a.c.g, com.d.c.a.c.aT, com.d.c.a.c.aU, com.d.c.a.c.bi, com.d.c.a.c.bf, com.d.c.a.c.al, com.d.c.a.c.l, com.d.c.a.c.be});

        public dm() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bu, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.bu
        protected final BitSet a() {
            return f1037a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$dn.class */
    public static class dn extends cz {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1038a = l.b(new com.d.c.a.c[]{com.d.c.a.c.bp, com.d.c.a.c.P, com.d.c.a.c.q, com.d.c.a.c.M});

        public dn() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return f1038a;
        }
    }

    /* renamed from: com.d.c.d.a.l$do, reason: invalid class name */
    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$do.class */
    public static class Cdo extends cz {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1039a = l.b(new com.d.c.a.c[]{com.d.c.a.c.aq, com.d.c.a.c.aB, com.d.c.a.c.ar, com.d.c.a.c.aD, com.d.c.a.c.aC});

        public Cdo() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return f1039a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$ds.class */
    public static class ds extends cz {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1040a = l.b(new com.d.c.a.c[]{com.d.c.a.c.aq, com.d.c.a.c.as});

        public ds() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return f1040a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$dp.class */
    public static class dp extends cw {
        public dp() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cw, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cw
        protected final boolean a() {
            return false;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$dq.class */
    public static class dq extends bt {
        public dq() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bu, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.bu
        protected final boolean b() {
            return false;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$dr.class */
    public static class dr extends bx {
        public dr() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bw, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$dt.class */
    public static class dt extends com.d.c.d.a.a {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1041a = l.b(new com.d.c.a.c[]{com.d.c.a.c.e});

        @Override // com.d.c.d.a.m
        public final List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            a(aVar, 1, list.size());
            com.d.c.d.d dVar = (com.d.c.d.d) list.get(0);
            a(dVar, z2);
            if (dVar.e() != 0) {
                d(aVar, dVar);
                if (dVar.a() == 21) {
                    a(aVar, f1041a, b(dVar));
                }
            }
            return Collections.singletonList(new com.d.i.v(aVar, dVar, z, i));
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$ac.class */
    public static class ac extends com.d.c.d.a.a {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1003a = l.b(new com.d.c.a.c[]{com.d.c.a.c.e});

        @Override // com.d.c.d.a.m
        public final List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            a(aVar, 1, list.size());
            com.d.c.d.d dVar = (com.d.c.d.d) list.get(0);
            a(dVar, z2);
            if (dVar.e() != 0) {
                d(aVar, dVar);
                if (dVar.a() == 21) {
                    a(aVar, f1003a, b(dVar));
                } else if (dVar.b() < 1.0f) {
                    throw new com.d.c.d.b("column-count must be one or greater", -1);
                }
            }
            return Collections.singletonList(new com.d.i.v(aVar, dVar, z, i));
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$ad.class */
    public static class ad extends bu {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1004a = l.b(new com.d.c.a.c[]{com.d.c.a.c.aq});

        public ad() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.bu, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.bu
        protected final BitSet a() {
            return f1004a;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List b(com.d.c.a.a aVar, com.d.c.d.d dVar, com.d.c.d.d dVar2, int i2, boolean z2) {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(dVar);
        arrayList.add(dVar2);
        return Collections.singletonList(new com.d.i.v(aVar, new com.d.c.d.j(arrayList), z2, i2));
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$dl.class */
    public static class dl extends com.d.c.d.a.a {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1036a = l.b(new com.d.c.a.c[]{com.d.c.a.c.ap});

        @Override // com.d.c.d.a.m
        public final List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            int i2;
            a(aVar, 1, Integer.MAX_VALUE, list.size());
            a((com.d.c.d.d) list.get(0), z2);
            if (((com.d.c.d.j) list.get(0)).e() == 0) {
                return Collections.singletonList(new com.d.i.v(aVar, (com.d.c.d.d) list.get(0), z, i));
            }
            if (list.size() == 1) {
                com.d.c.d.d dVar = (com.d.c.d.d) list.get(0);
                if (dVar.a() == 21) {
                    a(aVar, f1036a, b(dVar));
                    return Collections.singletonList(new com.d.i.v(com.d.c.a.a.ay, dVar, z, i));
                }
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                com.d.c.d.j jVar = (com.d.c.d.j) it.next();
                if (jVar.i() != 7) {
                    throw new com.d.c.d.b("One or more functions must be provided for transform property", -1);
                }
                String a2 = jVar.n().a();
                if (a2.equalsIgnoreCase("matrix")) {
                    i2 = 6;
                    Iterator<com.d.c.d.j> it2 = jVar.n().b().iterator();
                    while (it2.hasNext()) {
                        k(aVar, it2.next());
                    }
                } else if (a2.equalsIgnoreCase("translate")) {
                    i2 = 2;
                    Iterator<com.d.c.d.j> it3 = jVar.n().b().iterator();
                    while (it3.hasNext()) {
                        i(aVar, it3.next());
                    }
                } else if (a2.equalsIgnoreCase("translateX")) {
                    i2 = 1;
                    Iterator<com.d.c.d.j> it4 = jVar.n().b().iterator();
                    while (it4.hasNext()) {
                        i(aVar, it4.next());
                    }
                } else if (a2.equalsIgnoreCase("translateY")) {
                    i2 = 1;
                    Iterator<com.d.c.d.j> it5 = jVar.n().b().iterator();
                    while (it5.hasNext()) {
                        i(aVar, it5.next());
                    }
                } else if (a2.equalsIgnoreCase("scale")) {
                    i2 = 2;
                    Iterator<com.d.c.d.j> it6 = jVar.n().b().iterator();
                    while (it6.hasNext()) {
                        k(aVar, it6.next());
                    }
                    if (jVar.n().b().size() == 1) {
                        i2 = 1;
                    }
                } else if (a2.equalsIgnoreCase("scaleX")) {
                    i2 = 1;
                    Iterator<com.d.c.d.j> it7 = jVar.n().b().iterator();
                    while (it7.hasNext()) {
                        k(aVar, it7.next());
                    }
                } else if (a2.equalsIgnoreCase("scaleY")) {
                    i2 = 1;
                    Iterator<com.d.c.d.j> it8 = jVar.n().b().iterator();
                    while (it8.hasNext()) {
                        k(aVar, it8.next());
                    }
                } else if (a2.equalsIgnoreCase("skew")) {
                    i2 = 2;
                    Iterator<com.d.c.d.j> it9 = jVar.n().b().iterator();
                    while (it9.hasNext()) {
                        l(aVar, it9.next());
                    }
                    if (jVar.n().b().size() == 1) {
                        i2 = 1;
                    }
                } else if (a2.equalsIgnoreCase("skewX")) {
                    i2 = 1;
                    Iterator<com.d.c.d.j> it10 = jVar.n().b().iterator();
                    while (it10.hasNext()) {
                        l(aVar, it10.next());
                    }
                } else if (a2.equalsIgnoreCase("skewY")) {
                    i2 = 1;
                    Iterator<com.d.c.d.j> it11 = jVar.n().b().iterator();
                    while (it11.hasNext()) {
                        l(aVar, it11.next());
                    }
                } else if (a2.equalsIgnoreCase("rotate")) {
                    i2 = 1;
                    Iterator<com.d.c.d.j> it12 = jVar.n().b().iterator();
                    while (it12.hasNext()) {
                        l(aVar, it12.next());
                    }
                } else {
                    throw new com.d.c.d.b("Unsupported function provided in transform property: " + a2, -1);
                }
                a(aVar, i2, jVar.n().b().size());
            }
            return Collections.singletonList(new com.d.i.v(com.d.c.a.a.ay, new com.d.c.d.j((List<com.d.c.d.j>) list), z, i));
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$dj.class */
    public static class dj extends com.d.c.d.a.a {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1034a = l.b(new com.d.c.a.c[]{com.d.c.a.c.aa, com.d.c.a.c.n, com.d.c.a.c.aJ});

        @Override // com.d.c.d.a.m
        public final List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            a(aVar, 1, list.size());
            com.d.c.d.d dVar = (com.d.c.d.d) list.get(0);
            a(dVar, z2);
            if (dVar.e() == 0) {
                return Collections.singletonList(new com.d.i.v(aVar, dVar, z, i));
            }
            if (dVar.a() == 21) {
                com.d.c.a.c b2 = b(dVar);
                a(aVar, f1034a, b2);
                if (b2 == com.d.c.a.c.aa) {
                    return Collections.singletonList(new com.d.i.v(aVar, new com.d.c.d.j((short) 2, 0.0f, "0%"), z, i));
                }
                if (b2 == com.d.c.a.c.n) {
                    return Collections.singletonList(new com.d.i.v(aVar, new com.d.c.d.j((short) 2, 50.0f, "50%"), z, i));
                }
                return Collections.singletonList(new com.d.i.v(aVar, new com.d.c.d.j((short) 2, 100.0f, "100%"), z, i));
            }
            i(aVar, dVar);
            return Collections.singletonList(new com.d.i.v(aVar, dVar, z, i));
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$dk.class */
    public static class dk extends com.d.c.d.a.a {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1035a = l.b(new com.d.c.a.c[]{com.d.c.a.c.bi, com.d.c.a.c.n, com.d.c.a.c.l});

        @Override // com.d.c.d.a.m
        public final List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            a(aVar, 1, list.size());
            com.d.c.d.d dVar = (com.d.c.d.d) list.get(0);
            a(dVar, z2);
            if (dVar.e() == 0) {
                return Collections.singletonList(new com.d.i.v(aVar, dVar, z, i));
            }
            if (dVar.a() == 21) {
                com.d.c.a.c b2 = b(dVar);
                a(aVar, f1035a, b2);
                if (b2 == com.d.c.a.c.bi) {
                    return Collections.singletonList(new com.d.i.v(aVar, new com.d.c.d.j((short) 2, 0.0f, "0%"), z, i));
                }
                if (b2 == com.d.c.a.c.n) {
                    return Collections.singletonList(new com.d.i.v(aVar, new com.d.c.d.j((short) 2, 50.0f, "50%"), z, i));
                }
                return Collections.singletonList(new com.d.i.v(aVar, new com.d.c.d.j((short) 2, 100.0f, "100%"), z, i));
            }
            i(aVar, dVar);
            return Collections.singletonList(new com.d.i.v(aVar, dVar, z, i));
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$di.class */
    public static class di extends com.d.c.d.a.a {
        @Override // com.d.c.d.a.m
        public final List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            a(aVar, 2, 3, list.size());
            return Arrays.asList(new dj().a(com.d.c.a.a.az, Collections.singletonList((com.d.c.d.d) list.get(0)), i, z).get(0), new dk().a(com.d.c.a.a.aA, Collections.singletonList((com.d.c.d.d) list.get(1)), i, z).get(0));
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$bp.class */
    public static class bp extends cz {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1020a = l.b(new com.d.c.a.c[]{com.d.c.a.c.e, com.d.c.a.c.bW, com.d.c.a.c.bX});

        public bp() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return f1020a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$x.class */
    public static class x extends cz {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1044a = l.b(new com.d.c.a.c[]{com.d.c.a.c.bY, com.d.c.a.c.bZ});

        public x() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return f1044a;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$ap.class */
    public static class ap extends cw {
        public ap() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cw, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cw
        protected final boolean a() {
            return false;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/l$ar.class */
    public static class ar extends cz {

        /* renamed from: a, reason: collision with root package name */
        private static final BitSet f1011a = l.b(new com.d.c.a.c[]{com.d.c.a.c.ak, com.d.c.a.c.aK});

        public ar() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.l.cz, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.l.cz
        protected final BitSet a() {
            return f1011a;
        }
    }
}
