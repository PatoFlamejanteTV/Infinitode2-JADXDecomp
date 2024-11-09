package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBTextureView.class */
public class ARBTextureView {
    public static final int GL_TEXTURE_VIEW_MIN_LEVEL = 33499;
    public static final int GL_TEXTURE_VIEW_NUM_LEVELS = 33500;
    public static final int GL_TEXTURE_VIEW_MIN_LAYER = 33501;
    public static final int GL_TEXTURE_VIEW_NUM_LAYERS = 33502;
    public static final int GL_TEXTURE_IMMUTABLE_LEVELS = 33503;

    static {
        GL.initialize();
    }

    protected ARBTextureView() {
        throw new UnsupportedOperationException();
    }

    public static void glTextureView(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum") int i4, @NativeType("GLuint") int i5, @NativeType("GLuint") int i6, @NativeType("GLuint") int i7, @NativeType("GLuint") int i8) {
        GL43C.glTextureView(i, i2, i3, i4, i5, i6, i7, i8);
    }
}
