package org.a.c.f;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import org.a.c.b.m;
import org.a.c.b.n;
import org.a.c.b.s;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:org/a/c/f/a.class */
public abstract class a {
    private final CharsetDecoder d = org.a.c.i.a.f.newDecoder();

    /* renamed from: a, reason: collision with root package name */
    protected final k f4429a;

    /* renamed from: b, reason: collision with root package name */
    protected org.a.c.b.e f4430b;
    private static int c = Long.toString(Long.MAX_VALUE).length();
    private static final org.a.a.a.a e = org.a.a.a.c.a(a.class);

    public a(k kVar) {
        this.f4429a = kVar;
    }

    private static boolean a(char c2) {
        if (d(c2)) {
            return true;
        }
        if (c2 < 'a' || c2 > 'f') {
            return c2 >= 'A' && c2 <= 'F';
        }
        return true;
    }

    private org.a.c.b.b p() {
        long b2 = this.f4429a.b();
        org.a.c.b.b f = f();
        l();
        if (!(f instanceof org.a.c.b.l) || !k()) {
            return f;
        }
        long b3 = this.f4429a.b();
        org.a.c.b.b f2 = f();
        l();
        b('R');
        if (!(f instanceof org.a.c.b.i)) {
            new StringBuilder("expected number, actual=").append(f).append(" at offset ").append(b2);
            return org.a.c.b.k.f4371a;
        }
        if (!(f2 instanceof org.a.c.b.i)) {
            new StringBuilder("expected number, actual=").append(f).append(" at offset ").append(b3);
            return org.a.c.b.k.f4371a;
        }
        return a(new n(((org.a.c.b.i) f).b(), ((org.a.c.b.i) f2).c()));
    }

    private org.a.c.b.b a(n nVar) {
        if (this.f4430b == null) {
            throw new IOException("object reference " + nVar + " at offset " + this.f4429a.b() + " in content stream");
        }
        return this.f4430b.a(nVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final org.a.c.b.d a() {
        b('<');
        b('<');
        l();
        org.a.c.b.d dVar = new org.a.c.b.d();
        boolean z = false;
        while (!z) {
            l();
            char c2 = (char) this.f4429a.c();
            if (c2 == '>') {
                z = true;
            } else if (c2 != '/') {
                new StringBuilder("Invalid dictionary, found: '").append(c2).append("' but expected: '/' at offset ").append(this.f4429a.b());
                if (q()) {
                    return dVar;
                }
            } else {
                a(dVar);
            }
        }
        b('>');
        b('>');
        return dVar;
    }

    private boolean q() {
        int i;
        int a2 = this.f4429a.a();
        while (true) {
            i = a2;
            if (i == -1 || i == 47 || i == 62) {
                break;
            }
            if (i == 101 && this.f4429a.a() == 110 && this.f4429a.a() == 100) {
                int a3 = this.f4429a.a();
                boolean z = a3 == 115 && this.f4429a.a() == 116 && this.f4429a.a() == 114 && this.f4429a.a() == 101 && this.f4429a.a() == 97 && this.f4429a.a() == 109;
                boolean z2 = z;
                boolean z3 = !z && a3 == 111 && this.f4429a.a() == 98 && this.f4429a.a() == 106;
                if (z2 || z3) {
                    return true;
                }
            }
            a2 = this.f4429a.a();
        }
        if (i == -1) {
            return true;
        }
        this.f4429a.a(i);
        return false;
    }

    private void a(org.a.c.b.d dVar) {
        org.a.c.b.j e2 = e();
        org.a.c.b.b p = p();
        l();
        if (((char) this.f4429a.c()) == 'd') {
            String g = g();
            if (!g.equals("def")) {
                this.f4429a.b(g.getBytes(org.a.c.i.a.d));
            } else {
                l();
            }
        }
        if (p == null) {
            new StringBuilder("Bad dictionary declaration at offset ").append(this.f4429a.b());
        } else {
            p.a(true);
            dVar.a(e2, p);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void b() {
        int i;
        int a2 = this.f4429a.a();
        while (true) {
            i = a2;
            if (32 != i) {
                break;
            } else {
                a2 = this.f4429a.a();
            }
        }
        if (13 == i) {
            int a3 = this.f4429a.a();
            if (10 != a3) {
                this.f4429a.a(a3);
                return;
            }
            return;
        }
        if (10 != i) {
            this.f4429a.a(i);
        }
    }

    private int e(int i) {
        int i2 = i;
        byte[] bArr = new byte[3];
        int a2 = this.f4429a.a(bArr);
        if (a2 == 3 && bArr[0] == 13 && ((bArr[1] == 10 && bArr[2] == 47) || bArr[2] == 62 || bArr[1] == 47 || bArr[1] == 62)) {
            i2 = 0;
        }
        if (a2 > 0) {
            this.f4429a.b(bArr, 0, a2);
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:35:0x00a7. Please report as an issue. */
    public final s c() {
        int i;
        int a2;
        char a3 = (char) this.f4429a.a();
        if (a3 == '<') {
            return r();
        }
        if (a3 != '(') {
            throw new IOException("parseCOSString string should start with '(' or '<' and not '" + a3 + "' at offset " + this.f4429a.b());
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 1;
        int a4 = this.f4429a.a();
        while (true) {
            i = a4;
            if (i2 > 0 && i != -1) {
                char c2 = (char) i;
                int i3 = -2;
                if (c2 == ')') {
                    int e2 = e(i2 - 1);
                    i2 = e2;
                    if (e2 != 0) {
                        byteArrayOutputStream.write(c2);
                    }
                } else if (c2 == '(') {
                    i2++;
                    byteArrayOutputStream.write(c2);
                } else if (c2 == '\\') {
                    char a5 = (char) this.f4429a.a();
                    switch (a5) {
                        case '\n':
                        case '\r':
                            do {
                                a2 = this.f4429a.a();
                                if (f(a2)) {
                                }
                                i3 = a2;
                                break;
                            } while (a2 != -1);
                            i3 = a2;
                        case '(':
                        case '\\':
                            byteArrayOutputStream.write(a5);
                            break;
                        case ')':
                            int e3 = e(i2);
                            i2 = e3;
                            if (e3 != 0) {
                                byteArrayOutputStream.write(a5);
                                break;
                            } else {
                                byteArrayOutputStream.write(92);
                                break;
                            }
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                            StringBuilder sb = new StringBuilder();
                            sb.append(a5);
                            int a6 = this.f4429a.a();
                            char c3 = (char) a6;
                            if (c3 >= '0' && c3 <= '7') {
                                sb.append(c3);
                                int a7 = this.f4429a.a();
                                char c4 = (char) a7;
                                if (c4 >= '0' && c4 <= '7') {
                                    sb.append(c4);
                                } else {
                                    i3 = a7;
                                }
                            } else {
                                i3 = a6;
                            }
                            try {
                                byteArrayOutputStream.write(Integer.parseInt(sb.toString(), 8));
                                break;
                            } catch (NumberFormatException e4) {
                                throw new IOException("Error: Expected octal character, actual='" + ((Object) sb) + "'", e4);
                            }
                            break;
                        case 'b':
                            byteArrayOutputStream.write(8);
                            break;
                        case 'f':
                            byteArrayOutputStream.write(12);
                            break;
                        case 'n':
                            byteArrayOutputStream.write(10);
                            break;
                        case 'r':
                            byteArrayOutputStream.write(13);
                            break;
                        case 't':
                            byteArrayOutputStream.write(9);
                            break;
                        default:
                            byteArrayOutputStream.write(a5);
                            break;
                    }
                } else {
                    byteArrayOutputStream.write(c2);
                }
                if (i3 != -2) {
                    a4 = i3;
                } else {
                    a4 = this.f4429a.a();
                }
            }
        }
        if (i != -1) {
            this.f4429a.a(i);
        }
        return new s(byteArrayOutputStream.toByteArray());
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0099, code lost:            return org.a.c.b.s.a(r0.toString());     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private org.a.c.b.s r() {
        /*
            r4 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = r0
            r1.<init>()
            r5 = r0
        L8:
            r0 = r4
            org.a.c.f.k r0 = r0.f4429a
            int r0 = r0.a()
            r1 = r0
            r6 = r1
            char r0 = (char) r0
            boolean r0 = a(r0)
            if (r0 == 0) goto L24
            r0 = r5
            r1 = r6
            char r1 = (char) r1
            java.lang.StringBuilder r0 = r0.append(r1)
            goto L8
        L24:
            r0 = r6
            r1 = 62
            if (r0 == r1) goto L92
            r0 = r6
            if (r0 >= 0) goto L38
            java.io.IOException r0 = new java.io.IOException
            r1 = r0
            java.lang.String r2 = "Missing closing bracket for hex string. Reached EOS."
            r1.<init>(r2)
            throw r0
        L38:
            r0 = r6
            r1 = 32
            if (r0 == r1) goto L8
            r0 = r6
            r1 = 10
            if (r0 == r1) goto L8
            r0 = r6
            r1 = 9
            if (r0 == r1) goto L8
            r0 = r6
            r1 = 13
            if (r0 == r1) goto L8
            r0 = r6
            r1 = 8
            if (r0 == r1) goto L8
            r0 = r6
            r1 = 12
            if (r0 == r1) goto L8
            r0 = r5
            int r0 = r0.length()
            r1 = 2
            int r0 = r0 % r1
            if (r0 == 0) goto L70
            r0 = r5
            r1 = r0
            int r1 = r1.length()
            r2 = 1
            int r1 = r1 - r2
            java.lang.StringBuilder r0 = r0.deleteCharAt(r1)
        L70:
            r0 = r4
            org.a.c.f.k r0 = r0.f4429a
            int r0 = r0.a()
            r1 = r0
            r6 = r1
            r1 = 62
            if (r0 == r1) goto L84
            r0 = r6
            if (r0 >= 0) goto L70
        L84:
            r0 = r6
            if (r0 >= 0) goto L92
            java.io.IOException r0 = new java.io.IOException
            r1 = r0
            java.lang.String r2 = "Missing closing bracket for hex string. Reached EOS."
            r1.<init>(r2)
            throw r0
        L92:
            r0 = r5
            java.lang.String r0 = r0.toString()
            org.a.c.b.s r0 = org.a.c.b.s.a(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.a.c.f.a.r():org.a.c.b.s");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0114, code lost:            r6.f4429a.a();        l();     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0123, code lost:            return r0;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final org.a.c.b.a d() {
        /*
            Method dump skipped, instructions count: 292
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.a.c.f.a.d():org.a.c.b.a");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean a(int i) {
        return i == 32 || i == 13 || i == 10 || i == 9 || i == 62 || i == 60 || i == 91 || i == 47 || i == 93 || i == 41 || i == 40 || i == 0 || i == 12;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final org.a.c.b.j e() {
        String str;
        b('/');
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int a2 = this.f4429a.a();
        while (a2 != -1) {
            int i = a2;
            if (i == 35) {
                int a3 = this.f4429a.a();
                int a4 = this.f4429a.a();
                if (a((char) a3) && a((char) a4)) {
                    String sb = new StringBuilder().append((char) a3).append((char) a4).toString();
                    try {
                        byteArrayOutputStream.write(Integer.parseInt(sb, 16));
                        a2 = this.f4429a.a();
                    } catch (NumberFormatException e2) {
                        throw new IOException("Error: expected hex digit, actual='" + sb + "'", e2);
                    }
                } else {
                    if (a4 == -1 || a3 == -1) {
                        a2 = -1;
                        break;
                    }
                    this.f4429a.a(a4);
                    a2 = a3;
                    byteArrayOutputStream.write(i);
                }
            } else {
                if (a(i)) {
                    break;
                }
                byteArrayOutputStream.write(i);
                a2 = this.f4429a.a();
            }
        }
        if (a2 != -1) {
            this.f4429a.a(a2);
        }
        if (a(byteArrayOutputStream.toByteArray())) {
            str = new String(byteArrayOutputStream.toByteArray(), org.a.c.i.a.f);
        } else {
            str = new String(byteArrayOutputStream.toByteArray(), org.a.c.i.a.e);
        }
        return org.a.c.b.j.a(str);
    }

    private boolean a(byte[] bArr) {
        try {
            this.d.decode(ByteBuffer.wrap(bArr));
            return true;
        } catch (CharacterCodingException unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final org.a.c.b.b f() {
        org.a.c.b.b bVar = null;
        l();
        char c2 = (char) this.f4429a.c();
        switch (c2) {
            case '(':
                bVar = c();
                break;
            case '/':
                bVar = e();
                break;
            case '<':
                int a2 = this.f4429a.a();
                char c3 = (char) this.f4429a.c();
                this.f4429a.a(a2);
                if (c3 == '<') {
                    bVar = a();
                    l();
                    break;
                } else {
                    bVar = c();
                    break;
                }
            case 'R':
                this.f4429a.a();
                bVar = new m(null);
                break;
            case '[':
                bVar = d();
                break;
            case 'f':
                String str = new String(this.f4429a.b(5), org.a.c.i.a.d);
                if (str.equals("false")) {
                    bVar = org.a.c.b.c.f4358b;
                    break;
                } else {
                    throw new IOException("expected false actual='" + str + "' " + this.f4429a + "' at offset " + this.f4429a.b());
                }
            case 'n':
                a("null");
                bVar = org.a.c.b.k.f4371a;
                break;
            case 't':
                String str2 = new String(this.f4429a.b(4), org.a.c.i.a.d);
                if (str2.equals("true")) {
                    bVar = org.a.c.b.c.f4357a;
                    break;
                } else {
                    throw new IOException("expected true actual='" + str2 + "' " + this.f4429a + "' at offset " + this.f4429a.b());
                }
            case 65535:
                return null;
            default:
                if (Character.isDigit(c2) || c2 == '-' || c2 == '+' || c2 == '.') {
                    StringBuilder sb = new StringBuilder();
                    int a3 = this.f4429a.a();
                    while (true) {
                        int i = a3;
                        char c4 = (char) a3;
                        if (Character.isDigit(c4) || c4 == '-' || c4 == '+' || c4 == '.' || c4 == 'E' || c4 == 'e') {
                            sb.append(c4);
                            a3 = this.f4429a.a();
                        } else {
                            if (i != -1) {
                                this.f4429a.a(i);
                            }
                            bVar = org.a.c.b.l.a(sb.toString());
                            break;
                        }
                    }
                } else {
                    String g = g();
                    if (!g.isEmpty()) {
                        if ("endobj".equals(g) || "endstream".equals(g)) {
                            this.f4429a.b(g.getBytes(org.a.c.i.a.d));
                            break;
                        }
                    } else {
                        int c5 = this.f4429a.c();
                        throw new IOException("Unknown dir object c='" + c2 + "' cInt=" + ((int) c2) + " peek='" + ((char) c5) + "' peekInt=" + c5 + " at offset " + this.f4429a.b());
                    }
                }
                break;
        }
        return bVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String g() {
        int i;
        l();
        StringBuilder sb = new StringBuilder();
        int a2 = this.f4429a.a();
        while (true) {
            i = a2;
            if (a((int) ((char) i)) || i == -1) {
                break;
            }
            sb.append((char) i);
            a2 = this.f4429a.a();
        }
        if (i != -1) {
            this.f4429a.a(i);
        }
        return sb.toString();
    }

    private void a(String str) {
        a(str.toCharArray());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(char[] cArr) {
        l();
        for (char c2 : cArr) {
            if (this.f4429a.a() != c2) {
                throw new IOException("Expected string '" + new String(cArr) + "' but missed at character '" + c2 + "' at offset " + this.f4429a.b());
            }
        }
        l();
    }

    private void b(char c2) {
        char a2 = (char) this.f4429a.a();
        if (a2 != c2) {
            throw new IOException("expected='" + c2 + "' actual='" + a2 + "' at offset " + this.f4429a.b());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean b(int i) {
        return i == 93;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String h() {
        int a2;
        if (this.f4429a.d()) {
            throw new IOException("Error: End-of-File, expected line");
        }
        StringBuilder sb = new StringBuilder(11);
        while (true) {
            a2 = this.f4429a.a();
            if (a2 == -1 || f(a2)) {
                break;
            }
            sb.append((char) a2);
        }
        if (h(a2) && g(this.f4429a.c())) {
            this.f4429a.a();
        }
        return sb.toString();
    }

    private boolean f(int i) {
        return g(i) || h(i);
    }

    private static boolean g(int i) {
        return 10 == i;
    }

    private static boolean h(int i) {
        return 13 == i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean i() {
        return c(this.f4429a.c());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean c(int i) {
        return i == 0 || i == 9 || i == 12 || i == 10 || i == 13 || i == 32;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean j() {
        return i(this.f4429a.c());
    }

    private static boolean i(int i) {
        return 32 == i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean k() {
        return d(this.f4429a.c());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean d(int i) {
        return i >= 48 && i <= 57;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void l() {
        int a2 = this.f4429a.a();
        while (true) {
            if (!c(a2) && a2 != 37) {
                break;
            }
            if (a2 == 37) {
                int a3 = this.f4429a.a();
                while (true) {
                    a2 = a3;
                    if (!f(a2) && a2 != -1) {
                        a3 = this.f4429a.a();
                    }
                }
            } else {
                a2 = this.f4429a.a();
            }
        }
        if (a2 != -1) {
            this.f4429a.a(a2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long m() {
        long o = o();
        if (o < 0 || o >= 10000000000L) {
            throw new IOException("Object Number '" + o + "' has more than 10 digits or is negative");
        }
        return o;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int n() {
        int s = s();
        if (s < 0 || s > User32.HWND_BROADCAST) {
            throw new IOException("Generation Number '" + s + "' has more than 5 digits");
        }
        return s;
    }

    private int s() {
        l();
        StringBuilder t = t();
        try {
            return Integer.parseInt(t.toString());
        } catch (NumberFormatException e2) {
            this.f4429a.b(t.toString().getBytes(org.a.c.i.a.d));
            throw new IOException("Error: Expected an integer type at offset " + this.f4429a.b() + ", instead got '" + ((Object) t) + "'", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long o() {
        l();
        StringBuilder t = t();
        try {
            return Long.parseLong(t.toString());
        } catch (NumberFormatException e2) {
            this.f4429a.b(t.toString().getBytes(org.a.c.i.a.d));
            throw new IOException("Error: Expected a long type at offset " + this.f4429a.b() + ", instead got '" + ((Object) t) + "'", e2);
        }
    }

    private StringBuilder t() {
        StringBuilder sb = new StringBuilder();
        do {
            int a2 = this.f4429a.a();
            if (a2 != 32 && a2 != 10 && a2 != 13 && a2 != 60 && a2 != 91 && a2 != 40 && a2 != 0 && a2 != -1) {
                sb.append((char) a2);
            } else {
                if (a2 != -1) {
                    this.f4429a.a(a2);
                }
                return sb;
            }
        } while (sb.length() <= c);
        throw new IOException("Number '" + ((Object) sb) + "' is getting too long, stop reading at offset " + this.f4429a.b());
    }
}
