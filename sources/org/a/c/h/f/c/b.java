package org.a.c.h.f.c;

import com.a.a.a.am;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import org.a.a.a.c;
import org.a.c.b.g;
import org.a.c.b.m;
import org.a.c.b.p;
import org.a.c.c.k;
import org.a.c.h.a.d;
import org.a.c.h.a.i;
import org.a.c.h.f.a.f;
import org.a.c.h.j;

/* loaded from: infinitode-2.jar:org/a/c/h/f/c/b.class */
public final class b extends org.a.c.h.f.a implements d {

    /* renamed from: a, reason: collision with root package name */
    private f f4597a;

    /* renamed from: b, reason: collision with root package name */
    private final j f4598b;

    static {
        c.a(b.class);
    }

    public b(org.a.c.h.b bVar, InputStream inputStream, org.a.c.b.b bVar2, int i, int i2, int i3, f fVar) {
        super(a(bVar, inputStream), org.a.c.b.j.bE);
        f().a(org.a.c.b.j.aY, bVar2);
        this.f4598b = null;
        this.f4597a = null;
        a(i3);
        c(i);
        b(i2);
        a(fVar);
    }

    private b(i iVar, j jVar) {
        super(iVar, org.a.c.b.j.bE);
        this.f4598b = jVar;
        List<org.a.c.b.j> e = iVar.e();
        if (e != null && !e.isEmpty() && org.a.c.b.j.bN.equals(e.get(e.size() - 1))) {
            g gVar = null;
            try {
                g c = iVar.c();
                gVar = c;
                k a2 = c.a();
                iVar.f().a(a2.a());
                this.f4597a = a2.b();
                am.a((Closeable) gVar);
            } catch (Throwable th) {
                am.a((Closeable) gVar);
                throw th;
            }
        }
    }

    private static p a(org.a.c.h.b bVar, InputStream inputStream) {
        p a2 = bVar.a().a();
        OutputStream outputStream = null;
        try {
            outputStream = a2.m();
            am.a(inputStream, outputStream);
            outputStream.close();
            return a2;
        } catch (Throwable th) {
            if (outputStream != null) {
                outputStream.close();
            }
            throw th;
        }
    }

    public final b a() {
        p e = f().e(org.a.c.b.j.ds);
        if (e != null) {
            return new b(new i(e), null);
        }
        return null;
    }

    public final int d() {
        if (i()) {
            return 1;
        }
        return f().c(org.a.c.b.j.z, org.a.c.b.j.D);
    }

    private void a(int i) {
        f().a(org.a.c.b.j.z, i);
    }

    public final f e() {
        if (this.f4597a == null) {
            org.a.c.b.b d = f().d(org.a.c.b.j.ac, org.a.c.b.j.al);
            if (d != null) {
                m mVar = null;
                if ((d instanceof m) && this.f4598b != null && this.f4598b.b() != null) {
                    mVar = (m) d;
                    this.f4597a = this.f4598b.b().b(mVar);
                    if (this.f4597a != null) {
                        return this.f4597a;
                    }
                }
                this.f4597a = f.a(d, this.f4598b);
                if (mVar != null) {
                    this.f4598b.b().a(mVar, this.f4597a);
                }
            } else {
                if (i()) {
                    return org.a.c.h.f.a.i.f4574a;
                }
                throw new IOException("could not determine color space");
            }
        }
        return this.f4597a;
    }

    private void a(f fVar) {
        f().a(org.a.c.b.j.ac, fVar != null ? fVar.f() : null);
    }

    public final int g() {
        return f().j(org.a.c.b.j.bx);
    }

    private void b(int i) {
        f().a(org.a.c.b.j.bx, i);
    }

    public final int h() {
        return f().j(org.a.c.b.j.ea);
    }

    private void c(int i) {
        f().a(org.a.c.b.j.ea, i);
    }

    public final void a(boolean z) {
        f().a(org.a.c.b.j.bJ, z);
    }

    public final void a(org.a.c.b.a aVar) {
        f().a(org.a.c.b.j.aq, (org.a.c.b.b) aVar);
    }

    private boolean i() {
        return f().b(org.a.c.b.j.bF, false);
    }
}
