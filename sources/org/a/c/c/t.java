package org.a.c.c;

import java.io.FilterOutputStream;
import java.io.OutputStream;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:org/a/c/c/t.class */
public class t {
    static void a(int i, int i2, int i3, int i4, byte[] bArr, byte[] bArr2) {
        int i5;
        if (i == 1) {
            return;
        }
        int i6 = ((i2 * i3) + 7) / 8;
        int length = bArr.length;
        switch (i) {
            case 2:
                if (i3 == 8) {
                    for (int i7 = i6; i7 < length; i7++) {
                        bArr[i7] = (byte) ((bArr[i7] & 255) + (bArr[i7 - i6] & 255));
                    }
                    return;
                }
                if (i3 == 16) {
                    for (int i8 = i6; i8 < length; i8 += 2) {
                        int i9 = ((bArr[i8] & 255) << 8) + (bArr[i8 + 1] & 255);
                        int i10 = ((bArr[i8 - i6] & 255) << 8) + (bArr[(i8 - i6) + 1] & 255);
                        bArr[i8] = (byte) ((i9 + i10) >> 8);
                        bArr[i8 + 1] = (byte) (i9 + i10);
                    }
                    return;
                }
                if (i3 == 1 && i2 == 1) {
                    for (int i11 = 0; i11 < length; i11++) {
                        for (int i12 = 7; i12 >= 0; i12--) {
                            int i13 = (bArr[i11] >> i12) & 1;
                            if (i11 != 0 || i12 != 7) {
                                if (i12 == 7) {
                                    i5 = bArr[i11 - 1] & 1;
                                } else {
                                    i5 = (bArr[i11] >> (i12 + 1)) & 1;
                                }
                                if (((i13 + i5) & 1) == 0) {
                                    bArr[i11] = (byte) (bArr[i11] & ((1 << i12) ^ (-1)));
                                } else {
                                    bArr[i11] = (byte) (bArr[i11] | (1 << i12));
                                }
                            }
                        }
                    }
                    return;
                }
                int i14 = i4 * i2;
                for (int i15 = i2; i15 < i14; i15++) {
                    int i16 = (i15 * i3) / 8;
                    int i17 = (8 - ((i15 * i3) % 8)) - i3;
                    bArr[i16] = (byte) a(bArr[i16], i17, i3, b(bArr[i16], i17, i3) + b(bArr[((i15 - i2) * i3) / 8], (8 - (((i15 - i2) * i3) % 8)) - i3, i3));
                }
                return;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            default:
                return;
            case 10:
                return;
            case 11:
                for (int i18 = i6; i18 < length; i18++) {
                    bArr[i18] = (byte) (bArr[i18] + bArr[i18 - i6]);
                }
                return;
            case 12:
                for (int i19 = 0; i19 < length; i19++) {
                    bArr[i19] = (byte) ((bArr[i19] & 255) + (bArr2[i19] & 255));
                }
                return;
            case 13:
                for (int i20 = 0; i20 < length; i20++) {
                    bArr[i20] = (byte) ((bArr[i20] & 255) + (((i20 - i6 >= 0 ? bArr[i20 - i6] & 255 : 0) + (bArr2[i20] & 255)) / 2));
                }
                return;
            case 14:
                for (int i21 = 0; i21 < length; i21++) {
                    int i22 = bArr[i21] & 255;
                    int i23 = i21 - i6 >= 0 ? bArr[i21 - i6] & 255 : 0;
                    int i24 = bArr2[i21] & 255;
                    int i25 = i21 - i6 >= 0 ? bArr2[i21 - i6] & 255 : 0;
                    int i26 = (i23 + i24) - i25;
                    int abs = Math.abs(i26 - i23);
                    int abs2 = Math.abs(i26 - i24);
                    int abs3 = Math.abs(i26 - i25);
                    if (abs <= abs2 && abs <= abs3) {
                        bArr[i21] = (byte) (i22 + i23);
                    } else if (abs2 <= abs3) {
                        bArr[i21] = (byte) (i22 + i24);
                    } else {
                        bArr[i21] = (byte) (i22 + i25);
                    }
                }
                return;
        }
    }

    static int a(int i, int i2, int i3) {
        return ((i3 * (i * i2)) + 7) / 8;
    }

    private static int b(int i, int i2, int i3) {
        return (i >>> i2) & ((1 << i3) - 1);
    }

    private static int a(int i, int i2, int i3, int i4) {
        int i5 = (1 << i3) - 1;
        return (i & ((i5 << i2) ^ (-1))) | ((i4 & i5) << i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static OutputStream a(OutputStream outputStream, org.a.c.b.d dVar) {
        int j = dVar.j(org.a.c.b.j.cT);
        if (j > 1) {
            return new a(outputStream, j, Math.min(dVar.b(org.a.c.b.j.ab, 1), 32), dVar.b(org.a.c.b.j.z, 8), dVar.b(org.a.c.b.j.ad, 1));
        }
        return outputStream;
    }

    /* loaded from: infinitode-2.jar:org/a/c/c/t$a.class */
    static final class a extends FilterOutputStream {

        /* renamed from: a, reason: collision with root package name */
        private int f4411a;

        /* renamed from: b, reason: collision with root package name */
        private final int f4412b;
        private final int c;
        private final int d;
        private final int e;
        private final boolean f;
        private byte[] g;
        private byte[] h;
        private int i;
        private boolean j;

        a(OutputStream outputStream, int i, int i2, int i3, int i4) {
            super(outputStream);
            this.i = 0;
            this.j = false;
            this.f4411a = i;
            this.f4412b = i2;
            this.c = i3;
            this.d = i4;
            this.e = t.a(i2, i3, i4);
            this.f = i >= 10;
            this.g = new byte[this.e];
            this.h = new byte[this.e];
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public final void write(byte[] bArr) {
            write(bArr, 0, bArr.length);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public final void write(byte[] bArr, int i, int i2) {
            int i3 = i;
            int i4 = i + i2;
            while (i3 < i4) {
                if (this.f && this.i == 0 && !this.j) {
                    this.f4411a = bArr[i3] + 10;
                    i3++;
                    this.j = true;
                } else {
                    int min = Math.min(this.e - this.i, i4 - i3);
                    System.arraycopy(bArr, i3, this.g, this.i, min);
                    this.i += min;
                    i3 += min;
                    if (this.i == this.g.length) {
                        a();
                    }
                }
            }
        }

        private void a() {
            t.a(this.f4411a, this.f4412b, this.c, this.d, this.g, this.h);
            this.out.write(this.g);
            b();
        }

        private void b() {
            byte[] bArr = this.h;
            this.h = this.g;
            this.g = bArr;
            this.i = 0;
            this.j = false;
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
        public final void flush() {
            if (this.i > 0) {
                Arrays.fill(this.g, this.i, this.e, (byte) 0);
                a();
            }
            super.flush();
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public final void write(int i) {
            throw new UnsupportedOperationException("Not supported");
        }
    }
}
