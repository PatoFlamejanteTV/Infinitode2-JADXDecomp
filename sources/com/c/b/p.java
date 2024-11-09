package com.c.b;

/* loaded from: infinitode-2.jar:com/c/b/p.class */
final class p extends i {

    /* renamed from: b, reason: collision with root package name */
    private float[][] f936b = (float[][]) null;
    private int[] c = null;
    private int[] d = null;
    private Object[] e = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.c.b.i
    public final Object a(e eVar, o oVar, Object obj) {
        l lVar = eVar.f914a;
        b bVar = new b(this);
        a aVar = (a) obj;
        bVar.f940b = aVar;
        bVar.f939a = oVar;
        bVar.c = new Object[aVar.f937a];
        bVar.d = new Object[aVar.f937a];
        bVar.e = new Object[aVar.f937a];
        bVar.f = new k[aVar.f937a];
        bVar.g = new h[aVar.f937a];
        bVar.h = new j[aVar.f937a];
        for (int i = 0; i < aVar.f937a; i++) {
            int i2 = aVar.c[i];
            int i3 = aVar.d[i];
            int i4 = aVar.e[i];
            bVar.f[i] = k.f928a[lVar.l[i2]];
            bVar.c[i] = bVar.f[i].b();
            bVar.g[i] = h.f925a[lVar.n[i3]];
            bVar.d[i] = bVar.g[i].a(eVar, oVar, lVar.o[i3]);
            bVar.h[i] = j.f927a[lVar.p[i4]];
            bVar.e[i] = bVar.h[i].a(eVar, oVar, lVar.q[i4]);
        }
        return bVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.c.b.i
    public final Object a(l lVar, com.c.a.a aVar) {
        a aVar2 = new a(this);
        if (aVar.c(1) != 0) {
            aVar2.f937a = aVar.c(4) + 1;
        } else {
            aVar2.f937a = 1;
        }
        if (aVar.c(1) != 0) {
            aVar2.f = aVar.c(8) + 1;
            for (int i = 0; i < aVar2.f; i++) {
                int c = aVar.c(o.b(lVar.f929a));
                aVar2.g[i] = c;
                int c2 = aVar.c(o.b(lVar.f929a));
                aVar2.h[i] = c2;
                if (c < 0 || c2 < 0 || c == c2 || c >= lVar.f929a || c2 >= lVar.f929a) {
                    aVar2.a();
                    return null;
                }
            }
        }
        if (aVar.c(2) > 0) {
            aVar2.a();
            return null;
        }
        if (aVar2.f937a > 1) {
            for (int i2 = 0; i2 < lVar.f929a; i2++) {
                aVar2.f938b[i2] = aVar.c(4);
                if (aVar2.f938b[i2] >= aVar2.f937a) {
                    aVar2.a();
                    return null;
                }
            }
        }
        for (int i3 = 0; i3 < aVar2.f937a; i3++) {
            aVar2.c[i3] = aVar.c(8);
            if (aVar2.c[i3] >= lVar.e) {
                aVar2.a();
                return null;
            }
            aVar2.d[i3] = aVar.c(8);
            if (aVar2.d[i3] >= lVar.f) {
                aVar2.a();
                return null;
            }
            aVar2.e[i3] = aVar.c(8);
            if (aVar2.e[i3] >= lVar.g) {
                aVar2.a();
                return null;
            }
        }
        return aVar2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Type inference failed for: r1v16, types: [float[], float[][]] */
    @Override // com.c.b.i
    public final synchronized int a(com.c.b.a aVar, Object obj) {
        e eVar = aVar.k;
        l lVar = eVar.f914a;
        b bVar = (b) obj;
        a aVar2 = bVar.f940b;
        o oVar = bVar.f939a;
        int i = lVar.c[aVar.d];
        aVar.f = i;
        float[] fArr = eVar.c[aVar.d][aVar.c][aVar.e][oVar.f935b];
        if (this.f936b == null || this.f936b.length < lVar.f929a) {
            this.f936b = new float[lVar.f929a];
            this.d = new int[lVar.f929a];
            this.c = new int[lVar.f929a];
            this.e = new Object[lVar.f929a];
        }
        for (int i2 = 0; i2 < lVar.f929a; i2++) {
            float[] fArr2 = aVar.f904a[i2];
            int i3 = aVar2.f938b[i2];
            this.e[i2] = bVar.g[i3].a(aVar, bVar.d[i3], this.e[i2]);
            if (this.e[i2] != null) {
                this.d[i2] = 1;
            } else {
                this.d[i2] = 0;
            }
            for (int i4 = 0; i4 < i / 2; i4++) {
                fArr2[i4] = 0.0f;
            }
        }
        for (int i5 = 0; i5 < aVar2.f; i5++) {
            if (this.d[aVar2.g[i5]] != 0 || this.d[aVar2.h[i5]] != 0) {
                this.d[aVar2.g[i5]] = 1;
                this.d[aVar2.h[i5]] = 1;
            }
        }
        for (int i6 = 0; i6 < aVar2.f937a; i6++) {
            int i7 = 0;
            for (int i8 = 0; i8 < lVar.f929a; i8++) {
                if (aVar2.f938b[i8] == i6) {
                    if (this.d[i8] != 0) {
                        this.c[i7] = 1;
                    } else {
                        this.c[i7] = 0;
                    }
                    int i9 = i7;
                    i7++;
                    this.f936b[i9] = aVar.f904a[i8];
                }
            }
            bVar.h[i6].a(aVar, bVar.e[i6], this.f936b, this.c, i7);
        }
        for (int i10 = aVar2.f - 1; i10 >= 0; i10--) {
            float[] fArr3 = aVar.f904a[aVar2.g[i10]];
            float[] fArr4 = aVar.f904a[aVar2.h[i10]];
            for (int i11 = 0; i11 < i / 2; i11++) {
                float f = fArr3[i11];
                float f2 = fArr4[i11];
                if (f > 0.0f) {
                    if (f2 > 0.0f) {
                        fArr3[i11] = f;
                        fArr4[i11] = f - f2;
                    } else {
                        fArr4[i11] = f;
                        fArr3[i11] = f + f2;
                    }
                } else if (f2 > 0.0f) {
                    fArr3[i11] = f;
                    fArr4[i11] = f + f2;
                } else {
                    fArr4[i11] = f;
                    fArr3[i11] = f - f2;
                }
            }
        }
        for (int i12 = 0; i12 < lVar.f929a; i12++) {
            float[] fArr5 = aVar.f904a[i12];
            int i13 = aVar2.f938b[i12];
            bVar.g[i13].a(aVar, bVar.d[i13], this.e[i12], fArr5);
        }
        for (int i14 = 0; i14 < lVar.f929a; i14++) {
            float[] fArr6 = aVar.f904a[i14];
            ((q) eVar.d[aVar.d][0]).a(fArr6, fArr6);
        }
        for (int i15 = 0; i15 < lVar.f929a; i15++) {
            float[] fArr7 = aVar.f904a[i15];
            if (this.d[i15] != 0) {
                for (int i16 = 0; i16 < i; i16++) {
                    int i17 = i16;
                    fArr7[i17] = fArr7[i17] * fArr[i16];
                }
            } else {
                for (int i18 = 0; i18 < i; i18++) {
                    fArr7[i18] = 0.0f;
                }
            }
        }
        return 0;
    }

    /* loaded from: infinitode-2.jar:com/c/b/p$a.class */
    class a {

        /* renamed from: a, reason: collision with root package name */
        int f937a;
        int f;

        /* renamed from: b, reason: collision with root package name */
        int[] f938b = new int[256];
        int[] c = new int[16];
        int[] d = new int[16];
        int[] e = new int[16];
        int[] g = new int[256];
        int[] h = new int[256];

        a(p pVar) {
        }

        final void a() {
            this.f938b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.g = null;
            this.h = null;
        }
    }

    /* loaded from: infinitode-2.jar:com/c/b/p$b.class */
    class b {

        /* renamed from: a, reason: collision with root package name */
        o f939a;

        /* renamed from: b, reason: collision with root package name */
        a f940b;
        Object[] c;
        Object[] d;
        Object[] e;
        k[] f;
        h[] g;
        j[] h;

        b(p pVar) {
        }
    }
}
