package org.a.c.h.e;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.a.b.f.al;
import org.a.b.f.ap;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:org/a/c/h/e/ab.class */
public class ab extends aa {
    private static final org.a.a.a.a e = org.a.a.a.c.a(ab.class);
    private static final Map<String, Integer> f = new HashMap(User32.VK_PLAY);
    private org.a.b.f.d g;
    private org.a.b.f.d h;
    private org.a.b.f.d i;
    private boolean j;
    private Map<Integer, Integer> k;
    private final ap l;
    private final boolean m;
    private org.a.b.h.a n;

    static {
        for (Map.Entry<Integer, String> entry : org.a.c.h.e.a.f.c.d().entrySet()) {
            if (!f.containsKey(entry.getValue())) {
                f.put(entry.getValue(), entry.getKey());
            }
        }
    }

    public ab(org.a.c.b.d dVar) {
        super(dVar);
        org.a.c.h.a.i p;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = false;
        ap apVar = null;
        if (b() != null && (p = super.b().p()) != null) {
            try {
                apVar = new al(true).b(p.c());
            } catch (IOException unused) {
                new StringBuilder("Could not read embedded TTF for font ").append(s());
            } catch (NullPointerException unused2) {
                new StringBuilder("Could not read embedded TTF for font ").append(s());
            }
        }
        this.m = apVar != null;
        if (apVar == null) {
            m<ap> a2 = l.a().a(s(), b());
            apVar = a2.c();
            if (a2.d()) {
                new StringBuilder("Using fallback font '").append(apVar).append("' for '").append(s()).append("'");
            }
        }
        this.l = apVar;
        l();
    }

    private String s() {
        return this.f4561b.g(org.a.c.b.j.v);
    }

    @Override // org.a.c.h.e.aa
    protected final org.a.c.h.e.a.c m() {
        if (!c() && a() != null) {
            return new org.a.c.h.e.a.j(a());
        }
        if (r() != null && !r().booleanValue()) {
            return org.a.c.h.e.a.h.c;
        }
        String c = ah.c(d());
        if (i() && !c.equals("Symbol") && !c.equals("ZapfDingbats")) {
            return org.a.c.h.e.a.h.c;
        }
        org.a.b.f.ag k = this.l.k();
        HashMap hashMap = new HashMap();
        for (int i = 0; i <= 256; i++) {
            int g = g(i);
            if (g > 0) {
                String str = null;
                if (k != null) {
                    str = k.a(g);
                }
                if (str == null) {
                    str = Integer.toString(g);
                }
                hashMap.put(Integer.valueOf(i), str);
            }
        }
        return new org.a.c.h.e.a.a(hashMap);
    }

    @Override // org.a.c.h.e.u
    public final int a(InputStream inputStream) {
        return inputStream.read();
    }

    @Override // org.a.c.h.e.u
    public final String d() {
        return s();
    }

    @Override // org.a.c.h.e.u
    public final org.a.b.h.a e() {
        if (this.n == null) {
            this.n = t();
        }
        return this.n;
    }

    private org.a.b.h.a t() {
        org.a.c.h.a.h j;
        if (b() != null && (j = b().j()) != null) {
            return new org.a.b.h.a(j.c(), j.d(), j.e(), j.g());
        }
        return this.l.c();
    }

    @Override // org.a.c.h.e.u
    public final float c(int i) {
        float a2 = this.l.a(g(i));
        float x = this.l.x();
        if (x != 1000.0f) {
            a2 *= 1000.0f / x;
        }
        return a2;
    }

    @Override // org.a.c.h.e.u
    protected final byte[] d(int i) {
        if (this.c != null) {
            if (!this.c.a(o().a(i))) {
                throw new IllegalArgumentException(String.format("U+%04X is not available in this font's encoding: %s", Integer.valueOf(i), this.c.a()));
            }
            String a2 = o().a(i);
            Map<String, Integer> e2 = this.c.e();
            if (!this.l.b(a2) && !this.l.b(l.a(i))) {
                throw new IllegalArgumentException(String.format("No glyph for U+%04X in font %s", Integer.valueOf(i), d()));
            }
            return new byte[]{(byte) e2.get(a2).intValue()};
        }
        String a3 = o().a(i);
        if (!this.l.b(a3)) {
            throw new IllegalArgumentException(String.format("No glyph for U+%04X in font %s", Integer.valueOf(i), d()));
        }
        Integer num = u().get(Integer.valueOf(this.l.e(a3)));
        if (num == null) {
            throw new IllegalArgumentException(String.format("U+%04X is not available in this font's Encoding", Integer.valueOf(i)));
        }
        return new byte[]{(byte) num.intValue()};
    }

    private Map<Integer, Integer> u() {
        if (this.k != null) {
            return this.k;
        }
        this.k = new HashMap();
        for (int i = 0; i <= 255; i++) {
            int g = g(i);
            if (!this.k.containsKey(Integer.valueOf(g))) {
                this.k.put(Integer.valueOf(g), Integer.valueOf(i));
            }
        }
        return this.k;
    }

    @Override // org.a.c.h.e.u
    public final boolean c() {
        return this.m;
    }

    private int g(int i) {
        Integer num;
        String a2;
        v();
        int i2 = 0;
        if (!p()) {
            String a3 = this.c.a(i);
            if (".notdef".equals(a3)) {
                return 0;
            }
            if (this.g != null && (a2 = org.a.c.h.e.a.d.a().a(a3)) != null) {
                i2 = this.g.a(a2.codePointAt(0));
            }
            if (i2 == 0 && this.i != null && (num = f.get(a3)) != null) {
                i2 = this.i.a(num.intValue());
            }
            if (i2 == 0) {
                i2 = this.l.e(a3);
            }
        } else {
            if (this.h != null) {
                i2 = this.h.a(i);
                if (i >= 0 && i <= 255) {
                    if (i2 == 0) {
                        i2 = this.h.a(i + 61440);
                    }
                    if (i2 == 0) {
                        i2 = this.h.a(i + User32.SC_KEYMENU);
                    }
                    if (i2 == 0) {
                        i2 = this.h.a(i + 61952);
                    }
                }
            }
            if (i2 == 0 && this.i != null) {
                i2 = this.i.a(i);
            }
            if (i2 == 0 && this.g != null && this.c != null) {
                String a4 = this.c.a(i);
                if (".notdef".equals(a4)) {
                    return 0;
                }
                String a5 = org.a.c.h.e.a.d.a().a(a4);
                if (a5 != null) {
                    i2 = this.g.a(a5.codePointAt(0));
                }
            }
        }
        return i2;
    }

    private void v() {
        if (this.j) {
            return;
        }
        org.a.b.f.f r = this.l.r();
        if (r != null) {
            for (org.a.b.f.d dVar : r.a()) {
                if (3 == dVar.b()) {
                    if (1 == dVar.a()) {
                        this.g = dVar;
                    } else if (0 == dVar.a()) {
                        this.h = dVar;
                    }
                } else if (1 == dVar.b() && 0 == dVar.a()) {
                    this.i = dVar;
                }
            }
        }
        this.j = true;
    }
}
