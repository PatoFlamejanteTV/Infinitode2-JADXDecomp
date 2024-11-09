package org.a.b.f;

import com.vladsch.flexmark.util.html.Attribute;
import java.awt.geom.GeneralPath;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:org/a/b/f/ap.class */
public class ap implements Closeable, org.a.b.b {
    private final ak d;
    private Map<String, Integer> e;

    /* renamed from: b, reason: collision with root package name */
    private int f4287b = -1;
    private int c = -1;

    /* renamed from: a, reason: collision with root package name */
    protected Map<String, an> f4288a = new HashMap();
    private final List<String> f = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    public ap(ak akVar) {
        this.d = akVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.d.close();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(float f) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(an anVar) {
        this.f4288a.put(anVar.E(), anVar);
    }

    public final Collection<an> h() {
        return this.f4288a.values();
    }

    public final Map<String, an> i() {
        return this.f4288a;
    }

    public final synchronized byte[] b(an anVar) {
        long e = this.d.e();
        this.d.a(anVar.D());
        byte[] d = this.d.d((int) anVar.C());
        this.d.a(e);
        return d;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final synchronized an d(String str) {
        an anVar = this.f4288a.get(str);
        if (anVar != null && !anVar.F()) {
            c(anVar);
        }
        return anVar;
    }

    public final z j() {
        return (z) d(Attribute.NAME_ATTR);
    }

    public final ag k() {
        return (ag) d("post");
    }

    public final aa l() {
        return (aa) d("OS/2");
    }

    public final w m() {
        return (w) d("maxp");
    }

    public final q n() {
        return (q) d("head");
    }

    public final r o() {
        return (r) d("hhea");
    }

    public final s p() {
        return (s) d("hmtx");
    }

    public final t q() {
        return (t) d("loca");
    }

    public p e() {
        return (p) d("glyf");
    }

    public final f r() {
        return (f) d("cmap");
    }

    public final aq s() {
        return (aq) d("vhea");
    }

    public final ar t() {
        return (ar) d("vmtx");
    }

    private n a() {
        return (n) d("GSUB");
    }

    public final InputStream u() {
        return this.d.f();
    }

    public final long v() {
        return this.d.g();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c(an anVar) {
        synchronized (this.d) {
            long e = this.d.e();
            this.d.a(anVar.D());
            anVar.a(this, this.d);
            this.d.a(e);
        }
    }

    public final int w() {
        if (this.f4287b == -1) {
            w m = m();
            if (m != null) {
                this.f4287b = m.n();
            } else {
                this.f4287b = 0;
            }
        }
        return this.f4287b;
    }

    public final int x() {
        if (this.c == -1) {
            q n = n();
            if (n != null) {
                this.c = n.k();
            } else {
                this.c = 0;
            }
        }
        return this.c;
    }

    public final int a(int i) {
        s p = p();
        if (p != null) {
            return p.a(i);
        }
        return User32.VK_PLAY;
    }

    @Override // org.a.b.b
    public final String b() {
        if (j() != null) {
            return j().d();
        }
        return null;
    }

    private synchronized void f() {
        if (this.e == null && k() != null) {
            String[] i = k().i();
            if (i != null) {
                this.e = new HashMap(i.length);
                for (int i2 = 0; i2 < i.length; i2++) {
                    this.e.put(i[i2], Integer.valueOf(i2));
                }
                return;
            }
            this.e = new HashMap();
        }
    }

    @Deprecated
    public final d y() {
        return b(true);
    }

    @Deprecated
    private d b(boolean z) {
        return c(true);
    }

    public final c z() {
        return a(true);
    }

    public final c a(boolean z) {
        n a2;
        d c = c(z);
        if (!this.f.isEmpty() && (a2 = a()) != null) {
            return new ai(c, a2, Collections.unmodifiableList(this.f));
        }
        return c;
    }

    private d c(boolean z) {
        f r = r();
        if (r == null) {
            if (z) {
                throw new IOException("The TrueType font " + b() + " does not contain a 'cmap' table");
            }
            return null;
        }
        d a2 = r.a(0, 4);
        d dVar = a2;
        if (a2 == null) {
            dVar = r.a(3, 10);
        }
        if (dVar == null) {
            dVar = r.a(0, 3);
        }
        if (dVar == null) {
            dVar = r.a(3, 1);
        }
        if (dVar == null) {
            dVar = r.a(3, 0);
        }
        if (dVar == null) {
            if (z) {
                throw new IOException("The TrueType font does not contain a Unicode cmap");
            }
            if (r.a().length > 0) {
                dVar = r.a()[0];
            }
        }
        return dVar;
    }

    public final int e(String str) {
        Integer num;
        f();
        if (this.e != null && (num = this.e.get(str)) != null && num.intValue() > 0 && num.intValue() < m().n()) {
            return num.intValue();
        }
        int f = f(str);
        if (f >= 0) {
            return a(false).a(f);
        }
        return 0;
    }

    private static int f(String str) {
        if (str.startsWith("uni") && str.length() == 7) {
            int length = str.length();
            StringBuilder sb = new StringBuilder();
            for (int i = 3; i + 4 <= length; i += 4) {
                try {
                    int i2 = i;
                    int parseInt = Integer.parseInt(str.substring(i2, i2 + 4), 16);
                    if (parseInt <= 55295 || parseInt >= 57344) {
                        sb.append((char) parseInt);
                    }
                } catch (NumberFormatException unused) {
                    return -1;
                }
            }
            String sb2 = sb.toString();
            if (sb2.length() == 0) {
                return -1;
            }
            return sb2.codePointAt(0);
        }
        return -1;
    }

    public GeneralPath c(String str) {
        k a2 = e().a(e(str));
        if (a2 == null) {
            return new GeneralPath();
        }
        return a2.b();
    }

    @Override // org.a.b.b
    public final float a(String str) {
        return a(Integer.valueOf(e(str)).intValue());
    }

    @Override // org.a.b.b
    public final boolean b(String str) {
        return e(str) != 0;
    }

    @Override // org.a.b.b
    public final org.a.b.h.a c() {
        short n = n().n();
        short m = n().m();
        float x = 1000.0f / x();
        return new org.a.b.h.a(n * x, n().p() * x, m * x, n().o() * x);
    }

    @Override // org.a.b.b
    public final List<Number> d() {
        float x = 1000.0f / x();
        return Arrays.asList(Float.valueOf(0.001f * x), 0, 0, Float.valueOf(0.001f * x), 0, 0);
    }

    public String toString() {
        try {
            if (j() != null) {
                return j().d();
            }
            return "(null)";
        } catch (IOException e) {
            return "(null - " + e.getMessage() + ")";
        }
    }
}
