package org.lwjgl.opengl;

import java.nio.LongBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBTimerQuery.class */
public class ARBTimerQuery {
    public static final int GL_TIME_ELAPSED = 35007;
    public static final int GL_TIMESTAMP = 36392;

    static {
        GL.initialize();
    }

    protected ARBTimerQuery() {
        throw new UnsupportedOperationException();
    }

    public static void glQueryCounter(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        GL33C.glQueryCounter(i, i2);
    }

    public static void nglGetQueryObjecti64v(int i, int i2, long j) {
        GL33C.nglGetQueryObjecti64v(i, i2, j);
    }

    public static void glGetQueryObjecti64v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint64 *") LongBuffer longBuffer) {
        GL33C.glGetQueryObjecti64v(i, i2, longBuffer);
    }

    public static void glGetQueryObjecti64v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint64 *") long j) {
        GL33C.glGetQueryObjecti64v(i, i2, j);
    }

    @NativeType("void")
    public static long glGetQueryObjecti64(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL33C.glGetQueryObjecti64(i, i2);
    }

    public static void nglGetQueryObjectui64v(int i, int i2, long j) {
        GL33C.nglGetQueryObjectui64v(i, i2, j);
    }

    public static void glGetQueryObjectui64v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint64 *") LongBuffer longBuffer) {
        GL33C.glGetQueryObjectui64v(i, i2, longBuffer);
    }

    public static void glGetQueryObjectui64v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint64 *") long j) {
        GL33C.glGetQueryObjectui64v(i, i2, j);
    }

    @NativeType("void")
    public static long glGetQueryObjectui64(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL33C.glGetQueryObjectui64(i, i2);
    }

    public static void glGetQueryObjecti64v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint64 *") long[] jArr) {
        GL33C.glGetQueryObjecti64v(i, i2, jArr);
    }

    public static void glGetQueryObjectui64v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint64 *") long[] jArr) {
        GL33C.glGetQueryObjectui64v(i, i2, jArr);
    }
}
