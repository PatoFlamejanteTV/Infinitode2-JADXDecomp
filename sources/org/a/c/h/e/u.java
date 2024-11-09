package org.a.c.h.e;

import com.a.a.a.am;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: infinitode-2.jar:org/a/c/h/e/u.class */
public abstract class u implements org.a.c.h.a.c {
    private static final org.a.a.a.a c = org.a.a.a.c.a(u.class);

    /* renamed from: a, reason: collision with root package name */
    protected static final org.a.c.i.d f4560a = new org.a.c.i.d(0.001f, 0.0f, 0.0f, 0.001f, 0.0f, 0.0f);

    /* renamed from: b, reason: collision with root package name */
    protected final org.a.c.b.d f4561b;
    private final org.a.b.c.a d;
    private final l e;
    private v f;
    private List<Float> g;
    private final Map<Integer, Float> h;

    protected abstract float b(int i);

    public abstract float c(int i);

    public abstract boolean c();

    protected abstract byte[] d(int i);

    public abstract int a(InputStream inputStream);

    public abstract String d();

    public abstract org.a.b.h.a e();

    public abstract void f(int i);

    public abstract void j();

    public abstract boolean k();

    /* JADX INFO: Access modifiers changed from: package-private */
    public u() {
        this.f4561b = new org.a.c.b.d();
        this.f4561b.a(org.a.c.b.j.dN, (org.a.c.b.b) org.a.c.b.j.be);
        this.d = null;
        this.f = null;
        this.e = null;
        this.h = new HashMap();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public u(String str) {
        this.f4561b = new org.a.c.b.d();
        this.f4561b.a(org.a.c.b.j.dN, (org.a.c.b.b) org.a.c.b.j.be);
        this.d = null;
        this.e = ah.a(str);
        if (this.e == null) {
            throw new IllegalArgumentException("No AFM for font " + str);
        }
        this.f = l.a(this.e);
        this.h = new ConcurrentHashMap();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public u(org.a.c.b.d dVar) {
        this.f4561b = dVar;
        this.h = new HashMap();
        this.e = ah.a(d());
        this.f = l();
        this.d = m();
    }

    private v l() {
        org.a.c.b.d dVar = (org.a.c.b.d) this.f4561b.a(org.a.c.b.j.bg);
        if (dVar != null) {
            return new v(dVar);
        }
        if (this.e != null) {
            return l.a(this.e);
        }
        return null;
    }

    private org.a.b.c.a m() {
        org.a.c.b.b a2 = this.f4561b.a(org.a.c.b.j.dJ);
        if (a2 == null) {
            return null;
        }
        org.a.b.c.a aVar = null;
        try {
            org.a.b.c.a a3 = a(a2);
            aVar = a3;
            if (a3 != null && !aVar.b()) {
                new StringBuilder("Invalid ToUnicode CMap in font ").append(d());
                String c2 = aVar.c() != null ? aVar.c() : "";
                String e = aVar.e() != null ? aVar.e() : "";
                org.a.c.b.b a4 = this.f4561b.a(org.a.c.b.j.aR);
                if (c2.contains("Identity") || e.contains("Identity") || org.a.c.b.j.bC.equals(a4) || org.a.c.b.j.bD.equals(a4)) {
                    aVar = c.a(org.a.c.b.j.bC.a());
                }
            }
        } catch (IOException unused) {
            new StringBuilder("Could not read ToUnicode CMap in font ").append(d());
        }
        return aVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final l a() {
        return this.e;
    }

    public v b() {
        return this.f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static org.a.b.c.a a(org.a.c.b.b bVar) {
        if (bVar instanceof org.a.c.b.j) {
            return c.a(((org.a.c.b.j) bVar).a());
        }
        if (bVar instanceof org.a.c.b.p) {
            org.a.c.b.g gVar = null;
            try {
                org.a.c.b.g k = ((org.a.c.b.p) bVar).k();
                gVar = k;
                org.a.b.c.a a2 = c.a(k);
                am.a((Closeable) gVar);
                return a2;
            } catch (Throwable th) {
                am.a((Closeable) gVar);
                throw th;
            }
        }
        throw new IOException("Expected Name or Stream");
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // org.a.c.h.a.c
    /* renamed from: n, reason: merged with bridge method [inline-methods] */
    public org.a.c.b.d f() {
        return this.f4561b;
    }

    public float a(int i) {
        Float f = this.h.get(Integer.valueOf(i));
        if (f != null) {
            return f.floatValue();
        }
        if (this.f4561b.a(org.a.c.b.j.eb) != null || this.f4561b.o(org.a.c.b.j.ck)) {
            int b2 = this.f4561b.b(org.a.c.b.j.ba, -1);
            int b3 = this.f4561b.b(org.a.c.b.j.bW, -1);
            int size = g().size();
            int i2 = i - b2;
            if (size > 0 && i >= b2 && i <= b3 && i2 < size) {
                Float f2 = g().get(i2);
                Float f3 = f2;
                if (f2 == null) {
                    f3 = Float.valueOf(0.0f);
                }
                this.h.put(Integer.valueOf(i), f3);
                return f3.floatValue();
            }
            v b4 = b();
            if (b4 != null) {
                Float valueOf = Float.valueOf(b4.n());
                this.h.put(Integer.valueOf(i), valueOf);
                return valueOf.floatValue();
            }
        }
        if (i()) {
            Float valueOf2 = Float.valueOf(b(i));
            this.h.put(Integer.valueOf(i), valueOf2);
            return valueOf2.floatValue();
        }
        Float valueOf3 = Float.valueOf(c(i));
        this.h.put(Integer.valueOf(i), valueOf3);
        return valueOf3.floatValue();
    }

    public final byte[] a(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < str.length()) {
                int codePointAt = str.codePointAt(i2);
                byteArrayOutputStream.write(d(codePointAt));
                i = i2 + Character.charCount(codePointAt);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public float b(String str) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(a(str));
        float f = 0.0f;
        while (true) {
            float f2 = f;
            if (byteArrayInputStream.available() > 0) {
                f = f2 + a(a(byteArrayInputStream));
            } else {
                return f2;
            }
        }
    }

    public String a(int i, org.a.c.h.e.a.d dVar) {
        return e(i);
    }

    public String e(int i) {
        if (this.d != null) {
            if (this.d.c() != null && this.d.c().startsWith("Identity-") && ((this.f4561b.a(org.a.c.b.j.dJ) instanceof org.a.c.b.j) || !this.d.b())) {
                return new String(new char[]{(char) i});
            }
            return this.d.a(i);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final List<Float> g() {
        if (this.g == null) {
            org.a.c.b.a aVar = (org.a.c.b.a) this.f4561b.a(org.a.c.b.j.eb);
            if (aVar != null) {
                this.g = org.a.c.h.a.a.b(aVar);
            } else {
                this.g = Collections.emptyList();
            }
        }
        return this.g;
    }

    public org.a.c.i.d h() {
        return f4560a;
    }

    public boolean i() {
        if (c()) {
            return false;
        }
        return ah.b(d());
    }

    public boolean equals(Object obj) {
        return (obj instanceof u) && ((u) obj).f() == f();
    }

    public int hashCode() {
        return f().hashCode();
    }

    public String toString() {
        return getClass().getSimpleName() + SequenceUtils.SPACE + d();
    }
}
