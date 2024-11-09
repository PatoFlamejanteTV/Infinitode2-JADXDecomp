package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBTextureStorage.class */
public class ARBTextureStorage {
    public static final int GL_TEXTURE_IMMUTABLE_FORMAT = 37167;

    public static native void glTextureStorage1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLenum") int i4, @NativeType("GLsizei") int i5);

    public static native void glTextureStorage2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLenum") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6);

    public static native void glTextureStorage3DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLenum") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7);

    static {
        GL.initialize();
    }

    protected ARBTextureStorage() {
        throw new UnsupportedOperationException();
    }

    public static void glTexStorage1D(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4) {
        GL42C.glTexStorage1D(i, i2, i3, i4);
    }

    public static void glTexStorage2D(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5) {
        GL42C.glTexStorage2D(i, i2, i3, i4, i5);
    }

    public static void glTexStorage3D(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6) {
        GL42C.glTexStorage3D(i, i2, i3, i4, i5, i6);
    }
}
