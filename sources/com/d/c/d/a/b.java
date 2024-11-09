package com.d.c.d.a;

import com.d.i.v;
import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/c/d/a/b.class */
public final class b extends a {

    /* renamed from: a, reason: collision with root package name */
    private static final com.d.c.a.a[] f993a = {com.d.c.a.a.c, com.d.c.a.a.d, com.d.c.a.a.e, com.d.c.a.a.f, com.d.c.a.a.g};

    private boolean a(com.d.c.d.j jVar) {
        com.d.c.a.c b2;
        short a2 = jVar.a();
        if (a((com.d.c.d.d) jVar) || a2 == 2) {
            return true;
        }
        return a2 == 21 && (b2 = com.d.c.a.c.b(jVar.c())) != null && l.n.get(b2.f968a);
    }

    @Override // com.d.c.d.a.m
    public final List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
        List a2 = a(f993a, list, i, z, z2);
        if (a2 != null) {
            return a2;
        }
        v vVar = null;
        v vVar2 = null;
        v vVar3 = null;
        v vVar4 = null;
        v vVar5 = null;
        int i2 = 0;
        while (i2 < list.size()) {
            com.d.c.d.j jVar = (com.d.c.d.j) list.get(i2);
            a((com.d.c.d.d) jVar, false);
            boolean z3 = false;
            short a3 = jVar.a();
            if (a3 != 21) {
                if (a3 == 25) {
                    if (vVar == null) {
                        vVar = new v(com.d.c.a.a.c, jVar, z, i);
                    } else {
                        throw new com.d.c.d.b("A background-color value cannot be set twice", -1);
                    }
                } else if (a3 == 20) {
                    if (vVar2 == null) {
                        vVar2 = new v(com.d.c.a.a.d, jVar, z, i);
                    } else {
                        throw new com.d.c.d.b("A background-image value cannot be set twice", -1);
                    }
                }
            } else {
                com.d.c.d.h a4 = f.a(jVar.c());
                if (a4 != null) {
                    if (vVar != null) {
                        throw new com.d.c.d.b("A background-color value cannot be set twice", -1);
                    }
                    vVar = new v(com.d.c.a.a.c, new com.d.c.d.j(a4), z, i);
                    i2++;
                } else {
                    com.d.c.a.a aVar2 = com.d.c.a.a.be;
                    com.d.c.a.c b2 = b(jVar);
                    if (l.l.get(b2.f968a)) {
                        if (vVar3 == null) {
                            vVar3 = new v(com.d.c.a.a.e, jVar, z, i);
                        } else {
                            throw new com.d.c.d.b("A background-repeat value cannot be set twice", -1);
                        }
                    }
                    if (l.m.get(b2.f968a)) {
                        if (vVar4 == null) {
                            vVar4 = new v(com.d.c.a.a.f, jVar, z, i);
                        } else {
                            throw new com.d.c.d.b("A background-attachment value cannot be set twice", -1);
                        }
                    }
                    if (b2 == com.d.c.a.c.bj) {
                        if (vVar == null) {
                            vVar = new v(com.d.c.a.a.c, jVar, z, i);
                        } else {
                            throw new com.d.c.d.b("A background-color value cannot be set twice", -1);
                        }
                    }
                    if (b2 == com.d.c.a.c.ap) {
                        if (vVar2 == null) {
                            vVar2 = new v(com.d.c.a.a.d, jVar, z, i);
                        } else {
                            throw new com.d.c.d.b("A background-image value cannot be set twice", -1);
                        }
                    }
                    if (l.n.get(b2.f968a)) {
                        z3 = true;
                    }
                }
            }
            if (z3 || a((com.d.c.d.d) jVar) || a3 == 2) {
                if (vVar5 != null) {
                    throw new com.d.c.d.b("A background-position value cannot be set twice", -1);
                }
                ArrayList arrayList = new ArrayList(2);
                arrayList.add(jVar);
                if (i2 < list.size() - 1) {
                    com.d.c.d.j jVar2 = (com.d.c.d.j) list.get(i2 + 1);
                    if (a(jVar2)) {
                        arrayList.add(jVar2);
                        i2++;
                    }
                }
                vVar5 = (v) com.d.c.a.a.e(com.d.c.a.a.g).a(com.d.c.a.a.g, arrayList, i, z).get(0);
            }
            i2++;
        }
        if (vVar == null) {
            vVar = new v(com.d.c.a.a.c, new com.d.c.d.j(com.d.c.a.c.bj), z, i);
        }
        if (vVar2 == null) {
            vVar2 = new v(com.d.c.a.a.d, new com.d.c.d.j(com.d.c.a.c.ap), z, i);
        }
        if (vVar3 == null) {
            vVar3 = new v(com.d.c.a.a.e, new com.d.c.d.j(com.d.c.a.c.aF), z, i);
        }
        if (vVar4 == null) {
            vVar4 = new v(com.d.c.a.a.f, new com.d.c.d.j(com.d.c.a.c.aM), z, i);
        }
        if (vVar5 == null) {
            ArrayList arrayList2 = new ArrayList(2);
            arrayList2.add(new com.d.c.d.j((short) 2, 0.0f, "0%"));
            arrayList2.add(new com.d.c.d.j((short) 2, 0.0f, "0%"));
            vVar5 = new v(com.d.c.a.a.g, new com.d.c.d.j(arrayList2), z, i);
        }
        ArrayList arrayList3 = new ArrayList(5);
        arrayList3.add(vVar);
        arrayList3.add(vVar2);
        arrayList3.add(vVar3);
        arrayList3.add(vVar4);
        arrayList3.add(vVar5);
        return arrayList3;
    }
}
