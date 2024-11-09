package org.a.c.h.e;

import com.a.a.a.am;
import java.io.Closeable;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:org/a/c/h/e/o.class */
public abstract class o implements org.a.c.h.a.c {

    /* renamed from: a, reason: collision with root package name */
    protected final ac f4552a;

    /* renamed from: b, reason: collision with root package name */
    private Map<Integer, Float> f4553b;
    private float c;
    private final Map<Integer, Float> d = new HashMap();
    private final Map<Integer, org.a.c.i.f> e = new HashMap();
    private float[] f = {880.0f, -1000.0f};
    private org.a.c.b.d g;
    private v h;

    public abstract org.a.c.i.d d();

    public abstract org.a.b.h.a e();

    public abstract float b(int i);

    public abstract boolean g();

    public abstract int c(int i);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract byte[] d(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public o(org.a.c.b.d dVar, ac acVar) {
        this.g = dVar;
        this.f4552a = acVar;
        j();
        k();
    }

    private void j() {
        this.f4553b = new HashMap();
        org.a.c.b.b a2 = this.g.a(org.a.c.b.j.dX);
        if (a2 instanceof org.a.c.b.a) {
            org.a.c.b.a aVar = (org.a.c.b.a) a2;
            int b2 = aVar.b();
            int i = 0;
            while (i < b2) {
                int i2 = i;
                int i3 = i + 1;
                org.a.c.b.l lVar = (org.a.c.b.l) aVar.a(i2);
                i = i3 + 1;
                org.a.c.b.b a3 = aVar.a(i3);
                if (a3 instanceof org.a.c.b.a) {
                    org.a.c.b.a aVar2 = (org.a.c.b.a) a3;
                    int c = lVar.c();
                    int b3 = aVar2.b();
                    for (int i4 = 0; i4 < b3; i4++) {
                        this.f4553b.put(Integer.valueOf(c + i4), Float.valueOf(((org.a.c.b.l) aVar2.a(i4)).a()));
                    }
                } else {
                    org.a.c.b.l lVar2 = (org.a.c.b.l) a3;
                    i++;
                    org.a.c.b.l lVar3 = (org.a.c.b.l) aVar.a(i);
                    int c2 = lVar.c();
                    int c3 = lVar2.c();
                    float a4 = lVar3.a();
                    for (int i5 = c2; i5 <= c3; i5++) {
                        this.f4553b.put(Integer.valueOf(i5), Float.valueOf(a4));
                    }
                }
            }
        }
    }

    private void k() {
        org.a.c.b.b a2 = this.g.a(org.a.c.b.j.aN);
        if (a2 instanceof org.a.c.b.a) {
            org.a.c.b.a aVar = (org.a.c.b.a) a2;
            org.a.c.b.b a3 = aVar.a(0);
            org.a.c.b.b a4 = aVar.a(1);
            if ((a3 instanceof org.a.c.b.l) && (a4 instanceof org.a.c.b.l)) {
                this.f[0] = ((org.a.c.b.l) a3).a();
                this.f[1] = ((org.a.c.b.l) a4).a();
            }
        }
        org.a.c.b.b a5 = this.g.a(org.a.c.b.j.dY);
        if (a5 instanceof org.a.c.b.a) {
            org.a.c.b.a aVar2 = (org.a.c.b.a) a5;
            int i = 0;
            while (i < aVar2.b()) {
                org.a.c.b.l lVar = (org.a.c.b.l) aVar2.a(i);
                int i2 = i + 1;
                org.a.c.b.b a6 = aVar2.a(i2);
                if (a6 instanceof org.a.c.b.a) {
                    org.a.c.b.a aVar3 = (org.a.c.b.a) a6;
                    int i3 = 0;
                    while (i3 < aVar3.b()) {
                        int c = lVar.c() + (i3 / 3);
                        org.a.c.b.l lVar2 = (org.a.c.b.l) aVar3.a(i3);
                        int i4 = i3 + 1;
                        org.a.c.b.l lVar3 = (org.a.c.b.l) aVar3.a(i4);
                        int i5 = i4 + 1;
                        org.a.c.b.l lVar4 = (org.a.c.b.l) aVar3.a(i5);
                        this.d.put(Integer.valueOf(c), Float.valueOf(lVar2.a()));
                        this.e.put(Integer.valueOf(c), new org.a.c.i.f(lVar3.a(), lVar4.a()));
                        i3 = i5 + 1;
                    }
                } else {
                    int c2 = lVar.c();
                    int c3 = ((org.a.c.b.l) a6).c();
                    int i6 = i2 + 1;
                    org.a.c.b.l lVar5 = (org.a.c.b.l) aVar2.a(i6);
                    int i7 = i6 + 1;
                    org.a.c.b.l lVar6 = (org.a.c.b.l) aVar2.a(i7);
                    i2 = i7 + 1;
                    org.a.c.b.l lVar7 = (org.a.c.b.l) aVar2.a(i2);
                    for (int i8 = c2; i8 <= c3; i8++) {
                        this.d.put(Integer.valueOf(i8), Float.valueOf(lVar5.a()));
                        this.e.put(Integer.valueOf(i8), new org.a.c.i.f(lVar6.a(), lVar7.a()));
                    }
                }
                i = i2 + 1;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // org.a.c.h.a.c
    /* renamed from: l, reason: merged with bridge method [inline-methods] */
    public org.a.c.b.d f() {
        return this.g;
    }

    public final String a() {
        return this.g.g(org.a.c.b.j.v);
    }

    public final String b() {
        return a();
    }

    public final v c() {
        org.a.c.b.d dVar;
        if (this.h == null && (dVar = (org.a.c.b.d) this.g.a(org.a.c.b.j.bg)) != null) {
            this.h = new v(dVar);
        }
        return this.h;
    }

    private float m() {
        if (this.c == 0.0f) {
            org.a.c.b.b a2 = this.g.a(org.a.c.b.j.aM);
            if (a2 instanceof org.a.c.b.l) {
                this.c = ((org.a.c.b.l) a2).a();
            } else {
                this.c = 1000.0f;
            }
        }
        return this.c;
    }

    private float e(int i) {
        Float f = this.f4553b.get(Integer.valueOf(i));
        Float f2 = f;
        if (f == null) {
            f2 = Float.valueOf(m());
        }
        return f2.floatValue();
    }

    public final float a(int i) {
        return e(c(i));
    }

    public final t h() {
        org.a.c.b.b a2 = this.g.a(org.a.c.b.j.Z);
        if (a2 instanceof org.a.c.b.d) {
            return new t((org.a.c.b.d) a2);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int[] i() {
        int[] iArr = null;
        org.a.c.b.b a2 = this.g.a(org.a.c.b.j.X);
        if (a2 instanceof org.a.c.b.p) {
            org.a.c.b.g k = ((org.a.c.b.p) a2).k();
            byte[] a3 = am.a((InputStream) k);
            am.a((Closeable) k);
            int length = a3.length / 2;
            iArr = new int[length];
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                iArr[i2] = ((a3[i] & 255) << 8) | (a3[i + 1] & 255);
                i += 2;
            }
        }
        return iArr;
    }
}
