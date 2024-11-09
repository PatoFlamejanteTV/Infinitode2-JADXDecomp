package b.a.a;

import com.prineside.luaj.Lua;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.io.UnsupportedEncodingException;

/* loaded from: infinitode-2.jar:b/a/a/b.class */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    static byte f3a = 0;

    /* renamed from: b, reason: collision with root package name */
    static byte f4b = 1;
    private int d;
    private int f;
    private int g;
    private int h;
    private Float i;
    private boolean j;
    private final PushbackInputStream l;
    private boolean q;
    private final int[] c = new int[433];
    private byte[] e = new byte[1732];
    private final int[] k = {0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, Lua.MASK_NOT_Bx, 32767, 65535, Lua.MAXARG_sBx};
    private final g m = new g();
    private final byte[] n = new byte[4];
    private d[] o = new d[1];
    private byte[] p = null;

    public b(InputStream inputStream) {
        this.q = true;
        if (inputStream == null) {
            throw new NullPointerException("in");
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        a(bufferedInputStream);
        this.q = true;
        this.l = new PushbackInputStream(bufferedInputStream, 1732);
        d();
    }

    private void a(InputStream inputStream) {
        int i = -1;
        try {
            inputStream.mark(10);
            i = b(inputStream);
            try {
                inputStream.reset();
            } catch (IOException unused) {
            }
        } catch (IOException unused2) {
            try {
                inputStream.reset();
            } catch (IOException unused3) {
            }
        } catch (Throwable th) {
            try {
                inputStream.reset();
            } catch (IOException unused4) {
            }
            throw th;
        }
        if (i > 0) {
            try {
                this.p = new byte[i];
                inputStream.read(this.p, 0, this.p.length);
                a(this.p);
            } catch (IOException unused5) {
            }
        }
    }

    private static int b(InputStream inputStream) {
        byte[] bArr = new byte[4];
        int i = -10;
        inputStream.read(bArr, 0, 3);
        if (bArr[0] == 73 && bArr[1] == 68 && bArr[2] == 51) {
            inputStream.read(bArr, 0, 3);
            inputStream.read(bArr, 0, 4);
            i = (bArr[0] << 21) + (bArr[1] << 14) + (bArr[2] << 7) + bArr[3];
        }
        return i + 10;
    }

    private void a(byte[] bArr) {
        int i;
        int i2;
        int i3;
        if (bArr == null || !"ID3".equals(new String(bArr, 0, 3)) || (i = bArr[3] & 255) < 2 || i > 4) {
            return;
        }
        Float f = null;
        Float f2 = null;
        int i4 = 10;
        while (i4 < bArr.length && bArr[i4] > 0) {
            try {
                if (i == 3 || i == 4) {
                    String str = new String(bArr, i4, 4);
                    i2 = ((bArr[i4 + 4] << 24) & (-16777216)) | ((bArr[i4 + 5] << 16) & 16711680) | ((bArr[i4 + 6] << 8) & 65280) | (bArr[i4 + 7] & 255);
                    i3 = i4 + 10;
                    if (str.equals("TXXX")) {
                        String[] split = a(bArr, i3, i2, 1).split("��");
                        if (split.length == 2) {
                            String str2 = split[0];
                            String str3 = split[1];
                            if (str2.equals("replaygain_track_peak")) {
                                f2 = Float.valueOf(Float.parseFloat(str3));
                                if (f != null) {
                                    break;
                                }
                            } else if (str2.equals("replaygain_track_gain")) {
                                f = Float.valueOf(Float.parseFloat(str3.replace(" dB", "")) + 3.0f);
                                if (f2 != null) {
                                    break;
                                }
                            }
                        }
                    }
                    i4 = i3 + i2;
                } else {
                    String str4 = new String(bArr, i4, 3);
                    i2 = 0 + (bArr[i4 + 3] << 16) + (bArr[i4 + 4] << 8) + bArr[i4 + 5];
                    i3 = i4 + 6;
                    if (str4.equals("TXXX")) {
                        String[] split2 = a(bArr, i3, i2, 1).split("��");
                        if (split2.length == 2) {
                            String str5 = split2[0];
                            String str6 = split2[1];
                            if (str5.equals("replaygain_track_peak")) {
                                f2 = Float.valueOf(Float.parseFloat(str6));
                                if (f != null) {
                                    break;
                                }
                            } else if (str5.equals("replaygain_track_gain")) {
                                f = Float.valueOf(Float.parseFloat(str6.replace(" dB", "")) + 3.0f);
                                if (f2 != null) {
                                    break;
                                }
                            } else {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                    i4 = i3 + i2;
                }
            } catch (RuntimeException unused) {
                return;
            }
            return;
        }
        if (f != null && f2 != null) {
            this.i = Float.valueOf((float) Math.pow(10.0d, f.floatValue() / 20.0f));
            this.i = Float.valueOf(Math.min(1.0f / f2.floatValue(), this.i.floatValue()));
        }
    }

    private static String a(byte[] bArr, int i, int i2, int i3) {
        String str = null;
        try {
            str = new String(bArr, i + 1, i2 - 1, new String[]{"ISO-8859-1", "UTF16", "UTF-16BE", "UTF-8"}[bArr[i]]);
        } catch (UnsupportedEncodingException unused) {
        }
        return str;
    }

    public final void a() {
        try {
            this.l.close();
        } catch (IOException e) {
            throw a(258, e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v10, types: [b.a.a.g] */
    /* JADX WARN: Type inference failed for: r0v2, types: [int] */
    /* JADX WARN: Type inference failed for: r0v22, types: [b.a.a.b] */
    public final g b() {
        c cVar = 0;
        g gVar = null;
        try {
            gVar = f();
            if (this.q) {
                gVar.a(this.e);
                cVar = this;
                cVar.q = false;
            }
        } catch (c e) {
            c a2 = cVar.a();
            if (a2 == 261) {
                try {
                    d();
                    a2 = f();
                    gVar = a2;
                } catch (c e2) {
                    if (a2.a() != 260) {
                        throw a(e2.a(), e2);
                    }
                }
            } else if (e.a() != 260) {
                throw a(e.a(), e);
            }
        }
        return gVar;
    }

    private g f() {
        if (this.d == -1) {
            g();
        }
        return this.m;
    }

    private void g() {
        this.m.a(this, this.o);
    }

    public final void c() {
        if (this.f == -1 && this.g == -1 && this.d > 0) {
            try {
                this.l.unread(this.e, 0, this.d);
            } catch (IOException unused) {
                throw b(258);
            }
        }
    }

    public final void d() {
        this.d = -1;
        this.f = -1;
        this.g = -1;
    }

    public final boolean a(int i) {
        int b2 = b(this.n, 0, 4);
        int i2 = ((this.n[0] << 24) & (-16777216)) | ((this.n[1] << 16) & 16711680) | ((this.n[2] << 8) & 65280) | (this.n[3] & 255);
        try {
            this.l.unread(this.n, 0, b2);
        } catch (IOException unused) {
        }
        boolean z = false;
        switch (b2) {
            case 0:
                z = true;
                break;
            case 4:
                z = a(i2, i, this.h);
                break;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static c b(int i) {
        return new c(i, (Throwable) null);
    }

    private static c a(int i, Throwable th) {
        return new c(i, th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(byte b2) {
        if (b(this.n, 0, 3) != 3) {
            throw a(260, null);
        }
        int i = ((this.n[0] << 16) & 16711680) | ((this.n[1] << 8) & 65280) | (this.n[2] & 255);
        do {
            int i2 = i << 8;
            if (b(this.n, 3, 1) != 1) {
                throw a(260, null);
            }
            i = i2 | (this.n[3] & 255);
        } while (!a(i, b2, this.h));
        return i;
    }

    private boolean a(int i, int i2, int i3) {
        boolean z;
        boolean z2;
        if (i2 == 0) {
            z2 = (i & (-2097152)) == -2097152;
        } else {
            if ((i & (-521216)) == i3) {
                if (((i & 192) == 192) == this.j) {
                    z = true;
                    z2 = z;
                }
            }
            z = false;
            z2 = z;
        }
        if (z2) {
            z2 = ((i >>> 10) & 3) != 3;
        }
        if (z2) {
            z2 = ((i >>> 17) & 3) != 0;
        }
        if (z2) {
            z2 = ((i >>> 19) & 3) != 1;
        }
        return z2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int c(int i) {
        int a2 = a(this.e, 0, i);
        this.d = i;
        this.f = -1;
        this.g = -1;
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void e() {
        int i = 0;
        byte[] bArr = this.e;
        int i2 = this.d;
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 < i2) {
                byte b2 = 0;
                byte b3 = 0;
                byte b4 = 0;
                byte b5 = bArr[i4];
                if (i4 + 1 < i2) {
                    b2 = bArr[i4 + 1];
                }
                if (i4 + 2 < i2) {
                    b3 = bArr[i4 + 2];
                }
                if (i4 + 3 < i2) {
                    b4 = bArr[i4 + 3];
                }
                int i5 = i;
                i++;
                this.c[i5] = ((b5 << 24) & (-16777216)) | ((b2 << 16) & 16711680) | ((b3 << 8) & 65280) | (b4 & 255);
                i3 = i4 + 4;
            } else {
                this.f = 0;
                this.g = 0;
                return;
            }
        }
    }

    public final int d(int i) {
        int i2 = this.g + i;
        if (this.f < 0) {
            this.f = 0;
        }
        if (i2 <= 32) {
            int i3 = (this.c[this.f] >>> (32 - i2)) & this.k[i];
            int i4 = this.g + i;
            this.g = i4;
            if (i4 == 32) {
                this.g = 0;
                this.f++;
            }
            return i3;
        }
        int i5 = this.c[this.f] & 65535;
        this.f++;
        int i6 = ((((i5 << 16) & (-65536)) | (((this.c[this.f] & (-65536)) >>> 16) & 65535)) >>> (48 - i2)) & this.k[i];
        this.g = i2 - 32;
        return i6;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void e(int i) {
        this.h = i & (-193);
        this.j = (i & 192) == 192;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001c, code lost:            if (r0 <= 0) goto L21;     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x001f, code lost:            r1 = r7;        r7 = r7 + 1;        r6[r1] = 0;     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0018, code lost:            r0 = r8;        r8 = r8 - 1;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int a(byte[] r6, int r7, int r8) {
        /*
            r5 = this;
            r0 = 0
            r9 = r0
        L3:
            r0 = r8
            if (r0 <= 0) goto L3d
            r0 = r5
            java.io.PushbackInputStream r0 = r0.l     // Catch: java.io.IOException -> L40
            r1 = r6
            r2 = r7
            r3 = r8
            int r0 = r0.read(r1, r2, r3)     // Catch: java.io.IOException -> L40
            r1 = r0
            r10 = r1
            r1 = -1
            if (r0 != r1) goto L29
        L18:
            r0 = r8
            int r8 = r8 + (-1)
            if (r0 <= 0) goto L3d
            r0 = r6
            r1 = r7
            int r7 = r7 + 1
            r2 = 0
            r0[r1] = r2     // Catch: java.io.IOException -> L40
            goto L18
        L29:
            r0 = r9
            r1 = r10
            int r0 = r0 + r1
            r9 = r0
            r0 = r7
            r1 = r10
            int r0 = r0 + r1
            r7 = r0
            r0 = r8
            r1 = r10
            int r0 = r0 - r1
            r8 = r0
            goto L3
        L3d:
            goto L4b
        L40:
            r10 = move-exception
            r0 = 258(0x102, float:3.62E-43)
            r1 = r10
            b.a.a.c r0 = a(r0, r1)
            throw r0
        L4b:
            r0 = r9
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.b.a(byte[], int, int):int");
    }

    private int b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (i2 > 0) {
            try {
                int read = this.l.read(bArr, i, i2);
                if (read == -1) {
                    break;
                }
                i3 += read;
                i += read;
                i2 -= read;
            } catch (IOException e) {
                throw a(258, e);
            }
        }
        return i3;
    }
}
