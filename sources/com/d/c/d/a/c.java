package com.d.c.d.a;

import com.a.a.c.k.b.aa;
import com.d.i.v;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/c/d/a/c.class */
public class c implements com.d.a.c {

    /* loaded from: infinitode-2.jar:com/d/c/d/a/c$e.class */
    static abstract class e extends com.d.c.d.a.a {
        protected abstract com.d.c.a.a[][] a();

        private e() {
        }

        /* synthetic */ e(byte b2) {
            this();
        }

        private static void a(List list, com.d.c.a.a[] aVarArr, com.d.c.d.d dVar, int i, boolean z) {
            for (com.d.c.a.a aVar : aVarArr) {
                list.add(new v(aVar, dVar, z, i));
            }
        }

        @Override // com.d.c.d.a.m
        public List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            com.d.c.a.a[][] a2 = a();
            ArrayList arrayList = new ArrayList(3);
            if (list.size() == 1 && ((com.d.c.d.d) list.get(0)).e() == 0) {
                com.d.c.d.d dVar = (com.d.c.d.d) list.get(0);
                a(arrayList, a2[0], dVar, i, z);
                a(arrayList, a2[1], dVar, i, z);
                a(arrayList, a2[2], dVar, i, z);
                return arrayList;
            }
            a(aVar, 1, 3, list.size());
            boolean z3 = false;
            boolean z4 = false;
            boolean z5 = false;
            Iterator it = list.iterator();
            while (it.hasNext()) {
                com.d.c.d.d dVar2 = (com.d.c.d.d) it.next();
                a(dVar2, false);
                boolean z6 = false;
                com.d.c.d.d d = d(dVar2);
                if (d != null) {
                    if (z5) {
                        throw new com.d.c.d.b("A border width cannot be set twice", -1);
                    }
                    z5 = true;
                    z6 = true;
                    a(arrayList, a2[0], d, i, z);
                }
                if (c(dVar2)) {
                    if (z3) {
                        throw new com.d.c.d.b("A border style cannot be set twice", -1);
                    }
                    z3 = true;
                    z6 = true;
                    a(arrayList, a2[1], dVar2, i, z);
                }
                com.d.c.d.d e = e(dVar2);
                if (e != null) {
                    if (z4) {
                        throw new com.d.c.d.b("A border color cannot be set twice", -1);
                    }
                    z4 = true;
                    z6 = true;
                    a(arrayList, a2[2], e, i, z);
                }
                if (!z6) {
                    throw new com.d.c.d.b(dVar2.d() + " is not a border width, style, or color", -1);
                }
            }
            if (!z5) {
                a(arrayList, a2[0], new com.d.c.d.j(com.d.c.a.c.L), i, z);
            }
            if (!z3) {
                a(arrayList, a2[1], new com.d.c.d.j(com.d.c.a.c.L), i, z);
            }
            if (!z4) {
                a(arrayList, a2[2], new com.d.c.d.j(com.d.c.a.c.L), i, z);
            }
            return arrayList;
        }

        private static boolean c(com.d.c.d.d dVar) {
            com.d.c.a.c b2;
            if (dVar.a() == 21 && (b2 = com.d.c.a.c.b(dVar.d())) != null) {
                return l.f1001a.get(b2.f968a);
            }
            return false;
        }

        private com.d.c.d.d d(com.d.c.d.d dVar) {
            if (dVar.a() != 21 && !a(dVar)) {
                return null;
            }
            if (a(dVar)) {
                return dVar;
            }
            com.d.c.a.c b2 = com.d.c.a.c.b(dVar.c());
            if (b2 != null && l.f1002b.get(b2.f968a)) {
                return com.d.c.d.a.f.b(b2.toString());
            }
            return null;
        }

        private static com.d.c.d.d e(com.d.c.d.d dVar) {
            short a2 = dVar.a();
            if (a2 != 21 && a2 != 25) {
                return null;
            }
            if (a2 == 25) {
                return dVar;
            }
            com.d.c.d.h a3 = com.d.c.d.a.f.a(dVar.c());
            if (a3 != null) {
                return new com.d.c.d.j(a3);
            }
            com.d.c.a.c b2 = com.d.c.a.c.b(dVar.d());
            if (b2 == null || b2 != com.d.c.a.c.bj) {
                return null;
            }
            return dVar;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/c$f.class */
    public static class f extends e {
        public f() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.c.e, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [com.d.c.a.a[], com.d.c.a.a[][]] */
        @Override // com.d.c.d.a.c.e
        protected final com.d.c.a.a[][] a() {
            return new com.d.c.a.a[]{new com.d.c.a.a[]{com.d.c.a.a.aK}, new com.d.c.a.a[]{com.d.c.a.a.aG}, new com.d.c.a.a[]{com.d.c.a.a.aC}};
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/c$d.class */
    public static class d extends e {
        public d() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.c.e, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [com.d.c.a.a[], com.d.c.a.a[][]] */
        @Override // com.d.c.d.a.c.e
        protected final com.d.c.a.a[][] a() {
            return new com.d.c.a.a[]{new com.d.c.a.a[]{com.d.c.a.a.aL}, new com.d.c.a.a[]{com.d.c.a.a.aH}, new com.d.c.a.a[]{com.d.c.a.a.aD}};
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/c$b.class */
    public static class b extends e {
        public b() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.c.e, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [com.d.c.a.a[], com.d.c.a.a[][]] */
        @Override // com.d.c.d.a.c.e
        protected final com.d.c.a.a[][] a() {
            return new com.d.c.a.a[]{new com.d.c.a.a[]{com.d.c.a.a.aM}, new com.d.c.a.a[]{com.d.c.a.a.aI}, new com.d.c.a.a[]{com.d.c.a.a.aE}};
        }
    }

    /* renamed from: com.d.c.d.a.c$c, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:com/d/c/d/a/c$c.class */
    public static class C0020c extends e {
        public C0020c() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.c.e, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [com.d.c.a.a[], com.d.c.a.a[][]] */
        @Override // com.d.c.d.a.c.e
        protected final com.d.c.a.a[][] a() {
            return new com.d.c.a.a[]{new com.d.c.a.a[]{com.d.c.a.a.aN}, new com.d.c.a.a[]{com.d.c.a.a.aJ}, new com.d.c.a.a[]{com.d.c.a.a.aF}};
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/c$a.class */
    public static class a extends e {
        public a() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.c.e, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [com.d.c.a.a[], com.d.c.a.a[][]] */
        @Override // com.d.c.d.a.c.e
        protected final com.d.c.a.a[][] a() {
            return new com.d.c.a.a[]{new com.d.c.a.a[]{com.d.c.a.a.aK, com.d.c.a.a.aL, com.d.c.a.a.aM, com.d.c.a.a.aN}, new com.d.c.a.a[]{com.d.c.a.a.aG, com.d.c.a.a.aH, com.d.c.a.a.aI, com.d.c.a.a.aJ}, new com.d.c.a.a[]{com.d.c.a.a.aC, com.d.c.a.a.aD, com.d.c.a.a.aE, com.d.c.a.a.aF}};
        }
    }

    @Override // com.d.a.c
    public com.d.a.b a() {
        return new com.d.a.b() { // from class: com.d.a.e

            /* renamed from: a, reason: collision with root package name */
            private List<aa> f958a;

            @Override // com.d.a.b
            public void a(String str, byte b2) {
                this.f958a = Collections.singletonList(new aa(0, str.length(), b2));
            }

            @Override // com.d.a.b
            public int a() {
                return this.f958a.size();
            }

            @Override // com.d.a.b
            public aa a(int i) {
                return this.f958a.get(i);
            }

            @Override // com.d.a.b
            public byte a(String str) {
                return (byte) 0;
            }
        };
    }
}
