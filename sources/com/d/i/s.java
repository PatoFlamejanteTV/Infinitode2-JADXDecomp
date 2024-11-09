package com.d.i;

import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/d/i/s.class */
public final class s {

    /* renamed from: a, reason: collision with root package name */
    private r f1373a;

    /* renamed from: b, reason: collision with root package name */
    private int f1374b;
    private String c;
    private int d;
    private int e;
    private int f;
    private com.d.e.o g;
    private boolean h = false;
    private byte i;
    private float j;

    public final void a(byte b2) {
        this.i = b2;
    }

    public final byte a() {
        return this.i;
    }

    public final void a(float f) {
        this.j = f;
    }

    public final float b() {
        return this.j;
    }

    public final void a(com.d.e.v vVar) {
        if (!c() && this.c.charAt(this.e - 1) == ' ') {
            this.e--;
            com.d.d.r d = vVar.d();
            vVar.w();
            b(d.a(g().a().d(vVar), d()));
        }
    }

    public final boolean c() {
        return this.d == this.e && !this.h;
    }

    public final String d() {
        if (i() != null) {
            if (this.d == -1 || this.e == -1) {
                throw new RuntimeException("negative index in InlineBox");
            }
            if (this.e < this.d) {
                throw new RuntimeException("end is less than setStartStyle");
            }
            return i().substring(this.d, this.e);
        }
        throw new RuntimeException("No master text set!");
    }

    public final void a(int i, int i2) {
        if (i2 < i) {
            com.d.m.k.b((Object) ("setting substring to: " + i + SequenceUtils.SPACE + i2));
            throw new RuntimeException("set substring length too long: " + this);
        }
        if (i2 < 0 || i < 0) {
            throw new RuntimeException("Trying to set negative index to inline box");
        }
        this.d = i;
        this.e = i2;
        if (this.e > 0 && this.c.charAt(this.e - 1) == '\n') {
            this.h = true;
            this.e--;
        }
    }

    private String i() {
        return this.c;
    }

    public final void a(String str) {
        this.c = str;
    }

    public final int e() {
        return this.f1374b;
    }

    public final void a(int i) {
        this.f1374b = i;
    }

    public final int f() {
        return this.f;
    }

    public final void b(int i) {
        this.f = i;
    }

    public final void a(ab abVar) {
        abVar.p().a(abVar, this);
    }

    public final r g() {
        return this.f1373a;
    }

    public final void a(r rVar) {
        this.f1373a = rVar;
    }

    public final boolean h() {
        return this.g != null;
    }

    public final void a(com.d.e.o oVar) {
        this.g = oVar;
    }

    public final void b(ab abVar) {
        String a2 = this.g.a().a(abVar, this.g.b(), this);
        this.d = 0;
        this.e = a2.length();
        this.c = a2;
        com.d.d.r f = abVar.f();
        abVar.q();
        this.f = f.a(g().a().d(abVar), a2);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("InlineText: ");
        if (this.h || h()) {
            sb.append("(");
            if (this.h) {
                sb.append('L');
            }
            if (h()) {
                sb.append('F');
            }
            sb.append(") ");
        }
        sb.append('(');
        sb.append(d());
        sb.append(')');
        return sb.toString();
    }

    public final void a(h hVar) {
        if (b() != 0.0f) {
            return;
        }
        String d = d();
        int length = d.length();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = d.charAt(i3);
            if (charAt == ' ' || charAt == 160 || charAt == 12288) {
                i++;
            } else {
                i2++;
            }
        }
        hVar.a(hVar.a() + i);
        hVar.b(hVar.b() + i2);
    }

    public final float a(t tVar) {
        float f;
        float b2;
        if (b() != 0.0f) {
            return 0.0f;
        }
        String d = d();
        int length = d.length();
        float f2 = 0.0f;
        for (int i = 0; i < length; i++) {
            char charAt = d.charAt(i);
            if (charAt == ' ' || charAt == 160 || charAt == 12288) {
                f = f2;
                b2 = tVar.b();
            } else {
                f = f2;
                b2 = tVar.a();
            }
            f2 = f + b2;
        }
        return f2;
    }
}
