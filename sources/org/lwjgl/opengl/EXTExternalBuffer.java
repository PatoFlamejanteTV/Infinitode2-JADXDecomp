package org.lwjgl.opengl;

import org.lwjgl.system.Checks;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTExternalBuffer.class */
public class EXTExternalBuffer {
    public static native void nglBufferStorageExternalEXT(int i, long j, long j2, long j3, int i2);

    public static native void nglNamedBufferStorageExternalEXT(int i, long j, long j2, long j3, int i2);

    static {
        GL.initialize();
    }

    protected EXTExternalBuffer() {
        throw new UnsupportedOperationException();
    }

    public static void glBufferStorageExternalEXT(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLeglClientBufferEXT") long j3, @NativeType("GLbitfield") int i2) {
        if (Checks.CHECKS) {
            Checks.check(j3);
        }
        nglBufferStorageExternalEXT(i, j, j2, j3, i2);
    }

    public static void glNamedBufferStorageExternalEXT(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLeglClientBufferEXT") long j3, @NativeType("GLbitfield") int i2) {
        if (Checks.CHECKS) {
            Checks.check(j3);
        }
        nglNamedBufferStorageExternalEXT(i, j, j2, j3, i2);
    }
}
