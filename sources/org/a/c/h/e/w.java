package org.a.c.h.e;

import java.io.IOException;

/* loaded from: infinitode-2.jar:org/a/c/h/e/w.class */
public final class w {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4564a = org.a.a.a.c.a(w.class);

    private w() {
    }

    public static u a(org.a.c.b.d dVar, org.a.c.h.k kVar) {
        org.a.c.b.j b2 = dVar.b(org.a.c.b.j.dN, org.a.c.b.j.be);
        if (!org.a.c.b.j.be.equals(b2)) {
            new StringBuilder("Expected 'Font' dictionary but found '").append(b2.a()).append("'");
        }
        org.a.c.b.j b3 = dVar.b(org.a.c.b.j.dE);
        if (org.a.c.b.j.dP.equals(b3)) {
            org.a.c.b.b a2 = dVar.a(org.a.c.b.j.bg);
            if ((a2 instanceof org.a.c.b.d) && ((org.a.c.b.d) a2).o(org.a.c.b.j.bk)) {
                return new ad(dVar);
            }
            return new ae(dVar);
        }
        if (org.a.c.b.j.cm.equals(b3)) {
            org.a.c.b.b a3 = dVar.a(org.a.c.b.j.bg);
            if ((a3 instanceof org.a.c.b.d) && ((org.a.c.b.d) a3).o(org.a.c.b.j.bk)) {
                return new ad(dVar);
            }
            return new x(dVar);
        }
        if (org.a.c.b.j.dK.equals(b3)) {
            return new ab(dVar);
        }
        if (org.a.c.b.j.dQ.equals(b3)) {
            return new ag(dVar, kVar);
        }
        if (org.a.c.b.j.dO.equals(b3)) {
            return new ac(dVar);
        }
        if (org.a.c.b.j.V.equals(b3)) {
            throw new IllegalArgumentException("Type 0 descendant font not allowed");
        }
        if (org.a.c.b.j.W.equals(b3)) {
            throw new IllegalArgumentException("Type 2 descendant font not allowed");
        }
        new StringBuilder("Invalid font subtype '").append(b3).append("'");
        return new ae(dVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static o a(org.a.c.b.d dVar, ac acVar) {
        org.a.c.b.j b2 = dVar.b(org.a.c.b.j.dN, org.a.c.b.j.be);
        if (!org.a.c.b.j.be.equals(b2)) {
            throw new IllegalArgumentException("Expected 'Font' dictionary but found '" + b2.a() + "'");
        }
        org.a.c.b.j b3 = dVar.b(org.a.c.b.j.dE);
        if (org.a.c.b.j.V.equals(b3)) {
            return new p(dVar, acVar);
        }
        if (org.a.c.b.j.W.equals(b3)) {
            return new q(dVar, acVar);
        }
        throw new IOException("Invalid font type: " + b2);
    }
}
