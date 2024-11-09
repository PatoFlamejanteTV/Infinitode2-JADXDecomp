package com.d.h;

import com.d.e.aa;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Locale;
import java.util.logging.Level;

/* loaded from: infinitode-2.jar:com/d/h/v.class */
public final class v extends com.d.l.c {

    /* renamed from: b, reason: collision with root package name */
    private aa f1275b;
    private final m c;

    public v(m mVar) {
        this.c = mVar;
    }

    private static byte[] a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(inputStream.available());
        byte[] bArr = new byte[10240];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    @Override // com.d.l.c, com.d.d.s
    public final com.d.j.f b(String str) {
        com.d.j.f fVar;
        String f = f(str);
        if (f != null) {
            com.d.j.f fVar2 = this.f1410a.get(f);
            com.d.j.f fVar3 = fVar2;
            if (fVar2 != null && (fVar3.d() instanceof i)) {
                i iVar = (i) fVar3.d();
                return new com.d.j.f(fVar3.e(), new i(iVar.c(), iVar.f(), iVar.a(), iVar.b(), iVar.g(), iVar.e()));
            }
            if (com.d.m.f.a(f)) {
                fVar = h(f);
                this.c.a((i) fVar.d());
                this.f1410a.put(f, fVar);
            } else {
                InputStream g = g(f);
                if (g != null) {
                    try {
                        try {
                            URI uri = new URI(str);
                            if (uri.getPath() == null || !uri.getPath().toLowerCase(Locale.US).endsWith(".pdf")) {
                                i iVar2 = new i(a(g), str);
                                a(iVar2);
                                this.c.a(iVar2);
                                fVar3 = new com.d.j.f(f, iVar2);
                            }
                            this.f1410a.put(f, fVar3);
                        } finally {
                            try {
                                g.close();
                            } catch (IOException unused) {
                            }
                        }
                    } catch (Exception e) {
                        com.d.m.l.a("Can't read image file; unexpected problem for URI '" + str + "'", e);
                        try {
                            g.close();
                        } catch (IOException unused2) {
                        }
                    }
                }
                if (fVar3 != null) {
                    fVar = new com.d.j.f(fVar3.e(), fVar3.d());
                } else {
                    fVar = new com.d.j.f(str, null);
                }
            }
            return fVar;
        }
        com.d.m.l.e(Level.INFO, "URI resolver rejected loading image at (" + str + ")");
        return new com.d.j.f(str, null);
    }

    private com.d.j.f h(String str) {
        try {
            i iVar = new i(com.d.m.f.c(str), str);
            a(iVar);
            return new com.d.j.f(null, iVar);
        } catch (Exception e) {
            com.d.m.l.a("Can't read XHTML embedded image.", e);
            return new com.d.j.f(null, null);
        }
    }

    private void a(i iVar) {
        float s = this.f1275b.s();
        if (s != 1.0f) {
            iVar.a((int) (iVar.a() * s), (int) (iVar.b() * s));
        }
    }

    public final void a(aa aaVar) {
        this.f1275b = aaVar;
    }
}
