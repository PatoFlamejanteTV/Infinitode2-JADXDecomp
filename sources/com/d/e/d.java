package com.d.e;

import java.awt.Shape;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/e/d.class */
public final class d {
    public final void a(com.d.c.f.d dVar, Shape shape, t tVar, List<com.d.i.f> list, List<com.d.i.f> list2, h hVar) {
        if (tVar.j()) {
            b(dVar, shape, tVar, list, list2, hVar);
        } else {
            a(dVar, shape, tVar, tVar.f(), list, list2, hVar);
        }
    }

    public final boolean a(com.d.c.f.d dVar, Shape shape, com.d.i.f fVar) {
        return a(dVar, shape, fVar, fVar);
    }

    private void b(com.d.c.f.d dVar, Shape shape, t tVar, List<com.d.i.f> list, List<com.d.i.f> list2, h hVar) {
        for (com.d.i.f fVar : ((com.d.i.r) tVar.f()).l()) {
            if (fVar.a(dVar, shape)) {
                if (fVar instanceof com.d.i.r) {
                    list2.add(fVar);
                } else {
                    com.d.i.c cVar = (com.d.i.c) fVar;
                    if (cVar.v()) {
                        if (a(dVar, shape, fVar)) {
                            list2.add(fVar);
                        }
                    } else {
                        a(dVar, shape, tVar, cVar, list, list2, hVar);
                    }
                }
            }
        }
    }

    private static boolean a(Shape shape, com.d.i.f fVar) {
        if (shape == null) {
            return true;
        }
        y at = fVar.at();
        if (at == null) {
            return false;
        }
        return shape.intersects(at.a());
    }

    public final void a(com.d.c.f.d dVar, Shape shape, t tVar, com.d.i.f fVar, List<com.d.i.f> list, List<com.d.i.f> list2, h hVar) {
        if (tVar != fVar.af()) {
            return;
        }
        boolean z = fVar instanceof com.d.i.c;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        if (z) {
            i = list.size();
            i2 = list2.size();
            i3 = hVar.a().size();
            i4 = hVar.b().size();
        }
        if (fVar instanceof com.d.i.u) {
            if (a(shape, fVar) || (fVar.at() == null && fVar.a(dVar, shape))) {
                list2.add(fVar);
                ((com.d.i.u) fVar).a(list2, tVar);
            }
        } else {
            boolean a2 = a(shape, fVar);
            if ((fVar.Z() == null || !(fVar instanceof com.d.i.c)) && (a2 || (fVar.at() == null && fVar.a(dVar, shape)))) {
                list.add(fVar);
                if (fVar.a().q() && (dVar instanceof com.d.i.ab)) {
                    com.d.f.d dVar2 = (com.d.f.d) fVar;
                    if (dVar2.m()) {
                        dVar2.d((com.d.i.ab) dVar);
                    }
                }
            }
            if ((fVar.at() == null || a2) && (fVar.Z() == null || fVar == tVar.f())) {
                for (int i5 = 0; i5 < fVar.V(); i5++) {
                    a(dVar, shape, tVar, fVar.k(i5), list, list2, hVar);
                }
            }
        }
        a(dVar, fVar, list, list2, hVar, z, i, i2, i3, i4);
    }

    private static void a(com.d.c.f.d dVar, com.d.i.f fVar, List<com.d.i.f> list, List<com.d.i.f> list2, h hVar, boolean z, int i, int i2, int i3, int i4) {
        if (z && (dVar instanceof com.d.i.ab)) {
            com.d.i.c cVar = (com.d.i.c) fVar;
            if (cVar.P()) {
                int size = list.size();
                if (i != size) {
                    hVar.a().add(i3, new f(cVar, new e(i, size)));
                }
                int size2 = list2.size();
                if (i2 != size2) {
                    hVar.b().add(i4, new f(cVar, new e(i2, size2)));
                }
            }
        }
    }

    private boolean a(com.d.c.f.d dVar, Shape shape, com.d.i.f fVar, com.d.i.f fVar2) {
        if (fVar2 instanceof com.d.i.u) {
            if (fVar2.a(dVar, shape)) {
                return true;
            }
            return false;
        }
        if ((fVar2.Z() == null || !(fVar2 instanceof com.d.i.c)) && fVar2.a(dVar, shape)) {
            return true;
        }
        if (fVar2.Z() == null || fVar2 == fVar) {
            for (int i = 0; i < fVar2.V(); i++) {
                if (a(dVar, shape, fVar, fVar2.k(i))) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
