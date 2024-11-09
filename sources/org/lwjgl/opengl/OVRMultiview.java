package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/OVRMultiview.class */
public class OVRMultiview {
    public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_NUM_VIEWS_OVR = 38448;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_BASE_VIEW_INDEX_OVR = 38450;
    public static final int GL_MAX_VIEWS_OVR = 38449;
    public static final int GL_FRAMEBUFFER_INCOMPLETE_VIEW_TARGETS_OVR = 38451;

    public static native void glFramebufferTextureMultiviewOVR(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6);

    public static native void glNamedFramebufferTextureMultiviewOVR(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6);

    static {
        GL.initialize();
    }

    protected OVRMultiview() {
        throw new UnsupportedOperationException();
    }
}
