package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.LongBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTTimerQuery.class */
public class EXTTimerQuery {
    public static final int GL_TIME_ELAPSED_EXT = 35007;

    public static native void nglGetQueryObjecti64vEXT(int i, int i2, long j);

    public static native void nglGetQueryObjectui64vEXT(int i, int i2, long j);

    static {
        GL.initialize();
    }

    protected EXTTimerQuery() {
        throw new UnsupportedOperationException();
    }

    public static void glGetQueryObjecti64vEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint64 *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglGetQueryObjecti64vEXT(i, i2, MemoryUtil.memAddress(longBuffer));
    }

    public static void glGetQueryObjecti64vEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint64 *") long j) {
        nglGetQueryObjecti64vEXT(i, i2, j);
    }

    @NativeType("void")
    public static long glGetQueryObjecti64EXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nglGetQueryObjecti64vEXT(i, i2, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetQueryObjectui64vEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint64 *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglGetQueryObjectui64vEXT(i, i2, MemoryUtil.memAddress(longBuffer));
    }

    public static void glGetQueryObjectui64vEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint64 *") long j) {
        nglGetQueryObjectui64vEXT(i, i2, j);
    }

    @NativeType("void")
    public static long glGetQueryObjectui64EXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nglGetQueryObjectui64vEXT(i, i2, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetQueryObjecti64vEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint64 *") long[] jArr) {
        long j = GL.getICD().glGetQueryObjecti64vEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, i2, jArr, j);
    }

    public static void glGetQueryObjectui64vEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint64 *") long[] jArr) {
        long j = GL.getICD().glGetQueryObjectui64vEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, i2, jArr, j);
    }
}
