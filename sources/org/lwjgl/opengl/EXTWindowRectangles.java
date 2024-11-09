package org.lwjgl.opengl;

import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTWindowRectangles.class */
public class EXTWindowRectangles {
    public static final int GL_INCLUSIVE_EXT = 36624;
    public static final int GL_EXCLUSIVE_EXT = 36625;
    public static final int GL_WINDOW_RECTANGLE_EXT = 36626;
    public static final int GL_WINDOW_RECTANGLE_MODE_EXT = 36627;
    public static final int GL_MAX_WINDOW_RECTANGLES_EXT = 36628;
    public static final int GL_NUM_WINDOW_RECTANGLES_EXT = 36629;

    public static native void nglWindowRectanglesEXT(int i, int i2, long j);

    static {
        GL.initialize();
    }

    protected EXTWindowRectangles() {
        throw new UnsupportedOperationException();
    }

    public static void glWindowRectanglesEXT(@NativeType("GLenum") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        nglWindowRectanglesEXT(i, Checks.remainingSafe(intBuffer) >> 2, MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glWindowRectanglesEXT(@NativeType("GLenum") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glWindowRectanglesEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, Checks.lengthSafe(iArr) >> 2, iArr, j);
    }
}
