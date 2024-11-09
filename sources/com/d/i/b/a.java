package com.d.i.b;

import com.d.e.t;
import com.d.f.f;
import com.d.f.j;
import com.d.i.ab;
import com.d.i.c;
import com.d.i.u;
import com.d.i.y;
import com.d.i.z;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/i/b/a.class */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private List<com.d.d.b> f1334a = null;

    /* renamed from: b, reason: collision with root package name */
    private List<com.d.d.b> f1335b = null;
    private List<f> c = null;
    private List<com.d.d.b> d = null;
    private List<com.d.d.b> e = null;
    private boolean f = false;
    private boolean g = false;

    private void a(com.d.d.b bVar) {
        if (this.f1334a == null) {
            this.f1334a = new ArrayList();
        }
        this.f1334a.add(bVar);
    }

    private void b(com.d.d.b bVar) {
        if (this.f1335b == null) {
            this.f1335b = new ArrayList();
        }
        this.f1335b.add(bVar);
    }

    private void a(f fVar) {
        if (this.c == null) {
            this.c = new ArrayList();
        }
        this.c.add(fVar);
    }

    private void c(com.d.d.b bVar) {
        if (this.d == null) {
            this.d = new ArrayList();
        }
        this.d.add(bVar);
        if (!(bVar instanceof y) && !(bVar instanceof z)) {
            this.g = true;
        }
    }

    private void d(com.d.d.b bVar) {
        if (this.e == null) {
            this.e = new ArrayList();
        }
        this.e.add(bVar);
        if (!(bVar instanceof y) && !(bVar instanceof z)) {
            this.f = true;
        }
    }

    private void a(y yVar) {
        a((com.d.d.b) yVar);
        b(yVar);
        c(yVar);
        d(yVar);
    }

    private void a(z zVar) {
        a((com.d.d.b) zVar);
        b(zVar);
        c(zVar);
        d(zVar);
    }

    public final List<com.d.d.b> a() {
        return this.f1334a == null ? Collections.emptyList() : this.f1334a;
    }

    public final List<com.d.d.b> b() {
        return this.f1335b == null ? Collections.emptyList() : this.f1335b;
    }

    public final List<f> c() {
        return this.c == null ? Collections.emptyList() : this.c;
    }

    public final List<com.d.d.b> d() {
        return this.g ? this.d : Collections.emptyList();
    }

    public final List<com.d.d.b> e() {
        return this.f ? this.e : Collections.emptyList();
    }

    private boolean a(com.d.i.f fVar, Shape shape) {
        a(fVar);
        if (fVar instanceof c) {
            c cVar = (c) fVar;
            if (cVar.E() != null) {
                c(cVar);
            }
            if (cVar.u()) {
                d(cVar);
            }
        }
        if ((fVar instanceof f) && ((f) fVar).o()) {
            a((f) fVar);
        }
        if (shape != null) {
            a(new y(shape));
            return true;
        }
        return false;
    }

    public final void a(ab abVar, t tVar) {
        if (!tVar.j()) {
            a(abVar, tVar, tVar.f());
        }
    }

    public final void a(ab abVar, t tVar, com.d.i.f fVar) {
        if (tVar != fVar.af()) {
            return;
        }
        if (fVar instanceof u) {
            a(tVar, (u) fVar);
            return;
        }
        Rectangle rectangle = null;
        boolean z = false;
        if (fVar.Z() == null || tVar.f() == fVar || !(fVar instanceof c)) {
            if ((abVar instanceof ab) && (fVar instanceof c)) {
                c cVar = (c) fVar;
                if (cVar.P()) {
                    rectangle = cVar.k(abVar);
                }
            }
            z = a(fVar, (Shape) rectangle);
        }
        if ((!(fVar instanceof j) || ((!((j) fVar).q() && !((j) fVar).p()) || !((j) fVar).f().m() || ((fVar.Z() != null && fVar != tVar.f()) || !(abVar instanceof ab)))) && (fVar.Z() == null || fVar == tVar.f())) {
            for (int i = 0; i < fVar.V(); i++) {
                a(abVar, tVar, fVar.k(i));
            }
        }
        if (z) {
            a(new z(null));
        }
    }

    private void a(t tVar, u uVar) {
        b(uVar);
        uVar.a(this.f1335b, tVar);
    }
}
