package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTFramebufferBlit.class */
public class EXTFramebufferBlit {
    public static final int GL_READ_FRAMEBUFFER_EXT = 36008;
    public static final int GL_DRAW_FRAMEBUFFER_EXT = 36009;
    public static final int GL_DRAW_FRAMEBUFFER_BINDING_EXT = 36006;
    public static final int GL_READ_FRAMEBUFFER_BINDING_EXT = 36010;

    public static native void glBlitFramebufferEXT(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLint") int i7, @NativeType("GLint") int i8, @NativeType("GLbitfield") int i9, @NativeType("GLenum") int i10);

    static {
        GL.initialize();
    }

    protected EXTFramebufferBlit() {
        throw new UnsupportedOperationException();
    }
}
