package com.b.a.a;

import com.b.a.a.f;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.lwjgl.opengl.CGL;

/* loaded from: infinitode-2.jar:com/b/a/a/z.class */
public final class z {

    /* renamed from: b, reason: collision with root package name */
    private int[] f840b;
    private int[] c;
    private byte[] d;
    private byte[] e;
    private x f;

    /* renamed from: a, reason: collision with root package name */
    public static final z f841a;

    private z() {
        a(f.a("ubidi.icu"));
    }

    private void a(ByteBuffer byteBuffer) {
        f.b(byteBuffer, 1114195049, new a((byte) 0));
        int i = byteBuffer.getInt();
        if (i < 16) {
            throw new IOException("indexes[0] too small in ubidi.icu");
        }
        this.f840b = new int[i];
        this.f840b[0] = i;
        for (int i2 = 1; i2 < i; i2++) {
            this.f840b[i2] = byteBuffer.getInt();
        }
        this.f = x.b(byteBuffer);
        int i3 = this.f840b[2];
        int b2 = this.f.b();
        if (b2 > i3) {
            throw new IOException("ubidi.icu: not enough bytes for the trie");
        }
        f.a(byteBuffer, i3 - b2);
        int i4 = this.f840b[3];
        if (i4 > 0) {
            this.c = f.c(byteBuffer, i4, 0);
        }
        this.d = new byte[this.f840b[5] - this.f840b[4]];
        byteBuffer.get(this.d);
        this.e = new byte[this.f840b[7] - this.f840b[6]];
        byteBuffer.get(this.e);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/b/a/a/z$a.class */
    public static final class a implements f.a {
        private a() {
        }

        /* synthetic */ a(byte b2) {
            this();
        }

        @Override // com.b.a.a.f.a
        public final boolean a(byte[] bArr) {
            return bArr[0] == 2;
        }
    }

    public final int a(int i) {
        return j(this.f.a(i));
    }

    public final boolean b(int i) {
        return b(this.f.a(i), 12);
    }

    private final int a(int i, int i2) {
        int k = k(i2);
        if (k != -4) {
            return i + k;
        }
        int i3 = this.f840b[3];
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = this.c[i4];
            int l = l(i5);
            if (i == l) {
                return l(this.c[m(i5)]);
            }
            if (i < l) {
                break;
            }
        }
        return i;
    }

    public final int c(int i) {
        return a(i, this.f.a(i));
    }

    public final boolean d(int i) {
        return b(this.f.a(i), 11);
    }

    public final boolean e(int i) {
        return b(this.f.a(i), 10);
    }

    public final int f(int i) {
        return (this.f.a(i) & CGL.kCGLCPDispatchTableSize) >> 5;
    }

    public final int g(int i) {
        int i2 = this.f840b[4];
        int i3 = this.f840b[5];
        if (i2 <= i && i < i3) {
            return this.d[i - i2] & 255;
        }
        int i4 = this.f840b[6];
        int i5 = this.f840b[7];
        if (i4 <= i && i < i5) {
            return this.e[i - i4] & 255;
        }
        return 0;
    }

    public final int h(int i) {
        return (this.f.a(i) & 768) >> 8;
    }

    public final int i(int i) {
        int a2 = this.f.a(i);
        if ((a2 & 768) == 0) {
            return i;
        }
        return a(i, a2);
    }

    private static final int j(int i) {
        return i & 31;
    }

    private static final boolean b(int i, int i2) {
        return ((i >> i2) & 1) != 0;
    }

    private static final int k(int i) {
        return ((short) i) >> 13;
    }

    private static final int l(int i) {
        return i & 2097151;
    }

    private static final int m(int i) {
        return i >>> 21;
    }

    static {
        try {
            f841a = new z();
        } catch (IOException e) {
            throw new com.b.a.d.c(e);
        }
    }
}
