package com.d.f;

import com.d.e.v;
import com.d.i.ab;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/f/j.class */
public final class j extends com.d.i.c {

    /* renamed from: a, reason: collision with root package name */
    private List f1192a = new ArrayList();

    /* renamed from: b, reason: collision with root package name */
    private boolean f1193b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private int g;

    @Override // com.d.i.c
    public final com.d.i.c c() {
        j jVar = new j();
        jVar.a(a());
        jVar.a(ai());
        return jVar;
    }

    public final List d() {
        return this.f1192a;
    }

    public final void a(int i) {
        Iterator it = this.f1192a.iterator();
        while (it.hasNext()) {
            ((c) it.next()).a(i);
        }
    }

    public final void b(int i) {
        Iterator it = this.f1192a.iterator();
        while (it.hasNext()) {
            ((c) it.next()).b(i);
        }
    }

    public final void e(v vVar) {
        int i = 0;
        this.f1192a.clear();
        l(vVar);
        Iterator<com.d.i.f> W = W();
        while (W.hasNext()) {
            i iVar = (i) W.next();
            iVar.l(vVar);
            Iterator<com.d.i.f> W2 = iVar.W();
            while (W2.hasNext()) {
                a((f) W2.next(), i);
            }
            i++;
        }
    }

    public final void f(v vVar) {
        l(vVar);
        Iterator<com.d.i.f> W = W();
        while (W.hasNext()) {
            i iVar = (i) W.next();
            iVar.l(vVar);
            Iterator<com.d.i.f> W2 = iVar.W();
            while (W2.hasNext()) {
                ((f) W2.next()).c((com.d.c.f.d) vVar);
            }
        }
    }

    public final f a(int i, int i2) {
        if (i >= this.f1192a.size()) {
            return null;
        }
        c cVar = (c) this.f1192a.get(i);
        if (i2 >= cVar.a().size()) {
            return null;
        }
        return (f) cVar.a().get(i2);
    }

    private void a(int i, int i2, f fVar) {
        ((c) this.f1192a.get(i)).a().set(i2, fVar);
    }

    private void d(int i) {
        int f = f().f();
        for (int size = this.f1192a.size(); size < i; size++) {
            c cVar = new c();
            cVar.a(f);
            this.f1192a.add(cVar);
        }
    }

    public final d f() {
        return (d) U();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.d.i.c
    public final void a(v vVar, int i) {
        if (t()) {
            e(vVar);
            j(false);
        }
        if (o()) {
            g(vVar);
            a(false);
        }
        super.a(vVar, i);
    }

    private void a(f fVar, int i) {
        int an = fVar.a().an();
        int ao = fVar.a().ao();
        List g = f().g();
        int size = g.size();
        int i2 = 0;
        d(i + an);
        while (i2 < size && a(i, i2) != null) {
            i2++;
        }
        int i3 = i2;
        f fVar2 = fVar;
        while (true) {
            f fVar3 = fVar2;
            if (ao > 0) {
                while (i2 >= f().g().size()) {
                    f().d(1);
                }
                if (ao < ((b) g.get(i2)).a()) {
                    f().a(i2, ao);
                }
                int a2 = ((b) g.get(i2)).a();
                for (int i4 = 0; i4 < an; i4++) {
                    if (a(i + i4, i2) == null) {
                        a(i + i4, i2, fVar3);
                    }
                }
                i2++;
                ao -= a2;
                fVar2 = f.f1185a;
            } else {
                fVar.b(i);
                fVar.a(f().c(i3));
                return;
            }
        }
    }

    @Override // com.d.i.c, com.d.i.f
    public final void c(v vVar) {
        super.c(vVar);
        this.f1192a.clear();
        a(true);
        j(true);
        d(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void g(v vVar) {
        int[] e = f().e();
        Iterator it = this.f1192a.iterator();
        while (it.hasNext()) {
            List a2 = ((c) it.next()).a();
            int f = f().a().f(vVar);
            for (int i = 0; i < a2.size(); i++) {
                f fVar = (f) a2.get(i);
                if (fVar != null && fVar != f.f1185a) {
                    int i2 = i;
                    int ao = fVar.a().ao();
                    while (ao > 0 && i2 < a2.size()) {
                        ao -= f().a(i2);
                        i2++;
                    }
                    fVar.b(vVar, (e[i2] - e[i]) - f);
                    fVar.n(e[i] + f);
                }
            }
        }
    }

    @Override // com.d.i.c
    public final boolean b_() {
        return true;
    }

    public final int g() {
        return this.f1192a.size();
    }

    @Override // com.d.i.c
    protected final boolean n() {
        return true;
    }

    @Override // com.d.i.f
    public final void c(ab abVar) {
    }

    @Override // com.d.i.f
    public final void b(ab abVar) {
    }

    public final i j() {
        if (V() > 0) {
            return (i) k(V() - 1);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean o() {
        return this.f1193b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(boolean z) {
        this.f1193b = z;
    }

    private boolean t() {
        return this.c;
    }

    private void j(boolean z) {
        this.c = z;
    }

    @Override // com.d.i.c
    public final void a_(v vVar, int i) {
        boolean z = vVar.r() && (q() || p()) && f().a().as();
        boolean z2 = z;
        if (z) {
            vVar.c(vVar.C() + 1);
        }
        super.a_(vVar, i);
        if (z2) {
            vVar.c(vVar.C() - 1);
        }
    }

    public final boolean p() {
        return this.d;
    }

    public final void b(boolean z) {
        this.d = true;
    }

    public final boolean q() {
        return this.e;
    }

    public final void c(boolean z) {
        this.e = true;
    }

    public final boolean r() {
        return this.f;
    }

    public final void d(boolean z) {
        this.f = z;
    }

    public final int s() {
        return this.g;
    }

    public final void c(int i) {
        this.g = i;
    }
}
