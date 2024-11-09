package org.lwjgl.opengl;

import java.nio.IntBuffer;
import java.nio.LongBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBSync.class */
public class ARBSync {
    public static final int GL_MAX_SERVER_WAIT_TIMEOUT = 37137;
    public static final int GL_OBJECT_TYPE = 37138;
    public static final int GL_SYNC_CONDITION = 37139;
    public static final int GL_SYNC_STATUS = 37140;
    public static final int GL_SYNC_FLAGS = 37141;
    public static final int GL_SYNC_FENCE = 37142;
    public static final int GL_SYNC_GPU_COMMANDS_COMPLETE = 37143;
    public static final int GL_UNSIGNALED = 37144;
    public static final int GL_SIGNALED = 37145;
    public static final int GL_SYNC_FLUSH_COMMANDS_BIT = 1;
    public static final long GL_TIMEOUT_IGNORED = -1;
    public static final int GL_ALREADY_SIGNALED = 37146;
    public static final int GL_TIMEOUT_EXPIRED = 37147;
    public static final int GL_CONDITION_SATISFIED = 37148;
    public static final int GL_WAIT_FAILED = 37149;

    static {
        GL.initialize();
    }

    protected ARBSync() {
        throw new UnsupportedOperationException();
    }

    @NativeType("GLsync")
    public static long glFenceSync(@NativeType("GLenum") int i, @NativeType("GLbitfield") int i2) {
        return GL32C.glFenceSync(i, i2);
    }

    public static boolean nglIsSync(long j) {
        return GL32C.nglIsSync(j);
    }

    @NativeType("GLboolean")
    public static boolean glIsSync(@NativeType("GLsync") long j) {
        return GL32C.glIsSync(j);
    }

    public static void nglDeleteSync(long j) {
        GL32C.nglDeleteSync(j);
    }

    public static void glDeleteSync(@NativeType("GLsync") long j) {
        GL32C.glDeleteSync(j);
    }

    public static int nglClientWaitSync(long j, int i, long j2) {
        return GL32C.nglClientWaitSync(j, i, j2);
    }

    @NativeType("GLenum")
    public static int glClientWaitSync(@NativeType("GLsync") long j, @NativeType("GLbitfield") int i, @NativeType("GLuint64") long j2) {
        return GL32C.glClientWaitSync(j, i, j2);
    }

    public static void nglWaitSync(long j, int i, long j2) {
        GL32C.nglWaitSync(j, i, j2);
    }

    public static void glWaitSync(@NativeType("GLsync") long j, @NativeType("GLbitfield") int i, @NativeType("GLuint64") long j2) {
        GL32C.glWaitSync(j, i, j2);
    }

    public static void nglGetInteger64v(int i, long j) {
        GL32C.nglGetInteger64v(i, j);
    }

    public static void glGetInteger64v(@NativeType("GLenum") int i, @NativeType("GLint64 *") LongBuffer longBuffer) {
        GL32C.glGetInteger64v(i, longBuffer);
    }

    @NativeType("void")
    public static long glGetInteger64(@NativeType("GLenum") int i) {
        return GL32C.glGetInteger64(i);
    }

    public static void nglGetSynciv(long j, int i, int i2, long j2, long j3) {
        GL32C.nglGetSynciv(j, i, i2, j2, j3);
    }

    public static void glGetSynciv(@NativeType("GLsync") long j, @NativeType("GLenum") int i, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLint *") IntBuffer intBuffer2) {
        GL32C.glGetSynciv(j, i, intBuffer, intBuffer2);
    }

    @NativeType("void")
    public static int glGetSynci(@NativeType("GLsync") long j, @NativeType("GLenum") int i, @NativeType("GLsizei *") IntBuffer intBuffer) {
        return GL32C.glGetSynci(j, i, intBuffer);
    }

    public static void glGetInteger64v(@NativeType("GLenum") int i, @NativeType("GLint64 *") long[] jArr) {
        GL32C.glGetInteger64v(i, jArr);
    }

    public static void glGetSynciv(@NativeType("GLsync") long j, @NativeType("GLenum") int i, @NativeType("GLsizei *") int[] iArr, @NativeType("GLint *") int[] iArr2) {
        GL32C.glGetSynciv(j, i, iArr, iArr2);
    }
}
