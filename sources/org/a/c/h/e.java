package org.a.c.h;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.a.c.b.l;
import org.a.c.b.p;

/* loaded from: infinitode-2.jar:org/a/c/h/e.class */
public class e implements org.a.c.a.a, org.a.c.h.a.c {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4515a = org.a.a.a.c.a(e.class);

    /* renamed from: b, reason: collision with root package name */
    private final org.a.c.b.d f4516b;
    private j c;
    private k d;
    private org.a.c.h.a.h e;

    public e() {
        this(org.a.c.h.a.h.f4488a);
    }

    public e(org.a.c.h.a.h hVar) {
        this.f4516b = new org.a.c.b.d();
        this.f4516b.a(org.a.c.b.j.dN, (org.a.c.b.b) org.a.c.b.j.cK);
        this.f4516b.a(org.a.c.b.j.ci, hVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(org.a.c.b.d dVar, k kVar) {
        this.f4516b = dVar;
        this.d = kVar;
    }

    @Override // org.a.c.h.a.c
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public final org.a.c.b.d f() {
        return this.f4516b;
    }

    @Override // org.a.c.a.a
    public final InputStream a() {
        org.a.c.b.b a2 = this.f4516b.a(org.a.c.b.j.af);
        if (a2 instanceof p) {
            return ((p) a2).k();
        }
        if ((a2 instanceof org.a.c.b.a) && ((org.a.c.b.a) a2).b() > 0) {
            org.a.c.b.a aVar = (org.a.c.b.a) a2;
            byte[] bArr = {10};
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < aVar.b(); i++) {
                org.a.c.b.b a3 = aVar.a(i);
                if (a3 instanceof p) {
                    arrayList.add(((p) a3).k());
                    arrayList.add(new ByteArrayInputStream(bArr));
                }
            }
            return new SequenceInputStream(Collections.enumeration(arrayList));
        }
        return new ByteArrayInputStream(new byte[0]);
    }

    public final boolean c() {
        org.a.c.b.b a2 = this.f4516b.a(org.a.c.b.j.af);
        return a2 instanceof p ? ((p) a2).a() > 0 : (a2 instanceof org.a.c.b.a) && ((org.a.c.b.a) a2).b() > 0;
    }

    public final j d() {
        if (this.c == null) {
            org.a.c.b.b a2 = h.a(this.f4516b, org.a.c.b.j.dg);
            if (a2 instanceof org.a.c.b.d) {
                this.c = new j((org.a.c.b.d) a2, this.d);
            }
        }
        return this.c;
    }

    public final void a(j jVar) {
        this.c = jVar;
        if (jVar != null) {
            this.f4516b.a(org.a.c.b.j.dg, jVar);
        } else {
            this.f4516b.m(org.a.c.b.j.dg);
        }
    }

    public final org.a.c.h.a.h e() {
        if (this.e == null) {
            org.a.c.b.b a2 = h.a(this.f4516b, org.a.c.b.j.ci);
            if (a2 instanceof org.a.c.b.a) {
                this.e = new org.a.c.h.a.h((org.a.c.b.a) a2);
            }
        }
        if (this.e == null) {
            this.e = org.a.c.h.a.h.f4488a;
        }
        return this.e;
    }

    public final org.a.c.h.a.h g() {
        org.a.c.b.b a2 = h.a(this.f4516b, org.a.c.b.j.aj);
        if (a2 instanceof org.a.c.b.a) {
            return a(new org.a.c.h.a.h((org.a.c.b.a) a2));
        }
        return e();
    }

    private org.a.c.h.a.h a(org.a.c.h.a.h hVar) {
        org.a.c.h.a.h e = e();
        org.a.c.h.a.h hVar2 = new org.a.c.h.a.h();
        hVar2.a(Math.max(e.c(), hVar.c()));
        hVar2.b(Math.max(e.d(), hVar.d()));
        hVar2.c(Math.min(e.e(), hVar.e()));
        hVar2.d(Math.min(e.g(), hVar.g()));
        return hVar2;
    }

    public final int h() {
        org.a.c.b.b a2 = h.a(this.f4516b, org.a.c.b.j.dj);
        if (a2 instanceof l) {
            int c = ((l) a2).c();
            if (c % 90 == 0) {
                return ((c % 360) + 360) % 360;
            }
            return 0;
        }
        return 0;
    }

    public final void a(org.a.c.h.a.i iVar) {
        this.f4516b.a(org.a.c.b.j.af, iVar);
    }

    public final List<org.a.c.h.g.b.b> i() {
        return a(new f(this));
    }

    private List<org.a.c.h.g.b.b> a(org.a.c.h.g.b.a aVar) {
        org.a.c.b.b a2 = this.f4516b.a(org.a.c.b.j.i);
        if (a2 instanceof org.a.c.b.a) {
            org.a.c.b.a aVar2 = (org.a.c.b.a) a2;
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < aVar2.b(); i++) {
                org.a.c.b.b a3 = aVar2.a(i);
                if (a3 != null) {
                    arrayList.add(org.a.c.h.g.b.b.a(a3));
                }
            }
            return new org.a.c.h.a.a(arrayList, aVar2);
        }
        return new org.a.c.h.a.a(this.f4516b, org.a.c.b.j.i);
    }

    public final void a(List<org.a.c.h.g.b.b> list) {
        this.f4516b.a(org.a.c.b.j.i, (org.a.c.b.b) org.a.c.h.a.a.b(list));
    }

    public boolean equals(Object obj) {
        return (obj instanceof e) && ((e) obj).f() == f();
    }

    public int hashCode() {
        return this.f4516b.hashCode();
    }
}
