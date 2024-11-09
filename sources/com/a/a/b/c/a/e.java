package com.a.a.b.c.a;

import org.lwjgl.opengl.CGL;

/* loaded from: infinitode-2.jar:com/a/a/b/c/a/e.class */
public /* synthetic */ class e {
    public static double a(CharSequence charSequence) {
        return a(charSequence, 0, charSequence.length());
    }

    private static double a(CharSequence charSequence, int i, int i2) {
        long a2 = new c().a(charSequence, 0, i2);
        if (a2 == -1) {
            throw new NumberFormatException("Illegal input");
        }
        return Double.longBitsToDouble(a2);
    }

    public static int a(long j, long j2) {
        long j3 = j - 13511005043687472L;
        long j4 = j2 - 13511005043687472L;
        if ((((j + 19703549022044230L) | j3 | (j2 + 19703549022044230L) | j4) & (-35747867511423104L)) != 0) {
            return -1;
        }
        return ((int) ((j4 * 281475406208040961L) >>> 48)) + (((int) ((j3 * 281475406208040961L) >>> 48)) * CGL.kCGLBadAttribute);
    }

    public static float b(CharSequence charSequence) {
        return b(charSequence, 0, charSequence.length());
    }

    private static float b(CharSequence charSequence, int i, int i2) {
        long a2 = new g().a(charSequence, 0, i2);
        if (a2 == -1) {
            throw new NumberFormatException("Illegal input");
        }
        return Float.intBitsToFloat((int) a2);
    }

    public String toString() {
        Object[] objArr = new Object[3];
        throw null;
    }
}
