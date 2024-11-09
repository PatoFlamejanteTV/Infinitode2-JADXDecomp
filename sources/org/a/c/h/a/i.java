package org.a.c.h.a;

import com.a.a.a.am;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import org.a.c.b.j;
import org.a.c.b.p;

/* loaded from: infinitode-2.jar:org/a/c/h/a/i.class */
public class i implements c {

    /* renamed from: a, reason: collision with root package name */
    private final p f4490a;

    public i(org.a.c.h.b bVar) {
        this.f4490a = bVar.a().a();
    }

    public i(p pVar) {
        this.f4490a = pVar;
    }

    public i(org.a.c.h.b bVar, InputStream inputStream, j jVar) {
        this(bVar, inputStream, (org.a.c.b.b) jVar);
    }

    private i(org.a.c.h.b bVar, InputStream inputStream, org.a.c.b.b bVar2) {
        OutputStream outputStream = null;
        try {
            this.f4490a = bVar.a().a();
            outputStream = this.f4490a.a(bVar2);
            am.a(inputStream, outputStream);
            outputStream.close();
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Throwable th) {
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    @Override // org.a.c.h.a.c
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public final p f() {
        return this.f4490a;
    }

    public final OutputStream b() {
        return this.f4490a.l();
    }

    public final OutputStream a(j jVar) {
        return this.f4490a.a((org.a.c.b.b) jVar);
    }

    public final org.a.c.b.g c() {
        return this.f4490a.k();
    }

    public final int d() {
        return this.f4490a.b(j.bX, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final List<j> e() {
        List list = null;
        org.a.c.b.b o = this.f4490a.o();
        if (o instanceof j) {
            j jVar = (j) o;
            list = new a(jVar, jVar, this.f4490a, j.aY);
        } else if (o instanceof org.a.c.b.a) {
            list = ((org.a.c.b.a) o).e();
        }
        return list;
    }

    public final byte[] g() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        org.a.c.b.g gVar = null;
        try {
            org.a.c.b.g c = c();
            gVar = c;
            am.a(c, byteArrayOutputStream);
            if (gVar != null) {
                gVar.close();
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            if (gVar != null) {
                gVar.close();
            }
            throw th;
        }
    }
}
