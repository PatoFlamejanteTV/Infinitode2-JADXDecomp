package com.c.b;

/* loaded from: infinitode-2.jar:com/c/b/s.class */
class s extends j {

    /* renamed from: b, reason: collision with root package name */
    private static int[][][] f943b = new int[2];
    private static int[][] c = (int[][]) null;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.c.b.j
    public final Object a(l lVar, com.c.a.a aVar) {
        int i = 0;
        a aVar2 = new a(this);
        aVar2.f944a = aVar.c(24);
        aVar2.f945b = aVar.c(24);
        aVar2.c = aVar.c(24) + 1;
        aVar2.d = aVar.c(6) + 1;
        aVar2.e = aVar.c(8);
        for (int i2 = 0; i2 < aVar2.d; i2++) {
            int c2 = aVar.c(3);
            if (aVar.c(1) != 0) {
                c2 |= aVar.c(5) << 3;
            }
            aVar2.f[i2] = c2;
            i += o.c(c2);
        }
        for (int i3 = 0; i3 < i; i3++) {
            aVar2.g[i3] = aVar.c(8);
        }
        if (aVar2.e >= lVar.h) {
            return null;
        }
        for (int i4 = 0; i4 < i; i4++) {
            if (aVar2.g[i4] >= lVar.h) {
                return null;
            }
        }
        return aVar2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Type inference failed for: r1v11, types: [int[], int[][]] */
    /* JADX WARN: Type inference failed for: r1v23, types: [int[], int[][]] */
    @Override // com.c.b.j
    public final Object a(e eVar, o oVar, Object obj) {
        a aVar = (a) obj;
        b bVar = new b(this);
        int i = 0;
        int i2 = 0;
        bVar.f946a = aVar;
        bVar.f947b = aVar.d;
        bVar.d = eVar.e;
        bVar.e = eVar.e[aVar.e];
        int i3 = bVar.e.f906a;
        bVar.f = new int[bVar.f947b];
        for (int i4 = 0; i4 < bVar.f947b; i4++) {
            int i5 = aVar.f[i4];
            int a2 = o.a(i5);
            if (a2 != 0) {
                if (a2 > i2) {
                    i2 = a2;
                }
                bVar.f[i4] = new int[a2];
                for (int i6 = 0; i6 < a2; i6++) {
                    if ((i5 & (1 << i6)) != 0) {
                        int i7 = i;
                        i++;
                        bVar.f[i4][i6] = aVar.g[i7];
                    }
                }
            }
        }
        bVar.g = (int) Math.rint(Math.pow(bVar.f947b, i3));
        bVar.c = i2;
        bVar.h = new int[bVar.g];
        for (int i8 = 0; i8 < bVar.g; i8++) {
            int i9 = i8;
            int i10 = bVar.g / bVar.f947b;
            bVar.h[i8] = new int[i3];
            for (int i11 = 0; i11 < i3; i11++) {
                int i12 = i9 / i10;
                i9 -= i12 * i10;
                i10 /= bVar.f947b;
                bVar.h[i8][i11] = i12;
            }
        }
        return bVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v71, types: [int[][], int[][][]] */
    public static synchronized int a(com.c.b.a aVar, Object obj, float[][] fArr, int i, int i2) {
        com.c.b.b bVar;
        b bVar2 = (b) obj;
        a aVar2 = bVar2.f946a;
        int i3 = aVar2.c;
        int i4 = bVar2.e.f906a;
        int i5 = (aVar2.f945b - aVar2.f944a) / i3;
        int i6 = ((i5 + i4) - 1) / i4;
        if (f943b.length < i) {
            f943b = new int[i];
        }
        for (int i7 = 0; i7 < i; i7++) {
            if (f943b[i7] == null || f943b[i7].length < i6) {
                f943b[i7] = new int[i6];
            }
        }
        for (int i8 = 0; i8 < bVar2.c; i8++) {
            int i9 = 0;
            int i10 = 0;
            while (i9 < i5) {
                if (i8 == 0) {
                    for (int i11 = 0; i11 < i; i11++) {
                        int a2 = bVar2.e.a(aVar.f905b);
                        if (a2 != -1) {
                            f943b[i11][i10] = bVar2.h[a2];
                            if (f943b[i11][i10] == null) {
                                return 0;
                            }
                        } else {
                            return 0;
                        }
                    }
                }
                int i12 = 0;
                while (i12 < i4 && i9 < i5) {
                    for (int i13 = 0; i13 < i; i13++) {
                        int i14 = aVar2.f944a + (i9 * i3);
                        int i15 = f943b[i13][i10][i12];
                        if ((aVar2.f[i15] & (1 << i8)) != 0 && (bVar = bVar2.d[bVar2.f[i15][i8]]) != null) {
                            if (i2 == 0) {
                                if (bVar.a(fArr[i13], i14, aVar.f905b, i3) == -1) {
                                    return 0;
                                }
                            } else if (i2 == 1 && bVar.b(fArr[i13], i14, aVar.f905b, i3) == -1) {
                                return 0;
                            }
                        }
                    }
                    i12++;
                    i9++;
                }
                i10++;
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Type inference failed for: r0v16, types: [int[], int[][]] */
    public static synchronized int a(com.c.b.a aVar, Object obj, float[][] fArr, int i) {
        com.c.b.b bVar;
        b bVar2 = (b) obj;
        a aVar2 = bVar2.f946a;
        int i2 = aVar2.c;
        int i3 = bVar2.e.f906a;
        int i4 = (aVar2.f945b - aVar2.f944a) / i2;
        int i5 = ((i4 + i3) - 1) / i3;
        if (c == null || c.length < i5) {
            c = new int[i5];
        }
        for (int i6 = 0; i6 < bVar2.c; i6++) {
            int i7 = 0;
            int i8 = 0;
            while (i7 < i4) {
                if (i6 == 0) {
                    int a2 = bVar2.e.a(aVar.f905b);
                    if (a2 != -1) {
                        c[i8] = bVar2.h[a2];
                        if (c[i8] == null) {
                            return 0;
                        }
                    } else {
                        return 0;
                    }
                }
                int i9 = 0;
                while (i9 < i3 && i7 < i4) {
                    int i10 = aVar2.f944a + (i7 * i2);
                    int i11 = c[i8][i9];
                    if ((aVar2.f[i11] & (1 << i6)) == 0 || (bVar = bVar2.d[bVar2.f[i11][i6]]) == null || bVar.a(fArr, i10, i, aVar.f905b, i2) != -1) {
                        i9++;
                        i7++;
                    } else {
                        return 0;
                    }
                }
                i8++;
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.c.b.j
    public int a(com.c.b.a aVar, Object obj, float[][] fArr, int[] iArr, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            if (iArr[i3] != 0) {
                int i4 = i2;
                i2++;
                fArr[i4] = fArr[i3];
            }
        }
        if (i2 != 0) {
            return a(aVar, obj, fArr, i2, 0);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/c/b/s$b.class */
    public class b {

        /* renamed from: a, reason: collision with root package name */
        a f946a;

        /* renamed from: b, reason: collision with root package name */
        int f947b;
        int c;
        com.c.b.b[] d;
        com.c.b.b e;
        int[][] f;
        int g;
        int[][] h;

        b(s sVar) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/c/b/s$a.class */
    public class a {

        /* renamed from: a, reason: collision with root package name */
        int f944a;

        /* renamed from: b, reason: collision with root package name */
        int f945b;
        int c;
        int d;
        int e;
        int[] f = new int[64];
        int[] g = new int[256];

        a(s sVar) {
        }
    }
}
