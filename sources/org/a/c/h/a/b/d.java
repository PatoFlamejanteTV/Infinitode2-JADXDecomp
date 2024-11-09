package org.a.c.h.a.b;

import java.io.IOException;
import org.a.c.b.j;
import org.a.c.h.a.g;

/* loaded from: infinitode-2.jar:org/a/c/h/a/b/d.class */
public final class d extends a {

    /* renamed from: a, reason: collision with root package name */
    private org.a.c.b.a f4481a;

    /* renamed from: b, reason: collision with root package name */
    private org.a.c.b.a f4482b;
    private org.a.c.b.a c;
    private a[] d;
    private float[] e;

    public d(org.a.c.b.b bVar) {
        super(bVar);
        this.f4481a = null;
        this.f4482b = null;
        this.c = null;
        this.d = null;
        this.e = null;
    }

    @Override // org.a.c.h.a.b.a
    public final int a() {
        return 3;
    }

    @Override // org.a.c.h.a.b.a
    public final float[] a(float[] fArr) {
        a aVar = null;
        float f = fArr[0];
        g b2 = b(0);
        float a2 = a(f, b2.a(), b2.b());
        if (this.d == null) {
            org.a.c.b.a h = h();
            this.d = new a[h.b()];
            for (int i = 0; i < h.b(); i++) {
                this.d[i] = a.a(h.a(i));
            }
        }
        if (this.d.length == 1) {
            aVar = this.d[0];
            g c = c(0);
            a2 = a(a2, b2.a(), b2.b(), c.a(), c.b());
        } else {
            if (this.e == null) {
                this.e = i().d();
            }
            int length = this.e.length;
            float[] fArr2 = new float[length + 2];
            int length2 = fArr2.length;
            fArr2[0] = b2.a();
            fArr2[length2 - 1] = b2.b();
            System.arraycopy(this.e, 0, fArr2, 1, length);
            for (int i2 = 0; i2 < length2 - 1; i2++) {
                if (a2 >= fArr2[i2] && (a2 < fArr2[i2 + 1] || (i2 == length2 - 2 && a2 == fArr2[i2 + 1]))) {
                    aVar = this.d[i2];
                    g c2 = c(i2);
                    a2 = a(a2, fArr2[i2], fArr2[i2 + 1], c2.a(), c2.b());
                    break;
                }
            }
            if (aVar == null) {
                throw new IOException("partition not found in type 3 function");
            }
        }
        return b(aVar.a(new float[]{a2}));
    }

    private org.a.c.b.a h() {
        if (this.f4481a == null) {
            this.f4481a = (org.a.c.b.a) f().a(j.bt);
        }
        return this.f4481a;
    }

    private org.a.c.b.a i() {
        if (this.c == null) {
            this.c = (org.a.c.b.a) f().a(j.C);
        }
        return this.c;
    }

    private org.a.c.b.a j() {
        if (this.f4482b == null) {
            this.f4482b = (org.a.c.b.a) f().a(j.aP);
        }
        return this.f4482b;
    }

    private g c(int i) {
        return new g(j(), i);
    }
}
