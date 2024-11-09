package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/AMDVertexShaderTessellator.class */
public class AMDVertexShaderTessellator {
    public static final int GL_SAMPLER_BUFFER_AMD = 36865;
    public static final int GL_INT_SAMPLER_BUFFER_AMD = 36866;
    public static final int GL_UNSIGNED_INT_SAMPLER_BUFFER_AMD = 36867;
    public static final int GL_DISCRETE_AMD = 36870;
    public static final int GL_CONTINUOUS_AMD = 36871;
    public static final int GL_TESSELLATION_MODE_AMD = 36868;
    public static final int GL_TESSELLATION_FACTOR_AMD = 36869;

    public static native void glTessellationFactorAMD(@NativeType("GLfloat") float f);

    public static native void glTessellationModeAMD(@NativeType("GLenum") int i);

    static {
        GL.initialize();
    }

    protected AMDVertexShaderTessellator() {
        throw new UnsupportedOperationException();
    }
}
