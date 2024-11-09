package com.d.i;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/* loaded from: infinitode-2.jar:com/d/i/q.class */
public final class q implements com.d.e.ac {

    /* renamed from: a, reason: collision with root package name */
    private Element f1369a;

    /* renamed from: b, reason: collision with root package name */
    private String f1370b;
    private String c;
    private boolean d;
    private boolean e;
    private boolean f;
    private com.d.c.f.c g;
    private com.d.c.b.b h;
    private e i;
    private boolean j;
    private int k;
    private int l;
    private int m;
    private String n;
    private final Text o;
    private byte p;

    public q(String str, Text text) {
        this.c = str;
        this.f1370b = str;
        this.o = text;
    }

    public final void a(byte b2) {
        this.p = b2;
    }

    public final byte b() {
        return this.p;
    }

    public final String c() {
        return this.c;
    }

    public final void a(String str) {
        this.c = str;
        this.f1370b = str;
    }

    public final void d() {
        this.c = this.f1370b;
        this.c = com.d.e.ad.a(this.c, a());
    }

    public final boolean e() {
        return this.d;
    }

    public final void a(boolean z) {
        this.d = z;
    }

    public final boolean f() {
        return this.f;
    }

    public final void b(boolean z) {
        this.f = z;
    }

    public final boolean g() {
        return this.e;
    }

    public final void c(boolean z) {
        this.e = z;
    }

    @Override // com.d.e.ac
    public final com.d.c.f.c a() {
        return this.g;
    }

    @Override // com.d.e.ac
    public final void a(com.d.c.f.c cVar) {
        this.g = cVar;
    }

    public final Element h() {
        return this.f1369a;
    }

    @Override // com.d.e.ac
    public final void a(Element element) {
        this.f1369a = element;
    }

    public final com.d.c.b.b i() {
        return this.h;
    }

    public final void a(com.d.c.b.b bVar) {
        this.h = bVar;
    }

    public final boolean j() {
        return this.h != null;
    }

    private int a(com.d.e.v vVar, String str) {
        com.d.d.r d = vVar.d();
        vVar.w();
        return d.a(vVar.c(a().b(vVar)), str);
    }

    private int b(com.d.e.v vVar, String str) {
        int i = 0;
        for (char c : str.toCharArray()) {
            int a2 = a(vVar, Character.toString(c));
            if (a2 > i) {
                i = a2;
            }
        }
        return i;
    }

    private void b(com.d.e.v vVar, int i, boolean z) {
        int i2;
        int i3 = 0;
        while (true) {
            i2 = i3;
            int indexOf = this.c.indexOf(SequenceUtils.EOL, i2);
            if (indexOf == -1) {
                break;
            }
            String substring = this.c.substring(i2, indexOf);
            if (z) {
                substring = substring.trim();
            }
            int a2 = a(vVar, substring);
            if (i2 == 0) {
                a2 += a().a(vVar, i, 1);
            }
            if (a2 > this.k) {
                this.k = a2;
            }
            if (i2 == 0) {
                this.m = a2;
            }
            i3 = indexOf + 1;
        }
        String substring2 = this.c.substring(i2);
        if (z) {
            substring2 = substring2.trim();
        }
        int a3 = a(vVar, substring2) + a().a(vVar, i, 2);
        if (a3 > this.k) {
            this.k = a3;
        }
        if (i2 == 0) {
            this.m = a3;
        }
    }

    private int b(com.d.e.v vVar) {
        com.d.d.r d = vVar.d();
        vVar.w();
        return d.a(a().d(vVar), SequenceUtils.SPACE);
    }

    public final int a(com.d.e.v vVar) {
        if (this.c.length() > 0 && this.c.charAt(this.c.length() - 1) == ' ') {
            return b(vVar);
        }
        return 0;
    }

    private int a(com.d.e.v vVar, int i, boolean z, boolean z2) {
        int i2;
        int i3;
        int i4 = -1;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        String d = d(z);
        com.d.d.g b2 = com.d.e.j.b(d, vVar.y());
        while (true) {
            int a2 = b2.a();
            if (a2 == -1) {
                break;
            }
            String substring = d.substring(i5, a2);
            int a3 = a(vVar, substring);
            if (a().j() == com.d.c.a.c.as) {
                i3 = b(vVar, substring);
            } else {
                i3 = a3;
            }
            if (i7 > 0) {
                if (z2) {
                    for (int i10 = 0; i10 < i7; i10++) {
                        a3 += i4;
                        i3 += i4;
                    }
                } else {
                    i6 += i4;
                }
                i7 = 0;
            }
            if (i3 > 0) {
                i8 = i3;
                i9 = i3;
            }
            if (i3 > this.l) {
                this.l = i3;
            }
            i6 += a3;
            i5 = a2;
            for (int i11 = a2; i11 < d.length() && d.charAt(i11) == ' '; i11++) {
                i7++;
                i5++;
            }
            if (i7 > 0 && i4 == -1) {
                i4 = b(vVar);
            }
        }
        String substring2 = d.substring(i5);
        int a4 = a(vVar, substring2);
        if (a().j() == com.d.c.a.c.as) {
            i2 = b(vVar, substring2);
        } else {
            i2 = a4;
        }
        if (i7 > 0) {
            if (z2) {
                for (int i12 = 0; i12 < i7; i12++) {
                    a4 += i4;
                    i2 += i4;
                }
            } else {
                i6 += i4;
            }
        }
        if (i2 > 0) {
            i8 = i2;
            i9 = i2;
        }
        if (i2 > this.l) {
            this.l = i2;
        }
        int i13 = i6 + a4;
        if (g()) {
            int a5 = a().a(vVar, i, 1);
            if (i8 + a5 > this.l) {
                this.l = i8 + a5;
            }
            i13 += a5;
        }
        if (f()) {
            int a6 = a().a(vVar, i, 2);
            if (i9 + a6 > this.l) {
                this.l = i9 + a6;
            }
            i13 += a6;
        }
        return i13;
    }

    private String d(boolean z) {
        if (!z) {
            return c();
        }
        if (this.c.length() > 0 && this.c.charAt(0) == ' ') {
            return this.c.substring(1);
        }
        return this.c;
    }

    private int a(com.d.e.v vVar, int i) {
        return a().a(vVar, i, 1) + a().a(vVar, i, 2);
    }

    public final void a(com.d.e.v vVar, int i, boolean z) {
        if (!this.j) {
            com.d.c.a.c i2 = a().i();
            if (i2 == com.d.c.a.c.ar) {
                int a2 = a(vVar, i) + a(vVar, d(z));
                this.k = a2;
                this.l = a2;
            } else if (i2 == com.d.c.a.c.aB) {
                b(vVar, i, false);
                this.l = this.k;
            } else if (i2 == com.d.c.a.c.aD) {
                a(vVar, i, false, true);
                b(vVar, i, false);
            } else if (i2 == com.d.c.a.c.aC) {
                a(vVar, i, z, false);
                b(vVar, i, true);
            } else {
                this.k = a(vVar, i, z, false);
            }
            this.l = Math.min(this.k, this.l);
            this.j = true;
        }
    }

    public final int k() {
        return this.k;
    }

    public final int l() {
        return this.l;
    }

    public final int m() {
        return this.m;
    }

    public final String n() {
        return this.n;
    }

    public final void b(String str) {
        this.n = str;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("InlineBox: ");
        if (h() != null) {
            sb.append("<");
            sb.append(h().getNodeName());
            sb.append("> ");
        } else {
            sb.append("(anonymous) ");
        }
        if (n() != null) {
            sb.append(':');
            sb.append(n());
            sb.append(' ');
        }
        if (g() || f()) {
            sb.append("(");
            if (g()) {
                sb.append("S");
            }
            if (f()) {
                sb.append("E");
            }
            sb.append(") ");
        }
        a(sb);
        sb.append("(");
        sb.append(r());
        sb.append(") ");
        return sb.toString();
    }

    private void a(StringBuilder sb) {
        if (a().F()) {
            sb.append("(relative) ");
        }
        if (a().B()) {
            sb.append("(fixed) ");
        }
        if (a().A()) {
            sb.append("(absolute) ");
        }
        if (a().C()) {
            sb.append("(floated) ");
        }
    }

    private String r() {
        if (this.c == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.c.length() && i < 40; i++) {
            char charAt = this.c.charAt(i);
            if (charAt == '\n') {
                sb.append(' ');
            } else {
                sb.append(charAt);
            }
        }
        if (sb.length() == 40) {
            sb.append("...");
        }
        return sb.toString();
    }

    public final e o() {
        return this.i;
    }

    public final void a(e eVar) {
        this.i = eVar;
    }

    public final void p() {
        this.c = "";
        this.f1370b = "";
    }

    public final Text q() {
        return this.o;
    }
}
