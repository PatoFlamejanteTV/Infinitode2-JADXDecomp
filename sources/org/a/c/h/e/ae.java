package org.a.c.h.e;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.bytebuddy.description.type.TypeDescription;

/* loaded from: infinitode-2.jar:org/a/c/h/e/ae.class */
public class ae extends aa {
    private static final org.a.a.a.a s = org.a.a.a.c.a(ae.class);
    private static final Map<String, String> t;
    public static final ae e;
    public static final ae f;
    public static final ae g;
    public static final ae h;
    public static final ae i;
    public static final ae j;
    public static final ae k;
    public static final ae l;
    public static final ae m;
    public static final ae n;
    public static final ae o;
    public static final ae p;
    public static final ae q;
    public static final ae r;
    private final org.a.b.g.d u;
    private final org.a.b.b v;
    private final boolean w;
    private org.a.c.i.d x;
    private final AffineTransform y;
    private org.a.b.h.a z;
    private final Map<Integer, byte[]> A;

    static {
        HashMap hashMap = new HashMap();
        t = hashMap;
        hashMap.put("ff", "f_f");
        t.put("ffi", "f_f_i");
        t.put("ffl", "f_f_l");
        t.put("fi", "f_i");
        t.put("fl", "f_l");
        t.put("st", "s_t");
        t.put("IJ", "I_J");
        t.put("ij", "i_j");
        t.put("ellipsis", "elipsis");
        e = new ae("Times-Roman");
        f = new ae("Times-Bold");
        g = new ae("Times-Italic");
        h = new ae("Times-BoldItalic");
        i = new ae("Helvetica");
        j = new ae("Helvetica-Bold");
        k = new ae("Helvetica-Oblique");
        l = new ae("Helvetica-BoldOblique");
        m = new ae("Courier");
        n = new ae("Courier-Bold");
        o = new ae("Courier-Oblique");
        p = new ae("Courier-BoldOblique");
        q = new ae("Symbol");
        r = new ae("ZapfDingbats");
    }

    private ae(String str) {
        super(str);
        String str2;
        this.f4561b.a(org.a.c.b.j.dE, (org.a.c.b.b) org.a.c.b.j.dP);
        this.f4561b.a(org.a.c.b.j.v, str);
        if ("ZapfDingbats".equals(str)) {
            this.c = org.a.c.h.e.a.l.c;
        } else if ("Symbol".equals(str)) {
            this.c = org.a.c.h.e.a.i.c;
        } else {
            this.c = org.a.c.h.e.a.k.c;
            this.f4561b.a(org.a.c.b.j.aR, (org.a.c.b.b) org.a.c.b.j.ec);
        }
        this.A = new ConcurrentHashMap();
        this.u = null;
        m<org.a.b.b> b2 = l.a().b(s(), b());
        this.v = b2.c();
        if (b2.d()) {
            try {
                str2 = this.v.b();
            } catch (IOException unused) {
                str2 = TypeDescription.Generic.OfWildcardType.SYMBOL;
            }
            new StringBuilder("Using fallback font ").append(str2).append(" for base font ").append(s());
        }
        this.w = false;
        this.y = new AffineTransform();
    }

    public ae(org.a.c.b.d dVar) {
        super(dVar);
        this.A = new HashMap();
        v b2 = b();
        org.a.b.g.d dVar2 = null;
        if (b2 != null) {
            if (b2.q() != null) {
                throw new IllegalArgumentException("Use PDType1CFont for FontFile3");
            }
            org.a.c.h.a.i o2 = b2.o();
            if (o2 != null) {
                try {
                    org.a.c.b.p f2 = o2.f();
                    int j2 = f2.j(org.a.c.b.j.bY);
                    int j3 = f2.j(org.a.c.b.j.bZ);
                    byte[] g2 = o2.g();
                    int a2 = a(g2, j2);
                    int a3 = a(g2, a2, j3);
                    if (g2.length > 0 && (g2[0] & 255) == 128) {
                        dVar2 = org.a.b.g.d.a(g2);
                    } else {
                        byte[] copyOfRange = Arrays.copyOfRange(g2, 0, a2);
                        byte[] copyOfRange2 = Arrays.copyOfRange(g2, a2, a2 + a3);
                        if (a2 > 0 && a3 > 0) {
                            dVar2 = org.a.b.g.d.a(copyOfRange, copyOfRange2);
                        }
                    }
                } catch (org.a.b.g.a unused) {
                    new StringBuilder("Can't read damaged embedded Type1 font ").append(b2.g());
                } catch (IOException unused2) {
                    new StringBuilder("Can't read the embedded Type1 font ").append(b2.g());
                }
            }
        }
        this.w = dVar2 != null;
        this.u = dVar2;
        if (this.u != null) {
            this.v = this.u;
        } else {
            m<org.a.b.b> b3 = l.a().b(s(), b2);
            this.v = b3.c();
            if (b3.d()) {
                new StringBuilder("Using fallback font ").append(this.v.b()).append(" for ").append(s());
            }
        }
        l();
        this.y = h().a();
        this.y.scale(1000.0d, 1000.0d);
    }

    private int a(byte[] bArr, int i2) {
        int max = Math.max(0, i2 - 4);
        int i3 = max;
        if (max <= 0 || i3 > bArr.length - 4) {
            i3 = bArr.length - 4;
        }
        int b2 = b(bArr, i3);
        int i4 = b2;
        if (b2 == 0 && i2 > 0) {
            i4 = b(bArr, bArr.length - 4);
        }
        if (i2 - i4 != 0 && i4 > 0) {
            if (s.c()) {
                new StringBuilder("Ignored invalid Length1 ").append(i2).append(" for Type 1 font ").append(d());
            }
            return i4;
        }
        return i2;
    }

    private static int b(byte[] bArr, int i2) {
        int i3 = i2;
        while (true) {
            if (i3 <= 0) {
                break;
            }
            if (bArr[i3] == 101 && bArr[i3 + 1] == 120 && bArr[i3 + 2] == 101 && bArr[i3 + 3] == 99) {
                i3 += 4;
                while (i3 < bArr.length && (bArr[i3] == 13 || bArr[i3] == 10 || bArr[i3] == 32 || bArr[i3] == 9)) {
                    i3++;
                }
            } else {
                i3--;
            }
        }
        return i3;
    }

    private int a(byte[] bArr, int i2, int i3) {
        if (i3 < 0 || i3 > bArr.length - i2) {
            new StringBuilder("Ignored invalid Length2 ").append(i3).append(" for Type 1 font ").append(d());
            return bArr.length - i2;
        }
        return i3;
    }

    private String s() {
        return this.f4561b.g(org.a.c.b.j.v);
    }

    @Override // org.a.c.h.e.u
    protected final byte[] d(int i2) {
        byte[] bArr = this.A.get(Integer.valueOf(i2));
        if (bArr != null) {
            return bArr;
        }
        String a2 = o().a(i2);
        if (i()) {
            if (!this.c.a(a2)) {
                throw new IllegalArgumentException(String.format("U+%04X ('%s') is not available in this font %s encoding: %s", Integer.valueOf(i2), a2, d(), this.c.a()));
            }
            if (".notdef".equals(a2)) {
                throw new IllegalArgumentException(String.format("No glyph for U+%04X in font %s", Integer.valueOf(i2), d()));
            }
        } else {
            if (!this.c.a(a2)) {
                throw new IllegalArgumentException(String.format("U+%04X ('%s') is not available in this font %s (generic: %s) encoding: %s", Integer.valueOf(i2), a2, d(), this.v.b(), this.c.a()));
            }
            String c = c(a2);
            if (c.equals(".notdef") || !this.v.b(c)) {
                throw new IllegalArgumentException(String.format("No glyph for U+%04X in font %s (generic: %s)", Integer.valueOf(i2), d(), this.v.b()));
            }
        }
        byte[] bArr2 = {(byte) this.c.e().get(a2).intValue()};
        this.A.put(Integer.valueOf(i2), bArr2);
        return bArr2;
    }

    @Override // org.a.c.h.e.u
    public final float c(int i2) {
        String g2 = g(i2);
        if (!this.w && ".notdef".equals(g2)) {
            return 250.0f;
        }
        Point2D.Float r0 = new Point2D.Float(this.v.a(g2), 0.0f);
        this.y.transform(r0, r0);
        return (float) r0.getX();
    }

    @Override // org.a.c.h.e.u
    public final boolean c() {
        return this.w;
    }

    @Override // org.a.c.h.e.u
    public final int a(InputStream inputStream) {
        return inputStream.read();
    }

    @Override // org.a.c.h.e.aa
    protected final org.a.c.h.e.a.c m() {
        if (!c() && a() != null) {
            return new org.a.c.h.e.a.j(a());
        }
        if (this.v instanceof org.a.b.a) {
            return org.a.c.h.e.a.j.a(((org.a.b.a) this.v).a());
        }
        return org.a.c.h.e.a.h.c;
    }

    @Override // org.a.c.h.e.u
    public final String d() {
        return s();
    }

    @Override // org.a.c.h.e.u
    public final org.a.b.h.a e() {
        if (this.z == null) {
            this.z = t();
        }
        return this.z;
    }

    private org.a.b.h.a t() {
        org.a.c.h.a.h j2;
        if (b() != null && (j2 = b().j()) != null && (j2.c() != 0.0f || j2.d() != 0.0f || j2.e() != 0.0f || j2.g() != 0.0f)) {
            return new org.a.b.h.a(j2.c(), j2.d(), j2.e(), j2.g());
        }
        return this.v.c();
    }

    private String g(int i2) {
        return c(n().a(i2));
    }

    private String c(String str) {
        Integer num;
        if (c() || this.v.b(str)) {
            return str;
        }
        String str2 = t.get(str);
        if (str2 != null && !str.equals(".notdef") && this.v.b(str2)) {
            return str2;
        }
        String a2 = o().a(str);
        if (a2 != null && a2.length() == 1) {
            String a3 = l.a(a2.codePointAt(0));
            if (this.v.b(a3)) {
                return a3;
            }
            if ("SymbolMT".equals(this.v.b()) && (num = org.a.c.h.e.a.i.c.e().get(str)) != null) {
                String a4 = l.a(num.intValue() + 61440);
                if (this.v.b(a4)) {
                    return a4;
                }
                return ".notdef";
            }
            return ".notdef";
        }
        return ".notdef";
    }

    @Override // org.a.c.h.e.u
    public final org.a.c.i.d h() {
        if (this.x == null) {
            List<Number> list = null;
            try {
                list = this.v.d();
            } catch (IOException unused) {
                this.x = f4560a;
            }
            if (list != null && list.size() == 6) {
                this.x = new org.a.c.i.d(list.get(0).floatValue(), list.get(1).floatValue(), list.get(2).floatValue(), list.get(3).floatValue(), list.get(4).floatValue(), list.get(5).floatValue());
            } else {
                return super.h();
            }
        }
        return this.x;
    }
}
