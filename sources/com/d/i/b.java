package com.d.i;

import java.util.List;
import org.w3c.dom.Element;

/* loaded from: infinitode-2.jar:com/d/i/b.class */
public final class b extends c {

    /* renamed from: a, reason: collision with root package name */
    private List<q> f1333a;

    public b(Element element) {
        a(element);
    }

    @Override // com.d.i.c
    public final void b(com.d.e.v vVar) {
        a(vVar, 0, m(vVar), true);
    }

    @Override // com.d.i.f
    public final int d_() {
        return Y().d_();
    }

    public final List<q> f() {
        return this.f1333a;
    }

    public final void a(List<q> list) {
        this.f1333a = list;
    }

    private static boolean a(com.d.e.ac acVar) {
        com.d.c.f.c a2 = acVar.a();
        return a2.C() || a2.A() || a2.B() || a2.P();
    }

    @Override // com.d.i.c
    public final boolean n() {
        return G().stream().allMatch(b::a);
    }

    public final void a(int i) {
        for (com.d.e.ac acVar : G()) {
            if (acVar instanceof c) {
                c cVar = (c) acVar;
                if (cVar.K()) {
                    cVar.L().a(i);
                }
            }
        }
    }

    @Override // com.d.i.c
    public final boolean k() {
        return false;
    }

    @Override // com.d.i.c
    public final void a_(com.d.e.v vVar) {
        a(vVar, U().a());
    }

    @Override // com.d.i.c
    public final c c() {
        throw new IllegalArgumentException("cannot be copied");
    }
}
