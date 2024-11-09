package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVCopyImage.class */
public class NVCopyImage {
    public static native void glCopyImageSubDataNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLuint") int i7, @NativeType("GLenum") int i8, @NativeType("GLint") int i9, @NativeType("GLint") int i10, @NativeType("GLint") int i11, @NativeType("GLint") int i12, @NativeType("GLsizei") int i13, @NativeType("GLsizei") int i14, @NativeType("GLsizei") int i15);

    static {
        GL.initialize();
    }

    protected NVCopyImage() {
        throw new UnsupportedOperationException();
    }
}
