package org.a.c.f;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:org/a/c/f/g.class */
public class g extends a {
    private static final org.a.a.a.a c = org.a.a.a.c.a(g.class);
    private final List<Object> d;
    private final byte[] e;

    public g(org.a.c.a.a aVar) {
        super(new d(aVar.a()));
        this.d = new ArrayList(100);
        this.e = new byte[10];
    }

    public g(byte[] bArr) {
        super(new d(new ByteArrayInputStream(bArr)));
        this.d = new ArrayList(100);
        this.e = new byte[10];
    }

    public final void p() {
        while (true) {
            Object r = r();
            if (r != null) {
                this.d.add(r);
            } else {
                return;
            }
        }
    }

    public final List<Object> q() {
        return this.d;
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x0255, code lost:            r0 = false;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object r() {
        /*
            Method dump skipped, instructions count: 1078
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.a.c.f.g.r():java.lang.Object");
    }

    private boolean a(k kVar) {
        int a2 = kVar.a(this.e, 0, 10);
        boolean z = true;
        int i = -1;
        int i2 = -1;
        if (a2 > 0) {
            for (int i3 = 0; i3 < a2; i3++) {
                byte b2 = this.e[i3];
                if ((b2 != 0 && b2 < 9) || (b2 > 10 && b2 < 32 && b2 != 13)) {
                    z = false;
                    break;
                }
                if (i == -1 && b2 != 0 && b2 != 9 && b2 != 32 && b2 != 10 && b2 != 13) {
                    i = i3;
                } else if (i != -1 && i2 == -1 && (b2 == 0 || b2 == 9 || b2 == 32 || b2 == 10 || b2 == 13)) {
                    i2 = i3;
                }
            }
            if (i2 != -1 && i != -1) {
                String str = new String(this.e, i, i2 - i);
                if (!"Q".equals(str) && !"EMC".equals(str) && !"S".equals(str)) {
                    z = false;
                }
            }
            if (a2 == 10) {
                if (i != -1 && i2 == -1) {
                    i2 = 10;
                }
                if (i2 != -1 && i != -1 && i2 - i > 3) {
                    z = false;
                }
            }
            kVar.b(this.e, 0, a2);
        }
        if (!z) {
            new StringBuilder("ignoring 'EI' assumed to be in the middle of inline image at stream offset ").append(kVar.b());
        }
        return z;
    }

    private String s() {
        l();
        StringBuilder sb = new StringBuilder(4);
        int c2 = this.f4429a.c();
        while (c2 != -1 && !c(c2) && !b(c2) && c2 != 91 && c2 != 60 && c2 != 40 && c2 != 47 && (c2 < 48 || c2 > 57)) {
            char a2 = (char) this.f4429a.a();
            c2 = this.f4429a.c();
            sb.append(a2);
            if (a2 == 'd' && (c2 == 48 || c2 == 49)) {
                sb.append((char) this.f4429a.a());
                c2 = this.f4429a.c();
            }
        }
        return sb.toString();
    }

    private static boolean e(int i) {
        return i == 10 || i == 13 || i == 32;
    }

    private boolean t() {
        return e(this.f4429a.c());
    }
}
