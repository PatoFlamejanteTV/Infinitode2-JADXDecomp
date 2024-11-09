package org.a.b.g;

import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:org/a/b/g/e.class */
class e {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4343a = org.a.a.a.c.a(e.class);

    /* renamed from: b, reason: collision with root package name */
    private final ByteBuffer f4344b;
    private int d = 0;
    private b c = a((b) null);

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(byte[] bArr) {
        this.f4344b = ByteBuffer.wrap(bArr);
    }

    public final b a() {
        b bVar = this.c;
        this.c = a(bVar);
        return bVar;
    }

    public final b b() {
        return this.c;
    }

    private char c() {
        return (char) this.f4344b.get();
    }

    private b a(b bVar) {
        boolean z;
        do {
            z = false;
            while (this.f4344b.hasRemaining()) {
                char c = c();
                if (c == '%') {
                    f();
                } else {
                    if (c == '(') {
                        return g();
                    }
                    if (c == ')') {
                        throw new IOException("unexpected closing parenthesis");
                    }
                    if (c == '[') {
                        return new b(c, b.f);
                    }
                    if (c == '{') {
                        return new b(c, b.h);
                    }
                    if (c == ']') {
                        return new b(c, b.g);
                    }
                    if (c == '}') {
                        return new b(c, b.i);
                    }
                    if (c == '/') {
                        return new b(e(), b.c);
                    }
                    if (c == '<') {
                        if (c() == c) {
                            return new b("<<", b.k);
                        }
                        this.f4344b.position(this.f4344b.position() - 1);
                        return new b(c, b.f4338b);
                    }
                    if (c == '>') {
                        if (c() == c) {
                            return new b(">>", b.l);
                        }
                        this.f4344b.position(this.f4344b.position() - 1);
                        return new b(c, b.f4338b);
                    }
                    if (Character.isWhitespace(c)) {
                        z = true;
                    } else if (c == 0) {
                        z = true;
                    } else {
                        this.f4344b.position(this.f4344b.position() - 1);
                        b d = d();
                        if (d != null) {
                            return d;
                        }
                        String e = e();
                        if (e == null) {
                            throw new a("Could not read token at position " + this.f4344b.position());
                        }
                        if (!e.equals("RD") && !e.equals("-|")) {
                            return new b(e, b.f4338b);
                        }
                        if (bVar.b() == b.e) {
                            return a(bVar.c());
                        }
                        throw new IOException("expected INTEGER before -| or RD");
                    }
                }
            }
        } while (z);
        return null;
    }

    private b d() {
        char c;
        char c2;
        this.f4344b.mark();
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = null;
        char c3 = c();
        boolean z = false;
        if (c3 == '+' || c3 == '-') {
            sb.append(c3);
            c3 = c();
        }
        while (Character.isDigit(c3)) {
            sb.append(c3);
            c3 = c();
            z = true;
        }
        if (c3 == '.') {
            sb.append(c3);
            c = c();
        } else if (c3 == '#') {
            sb2 = sb;
            sb = new StringBuilder();
            c = c();
        } else {
            if (sb.length() == 0 || !z) {
                this.f4344b.reset();
                return null;
            }
            this.f4344b.position(this.f4344b.position() - 1);
            return new b(sb.toString(), b.e);
        }
        if (Character.isDigit(c)) {
            sb.append(c);
            char c4 = c();
            while (true) {
                c2 = c4;
                if (!Character.isDigit(c2)) {
                    break;
                }
                sb.append(c2);
                c4 = c();
            }
            if (c2 == 'E') {
                sb.append(c2);
                char c5 = c();
                char c6 = c5;
                if (c5 == '-') {
                    sb.append(c6);
                    c6 = c();
                }
                if (Character.isDigit(c6)) {
                    sb.append(c6);
                    char c7 = c();
                    while (true) {
                        char c8 = c7;
                        if (!Character.isDigit(c8)) {
                            break;
                        }
                        sb.append(c8);
                        c7 = c();
                    }
                } else {
                    this.f4344b.reset();
                    return null;
                }
            }
            this.f4344b.position(this.f4344b.position() - 1);
            if (sb2 != null) {
                return new b(Integer.valueOf(Integer.parseInt(sb.toString(), Integer.parseInt(sb2.toString()))).toString(), b.e);
            }
            return new b(sb.toString(), b.d);
        }
        this.f4344b.reset();
        return null;
    }

    private String e() {
        StringBuilder sb = new StringBuilder();
        while (this.f4344b.hasRemaining()) {
            this.f4344b.mark();
            char c = c();
            if (Character.isWhitespace(c) || c == '(' || c == ')' || c == '<' || c == '>' || c == '[' || c == ']' || c == '{' || c == '}' || c == '/' || c == '%') {
                this.f4344b.reset();
                break;
            }
            sb.append(c);
        }
        String sb2 = sb.toString();
        if (sb2.length() == 0) {
            return null;
        }
        return sb2;
    }

    private String f() {
        char c;
        StringBuilder sb = new StringBuilder();
        while (this.f4344b.hasRemaining() && (c = c()) != '\r' && c != '\n') {
            sb.append(c);
        }
        return sb.toString();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0123, code lost:            if (java.lang.Character.isDigit(r0) == false) goto L40;     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0126, code lost:            r0.append((char) java.lang.Integer.valueOf(java.lang.Integer.parseInt(java.lang.String.valueOf(new char[]{r0, c(), c()}), 8)).intValue());     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private org.a.b.g.b g() {
        /*
            Method dump skipped, instructions count: 363
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.a.b.g.e.g():org.a.b.g.b");
    }

    private b a(int i) {
        this.f4344b.get();
        byte[] bArr = new byte[i];
        this.f4344b.get(bArr);
        return new b(bArr, b.j);
    }
}
