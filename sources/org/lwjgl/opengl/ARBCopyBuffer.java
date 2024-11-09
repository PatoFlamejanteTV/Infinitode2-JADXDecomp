package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBCopyBuffer.class */
public class ARBCopyBuffer {
    public static final int GL_COPY_READ_BUFFER = 36662;
    public static final int GL_COPY_WRITE_BUFFER = 36663;

    static {
        GL.initialize();
    }

    protected ARBCopyBuffer() {
        throw new UnsupportedOperationException();
    }

    public static void glCopyBufferSubData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLintptr") long j2, @NativeType("GLsizeiptr") long j3) {
        GL31C.glCopyBufferSubData(i, i2, j, j2, j3);
    }
}
