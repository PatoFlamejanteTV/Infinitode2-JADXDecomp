package org.a.c.c;

import com.prineside.tdi2.events.EventListeners;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:org/a/c/c/e.class */
final class e extends FilterInputStream {
    private final int e;
    private final byte[] f;
    private int g;
    private int h;
    private final int i;
    private final int j;
    private int[] k;
    private int[] l;
    private int m;
    private int n;
    private int o;
    private boolean p;
    private boolean q;
    private int r;
    private int s;

    /* renamed from: a, reason: collision with root package name */
    static final short[][] f4391a = {new short[]{2, 3}, new short[]{2, 3}, new short[]{2, 3}, new short[]{3}, new short[]{4, 5}, new short[]{4, 5, 7}, new short[]{4, 7}, new short[]{24}, new short[]{23, 24, 55, 8, 15}, new short[]{23, 24, 40, 55, 103, 104, 108, 8, 12, 13}, new short[]{18, 19, 20, 21, 22, 23, 28, 29, 30, 31, 36, 39, 40, 43, 44, 51, 52, 53, 55, 56, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 200, 201, 202, 203, 204, 205, 210, 211, 212, 213, 214, 215, 218, 219}, new short[]{74, 75, 76, 77, 82, 83, 84, 85, 90, 91, 100, 101, 108, 109, 114, 115, 116, 117, 118, 119}};

    /* renamed from: b, reason: collision with root package name */
    static final short[][] f4392b = {new short[]{3, 2}, new short[]{1, 4}, new short[]{6, 5}, new short[]{7}, new short[]{9, 8}, new short[]{10, 11, 12}, new short[]{13, 14}, new short[]{15}, new short[]{16, 17, 0, 18, 64}, new short[]{24, 25, 23, 22, 19, 20, 21, 1792, 1856, 1920}, new short[]{1984, 2048, 2112, 2176, 2240, 2304, 2368, 2432, 2496, 2560, 52, 55, 56, 59, 60, 320, 384, 448, 53, 54, 50, 51, 44, 45, 46, 47, 57, 58, 61, 256, 48, 49, 62, 63, 30, 31, 32, 33, 40, 41, 128, 192, 26, 27, 28, 29, 34, 35, 36, 37, 38, 39, 42, 43}, new short[]{640, 704, 768, 832, 1280, 1344, 1408, 1472, 1536, 1600, 1664, 1728, 512, 576, 896, 960, 1024, 1088, 1152, 1216}};
    public static final short[][] c = {new short[]{7, 8, 11, 12, 14, 15}, new short[]{18, 19, 20, 27, 7, 8}, new short[]{23, 24, 42, 43, 3, 52, 53, 7, 8}, new short[]{19, 23, 24, 36, 39, 40, 43, 3, 55, 4, 8, 12}, new short[]{18, 19, 20, 21, 22, 23, 26, 27, 2, 36, 37, 40, 41, 42, 43, 44, 45, 3, 50, 51, 52, 53, 54, 55, 4, 74, 75, 5, 82, 83, 84, 85, 88, 89, 90, 91, 100, 101, 103, 104, 10, 11}, new short[]{152, 153, 154, 155, 204, 205, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219}, new short[0], new short[]{8, 12, 13}, new short[]{18, 19, 20, 21, 22, 23, 28, 29, 30, 31}};
    public static final short[][] d = {new short[]{2, 3, 4, 5, 6, 7}, new short[]{128, 8, 9, 64, 10, 11}, new short[]{192, 1664, 16, 17, 13, 14, 15, 1, 12}, new short[]{26, 21, 28, 27, 18, 24, 25, 22, 256, 23, 20, 19}, new short[]{33, 34, 35, 36, 37, 38, 31, 32, 29, 53, 54, 39, 40, 41, 42, 43, 44, 30, 61, 62, 63, 0, 320, 384, 45, 59, 60, 46, 49, 50, 51, 52, 55, 56, 57, 58, 448, 512, 640, 576, 47, 48}, new short[]{1472, 1536, 1600, 1728, 704, 768, 832, 896, 960, 1024, 1088, 1152, 1216, 1280, 1344, 1408}, new short[0], new short[]{1792, 1856, 1920}, new short[]{1984, 2048, 2112, 2176, 2240, 2304, 2368, 2432, 2496, 2560}};
    private static a t;
    private static a u;
    private static b v;
    private static b w;
    private static b x;
    private static b y;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(InputStream inputStream, int i, int i2, int i3, long j) {
        super(inputStream);
        this.o = 0;
        this.p = false;
        this.q = false;
        this.r = -1;
        this.s = -1;
        this.e = i;
        this.f = new byte[(i + 7) / 8];
        this.j = i2;
        this.i = 1;
        this.k = new int[i + 2];
        this.l = new int[i + 2];
        switch (i2) {
            case 2:
                this.q = (j & 8) != 0;
                return;
            case 3:
                this.p = (j & 1) != 0;
                this.q = (j & 8) != 0;
                return;
            case 4:
                this.q = (j & 4) != 0;
                return;
            default:
                return;
        }
    }

    private void a() {
        if (this.h >= this.g) {
            this.g = 0;
            try {
                g();
            } catch (EOFException e) {
                if (this.g != 0) {
                    throw e;
                }
                this.g = -1;
            }
            this.h = 0;
        }
    }

    private void b() {
        int a2;
        int i = 0;
        boolean z = true;
        this.n = 0;
        do {
            if (z) {
                a2 = a(w);
            } else {
                a2 = a(v);
            }
            if (a2 != -2000) {
                i += a2;
                int[] iArr = this.l;
                int i2 = this.n;
                this.n = i2 + 1;
                iArr[i2] = i;
                z = !z;
            }
        } while (i < this.e);
    }

    private void c() {
        this.m = this.n;
        int[] iArr = this.l;
        this.l = this.k;
        this.k = iArr;
        boolean z = true;
        int i = 0;
        this.n = 0;
        while (i < this.e) {
            a aVar = y.f4395a;
            while (true) {
                a a2 = aVar.a(i());
                aVar = a2;
                if (a2 == null) {
                    break;
                }
                if (aVar.e) {
                    switch (aVar.c) {
                        case -4000:
                            int a3 = i + a(z ? w : v);
                            int[] iArr2 = this.l;
                            int i2 = this.n;
                            this.n = i2 + 1;
                            iArr2[i2] = a3;
                            i = a3 + a(z ? v : w);
                            int[] iArr3 = this.l;
                            int i3 = this.n;
                            this.n = i3 + 1;
                            iArr3[i3] = i;
                            break;
                        case EventListeners.PRIORITY_LOWEST /* -3000 */:
                            int a4 = a(i, z) + 1;
                            if (a4 < this.m) {
                                i = this.k[a4];
                                break;
                            } else {
                                i = this.e;
                                break;
                            }
                        default:
                            int a5 = a(i, z);
                            if (a5 < this.m && a5 != -1) {
                                i = this.k[a5] + aVar.c;
                            } else {
                                i = this.e + aVar.c;
                            }
                            this.l[this.n] = i;
                            this.n++;
                            z = !z;
                            break;
                    }
                }
            }
        }
    }

    private int a(int i, boolean z) {
        int i2 = (this.o & (-2)) + (z ? 0 : 1);
        int i3 = i2;
        if (i2 > 2) {
            i3 -= 2;
        }
        if (i == 0) {
            return i3;
        }
        for (int i4 = i3; i4 < this.m; i4 += 2) {
            if (i < this.k[i4]) {
                this.o = i4;
                return i4;
            }
        }
        return -1;
    }

    private void d() {
        if (this.q) {
            h();
        }
        b();
    }

    private void e() {
        if (this.q) {
            h();
        }
        loop0: while (true) {
            a aVar = x.f4395a;
            do {
                a a2 = aVar.a(i());
                aVar = a2;
                if (a2 != null) {
                }
            } while (!aVar.e);
            if (this.p || i()) {
                b();
            } else {
                c();
                return;
            }
        }
        if (this.p) {
        }
        b();
    }

    private void f() {
        if (this.q) {
            h();
        }
        c();
    }

    private void g() {
        switch (this.j) {
            case 2:
                d();
                break;
            case 3:
                e();
                break;
            case 4:
                f();
                break;
        }
        int i = 0;
        boolean z = true;
        this.o = 0;
        for (int i2 = 0; i2 <= this.n; i2++) {
            int i3 = this.e;
            if (i2 != this.n) {
                i3 = this.l[i2];
            }
            if (i3 > this.e) {
                i3 = this.e;
            }
            int i4 = i / 8;
            while (i % 8 != 0 && i3 - i > 0) {
                byte[] bArr = this.f;
                bArr[i4] = (byte) (bArr[i4] | (z ? 0 : 1 << (7 - (i % 8))));
                i++;
            }
            if (i % 8 == 0) {
                i4 = i / 8;
                byte b2 = (byte) (z ? 0 : 255);
                while (i3 - i > 7) {
                    this.f[i4] = b2;
                    i += 8;
                    i4++;
                }
            }
            while (i3 - i > 0) {
                if (i % 8 == 0) {
                    this.f[i4] = 0;
                }
                byte[] bArr2 = this.f;
                int i5 = i4;
                bArr2[i5] = (byte) (bArr2[i5] | (z ? 0 : 1 << (7 - (i % 8))));
                i++;
            }
            z = !z;
        }
        if (i != this.e) {
            throw new IOException("Sum of run-lengths does not equal scan line width: " + i + " > " + this.e);
        }
        this.g = (i + 7) / 8;
    }

    private int a(b bVar) {
        int i = 0;
        a aVar = bVar.f4395a;
        while (true) {
            a a2 = aVar.a(i());
            aVar = a2;
            if (a2 == null) {
                throw new IOException("Unknown code in Huffman RLE stream");
            }
            if (aVar.e) {
                i += aVar.c;
                if (aVar.c < 64) {
                    return i;
                }
                aVar = bVar.f4395a;
            }
        }
    }

    private void h() {
        this.s = -1;
    }

    private boolean i() {
        boolean z;
        if (this.s < 0 || this.s > 7) {
            this.r = this.in.read();
            if (this.r == -1) {
                throw new EOFException("Unexpected end of Huffman RLE stream");
            }
            this.s = 0;
        }
        if (this.i == 1) {
            z = ((this.r >> (7 - this.s)) & 1) == 1;
        } else {
            z = ((this.r >> this.s) & 1) == 1;
        }
        this.s++;
        if (this.s > 7) {
            this.s = -1;
        }
        return z;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read() {
        if (this.g < 0) {
            return 0;
        }
        if (this.h >= this.g) {
            a();
            if (this.g < 0) {
                return 0;
            }
        }
        byte[] bArr = this.f;
        int i = this.h;
        this.h = i + 1;
        return bArr[i] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) {
        if (this.g < 0) {
            Arrays.fill(bArr, i, i + i2, (byte) 0);
            return i2;
        }
        if (this.h >= this.g) {
            a();
            if (this.g < 0) {
                Arrays.fill(bArr, i, i + i2, (byte) 0);
                return i2;
            }
        }
        int min = Math.min(this.g - this.h, i2);
        System.arraycopy(this.f, this.h, bArr, i, min);
        this.h += min;
        return min;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final long skip(long j) {
        if (this.g < 0) {
            return -1L;
        }
        if (this.h >= this.g) {
            a();
            if (this.g < 0) {
                return -1L;
            }
        }
        int min = (int) Math.min(this.g - this.h, j);
        this.h += min;
        return min;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final synchronized void reset() {
        throw new IOException("mark/reset not supported");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/c/c/e$a.class */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        a f4393a;

        /* renamed from: b, reason: collision with root package name */
        a f4394b;
        int c;
        boolean d;
        boolean e;

        private a() {
            this.d = false;
            this.e = false;
        }

        /* synthetic */ a(byte b2) {
            this();
        }

        final void a(boolean z, a aVar) {
            if (!z) {
                this.f4393a = aVar;
            } else {
                this.f4394b = aVar;
            }
        }

        final a a(boolean z) {
            return z ? this.f4394b : this.f4393a;
        }

        public final String toString() {
            return "[leaf=" + this.e + ", value=" + this.c + ", canBeFill=" + this.d + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/c/c/e$b.class */
    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        final a f4395a;

        private b() {
            this.f4395a = new a((byte) 0);
        }

        /* synthetic */ b(byte b2) {
            this();
        }

        final void a(int i, int i2, int i3) {
            a aVar = this.f4395a;
            for (int i4 = 0; i4 < i; i4++) {
                boolean z = ((i2 >> ((i - 1) - i4)) & 1) == 1;
                a a2 = aVar.a(z);
                a aVar2 = a2;
                if (a2 == null) {
                    aVar2 = new a((byte) 0);
                    if (i4 == i - 1) {
                        aVar2.c = i3;
                        aVar2.e = true;
                    }
                    if (i2 == 0) {
                        aVar2.d = true;
                    }
                    aVar.a(z, aVar2);
                } else if (aVar2.e) {
                    throw new IOException("node is leaf, no other following");
                }
                aVar = aVar2;
            }
        }

        final void a(int i, int i2, a aVar) {
            a aVar2 = this.f4395a;
            for (int i3 = 0; i3 < 12; i3++) {
                boolean z = ((i2 >> (11 - i3)) & 1) == 1;
                a a2 = aVar2.a(z);
                a aVar3 = a2;
                if (a2 == null) {
                    if (i3 == 11) {
                        aVar3 = aVar;
                    } else {
                        aVar3 = new a((byte) 0);
                    }
                    if (i2 == 0) {
                        aVar3.d = true;
                    }
                    aVar2.a(z, aVar3);
                } else if (aVar3.e) {
                    throw new IOException("node is leaf, no other following");
                }
                aVar2 = aVar3;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [short[], short[][]] */
    /* JADX WARN: Type inference failed for: r0v3, types: [short[], short[][]] */
    /* JADX WARN: Type inference failed for: r0v5, types: [short[], short[][]] */
    /* JADX WARN: Type inference failed for: r0v7, types: [short[], short[][]] */
    static {
        a aVar = new a((byte) 0);
        t = aVar;
        aVar.e = true;
        t.c = EventListeners.PRIORITY_VERY_LOW;
        a aVar2 = new a((byte) 0);
        u = aVar2;
        aVar2.c = EventListeners.PRIORITY_LOW;
        a aVar3 = u;
        aVar3.f4393a = aVar3;
        u.f4394b = t;
        x = new b((byte) 0);
        try {
            x.a(12, 0, u);
            x.a(12, 1, t);
            v = new b((byte) 0);
            for (int i = 0; i < f4391a.length; i++) {
                try {
                    for (int i2 = 0; i2 < f4391a[i].length; i2++) {
                        v.a(i + 2, f4391a[i][i2], f4392b[i][i2]);
                    }
                } catch (IOException e) {
                    throw new AssertionError(e);
                }
            }
            v.a(12, 0, u);
            v.a(12, 1, t);
            w = new b((byte) 0);
            for (int i3 = 0; i3 < c.length; i3++) {
                try {
                    for (int i4 = 0; i4 < c[i3].length; i4++) {
                        w.a(i3 + 4, c[i3][i4], d[i3][i4]);
                    }
                } catch (IOException e2) {
                    throw new AssertionError(e2);
                }
            }
            w.a(12, 0, u);
            w.a(12, 1, t);
            y = new b((byte) 0);
            try {
                y.a(4, 1, EventListeners.PRIORITY_LOWEST);
                y.a(3, 1, -4000);
                y.a(1, 1, 0);
                y.a(3, 3, 1);
                y.a(6, 3, 2);
                y.a(7, 3, 3);
                y.a(3, 2, -1);
                y.a(6, 2, -2);
                y.a(7, 2, -3);
            } catch (IOException e3) {
                throw new AssertionError(e3);
            }
        } catch (IOException e4) {
            throw new AssertionError(e4);
        }
    }
}
