package com.d.c.d.a;

import com.a.a.c.f.s;
import com.a.a.c.p;
import com.d.i.v;
import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/c/d/a/j.class */
public abstract class j {

    /* loaded from: infinitode-2.jar:com/d/c/d/a/j$f.class */
    static abstract class f extends com.d.c.d.a.a {
        protected abstract com.d.c.a.a[] a();

        protected abstract m b();

        private f() {
        }

        /* synthetic */ f(byte b2) {
            this();
        }

        @Override // com.d.c.d.a.m
        public List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            ArrayList arrayList = new ArrayList(4);
            a(aVar, 1, 4, list.size());
            m b2 = b();
            com.d.c.a.a[] a2 = a();
            switch (list.size()) {
                case 1:
                    v vVar = (v) b2.a(aVar, list, i, z).get(0);
                    arrayList.add(a(vVar, a2[0]));
                    arrayList.add(a(vVar, a2[1]));
                    arrayList.add(a(vVar, a2[2]));
                    arrayList.add(a(vVar, a2[3]));
                    break;
                case 2:
                    v vVar2 = (v) b2.a(aVar, list.subList(0, 1), i, z, false).get(0);
                    v vVar3 = (v) b2.a(aVar, list.subList(1, 2), i, z, false).get(0);
                    arrayList.add(a(vVar2, a2[0]));
                    arrayList.add(a(vVar3, a2[1]));
                    arrayList.add(a(vVar2, a2[2]));
                    arrayList.add(a(vVar3, a2[3]));
                    break;
                case 3:
                    v vVar4 = (v) b2.a(aVar, list.subList(0, 1), i, z, false).get(0);
                    v vVar5 = (v) b2.a(aVar, list.subList(1, 2), i, z, false).get(0);
                    v vVar6 = (v) b2.a(aVar, list.subList(2, 3), i, z, false).get(0);
                    arrayList.add(a(vVar4, a2[0]));
                    arrayList.add(a(vVar5, a2[1]));
                    arrayList.add(a(vVar6, a2[2]));
                    arrayList.add(a(vVar5, a2[3]));
                    break;
                case 4:
                    v vVar7 = (v) b2.a(aVar, list.subList(0, 1), i, z, false).get(0);
                    v vVar8 = (v) b2.a(aVar, list.subList(1, 2), i, z, false).get(0);
                    v vVar9 = (v) b2.a(aVar, list.subList(2, 3), i, z, false).get(0);
                    v vVar10 = (v) b2.a(aVar, list.subList(3, 4), i, z, false).get(0);
                    arrayList.add(a(vVar7, a2[0]));
                    arrayList.add(a(vVar8, a2[1]));
                    arrayList.add(a(vVar9, a2[2]));
                    arrayList.add(a(vVar10, a2[3]));
                    break;
            }
            return arrayList;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/j$a.class */
    public static class a extends f {
        public a() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.j.f, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.j.f
        protected final com.d.c.a.a[] a() {
            return new com.d.c.a.a[]{com.d.c.a.a.aC, com.d.c.a.a.aD, com.d.c.a.a.aE, com.d.c.a.a.aF};
        }

        @Override // com.d.c.d.a.j.f
        protected final m b() {
            return l.q;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/j$c.class */
    public static class c extends f {
        public c() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.j.f, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.j.f
        protected final com.d.c.a.a[] a() {
            return new com.d.c.a.a[]{com.d.c.a.a.aG, com.d.c.a.a.aH, com.d.c.a.a.aI, com.d.c.a.a.aJ};
        }

        @Override // com.d.c.d.a.j.f
        protected final m b() {
            return l.r;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/j$d.class */
    public static class d extends f {
        public d() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.j.f, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.j.f
        protected final com.d.c.a.a[] a() {
            return new com.d.c.a.a[]{com.d.c.a.a.aK, com.d.c.a.a.aL, com.d.c.a.a.aM, com.d.c.a.a.aN};
        }

        @Override // com.d.c.d.a.j.f
        protected final m b() {
            return l.s;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/j$b.class */
    public static class b extends f {
        public b() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.j.f, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.j.f
        protected final com.d.c.a.a[] a() {
            return new com.d.c.a.a[]{com.d.c.a.a.aO, com.d.c.a.a.aP, com.d.c.a.a.aQ, com.d.c.a.a.aR};
        }

        @Override // com.d.c.d.a.j.f
        protected final m b() {
            return l.t;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/j$e.class */
    public static class e extends f {
        public e() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.j.f, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.j.f
        protected final com.d.c.a.a[] a() {
            return new com.d.c.a.a[]{com.d.c.a.a.aS, com.d.c.a.a.aT, com.d.c.a.a.aU, com.d.c.a.a.aV};
        }

        @Override // com.d.c.d.a.j.f
        protected final m b() {
            return l.u;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/j$g.class */
    public static class g extends f {
        public g() {
            super((byte) 0);
        }

        @Override // com.d.c.d.a.j.f, com.d.c.d.a.m
        public final /* bridge */ /* synthetic */ List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
            return super.a(aVar, list, i, z, z2);
        }

        @Override // com.d.c.d.a.j.f
        protected final com.d.c.a.a[] a() {
            return new com.d.c.a.a[]{com.d.c.a.a.aW, com.d.c.a.a.aX, com.d.c.a.a.aY, com.d.c.a.a.aZ};
        }

        @Override // com.d.c.d.a.j.f
        protected final m b() {
            return l.v;
        }
    }

    public static List<s> a(List<s> list) {
        return list;
    }

    public static com.a.a.c.c.g a(com.a.a.c.c.g gVar) {
        return gVar;
    }

    public static com.a.a.c.k<?> a(com.a.a.c.k<?> kVar) {
        return kVar;
    }

    public static com.a.a.c.k<?> b(com.a.a.c.k<?> kVar) {
        return kVar;
    }

    public static com.a.a.c.k<?> c(com.a.a.c.k<?> kVar) {
        return kVar;
    }

    public static com.a.a.c.k<?> d(com.a.a.c.k<?> kVar) {
        return kVar;
    }

    public static com.a.a.c.k<?> e(com.a.a.c.k<?> kVar) {
        return kVar;
    }

    public static com.a.a.c.k<?> f(com.a.a.c.k<?> kVar) {
        return kVar;
    }

    public static com.a.a.c.k<?> g(com.a.a.c.k<?> kVar) {
        return kVar;
    }

    public static com.a.a.c.k<?> h(com.a.a.c.k<?> kVar) {
        return kVar;
    }

    public static p a(p pVar) {
        return pVar;
    }
}
