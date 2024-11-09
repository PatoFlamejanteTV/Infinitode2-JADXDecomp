package org.a.c.b;

import com.a.a.a.am;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:org/a/c/b/p.class */
public class p extends d implements Closeable {

    /* renamed from: b, reason: collision with root package name */
    private org.a.c.d.e f4378b;
    private final org.a.c.d.g c;
    private boolean d;
    private static final org.a.a.a.a e = org.a.a.a.c.a(p.class);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean a(p pVar, boolean z) {
        pVar.d = false;
        return false;
    }

    public p() {
        this(org.a.c.d.g.a());
    }

    public p(org.a.c.d.g gVar) {
        a(j.bX, 0);
        this.c = gVar != null ? gVar : org.a.c.d.g.a();
    }

    private void q() {
        if (this.f4378b != null && this.f4378b.d()) {
            throw new IOException("COSStream has been closed and cannot be read. Perhaps its enclosing PDDocument has been closed?");
        }
    }

    private void b(boolean z) {
        if (this.f4378b == null) {
            this.f4378b = this.c.d();
        }
    }

    public final InputStream j() {
        q();
        if (this.d) {
            throw new IllegalStateException("Cannot read while there is an open stream writer");
        }
        b(true);
        return new org.a.c.d.c(this.f4378b);
    }

    public final g k() {
        return a(org.a.c.c.j.f4400a);
    }

    private g a(org.a.c.c.j jVar) {
        q();
        if (this.d) {
            throw new IllegalStateException("Cannot read while there is an open stream writer");
        }
        b(true);
        return g.a(r(), this, new org.a.c.d.c(this.f4378b), this.c, jVar);
    }

    public final OutputStream l() {
        return a((b) null);
    }

    public final OutputStream a(b bVar) {
        q();
        if (this.d) {
            throw new IllegalStateException("Cannot have more than one open stream writer.");
        }
        if (bVar != null) {
            a(j.aY, bVar);
        }
        am.a(this.f4378b);
        this.f4378b = this.c.d();
        o oVar = new o(r(), this, new org.a.c.d.d(this.f4378b), this.c);
        this.d = true;
        return new q(this, oVar);
    }

    public final OutputStream m() {
        q();
        if (this.d) {
            throw new IllegalStateException("Cannot have more than one open stream writer.");
        }
        am.a(this.f4378b);
        this.f4378b = this.c.d();
        org.a.c.d.d dVar = new org.a.c.d.d(this.f4378b);
        this.d = true;
        return new r(this, dVar);
    }

    private List<org.a.c.c.l> r() {
        ArrayList arrayList = new ArrayList();
        b o = o();
        if (o instanceof j) {
            arrayList.add(org.a.c.c.m.f4405a.a((j) o));
        } else if (o instanceof a) {
            a aVar = (a) o;
            for (int i = 0; i < aVar.b(); i++) {
                arrayList.add(org.a.c.c.m.f4405a.a((j) aVar.b(i)));
            }
        }
        return arrayList;
    }

    public final long n() {
        if (this.d) {
            throw new IllegalStateException("There is an open OutputStream associated with this COSStream. It must be closed before queryinglength of this COSStream.");
        }
        return b(j.bX, 0);
    }

    public final b o() {
        return a(j.aY);
    }

    public final String p() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        g gVar = null;
        try {
            g k = k();
            gVar = k;
            am.a(k, byteArrayOutputStream);
            am.a((Closeable) gVar);
            return new s(byteArrayOutputStream.toByteArray()).b();
        } catch (IOException unused) {
            am.a((Closeable) gVar);
            return "";
        } catch (Throwable th) {
            am.a((Closeable) gVar);
            throw th;
        }
    }

    @Override // org.a.c.b.d, org.a.c.b.b
    public final Object a(u uVar) {
        return uVar.a(this);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f4378b != null) {
            this.f4378b.close();
        }
    }
}
