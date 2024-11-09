package com.a.a.c.m;

import java.lang.reflect.Array;

/* loaded from: infinitode-2.jar:com/a/a/c/m/c.class */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    private a f713a = null;

    /* renamed from: b, reason: collision with root package name */
    private b f714b = null;
    private g c = null;
    private e d = null;
    private f e = null;
    private d f = null;
    private C0013c g = null;

    public final a a() {
        if (this.f713a == null) {
            this.f713a = new a();
        }
        return this.f713a;
    }

    public final b b() {
        if (this.f714b == null) {
            this.f714b = new b();
        }
        return this.f714b;
    }

    public final g c() {
        if (this.c == null) {
            this.c = new g();
        }
        return this.c;
    }

    public final e d() {
        if (this.d == null) {
            this.d = new e();
        }
        return this.d;
    }

    public final f e() {
        if (this.e == null) {
            this.e = new f();
        }
        return this.e;
    }

    public final d f() {
        if (this.f == null) {
            this.f = new d();
        }
        return this.f;
    }

    public final C0013c g() {
        if (this.g == null) {
            this.g = new C0013c();
        }
        return this.g;
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/m/c$a.class */
    public static final class a extends x<boolean[]> {
        @Override // com.a.a.c.m.x
        public final /* synthetic */ boolean[] a(int i) {
            return b(i);
        }

        private static boolean[] b(int i) {
            return new boolean[i];
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/m/c$b.class */
    public static final class b extends x<byte[]> {
        @Override // com.a.a.c.m.x
        public final /* synthetic */ byte[] a(int i) {
            return b(i);
        }

        private static byte[] b(int i) {
            return new byte[i];
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/m/c$g.class */
    public static final class g extends x<short[]> {
        @Override // com.a.a.c.m.x
        public final /* synthetic */ short[] a(int i) {
            return b(i);
        }

        private static short[] b(int i) {
            return new short[i];
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/m/c$e.class */
    public static final class e extends x<int[]> {
        @Override // com.a.a.c.m.x
        public final /* synthetic */ int[] a(int i) {
            return b(i);
        }

        private static int[] b(int i) {
            return new int[i];
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/m/c$f.class */
    public static final class f extends x<long[]> {
        @Override // com.a.a.c.m.x
        public final /* synthetic */ long[] a(int i) {
            return b(i);
        }

        private static long[] b(int i) {
            return new long[i];
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/m/c$d.class */
    public static final class d extends x<float[]> {
        @Override // com.a.a.c.m.x
        public final /* synthetic */ float[] a(int i) {
            return b(i);
        }

        private static float[] b(int i) {
            return new float[i];
        }
    }

    /* renamed from: com.a.a.c.m.c$c, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:com/a/a/c/m/c$c.class */
    public static final class C0013c extends x<double[]> {
        @Override // com.a.a.c.m.x
        public final /* synthetic */ double[] a(int i) {
            return b(i);
        }

        private static double[] b(int i) {
            return new double[i];
        }
    }

    public static Object a(Object obj) {
        return new com.a.a.c.m.d(obj.getClass(), Array.getLength(obj), obj);
    }
}
