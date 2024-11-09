package com.d.e;

import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/* loaded from: infinitode-2.jar:com/d/e/c.class */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private static /* synthetic */ boolean f1126a;

    static {
        f1126a = !c.class.desiredAssertionStatus();
    }

    private static void b(v vVar, Document document) {
        vVar.f().a(vVar, document);
        vVar.f().a(vVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14, types: [com.d.i.c] */
    public static com.d.i.c a(v vVar, Document document) {
        com.d.f.d dVar;
        b(vVar, document);
        Element documentElement = document.getDocumentElement();
        com.d.c.f.c a2 = vVar.y().a(documentElement);
        if (a2.q() || a2.r()) {
            dVar = new com.d.f.d();
        } else {
            dVar = new com.d.i.c();
        }
        dVar.a(a2);
        dVar.a(documentElement);
        vVar.a(a2);
        return dVar;
    }

    public static void a(v vVar, com.d.i.c cVar) {
        if (cVar.z()) {
            cVar.g(4);
            return;
        }
        ArrayList arrayList = new ArrayList();
        a aVar = new a();
        a(vVar, cVar, cVar.ai(), (List<ac>) arrayList, aVar, false);
        boolean c = c(cVar.a().e(com.d.c.a.a.G));
        if (!c && !aVar.b()) {
            a(vVar, cVar, arrayList, aVar);
            return;
        }
        b(arrayList);
        if (c) {
            b(vVar, cVar, arrayList, aVar);
        } else {
            a(vVar, cVar, arrayList, aVar, com.d.c.a.c.aX);
        }
    }

    public static com.d.f.d a(v vVar, com.d.c.c.e eVar, com.d.c.a.d[] dVarArr, int i, int i2) {
        com.d.f.f a2;
        if (!eVar.a(dVarArr)) {
            return null;
        }
        Element ai = vVar.p().f().ai();
        a aVar = new a();
        com.d.c.f.c a3 = new com.d.c.f.f().a(eVar.a());
        com.d.c.f.c a4 = a3.a(com.d.c.c.a.a(new com.d.i.v[]{new com.d.i.v(com.d.c.a.a.G, new com.d.c.d.j(com.d.c.a.c.aV), true, 1), new com.d.i.v(com.d.c.a.a.ax, new com.d.c.d.j((short) 2, 100.0f, "100%"), true, 1)}));
        com.d.f.d dVar = (com.d.f.d) a(a4, aVar, false);
        dVar.a(true);
        dVar.a(a4);
        dVar.a(ai);
        dVar.i(true);
        dVar.g(2);
        com.d.c.f.c a5 = a3.a(com.d.c.a.c.bd);
        com.d.f.j jVar = (com.d.f.j) a(a5, aVar, false);
        jVar.a(a5);
        jVar.a(ai);
        jVar.i(true);
        jVar.g(2);
        dVar.b(jVar);
        com.d.f.i iVar = null;
        if (i2 == 2) {
            com.d.c.f.c a6 = a3.a(com.d.c.a.c.bc);
            com.d.f.i iVar2 = (com.d.f.i) a(a6, aVar, false);
            iVar = iVar2;
            iVar2.a(a6);
            iVar.a(ai);
            iVar.i(true);
            iVar.g(2);
            iVar.a(i);
            jVar.b(iVar);
        }
        int i3 = 0;
        boolean z = dVarArr.length > 1 && i2 == 2;
        for (com.d.c.a.d dVar2 : dVarArr) {
            com.d.c.c.a a7 = eVar.a(dVar2, z);
            if (a7 != null && (a2 = a(vVar, a7, z)) != null) {
                if (i2 == 1) {
                    com.d.c.f.c a8 = a3.a(com.d.c.a.c.bc);
                    com.d.f.i iVar3 = (com.d.f.i) a(a8, aVar, false);
                    iVar = iVar3;
                    iVar3.a(a8);
                    iVar.a(ai);
                    iVar.i(true);
                    iVar.g(2);
                    iVar.a(i);
                    jVar.b(iVar);
                }
                iVar.b(a2);
                i3++;
            }
        }
        if (i2 == 1 && i3 > 0) {
            int i4 = 0;
            Iterator<com.d.i.f> W = jVar.W();
            while (W.hasNext()) {
                com.d.f.i iVar4 = (com.d.f.i) W.next();
                iVar4.a(i / i3);
                i4 += iVar4.f();
            }
            Iterator<com.d.i.f> W2 = jVar.W();
            while (W2.hasNext() && i4 < i) {
                com.d.f.i iVar5 = (com.d.f.i) W2.next();
                iVar5.a(iVar5.f() + 1);
                i4++;
            }
        }
        if (i3 > 0) {
            return dVar;
        }
        return null;
    }

    private static com.d.f.f a(v vVar, com.d.c.c.a aVar, boolean z) {
        boolean z2 = true;
        com.d.i.v a2 = aVar.a(com.d.c.a.a.C);
        com.d.c.f.c a3 = new com.d.c.f.f().a(aVar);
        if (a3.w() && !z) {
            return null;
        }
        if (a3.a(com.d.c.a.a.C, com.d.c.a.c.ap) || a3.a(com.d.c.a.a.C, com.d.c.a.c.aq)) {
            z2 = false;
        }
        if (a3.H() && !z && !z2) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        a aVar2 = new a();
        aVar2.b(true);
        aVar2.c(true);
        com.d.f.f fVar = new com.d.f.f();
        fVar.i(true);
        fVar.a(a3);
        fVar.a(vVar.p().f().ai());
        if (z2 && !a3.w()) {
            arrayList.addAll(a(vVar, vVar.p().f().ai(), (com.d.c.d.j) a2.e(), a3, aVar2));
            b(arrayList);
        }
        a(vVar, fVar, arrayList, aVar2, com.d.c.a.c.aX);
        return fVar;
    }

    private static void a(v vVar, com.d.i.c cVar, List<ac> list, a aVar) {
        if (list.size() > 0) {
            if (aVar.a()) {
                a(vVar.y(), cVar, list, aVar.c());
                cVar.g(2);
                return;
            }
            ag.a(list);
            if (list.size() > 0) {
                cVar.b(list);
                cVar.g(1);
                return;
            } else {
                cVar.g(4);
                return;
            }
        }
        cVar.g(4);
    }

    private static boolean a(com.d.c.a.c cVar, List<ac> list) {
        return list.stream().allMatch(acVar -> {
            return b(cVar, acVar.a().e(com.d.c.a.a.G));
        });
    }

    private static void a(v vVar, com.d.i.c cVar, List<ac> list, a aVar, com.d.c.a.c cVar2) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        com.d.c.a.c b2 = b(cVar2);
        for (ac acVar : list) {
            if (a(cVar2, acVar.a().e(com.d.c.a.a.G))) {
                arrayList.add(acVar);
            } else {
                if (arrayList.size() > 0) {
                    a(vVar, (com.d.i.c) arrayList.get(0), b2, arrayList, arrayList2);
                    arrayList = new ArrayList();
                }
                arrayList2.add(acVar);
            }
        }
        if (arrayList.size() > 0) {
            a(vVar, (com.d.i.c) arrayList.get(0), b2, arrayList, arrayList2);
        }
        if (b2 == com.d.c.a.c.aV) {
            a(arrayList2);
            aVar.a(true);
            a(vVar, cVar, arrayList2, aVar);
            return;
        }
        a(vVar, cVar, arrayList2, aVar, b2);
    }

    private static boolean a(com.d.c.a.c cVar, com.d.c.a.c cVar2) {
        return cVar == com.d.c.a.c.bd ? cVar2 == com.d.c.a.c.bd || cVar2 == com.d.c.a.c.bb || cVar2 == com.d.c.a.c.ba || cVar2 == com.d.c.a.c.aW : cVar == cVar2;
    }

    private static void a(List<ac> list) {
        HashMap hashMap = new HashMap();
        for (ac acVar : list) {
            if (acVar instanceof com.d.i.q) {
                com.d.i.q qVar = (com.d.i.q) acVar;
                Element h = qVar.h();
                if (!hashMap.containsKey(h)) {
                    qVar.c(true);
                }
                hashMap.put(h, qVar);
            }
        }
        Iterator it = hashMap.values().iterator();
        while (it.hasNext()) {
            ((com.d.i.q) it.next()).b(true);
        }
    }

    private static void b(List<ac> list) {
        int i = 0;
        boolean z = false;
        int i2 = 0;
        while (i2 < list.size()) {
            if (!list.get(i2).a().y()) {
                if (z) {
                    int size = list.size();
                    ag.a(list.subList(i, i2));
                    i2 -= size - list.size();
                }
                z = false;
            } else if (!z) {
                z = true;
                i = i2;
            }
            i2++;
        }
        if (z) {
            ag.a(list.subList(i, i2));
        }
    }

    private static void b(v vVar, com.d.i.c cVar, List<ac> list, a aVar) {
        com.d.c.a.c e = cVar.a().e(com.d.c.a.a.G);
        com.d.c.a.c a2 = a(e);
        if (a2 == null && cVar.au() && c(list)) {
            a(vVar, cVar, list, aVar, com.d.c.a.c.aX);
            return;
        }
        if (a2 == null || a(e, list)) {
            if (cVar.au()) {
                a(list);
            }
            a(vVar, cVar, list, aVar);
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (ac acVar : list) {
            if (!b(e, acVar.a().e(com.d.c.a.a.G))) {
                arrayList.add(acVar);
            } else {
                if (arrayList.size() > 0) {
                    a(vVar, cVar, a2, arrayList, arrayList2);
                    arrayList = new ArrayList();
                }
                arrayList2.add(acVar);
            }
        }
        if (arrayList.size() > 0) {
            a(vVar, cVar, a2, arrayList, arrayList2);
        }
        aVar.a(true);
        a(vVar, cVar, arrayList2, aVar);
    }

    private static boolean a(ac acVar) {
        com.d.c.a.c e = acVar.a().e(com.d.c.a.a.G);
        return e == com.d.c.a.c.bb || e == com.d.c.a.c.bd || e == com.d.c.a.c.ba || e == com.d.c.a.c.bc;
    }

    private static boolean c(List<ac> list) {
        return list.stream().anyMatch(c::a);
    }

    private static boolean a(com.d.i.c cVar) {
        com.d.c.f.c a2 = cVar.a().a();
        return a2 != null && a2.o();
    }

    private static void a(v vVar, com.d.i.c cVar, com.d.c.a.c cVar2, List<ac> list, List<ac> list2) {
        com.d.c.a.c cVar3;
        a d = d(list);
        if (a(cVar) && cVar2 == com.d.c.a.c.aV) {
            cVar3 = com.d.c.a.c.T;
        } else {
            cVar3 = cVar2;
        }
        com.d.c.f.c a2 = cVar.a().a(cVar3);
        com.d.i.c a3 = a(a2, d, false);
        a3.a(a2);
        a3.i(true);
        a3.a(cVar.ai());
        b(vVar, a3, list, d);
        if (cVar2 == com.d.c.a.c.aV) {
            list2.add(a(vVar, (com.d.f.d) a3));
        } else {
            list2.add(a3);
        }
    }

    private static com.d.i.c a(v vVar, com.d.f.d dVar) {
        com.d.c.f.c a2;
        ArrayList arrayList = new ArrayList();
        com.d.i.f fVar = null;
        ArrayList arrayList2 = new ArrayList();
        com.d.i.f fVar2 = null;
        ArrayList arrayList3 = new ArrayList();
        for (com.d.i.f fVar3 : dVar.X()) {
            com.d.c.a.c e = fVar3.a().e(com.d.c.a.a.G);
            if (e != com.d.c.a.c.aW) {
                if (e == com.d.c.a.c.bb && fVar == null) {
                    fVar = fVar3;
                } else if (e == com.d.c.a.c.ba && fVar2 == null) {
                    fVar2 = fVar3;
                } else {
                    arrayList2.add(fVar3);
                }
            } else if (fVar3.a().e(com.d.c.a.a.y) == com.d.c.a.c.l) {
                arrayList3.add(fVar3);
            } else {
                arrayList.add(fVar3);
            }
        }
        dVar.R();
        if (fVar != null) {
            ((com.d.f.j) fVar).c(true);
            dVar.b(fVar);
        }
        dVar.c(arrayList2);
        if (fVar2 != null) {
            ((com.d.f.j) fVar2).b(true);
            dVar.b(fVar2);
        }
        if (arrayList.size() == 0 && arrayList3.size() == 0) {
            return dVar;
        }
        if (dVar.a().C()) {
            a2 = dVar.a().a(com.d.c.c.a.a(new com.d.i.v[]{com.d.c.c.a.a(com.d.c.a.a.G, com.d.c.a.c.h), com.d.c.c.a.a(com.d.c.a.a.I, dVar.a().e(com.d.c.a.a.I))}));
        } else {
            a2 = dVar.a().a(com.d.c.a.c.h);
        }
        com.d.i.c cVar = new com.d.i.c();
        cVar.a(a2);
        cVar.i(true);
        cVar.h(true);
        cVar.a(dVar.ai());
        cVar.g(2);
        cVar.c(arrayList);
        cVar.b(dVar);
        cVar.c(arrayList3);
        if (dVar.a().C()) {
            cVar.a(new com.d.i.n());
            dVar.a((com.d.i.n) null);
            dVar.a(dVar.a().a().a(com.d.c.c.a.a(vVar.y().j().a(dVar.ai(), false), new com.d.i.v[]{com.d.c.c.a.a(com.d.c.a.a.I, com.d.c.a.c.ap)})));
        }
        return cVar;
    }

    private static a d(List<ac> list) {
        a aVar = new a();
        if (list.stream().anyMatch(acVar -> {
            return !acVar.a().y();
        })) {
            aVar.a(true);
        }
        return aVar;
    }

    private static com.d.c.a.c a(com.d.c.a.c cVar) {
        if (cVar == com.d.c.a.c.aV || cVar == com.d.c.a.c.T) {
            return com.d.c.a.c.bd;
        }
        if (cVar == com.d.c.a.c.bb || cVar == com.d.c.a.c.bd || cVar == com.d.c.a.c.ba) {
            return com.d.c.a.c.bc;
        }
        if (cVar == com.d.c.a.c.bc) {
            return com.d.c.a.c.aX;
        }
        return null;
    }

    private static com.d.c.a.c b(com.d.c.a.c cVar) {
        if (cVar == com.d.c.a.c.aX) {
            return com.d.c.a.c.bc;
        }
        if (cVar == com.d.c.a.c.bc) {
            return com.d.c.a.c.bd;
        }
        if (cVar == com.d.c.a.c.bb || cVar == com.d.c.a.c.bd || cVar == com.d.c.a.c.ba) {
            return com.d.c.a.c.aV;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(com.d.c.a.c cVar, com.d.c.a.c cVar2) {
        if (cVar == com.d.c.a.c.aV && (cVar2 == com.d.c.a.c.bb || cVar2 == com.d.c.a.c.bd || cVar2 == com.d.c.a.c.ba || cVar2 == com.d.c.a.c.aW)) {
            return true;
        }
        if ((cVar == com.d.c.a.c.bb || cVar == com.d.c.a.c.bd || cVar == com.d.c.a.c.ba) && cVar2 == com.d.c.a.c.bc) {
            return true;
        }
        if (cVar == com.d.c.a.c.bc && cVar2 == com.d.c.a.c.aX) {
            return true;
        }
        if (cVar == com.d.c.a.c.T) {
            return cVar2 == com.d.c.a.c.bb || cVar2 == com.d.c.a.c.bd || cVar2 == com.d.c.a.c.ba;
        }
        return false;
    }

    private static boolean c(com.d.c.a.c cVar) {
        return cVar == com.d.c.a.c.aV || cVar == com.d.c.a.c.T || cVar == com.d.c.a.c.bb || cVar == com.d.c.a.c.bd || cVar == com.d.c.a.c.ba || cVar == com.d.c.a.c.bc;
    }

    private static boolean b(com.d.i.e eVar) {
        if (eVar.a().equals("attr")) {
            List<com.d.c.d.j> b2 = eVar.b();
            return b2.size() == 1 && b2.get(0).a() == 21;
        }
        return false;
    }

    public static boolean a(com.d.i.e eVar) {
        if (eVar.a().equals("element")) {
            List<com.d.c.d.j> b2 = eVar.b();
            if (b2.size() <= 0 || b2.size() > 2) {
                return false;
            }
            boolean z = b2.get(0).a() == 21;
            boolean z2 = z;
            if (z && b2.size() == 2) {
                z2 = b2.get(1).a() == 21;
            }
            return z2;
        }
        return false;
    }

    private static l a(com.d.i.e eVar, v vVar, com.d.c.f.c cVar) {
        if (eVar.a().equals("counter")) {
            List<com.d.c.d.j> b2 = eVar.b();
            if (b2.size() <= 0 || b2.size() > 2) {
                return null;
            }
            com.d.c.d.j jVar = b2.get(0);
            if (jVar.a() != 21) {
                return null;
            }
            String c = jVar.c();
            if (c.equals("page") || c.equals("pages")) {
                return null;
            }
            String c2 = jVar.c();
            com.d.c.a.c cVar2 = com.d.c.a.c.v;
            if (b2.size() == 2) {
                com.d.c.d.j jVar2 = b2.get(1);
                if (jVar2.a() != 21) {
                    return null;
                }
                com.d.c.a.c b3 = com.d.c.a.c.b(jVar2.c());
                if (b3 != null) {
                    jVar2.a(b3);
                    cVar2 = b3;
                }
            }
            return new l(vVar.b(cVar).a(c2), cVar2);
        }
        if (eVar.a().equals("counters")) {
            List<com.d.c.d.j> b4 = eVar.b();
            if (b4.size() < 2 || b4.size() > 3) {
                return null;
            }
            com.d.c.d.j jVar3 = b4.get(0);
            if (jVar3.a() != 21) {
                return null;
            }
            String c3 = jVar3.c();
            com.d.c.d.j jVar4 = b4.get(1);
            if (jVar4.a() != 19) {
                return null;
            }
            String c4 = jVar4.c();
            com.d.c.a.c cVar3 = com.d.c.a.c.v;
            if (b4.size() == 3) {
                com.d.c.d.j jVar5 = b4.get(2);
                if (jVar5.a() != 21) {
                    return null;
                }
                com.d.c.a.c b5 = com.d.c.a.c.b(jVar5.c());
                if (b5 != null) {
                    jVar5.a(b5);
                    cVar3 = b5;
                }
            }
            return new l(vVar.b(cVar).b(c3), c4, cVar3);
        }
        return null;
    }

    private static String a(com.d.i.e eVar, Element element) {
        return element.getAttribute(eVar.b().get(0).c());
    }

    private static List<ac> a(v vVar, Element element, com.d.c.d.j jVar, String str, com.d.c.f.c cVar, int i, a aVar) {
        List<com.d.c.d.j> l = jVar.l();
        ArrayList arrayList = new ArrayList(l.size());
        for (com.d.c.d.j jVar2 : l) {
            com.d.c.b.b bVar = null;
            com.d.i.e eVar = null;
            String str2 = null;
            short a2 = jVar2.a();
            if (a2 == 19) {
                str2 = jVar2.c();
            } else if (jVar2.i() == 7) {
                if (i == 1 && b(jVar2.n())) {
                    str2 = a(jVar2.n(), element);
                } else {
                    l lVar = null;
                    if (i == 1) {
                        lVar = a(jVar2.n(), vVar, cVar);
                    }
                    if (lVar != null) {
                        str2 = lVar.a();
                        bVar = null;
                        eVar = null;
                    } else if (i == 2 && a(jVar2.n())) {
                        com.d.i.c a3 = a(vVar, jVar2);
                        if (a3 != null) {
                            arrayList.add(a3.c());
                            aVar.a(true);
                        }
                    } else {
                        com.d.c.b.b a4 = vVar.x().a(vVar, jVar2.n());
                        bVar = a4;
                        if (a4 != null) {
                            eVar = jVar2.n();
                            str2 = bVar.a();
                        }
                    }
                }
            } else if (a2 == 21 && cVar.i(com.d.c.a.a.ai) != com.d.c.a.c.ap) {
                com.d.c.a.c h = jVar2.h();
                if (h != com.d.c.a.c.au) {
                    if (h == com.d.c.a.c.p) {
                        str2 = cVar.c(com.d.c.a.a.ai)[1];
                    }
                } else {
                    str2 = cVar.c(com.d.c.a.a.ai)[0];
                }
            }
            if (str2 != null) {
                com.d.i.q qVar = new com.d.i.q(str2, null);
                qVar.a(bVar);
                qVar.a(eVar);
                qVar.a(element);
                qVar.b(str);
                qVar.c(true);
                qVar.b(true);
                arrayList.add(qVar);
            }
        }
        return arrayList;
    }

    public static com.d.i.c a(v vVar, com.d.c.d.j jVar) {
        List<com.d.c.d.j> b2 = jVar.n().b();
        String c = b2.get(0).c();
        com.d.c.a.e eVar = null;
        if (b2.size() == 2) {
            eVar = com.d.c.a.e.a(b2.get(1).c());
        }
        if (eVar == null) {
            eVar = com.d.c.a.e.f973b;
        }
        return vVar.F().a(c, vVar.G(), eVar);
    }

    private static void a(v vVar, Element element, com.d.c.f.c cVar, String str, List<ac> list, a aVar) {
        com.d.c.c.a a2 = vVar.c().a(element, str);
        if (a2 != null) {
            com.d.i.v a3 = a2.a(com.d.c.a.a.C);
            com.d.i.v a4 = a2.a(com.d.c.a.a.E);
            com.d.i.v a5 = a2.a(com.d.c.a.a.D);
            com.d.c.f.c cVar2 = null;
            if (a3 != null || a4 != null || a5 != null) {
                com.d.c.f.c a6 = cVar.a(a2);
                cVar2 = a6;
                if (a6.w() || cVar2.a(com.d.c.a.a.C, com.d.c.a.c.ap)) {
                    return;
                }
                if (cVar2.a(com.d.c.a.a.C, com.d.c.a.c.aq) && (str.equals("before") || str.equals("after"))) {
                    return;
                }
                if (cVar2.q() || cVar2.v() || cVar2.t()) {
                    cVar2 = cVar.a(com.d.c.c.a.a(a2, new com.d.i.v[]{com.d.c.c.a.a(com.d.c.a.a.G, com.d.c.a.c.h)}));
                }
                vVar.a(cVar2);
            }
            if (a3 != null) {
                list.addAll(a(vVar, element, str, cVar2, (com.d.c.d.j) a3.e(), aVar));
            }
        }
    }

    private static List<ac> a(v vVar, Element element, String str, com.d.c.f.c cVar, com.d.c.d.j jVar, a aVar) {
        if (cVar.w() || cVar.a(com.d.c.a.a.G, com.d.c.a.c.aY) || cVar.a(com.d.c.a.a.G, com.d.c.a.c.aZ)) {
            return Collections.emptyList();
        }
        List<ac> a2 = a(vVar, element, jVar, str, cVar, 1, null);
        if (cVar.o()) {
            Iterator<ac> it = a2.iterator();
            while (it.hasNext()) {
                com.d.i.q qVar = (com.d.i.q) it.next();
                qVar.a(cVar);
                qVar.d();
            }
            return a2;
        }
        com.d.c.f.c a3 = cVar.a(com.d.c.a.c.R);
        Iterator<ac> it2 = a2.iterator();
        while (it2.hasNext()) {
            com.d.i.q qVar2 = (com.d.i.q) it2.next();
            qVar2.a(a3);
            qVar2.d();
            qVar2.a((Element) null);
        }
        com.d.i.c a4 = a(cVar, aVar, true);
        a4.a(cVar);
        a4.b(a2);
        a4.a(element);
        a4.g(1);
        a4.a(str);
        if (!cVar.y()) {
            aVar.a(true);
        }
        return new ArrayList(Collections.singletonList(a4));
    }

    private static List<ac> a(v vVar, Element element, com.d.c.d.j jVar, com.d.c.f.c cVar, a aVar) {
        List<ac> a2 = a(vVar, element, jVar, null, cVar, 2, aVar);
        com.d.c.f.c a3 = cVar.a(com.d.c.a.c.R);
        for (ac acVar : a2) {
            if (acVar instanceof com.d.i.q) {
                com.d.i.q qVar = (com.d.i.q) acVar;
                qVar.a((Element) null);
                qVar.a(a3);
                qVar.d();
            }
        }
        return a2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v41, types: [com.d.i.c] */
    private static com.d.i.c a(com.d.c.f.c cVar, a aVar, boolean z) {
        com.d.f.d dVar;
        if (cVar.C() && !cVar.A() && !cVar.B()) {
            if (cVar.q() || cVar.r()) {
                dVar = new com.d.f.d();
            } else {
                dVar = new com.d.i.c();
            }
            dVar.a(new com.d.i.n());
            return dVar;
        }
        if (cVar.x()) {
            return new com.d.i.c();
        }
        if (!z && (cVar.q() || cVar.r())) {
            return new com.d.f.d();
        }
        if (cVar.s()) {
            aVar.b(true);
            return new com.d.f.f();
        }
        if (!z && cVar.v()) {
            aVar.b(true);
            return new com.d.f.i();
        }
        if (!z && cVar.t()) {
            aVar.b(true);
            return new com.d.f.j();
        }
        if (cVar.u()) {
            aVar.b(true);
            return new com.d.i.c();
        }
        return new com.d.i.c();
    }

    private static void a(v vVar, com.d.f.d dVar, com.d.f.h hVar) {
        aa y = vVar.y();
        boolean z = false;
        for (Node firstChild = hVar.b().getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            if (firstChild.getNodeType() == 1) {
                Element element = (Element) firstChild;
                com.d.c.f.c a2 = y.a(element);
                if (a2.a(com.d.c.a.a.G, com.d.c.a.c.aY)) {
                    z = true;
                    com.d.f.h hVar2 = new com.d.f.h(element, a2);
                    hVar2.a(hVar);
                    dVar.a(hVar2);
                }
            }
        }
        if (!z) {
            dVar.a(hVar);
        }
    }

    private static void a(v vVar, com.d.f.d dVar, Element element, com.d.c.f.c cVar) {
        if (cVar.a(com.d.c.a.a.G, com.d.c.a.c.aY)) {
            dVar.a(new com.d.f.h(element, cVar));
        } else {
            a(vVar, dVar, new com.d.f.h(element, cVar));
        }
    }

    private static com.d.i.q a(String str, Element element, com.d.c.f.c cVar, Text text) {
        com.d.i.q qVar = new com.d.i.q(str, text);
        if (cVar.o() && !(element.getParentNode() instanceof Document)) {
            qVar.a(cVar);
            qVar.a(element);
        } else {
            qVar.a(cVar.a(com.d.c.a.c.R));
        }
        qVar.d();
        return qVar;
    }

    private static void a(v vVar, com.d.i.c cVar, Element element, List<ac> list, a aVar, boolean z) {
        Node nextSibling;
        Node namedItem;
        aa y = vVar.y();
        com.d.c.f.c a2 = y.a(element);
        a(vVar, element, a2, "before", list, aVar);
        Node firstChild = element.getFirstChild();
        boolean z2 = z;
        boolean z3 = z;
        if (firstChild != null) {
            com.d.i.q qVar = null;
            do {
                ac acVar = null;
                short nodeType = firstChild.getNodeType();
                if (nodeType != 1) {
                    if (nodeType == 3 || nodeType == 4) {
                        z2 = false;
                        z3 = false;
                        Text text = (Text) firstChild;
                        if (!text.getParentNode().getNodeName().equals("textarea")) {
                            qVar = a(vVar, text, element, a2, qVar, list);
                        }
                        acVar = null;
                    }
                } else {
                    Element element2 = (Element) firstChild;
                    com.d.c.f.c a3 = y.a(element2);
                    if (!a3.w()) {
                        Integer num = null;
                        if (FlexmarkHtmlConverter.OL_NODE.equals(firstChild.getNodeName())) {
                            Node namedItem2 = firstChild.getAttributes().getNamedItem("start");
                            if (namedItem2 != null) {
                                try {
                                    num = new Integer(Integer.parseInt(namedItem2.getNodeValue()) - 1);
                                } catch (NumberFormatException unused) {
                                }
                            }
                        } else if (FlexmarkHtmlConverter.LI_NODE.equals(firstChild.getNodeName()) && (namedItem = firstChild.getAttributes().getNamedItem("value")) != null) {
                            try {
                                num = new Integer(Integer.parseInt(namedItem.getNodeValue()) - 1);
                            } catch (NumberFormatException unused2) {
                            }
                        }
                        vVar.a(a3, num);
                        if (a3.a(com.d.c.a.a.G, com.d.c.a.c.aY) || a3.a(com.d.c.a.a.G, com.d.c.a.c.aZ)) {
                            if (cVar != null && (cVar.a().q() || cVar.a().r())) {
                                a(vVar, (com.d.f.d) cVar, element2, a3);
                            }
                        } else if (a3.o()) {
                            if (z2) {
                                z2 = false;
                                com.d.i.q a4 = a("", element, a2, (Text) null);
                                a4.c(true);
                                a4.b(false);
                                list.add(a4);
                                qVar = a4;
                            }
                            a(vVar, (com.d.i.c) null, element2, list, aVar, true);
                            if (z) {
                                if (qVar != null) {
                                    qVar.b(false);
                                }
                                z3 = true;
                            }
                        } else {
                            if (a3.Y() && vVar.r()) {
                                acVar = new com.d.i.p();
                            } else {
                                acVar = a(a3, aVar, false);
                            }
                            acVar.a(a3);
                            acVar.a(element2);
                            if (a3.Y() && vVar.r()) {
                                com.d.i.p pVar = (com.d.i.p) acVar;
                                pVar.a(new com.d.i.o(pVar));
                                pVar.f().a(a3.a(com.d.c.a.c.h));
                                pVar.f().a(element2);
                                pVar.f().l(vVar);
                            }
                            if (a3.X()) {
                                ((com.d.i.c) acVar).f(vVar.b(a3).a("list-item"));
                            }
                            if (a3.q() || a3.r()) {
                                com.d.f.d dVar = (com.d.f.d) acVar;
                                dVar.l(vVar);
                                acVar = a(vVar, dVar);
                            }
                            if (!aVar.a() && !a3.y()) {
                                aVar.a(true);
                            }
                            com.d.i.c cVar2 = (com.d.i.c) acVar;
                            if (cVar2.a().ae()) {
                                cVar2.b(vVar.c().a(element2, "first-line"));
                            }
                            if (cVar2.a().af()) {
                                cVar2.a(vVar.c().a(element2, "first-letter"));
                            }
                            cVar2.l(vVar);
                        }
                    }
                    nextSibling = firstChild.getNextSibling();
                    firstChild = nextSibling;
                }
                if (acVar != null) {
                    list.add(acVar);
                }
                nextSibling = firstChild.getNextSibling();
                firstChild = nextSibling;
            } while (nextSibling != null);
        }
        if (z2 || z3) {
            com.d.i.q a5 = a("", element, a2, (Text) null);
            a5.c(z2);
            a5.b(z3);
            list.add(a5);
        }
        a(vVar, element, a2, "after", list, aVar);
    }

    private static com.d.i.q a(com.d.i.q qVar, com.d.i.q qVar2) {
        qVar.b(true);
        if (qVar2 == null) {
            qVar.c(true);
        } else {
            qVar2.b(false);
        }
        return qVar;
    }

    private static com.d.i.q a(Text text, Element element, com.d.c.f.c cVar, com.d.i.q qVar, List<ac> list) {
        com.d.i.q a2 = a(text.getData(), element, cVar, text);
        a2.a((byte) 0);
        com.d.i.q a3 = a(a2, qVar);
        list.add(a2);
        return a3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0114, code lost:            if (r0 != r7.getLength()) goto L39;     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0117, code lost:            r0 = r0.a(r13);     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0123, code lost:            if (com.d.e.c.f1126a != false) goto L45;     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0128, code lost:            if (r0 != null) goto L45;     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0132, code lost:            throw new java.lang.AssertionError();     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0135, code lost:            if (r0 == null) goto L51;     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0138, code lost:            r0 = java.lang.Math.min(r0.b() - (r13 - r0.a()), r7.getLength() - r14);        r1 = r14;        r15 = r7.getData().substring(r1, r1 + r0);     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x016e, code lost:            if (r0.c() != 1) goto L50;     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0171, code lost:            r15 = r6.g().b(r15);     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x017e, code lost:            r13 = r13 + r0;        r14 = r14 + r0;        r0 = a(r15, r8, r9, r7);        r0.a(r0.c());        r10 = a(r0, r10);        r11.add(r0);     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0209, code lost:            if (r14 < r7.getLength()) goto L58;     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x01b5, code lost:            r0 = r7.getLength() - r14;        r0 = a(r7.getData().substring(r14, r0), r8, r9, r7);        r0.a(r6.i());        r10 = a(r0, r10);        r11.add(r0);        r13 = r13 + r0;        r14 = r14 + r0;     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x020e, code lost:            return r10;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.d.i.q a(com.d.e.v r6, org.w3c.dom.Text r7, org.w3c.dom.Element r8, com.d.c.f.c r9, com.d.i.q r10, java.util.List<com.d.e.ac> r11) {
        /*
            Method dump skipped, instructions count: 527
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.d.e.c.a(com.d.e.v, org.w3c.dom.Text, org.w3c.dom.Element, com.d.c.f.c, com.d.i.q, java.util.List):com.d.i.q");
    }

    private static void a(aa aaVar, com.d.i.f fVar, List<ac> list, boolean z) {
        ArrayList arrayList = new ArrayList();
        ArrayDeque arrayDeque = new ArrayDeque();
        ArrayList arrayList2 = null;
        for (ac acVar : list) {
            if (acVar.a().y() && (!z || !acVar.a().P())) {
                arrayList.add(acVar);
                if (acVar.a().o()) {
                    com.d.i.q qVar = (com.d.i.q) acVar;
                    if (qVar.g()) {
                        arrayDeque.add(qVar);
                    }
                    if (qVar.f()) {
                        arrayDeque.removeLast();
                    }
                }
            } else {
                if (arrayList.size() > 0) {
                    a(fVar, arrayList, arrayList2);
                    arrayList = new ArrayList();
                    arrayList2 = new ArrayList(arrayDeque);
                }
                fVar.b((com.d.i.f) acVar);
            }
        }
        a(fVar, arrayList, arrayList2);
    }

    private static void a(com.d.i.f fVar, List<ac> list, List<com.d.i.q> list2) {
        ag.a(list);
        if (list.size() > 0) {
            com.d.i.b bVar = new com.d.i.b(fVar.ai());
            bVar.a(fVar.a().a(com.d.c.a.c.h));
            bVar.i(true);
            if (list2 != null && list2.size() > 0) {
                bVar.a(list2);
            }
            fVar.b(bVar);
            bVar.g(1);
            bVar.b(list);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/e/c$a.class */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private boolean f1127a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f1128b;
        private boolean c;

        public final boolean a() {
            return this.f1127a;
        }

        public final void a(boolean z) {
            this.f1127a = true;
        }

        public final boolean b() {
            return this.f1128b;
        }

        public final void b(boolean z) {
            this.f1128b = true;
        }

        public final boolean c() {
            return this.c;
        }

        public final void c(boolean z) {
            this.c = true;
        }
    }
}
