package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBMapBufferRange.class */
public class ARBMapBufferRange {
    public static final int GL_MAP_READ_BIT = 1;
    public static final int GL_MAP_WRITE_BIT = 2;
    public static final int GL_MAP_INVALIDATE_RANGE_BIT = 4;
    public static final int GL_MAP_INVALIDATE_BUFFER_BIT = 8;
    public static final int GL_MAP_FLUSH_EXPLICIT_BIT = 16;
    public static final int GL_MAP_UNSYNCHRONIZED_BIT = 32;

    static {
        GL.initialize();
    }

    protected ARBMapBufferRange() {
        throw new UnsupportedOperationException();
    }

    public static long nglMapBufferRange(int i, long j, long j2, int i2) {
        return GL30C.nglMapBufferRange(i, j, j2, i2);
    }

    @NativeType("void *")
    public static ByteBuffer glMapBufferRange(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLbitfield") int i2) {
        return GL30C.glMapBufferRange(i, j, j2, i2);
    }

    @NativeType("void *")
    public static ByteBuffer glMapBufferRange(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLbitfield") int i2, ByteBuffer byteBuffer) {
        return GL30C.glMapBufferRange(i, j, j2, i2, byteBuffer);
    }

    public static void glFlushMappedBufferRange(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2) {
        GL30C.glFlushMappedBufferRange(i, j, j2);
    }
}
