package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVTextureMultisample.class */
public class NVTextureMultisample {
    public static final int GL_TEXTURE_COVERAGE_SAMPLES_NV = 36933;
    public static final int GL_TEXTURE_COLOR_SAMPLES_NV = 36934;

    public static native void glTexImage2DMultisampleCoverageNV(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLboolean") boolean z);

    public static native void glTexImage3DMultisampleCoverageNV(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLboolean") boolean z);

    public static native void glTextureImage2DMultisampleNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLboolean") boolean z);

    public static native void glTextureImage3DMultisampleNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLboolean") boolean z);

    public static native void glTextureImage2DMultisampleCoverageNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLboolean") boolean z);

    public static native void glTextureImage3DMultisampleCoverageNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLboolean") boolean z);

    static {
        GL.initialize();
    }

    protected NVTextureMultisample() {
        throw new UnsupportedOperationException();
    }
}
