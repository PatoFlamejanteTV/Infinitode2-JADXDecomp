package com.c.b;

/* loaded from: infinitode-2.jar:com/c/b/f.class */
final class f extends h {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.c.b.h
    public final Object a(l lVar, com.c.a.a aVar) {
        a aVar2 = new a(this);
        aVar2.f916a = aVar.c(8);
        aVar2.f917b = aVar.c(16);
        aVar2.c = aVar.c(16);
        aVar2.d = aVar.c(6);
        aVar2.e = aVar.c(8);
        aVar2.f = aVar.c(4) + 1;
        if (aVar2.f916a <= 0 || aVar2.f917b <= 0 || aVar2.c <= 0 || aVar2.f <= 0) {
            return null;
        }
        for (int i = 0; i < aVar2.f; i++) {
            aVar2.g[i] = aVar.c(8);
            if (aVar2.g[i] < 0 || aVar2.g[i] >= lVar.h) {
                return null;
            }
        }
        return aVar2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.c.b.h
    public final Object a(e eVar, o oVar, Object obj) {
        l lVar = eVar.f914a;
        a aVar = (a) obj;
        b bVar = new b(this);
        bVar.c = aVar.f916a;
        bVar.f918a = lVar.c[oVar.f934a] / 2;
        bVar.f919b = aVar.c;
        bVar.e = aVar;
        bVar.f.a(bVar.f919b, bVar.c);
        float a2 = bVar.f919b / a((float) (aVar.f917b / 2.0d));
        bVar.d = new int[bVar.f918a];
        for (int i = 0; i < bVar.f918a; i++) {
            int floor = (int) Math.floor(a((float) (((aVar.f917b / 2.0d) / bVar.f918a) * i)) * a2);
            int i2 = floor;
            if (floor >= bVar.f919b) {
                i2 = bVar.f919b;
            }
            bVar.d[i] = i2;
        }
        return bVar;
    }

    private static float a(float f) {
        return (float) ((13.1d * Math.atan(7.4E-4d * f)) + (2.24d * Math.atan(f * f * 1.85E-8d)) + (1.0E-4d * f));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.c.b.h
    public final Object a(com.c.b.a aVar, Object obj, Object obj2) {
        b bVar = (b) obj;
        a aVar2 = bVar.e;
        float[] fArr = null;
        if (obj2 instanceof float[]) {
            fArr = (float[]) obj2;
        }
        int c = aVar.f905b.c(aVar2.d);
        if (c > 0) {
            float f = (c / ((1 << aVar2.d) - 1)) * aVar2.e;
            int c2 = aVar.f905b.c(o.a(aVar2.f));
            if (c2 != -1 && c2 < aVar2.f) {
                com.c.b.b bVar2 = aVar.k.e[aVar2.g[c2]];
                float f2 = 0.0f;
                if (fArr == null || fArr.length < bVar.c + 1) {
                    fArr = new float[bVar.c + 1];
                } else {
                    for (int i = 0; i < fArr.length; i++) {
                        fArr[i] = 0.0f;
                    }
                }
                int i2 = 0;
                while (true) {
                    int i3 = i2;
                    if (i3 < bVar.c) {
                        if (bVar2.c(fArr, i3, aVar.f905b, bVar2.f906a) != -1) {
                            i2 = i3 + bVar2.f906a;
                        } else {
                            return null;
                        }
                    } else {
                        int i4 = 0;
                        while (i4 < bVar.c) {
                            int i5 = 0;
                            while (i5 < bVar2.f906a) {
                                float[] fArr2 = fArr;
                                int i6 = i4;
                                fArr2[i6] = fArr2[i6] + f2;
                                i5++;
                                i4++;
                            }
                            f2 = fArr[i4 - 1];
                        }
                        fArr[bVar.c] = f;
                        return fArr;
                    }
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.c.b.h
    public final int a(com.c.b.a aVar, Object obj, Object obj2, float[] fArr) {
        b bVar = (b) obj;
        a aVar2 = bVar.e;
        if (obj2 != null) {
            float[] fArr2 = (float[]) obj2;
            o.a(fArr, bVar.d, bVar.f918a, bVar.f919b, fArr2, bVar.c, fArr2[bVar.c], aVar2.e);
            return 1;
        }
        for (int i = 0; i < bVar.f918a; i++) {
            fArr[i] = 0.0f;
        }
        return 0;
    }

    /* loaded from: infinitode-2.jar:com/c/b/f$a.class */
    class a {

        /* renamed from: a, reason: collision with root package name */
        int f916a;

        /* renamed from: b, reason: collision with root package name */
        int f917b;
        int c;
        int d;
        int e;
        int f;
        int[] g = new int[16];

        a(f fVar) {
        }
    }

    /* loaded from: infinitode-2.jar:com/c/b/f$b.class */
    class b {

        /* renamed from: a, reason: collision with root package name */
        int f918a;

        /* renamed from: b, reason: collision with root package name */
        int f919b;
        int c;
        int[] d;
        a e;
        n f = new n();

        b(f fVar) {
        }
    }
}
