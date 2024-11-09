package org.a.c.h.a.b;

import org.a.c.h.a.b.a.g;

/* loaded from: infinitode-2.jar:org/a/c/h/a/b/e.class */
public final class e extends a {

    /* renamed from: a, reason: collision with root package name */
    private static final g f4483a = new g();

    /* renamed from: b, reason: collision with root package name */
    private final org.a.c.h.a.b.a.d f4484b;

    public e(org.a.c.b.b bVar) {
        super(bVar);
        this.f4484b = org.a.c.h.a.b.a.e.a((CharSequence) new String(c().g(), "ISO-8859-1"));
    }

    @Override // org.a.c.h.a.b.a
    public final int a() {
        return 4;
    }

    @Override // org.a.c.h.a.b.a
    public final float[] a(float[] fArr) {
        org.a.c.h.a.b.a.a aVar = new org.a.c.h.a.b.a.a(f4483a);
        for (int i = 0; i < fArr.length; i++) {
            org.a.c.h.a.g b2 = b(i);
            aVar.a().push(Float.valueOf(a(fArr[i], b2.a(), b2.b())));
        }
        this.f4484b.a(aVar);
        int d = d();
        int size = aVar.a().size();
        if (size < d) {
            throw new IllegalStateException("The type 4 function returned " + size + " values but the Range entry indicates that " + d + " values be returned.");
        }
        float[] fArr2 = new float[d];
        for (int i2 = d - 1; i2 >= 0; i2--) {
            org.a.c.h.a.g a2 = a(i2);
            fArr2[i2] = aVar.e();
            fArr2[i2] = a(fArr2[i2], a2.a(), a2.b());
        }
        return fArr2;
    }
}
