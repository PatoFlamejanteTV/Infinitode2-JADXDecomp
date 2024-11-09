package org.a.c.h.g.e;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.a.c.h.g.e.t;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:org/a/c/h/g/e/a.class */
class a {
    private final r d;
    private i e;
    private String f;

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4615a = org.a.a.a.c.a(a.class);

    /* renamed from: b, reason: collision with root package name */
    private static final org.a.c.a.a.a f4616b = org.a.c.a.a.a.a("BMC");
    private static final org.a.c.a.a.a c = org.a.c.a.a.a.a("EMC");
    private static final int[] g = {153, 193, 215};

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(r rVar) {
        this.d = rVar;
        a();
        this.e = rVar.n();
    }

    private void a() {
        if (this.d.h().e() == null) {
            return;
        }
        org.a.c.h.j e = this.d.h().e();
        for (org.a.c.h.g.b.m mVar : this.d.l()) {
            if (mVar.d() != null && mVar.d().e() != null) {
                org.a.c.h.j e2 = mVar.d().e();
                for (org.a.c.b.j jVar : e2.a()) {
                    try {
                        if (e.a(jVar) == null) {
                            new StringBuilder("Adding font resource ").append(jVar).append(" from widget to AcroForm");
                            e.a(jVar, e2.a(jVar));
                        }
                    } catch (IOException unused) {
                    }
                }
            }
        }
    }

    public final void a(String str) {
        org.a.c.h.g.b.q a2;
        this.f = str;
        if ((this.d instanceof q) && !((q) this.d).a()) {
            this.f = str.replaceAll("\\u000D\\u000A|[\\u000A\\u000B\\u000C\\u000D\\u0085\\u2028\\u2029]", SequenceUtils.SPACE);
        }
        for (org.a.c.h.g.b.m mVar : this.d.l()) {
            i iVar = this.e;
            if (mVar.f().a(org.a.c.b.j.an) != null) {
                this.e = b(mVar);
            }
            if (mVar.a() == null) {
                mVar.f().m(org.a.c.b.j.j);
                new StringBuilder("widget of field ").append(this.d.j()).append(" has no rectangle, no appearance stream created");
            } else {
                org.a.c.h.g.a.p g2 = this.d.g();
                if (g2 == null || g2.a() == null || mVar.f().a(org.a.c.b.j.j) != null) {
                    org.a.c.h.g.b.o c2 = mVar.c();
                    org.a.c.h.g.b.o oVar = c2;
                    if (c2 == null) {
                        oVar = new org.a.c.h.g.b.o();
                        mVar.a(oVar);
                    }
                    org.a.c.h.g.b.p b2 = oVar.b();
                    if (b2 != null && b2.b()) {
                        a2 = b2.c();
                    } else {
                        a2 = a(mVar);
                        oVar.a(a2);
                    }
                    if (mVar.h() != null || a2.d().d() == 0) {
                        a(mVar, a2);
                    }
                    b(mVar, a2);
                }
                this.e = iVar;
            }
        }
    }

    private org.a.c.h.g.b.q a(org.a.c.h.g.b.m mVar) {
        org.a.c.h.g.b.q qVar = new org.a.c.h.g.b.q(this.d.h().a());
        int c2 = c(mVar);
        org.a.c.h.a.h a2 = mVar.a();
        Point2D.Float a3 = org.a.c.i.d.a(Math.toRadians(c2), 0.0f, 0.0f).a(a2.h(), a2.i());
        org.a.c.h.a.h hVar = new org.a.c.h.a.h(Math.abs((float) a3.getX()), Math.abs((float) a3.getY()));
        qVar.a(hVar);
        AffineTransform a4 = a(hVar, c2);
        if (!a4.isIdentity()) {
            qVar.a(a4);
        }
        qVar.a(1);
        qVar.a(new org.a.c.h.j());
        return qVar;
    }

    private i b(org.a.c.h.g.b.m mVar) {
        return new i((org.a.c.b.s) mVar.f().a(org.a.c.b.j.an), this.d.h().e());
    }

    private static int c(org.a.c.h.g.b.m mVar) {
        org.a.c.h.g.b.n h = mVar.h();
        if (h != null) {
            return h.a();
        }
        return 0;
    }

    private void a(org.a.c.h.g.b.m mVar, org.a.c.h.g.b.q qVar) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        org.a.c.h.g gVar = new org.a.c.h.g(this.d.h().a(), qVar, byteArrayOutputStream);
        org.a.c.h.g.b.n h = mVar.h();
        if (h != null) {
            org.a.c.h.f.a.e c2 = h.c();
            if (c2 != null) {
                gVar.b(c2);
                org.a.c.h.a.h c3 = c(mVar, qVar);
                gVar.c(c3.c(), c3.d(), c3.h(), c3.i());
                gVar.g();
            }
            float f = 0.0f;
            org.a.c.h.f.a.e b2 = h.b();
            if (b2 != null) {
                gVar.a(b2);
                f = 1.0f;
            }
            org.a.c.h.g.b.r i = mVar.i();
            if (i != null && i.a() > 0.0f) {
                f = i.a();
            }
            if (f > 0.0f && b2 != null) {
                if (f != 1.0f) {
                    gVar.a(f);
                }
                org.a.c.h.a.h a2 = a(c(mVar, qVar), Math.max(0.5f, f / 2.0f));
                gVar.c(a2.c(), a2.d(), a2.h(), a2.i());
                gVar.f();
            }
        }
        gVar.close();
        byteArrayOutputStream.close();
        a(byteArrayOutputStream.toByteArray(), qVar);
    }

    private static List<Object> a(org.a.c.h.g.b.q qVar) {
        org.a.c.f.g gVar = new org.a.c.f.g(qVar);
        gVar.p();
        return gVar.q();
    }

    private void b(org.a.c.h.g.b.m mVar, org.a.c.h.g.b.q qVar) {
        this.e.a(qVar);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        org.a.c.g.d dVar = new org.a.c.g.d(byteArrayOutputStream);
        List<?> a2 = a(qVar);
        int indexOf = a2.indexOf(f4616b);
        if (indexOf == -1) {
            dVar.a(a2);
            dVar.a(org.a.c.b.j.dM, f4616b);
        } else {
            dVar.a(a2.subList(0, indexOf + 1));
        }
        a(mVar, qVar, byteArrayOutputStream);
        int indexOf2 = a2.indexOf(c);
        if (indexOf2 == -1) {
            dVar.a(c);
        } else {
            dVar.a(a2.subList(indexOf2, a2.size()));
        }
        byteArrayOutputStream.close();
        a(byteArrayOutputStream.toByteArray(), qVar);
    }

    private void a(org.a.c.h.g.b.m mVar, org.a.c.h.g.b.q qVar, OutputStream outputStream) {
        float f;
        org.a.c.h.g gVar = new org.a.c.h.g(this.d.h().a(), qVar, outputStream);
        org.a.c.h.a.h c2 = c(mVar, qVar);
        float f2 = 0.0f;
        if (mVar.i() != null) {
            f2 = mVar.i().a();
        }
        org.a.c.h.a.h a2 = a(c2, Math.max(1.0f, f2));
        org.a.c.h.a.h a3 = a(a2, Math.max(1.0f, f2));
        gVar.c();
        gVar.c(a2.c(), a2.d(), a2.h(), a2.i());
        gVar.j();
        org.a.c.h.e.u b2 = this.e.b();
        if (b2 == null) {
            throw new IllegalArgumentException("font is null, check whether /DA entry is incomplete or incorrect");
        }
        if (b2.d().contains("+")) {
            new StringBuilder("Font '").append(this.e.a().a()).append("' of field '").append(this.d.j()).append("' contains subsetted font '").append(b2.d()).append("'");
            new StringBuilder("acroForm.getDefaultResources().put(COSName.getPDFName(\"").append(this.e.a().a()).append("\", font);");
        }
        float c3 = this.e.c();
        float f3 = c3;
        if (c3 == 0.0f) {
            f3 = a(b2, a3);
        }
        if (this.d instanceof k) {
            b(gVar, qVar, b2, f3);
        }
        gVar.a();
        this.e.a(gVar, f3);
        float f4 = f3 / 1000.0f;
        float e = b2.e().e() * f4;
        float m = b2.b().m() * f4;
        float l = b2.b().l() * f4;
        if ((this.d instanceof q) && ((q) this.d).a()) {
            f = a3.g() - e;
        } else if (m <= a2.i()) {
            float d = a2.d() + ((a2.i() - m) / 2.0f);
            f = d;
            if (d - a2.d() < (-l)) {
                f = Math.min((-l) + a3.d(), Math.max(f, (a3.i() - a3.d()) - m));
            }
        } else {
            f = a2.d() - l;
        }
        float c4 = a3.c();
        if (c()) {
            a(gVar, qVar, b2, f3);
        } else if (this.d instanceof k) {
            a(gVar, qVar, a3, b2, f3);
        } else {
            s sVar = new s(this.f);
            b bVar = new b();
            bVar.a(b2);
            bVar.a(f3);
            bVar.b(b2.e().e() * f4);
            new t.a(gVar).a(bVar).a(sVar).a(a3.h()).a(b()).a(c4, f).a(this.d.o()).a().a();
        }
        gVar.b();
        gVar.d();
        gVar.close();
    }

    private static AffineTransform a(org.a.c.h.a.h hVar, int i) {
        if (i == 0) {
            return new AffineTransform();
        }
        float f = 0.0f;
        float f2 = 0.0f;
        switch (i) {
            case 90:
                f = hVar.g();
                break;
            case 180:
                f = hVar.g();
            case User32.WM_IME_ENDCOMPOSITION /* 270 */:
                f2 = hVar.e();
                break;
        }
        return org.a.c.i.d.a(Math.toRadians(i), f, f2).a();
    }

    private boolean b() {
        return (this.d instanceof q) && ((q) this.d).a();
    }

    private boolean c() {
        return (!(this.d instanceof q) || !((q) this.d).e() || ((q) this.d).a() || ((q) this.d).b() || ((q) this.d).d()) ? false : true;
    }

    private void a(org.a.c.h.g gVar, org.a.c.h.g.b.q qVar, org.a.c.h.e.u uVar, float f) {
        int k = ((q) this.d).k();
        int min = Math.min(this.f.length(), k);
        org.a.c.h.a.h a2 = a(qVar.g(), 1.0f);
        float h = qVar.g().h() / k;
        float d = a2.d() + ((qVar.g().i() - ((uVar.b().k() / 1000.0f) * f)) / 2.0f);
        float f2 = 0.0f;
        float f3 = h / 2.0f;
        for (int i = 0; i < min; i++) {
            int i2 = i;
            String substring = this.f.substring(i2, i2 + 1);
            float b2 = ((uVar.b(substring) / 1000.0f) * f) / 2.0f;
            gVar.a((f3 + (f2 / 2.0f)) - (b2 / 2.0f), d);
            gVar.a(substring);
            d = 0.0f;
            f2 = b2;
            f3 = h;
        }
    }

    private void b(org.a.c.h.g gVar, org.a.c.h.g.b.q qVar, org.a.c.h.e.u uVar, float f) {
        List<Integer> d = ((k) this.d).d();
        List<String> e = ((k) this.d).e();
        List<String> b2 = ((k) this.d).b();
        if (!e.isEmpty() && !b2.isEmpty() && d.isEmpty()) {
            d = new ArrayList();
            Iterator<String> it = e.iterator();
            while (it.hasNext()) {
                d.add(Integer.valueOf(b2.indexOf(it.next())));
            }
        }
        int k = ((k) this.d).k();
        float e2 = (uVar.e().e() * f) / 1000.0f;
        org.a.c.h.a.h a2 = a(qVar.g(), 1.0f);
        Iterator<Integer> it2 = d.iterator();
        while (it2.hasNext()) {
            int intValue = it2.next().intValue();
            gVar.b(g[0], g[1], g[2]);
            gVar.c(a2.c(), (a2.g() - (e2 * ((intValue - k) + 1))) + 2.0f, a2.h(), e2);
            gVar.g();
        }
        gVar.a(0);
    }

    private void a(org.a.c.h.g gVar, org.a.c.h.g.b.q qVar, org.a.c.h.a.h hVar, org.a.c.h.e.u uVar, float f) {
        gVar.a(0);
        int o = this.d.o();
        if (o == 1 || o == 2) {
            float h = (qVar.g().h() - ((uVar.b(this.f) / 1000.0f) * f)) - 4.0f;
            if (o == 1) {
                h /= 2.0f;
            }
            gVar.a(h, 0.0f);
        } else if (o != 0) {
            throw new IOException("Error: Unknown justification value:" + o);
        }
        List<String> a2 = ((k) this.d).a();
        int size = a2.size();
        float g2 = hVar.g();
        int k = ((k) this.d).k();
        for (int i = k; i < size; i++) {
            if (i == k) {
                g2 -= (uVar.b().k() / 1000.0f) * f;
            } else {
                g2 -= (uVar.e().e() / 1000.0f) * f;
                gVar.a();
            }
            gVar.a(hVar.c(), g2);
            gVar.a(a2.get(i));
            if (i != size - 1) {
                gVar.b();
            }
        }
    }

    private static void a(byte[] bArr, org.a.c.h.g.b.q qVar) {
        OutputStream l = qVar.f().l();
        l.write(bArr);
        l.close();
    }

    private float a(org.a.c.h.e.u uVar, org.a.c.h.a.h hVar) {
        float c2 = this.e.c();
        if (c2 == 0.0f) {
            if (b()) {
                return 12.0f;
            }
            float c3 = 1000.0f * uVar.h().c();
            float h = (hVar.h() / (uVar.b(this.f) * uVar.h().b())) * 1000.0f * uVar.h().b();
            float m = (uVar.b().m() - uVar.b().l()) * uVar.h().c();
            float f = m;
            if (m <= 0.0f) {
                f = uVar.e().e() * uVar.h().c();
            }
            return Math.min((hVar.i() / f) * c3, h);
        }
        return c2;
    }

    private static org.a.c.h.a.h c(org.a.c.h.g.b.m mVar, org.a.c.h.g.b.q qVar) {
        org.a.c.h.a.h g2 = qVar.g();
        org.a.c.h.a.h hVar = g2;
        if (g2 == null) {
            hVar = mVar.a().a();
        }
        return hVar;
    }

    private static org.a.c.h.a.h a(org.a.c.h.a.h hVar, float f) {
        return new org.a.c.h.a.h(hVar.c() + f, hVar.d() + f, hVar.h() - (f * 2.0f), hVar.i() - (f * 2.0f));
    }
}
