package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBTextureStorageMultisample.class */
public class ARBTextureStorageMultisample {
    public static native void glTextureStorage2DMultisampleEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLenum") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLboolean") boolean z);

    public static native void glTextureStorage3DMultisampleEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLenum") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLboolean") boolean z);

    static {
        GL.initialize();
    }

    protected ARBTextureStorageMultisample() {
        throw new UnsupportedOperationException();
    }

    public static void glTexStorage2DMultisample(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLboolean") boolean z) {
        GL43C.glTexStorage2DMultisample(i, i2, i3, i4, i5, z);
    }

    public static void glTexStorage3DMultisample(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLboolean") boolean z) {
        GL43C.glTexStorage3DMultisample(i, i2, i3, i4, i5, i6, z);
    }
}
