package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBSampleShading.class */
public class ARBSampleShading {
    public static final int GL_SAMPLE_SHADING_ARB = 35894;
    public static final int GL_MIN_SAMPLE_SHADING_VALUE_ARB = 35895;

    public static native void glMinSampleShadingARB(@NativeType("GLfloat") float f);

    static {
        GL.initialize();
    }

    protected ARBSampleShading() {
        throw new UnsupportedOperationException();
    }
}
