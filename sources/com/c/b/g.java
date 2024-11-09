package com.c.b;

/* loaded from: infinitode-2.jar:com/c/b/g.class */
final class g extends h {

    /* renamed from: b, reason: collision with root package name */
    private static float[] f920b = {1.0649863E-7f, 1.1341951E-7f, 1.2079015E-7f, 1.2863978E-7f, 1.369995E-7f, 1.459025E-7f, 1.5538409E-7f, 1.6548181E-7f, 1.7623574E-7f, 1.8768856E-7f, 1.998856E-7f, 2.128753E-7f, 2.2670913E-7f, 2.4144197E-7f, 2.5713223E-7f, 2.7384212E-7f, 2.9163792E-7f, 3.1059022E-7f, 3.307741E-7f, 3.5226967E-7f, 3.7516213E-7f, 3.995423E-7f, 4.255068E-7f, 4.5315863E-7f, 4.8260745E-7f, 5.1397E-7f, 5.4737063E-7f, 5.829419E-7f, 6.208247E-7f, 6.611694E-7f, 7.041359E-7f, 7.4989464E-7f, 7.98627E-7f, 8.505263E-7f, 9.057983E-7f, 9.646621E-7f, 1.0273513E-6f, 1.0941144E-6f, 1.1652161E-6f, 1.2409384E-6f, 1.3215816E-6f, 1.4074654E-6f, 1.4989305E-6f, 1.5963394E-6f, 1.7000785E-6f, 1.8105592E-6f, 1.9282195E-6f, 2.053526E-6f, 2.1869757E-6f, 2.3290977E-6f, 2.4804558E-6f, 2.6416496E-6f, 2.813319E-6f, 2.9961443E-6f, 3.1908505E-6f, 3.39821E-6f, 3.619045E-6f, 3.8542307E-6f, 4.1047006E-6f, 4.371447E-6f, 4.6555283E-6f, 4.958071E-6f, 5.280274E-6f, 5.623416E-6f, 5.988857E-6f, 6.3780467E-6f, 6.7925284E-6f, 7.2339453E-6f, 7.704048E-6f, 8.2047E-6f, 8.737888E-6f, 9.305725E-6f, 9.910464E-6f, 1.0554501E-5f, 1.1240392E-5f, 1.1970856E-5f, 1.2748789E-5f, 1.3577278E-5f, 1.4459606E-5f, 1.5399271E-5f, 1.6400005E-5f, 1.7465769E-5f, 1.8600793E-5f, 1.9809577E-5f, 2.1096914E-5f, 2.2467912E-5f, 2.3928002E-5f, 2.5482977E-5f, 2.7139005E-5f, 2.890265E-5f, 3.078091E-5f, 3.2781227E-5f, 3.4911533E-5f, 3.718028E-5f, 3.9596467E-5f, 4.2169668E-5f, 4.491009E-5f, 4.7828602E-5f, 5.0936775E-5f, 5.424693E-5f, 5.7772202E-5f, 6.152657E-5f, 6.552491E-5f, 6.9783084E-5f, 7.4317984E-5f, 7.914758E-5f, 8.429104E-5f, 8.976875E-5f, 9.560242E-5f, 1.0181521E-4f, 1.0843174E-4f, 1.1547824E-4f, 1.2298267E-4f, 1.3097477E-4f, 1.3948625E-4f, 1.4855085E-4f, 1.5820454E-4f, 1.6848555E-4f, 1.7943469E-4f, 1.9109536E-4f, 2.0351382E-4f, 2.167393E-4f, 2.3082423E-4f, 2.4582449E-4f, 2.6179955E-4f, 2.7881275E-4f, 2.9693157E-4f, 3.1622787E-4f, 3.3677815E-4f, 3.5866388E-4f, 3.8197188E-4f, 4.0679457E-4f, 4.3323037E-4f, 4.613841E-4f, 4.913675E-4f, 5.2329927E-4f, 5.573062E-4f, 5.935231E-4f, 6.320936E-4f, 6.731706E-4f, 7.16917E-4f, 7.635063E-4f, 8.1312325E-4f, 8.6596457E-4f, 9.2223985E-4f, 9.821722E-4f, 0.0010459992f, 0.0011139743f, 0.0011863665f, 0.0012634633f, 0.0013455702f, 0.0014330129f, 0.0015261382f, 0.0016253153f, 0.0017309374f, 0.0018434235f, 0.0019632196f, 0.0020908006f, 0.0022266726f, 0.0023713743f, 0.0025254795f, 0.0026895993f, 0.0028643848f, 0.0030505287f, 0.003248769f, 0.0034598925f, 0.0036847359f, 0.0039241905f, 0.0041792067f, 0.004450795f, 0.004740033f, 0.005048067f, 0.0053761187f, 0.005725489f, 0.0060975635f, 0.0064938175f, 0.0069158226f, 0.0073652514f, 0.007843887f, 0.008353627f, 0.008896492f, 0.009474637f, 0.010090352f, 0.01074608f, 0.011444421f, 0.012188144f, 0.012980198f, 0.013823725f, 0.014722068f, 0.015678791f, 0.016697686f, 0.017782796f, 0.018938422f, 0.020169148f, 0.021479854f, 0.022875736f, 0.02436233f, 0.025945531f, 0.027631618f, 0.029427277f, 0.031339627f, 0.03337625f, 0.035545226f, 0.037855156f, 0.0403152f, 0.042935107f, 0.045725275f, 0.048696756f, 0.05186135f, 0.05523159f, 0.05882085f, 0.062643364f, 0.06671428f, 0.07104975f, 0.075666964f, 0.08058423f, 0.08582105f, 0.09139818f, 0.097337745f, 0.1036633f, 0.11039993f, 0.11757434f, 0.12521498f, 0.13335215f, 0.14201812f, 0.15124726f, 0.16107617f, 0.1715438f, 0.18269168f, 0.19456401f, 0.20720787f, 0.22067343f, 0.23501402f, 0.25028655f, 0.26655158f, 0.28387362f, 0.3023213f, 0.32196787f, 0.34289113f, 0.36517414f, 0.3889052f, 0.41417846f, 0.44109413f, 0.4697589f, 0.50028646f, 0.53279793f, 0.5674221f, 0.6042964f, 0.64356697f, 0.6853896f, 0.72993004f, 0.777365f, 0.8278826f, 0.88168305f, 0.9389798f, 1.0f};

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.c.b.h
    public final Object a(l lVar, com.c.a.a aVar) {
        int i = 0;
        int i2 = -1;
        a aVar2 = new a(this);
        aVar2.f921a = aVar.c(5);
        for (int i3 = 0; i3 < aVar2.f921a; i3++) {
            aVar2.f922b[i3] = aVar.c(4);
            if (i2 < aVar2.f922b[i3]) {
                i2 = aVar2.f922b[i3];
            }
        }
        for (int i4 = 0; i4 < i2 + 1; i4++) {
            aVar2.c[i4] = aVar.c(3) + 1;
            aVar2.d[i4] = aVar.c(2);
            if (aVar2.d[i4] < 0) {
                aVar2.a();
                return null;
            }
            if (aVar2.d[i4] != 0) {
                aVar2.e[i4] = aVar.c(8);
            }
            if (aVar2.e[i4] < 0 || aVar2.e[i4] >= lVar.h) {
                aVar2.a();
                return null;
            }
            for (int i5 = 0; i5 < (1 << aVar2.d[i4]); i5++) {
                aVar2.f[i4][i5] = aVar.c(8) - 1;
                if (aVar2.f[i4][i5] < -1 || aVar2.f[i4][i5] >= lVar.h) {
                    aVar2.a();
                    return null;
                }
            }
        }
        aVar2.g = aVar.c(2) + 1;
        int c = aVar.c(4);
        int i6 = 0;
        for (int i7 = 0; i7 < aVar2.f921a; i7++) {
            i += aVar2.c[aVar2.f922b[i7]];
            while (i6 < i) {
                int c2 = aVar.c(c);
                aVar2.h[i6 + 2] = c2;
                if (c2 >= 0 && c2 < (1 << c)) {
                    i6++;
                } else {
                    aVar2.a();
                    return null;
                }
            }
        }
        aVar2.h[0] = 0;
        aVar2.h[1] = 1 << c;
        return aVar2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.c.b.h
    public final Object a(e eVar, o oVar, Object obj) {
        int i = 0;
        int[] iArr = new int[65];
        a aVar = (a) obj;
        b bVar = new b(this);
        bVar.i = aVar;
        bVar.g = aVar.h[1];
        for (int i2 = 0; i2 < aVar.f921a; i2++) {
            i += aVar.c[aVar.f922b[i2]];
        }
        int i3 = i + 2;
        bVar.f = i3;
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i4;
            iArr[i5] = i5;
        }
        for (int i6 = 0; i6 < i3 - 1; i6++) {
            for (int i7 = i6; i7 < i3; i7++) {
                if (aVar.h[iArr[i6]] > aVar.h[iArr[i7]]) {
                    int i8 = iArr[i7];
                    iArr[i7] = iArr[i6];
                    iArr[i6] = i8;
                }
            }
        }
        for (int i9 = 0; i9 < i3; i9++) {
            bVar.f924b[i9] = iArr[i9];
        }
        for (int i10 = 0; i10 < i3; i10++) {
            bVar.c[bVar.f924b[i10]] = i10;
        }
        for (int i11 = 0; i11 < i3; i11++) {
            bVar.f923a[i11] = aVar.h[bVar.f924b[i11]];
        }
        switch (aVar.g) {
            case 1:
                bVar.h = 256;
                break;
            case 2:
                bVar.h = 128;
                break;
            case 3:
                bVar.h = 86;
                break;
            case 4:
                bVar.h = 64;
                break;
            default:
                bVar.h = -1;
                break;
        }
        for (int i12 = 0; i12 < i3 - 2; i12++) {
            int i13 = 0;
            int i14 = 1;
            int i15 = 0;
            int i16 = bVar.g;
            int i17 = aVar.h[i12 + 2];
            for (int i18 = 0; i18 < i12 + 2; i18++) {
                int i19 = aVar.h[i18];
                if (i19 > i15 && i19 < i17) {
                    i13 = i18;
                    i15 = i19;
                }
                if (i19 < i16 && i19 > i17) {
                    i14 = i18;
                    i16 = i19;
                }
            }
            bVar.e[i12] = i13;
            bVar.d[i12] = i14;
        }
        return bVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.c.b.h
    public final Object a(com.c.b.a aVar, Object obj, Object obj2) {
        int i;
        b bVar = (b) obj;
        a aVar2 = bVar.i;
        com.c.b.b[] bVarArr = aVar.k.e;
        if (aVar.f905b.c(1) == 1) {
            int[] iArr = null;
            if (obj2 instanceof int[]) {
                iArr = (int[]) obj2;
            }
            if (iArr == null || iArr.length < bVar.f) {
                iArr = new int[bVar.f];
            } else {
                for (int i2 = 0; i2 < iArr.length; i2++) {
                    iArr[i2] = 0;
                }
            }
            iArr[0] = aVar.f905b.c(o.a(bVar.h - 1));
            iArr[1] = aVar.f905b.c(o.a(bVar.h - 1));
            int i3 = 2;
            for (int i4 = 0; i4 < aVar2.f921a; i4++) {
                int i5 = aVar2.f922b[i4];
                int i6 = aVar2.c[i5];
                int i7 = aVar2.d[i5];
                int i8 = 1 << i7;
                int i9 = 0;
                if (i7 != 0) {
                    int a2 = bVarArr[aVar2.e[i5]].a(aVar.f905b);
                    i9 = a2;
                    if (a2 == -1) {
                        return null;
                    }
                }
                for (int i10 = 0; i10 < i6; i10++) {
                    int i11 = aVar2.f[i5][i9 & (i8 - 1)];
                    i9 >>>= i7;
                    if (i11 >= 0) {
                        int a3 = bVarArr[i11].a(aVar.f905b);
                        iArr[i3 + i10] = a3;
                        if (a3 == -1) {
                            return null;
                        }
                    } else {
                        iArr[i3 + i10] = 0;
                    }
                }
                i3 += i6;
            }
            for (int i12 = 2; i12 < bVar.f; i12++) {
                int a4 = a(aVar2.h[bVar.e[i12 - 2]], aVar2.h[bVar.d[i12 - 2]], iArr[bVar.e[i12 - 2]], iArr[bVar.d[i12 - 2]], aVar2.h[i12]);
                int i13 = bVar.h - a4;
                int i14 = (i13 < a4 ? i13 : a4) << 1;
                int i15 = iArr[i12];
                if (i15 != 0) {
                    if (i15 >= i14) {
                        if (i13 > a4) {
                            i = i15 - a4;
                        } else {
                            i = (-1) - (i15 - i13);
                        }
                    } else if ((i15 & 1) != 0) {
                        i = -((i15 + 1) >>> 1);
                    } else {
                        i = i15 >> 1;
                    }
                    iArr[i12] = i + a4;
                    int[] iArr2 = iArr;
                    int i16 = bVar.e[i12 - 2];
                    iArr2[i16] = iArr2[i16] & 32767;
                    int[] iArr3 = iArr;
                    int i17 = bVar.d[i12 - 2];
                    iArr3[i17] = iArr3[i17] & 32767;
                } else {
                    iArr[i12] = a4 | 32768;
                }
            }
            return iArr;
        }
        return null;
    }

    private static int a(int i, int i2, int i3, int i4, int i5) {
        int i6 = i3 & 32767;
        int i7 = (i4 & 32767) - i6;
        int abs = (Math.abs(i7) * (i5 - i)) / (i2 - i);
        if (i7 < 0) {
            return i6 - abs;
        }
        return i6 + abs;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.c.b.h
    public final int a(com.c.b.a aVar, Object obj, Object obj2, float[] fArr) {
        b bVar = (b) obj;
        a aVar2 = bVar.i;
        int i = aVar.k.f914a.c[aVar.g] / 2;
        if (obj2 != null) {
            int[] iArr = (int[]) obj2;
            int i2 = 0;
            int i3 = 0;
            int i4 = iArr[0] * aVar2.g;
            for (int i5 = 1; i5 < bVar.f; i5++) {
                int i6 = bVar.f924b[i5];
                int i7 = iArr[i6] & 32767;
                if (i7 == iArr[i6]) {
                    int i8 = i7 * aVar2.g;
                    i2 = aVar2.h[i6];
                    a(i3, i2, i4, i8, fArr);
                    i3 = i2;
                    i4 = i8;
                }
            }
            for (int i9 = i2; i9 < i; i9++) {
                int i10 = i9;
                fArr[i10] = fArr[i10] * fArr[i9 - 1];
            }
            return 1;
        }
        for (int i11 = 0; i11 < i; i11++) {
            fArr[i11] = 0.0f;
        }
        return 0;
    }

    private static void a(int i, int i2, int i3, int i4, float[] fArr) {
        int i5;
        int i6;
        int i7 = i4 - i3;
        int i8 = i2 - i;
        int abs = Math.abs(i7);
        int i9 = i7 / i8;
        int i10 = i7 < 0 ? i9 - 1 : i9 + 1;
        int i11 = i;
        int i12 = i3;
        int i13 = 0;
        int abs2 = abs - Math.abs(i9 * i8);
        fArr[i11] = fArr[i11] * f920b[i12];
        while (true) {
            i11++;
            if (i11 < i2) {
                int i14 = i13 + abs2;
                i13 = i14;
                if (i14 >= i8) {
                    i13 -= i8;
                    i5 = i12;
                    i6 = i10;
                } else {
                    i5 = i12;
                    i6 = i9;
                }
                i12 = i5 + i6;
                fArr[i11] = fArr[i11] * f920b[i12];
            } else {
                return;
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/c/b/g$a.class */
    class a {

        /* renamed from: a, reason: collision with root package name */
        int f921a;
        int g;

        /* renamed from: b, reason: collision with root package name */
        int[] f922b = new int[31];
        int[] c = new int[16];
        int[] d = new int[16];
        int[] e = new int[16];
        int[][] f = new int[16];
        int[] h = new int[65];

        /* JADX WARN: Type inference failed for: r1v9, types: [int[], int[][]] */
        a(g gVar) {
            for (int i = 0; i < this.f.length; i++) {
                this.f[i] = new int[8];
            }
        }

        final void a() {
            this.f922b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = (int[][]) null;
            this.h = null;
        }
    }

    /* loaded from: infinitode-2.jar:com/c/b/g$b.class */
    class b {

        /* renamed from: a, reason: collision with root package name */
        int[] f923a = new int[65];

        /* renamed from: b, reason: collision with root package name */
        int[] f924b = new int[65];
        int[] c = new int[65];
        int[] d = new int[63];
        int[] e = new int[63];
        int f;
        int g;
        int h;
        a i;

        b(g gVar) {
        }
    }
}
