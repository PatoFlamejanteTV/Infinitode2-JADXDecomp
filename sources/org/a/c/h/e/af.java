package org.a.c.h.e;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:org/a/c/h/e/af.class */
public final class af implements org.a.c.a.a, org.a.c.h.a.c {

    /* renamed from: a, reason: collision with root package name */
    private final org.a.c.b.p f4522a;

    public af(ag agVar, org.a.c.b.p pVar) {
        this.f4522a = pVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // org.a.c.h.a.c
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public org.a.c.b.p f() {
        return this.f4522a;
    }

    public final org.a.c.h.a.i b() {
        return new org.a.c.h.a.i(this.f4522a);
    }

    @Override // org.a.c.a.a
    public final InputStream a() {
        return this.f4522a.k();
    }

    public final org.a.c.h.a.h c() {
        ArrayList arrayList = new ArrayList();
        org.a.c.f.g gVar = new org.a.c.f.g(this);
        Object r = gVar.r();
        while (true) {
            Object obj = r;
            if (obj != null) {
                if (obj instanceof org.a.c.b.m) {
                    arrayList.add(((org.a.c.b.m) obj).a());
                } else {
                    if (obj instanceof org.a.c.a.a.a) {
                        if (((org.a.c.a.a.a) obj).a().equals("d1") && arrayList.size() == 6) {
                            for (int i = 0; i < 6; i++) {
                                if (!(arrayList.get(i) instanceof org.a.c.b.l)) {
                                    return null;
                                }
                            }
                            return new org.a.c.h.a.h(((org.a.c.b.l) arrayList.get(2)).a(), ((org.a.c.b.l) arrayList.get(3)).a(), ((org.a.c.b.l) arrayList.get(4)).a() - ((org.a.c.b.l) arrayList.get(2)).a(), ((org.a.c.b.l) arrayList.get(5)).a() - ((org.a.c.b.l) arrayList.get(3)).a());
                        }
                        return null;
                    }
                    arrayList.add((org.a.c.b.b) obj);
                }
                r = gVar.r();
            } else {
                return null;
            }
        }
    }

    public final float d() {
        ArrayList arrayList = new ArrayList();
        org.a.c.f.g gVar = new org.a.c.f.g(this);
        Object r = gVar.r();
        while (true) {
            Object obj = r;
            if (obj != null) {
                if (obj instanceof org.a.c.b.m) {
                    arrayList.add(((org.a.c.b.m) obj).a());
                } else {
                    if (obj instanceof org.a.c.a.a.a) {
                        return a((org.a.c.a.a.a) obj, arrayList);
                    }
                    arrayList.add((org.a.c.b.b) obj);
                }
                r = gVar.r();
            } else {
                throw new IOException("Unexpected end of stream");
            }
        }
    }

    private static float a(org.a.c.a.a.a aVar, List<org.a.c.b.b> list) {
        if (aVar.a().equals("d0") || aVar.a().equals("d1")) {
            org.a.c.b.b bVar = list.get(0);
            if (bVar instanceof org.a.c.b.l) {
                return ((org.a.c.b.l) bVar).a();
            }
            throw new IOException("Unexpected argument type: " + bVar.getClass().getName());
        }
        throw new IOException("First operator must be d0 or d1");
    }
}
