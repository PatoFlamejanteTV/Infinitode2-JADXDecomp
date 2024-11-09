package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVPointSprite.class */
public class NVPointSprite {
    public static final int GL_POINT_SPRITE_NV = 34913;
    public static final int GL_COORD_REPLACE_NV = 34914;
    public static final int GL_POINT_SPRITE_R_MODE_NV = 34915;

    public static native void glPointParameteriNV(@NativeType("GLenum") int i, @NativeType("GLint") int i2);

    public static native void nglPointParameterivNV(int i, long j);

    static {
        GL.initialize();
    }

    protected NVPointSprite() {
        throw new UnsupportedOperationException();
    }

    public static void glPointParameterivNV(@NativeType("GLenum") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglPointParameterivNV(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glPointParameterivNV(@NativeType("GLenum") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glPointParameterivNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, iArr, j);
    }
}
