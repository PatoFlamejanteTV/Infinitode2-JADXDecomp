package com.b.a.a;

import com.b.a.c.i;

/* loaded from: infinitode-2.jar:com/b/a/a/a.class */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private boolean[] f776a = new boolean[256];

    /* renamed from: b, reason: collision with root package name */
    private int[] f777b = new int[64];
    private int[] c = new int[64];
    private int[] d = new int[18];
    private final int[] e;
    private final int f;
    private static /* synthetic */ boolean g;

    static {
        g = !a.class.desiredAssertionStatus();
    }

    public a(int[] iArr, int i) {
        this.e = iArr;
        this.f = i;
        this.d[0] = a(2048, 0, this.f - 1);
        for (int i2 = 1; i2 <= 16; i2++) {
            this.d[i2] = a(i2 << 12, this.d[i2 - 1], this.f - 1);
        }
        this.d[17] = this.f - 1;
        a();
    }

    public final boolean a(int i) {
        if (i <= 255) {
            return this.f776a[i];
        }
        if (i <= 2047) {
            return (this.f777b[i & 63] & (1 << (i >> 6))) != 0;
        }
        if (i < 55296 || (i >= 57344 && i <= 65535)) {
            int i2 = i >> 12;
            int i3 = (this.c[(i >> 6) & 63] >> i2) & 65537;
            if (i3 <= 1) {
                return i3 != 0;
            }
            return b(i, this.d[i2], this.d[i2 + 1]);
        }
        if (i <= 1114111) {
            return b(i, this.d[13], this.d[17]);
        }
        return false;
    }

    public final int a(CharSequence charSequence, int i, i.b bVar, com.a.a.a.am amVar) {
        char charAt;
        char charAt2;
        int i2 = i;
        int length = charSequence.length();
        int i3 = 0;
        if (i.b.NOT_CONTAINED != bVar) {
            while (i2 < length) {
                char charAt3 = charSequence.charAt(i2);
                if (charAt3 <= 255) {
                    if (!this.f776a[charAt3]) {
                        break;
                    }
                    i2++;
                } else if (charAt3 <= 2047) {
                    if ((this.f777b[charAt3 & '?'] & (1 << (charAt3 >> 6))) == 0) {
                        break;
                    }
                    i2++;
                } else if (charAt3 < 55296 || charAt3 >= 56320 || i2 + 1 == length || (charAt2 = charSequence.charAt(i2 + 1)) < 56320 || charAt2 >= 57344) {
                    int i4 = charAt3 >> '\f';
                    int i5 = (this.c[(charAt3 >> 6) & 63] >> i4) & 65537;
                    if (i5 <= 1) {
                        if (i5 == 0) {
                            break;
                        }
                        i2++;
                    } else {
                        if (!b(charAt3, this.d[i4], this.d[i4 + 1])) {
                            break;
                        }
                        i2++;
                    }
                } else {
                    if (!b(Character.toCodePoint(charAt3, charAt2), this.d[16], this.d[17])) {
                        break;
                    }
                    i3++;
                    i2++;
                    i2++;
                }
            }
        } else {
            while (i2 < length) {
                char charAt4 = charSequence.charAt(i2);
                if (charAt4 <= 255) {
                    if (this.f776a[charAt4]) {
                        break;
                    }
                    i2++;
                } else if (charAt4 <= 2047) {
                    if ((this.f777b[charAt4 & '?'] & (1 << (charAt4 >> 6))) != 0) {
                        break;
                    }
                    i2++;
                } else if (charAt4 < 55296 || charAt4 >= 56320 || i2 + 1 == length || (charAt = charSequence.charAt(i2 + 1)) < 56320 || charAt >= 57344) {
                    int i6 = charAt4 >> '\f';
                    int i7 = (this.c[(charAt4 >> 6) & 63] >> i6) & 65537;
                    if (i7 <= 1) {
                        if (i7 != 0) {
                            break;
                        }
                        i2++;
                    } else {
                        if (b(charAt4, this.d[i6], this.d[i6 + 1])) {
                            break;
                        }
                        i2++;
                    }
                } else {
                    if (b(Character.toCodePoint(charAt4, charAt), this.d[16], this.d[17])) {
                        break;
                    }
                    i3++;
                    i2++;
                    i2++;
                }
            }
        }
        if (amVar != null) {
            amVar.f47a = (i2 - i) - i3;
        }
        return i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x00d2, code lost:            return 0;     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x01a4, code lost:            return r9 + 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0029, code lost:            if (r0 > 2047) goto L14;     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x003c, code lost:            if ((r7.f777b[r0 & '?'] & (1 << (r0 >> 6))) != 0) goto L35;     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0045, code lost:            if (r0 < 55296) goto L24;     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004b, code lost:            if (r0 < 56320) goto L24;     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x004f, code lost:            if (r9 == 0) goto L24;     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0052, code lost:            r0 = r8.charAt(r9 - 1);     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0060, code lost:            if (r0 < 55296) goto L24;     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x0004, code lost:            if (com.b.a.c.i.b.NOT_CONTAINED != r10) goto L4;     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0067, code lost:            if (r0 < 56320) goto L32;     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00c8, code lost:            if (b(java.lang.Character.toCodePoint(r0, r0), r7.d[16], r7.d[17]) == false) goto L76;     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00cb, code lost:            r9 = r9 - 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x006a, code lost:            r0 = r0 >> '\f';        r0 = (r7.c[(r0 >> 6) & 63] >> r0) & 65537;     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0086, code lost:            if (r0 > 1) goto L29;     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x008b, code lost:            if (r0 != 0) goto L35;     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0007, code lost:            r9 = r9 - 1;        r0 = r8.charAt(r9);     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00a6, code lost:            if (b(r0, r7.d[r0], r7.d[r0 + 1]) == false) goto L80;     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00d4, code lost:            r9 = r9 - 1;        r0 = r8.charAt(r9);     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00e3, code lost:            if (r0 > 255) goto L44;     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00ec, code lost:            if (r7.f776a[r0] == false) goto L70;     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x019c, code lost:            if (r9 != 0) goto L89;     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0016, code lost:            if (r0 > 255) goto L9;     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x019f, code lost:            return 0;     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00f6, code lost:            if (r0 > 2047) goto L49;     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0109, code lost:            if ((r7.f777b[r0 & '?'] & (1 << (r0 >> 6))) == 0) goto L70;     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0112, code lost:            if (r0 < 55296) goto L59;     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0118, code lost:            if (r0 < 56320) goto L59;     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x011c, code lost:            if (r9 == 0) goto L59;     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x011f, code lost:            r0 = r8.charAt(r9 - 1);     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x012d, code lost:            if (r0 < 55296) goto L59;     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0134, code lost:            if (r0 < 56320) goto L67;     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0195, code lost:            if (b(java.lang.Character.toCodePoint(r0, r0), r7.d[16], r7.d[17]) != false) goto L84;     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x001f, code lost:            if (r7.f776a[r0] != false) goto L35;     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0198, code lost:            r9 = r9 - 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0137, code lost:            r0 = r0 >> '\f';        r0 = (r7.c[(r0 >> 6) & 63] >> r0) & 65537;     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0153, code lost:            if (r0 > 1) goto L64;     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0158, code lost:            if (r0 == 0) goto L70;     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0173, code lost:            if (b(r0, r7.d[r0], r7.d[r0 + 1]) != false) goto L88;     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x00cf, code lost:            if (r9 != 0) goto L82;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int a(java.lang.CharSequence r8, int r9, com.b.a.c.i.b r10) {
        /*
            Method dump skipped, instructions count: 421
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b.a.a.a.a(java.lang.CharSequence, int, com.b.a.c.i$b):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:            if (r9 > 0) goto L19;     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x005f, code lost:            r1 = r9;        r9 = r9 + 1;        r5[r1] = r5[r1] | r0;     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006f, code lost:            if (r9 < 64) goto L38;     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0072, code lost:            r8 = r8 + 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0077, code lost:            if (r8 >= r0) goto L31;     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x007a, code lost:            r10 = ((1 << r8) - 1) ^ (-1);     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0086, code lost:            if (r0 >= 32) goto L27;     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0089, code lost:            r10 = r10 & ((1 << r0) - 1);     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0093, code lost:            r9 = 0;     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x009a, code lost:            if (r9 >= 64) goto L39;     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x009d, code lost:            r1 = r9;        r5[r1] = r5[r1] | r10;        r9 = r9 + 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00ac, code lost:            r0 = 1 << r0;        r9 = 0;     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b7, code lost:            if (r9 >= r0) goto L40;     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00ba, code lost:            r1 = r9;        r5[r1] = r5[r1] | r0;        r9 = r9 + 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:41:?, code lost:            return;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void a(int[] r5, int r6, int r7) {
        /*
            Method dump skipped, instructions count: 202
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b.a.a.a.a(int[], int, int):void");
    }

    private void a() {
        int i;
        int i2;
        int i3 = 0;
        do {
            int i4 = i3;
            i3++;
            i = this.e[i4];
            if (i3 < this.f) {
                i3++;
                i2 = this.e[i3];
            } else {
                i2 = 1114112;
            }
            if (i >= 256) {
                break;
            }
            do {
                int i5 = i;
                i++;
                this.f776a[i5] = true;
                if (i >= i2) {
                    break;
                }
            } while (i < 256);
        } while (i2 <= 256);
        while (true) {
            if (i >= 2048) {
                break;
            }
            a(this.f777b, i, i2 <= 2048 ? i2 : 2048);
            if (i2 > 2048) {
                i = 2048;
                break;
            }
            int i6 = i3;
            i3++;
            i = this.e[i6];
            if (i3 < this.f) {
                i3++;
                i2 = this.e[i3];
            } else {
                i2 = 1114112;
            }
        }
        int i7 = 2048;
        while (i < 65536) {
            if (i2 > 65536) {
                i2 = 65536;
            }
            if (i < i7) {
                i = i7;
            }
            if (i < i2) {
                if (0 != (i & 63)) {
                    int i8 = i >> 6;
                    int[] iArr = this.c;
                    int i9 = i8 & 63;
                    iArr[i9] = iArr[i9] | (65537 << (i8 >> 6));
                    int i10 = (i8 + 1) << 6;
                    i = i10;
                    i7 = i10;
                }
                if (i < i2) {
                    if (i < (i2 & (-64))) {
                        a(this.c, i >> 6, i2 >> 6);
                    }
                    if (0 != (i2 & 63)) {
                        int i11 = i2 >> 6;
                        int[] iArr2 = this.c;
                        int i12 = i11 & 63;
                        iArr2[i12] = iArr2[i12] | (65537 << (i11 >> 6));
                        int i13 = (i11 + 1) << 6;
                        i2 = i13;
                        i7 = i13;
                    }
                }
            }
            if (i2 != 65536) {
                int i14 = i3;
                i3++;
                i = this.e[i14];
                if (i3 < this.f) {
                    i3++;
                    i2 = this.e[i3];
                } else {
                    i2 = 1114112;
                }
            } else {
                return;
            }
        }
    }

    private int a(int i, int i2, int i3) {
        if (i < this.e[i2]) {
            return i2;
        }
        if (i2 >= i3 || i >= this.e[i3 - 1]) {
            return i3;
        }
        while (true) {
            int i4 = (i2 + i3) >>> 1;
            if (i4 != i2) {
                if (i < this.e[i4]) {
                    i3 = i4;
                } else {
                    i2 = i4;
                }
            } else {
                return i3;
            }
        }
    }

    private final boolean b(int i, int i2, int i3) {
        return 0 != (a(i, i2, i3) & 1);
    }
}
