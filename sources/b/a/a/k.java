package b.a.a;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.linux.FCNTL;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:b/a/a/k.class */
public final class k implements b.a.a.f {

    /* renamed from: a, reason: collision with root package name */
    private int[] f17a;
    private int[] c;
    private float[][][] d;
    private float[][][] e;
    private float[] f;
    private float[][] g;
    private float[][] h;
    private int[] i;
    private b.a.a.b j;
    private g k;
    private n l;
    private n m;
    private m n;
    private int o;
    private b.a.a.a p;
    private a q;
    private f[] r;
    private f[] s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;
    private b[] O;
    private static int[][] T;
    private static final int[][] M = {new int[]{0, 0, 0, 0, 3, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4}, new int[]{0, 1, 2, 3, 0, 1, 2, 3, 1, 2, 3, 1, 2, 3, 2, 3}};
    private static int[] N = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 3, 3, 3, 2, 0};
    private static float[] P = {1.0f, 0.70710677f, 0.5f, 0.35355338f, 0.25f, 0.17677669f, 0.125f, 0.088388346f, 0.0625f, 0.044194173f, 0.03125f, 0.022097087f, 0.015625f, 0.011048543f, 0.0078125f, 0.0055242716f, 0.00390625f, 0.0027621358f, 0.001953125f, 0.0013810679f, 9.765625E-4f, 6.9053395E-4f, 4.8828125E-4f, 3.4526698E-4f, 2.4414062E-4f, 1.7263349E-4f, 1.2207031E-4f, 8.6316744E-5f, 6.1035156E-5f, 4.3158372E-5f, 3.0517578E-5f, 2.1579186E-5f, 1.5258789E-5f, 1.0789593E-5f, 7.6293945E-6f, 5.3947965E-6f, 3.8146973E-6f, 2.6973983E-6f, 1.9073486E-6f, 1.3486991E-6f, 9.536743E-7f, 6.7434956E-7f, 4.7683716E-7f, 3.3717478E-7f, 2.3841858E-7f, 1.6858739E-7f, 1.1920929E-7f, 8.4293696E-8f, 5.9604645E-8f, 4.2146848E-8f, 2.9802322E-8f, 2.1073424E-8f, 1.4901161E-8f, 1.0536712E-8f, 7.450581E-9f, 5.268356E-9f, 3.7252903E-9f, 2.634178E-9f, 1.8626451E-9f, 1.317089E-9f, 9.313226E-10f, 6.585445E-10f, 4.656613E-10f, 3.2927225E-10f};
    private static float[] Q = e();
    private static float[][] R = {new float[]{1.0f, 0.8408964f, 0.70710677f, 0.59460354f, 0.5f, 0.4204482f, 0.35355338f, 0.29730177f, 0.25f, 0.2102241f, 0.17677669f, 0.14865088f, 0.125f, 0.10511205f, 0.088388346f, 0.07432544f, 0.0625f, 0.052556027f, 0.044194173f, 0.03716272f, 0.03125f, 0.026278013f, 0.022097087f, 0.01858136f, 0.015625f, 0.013139007f, 0.011048543f, 0.00929068f, 0.0078125f, 0.0065695033f, 0.0055242716f, 0.00464534f}, new float[]{1.0f, 0.70710677f, 0.5f, 0.35355338f, 0.25f, 0.17677669f, 0.125f, 0.088388346f, 0.0625f, 0.044194173f, 0.03125f, 0.022097087f, 0.015625f, 0.011048543f, 0.0078125f, 0.0055242716f, 0.00390625f, 0.0027621358f, 0.001953125f, 0.0013810679f, 9.765625E-4f, 6.9053395E-4f, 4.8828125E-4f, 3.4526698E-4f, 2.4414062E-4f, 1.7263349E-4f, 1.2207031E-4f, 8.6316744E-5f, 6.1035156E-5f, 4.3158372E-5f, 3.0517578E-5f, 2.1579186E-5f}};
    private static float[] S = {0.0f, 0.2679492f, 0.57735026f, 1.0f, 1.7320508f, 3.732051f, 1.0E11f, -3.732051f, -1.7320508f, -1.0f, -0.57735026f, -0.2679492f, 0.0f, 0.2679492f, 0.57735026f, 1.0f};
    private static final float[] U = {0.8574929f, 0.881742f, 0.94962865f, 0.9833146f, 0.9955178f, 0.9991606f, 0.9998992f, 0.99999315f};
    private static final float[] V = {-0.51449573f, -0.47173196f, -0.31337744f, -0.1819132f, -0.09457419f, -0.040965583f, -0.014198569f, -0.0036999746f};
    private static float[][] W = {new float[]{-0.016141215f, -0.05360318f, -0.100707136f, -0.16280818f, -0.5f, -0.38388735f, -0.6206114f, -1.1659756f, -3.8720753f, -4.225629f, -1.519529f, -0.97416484f, -0.73744076f, -1.2071068f, -0.5163616f, -0.45426053f, -0.40715656f, -0.3696946f, -0.3387627f, -0.31242222f, -0.28939587f, -0.26880082f, -0.5f, -0.23251417f, -0.21596715f, -0.20004979f, -0.18449493f, -0.16905846f, -0.15350361f, -0.13758625f, -0.12103922f, -0.20710678f, -0.084752575f, -0.06415752f, -0.041131172f, -0.014790705f}, new float[]{-0.016141215f, -0.05360318f, -0.100707136f, -0.16280818f, -0.5f, -0.38388735f, -0.6206114f, -1.1659756f, -3.8720753f, -4.225629f, -1.519529f, -0.97416484f, -0.73744076f, -1.2071068f, -0.5163616f, -0.45426053f, -0.40715656f, -0.3696946f, -0.33908543f, -0.3151181f, -0.29642227f, -0.28184548f, -0.5411961f, -0.2621323f, -0.25387916f, -0.2329629f, -0.19852729f, -0.15233535f, -0.0964964f, -0.03342383f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f}, new float[]{-0.0483008f, -0.15715657f, -0.28325045f, -0.42953748f, -1.2071068f, -0.8242648f, -1.1451749f, -1.769529f, -4.5470223f, -3.489053f, -0.7329629f, -0.15076515f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f}, new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.15076514f, -0.7329629f, -3.489053f, -4.5470223f, -1.769529f, -1.1451749f, -0.8313774f, -1.306563f, -0.54142016f, -0.46528974f, -0.4106699f, -0.3700468f, -0.3387627f, -0.31242222f, -0.28939587f, -0.26880082f, -0.5f, -0.23251417f, -0.21596715f, -0.20004979f, -0.18449493f, -0.16905846f, -0.15350361f, -0.13758625f, -0.12103922f, -0.20710678f, -0.084752575f, -0.06415752f, -0.041131172f, -0.014790705f}};
    private static int[][][] X = {new int[]{new int[]{6, 5, 5, 5}, new int[]{9, 9, 9, 9}, new int[]{6, 9, 9, 9}}, new int[]{new int[]{6, 5, 7, 3}, new int[]{9, 9, 12, 6}, new int[]{6, 9, 12, 6}}, new int[]{new int[]{11, 10, 0, 0}, new int[]{18, 18, 0, 0}, new int[]{15, 18, 0, 0}}, new int[]{new int[]{7, 7, 7, 0}, new int[]{12, 12, 12, 0}, new int[]{6, 15, 12, 0}}, new int[]{new int[]{6, 6, 6, 3}, new int[]{12, 9, 9, 6}, new int[]{6, 12, 9, 6}}, new int[]{new int[]{8, 8, 5, 0}, new int[]{15, 12, 9, 0}, new int[]{6, 18, 9, 0}}};

    /* renamed from: b, reason: collision with root package name */
    private int f18b = 0;
    private float[] A = new float[32];
    private float[] B = new float[32];
    private final int[] C = new int[4];
    private int[] D = {0};
    private int[] E = {0};
    private int[] F = {0};
    private int[] G = {0};
    private int[] H = new int[User32.WM_TOUCH];
    private float[] I = new float[User32.WM_TOUCH];
    private float[] J = new float[18];
    private float[] K = new float[36];
    private int L = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:b/a/a/k$d.class */
    public static class d {

        /* renamed from: a, reason: collision with root package name */
        public int f23a = 0;

        /* renamed from: b, reason: collision with root package name */
        public int f24b = 0;
        public int c = 0;
        public int d = 0;
        public int e = 0;
        public int f = 0;
        public int g = 0;
        public int j = 0;
        public int k = 0;
        public int l = 0;
        public int m = 0;
        public int n = 0;
        public int[] h = new int[3];
        public int[] i = new int[3];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:b/a/a/k$f.class */
    public static class f {

        /* renamed from: a, reason: collision with root package name */
        public int[] f27a = new int[23];

        /* renamed from: b, reason: collision with root package name */
        public int[][] f28b = new int[3][13];
    }

    /* JADX WARN: Type inference failed for: r0v120, types: [int[], int[][]] */
    public k(b.a.a.b bVar, g gVar, n nVar, n nVar2, m mVar, int i) {
        o.a();
        this.c = new int[580];
        this.d = new float[2][32][18];
        this.e = new float[2][32][18];
        this.f = new float[User32.WM_TOUCH];
        this.g = new float[2][User32.WM_TOUCH];
        this.h = new float[2][User32.WM_TOUCH];
        this.i = new int[2];
        this.r = new f[2];
        this.r[0] = new f();
        this.r[1] = new f();
        this.s = this.r;
        this.O = new b[9];
        this.O[0] = new b(new int[]{0, 6, 12, 18, 24, 30, 36, 44, 54, 66, 80, 96, 116, 140, 168, 200, User32.VK_OEM_WSCTRL, GLFW.GLFW_KEY_PAUSE, GLFW.GLFW_KEY_KP_EQUAL, 396, 464, User32.WM_MOUSEWHEEL, User32.WM_TOUCH}, new int[]{0, 4, 8, 12, 18, 24, 32, 42, 56, 74, 100, 132, 174, 192});
        this.O[1] = new b(new int[]{0, 6, 12, 18, 24, 30, 36, 44, 54, 66, 80, 96, 114, 136, 162, 194, 232, User32.WM_INITMENU, GLFW.GLFW_KEY_KP_DECIMAL, 394, 464, 540, User32.WM_TOUCH}, new int[]{0, 4, 8, 12, 18, 26, 36, 48, 62, 80, 104, 136, 180, 192});
        this.O[2] = new b(new int[]{0, 6, 12, 18, 24, 30, 36, 44, 54, 66, 80, 96, 116, 140, 168, 200, User32.VK_OEM_WSCTRL, GLFW.GLFW_KEY_PAUSE, GLFW.GLFW_KEY_KP_EQUAL, 396, 464, User32.WM_MOUSEWHEEL, User32.WM_TOUCH}, new int[]{0, 4, 8, 12, 18, 26, 36, 48, 62, 80, 104, 134, 174, 192});
        this.O[3] = new b(new int[]{0, 4, 8, 12, 16, 20, 24, 30, 36, 44, 52, 62, 74, 90, 110, 134, 162, 196, User32.VK_OEM_WSCTRL, User32.WM_MENUCHAR, GLFW.GLFW_KEY_LEFT_ALT, 418, User32.WM_TOUCH}, new int[]{0, 4, 8, 12, 16, 22, 30, 40, 52, 66, 84, 106, 136, 192});
        this.O[4] = new b(new int[]{0, 4, 8, 12, 16, 20, 24, 30, 36, 42, 50, 60, 72, 88, 106, 128, 156, 190, 230, User32.WM_HSCROLL, GLFW.GLFW_KEY_KP_DECIMAL, 384, User32.WM_TOUCH}, new int[]{0, 4, 8, 12, 16, 22, 28, 38, 50, 64, 80, 100, 126, 192});
        this.O[5] = new b(new int[]{0, 4, 8, 12, 16, 20, 24, 30, 36, 44, 54, 66, 82, 102, 126, 156, 194, User32.VK_OEM_ATTN, 296, 364, FCNTL.S_IRWXU, User32.WM_MDITILE, User32.WM_TOUCH}, new int[]{0, 4, 8, 12, 16, 22, 30, 42, 58, 78, 104, 138, 180, 192});
        this.O[6] = new b(new int[]{0, 6, 12, 18, 24, 30, 36, 44, 54, 66, 80, 96, 116, 140, 168, 200, User32.VK_OEM_WSCTRL, GLFW.GLFW_KEY_PAUSE, GLFW.GLFW_KEY_KP_EQUAL, 396, 464, User32.WM_MOUSEWHEEL, User32.WM_TOUCH}, new int[]{0, 4, 8, 12, 18, 26, 36, 48, 62, 80, 104, 134, 174, 192});
        this.O[7] = new b(new int[]{0, 6, 12, 18, 24, 30, 36, 44, 54, 66, 80, 96, 116, 140, 168, 200, User32.VK_OEM_WSCTRL, GLFW.GLFW_KEY_PAUSE, GLFW.GLFW_KEY_KP_EQUAL, 396, 464, User32.WM_MOUSEWHEEL, User32.WM_TOUCH}, new int[]{0, 4, 8, 12, 18, 26, 36, 48, 62, 80, 104, 134, 174, 192});
        this.O[8] = new b(new int[]{0, 12, 24, 36, 48, 60, 72, 88, 108, 132, 160, 192, 232, GLFW.GLFW_KEY_CAPS_LOCK, GLFW.GLFW_KEY_KP_EQUAL, 400, 476, 566, 568, 570, 572, 574, User32.WM_TOUCH}, new int[]{0, 8, 16, 24, 36, 52, 72, 96, 124, 160, 162, 164, 166, 192});
        if (T == null) {
            T = new int[9];
            for (int i2 = 0; i2 < 9; i2++) {
                T[i2] = a(this.O[i2].f22b);
            }
        }
        new c(this, new int[]{0, 6, 11, 16, 21}, new int[]{0, 6, 12});
        this.f17a = new int[54];
        this.j = bVar;
        this.k = gVar;
        this.l = nVar;
        this.m = nVar2;
        this.n = mVar;
        this.o = 0;
        this.u = 0;
        this.w = this.k.f() == 3 ? 1 : 2;
        this.t = this.k.a() == 1 ? 2 : 1;
        this.z = this.k.d() + (this.k.a() == 1 ? 3 : this.k.a() == 2 ? 6 : 0);
        if (this.w == 2) {
            switch (this.o) {
                case 1:
                case 3:
                    this.y = 0;
                    this.x = 0;
                    break;
                case 2:
                    this.y = 1;
                    this.x = 1;
                    break;
                default:
                    this.x = 0;
                    this.y = 1;
                    break;
            }
        } else {
            this.y = 0;
            this.x = 0;
        }
        for (int i3 = 0; i3 < 2; i3++) {
            for (int i4 = 0; i4 < 576; i4++) {
                this.g[i3][i4] = 0.0f;
            }
        }
        int[] iArr = this.i;
        this.i[1] = 576;
        iArr[0] = 576;
        this.p = new b.a.a.a();
        this.q = new a();
    }

    @Override // b.a.a.f
    public final void a() {
        b();
    }

    private void b() {
        int h = this.k.h();
        c();
        for (int i = 0; i < h; i++) {
            this.p.b(this.j.d(8));
        }
        int a2 = this.p.a() >>> 3;
        int a3 = this.p.a() & 7;
        if (a3 != 0) {
            this.p.a(8 - a3);
            a2++;
        }
        int i2 = (this.u - a2) - this.q.f19a;
        this.u += h;
        if (i2 < 0) {
            return;
        }
        if (a2 > 4096) {
            this.u -= 4096;
            this.p.d(4096);
        }
        while (i2 > 0) {
            this.p.a(8);
            i2--;
        }
        for (int i3 = 0; i3 < this.t; i3++) {
            for (int i4 = 0; i4 < this.w; i4++) {
                this.v = this.p.a();
                if (this.k.a() == 1) {
                    a(i4, i3);
                } else {
                    c(i4, i3);
                }
                d(i4, i3);
                a(this.d[i4], i4, i3);
            }
            a(i3);
            if (this.o == 3 && this.w > 1) {
                d();
            }
            for (int i5 = this.x; i5 <= this.y; i5++) {
                b(this.e[i5], i5, i3);
                e(i5, i3);
                f(i5, i3);
                for (int i6 = 18; i6 < 576; i6 += 36) {
                    for (int i7 = 1; i7 < 18; i7 += 2) {
                        this.f[i6 + i7] = -this.f[i6 + i7];
                    }
                }
                if (i5 == 0 || this.o == 2) {
                    for (int i8 = 0; i8 < 18; i8++) {
                        int i9 = 0;
                        for (int i10 = 0; i10 < 576; i10 += 18) {
                            this.A[i9] = this.f[i10 + i8];
                            i9++;
                        }
                        this.l.a(this.A);
                        this.l.a(this.n);
                    }
                } else {
                    for (int i11 = 0; i11 < 18; i11++) {
                        int i12 = 0;
                        for (int i13 = 0; i13 < 576; i13 += 18) {
                            this.B[i12] = this.f[i13 + i11];
                            i12++;
                        }
                        this.m.a(this.B);
                        this.m.a(this.n);
                    }
                }
            }
        }
        this.L++;
    }

    private boolean c() {
        if (this.k.a() == 1) {
            this.q.f19a = this.j.d(9);
            if (this.w == 1) {
                this.j.d(5);
            } else {
                this.j.d(3);
            }
            for (int i = 0; i < this.w; i++) {
                this.q.f20b[i].f25a[0] = this.j.d(1);
                this.q.f20b[i].f25a[1] = this.j.d(1);
                this.q.f20b[i].f25a[2] = this.j.d(1);
                this.q.f20b[i].f25a[3] = this.j.d(1);
            }
            for (int i2 = 0; i2 < 2; i2++) {
                for (int i3 = 0; i3 < this.w; i3++) {
                    this.q.f20b[i3].f26b[i2].f23a = this.j.d(12);
                    this.q.f20b[i3].f26b[i2].f24b = this.j.d(9);
                    this.q.f20b[i3].f26b[i2].c = this.j.d(8);
                    this.q.f20b[i3].f26b[i2].d = this.j.d(4);
                    this.q.f20b[i3].f26b[i2].e = this.j.d(1);
                    if (this.q.f20b[i3].f26b[i2].e != 0) {
                        this.q.f20b[i3].f26b[i2].f = this.j.d(2);
                        this.q.f20b[i3].f26b[i2].g = this.j.d(1);
                        this.q.f20b[i3].f26b[i2].h[0] = this.j.d(5);
                        this.q.f20b[i3].f26b[i2].h[1] = this.j.d(5);
                        this.q.f20b[i3].f26b[i2].i[0] = this.j.d(3);
                        this.q.f20b[i3].f26b[i2].i[1] = this.j.d(3);
                        this.q.f20b[i3].f26b[i2].i[2] = this.j.d(3);
                        if (this.q.f20b[i3].f26b[i2].f == 0) {
                            return false;
                        }
                        if (this.q.f20b[i3].f26b[i2].f == 2 && this.q.f20b[i3].f26b[i2].g == 0) {
                            this.q.f20b[i3].f26b[i2].j = 8;
                        } else {
                            this.q.f20b[i3].f26b[i2].j = 7;
                        }
                        this.q.f20b[i3].f26b[i2].k = 20 - this.q.f20b[i3].f26b[i2].j;
                    } else {
                        this.q.f20b[i3].f26b[i2].h[0] = this.j.d(5);
                        this.q.f20b[i3].f26b[i2].h[1] = this.j.d(5);
                        this.q.f20b[i3].f26b[i2].h[2] = this.j.d(5);
                        this.q.f20b[i3].f26b[i2].j = this.j.d(4);
                        this.q.f20b[i3].f26b[i2].k = this.j.d(3);
                        this.q.f20b[i3].f26b[i2].f = 0;
                    }
                    this.q.f20b[i3].f26b[i2].l = this.j.d(1);
                    this.q.f20b[i3].f26b[i2].m = this.j.d(1);
                    this.q.f20b[i3].f26b[i2].n = this.j.d(1);
                }
            }
            return true;
        }
        this.q.f19a = this.j.d(8);
        if (this.w == 1) {
            this.j.d(1);
        } else {
            this.j.d(2);
        }
        for (int i4 = 0; i4 < this.w; i4++) {
            this.q.f20b[i4].f26b[0].f23a = this.j.d(12);
            this.q.f20b[i4].f26b[0].f24b = this.j.d(9);
            this.q.f20b[i4].f26b[0].c = this.j.d(8);
            this.q.f20b[i4].f26b[0].d = this.j.d(9);
            this.q.f20b[i4].f26b[0].e = this.j.d(1);
            if (this.q.f20b[i4].f26b[0].e != 0) {
                this.q.f20b[i4].f26b[0].f = this.j.d(2);
                this.q.f20b[i4].f26b[0].g = this.j.d(1);
                this.q.f20b[i4].f26b[0].h[0] = this.j.d(5);
                this.q.f20b[i4].f26b[0].h[1] = this.j.d(5);
                this.q.f20b[i4].f26b[0].i[0] = this.j.d(3);
                this.q.f20b[i4].f26b[0].i[1] = this.j.d(3);
                this.q.f20b[i4].f26b[0].i[2] = this.j.d(3);
                if (this.q.f20b[i4].f26b[0].f == 0) {
                    return false;
                }
                if (this.q.f20b[i4].f26b[0].f == 2 && this.q.f20b[i4].f26b[0].g == 0) {
                    this.q.f20b[i4].f26b[0].j = 8;
                } else {
                    this.q.f20b[i4].f26b[0].j = 7;
                    this.q.f20b[i4].f26b[0].k = 20 - this.q.f20b[i4].f26b[0].j;
                }
            } else {
                this.q.f20b[i4].f26b[0].h[0] = this.j.d(5);
                this.q.f20b[i4].f26b[0].h[1] = this.j.d(5);
                this.q.f20b[i4].f26b[0].h[2] = this.j.d(5);
                this.q.f20b[i4].f26b[0].j = this.j.d(4);
                this.q.f20b[i4].f26b[0].k = this.j.d(3);
                this.q.f20b[i4].f26b[0].f = 0;
            }
            this.q.f20b[i4].f26b[0].m = this.j.d(1);
            this.q.f20b[i4].f26b[0].n = this.j.d(1);
        }
        return true;
    }

    private void a(int i, int i2) {
        d dVar = this.q.f20b[i].f26b[i2];
        int i3 = dVar.d;
        int i4 = M[0][i3];
        int i5 = M[1][i3];
        if (dVar.e != 0 && dVar.f == 2) {
            if (dVar.g != 0) {
                for (int i6 = 0; i6 < 8; i6++) {
                    this.s[i].f27a[i6] = this.p.a(M[0][dVar.d]);
                }
                for (int i7 = 3; i7 < 6; i7++) {
                    for (int i8 = 0; i8 < 3; i8++) {
                        this.s[i].f28b[i8][i7] = this.p.a(M[0][dVar.d]);
                    }
                }
                for (int i9 = 6; i9 < 12; i9++) {
                    for (int i10 = 0; i10 < 3; i10++) {
                        this.s[i].f28b[i10][i9] = this.p.a(M[1][dVar.d]);
                    }
                }
                for (int i11 = 0; i11 < 3; i11++) {
                    this.s[i].f28b[i11][12] = 0;
                }
                return;
            }
            this.s[i].f28b[0][0] = this.p.a(i4);
            this.s[i].f28b[1][0] = this.p.a(i4);
            this.s[i].f28b[2][0] = this.p.a(i4);
            this.s[i].f28b[0][1] = this.p.a(i4);
            this.s[i].f28b[1][1] = this.p.a(i4);
            this.s[i].f28b[2][1] = this.p.a(i4);
            this.s[i].f28b[0][2] = this.p.a(i4);
            this.s[i].f28b[1][2] = this.p.a(i4);
            this.s[i].f28b[2][2] = this.p.a(i4);
            this.s[i].f28b[0][3] = this.p.a(i4);
            this.s[i].f28b[1][3] = this.p.a(i4);
            this.s[i].f28b[2][3] = this.p.a(i4);
            this.s[i].f28b[0][4] = this.p.a(i4);
            this.s[i].f28b[1][4] = this.p.a(i4);
            this.s[i].f28b[2][4] = this.p.a(i4);
            this.s[i].f28b[0][5] = this.p.a(i4);
            this.s[i].f28b[1][5] = this.p.a(i4);
            this.s[i].f28b[2][5] = this.p.a(i4);
            this.s[i].f28b[0][6] = this.p.a(i5);
            this.s[i].f28b[1][6] = this.p.a(i5);
            this.s[i].f28b[2][6] = this.p.a(i5);
            this.s[i].f28b[0][7] = this.p.a(i5);
            this.s[i].f28b[1][7] = this.p.a(i5);
            this.s[i].f28b[2][7] = this.p.a(i5);
            this.s[i].f28b[0][8] = this.p.a(i5);
            this.s[i].f28b[1][8] = this.p.a(i5);
            this.s[i].f28b[2][8] = this.p.a(i5);
            this.s[i].f28b[0][9] = this.p.a(i5);
            this.s[i].f28b[1][9] = this.p.a(i5);
            this.s[i].f28b[2][9] = this.p.a(i5);
            this.s[i].f28b[0][10] = this.p.a(i5);
            this.s[i].f28b[1][10] = this.p.a(i5);
            this.s[i].f28b[2][10] = this.p.a(i5);
            this.s[i].f28b[0][11] = this.p.a(i5);
            this.s[i].f28b[1][11] = this.p.a(i5);
            this.s[i].f28b[2][11] = this.p.a(i5);
            this.s[i].f28b[0][12] = 0;
            this.s[i].f28b[1][12] = 0;
            this.s[i].f28b[2][12] = 0;
            return;
        }
        if (this.q.f20b[i].f25a[0] == 0 || i2 == 0) {
            this.s[i].f27a[0] = this.p.a(i4);
            this.s[i].f27a[1] = this.p.a(i4);
            this.s[i].f27a[2] = this.p.a(i4);
            this.s[i].f27a[3] = this.p.a(i4);
            this.s[i].f27a[4] = this.p.a(i4);
            this.s[i].f27a[5] = this.p.a(i4);
        }
        if (this.q.f20b[i].f25a[1] == 0 || i2 == 0) {
            this.s[i].f27a[6] = this.p.a(i4);
            this.s[i].f27a[7] = this.p.a(i4);
            this.s[i].f27a[8] = this.p.a(i4);
            this.s[i].f27a[9] = this.p.a(i4);
            this.s[i].f27a[10] = this.p.a(i4);
        }
        if (this.q.f20b[i].f25a[2] == 0 || i2 == 0) {
            this.s[i].f27a[11] = this.p.a(i5);
            this.s[i].f27a[12] = this.p.a(i5);
            this.s[i].f27a[13] = this.p.a(i5);
            this.s[i].f27a[14] = this.p.a(i5);
            this.s[i].f27a[15] = this.p.a(i5);
        }
        if (this.q.f20b[i].f25a[3] == 0 || i2 == 0) {
            this.s[i].f27a[16] = this.p.a(i5);
            this.s[i].f27a[17] = this.p.a(i5);
            this.s[i].f27a[18] = this.p.a(i5);
            this.s[i].f27a[19] = this.p.a(i5);
            this.s[i].f27a[20] = this.p.a(i5);
        }
        this.s[i].f27a[21] = 0;
        this.s[i].f27a[22] = 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0149, code lost:            if (r0 == 3) goto L31;     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x005e, code lost:            if (r7 != 1) goto L18;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void b(int r7, int r8) {
        /*
            Method dump skipped, instructions count: 661
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.k.b(int, int):void");
    }

    private void c(int i, int i2) {
        int i3 = 0;
        d dVar = this.q.f20b[i].f26b[i2];
        b(i, i2);
        if (dVar.e != 0 && dVar.f == 2) {
            if (dVar.g != 0) {
                for (int i4 = 0; i4 < 8; i4++) {
                    this.s[i].f27a[i4] = this.f17a[i3];
                    i3++;
                }
                for (int i5 = 3; i5 < 12; i5++) {
                    for (int i6 = 0; i6 < 3; i6++) {
                        this.s[i].f28b[i6][i5] = this.f17a[i3];
                        i3++;
                    }
                }
                for (int i7 = 0; i7 < 3; i7++) {
                    this.s[i].f28b[i7][12] = 0;
                }
                return;
            }
            for (int i8 = 0; i8 < 12; i8++) {
                for (int i9 = 0; i9 < 3; i9++) {
                    this.s[i].f28b[i9][i8] = this.f17a[i3];
                    i3++;
                }
            }
            for (int i10 = 0; i10 < 3; i10++) {
                this.s[i].f28b[i10][12] = 0;
            }
            return;
        }
        for (int i11 = 0; i11 < 21; i11++) {
            this.s[i].f27a[i11] = this.f17a[i3];
            i3++;
        }
        this.s[i].f27a[21] = 0;
        this.s[i].f27a[22] = 0;
    }

    private void d(int i, int i2) {
        int i3;
        int i4;
        int i5;
        o oVar;
        this.D[0] = 0;
        this.E[0] = 0;
        this.F[0] = 0;
        this.G[0] = 0;
        int i6 = this.v + this.q.f20b[i].f26b[i2].f23a;
        if (this.q.f20b[i].f26b[i2].e != 0 && this.q.f20b[i].f26b[i2].f == 2) {
            i3 = this.z == 8 ? 72 : 36;
            i4 = 576;
        } else {
            int i7 = this.q.f20b[i].f26b[i2].j + 1;
            int i8 = i7 + this.q.f20b[i].f26b[i2].k + 1;
            int i9 = i8;
            if (i8 > this.O[this.z].f21a.length - 1) {
                i9 = this.O[this.z].f21a.length - 1;
            }
            i3 = this.O[this.z].f21a[i7];
            i4 = this.O[this.z].f21a[i9];
        }
        int i10 = 0;
        for (int i11 = 0; i11 < (this.q.f20b[i].f26b[i2].f24b << 1); i11 += 2) {
            if (i11 < i3) {
                oVar = o.f36a[this.q.f20b[i].f26b[i2].h[0]];
            } else if (i11 < i4) {
                oVar = o.f36a[this.q.f20b[i].f26b[i2].h[1]];
            } else {
                oVar = o.f36a[this.q.f20b[i].f26b[i2].h[2]];
            }
            o.a(oVar, this.D, this.E, this.F, this.G, this.p);
            int i12 = i10;
            int i13 = i10 + 1;
            this.c[i12] = this.D[0];
            i10 = i13 + 1;
            this.c[i13] = this.E[0];
            this.f18b = this.f18b + this.D[0] + this.E[0];
        }
        o oVar2 = o.f36a[this.q.f20b[i].f26b[i2].n + 32];
        int a2 = this.p.a();
        while (true) {
            i5 = a2;
            if (i5 >= i6 || i10 >= 576) {
                break;
            }
            o.a(oVar2, this.D, this.E, this.F, this.G, this.p);
            int i14 = i10;
            int i15 = i10 + 1;
            this.c[i14] = this.F[0];
            int i16 = i15 + 1;
            this.c[i15] = this.G[0];
            int i17 = i16 + 1;
            this.c[i16] = this.D[0];
            i10 = i17 + 1;
            this.c[i17] = this.E[0];
            this.f18b = this.f18b + this.F[0] + this.G[0] + this.D[0] + this.E[0];
            a2 = this.p.a();
        }
        if (i5 > i6) {
            this.p.c(i5 - i6);
            i10 -= 4;
        }
        int a3 = this.p.a();
        if (a3 < i6) {
            this.p.a(i6 - a3);
        }
        if (i10 < 576) {
            this.i[i] = i10;
        } else {
            this.i[i] = 576;
        }
        if (i10 < 0) {
            i10 = 0;
        }
        while (i10 < 576) {
            this.c[i10] = 0;
            i10++;
        }
    }

    private void a(int i, int i2, int i3) {
        if (i == 0) {
            this.h[0][i3] = 1.0f;
            this.h[1][i3] = 1.0f;
        } else if ((i & 1) != 0) {
            this.h[0][i3] = R[i2][(i + 1) >>> 1];
            this.h[1][i3] = 1.0f;
        } else {
            this.h[0][i3] = 1.0f;
            this.h[1][i3] = R[i2][i >>> 1];
        }
    }

    private void a(float[][] fArr, int i, int i2) {
        int i3;
        d dVar = this.q.f20b[i].f26b[i2];
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        if (dVar.e != 0 && dVar.f == 2 && dVar.g == 0) {
            int i8 = this.O[this.z].f22b[1];
            i6 = i8;
            i3 = (i8 << 2) - i6;
            i5 = 0;
        } else {
            i3 = this.O[this.z].f21a[1];
        }
        float pow = (float) Math.pow(2.0d, 0.25d * (dVar.c - 210.0d));
        for (int i9 = 0; i9 < this.i[i]; i9++) {
            int i10 = i9 % 18;
            int i11 = (i9 - i10) / 18;
            if (this.c[i9] == 0) {
                fArr[i11][i10] = 0.0f;
            } else {
                int i12 = this.c[i9];
                if (i12 < Q.length) {
                    if (this.c[i9] > 0) {
                        fArr[i11][i10] = pow * Q[i12];
                    } else if ((-i12) < Q.length) {
                        fArr[i11][i10] = (-pow) * Q[-i12];
                    } else {
                        fArr[i11][i10] = (-pow) * ((float) Math.pow(-i12, 1.3333333333333333d));
                    }
                } else if (this.c[i9] > 0) {
                    fArr[i11][i10] = pow * ((float) Math.pow(i12, 1.3333333333333333d));
                } else {
                    fArr[i11][i10] = (-pow) * ((float) Math.pow(-i12, 1.3333333333333333d));
                }
            }
        }
        for (int i13 = 0; i13 < this.i[i]; i13++) {
            int i14 = i13 % 18;
            int i15 = (i13 - i14) / 18;
            if (i7 == i3) {
                if (dVar.e != 0 && dVar.f == 2) {
                    if (dVar.g != 0) {
                        if (i7 == this.O[this.z].f21a[8]) {
                            int i16 = this.O[this.z].f22b[4];
                            i3 = (i16 << 2) - i16;
                            i4 = 3;
                            i6 = this.O[this.z].f22b[4] - this.O[this.z].f22b[3];
                            int i17 = this.O[this.z].f22b[3];
                            i5 = (i17 << 2) - i17;
                        } else if (i7 >= this.O[this.z].f21a[8]) {
                            i4++;
                            int i18 = this.O[this.z].f22b[i4 + 1];
                            i3 = (i18 << 2) - i18;
                            int i19 = this.O[this.z].f22b[i4];
                            i6 = this.O[this.z].f22b[i4 + 1] - i19;
                            i5 = (i19 << 2) - i19;
                        }
                    } else {
                        i4++;
                        int i20 = this.O[this.z].f22b[i4 + 1];
                        i3 = (i20 << 2) - i20;
                        int i21 = this.O[this.z].f22b[i4];
                        i6 = this.O[this.z].f22b[i4 + 1] - i21;
                        i5 = (i21 << 2) - i21;
                    }
                }
                i4++;
                i3 = this.O[this.z].f21a[i4 + 1];
            }
            if (dVar.e != 0 && ((dVar.f == 2 && dVar.g == 0) || (dVar.f == 2 && dVar.g != 0 && i13 >= 36))) {
                int i22 = (i7 - i5) / i6;
                int i23 = (this.s[i].f28b[i22][i4] << dVar.m) + (dVar.i[i22] << 2);
                float[] fArr2 = fArr[i15];
                fArr2[i14] = fArr2[i14] * P[i23];
            } else {
                int i24 = this.s[i].f27a[i4];
                if (dVar.l != 0) {
                    i24 += N[i4];
                }
                int i25 = i24 << dVar.m;
                float[] fArr3 = fArr[i15];
                fArr3[i14] = fArr3[i14] * P[i25];
            }
            i7++;
        }
        for (int i26 = this.i[i]; i26 < 576; i26++) {
            int i27 = i26 % 18;
            int i28 = (i26 - i27) / 18;
            if (i27 < 0) {
                i27 = 0;
            }
            if (i28 < 0) {
                i28 = 0;
            }
            fArr[i28][i27] = 0.0f;
        }
    }

    private void b(float[][] fArr, int i, int i2) {
        d dVar = this.q.f20b[i].f26b[i2];
        if (dVar.e != 0 && dVar.f == 2) {
            for (int i3 = 0; i3 < 576; i3++) {
                this.f[i3] = 0.0f;
            }
            if (dVar.g != 0) {
                for (int i4 = 0; i4 < 36; i4++) {
                    int i5 = i4 % 18;
                    this.f[i4] = fArr[(i4 - i5) / 18][i5];
                }
                for (int i6 = 3; i6 < 13; i6++) {
                    int i7 = this.O[this.z].f22b[i6];
                    int i8 = this.O[this.z].f22b[i6 + 1] - i7;
                    int i9 = (i7 << 2) - i7;
                    int i10 = 0;
                    int i11 = 0;
                    while (i10 < i8) {
                        int i12 = i9 + i10;
                        int i13 = i9 + i11;
                        int i14 = i12 % 18;
                        this.f[i13] = fArr[(i12 - i14) / 18][i14];
                        int i15 = i12 + i8;
                        int i16 = i13 + 1;
                        int i17 = i15 % 18;
                        this.f[i16] = fArr[(i15 - i17) / 18][i17];
                        int i18 = i15 + i8;
                        int i19 = i18 % 18;
                        this.f[i16 + 1] = fArr[(i18 - i19) / 18][i19];
                        i10++;
                        i11 += 3;
                    }
                }
                return;
            }
            for (int i20 = 0; i20 < 576; i20++) {
                int i21 = T[this.z][i20];
                int i22 = i21 % 18;
                this.f[i20] = fArr[(i21 - i22) / 18][i22];
            }
            return;
        }
        for (int i23 = 0; i23 < 576; i23++) {
            int i24 = i23 % 18;
            this.f[i23] = fArr[(i23 - i24) / 18][i24];
        }
    }

    private void a(int i) {
        if (this.w == 1) {
            for (int i2 = 0; i2 < 32; i2++) {
                for (int i3 = 0; i3 < 18; i3 += 3) {
                    this.e[0][i2][i3] = this.d[0][i2][i3];
                    this.e[0][i2][i3 + 1] = this.d[0][i2][i3 + 1];
                    this.e[0][i2][i3 + 2] = this.d[0][i2][i3 + 2];
                }
            }
            return;
        }
        d dVar = this.q.f20b[0].f26b[i];
        int i4 = this.k.i();
        boolean z = this.k.f() == 1 && (i4 & 2) != 0;
        boolean z2 = this.k.f() == 1 && (i4 & 1) != 0;
        boolean z3 = this.k.a() == 0 || this.k.a() == 2;
        int i5 = dVar.d & 1;
        for (int i6 = 0; i6 < 576; i6++) {
            this.H[i6] = 7;
            this.I[i6] = 0.0f;
        }
        if (z2) {
            if (dVar.e != 0 && dVar.f == 2) {
                if (dVar.g != 0) {
                    int i7 = 0;
                    for (int i8 = 0; i8 < 3; i8++) {
                        int i9 = 2;
                        int i10 = 12;
                        while (i10 >= 3) {
                            int i11 = this.O[this.z].f22b[i10];
                            int i12 = this.O[this.z].f22b[i10 + 1] - i11;
                            int i13 = (((i11 << 2) - i11) + ((i8 + 1) * i12)) - 1;
                            while (i12 > 0) {
                                if (this.d[1][i13 / 18][i13 % 18] != 0.0f) {
                                    i9 = i10;
                                    i10 = -10;
                                    i12 = -10;
                                }
                                i12--;
                                i13--;
                            }
                            i10--;
                        }
                        int i14 = i9 + 1;
                        int i15 = i14;
                        if (i14 > i7) {
                            i7 = i15;
                        }
                        while (i15 < 12) {
                            int i16 = this.O[this.z].f22b[i15];
                            int i17 = this.O[this.z].f22b[i15 + 1] - i16;
                            int i18 = ((i16 << 2) - i16) + (i8 * i17);
                            while (i17 > 0) {
                                this.H[i18] = this.s[1].f28b[i8][i15];
                                if (this.H[i18] != 7) {
                                    if (z3) {
                                        a(this.H[i18], i5, i18);
                                    } else {
                                        this.I[i18] = S[this.H[i18]];
                                    }
                                }
                                i18++;
                                i17--;
                            }
                            i15++;
                        }
                        int i19 = this.O[this.z].f22b[10];
                        int i20 = ((i19 << 2) - i19) + (i8 * (this.O[this.z].f22b[11] - i19));
                        int i21 = this.O[this.z].f22b[11];
                        int i22 = this.O[this.z].f22b[12] - i21;
                        int i23 = ((i21 << 2) - i21) + (i8 * i22);
                        while (i22 > 0) {
                            int[] iArr = this.H;
                            iArr[i23] = iArr[i20];
                            if (z3) {
                                this.h[0][i23] = this.h[0][i20];
                                this.h[1][i23] = this.h[1][i20];
                            } else {
                                float[] fArr = this.I;
                                fArr[i23] = fArr[i20];
                            }
                            i23++;
                            i22--;
                        }
                    }
                    if (i7 <= 3) {
                        int i24 = 2;
                        int i25 = 17;
                        int i26 = -1;
                        while (i24 >= 0) {
                            if (this.d[1][i24][i25] != 0.0f) {
                                i26 = (i24 << 4) + (i24 << 1) + i25;
                                i24 = -1;
                            } else {
                                i25--;
                                if (i25 < 0) {
                                    i24--;
                                    i25 = 17;
                                }
                            }
                        }
                        int i27 = 0;
                        while (this.O[this.z].f21a[i27] <= i26) {
                            i27++;
                        }
                        int i28 = this.O[this.z].f21a[i27];
                        for (int i29 = i27; i29 < 8; i29++) {
                            for (int i30 = this.O[this.z].f21a[i29 + 1] - this.O[this.z].f21a[i29]; i30 > 0; i30--) {
                                this.H[i28] = this.s[1].f27a[i29];
                                if (this.H[i28] != 7) {
                                    if (z3) {
                                        a(this.H[i28], i5, i28);
                                    } else {
                                        this.I[i28] = S[this.H[i28]];
                                    }
                                }
                                i28++;
                            }
                        }
                    }
                } else {
                    for (int i31 = 0; i31 < 3; i31++) {
                        int i32 = -1;
                        int i33 = 12;
                        while (i33 >= 0) {
                            int i34 = this.O[this.z].f22b[i33];
                            int i35 = this.O[this.z].f22b[i33 + 1] - i34;
                            int i36 = (((i34 << 2) - i34) + ((i31 + 1) * i35)) - 1;
                            while (i35 > 0) {
                                if (this.d[1][i36 / 18][i36 % 18] != 0.0f) {
                                    i32 = i33;
                                    i33 = -10;
                                    i35 = -10;
                                }
                                i35--;
                                i36--;
                            }
                            i33--;
                        }
                        for (int i37 = i32 + 1; i37 < 12; i37++) {
                            int i38 = this.O[this.z].f22b[i37];
                            int i39 = this.O[this.z].f22b[i37 + 1] - i38;
                            int i40 = ((i38 << 2) - i38) + (i31 * i39);
                            while (i39 > 0) {
                                this.H[i40] = this.s[1].f28b[i31][i37];
                                if (this.H[i40] != 7) {
                                    if (z3) {
                                        a(this.H[i40], i5, i40);
                                    } else {
                                        this.I[i40] = S[this.H[i40]];
                                    }
                                }
                                i40++;
                                i39--;
                            }
                        }
                        int i41 = this.O[this.z].f22b[10];
                        int i42 = this.O[this.z].f22b[11];
                        int i43 = ((i41 << 2) - i41) + (i31 * (i42 - i41));
                        int i44 = this.O[this.z].f22b[12] - i42;
                        int i45 = ((i42 << 2) - i42) + (i31 * i44);
                        while (i44 > 0) {
                            int[] iArr2 = this.H;
                            iArr2[i45] = iArr2[i43];
                            if (z3) {
                                this.h[0][i45] = this.h[0][i43];
                                this.h[1][i45] = this.h[1][i43];
                            } else {
                                float[] fArr2 = this.I;
                                fArr2[i45] = fArr2[i43];
                            }
                            i45++;
                            i44--;
                        }
                    }
                }
            } else {
                int i46 = 31;
                int i47 = 17;
                int i48 = 0;
                while (i46 >= 0) {
                    if (this.d[1][i46][i47] != 0.0f) {
                        i48 = (i46 << 4) + (i46 << 1) + i47;
                        i46 = -1;
                    } else {
                        i47--;
                        if (i47 < 0) {
                            i46--;
                            i47 = 17;
                        }
                    }
                }
                int i49 = 0;
                while (this.O[this.z].f21a[i49] <= i48) {
                    i49++;
                }
                int i50 = this.O[this.z].f21a[i49];
                for (int i51 = i49; i51 < 21; i51++) {
                    for (int i52 = this.O[this.z].f21a[i51 + 1] - this.O[this.z].f21a[i51]; i52 > 0; i52--) {
                        this.H[i50] = this.s[1].f27a[i51];
                        if (this.H[i50] != 7) {
                            if (z3) {
                                a(this.H[i50], i5, i50);
                            } else {
                                this.I[i50] = S[this.H[i50]];
                            }
                        }
                        i50++;
                    }
                }
                int i53 = this.O[this.z].f21a[20];
                for (int i54 = User32.WM_TOUCH - this.O[this.z].f21a[21]; i54 > 0 && i50 < 576; i54--) {
                    int[] iArr3 = this.H;
                    iArr3[i50] = iArr3[i53];
                    if (z3) {
                        this.h[0][i50] = this.h[0][i53];
                        this.h[1][i50] = this.h[1][i53];
                    } else {
                        float[] fArr3 = this.I;
                        fArr3[i50] = fArr3[i53];
                    }
                    i50++;
                }
            }
        }
        int i55 = 0;
        for (int i56 = 0; i56 < 32; i56++) {
            for (int i57 = 0; i57 < 18; i57++) {
                if (this.H[i55] == 7) {
                    if (z) {
                        this.e[0][i56][i57] = (this.d[0][i56][i57] + this.d[1][i56][i57]) * 0.70710677f;
                        this.e[1][i56][i57] = (this.d[0][i56][i57] - this.d[1][i56][i57]) * 0.70710677f;
                    } else {
                        this.e[0][i56][i57] = this.d[0][i56][i57];
                        this.e[1][i56][i57] = this.d[1][i56][i57];
                    }
                } else if (z2) {
                    if (z3) {
                        this.e[0][i56][i57] = this.d[0][i56][i57] * this.h[0][i55];
                        this.e[1][i56][i57] = this.d[0][i56][i57] * this.h[1][i55];
                    } else {
                        this.e[1][i56][i57] = this.d[0][i56][i57] / (1.0f + this.I[i55]);
                        this.e[0][i56][i57] = this.e[1][i56][i57] * this.I[i55];
                    }
                }
                i55++;
            }
        }
    }

    private void e(int i, int i2) {
        int i3;
        d dVar = this.q.f20b[i].f26b[i2];
        if (dVar.e != 0 && dVar.f == 2 && dVar.g == 0) {
            return;
        }
        if (dVar.e != 0 && dVar.g != 0 && dVar.f == 2) {
            i3 = 18;
        } else {
            i3 = 558;
        }
        for (int i4 = 0; i4 < i3; i4 += 18) {
            for (int i5 = 0; i5 < 8; i5++) {
                int i6 = (i4 + 17) - i5;
                int i7 = i4 + 18 + i5;
                float f2 = this.f[i6];
                float f3 = this.f[i7];
                this.f[i6] = (f2 * U[i5]) - (f3 * V[i5]);
                this.f[i7] = (f3 * U[i5]) + (f2 * V[i5]);
            }
        }
    }

    private void f(int i, int i2) {
        d dVar = this.q.f20b[i].f26b[i2];
        int i3 = 0;
        while (i3 < 576) {
            int i4 = (dVar.e == 0 || dVar.g == 0 || i3 >= 36) ? dVar.f : 0;
            float[] fArr = this.f;
            for (int i5 = 0; i5 < 18; i5++) {
                this.J[i5] = fArr[i5 + i3];
            }
            a(this.J, this.K, i4);
            for (int i6 = 0; i6 < 18; i6++) {
                fArr[i6 + i3] = this.J[i6];
            }
            float[][] fArr2 = this.g;
            fArr[i3 + 0] = this.K[0] + fArr2[i][i3];
            fArr2[i][i3] = this.K[18];
            fArr[i3 + 1] = this.K[1] + fArr2[i][i3 + 1];
            fArr2[i][i3 + 1] = this.K[19];
            fArr[i3 + 2] = this.K[2] + fArr2[i][i3 + 2];
            fArr2[i][i3 + 2] = this.K[20];
            fArr[i3 + 3] = this.K[3] + fArr2[i][i3 + 3];
            fArr2[i][i3 + 3] = this.K[21];
            fArr[i3 + 4] = this.K[4] + fArr2[i][i3 + 4];
            fArr2[i][i3 + 4] = this.K[22];
            fArr[i3 + 5] = this.K[5] + fArr2[i][i3 + 5];
            fArr2[i][i3 + 5] = this.K[23];
            fArr[i3 + 6] = this.K[6] + fArr2[i][i3 + 6];
            fArr2[i][i3 + 6] = this.K[24];
            fArr[i3 + 7] = this.K[7] + fArr2[i][i3 + 7];
            fArr2[i][i3 + 7] = this.K[25];
            fArr[i3 + 8] = this.K[8] + fArr2[i][i3 + 8];
            fArr2[i][i3 + 8] = this.K[26];
            fArr[i3 + 9] = this.K[9] + fArr2[i][i3 + 9];
            fArr2[i][i3 + 9] = this.K[27];
            fArr[i3 + 10] = this.K[10] + fArr2[i][i3 + 10];
            fArr2[i][i3 + 10] = this.K[28];
            fArr[i3 + 11] = this.K[11] + fArr2[i][i3 + 11];
            fArr2[i][i3 + 11] = this.K[29];
            fArr[i3 + 12] = this.K[12] + fArr2[i][i3 + 12];
            fArr2[i][i3 + 12] = this.K[30];
            fArr[i3 + 13] = this.K[13] + fArr2[i][i3 + 13];
            fArr2[i][i3 + 13] = this.K[31];
            fArr[i3 + 14] = this.K[14] + fArr2[i][i3 + 14];
            fArr2[i][i3 + 14] = this.K[32];
            fArr[i3 + 15] = this.K[15] + fArr2[i][i3 + 15];
            fArr2[i][i3 + 15] = this.K[33];
            fArr[i3 + 16] = this.K[16] + fArr2[i][i3 + 16];
            fArr2[i][i3 + 16] = this.K[34];
            fArr[i3 + 17] = this.K[17] + fArr2[i][i3 + 17];
            fArr2[i][i3 + 17] = this.K[35];
            i3 += 18;
        }
    }

    private void d() {
        for (int i = 0; i < 18; i++) {
            for (int i2 = 0; i2 < 18; i2 += 3) {
                this.e[0][i][i2] = (this.e[0][i][i2] + this.e[1][i][i2]) * 0.5f;
                this.e[0][i][i2 + 1] = (this.e[0][i][i2 + 1] + this.e[1][i][i2 + 1]) * 0.5f;
                this.e[0][i][i2 + 2] = (this.e[0][i][i2 + 2] + this.e[1][i][i2 + 2]) * 0.5f;
            }
        }
    }

    private static void a(float[] fArr, float[] fArr2, int i) {
        if (i == 2) {
            fArr2[0] = 0.0f;
            fArr2[1] = 0.0f;
            fArr2[2] = 0.0f;
            fArr2[3] = 0.0f;
            fArr2[4] = 0.0f;
            fArr2[5] = 0.0f;
            fArr2[6] = 0.0f;
            fArr2[7] = 0.0f;
            fArr2[8] = 0.0f;
            fArr2[9] = 0.0f;
            fArr2[10] = 0.0f;
            fArr2[11] = 0.0f;
            fArr2[12] = 0.0f;
            fArr2[13] = 0.0f;
            fArr2[14] = 0.0f;
            fArr2[15] = 0.0f;
            fArr2[16] = 0.0f;
            fArr2[17] = 0.0f;
            fArr2[18] = 0.0f;
            fArr2[19] = 0.0f;
            fArr2[20] = 0.0f;
            fArr2[21] = 0.0f;
            fArr2[22] = 0.0f;
            fArr2[23] = 0.0f;
            fArr2[24] = 0.0f;
            fArr2[25] = 0.0f;
            fArr2[26] = 0.0f;
            fArr2[27] = 0.0f;
            fArr2[28] = 0.0f;
            fArr2[29] = 0.0f;
            fArr2[30] = 0.0f;
            fArr2[31] = 0.0f;
            fArr2[32] = 0.0f;
            fArr2[33] = 0.0f;
            fArr2[34] = 0.0f;
            fArr2[35] = 0.0f;
            int i2 = 0;
            for (int i3 = 0; i3 < 3; i3++) {
                int i4 = i3 + 15;
                fArr[i4] = fArr[i4] + fArr[i3 + 12];
                int i5 = i3 + 12;
                fArr[i5] = fArr[i5] + fArr[i3 + 9];
                int i6 = i3 + 9;
                fArr[i6] = fArr[i6] + fArr[i3 + 6];
                int i7 = i3 + 6;
                fArr[i7] = fArr[i7] + fArr[i3 + 3];
                int i8 = i3 + 3;
                fArr[i8] = fArr[i8] + fArr[i3 + 0];
                int i9 = i3 + 15;
                fArr[i9] = fArr[i9] + fArr[i3 + 9];
                int i10 = i3 + 9;
                fArr[i10] = fArr[i10] + fArr[i3 + 3];
                float f2 = fArr[i3 + 12] * 0.5f;
                float f3 = fArr[i3 + 6] * 0.8660254f;
                float f4 = fArr[i3 + 0] + f2;
                float f5 = fArr[i3 + 0] - fArr[i3 + 12];
                float f6 = f4 + f3;
                float f7 = f4 - f3;
                float f8 = fArr[i3 + 15] * 0.5f;
                float f9 = fArr[i3 + 9] * 0.8660254f;
                float f10 = fArr[i3 + 3] + f8;
                float f11 = f10 + f9;
                float f12 = (f10 - f9) * 1.9318516f;
                float f13 = (fArr[i3 + 3] - fArr[i3 + 15]) * 0.70710677f;
                float f14 = f11 * 0.5176381f;
                float f15 = f6 + f14;
                float f16 = f6 - f14;
                float f17 = f5 + f13;
                float f18 = f5 - f13;
                float f19 = f7 + f12;
                float f20 = f7 - f12;
                float f21 = f15 * 0.5043145f;
                float f22 = f17 * 0.5411961f;
                float f23 = f19 * 0.6302362f;
                float f24 = f20 * 0.8213398f;
                float f25 = f18 * 1.306563f;
                float f26 = f16 * 3.830649f;
                float f27 = (-f21) * 0.7933533f;
                float f28 = (-f21) * 0.6087614f;
                float f29 = (-f22) * 0.9238795f;
                float f30 = (-f22) * 0.38268343f;
                float f31 = (-f23) * 0.9914449f;
                float f32 = (-f23) * 0.13052619f;
                float f33 = f25 * 0.38268343f;
                float f34 = f26 * 0.6087614f;
                float f35 = (-f26) * 0.7933533f;
                int i11 = i2 + 6;
                fArr2[i11] = fArr2[i11] + (f24 * 0.13052619f);
                int i12 = i2 + 7;
                fArr2[i12] = fArr2[i12] + f33;
                int i13 = i2 + 8;
                fArr2[i13] = fArr2[i13] + f34;
                int i14 = i2 + 9;
                fArr2[i14] = fArr2[i14] + f35;
                int i15 = i2 + 10;
                fArr2[i15] = fArr2[i15] + ((-f25) * 0.9238795f);
                int i16 = i2 + 11;
                fArr2[i16] = fArr2[i16] + ((-f24) * 0.9914449f);
                int i17 = i2 + 12;
                fArr2[i17] = fArr2[i17] + f31;
                int i18 = i2 + 13;
                fArr2[i18] = fArr2[i18] + f29;
                int i19 = i2 + 14;
                fArr2[i19] = fArr2[i19] + f27;
                int i20 = i2 + 15;
                fArr2[i20] = fArr2[i20] + f28;
                int i21 = i2 + 16;
                fArr2[i21] = fArr2[i21] + f30;
                int i22 = i2 + 17;
                fArr2[i22] = fArr2[i22] + f32;
                i2 += 6;
            }
            return;
        }
        fArr[17] = fArr[17] + fArr[16];
        fArr[16] = fArr[16] + fArr[15];
        fArr[15] = fArr[15] + fArr[14];
        fArr[14] = fArr[14] + fArr[13];
        fArr[13] = fArr[13] + fArr[12];
        fArr[12] = fArr[12] + fArr[11];
        fArr[11] = fArr[11] + fArr[10];
        fArr[10] = fArr[10] + fArr[9];
        fArr[9] = fArr[9] + fArr[8];
        fArr[8] = fArr[8] + fArr[7];
        fArr[7] = fArr[7] + fArr[6];
        fArr[6] = fArr[6] + fArr[5];
        fArr[5] = fArr[5] + fArr[4];
        fArr[4] = fArr[4] + fArr[3];
        fArr[3] = fArr[3] + fArr[2];
        fArr[2] = fArr[2] + fArr[1];
        fArr[1] = fArr[1] + fArr[0];
        fArr[17] = fArr[17] + fArr[15];
        fArr[15] = fArr[15] + fArr[13];
        fArr[13] = fArr[13] + fArr[11];
        fArr[11] = fArr[11] + fArr[9];
        fArr[9] = fArr[9] + fArr[7];
        fArr[7] = fArr[7] + fArr[5];
        fArr[5] = fArr[5] + fArr[3];
        fArr[3] = fArr[3] + fArr[1];
        float f36 = fArr[0] + fArr[0];
        float f37 = f36 + fArr[12];
        float f38 = f37 + (fArr[4] * 1.8793852f) + (fArr[8] * 1.5320889f) + (fArr[16] * 0.34729636f);
        float f39 = ((((f36 + fArr[4]) - fArr[8]) - fArr[12]) - fArr[12]) - fArr[16];
        float f40 = ((f37 - (fArr[4] * 0.34729636f)) - (fArr[8] * 1.8793852f)) + (fArr[16] * 1.5320889f);
        float f41 = ((f37 - (fArr[4] * 1.5320889f)) + (fArr[8] * 0.34729636f)) - (fArr[16] * 1.8793852f);
        float f42 = (((fArr[0] - fArr[4]) + fArr[8]) - fArr[12]) + fArr[16];
        float f43 = fArr[6] * 1.7320508f;
        float f44 = (fArr[2] * 1.9696155f) + f43 + (fArr[10] * 1.2855753f) + (fArr[14] * 0.6840403f);
        float f45 = ((fArr[2] - fArr[10]) - fArr[14]) * 1.7320508f;
        float f46 = (((fArr[2] * 1.2855753f) - f43) - (fArr[10] * 0.6840403f)) + (fArr[14] * 1.9696155f);
        float f47 = (((fArr[2] * 0.6840403f) - f43) + (fArr[10] * 1.9696155f)) - (fArr[14] * 1.2855753f);
        float f48 = fArr[1] + fArr[1];
        float f49 = f48 + fArr[13];
        float f50 = f49 + (fArr[5] * 1.8793852f) + (fArr[9] * 1.5320889f) + (fArr[17] * 0.34729636f);
        float f51 = ((((f48 + fArr[5]) - fArr[9]) - fArr[13]) - fArr[13]) - fArr[17];
        float f52 = ((f49 - (fArr[5] * 0.34729636f)) - (fArr[9] * 1.8793852f)) + (fArr[17] * 1.5320889f);
        float f53 = ((f49 - (fArr[5] * 1.5320889f)) + (fArr[9] * 0.34729636f)) - (fArr[17] * 1.8793852f);
        float f54 = ((((fArr[1] - fArr[5]) + fArr[9]) - fArr[13]) + fArr[17]) * 0.70710677f;
        float f55 = fArr[7] * 1.7320508f;
        float f56 = (fArr[3] * 1.9696155f) + f55 + (fArr[11] * 1.2855753f) + (fArr[15] * 0.6840403f);
        float f57 = ((fArr[3] - fArr[11]) - fArr[15]) * 1.7320508f;
        float f58 = (((fArr[3] * 1.2855753f) - f55) - (fArr[11] * 0.6840403f)) + (fArr[15] * 1.9696155f);
        float f59 = (((fArr[3] * 0.6840403f) - f55) + (fArr[11] * 1.9696155f)) - (fArr[15] * 1.2855753f);
        float f60 = f38 + f44;
        float f61 = (f50 + f56) * 0.5019099f;
        float f62 = f60 + f61;
        float f63 = f60 - f61;
        float f64 = f39 + f45;
        float f65 = (f51 + f57) * 0.5176381f;
        float f66 = f64 + f65;
        float f67 = f64 - f65;
        float f68 = f40 + f46;
        float f69 = (f52 + f58) * 0.55168897f;
        float f70 = f68 + f69;
        float f71 = f68 - f69;
        float f72 = f41 + f47;
        float f73 = (f53 + f59) * 0.61038727f;
        float f74 = f72 + f73;
        float f75 = f72 - f73;
        float f76 = f42 + f54;
        float f77 = f42 - f54;
        float f78 = f41 - f47;
        float f79 = (f53 - f59) * 0.8717234f;
        float f80 = f78 + f79;
        float f81 = f78 - f79;
        float f82 = f40 - f46;
        float f83 = (f52 - f58) * 1.1831008f;
        float f84 = f82 + f83;
        float f85 = f82 - f83;
        float f86 = f39 - f45;
        float f87 = (f51 - f57) * 1.9318516f;
        float f88 = f86 + f87;
        float f89 = f86 - f87;
        float f90 = f38 - f44;
        float f91 = (f50 - f56) * 5.7368565f;
        float f92 = f90 + f91;
        float f93 = f90 - f91;
        float[] fArr3 = W[i];
        fArr2[0] = (-f93) * fArr3[0];
        fArr2[1] = (-f89) * fArr3[1];
        fArr2[2] = (-f85) * fArr3[2];
        fArr2[3] = (-f81) * fArr3[3];
        fArr2[4] = (-f77) * fArr3[4];
        fArr2[5] = (-f75) * fArr3[5];
        fArr2[6] = (-f71) * fArr3[6];
        fArr2[7] = (-f67) * fArr3[7];
        fArr2[8] = (-f63) * fArr3[8];
        fArr2[9] = f63 * fArr3[9];
        fArr2[10] = f67 * fArr3[10];
        fArr2[11] = f71 * fArr3[11];
        fArr2[12] = f75 * fArr3[12];
        fArr2[13] = f77 * fArr3[13];
        fArr2[14] = f81 * fArr3[14];
        fArr2[15] = f85 * fArr3[15];
        fArr2[16] = f89 * fArr3[16];
        fArr2[17] = f93 * fArr3[17];
        fArr2[18] = f92 * fArr3[18];
        fArr2[19] = f88 * fArr3[19];
        fArr2[20] = f84 * fArr3[20];
        fArr2[21] = f80 * fArr3[21];
        fArr2[22] = f76 * fArr3[22];
        fArr2[23] = f74 * fArr3[23];
        fArr2[24] = f70 * fArr3[24];
        fArr2[25] = f66 * fArr3[25];
        fArr2[26] = f62 * fArr3[26];
        fArr2[27] = f62 * fArr3[27];
        fArr2[28] = f66 * fArr3[28];
        fArr2[29] = f70 * fArr3[29];
        fArr2[30] = f74 * fArr3[30];
        fArr2[31] = f76 * fArr3[31];
        fArr2[32] = f80 * fArr3[32];
        fArr2[33] = f84 * fArr3[33];
        fArr2[34] = f88 * fArr3[34];
        fArr2[35] = f92 * fArr3[35];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:b/a/a/k$b.class */
    public static class b {

        /* renamed from: a, reason: collision with root package name */
        public int[] f21a;

        /* renamed from: b, reason: collision with root package name */
        public int[] f22b;

        public b() {
            this.f21a = new int[23];
            this.f22b = new int[14];
        }

        public b(int[] iArr, int[] iArr2) {
            this.f21a = iArr;
            this.f22b = iArr2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:b/a/a/k$e.class */
    public static class e {

        /* renamed from: a, reason: collision with root package name */
        public int[] f25a = new int[4];

        /* renamed from: b, reason: collision with root package name */
        public d[] f26b = new d[2];

        public e() {
            this.f26b[0] = new d();
            this.f26b[1] = new d();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:b/a/a/k$a.class */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public int f19a = 0;

        /* renamed from: b, reason: collision with root package name */
        public e[] f20b = new e[2];

        public a() {
            this.f20b[0] = new e();
            this.f20b[1] = new e();
        }
    }

    private static float[] e() {
        float[] fArr = new float[8192];
        for (int i = 0; i < 8192; i++) {
            int i2 = i;
            fArr[i2] = (float) Math.pow(i2, 1.3333333333333333d);
        }
        return fArr;
    }

    private static int[] a(int[] iArr) {
        int i = 0;
        int[] iArr2 = new int[User32.WM_TOUCH];
        for (int i2 = 0; i2 < 13; i2++) {
            int i3 = iArr[i2];
            int i4 = iArr[i2 + 1];
            for (int i5 = 0; i5 < 3; i5++) {
                for (int i6 = i3; i6 < i4; i6++) {
                    int i7 = i;
                    i++;
                    iArr2[(3 * i6) + i5] = i7;
                }
            }
        }
        return iArr2;
    }

    /* loaded from: infinitode-2.jar:b/a/a/k$c.class */
    class c {
        public c(k kVar, int[] iArr, int[] iArr2) {
        }
    }
}
