package org.a.c.h.e;

import com.a.a.a.am;
import java.awt.geom.GeneralPath;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.a.b.f.al;
import org.a.b.f.ap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:org/a/c/h/e/aj.class */
public abstract class aj {
    private final org.a.c.h.b d;

    /* renamed from: a, reason: collision with root package name */
    protected ap f4527a;

    /* renamed from: b, reason: collision with root package name */
    protected v f4528b;
    protected final org.a.b.f.c c;
    private final Set<Integer> e = new HashSet();
    private final boolean f;

    protected abstract void a(InputStream inputStream, String str, Map<Integer, Integer> map);

    /* JADX INFO: Access modifiers changed from: package-private */
    public aj(org.a.c.h.b bVar, org.a.c.b.d dVar, ap apVar, boolean z) {
        this.d = bVar;
        this.f = z;
        this.f4527a = apVar;
        this.f4528b = c(apVar);
        if (!a(apVar)) {
            throw new IOException("This font does not permit embedding");
        }
        if (!z) {
            org.a.c.h.a.i iVar = new org.a.c.h.a.i(bVar, apVar.u(), org.a.c.b.j.bc);
            iVar.f().a(org.a.c.b.j.bY, apVar.v());
            this.f4528b.a(iVar);
        }
        dVar.a(org.a.c.b.j.v, apVar.b());
        apVar.y();
        this.c = apVar.z();
    }

    public final void a(InputStream inputStream) {
        org.a.c.h.a.i iVar = new org.a.c.h.a.i(this.d, inputStream, org.a.c.b.j.bc);
        try {
            org.a.c.b.g c = iVar.c();
            this.f4527a = new al().c(c);
            if (!a(this.f4527a)) {
                throw new IOException("This font does not permit embedding");
            }
            if (this.f4528b == null) {
                this.f4528b = c(this.f4527a);
            }
            am.a((Closeable) c);
            iVar.f().a(org.a.c.b.j.bY, this.f4527a.v());
            this.f4528b.a(iVar);
        } catch (Throwable th) {
            am.a((Closeable) null);
            throw th;
        }
    }

    private static boolean a(ap apVar) {
        if (apVar.l() != null) {
            int g = apVar.l().g() & 8;
            if ((g & 1) == 1 || (g & 512) == 512) {
                return false;
            }
            return true;
        }
        return true;
    }

    private static boolean b(ap apVar) {
        if (apVar.l() != null && (apVar.l().g() & 256) == 256) {
            return false;
        }
        return true;
    }

    private static v c(ap apVar) {
        v vVar = new v();
        vVar.a(apVar.b());
        org.a.b.f.aa l = apVar.l();
        org.a.b.f.ag k = apVar.k();
        vVar.a(k.a() > 0 || apVar.o().j() == 1);
        vVar.f((l.f() & 513) != 0);
        switch (l.e()) {
            case 1:
            case 3:
            case 4:
            case 5:
            case 7:
                vVar.b(true);
                break;
            case 10:
                vVar.d(true);
                break;
        }
        vVar.a(l.w());
        vVar.c(true);
        vVar.e(false);
        vVar.b(k.b());
        org.a.b.f.q n = apVar.n();
        org.a.c.h.a.h hVar = new org.a.c.h.a.h();
        float k2 = 1000.0f / n.k();
        hVar.a(n.n() * k2);
        hVar.b(n.p() * k2);
        hVar.c(n.m() * k2);
        hVar.d(n.o() * k2);
        vVar.a(hVar);
        org.a.b.f.r o = apVar.o();
        vVar.c(o.b() * k2);
        vVar.d(o.e() * k2);
        if (l.v() >= 1.2d) {
            vVar.e(l.B() * k2);
            vVar.f(l.A() * k2);
        } else {
            GeneralPath c = apVar.c("H");
            if (c != null) {
                vVar.e(((float) Math.round(c.getBounds2D().getMaxY())) * k2);
            } else {
                vVar.e((l.t() + l.u()) * k2);
            }
            GeneralPath c2 = apVar.c("x");
            if (c2 != null) {
                vVar.f(((float) Math.round(c2.getBounds2D().getMaxY())) * k2);
            } else {
                vVar.f((l.t() / 2.0f) * k2);
            }
        }
        vVar.g(vVar.j().h() * 0.13f);
        return vVar;
    }

    public final void a(int i) {
        this.e.add(Integer.valueOf(i));
    }

    public final void b() {
        if (!b(this.f4527a)) {
            throw new IOException("This font does not permit subsetting");
        }
        if (!this.f) {
            throw new IllegalStateException("Subsetting is disabled");
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("head");
        arrayList.add("hhea");
        arrayList.add("loca");
        arrayList.add("maxp");
        arrayList.add("cvt ");
        arrayList.add("prep");
        arrayList.add("glyf");
        arrayList.add("hmtx");
        arrayList.add("fpgm");
        arrayList.add("gasp");
        org.a.b.f.am amVar = new org.a.b.f.am(this.f4527a, arrayList);
        amVar.a(this.e);
        Map<Integer, Integer> a2 = amVar.a();
        String a3 = a(a2);
        amVar.a(a3);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        amVar.a(byteArrayOutputStream);
        a(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), a3, a2);
        this.f4527a.close();
    }

    public final boolean c() {
        return this.f;
    }

    private static String a(Map<Integer, Integer> map) {
        long hashCode = map.hashCode();
        StringBuilder sb = new StringBuilder();
        do {
            long j = hashCode / 25;
            sb.append("BCDEFGHIJKLMNOPQRSTUVWXYZ".charAt((int) (hashCode % 25)));
            hashCode = j;
            if (j == 0) {
                break;
            }
        } while (sb.length() < 6);
        while (sb.length() < 6) {
            sb.insert(0, 'A');
        }
        sb.append('+');
        return sb.toString();
    }
}
