package org.a.c.h;

import com.a.a.a.am;
import java.awt.Point;
import java.awt.image.Raster;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.a.b.f.ap;
import org.a.c.b.l;
import org.a.c.h.e.u;
import org.a.c.h.f.a.m;

/* loaded from: infinitode-2.jar:org/a/c/h/b.class */
public class b implements Closeable {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4491a = org.a.a.a.c.a(b.class);

    /* renamed from: b, reason: collision with root package name */
    private final org.a.c.b.e f4492b;
    private d c;
    private c d;
    private org.a.c.h.c.d e;
    private boolean f;
    private Long g;
    private final org.a.c.d.e h;
    private final Set<u> i;
    private final Set<ap> j;
    private org.a.c.h.g.c.a k;
    private k l;

    static {
        try {
            m.f4580a.a(Raster.createBandedRaster(0, 1, 1, 3, new Point(0, 0)));
        } catch (IOException unused) {
        }
        try {
            l.a("0");
            l.a("1");
        } catch (IOException unused2) {
        }
    }

    public b() {
        this(org.a.c.d.a.a());
    }

    private b(org.a.c.d.a aVar) {
        this.i = new HashSet();
        this.j = new HashSet();
        this.l = new k() { // from class: org.a.c.h.i

            /* renamed from: a, reason: collision with root package name */
            private final Map<org.a.c.b.m, SoftReference<u>> f4647a = new HashMap();

            /* renamed from: b, reason: collision with root package name */
            private final Map<org.a.c.b.m, SoftReference<org.a.c.h.f.a.f>> f4648b = new HashMap();

            {
                new HashMap();
                new HashMap();
                new HashMap();
                new HashMap();
                new HashMap();
            }

            @Override // org.a.c.h.k
            public u a(org.a.c.b.m mVar) {
                SoftReference<u> softReference = this.f4647a.get(mVar);
                if (softReference != null) {
                    return softReference.get();
                }
                return null;
            }

            @Override // org.a.c.h.k
            public void a(org.a.c.b.m mVar, u uVar) {
                this.f4647a.put(mVar, new SoftReference<>(uVar));
            }

            @Override // org.a.c.h.k
            public org.a.c.h.f.a.f b(org.a.c.b.m mVar) {
                SoftReference<org.a.c.h.f.a.f> softReference = this.f4648b.get(mVar);
                if (softReference != null) {
                    return softReference.get();
                }
                return null;
            }

            @Override // org.a.c.h.k
            public void a(org.a.c.b.m mVar, org.a.c.h.f.a.f fVar) {
                this.f4648b.put(mVar, new SoftReference<>(fVar));
            }
        };
        org.a.c.d.g gVar = null;
        try {
            gVar = new org.a.c.d.g(aVar);
        } catch (IOException e) {
            new StringBuilder("Error initializing scratch file: ").append(e.getMessage()).append(". Fall back to main memory usage only.");
            try {
                gVar = new org.a.c.d.g(org.a.c.d.a.a());
            } catch (IOException unused) {
            }
        }
        this.f4492b = new org.a.c.b.e(gVar);
        this.h = null;
        org.a.c.b.d dVar = new org.a.c.b.d();
        this.f4492b.c(dVar);
        org.a.c.b.d dVar2 = new org.a.c.b.d();
        dVar.a(org.a.c.b.j.di, (org.a.c.b.b) dVar2);
        dVar2.a(org.a.c.b.j.dN, (org.a.c.b.b) org.a.c.b.j.N);
        dVar2.a(org.a.c.b.j.dV, (org.a.c.b.b) org.a.c.b.j.a("1.4"));
        org.a.c.b.d dVar3 = new org.a.c.b.d();
        dVar2.a(org.a.c.b.j.cL, (org.a.c.b.b) dVar3);
        dVar3.a(org.a.c.b.j.dN, (org.a.c.b.b) org.a.c.b.j.cL);
        dVar3.a(org.a.c.b.j.bR, (org.a.c.b.b) new org.a.c.b.a());
        dVar3.a(org.a.c.b.j.ag, (org.a.c.b.b) org.a.c.b.i.f4366a);
    }

    public b(org.a.c.b.e eVar, org.a.c.d.e eVar2, org.a.c.h.c.j jVar) {
        this.i = new HashSet();
        this.j = new HashSet();
        this.l = new k() { // from class: org.a.c.h.i

            /* renamed from: a, reason: collision with root package name */
            private final Map<org.a.c.b.m, SoftReference<u>> f4647a = new HashMap();

            /* renamed from: b, reason: collision with root package name */
            private final Map<org.a.c.b.m, SoftReference<org.a.c.h.f.a.f>> f4648b = new HashMap();

            {
                new HashMap();
                new HashMap();
                new HashMap();
                new HashMap();
                new HashMap();
            }

            @Override // org.a.c.h.k
            public u a(org.a.c.b.m mVar) {
                SoftReference<u> softReference = this.f4647a.get(mVar);
                if (softReference != null) {
                    return softReference.get();
                }
                return null;
            }

            @Override // org.a.c.h.k
            public void a(org.a.c.b.m mVar, u uVar) {
                this.f4647a.put(mVar, new SoftReference<>(uVar));
            }

            @Override // org.a.c.h.k
            public org.a.c.h.f.a.f b(org.a.c.b.m mVar) {
                SoftReference<org.a.c.h.f.a.f> softReference = this.f4648b.get(mVar);
                if (softReference != null) {
                    return softReference.get();
                }
                return null;
            }

            @Override // org.a.c.h.k
            public void a(org.a.c.b.m mVar, org.a.c.h.f.a.f fVar) {
                this.f4648b.put(mVar, new SoftReference<>(fVar));
            }
        };
        this.f4492b = eVar;
        this.h = eVar2;
    }

    public final void a(e eVar) {
        l().a(eVar);
    }

    public final org.a.c.b.e a() {
        return this.f4492b;
    }

    public final d b() {
        if (this.c == null) {
            org.a.c.b.d h = this.f4492b.h();
            org.a.c.b.d d = h.d(org.a.c.b.j.bI);
            org.a.c.b.d dVar = d;
            if (d == null) {
                dVar = new org.a.c.b.d();
                h.a(org.a.c.b.j.bI, (org.a.c.b.b) dVar);
            }
            this.c = new d(dVar);
        }
        return this.c;
    }

    public final void a(d dVar) {
        this.c = dVar;
        this.f4492b.h().a(org.a.c.b.j.bI, (org.a.c.b.b) dVar.f());
    }

    public final c c() {
        if (this.d == null) {
            org.a.c.b.b a2 = this.f4492b.h().a(org.a.c.b.j.di);
            if (a2 instanceof org.a.c.b.d) {
                this.d = new c(this, (org.a.c.b.d) a2);
            } else {
                this.d = new c(this);
            }
        }
        return this.d;
    }

    private boolean k() {
        return this.f4492b.c();
    }

    public final org.a.c.h.c.d d() {
        if (this.e == null && k()) {
            this.e = new org.a.c.h.c.d(this.f4492b.d());
        }
        return this.e;
    }

    public final void a(org.a.c.h.c.d dVar) {
        this.e = dVar;
    }

    public final void a(ap apVar) {
        this.j.add(apVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Set<u> e() {
        return this.i;
    }

    public static b a(byte[] bArr) {
        return a(bArr, "");
    }

    private static b a(byte[] bArr, String str) {
        return a(bArr, str, null, null);
    }

    private static b a(byte[] bArr, String str, InputStream inputStream, String str2) {
        return a(bArr, str, inputStream, str2, org.a.c.d.a.a());
    }

    private static b a(byte[] bArr, String str, InputStream inputStream, String str2, org.a.c.d.a aVar) {
        org.a.c.f.f fVar = new org.a.c.f.f(new org.a.c.d.b(bArr), str, inputStream, str2, new org.a.c.d.g(aVar));
        fVar.x();
        return fVar.w();
    }

    public final void a(OutputStream outputStream) {
        if (this.f4492b.j()) {
            throw new IOException("Cannot save a document which has been closed");
        }
        Iterator<u> it = this.i.iterator();
        while (it.hasNext()) {
            it.next().j();
        }
        this.i.clear();
        org.a.c.g.b bVar = new org.a.c.g.b(outputStream);
        try {
            bVar.a(this);
        } finally {
            bVar.close();
        }
    }

    public final e a(int i) {
        return c().a().a(i);
    }

    private h l() {
        return c().a();
    }

    public final int f() {
        return c().a().a();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (!this.f4492b.j()) {
            IOException iOException = null;
            if (this.k != null) {
                iOException = am.a(this.k, f4491a, "SigningSupport", (IOException) null);
            }
            IOException a2 = am.a(this.f4492b, f4491a, "COSDocument", iOException);
            if (this.h != null) {
                a2 = am.a(this.h, f4491a, "RandomAccessRead pdfSource", a2);
            }
            Iterator<ap> it = this.j.iterator();
            while (it.hasNext()) {
                a2 = am.a(it.next(), f4491a, "TrueTypeFont", a2);
            }
            if (a2 != null) {
                throw a2;
            }
        }
    }

    public final void a(org.a.c.h.c.e eVar) {
        if (g()) {
            a(false);
        }
        if (!k()) {
            this.e = new org.a.c.h.c.d();
        }
        org.a.c.h.c.k a2 = org.a.c.h.c.l.f4508a.a(eVar);
        if (a2 != null) {
            d().a(a2);
            return;
        }
        throw new IOException("No security handler for policy " + eVar);
    }

    public final boolean g() {
        return this.f;
    }

    private void a(boolean z) {
        this.f = false;
    }

    public final Long h() {
        return this.g;
    }

    public final float i() {
        float b2 = a().b();
        if (b2 >= 1.4f) {
            String d = c().d();
            float f = -1.0f;
            if (d != null) {
                try {
                    f = Float.parseFloat(d);
                } catch (NumberFormatException unused) {
                }
            }
            return Math.max(f, b2);
        }
        return b2;
    }

    public final void a(float f) {
        float i = i();
        if (f != i && f >= i) {
            if (a().b() >= 1.4f) {
                c().b(Float.toString(f));
            } else {
                a().a(f);
            }
        }
    }

    public final k j() {
        return this.l;
    }
}
