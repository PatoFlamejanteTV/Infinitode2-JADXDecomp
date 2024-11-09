package com.c.b;

/* loaded from: infinitode-2.jar:com/c/b/b.class */
final class b {

    /* renamed from: a, reason: collision with root package name */
    int f906a;

    /* renamed from: b, reason: collision with root package name */
    private int f907b;
    private float[] d;
    private a e;
    private v c = new v();
    private int[] f = new int[15];

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized int a(float[] fArr, int i, com.c.a.a aVar, int i2) {
        int i3 = i2 / this.f906a;
        if (this.f.length < i3) {
            this.f = new int[i3];
        }
        for (int i4 = 0; i4 < i3; i4++) {
            int a2 = a(aVar);
            if (a2 != -1) {
                this.f[i4] = a2 * this.f906a;
            } else {
                return -1;
            }
        }
        int i5 = 0;
        int i6 = 0;
        while (true) {
            int i7 = i6;
            if (i5 < this.f906a) {
                for (int i8 = 0; i8 < i3; i8++) {
                    int i9 = i + i7 + i8;
                    fArr[i9] = fArr[i9] + this.d[this.f[i8] + i5];
                }
                i5++;
                i6 = i7 + i3;
            } else {
                return 0;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Failed to find 'out' block for switch in B:24:0x007a. Please report as an issue. */
    public final int b(float[] fArr, int i, com.c.a.a aVar, int i2) {
        if (this.f906a > 8) {
            int i3 = 0;
            while (i3 < i2) {
                int a2 = a(aVar);
                if (a2 == -1) {
                    return -1;
                }
                int i4 = a2 * this.f906a;
                int i5 = 0;
                while (i5 < this.f906a) {
                    int i6 = i3;
                    i3++;
                    int i7 = i + i6;
                    int i8 = i5;
                    i5++;
                    fArr[i7] = fArr[i7] + this.d[i4 + i8];
                }
            }
            return 0;
        }
        int i9 = 0;
        while (i9 < i2) {
            int a3 = a(aVar);
            if (a3 == -1) {
                return -1;
            }
            int i10 = a3 * this.f906a;
            int i11 = 0;
            switch (this.f906a) {
                case 1:
                    int i12 = i9;
                    i9++;
                    int i13 = i + i12;
                    fArr[i13] = fArr[i13] + this.d[i10 + i11];
                    break;
                case 2:
                    int i14 = i9;
                    i9++;
                    int i15 = i + i14;
                    int i16 = i11;
                    i11++;
                    fArr[i15] = fArr[i15] + this.d[i10 + i16];
                    int i122 = i9;
                    i9++;
                    int i132 = i + i122;
                    fArr[i132] = fArr[i132] + this.d[i10 + i11];
                    break;
                case 3:
                    int i17 = i9;
                    i9++;
                    int i18 = i + i17;
                    int i19 = i11;
                    i11++;
                    fArr[i18] = fArr[i18] + this.d[i10 + i19];
                    int i142 = i9;
                    i9++;
                    int i152 = i + i142;
                    int i162 = i11;
                    i11++;
                    fArr[i152] = fArr[i152] + this.d[i10 + i162];
                    int i1222 = i9;
                    i9++;
                    int i1322 = i + i1222;
                    fArr[i1322] = fArr[i1322] + this.d[i10 + i11];
                    break;
                case 4:
                    int i20 = i9;
                    i9++;
                    int i21 = i + i20;
                    int i22 = i11;
                    i11++;
                    fArr[i21] = fArr[i21] + this.d[i10 + i22];
                    int i172 = i9;
                    i9++;
                    int i182 = i + i172;
                    int i192 = i11;
                    i11++;
                    fArr[i182] = fArr[i182] + this.d[i10 + i192];
                    int i1422 = i9;
                    i9++;
                    int i1522 = i + i1422;
                    int i1622 = i11;
                    i11++;
                    fArr[i1522] = fArr[i1522] + this.d[i10 + i1622];
                    int i12222 = i9;
                    i9++;
                    int i13222 = i + i12222;
                    fArr[i13222] = fArr[i13222] + this.d[i10 + i11];
                    break;
                case 5:
                    int i23 = i9;
                    i9++;
                    int i24 = i + i23;
                    int i25 = i11;
                    i11++;
                    fArr[i24] = fArr[i24] + this.d[i10 + i25];
                    int i202 = i9;
                    i9++;
                    int i212 = i + i202;
                    int i222 = i11;
                    i11++;
                    fArr[i212] = fArr[i212] + this.d[i10 + i222];
                    int i1722 = i9;
                    i9++;
                    int i1822 = i + i1722;
                    int i1922 = i11;
                    i11++;
                    fArr[i1822] = fArr[i1822] + this.d[i10 + i1922];
                    int i14222 = i9;
                    i9++;
                    int i15222 = i + i14222;
                    int i16222 = i11;
                    i11++;
                    fArr[i15222] = fArr[i15222] + this.d[i10 + i16222];
                    int i122222 = i9;
                    i9++;
                    int i132222 = i + i122222;
                    fArr[i132222] = fArr[i132222] + this.d[i10 + i11];
                    break;
                case 6:
                    int i26 = i9;
                    i9++;
                    int i27 = i + i26;
                    int i28 = i11;
                    i11++;
                    fArr[i27] = fArr[i27] + this.d[i10 + i28];
                    int i232 = i9;
                    i9++;
                    int i242 = i + i232;
                    int i252 = i11;
                    i11++;
                    fArr[i242] = fArr[i242] + this.d[i10 + i252];
                    int i2022 = i9;
                    i9++;
                    int i2122 = i + i2022;
                    int i2222 = i11;
                    i11++;
                    fArr[i2122] = fArr[i2122] + this.d[i10 + i2222];
                    int i17222 = i9;
                    i9++;
                    int i18222 = i + i17222;
                    int i19222 = i11;
                    i11++;
                    fArr[i18222] = fArr[i18222] + this.d[i10 + i19222];
                    int i142222 = i9;
                    i9++;
                    int i152222 = i + i142222;
                    int i162222 = i11;
                    i11++;
                    fArr[i152222] = fArr[i152222] + this.d[i10 + i162222];
                    int i1222222 = i9;
                    i9++;
                    int i1322222 = i + i1222222;
                    fArr[i1322222] = fArr[i1322222] + this.d[i10 + i11];
                    break;
                case 7:
                    int i29 = i9;
                    i9++;
                    int i30 = i + i29;
                    int i31 = i11;
                    i11++;
                    fArr[i30] = fArr[i30] + this.d[i10 + i31];
                    int i262 = i9;
                    i9++;
                    int i272 = i + i262;
                    int i282 = i11;
                    i11++;
                    fArr[i272] = fArr[i272] + this.d[i10 + i282];
                    int i2322 = i9;
                    i9++;
                    int i2422 = i + i2322;
                    int i2522 = i11;
                    i11++;
                    fArr[i2422] = fArr[i2422] + this.d[i10 + i2522];
                    int i20222 = i9;
                    i9++;
                    int i21222 = i + i20222;
                    int i22222 = i11;
                    i11++;
                    fArr[i21222] = fArr[i21222] + this.d[i10 + i22222];
                    int i172222 = i9;
                    i9++;
                    int i182222 = i + i172222;
                    int i192222 = i11;
                    i11++;
                    fArr[i182222] = fArr[i182222] + this.d[i10 + i192222];
                    int i1422222 = i9;
                    i9++;
                    int i1522222 = i + i1422222;
                    int i1622222 = i11;
                    i11++;
                    fArr[i1522222] = fArr[i1522222] + this.d[i10 + i1622222];
                    int i12222222 = i9;
                    i9++;
                    int i13222222 = i + i12222222;
                    fArr[i13222222] = fArr[i13222222] + this.d[i10 + i11];
                    break;
                case 8:
                    int i32 = i9;
                    i9++;
                    int i33 = i + i32;
                    i11 = 0 + 1;
                    fArr[i33] = fArr[i33] + this.d[i10 + 0];
                    int i292 = i9;
                    i9++;
                    int i302 = i + i292;
                    int i312 = i11;
                    i11++;
                    fArr[i302] = fArr[i302] + this.d[i10 + i312];
                    int i2622 = i9;
                    i9++;
                    int i2722 = i + i2622;
                    int i2822 = i11;
                    i11++;
                    fArr[i2722] = fArr[i2722] + this.d[i10 + i2822];
                    int i23222 = i9;
                    i9++;
                    int i24222 = i + i23222;
                    int i25222 = i11;
                    i11++;
                    fArr[i24222] = fArr[i24222] + this.d[i10 + i25222];
                    int i202222 = i9;
                    i9++;
                    int i212222 = i + i202222;
                    int i222222 = i11;
                    i11++;
                    fArr[i212222] = fArr[i212222] + this.d[i10 + i222222];
                    int i1722222 = i9;
                    i9++;
                    int i1822222 = i + i1722222;
                    int i1922222 = i11;
                    i11++;
                    fArr[i1822222] = fArr[i1822222] + this.d[i10 + i1922222];
                    int i14222222 = i9;
                    i9++;
                    int i15222222 = i + i14222222;
                    int i16222222 = i11;
                    i11++;
                    fArr[i15222222] = fArr[i15222222] + this.d[i10 + i16222222];
                    int i122222222 = i9;
                    i9++;
                    int i132222222 = i + i122222222;
                    fArr[i132222222] = fArr[i132222222] + this.d[i10 + i11];
                    break;
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int c(float[] fArr, int i, com.c.a.a aVar, int i2) {
        int i3 = 0;
        while (i3 < i2) {
            int a2 = a(aVar);
            if (a2 == -1) {
                return -1;
            }
            int i4 = a2 * this.f906a;
            int i5 = 0;
            while (i5 < this.f906a) {
                int i6 = i3;
                i3++;
                int i7 = i5;
                i5++;
                fArr[i + i6] = this.d[i4 + i7];
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(float[][] fArr, int i, int i2, com.c.a.a aVar, int i3) {
        int i4 = 0;
        int i5 = i / i2;
        while (i5 < (i + i3) / i2) {
            int a2 = a(aVar);
            if (a2 == -1) {
                return -1;
            }
            int i6 = a2 * this.f906a;
            for (int i7 = 0; i7 < this.f906a; i7++) {
                int i8 = i4;
                i4++;
                float[] fArr2 = fArr[i8];
                int i9 = i5;
                fArr2[i9] = fArr2[i9] + this.d[i6 + i7];
                if (i4 == i2) {
                    i4 = 0;
                    i5++;
                }
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(com.c.a.a aVar) {
        int i = 0;
        a aVar2 = this.e;
        int a2 = aVar.a(aVar2.c);
        if (a2 >= 0) {
            i = aVar2.f908a[a2];
            aVar.b(aVar2.f909b[a2]);
            if (i <= 0) {
                return -i;
            }
        }
        do {
            switch (aVar.a()) {
                case 0:
                    i = aVar2.d[i];
                    break;
                case 1:
                    i = aVar2.e[i];
                    break;
                default:
                    return -1;
            }
        } while (i > 0);
        return -i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(v vVar) {
        this.c = vVar;
        this.f907b = vVar.f949b;
        this.f906a = vVar.f948a;
        this.d = vVar.a();
        this.e = a();
        if (this.e == null) {
            return -1;
        }
        return 0;
    }

    private static int[] a(int[] iArr, int i) {
        int[] iArr2 = new int[33];
        int[] iArr3 = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = iArr[i2];
            if (i3 > 0) {
                int i4 = iArr2[i3];
                if (i3 < 32 && (i4 >>> i3) != 0) {
                    return null;
                }
                iArr3[i2] = i4;
                int i5 = i3;
                while (true) {
                    if (i5 <= 0) {
                        break;
                    }
                    if ((iArr2[i5] & 1) != 0) {
                        if (i5 == 1) {
                            iArr2[1] = iArr2[1] + 1;
                        } else {
                            iArr2[i5] = iArr2[i5 - 1] << 1;
                        }
                    } else {
                        int i6 = i5;
                        iArr2[i6] = iArr2[i6] + 1;
                        i5--;
                    }
                }
                for (int i7 = i3 + 1; i7 < 33 && (iArr2[i7] >>> 1) == i4; i7++) {
                    i4 = iArr2[i7];
                    iArr2[i7] = iArr2[i7 - 1] << 1;
                }
            }
        }
        for (int i8 = 0; i8 < i; i8++) {
            int i9 = 0;
            for (int i10 = 0; i10 < iArr[i8]; i10++) {
                i9 = (i9 << 1) | ((iArr3[i8] >>> i10) & 1);
            }
            iArr3[i8] = i9;
        }
        return iArr3;
    }

    private a a() {
        int i;
        int i2;
        int i3 = 0;
        a aVar = new a(this);
        int[] iArr = new int[this.f907b << 1];
        aVar.d = iArr;
        int[] iArr2 = new int[this.f907b << 1];
        aVar.e = iArr2;
        int[] a2 = a(this.c.c, this.c.f949b);
        if (a2 == null) {
            return null;
        }
        for (int i4 = 0; i4 < this.f907b; i4++) {
            if (this.c.c[i4] > 0) {
                int i5 = 0;
                int i6 = 0;
                while (i6 < this.c.c[i4] - 1) {
                    if (((a2[i4] >>> i6) & 1) == 0) {
                        if (iArr[i5] == 0) {
                            i3++;
                            iArr[i5] = i3;
                        }
                        i2 = iArr[i5];
                    } else {
                        if (iArr2[i5] == 0) {
                            i3++;
                            iArr2[i5] = i3;
                        }
                        i2 = iArr2[i5];
                    }
                    i5 = i2;
                    i6++;
                }
                if (((a2[i4] >>> i6) & 1) == 0) {
                    iArr[i5] = -i4;
                } else {
                    iArr2[i5] = -i4;
                }
            }
        }
        aVar.c = o.a(this.f907b) - 4;
        if (aVar.c < 5) {
            aVar.c = 5;
        }
        int i7 = 1 << aVar.c;
        aVar.f908a = new int[i7];
        aVar.f909b = new int[i7];
        for (int i8 = 0; i8 < i7; i8++) {
            int i9 = 0;
            int i10 = 0;
            while (i10 < aVar.c && (i9 > 0 || i10 == 0)) {
                if ((i8 & (1 << i10)) != 0) {
                    i = iArr2[i9];
                } else {
                    i = iArr[i9];
                }
                i9 = i;
                i10++;
            }
            aVar.f908a[i8] = i9;
            aVar.f909b[i8] = i10;
        }
        return aVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/c/b/b$a.class */
    public class a {

        /* renamed from: a, reason: collision with root package name */
        int[] f908a;

        /* renamed from: b, reason: collision with root package name */
        int[] f909b;
        int c;
        int[] d;
        int[] e;

        a(b bVar) {
        }
    }
}
