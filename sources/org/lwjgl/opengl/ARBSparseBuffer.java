package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBSparseBuffer.class */
public class ARBSparseBuffer {
    public static final int GL_SPARSE_STORAGE_BIT_ARB = 1024;
    public static final int GL_SPARSE_BUFFER_PAGE_SIZE_ARB = 33528;

    public static native void glBufferPageCommitmentARB(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLboolean") boolean z);

    public static native void glNamedBufferPageCommitmentEXT(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLboolean") boolean z);

    public static native void glNamedBufferPageCommitmentARB(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLboolean") boolean z);

    static {
        GL.initialize();
    }

    protected ARBSparseBuffer() {
        throw new UnsupportedOperationException();
    }
}
