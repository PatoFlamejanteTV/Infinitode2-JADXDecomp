package com.b.a.a;

import com.b.a.a.f;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.MissingResourceException;
import org.lwjgl.openal.AL10;

/* loaded from: infinitode-2.jar:com/b/a/a/ab.class */
public final class ab {

    /* renamed from: a, reason: collision with root package name */
    public static final ab f780a;
    private x c;
    private static final int d;
    private static final int e;
    private static final int f;
    private static final int g;
    private static final int h;
    private static final int i;
    private static final int j;
    private static final int[] l;
    private x n;
    private int[] o;
    private int p;

    /* renamed from: b, reason: collision with root package name */
    public char[] f781b;
    private static /* synthetic */ boolean q;
    private b[] k = {new b(1, 256), new b(1, 128), new ac(this, 5), new an(this, 5), new b(1, 2), new b(1, 524288), new b(1, 1048576), new b(1, 1024), new b(1, 2048), new as(this, 8), new b(1, 67108864), new b(1, 8192), new b(1, 16384), new b(1, 64), new b(1, 4), new b(1, 33554432), new b(1, 16777216), new b(1, 512), new b(1, 32768), new b(1, 65536), new at(this, 5), new b(1, 2097152), new c(this, 22), new b(1, 32), new b(1, 4096), new b(1, 8), new b(1, 131072), new c(this, 27), new b(1, 16), new b(1, 262144), new c(this, 30), new b(1, 1), new b(1, 8388608), new b(1, 4194304), new c(this, 34), new b(1, 134217728), new b(1, 268435456), new g(this, 8, 37), new g(this, 9, 38), new g(this, 8, 39), new g(this, 9, 40), new au(this, 11), new b(1, 536870912), new b(1, 1073741824), new av(this, 6), new aw(this, 1), new ax(this, 1), new ay(this, 1), new ad(this, 1), new c(this, 49), new c(this, 50), new c(this, 51), new c(this, 52), new c(this, 53), new ae(this, 7), new c(this, 55), new af(this, 10), new b(2, 268435456), new b(2, 536870912), new b(2, 1073741824), new b(2, Integer.MIN_VALUE)};
    private e[] m = {new ag(this), new e(0, 130816, 8), new ah(this, 8), new e(2, 31, 0), new e(0, 917504, 17), new ai(this, 1), new aj(this), new ak(this), new e(2, 66060288, 20), new al(this, 1), new am(this, 0, 255, 0), new ao(this, 2), new h(this, 8, 4108, 1), new h(this, 9, AL10.AL_MIN_GAIN, 1), new h(this, 8, AL10.AL_MAX_GAIN, 2), new h(this, 9, AL10.AL_ORIENTATION, 2), new ap(this, 8), new aq(this, 8), new e(2, 992, 5), new e(2, 1015808, 15), new e(2, 31744, 10), new ar(this)};

    static {
        q = !ab.class.desiredAssertionStatus();
        d = g(0);
        e = g(15);
        f = g(18);
        g = g(12);
        h = g(13);
        i = g(14);
        j = g | h | i;
        l = new int[]{0, 0, 0, 0, 1, 0, 4, 5, 3, 2};
        try {
            f780a = new ab();
        } catch (IOException e2) {
            throw new MissingResourceException(e2.getMessage(), "", "");
        }
    }

    public final int a(int i2) {
        return this.c.a(i2);
    }

    public final int a(int i2, int i3) {
        if (!q && i3 < 0) {
            throw new AssertionError();
        }
        if (i3 >= this.p) {
            return 0;
        }
        return this.o[this.n.a(i2) + i3];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean f(int i2) {
        return (g(com.b.a.b.a.a(i2)) & (((e | f) | d) | j)) == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/b/a/a/ab$b.class */
    public class b {

        /* renamed from: a, reason: collision with root package name */
        private int f782a;

        /* renamed from: b, reason: collision with root package name */
        private int f783b;

        b(int i, int i2) {
            this.f782a = i;
            this.f783b = i2;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public b(int i) {
            this.f782a = i;
            this.f783b = 0;
        }

        boolean a(int i) {
            return (ab.this.a(i, this.f782a) & this.f783b) != 0;
        }
    }

    /* loaded from: infinitode-2.jar:com/b/a/a/ab$c.class */
    class c extends b {

        /* renamed from: a, reason: collision with root package name */
        private int f784a;

        c(ab abVar, int i) {
            super(4);
            this.f784a = i;
        }

        @Override // com.b.a.a.ab.b
        final boolean a(int i) {
            return aa.f779b.a(i, this.f784a);
        }
    }

    /* loaded from: infinitode-2.jar:com/b/a/a/ab$g.class */
    class g extends b {

        /* renamed from: a, reason: collision with root package name */
        private int f787a;

        g(ab abVar, int i, int i2) {
            super(i);
            this.f787a = i2;
        }

        @Override // com.b.a.a.ab.b
        final boolean a(int i) {
            return m.a(this.f787a - 37).b(i);
        }
    }

    public final boolean b(int i2, int i3) {
        if (i3 < 0 || 61 <= i3) {
            return false;
        }
        return this.k[i3].a(i2);
    }

    public final int b(int i2) {
        return a(i2) & 31;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/b/a/a/ab$e.class */
    public class e {

        /* renamed from: a, reason: collision with root package name */
        private int f785a;

        /* renamed from: b, reason: collision with root package name */
        private int f786b;
        private int c;

        /* JADX INFO: Access modifiers changed from: package-private */
        public e(int i, int i2, int i3) {
            this.f785a = i;
            this.f786b = i2;
            this.c = i3;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public e(int i) {
            this.f785a = i;
            this.f786b = 0;
        }

        int a(int i) {
            return (ab.this.a(i, this.f785a) & this.f786b) >>> this.c;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/b/a/a/ab$a.class */
    public class a extends e {
        /* JADX INFO: Access modifiers changed from: package-private */
        public a(ab abVar) {
            super(5);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/b/a/a/ab$d.class */
    public class d extends e {
        /* JADX INFO: Access modifiers changed from: package-private */
        public d(ab abVar, int i) {
            super(i);
        }
    }

    /* loaded from: infinitode-2.jar:com/b/a/a/ab$h.class */
    class h extends e {

        /* renamed from: a, reason: collision with root package name */
        private int f788a;

        h(ab abVar, int i, int i2, int i3) {
            super(i);
            this.f788a = i2;
        }

        @Override // com.b.a.a.ab.e
        final int a(int i) {
            return m.a(this.f788a - 4108).a(i);
        }
    }

    public final int c(int i2, int i3) {
        if (i3 < 4096) {
            return (i3 < 0 || i3 >= 61 || !this.k[i3].a(i2)) ? 0 : 1;
        }
        if (i3 < 4118) {
            return this.m[i3 - 4096].a(i2);
        }
        if (i3 == 8192) {
            return g(b(i2));
        }
        return 0;
    }

    private static int g(int i2) {
        return 1 << i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int h(int i2) {
        return i2 >> 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int i(int i2) {
        if (i2 == 0) {
            return 0;
        }
        if (i2 < 11) {
            return 1;
        }
        return i2 < 21 ? 2 : 3;
    }

    private ab() {
        if (this.k.length != 61) {
            throw new com.b.a.d.b("binProps.length!=UProperty.BINARY_LIMIT");
        }
        if (this.m.length != 22) {
            throw new com.b.a.d.b("intProps.length!=(UProperty.INT_LIMIT-UProperty.INT_START)");
        }
        ByteBuffer b2 = com.b.a.a.f.b("uprops.icu");
        com.b.a.a.f.a(b2, 1431335535, new f((byte) 0));
        int i2 = b2.getInt();
        b2.getInt();
        b2.getInt();
        int i3 = b2.getInt();
        int i4 = b2.getInt();
        this.p = b2.getInt();
        int i5 = b2.getInt();
        int i6 = b2.getInt();
        b2.getInt();
        b2.getInt();
        b2.getInt();
        b2.getInt();
        com.b.a.a.f.a(b2, 16);
        this.c = x.b(b2);
        int i7 = (i2 - 16) << 2;
        int b3 = this.c.b();
        if (b3 > i7) {
            throw new IOException("uprops.icu: not enough bytes for main trie");
        }
        com.b.a.a.f.a(b2, i7 - b3);
        com.b.a.a.f.a(b2, (i3 - i2) << 2);
        if (this.p > 0) {
            this.n = x.b(b2);
            int i8 = (i4 - i3) << 2;
            int b4 = this.n.b();
            if (b4 > i8) {
                throw new IOException("uprops.icu: not enough bytes for additional-properties trie");
            }
            com.b.a.a.f.a(b2, i8 - b4);
            this.o = com.b.a.a.f.c(b2, i5 - i4, 0);
        }
        int i9 = (i6 - i5) << 1;
        if (i9 > 0) {
            this.f781b = com.b.a.a.f.b(b2, i9, 0);
        }
    }

    /* loaded from: infinitode-2.jar:com/b/a/a/ab$f.class */
    static final class f implements f.a {
        private f() {
        }

        /* synthetic */ f(byte b2) {
            this();
        }

        @Override // com.b.a.a.f.a
        public final boolean a(byte[] bArr) {
            return bArr[0] == 7;
        }
    }
}
