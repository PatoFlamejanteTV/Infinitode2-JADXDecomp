package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBClipControl.class */
public class ARBClipControl {
    public static final int GL_LOWER_LEFT = 36001;
    public static final int GL_UPPER_LEFT = 36002;
    public static final int GL_NEGATIVE_ONE_TO_ONE = 37726;
    public static final int GL_ZERO_TO_ONE = 37727;
    public static final int GL_CLIP_ORIGIN = 37724;
    public static final int GL_CLIP_DEPTH_MODE = 37725;

    static {
        GL.initialize();
    }

    protected ARBClipControl() {
        throw new UnsupportedOperationException();
    }

    public static void glClipControl(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        GL45C.glClipControl(i, i2);
    }
}
