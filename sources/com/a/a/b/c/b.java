package com.a.a.b.c;

import java.util.Arrays;
import org.lwjgl.opengl.CGL;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:com/a/a/b/c/b.class */
public final class b {

    /* renamed from: b, reason: collision with root package name */
    private static char[] f99b = "0123456789ABCDEF".toCharArray();
    private static char[] c = "0123456789abcdef".toCharArray();
    private static byte[] d;
    private static byte[] e;
    private static int[] f;
    private static int[] g;
    private static int[] h;
    private static int[] i;
    private static int[] j;

    /* renamed from: a, reason: collision with root package name */
    protected static final int[] f100a;
    private static int[] k;

    static {
        int i2;
        int length = f99b.length;
        d = new byte[length];
        e = new byte[length];
        for (int i3 = 0; i3 < length; i3++) {
            d[i3] = (byte) f99b[i3];
            e[i3] = (byte) c[i3];
        }
        int[] iArr = new int[256];
        for (int i4 = 0; i4 < 32; i4++) {
            iArr[i4] = -1;
        }
        iArr[34] = 1;
        iArr[92] = 1;
        f = iArr;
        int[] iArr2 = new int[iArr.length];
        System.arraycopy(f, 0, iArr2, 0, iArr2.length);
        for (int i5 = 128; i5 < 256; i5++) {
            if ((i5 & CGL.kCGLCPDispatchTableSize) == 192) {
                i2 = 2;
            } else if ((i5 & User32.VK_OEM_ATTN) == 224) {
                i2 = 3;
            } else if ((i5 & User32.VK_EXSEL) == 240) {
                i2 = 4;
            } else {
                i2 = -1;
            }
            iArr2[i5] = i2;
        }
        g = iArr2;
        int[] iArr3 = new int[256];
        Arrays.fill(iArr3, -1);
        for (int i6 = 33; i6 < 256; i6++) {
            if (Character.isJavaIdentifierPart((char) i6)) {
                iArr3[i6] = 0;
            }
        }
        iArr3[64] = 0;
        iArr3[35] = 0;
        iArr3[42] = 0;
        iArr3[45] = 0;
        iArr3[43] = 0;
        h = iArr3;
        int[] iArr4 = new int[256];
        System.arraycopy(h, 0, iArr4, 0, 256);
        Arrays.fill(iArr4, 128, 128, 0);
        i = iArr4;
        int[] iArr5 = new int[256];
        System.arraycopy(g, 128, iArr5, 128, 128);
        Arrays.fill(iArr5, 0, 32, -1);
        iArr5[9] = 0;
        iArr5[10] = 10;
        iArr5[13] = 13;
        iArr5[42] = 42;
        j = iArr5;
        int[] iArr6 = new int[256];
        System.arraycopy(g, 128, iArr6, 128, 128);
        Arrays.fill(iArr6, 0, 32, -1);
        iArr6[32] = 1;
        iArr6[9] = 1;
        iArr6[10] = 10;
        iArr6[13] = 13;
        iArr6[47] = 47;
        iArr6[35] = 35;
        int[] iArr7 = new int[128];
        for (int i7 = 0; i7 < 32; i7++) {
            iArr7[i7] = -1;
        }
        iArr7[34] = 34;
        iArr7[92] = 92;
        iArr7[8] = 98;
        iArr7[9] = 116;
        iArr7[12] = 102;
        iArr7[10] = 110;
        iArr7[13] = 114;
        f100a = iArr7;
        int[] iArr8 = new int[256];
        k = iArr8;
        Arrays.fill(iArr8, -1);
        for (int i8 = 0; i8 < 10; i8++) {
            k[i8 + 48] = i8;
        }
        for (int i9 = 0; i9 < 6; i9++) {
            k[i9 + 97] = i9 + 10;
            k[i9 + 65] = i9 + 10;
        }
    }

    public static int[] a() {
        return f;
    }

    public static int[] b() {
        return g;
    }

    public static int[] c() {
        return h;
    }

    public static int[] d() {
        return i;
    }

    public static int[] e() {
        return j;
    }

    public static int[] f() {
        return f100a;
    }

    public static int[] a(int i2) {
        if (i2 == 34) {
            return f100a;
        }
        return a.f103a.a(i2);
    }

    public static int b(int i2) {
        return k[i2 & 255];
    }

    public static char c(int i2) {
        return f99b[i2];
    }

    public static void a(StringBuilder sb, String str) {
        int[] iArr = f100a;
        int length = iArr.length;
        int length2 = str.length();
        for (int i2 = 0; i2 < length2; i2++) {
            char charAt = str.charAt(i2);
            if (charAt >= length || iArr[charAt] == 0) {
                sb.append(charAt);
            } else {
                sb.append('\\');
                int i3 = iArr[charAt];
                if (i3 < 0) {
                    sb.append('u');
                    sb.append('0');
                    sb.append('0');
                    sb.append(f99b[charAt >> 4]);
                    sb.append(f99b[charAt & 15]);
                } else {
                    sb.append((char) i3);
                }
            }
        }
    }

    public static char[] a(boolean z) {
        return z ? (char[]) f99b.clone() : (char[]) c.clone();
    }

    public static byte[] b(boolean z) {
        return (byte[]) d.clone();
    }

    /* loaded from: infinitode-2.jar:com/a/a/b/c/b$a.class */
    static class a {

        /* renamed from: a, reason: collision with root package name */
        public static final a f103a = new a();

        /* renamed from: b, reason: collision with root package name */
        private int[][] f104b = new int[128];

        /* JADX WARN: Type inference failed for: r1v1, types: [int[], int[][]] */
        private a() {
        }

        public final int[] a(int i) {
            int[] iArr = this.f104b[i];
            int[] iArr2 = iArr;
            if (iArr == null) {
                int[] copyOf = Arrays.copyOf(b.f100a, 128);
                iArr2 = copyOf;
                if (copyOf[i] == 0) {
                    iArr2[i] = -1;
                }
                this.f104b[i] = iArr2;
            }
            return iArr2;
        }
    }
}
