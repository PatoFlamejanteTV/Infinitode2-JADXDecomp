package org.a.c.h.g.e;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:org/a/c/h/g/e/i.class */
public final class i {

    /* renamed from: a, reason: collision with root package name */
    private final org.a.c.h.j f4623a;

    /* renamed from: b, reason: collision with root package name */
    private org.a.c.b.j f4624b;
    private org.a.c.h.e.u c;
    private float d = 12.0f;
    private org.a.c.h.f.a.e e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(org.a.c.b.s sVar, org.a.c.h.j jVar) {
        if (sVar == null) {
            throw new IllegalArgumentException("/DA is a required entry");
        }
        if (jVar == null) {
            throw new IllegalArgumentException("/DR is a required entry");
        }
        this.f4623a = jVar;
        a(sVar.c());
    }

    private void a(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        org.a.c.f.g gVar = new org.a.c.f.g(bArr);
        Object r = gVar.r();
        while (true) {
            Object obj = r;
            if (obj != null) {
                if (obj instanceof org.a.c.b.m) {
                    arrayList.add(((org.a.c.b.m) obj).a());
                } else if (obj instanceof org.a.c.a.a.a) {
                    a((org.a.c.a.a.a) obj, arrayList);
                    arrayList = new ArrayList();
                } else {
                    arrayList.add((org.a.c.b.b) obj);
                }
                r = gVar.r();
            } else {
                return;
            }
        }
    }

    private void a(org.a.c.a.a.a aVar, List<org.a.c.b.b> list) {
        String a2 = aVar.a();
        if ("Tf".equals(a2)) {
            a(list);
            return;
        }
        if ("g".equals(a2)) {
            b(list);
        } else if ("rg".equals(a2)) {
            b(list);
        } else if ("k".equals(a2)) {
            b(list);
        }
    }

    private void a(List<org.a.c.b.b> list) {
        if (list.size() < 2) {
            throw new IOException("Missing operands for set font operator " + Arrays.toString(list.toArray()));
        }
        org.a.c.b.b bVar = list.get(0);
        org.a.c.b.b bVar2 = list.get(1);
        if (!(bVar instanceof org.a.c.b.j) || !(bVar2 instanceof org.a.c.b.l)) {
            return;
        }
        org.a.c.b.j jVar = (org.a.c.b.j) bVar;
        org.a.c.h.e.u a2 = this.f4623a.a(jVar);
        float a3 = ((org.a.c.b.l) bVar2).a();
        if (a2 == null) {
            throw new IOException("Could not find font: /" + jVar.a());
        }
        a(jVar);
        a(a2);
        a(a3);
    }

    private void b(List<org.a.c.b.b> list) {
        org.a.c.h.f.a.h hVar;
        switch (list.size()) {
            case 1:
                hVar = org.a.c.h.f.a.i.f4574a;
                break;
            case 2:
            default:
                throw new IOException("Missing operands for set non stroking color operator " + Arrays.toString(list.toArray()));
            case 3:
                hVar = org.a.c.h.f.a.m.f4580a;
                break;
            case 4:
                hVar = org.a.c.h.f.a.g.f4572a;
                break;
        }
        org.a.c.b.a aVar = new org.a.c.b.a();
        aVar.c(list);
        a(new org.a.c.h.f.a.e(aVar, hVar));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final org.a.c.b.j a() {
        return this.f4624b;
    }

    private void a(org.a.c.b.j jVar) {
        this.f4624b = jVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final org.a.c.h.e.u b() {
        return this.c;
    }

    private void a(org.a.c.h.e.u uVar) {
        this.c = uVar;
    }

    public final float c() {
        return this.d;
    }

    private void a(float f) {
        this.d = f;
    }

    private org.a.c.h.f.a.e d() {
        return this.e;
    }

    private void a(org.a.c.h.f.a.e eVar) {
        this.e = eVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(org.a.c.h.g gVar, float f) {
        float c = c();
        float f2 = c;
        if (c == 0.0f) {
            f2 = f;
        }
        gVar.a(b(), f2);
        if (d() != null) {
            gVar.b(d());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(org.a.c.h.g.b.q qVar) {
        org.a.c.h.j e = qVar.e();
        org.a.c.h.j jVar = e;
        if (e == null) {
            jVar = new org.a.c.h.j();
            qVar.a(jVar);
        }
        if (jVar.a(this.f4624b) == null) {
            jVar.a(this.f4624b, b());
        }
    }
}
