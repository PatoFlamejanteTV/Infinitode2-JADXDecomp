package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVMemoryObjectSparse.class */
public class NVMemoryObjectSparse {
    public static native void glBufferPageCommitmentMemNV(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLuint") int i2, @NativeType("GLuint64") long j3, @NativeType("GLboolean") boolean z);

    public static native void glNamedBufferPageCommitmentMemNV(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLuint") int i2, @NativeType("GLuint64") long j3, @NativeType("GLboolean") boolean z);

    public static native void glTexPageCommitmentMemNV(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("GLuint") int i10, @NativeType("GLuint64") long j, @NativeType("GLboolean") boolean z);

    public static native void glTexturePageCommitmentMemNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("GLuint") int i10, @NativeType("GLuint64") long j, @NativeType("GLboolean") boolean z);

    static {
        GL.initialize();
    }

    protected NVMemoryObjectSparse() {
        throw new UnsupportedOperationException();
    }
}
