package org.a.c.h.a.b;

import java.io.IOException;
import org.a.c.b.j;
import org.a.c.b.m;
import org.a.c.b.p;
import org.a.c.h.a.g;
import org.a.c.h.a.i;

/* loaded from: infinitode-2.jar:org/a/c/h/a/b/a.class */
public abstract class a implements org.a.c.h.a.c {

    /* renamed from: a, reason: collision with root package name */
    private i f4461a;

    /* renamed from: b, reason: collision with root package name */
    private org.a.c.b.d f4462b;
    private org.a.c.b.a c = null;
    private org.a.c.b.a d = null;
    private int e = -1;
    private int f = -1;

    public abstract int a();

    public abstract float[] a(float[] fArr);

    public a(org.a.c.b.b bVar) {
        this.f4461a = null;
        this.f4462b = null;
        if (bVar instanceof p) {
            this.f4461a = new i((p) bVar);
            this.f4461a.f().a(j.dN, (org.a.c.b.b) j.br);
        } else if (bVar instanceof org.a.c.b.d) {
            this.f4462b = (org.a.c.b.d) bVar;
        }
    }

    @Override // org.a.c.h.a.c
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public final org.a.c.b.d f() {
        if (this.f4461a != null) {
            return this.f4461a.f();
        }
        return this.f4462b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final i c() {
        return this.f4461a;
    }

    public static a a(org.a.c.b.b bVar) {
        if (bVar == j.bB) {
            return new f();
        }
        org.a.c.b.b bVar2 = bVar;
        if (bVar instanceof m) {
            bVar2 = ((m) bVar).a();
        }
        if (!(bVar2 instanceof org.a.c.b.d)) {
            throw new IOException("Error: Function must be a Dictionary, but is " + bVar2.getClass().getSimpleName());
        }
        org.a.c.b.d dVar = (org.a.c.b.d) bVar2;
        int j = dVar.j(j.bs);
        switch (j) {
            case 0:
                return new b(dVar);
            case 1:
            default:
                throw new IOException("Error: Unknown function type " + j);
            case 2:
                return new c(dVar);
            case 3:
                return new d(dVar);
            case 4:
                return new e(dVar);
        }
    }

    public final int d() {
        if (this.f == -1) {
            this.f = g().b() / 2;
        }
        return this.f;
    }

    public final g a(int i) {
        return new g(g(), i);
    }

    public final int e() {
        if (this.e == -1) {
            this.e = h().b() / 2;
        }
        return this.e;
    }

    public final g b(int i) {
        return new g(h(), i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final org.a.c.b.a g() {
        if (this.d == null) {
            this.d = (org.a.c.b.a) f().a(j.db);
        }
        return this.d;
    }

    private org.a.c.b.a h() {
        if (this.c == null) {
            this.c = (org.a.c.b.a) f().a(j.aI);
        }
        return this.c;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final float[] b(float[] fArr) {
        float[] fArr2;
        org.a.c.b.a g = g();
        if (g != null) {
            float[] d = g.d();
            int length = d.length / 2;
            fArr2 = new float[length];
            for (int i = 0; i < length; i++) {
                int i2 = i << 1;
                fArr2[i] = a(fArr[i], d[i2], d[i2 + 1]);
            }
        } else {
            fArr2 = fArr;
        }
        return fArr2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static float a(float f, float f2, float f3) {
        if (f < f2) {
            return f2;
        }
        if (f > f3) {
            return f3;
        }
        return f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static float a(float f, float f2, float f3, float f4, float f5) {
        return f4 + (((f - f2) * (f5 - f4)) / (f3 - f2));
    }

    public String toString() {
        return "FunctionType" + a();
    }
}
