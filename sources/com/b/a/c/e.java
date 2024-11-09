package com.b.a.c;

import com.b.a.c.c;
import java.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/b/a/c/e.class */
public final class e {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] a(c cVar) {
        int i = cVar.j;
        int i2 = cVar.f848b;
        if (i != i2) {
            Arrays.fill(cVar.e, i, i2, cVar.h);
            cVar.j = i2;
        }
        if (i2 < cVar.e.length) {
            byte[] bArr = new byte[i2];
            System.arraycopy(cVar.e, 0, bArr, 0, i2);
            return bArr;
        }
        return cVar.e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static f a(c cVar, int i) {
        int i2;
        int i3 = cVar.m[i].f865a;
        byte b2 = cVar.m[i].d;
        if (i > 0) {
            i2 = (i3 + cVar.m[i].f866b) - cVar.m[i - 1].f866b;
        } else {
            i2 = i3 + cVar.m[0].f866b;
        }
        return new f(i3, i2, b2);
    }

    private static void a(c cVar, byte b2) {
        cVar.m = cVar.n;
        cVar.k = 1;
        cVar.m[0] = new f(0, cVar.f848b, b2);
    }

    private static void a(c cVar, byte b2, byte b3) {
        if (b3 <= (b2 | 1)) {
            return;
        }
        byte b4 = (byte) (b2 + 1);
        f[] fVarArr = cVar.m;
        byte[] bArr = cVar.e;
        int i = cVar.k;
        if (cVar.j < cVar.f848b) {
            i--;
        }
        while (true) {
            byte b5 = (byte) (b3 - 1);
            b3 = b5;
            if (b5 < b4) {
                break;
            }
            int i2 = 0;
            while (true) {
                if (i2 < i && bArr[fVarArr[i2].f865a] < b3) {
                    i2++;
                } else if (i2 < i) {
                    int i3 = i2;
                    do {
                        i3++;
                        if (i3 >= i) {
                            break;
                        }
                    } while (bArr[fVarArr[i3].f865a] >= b3);
                    for (int i4 = i3 - 1; i2 < i4; i4--) {
                        f fVar = fVarArr[i2];
                        fVarArr[i2] = fVarArr[i4];
                        fVarArr[i4] = fVar;
                        i2++;
                    }
                    if (i3 != i) {
                        i2 = i3 + 1;
                    }
                }
            }
        }
        if ((b4 & 1) == 0) {
            int i5 = 0;
            if (cVar.j == cVar.f848b) {
                i--;
            }
            while (i5 < i) {
                f fVar2 = fVarArr[i5];
                fVarArr[i5] = fVarArr[i];
                fVarArr[i] = fVar2;
                i5++;
                i--;
            }
        }
    }

    private static int b(c cVar, int i) {
        f[] fVarArr = cVar.m;
        int i2 = cVar.k;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = fVarArr[i4].f866b - i3;
            int i6 = fVarArr[i4].f865a;
            if (i >= i6 && i < i6 + i5) {
                return i4;
            }
            i3 += i5;
        }
        throw new IllegalStateException("Internal ICU error in getRunFromLogicalIndex");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(c cVar) {
        if (cVar.k >= 0) {
            return;
        }
        if (cVar.i != 2) {
            a(cVar, cVar.h);
        } else {
            int i = cVar.f848b;
            byte[] bArr = cVar.e;
            byte b2 = -1;
            int i2 = cVar.j;
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                if (bArr[i4] != b2) {
                    i3++;
                    b2 = bArr[i4];
                }
            }
            if (i3 == 1 && i2 == i) {
                a(cVar, bArr[0]);
            } else {
                byte b3 = 126;
                byte b4 = 0;
                if (i2 < i) {
                    i3++;
                }
                cVar.b(i3);
                f[] fVarArr = cVar.l;
                int i5 = 0;
                int i6 = 0;
                do {
                    int i7 = i6;
                    byte b5 = bArr[i6];
                    if (b5 < b3) {
                        b3 = b5;
                    }
                    if (b5 > b4) {
                        b4 = b5;
                    }
                    do {
                        i6++;
                        if (i6 >= i2) {
                            break;
                        }
                    } while (bArr[i6] == b5);
                    fVarArr[i5] = new f(i7, i6 - i7, b5);
                    i5++;
                } while (i6 < i2);
                if (i2 < i) {
                    fVarArr[i5] = new f(i2, i - i2, cVar.h);
                    if (cVar.h < b3) {
                        b3 = cVar.h;
                    }
                }
                cVar.m = fVarArr;
                cVar.k = i3;
                a(cVar, b3, b4);
                int i8 = 0;
                for (int i9 = 0; i9 < i3; i9++) {
                    fVarArr[i9].d = bArr[fVarArr[i9].f865a];
                    f fVar = fVarArr[i9];
                    int i10 = fVar.f866b + i8;
                    fVar.f866b = i10;
                    i8 = i10;
                }
                if (i5 < i3) {
                    fVarArr[(cVar.h & 1) != 0 ? 0 : i5].d = cVar.h;
                }
            }
        }
        if (cVar.o.f853a > 0) {
            for (int i11 = 0; i11 < cVar.o.f853a; i11++) {
                c.h hVar = cVar.o.c[i11];
                cVar.m[b(cVar, hVar.f863a)].c |= hVar.f864b;
            }
        }
        if (cVar.p > 0) {
            for (int i12 = 0; i12 < cVar.f848b; i12++) {
                if (c.a(cVar.f847a[i12])) {
                    cVar.m[b(cVar, i12)].c--;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int[] c(c cVar) {
        f[] fVarArr = cVar.m;
        int i = cVar.f848b > cVar.c ? cVar.f848b : cVar.c;
        int i2 = i;
        int[] iArr = new int[i];
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < cVar.k; i5++) {
            int i6 = fVarArr[i5].f865a;
            int i7 = fVarArr[i5].f866b;
            if (!fVarArr[i5].c()) {
                int i8 = i6 + (i7 - i3);
                do {
                    int i9 = i4;
                    i4++;
                    i8--;
                    iArr[i9] = i8;
                    i3++;
                } while (i3 < i7);
            }
            do {
                int i10 = i4;
                i4++;
                int i11 = i6;
                i6++;
                iArr[i10] = i11;
                i3++;
            } while (i3 < i7);
        }
        if (cVar.o.f853a > 0) {
            int i12 = 0;
            int i13 = cVar.k;
            f[] fVarArr2 = cVar.m;
            for (int i14 = 0; i14 < i13; i14++) {
                int i15 = fVarArr2[i14].c;
                if ((i15 & 5) > 0) {
                    i12++;
                }
                if ((i15 & 10) > 0) {
                    i12++;
                }
            }
            int i16 = cVar.c;
            int i17 = i13 - 1;
            while (i17 >= 0 && i12 > 0) {
                int i18 = fVarArr2[i17].c;
                if ((i18 & 10) > 0) {
                    i16--;
                    iArr[i16] = -1;
                    i12--;
                }
                int i19 = i17 > 0 ? fVarArr2[i17 - 1].f866b : 0;
                for (int i20 = fVarArr2[i17].f866b - 1; i20 >= i19 && i12 > 0; i20--) {
                    i16--;
                    iArr[i16] = iArr[i20];
                }
                if ((i18 & 5) > 0) {
                    i16--;
                    iArr[i16] = -1;
                    i12--;
                }
                i17--;
            }
        } else if (cVar.p > 0) {
            int i21 = cVar.k;
            f[] fVarArr3 = cVar.m;
            int i22 = 0;
            int i23 = 0;
            int i24 = 0;
            while (i24 < i21) {
                int i25 = fVarArr3[i24].f866b - i22;
                int i26 = fVarArr3[i24].c;
                if (i26 == 0 && i23 == i22) {
                    i23 += i25;
                } else if (i26 == 0) {
                    int i27 = fVarArr3[i24].f866b;
                    for (int i28 = i22; i28 < i27; i28++) {
                        int i29 = i23;
                        i23++;
                        iArr[i29] = iArr[i28];
                    }
                } else {
                    int i30 = fVarArr3[i24].f865a;
                    boolean c = fVarArr3[i24].c();
                    int i31 = (i30 + i25) - 1;
                    for (int i32 = 0; i32 < i25; i32++) {
                        int i33 = c ? i30 + i32 : i31 - i32;
                        if (!c.a(cVar.f847a[i33])) {
                            int i34 = i23;
                            i23++;
                            iArr[i34] = i33;
                        }
                    }
                }
                i24++;
                i22 += i25;
            }
        }
        if (i2 == cVar.c) {
            return iArr;
        }
        int[] iArr2 = new int[cVar.c];
        System.arraycopy(iArr, 0, iArr2, 0, cVar.c);
        return iArr2;
    }
}
