package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTFramebufferMultisample.class */
public class EXTFramebufferMultisample {
    public static final int GL_RENDERBUFFER_SAMPLES_EXT = 36011;
    public static final int GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE_EXT = 36182;
    public static final int GL_MAX_SAMPLES_EXT = 36183;

    public static native void glRenderbufferStorageMultisampleEXT(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5);

    static {
        GL.initialize();
    }

    protected EXTFramebufferMultisample() {
        throw new UnsupportedOperationException();
    }
}
