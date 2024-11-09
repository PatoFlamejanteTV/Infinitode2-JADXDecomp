package com.d.i;

import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/i/p.class */
public final class p extends c {

    /* renamed from: a, reason: collision with root package name */
    private o f1366a;

    private static int a(List<aa> list, int i) {
        int i2 = 0;
        for (aa aaVar : list) {
            if (i >= aaVar.b() && i <= aaVar.a()) {
                return i2;
            }
            i2++;
        }
        return i2 - 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/i/p$a.class */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private final int f1367a;

        /* renamed from: b, reason: collision with root package name */
        private final int f1368b;
        private final int c;
        private final int d;

        /* synthetic */ a(int i, int i2, int i3, int i4, byte b2) {
            this(i, i2, i3, i4);
        }

        private a(int i, int i2, int i3, int i4) {
            this.f1367a = i;
            this.f1368b = i2;
            this.c = i3;
            this.d = i4;
        }
    }

    private int a(com.d.e.v vVar, f fVar, int i, int i2, int i3, int i4) {
        int i5;
        int aa = aa();
        List<aa> k = vVar.p().k();
        int a2 = a(k, aa);
        int a3 = k.get(a2).a();
        int i6 = 0;
        int i7 = 0;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new a(0, aa, a3, a2, (byte) 0));
        for (f fVar2 : fVar.X()) {
            int an = fVar2.an() + (((a) arrayList.get(i6)).f1368b - ((a) arrayList.get(i6)).f1367a);
            int i8 = an;
            if (an + fVar2.as() > ((a) arrayList.get(i6)).c) {
                int i9 = i6 + 1;
                int i10 = i9 % i3 == 0 ? ((a) arrayList.get(i6)).d + 1 : ((a) arrayList.get(i6)).d;
                int i11 = i10;
                if (i10 >= k.size()) {
                    vVar.p().b((com.d.c.f.d) vVar);
                }
                if (i9 % i3 != 0) {
                    i5 = ((a) arrayList.get(i6)).f1368b;
                } else {
                    i5 = k.get(i11).b();
                }
                arrayList.add(new a(fVar2.an(), i5, k.get(i11).a(), i11, (byte) 0));
                i6++;
                i8 = fVar2.an() + (((a) arrayList.get(i6)).f1368b - ((a) arrayList.get(i6)).f1367a);
            }
            fVar2.o(i8 - aa);
            i7 = Math.max((i8 - aa) + fVar2.as(), i7);
            fVar2.n(fVar2.am() + ((i6 % i3) * i2) + ((i6 % i3) * i) + i4);
        }
        return i7;
    }

    @Override // com.d.i.c
    public final void a_(com.d.e.v vVar, int i) {
        float b2;
        k(vVar);
        int Z = a().Z();
        int i2 = Z - 1;
        if (a().a(com.d.c.a.a.B, com.d.c.a.c.aq)) {
            b2 = a().c(vVar);
        } else {
            b2 = a().b(com.d.c.a.a.B, d_(), vVar);
        }
        float f = b2;
        int d_ = (int) ((d_() - (b2 * i2)) / Z);
        this.f1366a.b(af());
        this.f1366a.u(d_);
        this.f1366a.a(d_);
        this.f1366a.m(ab());
        this.f1366a.l(aa());
        vVar.a(Boolean.FALSE);
        this.f1366a.a_(vVar, i);
        vVar.a((Boolean) null);
        int a2 = a(vVar, this.f1366a, (int) f, d_, Z, ar() + am());
        this.f1366a.t(0);
        t(a2);
        this.f1366a.C();
    }

    public final void a(o oVar) {
        this.f1366a = oVar;
        b(oVar);
    }

    public final o f() {
        return this.f1366a;
    }
}
